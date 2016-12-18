package com.nmp90.pictureminer.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.databinding.ItemPictureBinding;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by joro on 18.12.16.
 */

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> {
    private final List<Picture> pictures;
    private PublishSubject<Picture> onSaveListener;
    private PublishSubject<Picture> onShareListener;

    public PicturesAdapter(List<Picture> pictures) {
        this.pictures = pictures;
        onShareListener = PublishSubject.create();
        onSaveListener = PublishSubject.create();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPictureBinding binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);

        binding.tvSave.setOnClickListener(view -> {
            onSaveListener.onNext(pictures.get(viewHolder.getAdapterPosition()));
        });

        binding.tvShare.setOnClickListener(view -> {
            onShareListener.onNext(pictures.get(viewHolder.getAdapterPosition()));
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setPicture(pictures.get(position));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public Observable<Picture> getSaveListener() {
        return onSaveListener;
    }

    public Observable<Picture> getShareListener() {
        return onShareListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPictureBinding binding;

        public ViewHolder(ItemPictureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
