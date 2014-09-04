package com.dhn.marrysocial.fragment;

import com.dhn.marrysocial.adapter.ContactsListAdapter;
import com.dhn.marrysocial.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class DynamicInfoFragment extends Fragment {

    private static final String TAG = "Page02Fragment";

    private ListView mListView;
    private BaseAdapter mListViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.page02_fragment_layout,
                container, false);
//        mListView = (ListView) view.findViewById(R.id.community_listview);
//        mListViewAdapter = new CommunityContentListAdapter(getActivity());
//        mListView.setAdapter(mListViewAdapter);
        return view;
    }
}
