package com.example.myloginpage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;


public class one extends Fragment implements Toolbar.OnMenuItemClickListener {

    Button B1, B2;
    Toolbar toolbar;
    SharedPrefrencesHelper sharedPrefrencesHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_one, container, false);

        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");

        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);




        B1 = view.findViewById(R.id.pdc);
        B2 = view.findViewById(R.id.install);


        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
        Integer role = Integer.valueOf(user.get(sharedPrefrencesHelper.ROLE));
        if(role <= 15){

            B2.setVisibility(View.GONE);

        }else {
            B2.setVisibility(View.VISIBLE);
        }

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new PDC();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.frame1, fragment2).commit();
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment1 = new Install();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.frame1, fragment1).commit();


            }
        });


        return view;
    }




    private void finish() {


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
            fm.replace(R.id.frame1, fragment2).commit();

        }else if (item.getItemId() == R.id.OSC) {

            Fragment fragment1 = new Install();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame1, fragment1).addToBackStack(null).commit();

        }

        return true;
    }
}




