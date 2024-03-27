package com.example.myloginpage;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class settings extends Fragment implements Toolbar.OnMenuItemClickListener {

SwitchCompat switchCompat;
TextView devrole,txt1,txt2;
boolean isNightmodeon;
    RelativeLayout t1;
Button updatepass;
    Toolbar toolbar;
    SharedPrefrencesHelper sharedPrefrencesHelper;
    public static final String IS_DARK = "IS_DARK";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_settings, container, false);
        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
        MainActivity activity = ((MainActivity) getActivity());
        activity.t1.setVisibility(View.GONE);
        txt1=view.findViewById(R.id.txt1);
        txt2=view.findViewById(R.id.txt2);

        updatepass = view.findViewById(R.id.upass);

        devrole = view.findViewById(R.id.devrole);




        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
        Integer role = Integer.valueOf(user.get(sharedPrefrencesHelper.ROLE));
        if(role.equals(50)){
            devrole.setText("Owner");

        } else if (role.equals(42)) {
            devrole.setText("Developer Lead");

        } else if (role.equals(41)) {
            devrole.setText("Developer");

        } else if (role.equals(40)) {
            devrole.setText("Super Administrator");

        }else if (role.equals(30)) {
            devrole.setText("Administrator");

        }else if (role.equals(20)) {
            devrole.setText("Editor");

        }else if (role.equals(15)) {
            devrole.setText("Updater");

        }
        else if (role.equals(10)) {
            devrole.setText("Viewer");

        }

        updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new updatepassword();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.frame1, fragment).addToBackStack(null).commit();

            }
        });


        switchCompat = view.findViewById(R.id.switch1);


        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((MainActivity)getActivity()).onchangedark();
                    txt2.setTextColor(Color.parseColor("#198754"));
                    txt1.setTextColor(Color.parseColor("#000000"));

                } else {

                    ((MainActivity)getActivity()).onchangelight();
                    txt1.setTextColor(Color.parseColor("#198754"));
                    txt2.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });
        boolean isNightModeOn = AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES;
            switchCompat.setChecked(isNightModeOn);
            if(isNightModeOn){
                txt2.setTextColor(Color.parseColor("#198754"));
                txt1.setTextColor(Color.parseColor("#FFFFFFFF"));

            }else {
                txt1.setTextColor(Color.parseColor("#198754"));
                txt2.setTextColor(Color.parseColor("#FF000000"));

            }


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