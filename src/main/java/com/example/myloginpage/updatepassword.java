package com.example.myloginpage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class updatepassword extends Fragment {
    SharedPrefrencesHelper sharedPrefrencesHelper;
    TextView txt,txt1,txt2,txt3;
    EditText newpass,confpass,curpass;
    Button submit;
    JSONArray jsonArray;
    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_updatepassword, container, false);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        requestQueue  = Volley.newRequestQueue(getActivity());
        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
        String name = user.get(sharedPrefrencesHelper.NAME);
        String email = user.get(sharedPrefrencesHelper.ID);

        txt = view.findViewById(R.id.txt);
        txt.setText("Update password for " + name);
        txt.setTextColor(Color.BLACK);

        txt1 = view.findViewById(R.id.cpasst);
        txt2=view.findViewById(R.id.npasst);
        txt3=view.findViewById(R.id.cnpasst);
        txt1.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        txt3.setVisibility(View.GONE);
        curpass = view.findViewById(R.id.cpass);
        newpass = view.findViewById(R.id.newpass);
        confpass = view.findViewById(R.id.confpass);
        submit = view.findViewById(R.id.button);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confpass1 = curpass.getText().toString();
                if(!confpass1.isEmpty()) {


                        checkpassword(email, confpass1);


                }else {

                    AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    //builder.setTitle("DialogBox");
                    builder.setMessage("Please Fill All The Fields");
                    builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }


            }

            private void checkpassword(String email, String confpass1) {


                String url="https://assettrack.cx/android_api/updatepass.php?email="+email+"&password="+confpass1;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")) {
                                txt1.setVisibility(View.VISIBLE);
                                String npass = newpass.getText().toString();
                                String cpass = confpass.getText().toString();
                                if (validatePassword(newpass.getText().toString())) {
                                    if (!npass.isEmpty() && !cpass.isEmpty()) {
                                        if (npass.equals(cpass)) {
                                            txt2.setVisibility(View.VISIBLE);
                                            txt3.setVisibility(View.VISIBLE);


                                            String url1="https://assettrack.cx/android_api/reset.php?email="+email+"&password="+npass;

                                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        String success = jsonObject.getString("success");
                                                        jsonArray = jsonObject.getJSONArray("login");
                                                        if (success.equals("1")) {


                                                          Fragment fragment = new settings();

                                                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                            fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit();
                                                        }

                                                    } catch (JSONException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }




                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            requestQueue.add(stringRequest);




                                        } else {

                                            Toast.makeText(getActivity(), "password did not match", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setCancelable(true);
                                        //builder.setTitle("DialogBox");
                                        builder.setMessage("Please Fill All The Fields");
                                        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        builder.show();

                                    }
                                    }else {

                                    Toast.makeText(getActivity(), "Not a valid password", Toast.LENGTH_SHORT).show();
                                }
                            }else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setCancelable(true);
                                //builder.setTitle("DialogBox");
                                builder.setMessage("Current password did not match");
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();

                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }




                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
                requestQueue.add(stringRequest);

            }
        });

   return view;
    }

    public boolean validatePassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,32}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

}