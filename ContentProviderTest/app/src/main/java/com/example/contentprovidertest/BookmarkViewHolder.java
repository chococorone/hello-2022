package com.example.contentprovidertest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contentprovidertest.databinding.ActivityMainBinding;
import com.example.contentprovidertest.databinding.BookmarksListBinding;

public class BookmarkViewHolder extends RecyclerView.ViewHolder {
    // ViewHolderはviewを保持する→ここで保持するviewはlistの中の一要素のこと
    // listの中の一要素に対してdata bindingするから、BookmarksListBindingを指定する ★ポイント
    private BookmarksListBinding mBinding;
    private static final String TAG = "CHOCO-ViewHolder";

    public BookmarkViewHolder(BookmarksListBinding binding){
        super(binding.getRoot());
        mBinding = binding;

    }

    public void bindTo(Bookmark bookmark, LifecycleOwner lifecycleOwner, MainViewModel viewModel){
        mBinding.setLifecycleOwner(lifecycleOwner);
        mBinding.setViewModel(viewModel);
        mBinding.setBookmark(bookmark);
        Log.d(TAG, bookmark.title);

        mBinding.executePendingBindings();
    }

}
