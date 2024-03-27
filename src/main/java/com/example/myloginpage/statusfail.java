package com.example.myloginpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class statusfail extends Fragment implements Toolbar.OnMenuItemClickListener {

TableLayout tableLayout;
    SharedPrefrencesHelper sharedPrefrencesHelper;


TabItem tabItem;


    Toolbar toolbar;
    TabLayout tabLayout,tabLayout1;
   ViewPager2 viewPager2,viewPager21;
    ViewPagerAdapter viewPagerAdapter;
    ViewPagerAdapter3 viewPagerAdapter3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_statusfail, container, false);

        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        Bundle bundle = getArguments();
        String congf = bundle.getString("conf");


        tabLayout = view.findViewById(R.id.tab);
        tabLayout1 = view.findViewById(R.id.tab1);
        tabLayout1.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        viewPager2= view.findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager21= view.findViewById(R.id.pager1);
        viewPagerAdapter3 = new ViewPagerAdapter3(getActivity());
        viewPager21.setAdapter(viewPagerAdapter3);

        if(congf.equals("2")) {
            tabLayout.setVisibility(View.VISIBLE);
            tabLayout1.setVisibility(View.GONE);
           viewPager2.setVisibility(View.VISIBLE);
            viewPager21.setVisibility(View.GONE);


        } else if (congf.equals("3")) {
            tabLayout.setVisibility(View.GONE);
            tabLayout1.setVisibility(View.VISIBLE);
            viewPager21.setVisibility(View.VISIBLE);
            viewPager2.setVisibility(View.GONE);

        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager21.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});

        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.logout){
            sharedPrefrencesHelper.logout();
        } else if (item.getItemId()==R.id.setting) {

            Fragment fragment2 = new settings();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame1, fragment2).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.pdc1) {

            Fragment fragment2 = new PDC();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame1, fragment2).addToBackStack(null).commit();

        }else if (item.getItemId() == R.id.OSC) {

            Fragment fragment1 = new Install();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame1, fragment1).addToBackStack(null).commit();
        }
        return  true;
    }
}