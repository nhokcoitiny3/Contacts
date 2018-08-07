package com.example.nhokc.project5.presenter.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nhokc.project5.view.ContactsFragment;
import com.example.nhokc.project5.view.InboxFragment;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    public FragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context =context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new ContactsFragment();
            case 1 :
                return  new InboxFragment();
            default:
                return new ContactsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
