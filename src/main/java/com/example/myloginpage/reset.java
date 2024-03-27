package com.example.myloginpage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class reset extends Fragment {

EditText et,et1;
Button reset;
    RequestQueue requestQueue;
    JSONArray jsonArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reset, container, false);

        et=view.findViewById(R.id.ps);
        et1=view.findViewById(R.id.ps1);
        reset = view.findViewById(R.id.update);
        requestQueue  = Volley.newRequestQueue(getActivity());

        Bundle bundle = getArguments();
        String email = bundle.getString("email");

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = et.getText().toString();
                String cpass = et1.getText().toString();

                if(pass.equals(cpass)){

                    updatepassword(pass,email);

                }else{

                    AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    //builder.setTitle("DialogBox");
                    builder.setMessage("Your passwords do not match");
                    builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }

            private void updatepassword(String pass, String email) {

                String url="https://assettrack.cx/android_api/reset.php?email="+email+"&password="+pass;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("2")) {
                                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                                builder.setCancelable(true);
                                //builder.setTitle("DialogBox");
                                builder.setMessage("Something went wrong, please contact an administrator");
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();
                            } else if (success.equals("1")) {

                                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                                builder.setCancelable(true);
                                //builder.setTitle("DialogBox");
                                builder.setMessage("Your password was successfully updated");
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();
                            movetomainactivity();    
                            
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    private void movetomainactivity() {
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                        ((Activity) getActivity()).overridePendingTransition(0, 0);

                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                        builder.setCancelable(true);
                        //builder.setTitle("DialogBox");
                        builder.setMessage(error.toString() +  "Your email wasn't recognized by our system, multiple attempts will block your access to the site");
                        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                });
                requestQueue.add(stringRequest);
            }


        });
       
   return view;
    }
}