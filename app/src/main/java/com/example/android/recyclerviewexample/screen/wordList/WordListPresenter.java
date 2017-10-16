package com.example.android.recyclerviewexample.screen.wordList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.data.word.WordDataSource;
import com.example.android.recyclerviewexample.data.word.WordRepository;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link WordListFragment}), retrieves the data and updates
 * the UI as required.
 */
final class WordListPresenter implements WordListContract.Presenter {
    private static final String TAG = WordListPresenter.class.getSimpleName();
    private final WordListContract.ViewModel mViewModel;
    private final WordRepository mWordRepository;
    private BroadcastReceiver mBroadcastReceiver;
    private IntentFilter mBroadcastReceiverIntentFilter;
    private List<Word> mWords;

    public WordListPresenter(WordListContract.ViewModel viewModel, WordRepository wordRepository) {
        mViewModel = viewModel;
        mWordRepository = wordRepository;
        initBroadcastReceiver();
    }
    
    public void initBroadcastReceiver(){
        mBroadcastReceiverIntentFilter = new IntentFilter();
        mBroadcastReceiverIntentFilter.addAction("updateProgress");
        mBroadcastReceiverIntentFilter.addAction("dismiss");
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onBroadcastReceive: " + intent.getAction());
                if(intent.getAction() == null){
                    return;
                }
                switch (intent.getAction()){
                    case "updateProgress":
                        mViewModel.updateProgressDialog(intent.getIntExtra("progress", 1));
                        break;
                    case "dismiss":
                        mViewModel.dismissProgressDialog();
                        break;
                }
            }
        };
    }

    @Override
    public void onStart() {
        if (mWordRepository != null) {
            mWordRepository.deleteAll();
            mWordRepository.addWord(new Word("a"));
            mWordRepository.addWord(new Word("b"));
            mWordRepository.addWord(new Word("c"));
            mWordRepository.getAllWords(new WordDataSource.LoadAllWordsCallback() {
                @Override
                public void onAllWordLoaded(List<Word> words) {
                    mWords = words;
                    mViewModel.changeDataSet(mWords);
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
        mViewModel.registerBroadcastReceiver(mBroadcastReceiver, mBroadcastReceiverIntentFilter);
    }

    @Override
    public void onStop() {
        mViewModel.unregisterBroadcastReceiver(mBroadcastReceiver);
    }

    @Override
    public void onItemWordListClicked(Word word) {
        Log.d(TAG, word.getWord() + " " + WordListPresenter.class.getName());
    }

    @Override
    public void onButtonLoadingClick(View v) {
        mViewModel.showProgressDialog();
    }
}
