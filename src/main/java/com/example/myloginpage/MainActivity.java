
    package com.example.myloginpage;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

import java.util.concurrent.Executor;


    public class MainActivity extends AppCompatActivity {

        Button blogin;
        EditText etlogin, etpassword;
        TextView v1,v2,v3;
        RelativeLayout t1;
        private  String email,password;
        RequestQueue requestQueue;
        SharedPrefrencesHelper sharedPrefrencesHelper;
        JSONArray jsonArray;
       BiometricPrompt biometricPrompt;
       BiometricPrompt.PromptInfo promptInfo;
       RelativeLayout rel;










        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            Toolbar toolbar;
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main);



            requestQueue  = Volley.newRequestQueue(getApplicationContext());
            sharedPrefrencesHelper = new SharedPrefrencesHelper(this);
           // email = password = "";
            blogin = findViewById(R.id.b_login);
            etlogin= findViewById(R.id.Username);
            etpassword = findViewById(R.id.password);
            t1 = findViewById(R.id.rel1);
            v3=findViewById(R.id.header);
            v1=findViewById(R.id.fpass);
            v2=findViewById(R.id.passf);
            v2.setVisibility(View.GONE);



            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate()){

                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(MainActivity.this, "dont have fingerprint",Toast.LENGTH_SHORT).show();
                    break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(MainActivity.this, "not working",Toast.LENGTH_SHORT).show();
                        break;
                        case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                            Toast.makeText(MainActivity.this, "no fingerprint",Toast.LENGTH_SHORT).show();
                            break;
            }

            Executor executor = ContextCompat.getMainExecutor(this);
            biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);

                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
//                    String email = etlogin.getText().toString().trim();
//                    String password = etpassword.getText().toString().trim();
//                    if(!email.isEmpty() || !password.isEmpty()){
//                        login(email,password);
//                    }else {
//                        Toast.makeText(MainActivity.this,"enter email & password",Toast.LENGTH_SHORT).show();
//                    }
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                }
            });

            promptInfo=new BiometricPrompt.PromptInfo.Builder().setTitle("Crosslionx Transit Solution").setDescription("User fingerprint to login").setDeviceCredentialAllowed(true).build();
            biometricPrompt.authenticate(promptInfo);



            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blogin.setVisibility(View.GONE);
                    etlogin.setVisibility(View.GONE);
                    etpassword.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);
                    v3.setVisibility(View.GONE);

                    Fragment fragment = new forgotpass();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();
                }
            });

            blogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String email = etlogin.getText().toString().trim();
                    String password = etpassword.getText().toString().trim();
                    if(!email.isEmpty() || !password.isEmpty()){
                        login(email,password);
                    }else {
                        Toast.makeText(MainActivity.this,"enter email & password",Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

        public void onchangelight() {
           t1.setVisibility(View.INVISIBLE);
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
        }

        public void onchangedark() {
            t1.setVisibility(View.GONE);
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
        }

        private void login(String email, String password) {

            String url="https://assettrack.cx/android_api/login1.php?email=" +email+ "&password=" +password;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        jsonArray = jsonObject.getJSONArray("login");
                        if (success.equals("1")){
                            blogin.setVisibility(View.GONE);
                            etlogin.setVisibility(View.GONE);
                            etpassword.setVisibility(View.GONE);
                            t1.setVisibility(View.GONE);
                            v3.setVisibility(View.GONE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String name = jsonObject1.getString("supervisor").trim();
                                String id = jsonObject1.getString("id").trim();
                                String role = jsonObject1.getString("role").trim();
                                String email = jsonObject1.getString("email").trim();
                                sharedPrefrencesHelper.createSession(name,id,role,email);
                                etlogin.setVisibility(View.GONE);
                                etpassword.setVisibility(View.GONE);
                                blogin.setVisibility(View.GONE);
                                Fragment fragment = new one();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame1, fragment).commit();
                            } }
                        else if(success.equals("2")) {
                           // Toast.makeText(MainActivity.this,"Account Does not exists",Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(true);
                            //builder.setTitle("DialogBox");
                            builder.setMessage("Account Does not exists");
                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }else if(success.equals("3")) {
                            //Toast.makeText(MainActivity.this,"Your account has been suspended.Please contact an administrator",Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(true);
                            //builder.setTitle("DialogBox");
                            builder.setMessage("Your account has been suspended.Please contact an administrator");
                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();

                        }else if(success.equals("4")) {
                         //   Toast.makeText(MainActivity.this,"Account not yet activated - Please check your email for activation link",Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(true);
                            //builder.setTitle("DialogBox");
                            builder.setMessage("Account not yet activated - Please check your email for activation link");
                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }else if(success.equals("5")) {

                            setVisible(v2);
                            setvisiblegone(blogin,etlogin,etpassword,v1);

                        }else if(success.equals("6")) {



//                           blogin.setVisibility(View.GONE);
//                            etlogin.setVisibility(View.GONE);
//                            etpassword.setVisibility(View.GONE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String count = jsonObject1.getString("count").trim();
                                //Toast.makeText(MainActivity.this,"Email & Password are Incorrect",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setCancelable(true);
                                builder.setMessage("You have " +count+ " login or password reset attempts, you will be blocked from logging in for 60 minutes after 5 attempts");
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();
                            }

                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                private void setvisiblegone(Button blogin, EditText etlogin, EditText etpassword, TextView v1) {

                    blogin.setVisibility(View.GONE);
                    etlogin.setVisibility(View.GONE);
                    etpassword.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            blogin.setVisibility(View.VISIBLE);
                            etlogin.setVisibility(View.VISIBLE);
                            etpassword.setVisibility(View.VISIBLE);
                            v1.setVisibility(View.VISIBLE);
                            etlogin.setText("");
                            etpassword.setText("");

                        }
                    }, 100000 * 6 );
                }

                private void setVisible(TextView v2) {


                            v2.setVisibility(View.VISIBLE);
                            v2.setText("You have exceeded the permitted attempts, you have been blocked for 60 minutes");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            v2.setVisibility(View.GONE);

                        }
                    }, 100000 * 6 );

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequest);
        }


    }

