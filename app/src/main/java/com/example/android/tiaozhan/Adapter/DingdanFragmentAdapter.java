package com.example.android.tiaozhan.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 72909 on 2016/8/5.
 */
public class DingdanFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    List<String> list1;

    public DingdanFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> list1) {
        super(fm);
        this.list = list;
        this.list1 = list1;
    }



    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list1.get(position);
    }
}
