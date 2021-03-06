package com.dhn.marrysocial;

import java.util.ArrayList;

import com.dhn.marrysocial.adapter.ViewPagerFragmentAdapter;
import com.dhn.marrysocial.fragment.ChatMsgFragment;
import com.dhn.marrysocial.fragment.DynamicInfoFragment;
import com.dhn.marrysocial.fragment.ContactsListFragment;
import com.dhn.marrysocial.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class MarrySocialMainActivity extends FragmentActivity {

    private static final String TAG = "MarrySocialMainActivity";

    private ViewPager mViewPager;
    private ArrayList<Fragment> mViewFragmentSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.marrysocial_main);
        initViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mViewFragmentSets = new ArrayList<Fragment>();
        mViewFragmentSets.add(new ChatMsgFragment());
        mViewFragmentSets.add(new DynamicInfoFragment());
        mViewFragmentSets.add(new ContactsListFragment());

        mViewPager.setAdapter(new ViewPagerFragmentAdapter(
                getSupportFragmentManager(), mViewFragmentSets));

        TabPageIndicator headBarIndicator = (TabPageIndicator) findViewById(R.id.community_headbar_indicator);
        headBarIndicator.setViewPager(mViewPager);
        headBarIndicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public void showMesage(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showMesage(R.string.exit_confirm);
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
