package com.example.nhokc.project5.presenter.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.nhokc.project5.model.Contact;
import com.example.nhokc.project5.model.Inbox;
import com.example.nhokc.project5.view.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GetDataService extends Service {
    private IBinder iBinder = new ContactService();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GetInboxAsyncTask getInboxAsyncTask = new GetInboxAsyncTask();
        getInboxAsyncTask.execute();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class GetInboxAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                getSms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }


    }

    public void getSms() throws InterruptedException {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("address"));
            String content = cursor.getString(cursor.getColumnIndex("body"));
            long time = Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            String date = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
            Inbox inbox = new Inbox(name, content, date);
            Bundle bundle = new Bundle();
            bundle.putSerializable("INBOX", inbox);
            Intent intent1 = new Intent();
            intent1.putExtra("DATA", bundle);
            intent1.setAction("MESSAGE");
            sendBroadcast(intent1);
            Thread.sleep(100);
        }
        Toast.makeText(getApplicationContext(),"Done!",Toast.LENGTH_LONG).show();
        cursor.close();
    }


    public class ContactService extends Binder {
        public GetDataService getService() {
            return GetDataService.this;
        }
    }

    public List<Contact> getContacts() {
        List<Contact> list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            String id  = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            list.add(new Contact(name,number));
        }
        cursor.close();
            return list;
    }

}
