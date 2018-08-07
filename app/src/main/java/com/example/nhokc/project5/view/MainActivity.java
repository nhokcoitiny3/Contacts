package com.example.nhokc.project5.view;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nhokc.project5.model.Contact;
import com.example.nhokc.project5.presenter.adapter.FragmentPagerAdapter;
import com.example.nhokc.project5.R;
import com.example.nhokc.project5.databinding.ActivityMainBinding;
import com.example.nhokc.project5.presenter.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentPagerAdapter adapter ;
    private static Context mContext;
    private Intent intent ;
    private List<Contact> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initialize();
        mContext= getApplicationContext();
        checkPermisionReadSms();
        checkPermisionReadContacts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        intent=new Intent(mContext, GetDataService.class);
        startService(intent);

    }

    private void initialize() {
        adapter = new FragmentPagerAdapter(this,getSupportFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.tabType.setupWithViewPager(binding.pager);
        binding.tabType.getTabAt(0).setText("Contacts");
        binding.tabType.getTabAt(1).setText("Inbox");
    }

    public void checkPermisionReadSms(){
        String permission =Manifest.permission.READ_SMS;
        if (ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                String[] permission_list = new String[1];
                permission_list[0] = permission;
                ActivityCompat.requestPermissions(this,permission_list,1);
            }
        }
    }
    public void checkPermisionReadContacts(){
        String permission =Manifest.permission.READ_CONTACTS;
        if (ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                String[] permission_list = new String[1];
                permission_list[0] = permission;
                ActivityCompat.requestPermissions(this,permission_list,1);
            }
        }
    }


    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
