package com.example.recyclerview.controller;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.view.MainActivity;
import com.example.recyclerview.view.PageFragment;
import com.example.recyclerview.view.SampleTwo;
import com.example.recyclerview.view.StreamFragment;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {
    private int nbTab;
    private List<Streamer> list;
    private MainActivity actt;
    public PageAdapter(FragmentManager fm) {
        super(fm);
        nbTab = 2;
        //this.list = streamerList;
    }

    public void setList(List<Streamer> listt, MainActivity act) {
        this.list = listt;
        this.actt = act;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position) {
            case 0:
                return PageFragment.newInstance(list, actt);
            case 1:
                return SampleTwo.newInstance(actt);
        }
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Stream";
            case 1:
                return "Clips";
            default:
                return null;
        }
    }
    public int getCount() {
        return nbTab;
    }
}
