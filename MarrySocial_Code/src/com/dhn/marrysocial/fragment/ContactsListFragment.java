package com.dhn.marrysocial.fragment;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.dhn.marrysocial.adapter.ContactsListAdapter;
import com.dhn.marrysocial.R;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsListFragment extends Fragment {

    private static final String TAG = "ContactsListFragment";

    private ListView mListView;
    private ContactsListAdapter mListViewAdapter;
    private ArrayList<ContactEntry> mContactMembers = new ArrayList<ContactEntry>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactMembers = getAllContactsInfo(getActivity());
        // getAllContactsInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.contacts_list_fragment_layout,
                container, false);
        mListView = (ListView) view.findViewById(R.id.contacts_listview);
        TextView emptyView = (TextView) view
                .findViewById(R.id.contacts_list_empty);
        mListViewAdapter = new ContactsListAdapter(getActivity());
        mListViewAdapter.setDataSource(mContactMembers);
        mListView.setAdapter(mListViewAdapter);
        mListView.setEmptyView(emptyView);
        return view;
    }

    // private void getAllContactsInfo() {
    // Cursor cursor = getActivity().getContentResolver().query(
    // ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    // int contactIdIndex = 0;
    // int nameIndex = 0;
    //
    // if (cursor.getCount() > 0) {
    // contactIdIndex = cursor.getColumnIndex(BaseColumns._ID);
    // nameIndex = cursor
    // .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
    // }
    //
    // while (cursor.moveToNext()) {
    // String contactId = cursor.getString(contactIdIndex);
    // String name = cursor.getString(nameIndex);
    // Log.i(TAG, "nannan contactId = " + contactId);
    // Log.i(TAG, "nannan name = " + name);
    //
    // Cursor phones = getActivity().getContentResolver().query(
    // ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
    // null,
    // ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
    // + contactId, null, null);
    // int phoneIndex = 0;
    // if (phones.getCount() > 0) {
    // phoneIndex = phones
    // .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    // }
    // while (phones.moveToNext()) {
    // String phoneNumber = phones.getString(phoneIndex);
    // Log.i(TAG, "nannan phoneNumber = " + phoneNumber);
    // }
    //
    // }
    // }

    public static class ContactEntry {
        public String contact_name;
        public String contact_phone_number;
        public int contact_id;
        public String contact_sortKey;
    }

    private ArrayList<ContactEntry> getAllContactsInfo(Activity context) {
        ArrayList<ContactEntry> contactMembers = new ArrayList<ContactEntry>();
        Cursor cursor = null;

        try {
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            // 获取联系人表的电话里的信息 包括：名字，名字拼音，联系人id,电话号码；
            // 然后在根据"sort-key"排序
            cursor = context.getContentResolver().query(
                    uri,
                    new String[] { "display_name", "sort_key", "contact_id",
                            "data1" }, null, null, "sort_key");

            while (cursor.moveToNext()) {
                ContactEntry contact = new ContactEntry();
                String name = cursor.getString(0);
                String sortKey = getSortKey(cursor.getString(1));
                String contact_phone = cursor
                        .getString(cursor
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                int contact_id = cursor
                        .getInt(cursor
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                contact.contact_name = name;
                contact.contact_sortKey = sortKey;
                contact.contact_phone_number = contact_phone;
                contact.contact_id = contact_id;
                if (name != null && isPhoneNumber(contact_phone)) {
                    contactMembers.add(contact);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return contactMembers;
    }

    private static String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }

    private static boolean isPhoneNumber(String input) {

        if (input == null) {
            return false;
        }

        String regex = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
        Pattern p = Pattern.compile(regex);
        return p.matcher(input).matches();
    }

}