package com.example.contentprovidertest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contentprovidertest.databinding.ActivityMainBinding;
import com.example.contentprovidertest.databinding.BookmarksListBinding;

class BookmarkListAdapter extends ListAdapter<Bookmark, BookmarkViewHolder>{
    private final LifecycleOwner mLifecycleOwner;
    private final MainViewModel mMainViewModel;

    public BookmarkListAdapter(LifecycleOwner lifecycleOwner, MainViewModel mainViewModel){
        super(DIFF_CALLBACK);
        mLifecycleOwner = lifecycleOwner;
        mMainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new BookmarkViewHolder(BookmarksListBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        holder.bindTo(getItem(position), mLifecycleOwner, mMainViewModel);
    }

    public static final DiffUtil.ItemCallback<Bookmark> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Bookmark>() {
                @Override
                public boolean areItemsTheSame(@NonNull Bookmark oldItem, @NonNull Bookmark newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Bookmark oldItem, @NonNull Bookmark newItem) {
                    return oldItem.equals(newItem);
                }
            };

}

