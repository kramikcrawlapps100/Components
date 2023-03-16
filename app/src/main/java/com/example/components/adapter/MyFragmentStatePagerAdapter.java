package com.example.components.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.components.fragments.FirstFragment;
import com.example.components.fragments.SecondFragment;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public MyFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new FirstFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + (position + 1);
    }
}
