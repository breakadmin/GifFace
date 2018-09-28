package pers.lbreak.emoji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * viewPager+fragment 适配器
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragment;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        this.fragment=fragment;
    }


    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }
}
