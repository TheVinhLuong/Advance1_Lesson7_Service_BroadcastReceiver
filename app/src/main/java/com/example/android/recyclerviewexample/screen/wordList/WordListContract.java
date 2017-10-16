package com.example.android.recyclerviewexample.screen.wordList;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.screen.BasePresenter;
import com.example.android.recyclerviewexample.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface WordListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void changeDataSet(List<Word> words);
        void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);
        void unregisterBroadcastReceiver(BroadcastReceiver broadcastReceiver);
        void showProgressDialog();
        void updateProgressDialog(int preogress);
        void dismissProgressDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordListClicked(Word word);
        void onButtonLoadingClick(android.view.View v);
    }
    
    interface View{
        void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);
        void unregisterBroadcastReceiver(BroadcastReceiver broadcastReceiver);
        void showProgressDialog();
        void updateProgressDialog(int preogress);
        void dismissProgressDialog();
    }
}
