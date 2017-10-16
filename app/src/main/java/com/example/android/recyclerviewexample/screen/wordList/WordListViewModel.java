package com.example.android.recyclerviewexample.screen.wordList;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.view.View;
import com.example.android.recyclerviewexample.data.word.Word;
import java.util.List;

/**
 * Exposes the data to be used in the WordList screen.
 */

public class WordListViewModel implements WordListContract.ViewModel, WordListAdapter.OnRecyclerViewItemClickListener {

    private WordListContract.Presenter mPresenter;
    private WordListAdapter mAdapter;
    private WordListContract.View mView;

    public WordListViewModel(WordListAdapter adapter, WordListContract.View view) {
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
        mView = view;
    }

    public WordListAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(WordListAdapter adapter) {
        mAdapter = adapter;
    }

    public WordListContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void changeDataSet(List<Word> words){
        mAdapter.changeDataSet(words);
    }

    @Override
    public void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver,
            IntentFilter intentFilter) {
        mView.registerBroadcastReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void unregisterBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        mView.unregisterBroadcastReceiver(broadcastReceiver);
    }

    @Override
    public void showProgressDialog() {
        mView.showProgressDialog();
    }

    @Override
    public void updateProgressDialog(int progress) {
        mView.updateProgressDialog(progress);
    }

    @Override
    public void dismissProgressDialog() {
        mView.dismissProgressDialog();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(WordListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(View view, Object item) {
        mPresenter.onItemWordListClicked((Word)item);
    }
}
