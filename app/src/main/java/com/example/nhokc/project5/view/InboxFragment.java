package com.example.nhokc.project5.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhokc.project5.R;
import com.example.nhokc.project5.databinding.FragmentInboxBinding;
import com.example.nhokc.project5.model.Inbox;
import com.example.nhokc.project5.presenter.adapter.RvInboxAdapter;
import com.example.nhokc.project5.presenter.iml.IInbox;
import com.example.nhokc.project5.presenter.iml.IOnClickItemRvListener;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment implements IInbox,IOnClickItemRvListener {
    private FragmentInboxBinding binding;
    private BroadcastReceiver broadcastReceiver;
    private List<Inbox> list = new ArrayList<>();
    private RvInboxAdapter adapter = new RvInboxAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_inbox,container,false);
        initialize();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras().getBundle("DATA");
                Inbox inbox = (Inbox) bundle.getSerializable("INBOX");
                list.add(inbox);
                adapter.notifyDataSetChanged();
            }
        };
        IntentFilter intentFilter = new IntentFilter(
                "MESSAGE");
        getContext().registerReceiver(broadcastReceiver,intentFilter);
        return binding.getRoot();
    }

    private void initialize() {
        adapter.setData(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvInbox.setLayoutManager(layoutManager);
        binding.rvInbox.setAdapter(adapter);
        adapter.setOnClickItemListener(this);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Inbox getInbox(int position) {
        return list.get(position);
    }

    @Override
    public void onClickItemRvListener(int position, int id) {
        showDialog(position);
    }

    private void showDialog(int position){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_inbox);
        TextView txtName = dialog.findViewById(R.id.txt_name);
        TextView txtDate = dialog.findViewById(R.id.txt_time);
        TextView txtContent = dialog.findViewById(R.id.txt_content);
        Button btnOk = dialog.findViewById(R.id.btn_ok);

        txtName.setText(list.get(position).getName());
        txtDate.setText(list.get(position).getDate());
        txtContent.setText(list.get(position).getContent());
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onDestroy() {
        getContext().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
