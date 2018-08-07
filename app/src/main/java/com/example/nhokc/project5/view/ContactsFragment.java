package com.example.nhokc.project5.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhokc.project5.R;
import com.example.nhokc.project5.databinding.FragmentContactsBinding;
import com.example.nhokc.project5.model.Contact;
import com.example.nhokc.project5.presenter.adapter.RvContactsAdapter;
import com.example.nhokc.project5.presenter.iml.IContact;
import com.example.nhokc.project5.presenter.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

public class ContactsFragment extends Fragment implements IContact {
    private FragmentContactsBinding binding;
    private List<Contact> contacts = new ArrayList<>();
    private RvContactsAdapter adapter = new RvContactsAdapter();
    private Intent intent ;
    private GetDataService getDataService = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_contacts, container, false);
        contacts=((MainActivity)getActivity()).getContacts();
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        intent=new Intent(getContext(), GetDataService.class);
        getContext().bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GetDataService.ContactService binder = (GetDataService.ContactService) iBinder;
            getDataService = binder.getService();
            contacts = getDataService.getContacts();
            initialize();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    private void initialize() {
        adapter.setData(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvContact.setLayoutManager(layoutManager);
        binding.rvContact.setAdapter(adapter);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getContact(int position) {
        return contacts.get(position);
    }
}
