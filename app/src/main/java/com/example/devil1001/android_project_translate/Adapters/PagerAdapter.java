package com.example.devil1001.android_project_translate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.devil1001.android_project_translate.Views.HistoryFragment;
import com.example.devil1001.android_project_translate.Views.TranslatorFragment;

/**
 * Created by devil1001 on 17.04.17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int tabsCount;

    public PagerAdapter(FragmentManager fm, int tabsCount) {
        super(fm);
        this.tabsCount = tabsCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TranslatorFragment tab1 = new TranslatorFragment();
                return tab1;
            case 1:
                HistoryFragment tab2 = new HistoryFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}