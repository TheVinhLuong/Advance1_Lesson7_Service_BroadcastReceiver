package com.example.android.recyclerviewexample.screen.wordList;

<<<<<<< Updated upstream
=======
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
>>>>>>> Stashed changes
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.recyclerviewexample.R;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.data.word.WordRepository;
import com.example.android.recyclerviewexample.data.word.local.WordLocalDatasource;
import com.example.android.recyclerviewexample.databinding.FragmentWordListBinding;
import com.example.android.recyclerviewexample.screen.BaseFragment;
<<<<<<< Updated upstream
=======
import com.example.android.recyclerviewexample.service.CountdownService;
>>>>>>> Stashed changes
import java.util.ArrayList;

/**
 * WordList Screen.
 */
<<<<<<< Updated upstream
public class WordListFragment extends BaseFragment {
    private WordListContract.ViewModel mViewModel;
    private WordListAdapter mWordListAdapter;
    private WordRepository mWordRepository;
=======
public class WordListFragment extends BaseFragment implements WordListContract.View{
    private WordListContract.ViewModel mViewModel;
    private WordListAdapter mWordListAdapter;
    private WordRepository mWordRepository;
    private ProgressDialog mProgressDialog;
>>>>>>> Stashed changes

    public WordListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        ArrayList<Word> words = new ArrayList<>();
        mWordRepository = WordRepository.getInstance(new WordLocalDatasource(getActivity()));
        mWordListAdapter = new WordListAdapter(getActivity(), words);
        mViewModel = new WordListViewModel(mWordListAdapter);
        WordListContract.Presenter presenter = new WordListPresenter(mViewModel, mWordRepository);
        mViewModel.setPresenter(presenter);
=======
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        ArrayList<Word> words = new ArrayList<>();
        mWordRepository = WordRepository.getInstance(new WordLocalDatasource(getActivity()));
        mWordListAdapter = new WordListAdapter(getActivity(), words);
        mViewModel = new WordListViewModel(mWordListAdapter, this);
        WordListContract.Presenter presenter = new WordListPresenter(mViewModel, mWordRepository);
        mViewModel.setPresenter(presenter);
        
>>>>>>> Stashed changes
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentWordListBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false);
        binding.setViewModel((WordListViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
<<<<<<< Updated upstream
=======

    @Override
    public void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void unregisterBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void showProgressDialog() {
        getActivity().runOnUiThread(() -> {
            mProgressDialog.show();
            getActivity().startService(new Intent(getActivity(), CountdownService.class));
        });
    }

    @Override
    public void updateProgressDialog(int progress) {
        getActivity().runOnUiThread(() -> {
            mProgressDialog.setProgress(progress);
        });
    }

    @Override
    public void dismissProgressDialog() {
        getActivity().runOnUiThread(() -> {
            mProgressDialog.dismiss();
        });
    }
>>>>>>> Stashed changes
}
