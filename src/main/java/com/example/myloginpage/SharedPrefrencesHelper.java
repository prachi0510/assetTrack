package com.example.myloginpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPrefrencesHelper {
        private SharedPreferences sharedPreferences;
        public SharedPreferences.Editor editor;
        private  static  final String PREF_NAME = "login";
    private  static  final String LOGIN = "is_login";
    public   static  final String NAME = "supervisor";
    public   static  final String EMAIL = "id";
    public   static  final String ID = "email";
    public   static  final String ROLE = "role";
    public   static  final String chklistID = "chklistID";
    public   static  final String LRVNOL= "leadVehicleID";
    public   static  final String LRVNOM= "midVehicleID";
    public   static  final String LRVNOT= "trailVehicleID";
    public   static  final String CONGF= "vehicleType";
        private Context context;
        int PRIVATE_MODE = 0;
    public SharedPrefrencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME,
                PRIVATE_MODE);
        this.context = context;
        editor = sharedPreferences.edit();
    }

    public void createSession(String name, String id, String role, String email){
        editor.putBoolean(LOGIN , true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, id);
        editor.putString(ROLE, role);
        editor.putString(ID, email);
        editor.apply();
    }
    public void createSession1(String chkid){
        editor.putBoolean(LOGIN , true);
        editor.putString(chklistID, chkid);

        editor.apply();
    }
    public void createSession2(String lrvnol,String lrvnot, String congf){
        editor.putBoolean(LOGIN , true);
        editor.putString(LRVNOL, lrvnol);
        editor.putString(LRVNOT, lrvnot);
        editor.putString(CONGF, congf);

        editor.apply();
    }
    public void createSession3(String lrvnol,String lrvnom,String lrvnot, String congf){
        editor.putBoolean(LOGIN , true);
        editor.putString(LRVNOL, lrvnol);
        editor.putString(LRVNOM, lrvnom);
        editor.putString(LRVNOT, lrvnot);
        editor.putString(CONGF, congf);

        editor.apply();
    }

    public boolean isLoggin(){

        return sharedPreferences.getBoolean(LOGIN,false);
    }
    public void checklogin(){
            if(!this.isLoggin()){
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }

    }
    public HashMap<String, String> getuserdetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME,null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ROLE, sharedPreferences.getString(ROLE,null));
        user.put(ID, sharedPreferences.getString(ID,null));
        return user;

    }
    public HashMap<String, String> getuserdetail1(){
        HashMap<String, String> user = new HashMap<>();
        user.put(chklistID, sharedPreferences.getString(chklistID,null));


        return user;

    }
    public HashMap<String, String> lrvdetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(LRVNOL, sharedPreferences.getString(LRVNOL,null));
        user.put(LRVNOT, sharedPreferences.getString(LRVNOT,null));
        user.put(CONGF, sharedPreferences.getString(CONGF,null));

        return user;

    }
    public HashMap<String, String> lrvdetails1(){
        HashMap<String, String> user = new HashMap<>();
        user.put(LRVNOL, sharedPreferences.getString(LRVNOL,null));
        user.put(LRVNOM, sharedPreferences.getString(LRVNOM,null));
        user.put(LRVNOT, sharedPreferences.getString(LRVNOT,null));
        user.put(CONGF, sharedPreferences.getString(CONGF,null));

        return user;

    }


    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);

    }
}
