package com.hanyuzhou.accountingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    LinkedList<MainFragment> fragments = new LinkedList<>();

    LinkedList<String> dates = new LinkedList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments(){
        dates = GlobalUtil.getInstance().databaseHelper.getAvaliableDate();

        if (!dates.contains(DateUtil.getFormattedDate())){
            dates.addLast(DateUtil.getFormattedDate());
        }

        for (String date:dates){
            MainFragment fragment = new MainFragment(date);
            fragments.add(fragment);
        }
    }

    public void reload(){
        for (MainFragment fragment :
                fragments) {
            fragment.reload();
        }
    }

    public int getLatsIndex(){
        return fragments.size()-1;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public String getDateStr(int index){
        return dates.get(index);
    }

    public int getTotalCost(int index){
        return fragments.get(index).getTotalCost();
    }
}
