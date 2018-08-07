package com.example.nhokc.project5.presenter.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhokc.project5.R;
import com.example.nhokc.project5.model.Inbox;
import com.example.nhokc.project5.model.holder.InboxHolder;
import com.example.nhokc.project5.presenter.iml.IInbox;
import com.example.nhokc.project5.databinding.ItemRvInboxBinding;
import com.example.nhokc.project5.presenter.iml.IOnClickItemRvListener;

public class RvInboxAdapter  extends RecyclerView.Adapter<InboxHolder>{
    private ItemRvInboxBinding binding;
    private IOnClickItemRvListener listener;
    private IInbox iInbox;

    public void setData(IInbox iInbox){
        this.iInbox = iInbox;
    }
    @NonNull
    @Override
    public InboxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_rv_inbox,parent,false);
        return new InboxHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxHolder holder, final int position) {
        Inbox inbox = iInbox.getInbox(position);
        holder.binding.txtName.setText(inbox.getName());
        holder.binding.txtContent.setText(inbox.getContent());
        holder.binding.txtTime.setText(inbox.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickItemRvListener(position,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iInbox.getCount();
    }

    public void setOnClickItemListener(IOnClickItemRvListener listener){
        this.listener = listener;
    }

}
