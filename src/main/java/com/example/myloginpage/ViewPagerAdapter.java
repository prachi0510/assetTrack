package com.example.myloginpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myloginpage.lrvlist.chklist;
import com.example.myloginpage.lrvlist.leadv;
import com.example.myloginpage.lrvlist.midv;
import com.example.myloginpage.lrvlist.trailv;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment = null;
        if(position ==0) {
            fragment= new chklist();
        } else if (position ==1) {
            fragment= new leadv();
        }

        else if (position == 2)
        {
            fragment = new trailv();
        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
