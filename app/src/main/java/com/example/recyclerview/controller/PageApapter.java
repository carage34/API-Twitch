package com.example.recyclerview.controller;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageApapter extends FragmentPagerAdapter {
    private int nbTab;
    public PageApapter(FragmentManager fm,int nbTab) {
        super(fm);
        this.nbTab = nbTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:

        }
    }
}
