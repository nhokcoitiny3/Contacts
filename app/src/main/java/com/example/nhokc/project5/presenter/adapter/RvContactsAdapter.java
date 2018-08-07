package com.example.nhokc.project5.presenter.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nhokc.project5.R;
import com.example.nhokc.project5.databinding.ItemRvContactBinding;
import com.example.nhokc.project5.model.Contact;
import com.example.nhokc.project5.model.holder.ContactHolder;
import com.example.nhokc.project5.presenter.iml.IContact;

public class RvContactsAdapter extends RecyclerView.Adapter<ContactHolder> {
    private ItemRvContactBinding binding;
    private IContact iContact;

    public void setData(IContact iContact) {
        this.iContact = iContact;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_rv_contact,parent,false);
        return new ContactHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = iContact.getContact(position);
        holder.binding.txtName.setText(contact.getName());
        holder.binding.txtNumber.setText(contact.getNumber());
    }

    @Override
    public int getItemCount() {
        return iContact.getCount();
    }
}
