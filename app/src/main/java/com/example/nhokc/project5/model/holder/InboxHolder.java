package com.example.nhokc.project5.model.holder;

import android.support.v7.widget.RecyclerView;

import com.example.nhokc.project5.databinding.ItemRvInboxBinding;

public class InboxHolder extends RecyclerView.ViewHolder {
    public ItemRvInboxBinding binding;
    public InboxHolder(ItemRvInboxBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
}
