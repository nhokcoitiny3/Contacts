package com.example.nhokc.project5.model.holder;

import android.support.v7.widget.RecyclerView;

import com.example.nhokc.project5.databinding.ItemRvContactBinding;

public class ContactHolder extends RecyclerView.ViewHolder {

    public ItemRvContactBinding binding;
    public ContactHolder(ItemRvContactBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
