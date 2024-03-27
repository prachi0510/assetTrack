package com.example.myloginpage;

import android.content.DialogInterface;
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

public class code extends Fragment {
EditText et;
Button update;
    RequestQueue requestQueue;
    JSONArray jsonArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_code, container, false);

        et=view.findViewById(R.id.et);
        update=view.findViewById(R.id.update);
        requestQueue  = Volley.newRequestQueue(getActivity());
        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = et.getText().toString();

                 if(!code.isEmpty()){
                     getcode(email,code);
                 }else {
                     AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                     builder.setCancelable(true);
                     //builder.setTitle("DialogBox");
                     builder.setMessage("Please Fill in this field");
                     builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     });
                     builder.show();
                 }

            }

            private void getcode(String email, String code) {

                String url="https://assettrack.cx/android_api/getcode.php?email=" +email +" &vc=" +code;


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
                                builder.setMessage("Your account could not be located");
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();
                            } else if (success.equals("1")) {
                                Bundle bundle= new Bundle();
                                bundle.putString("email",email);

                                Fragment fragment =new reset();
                                fragment.setArguments(bundle);
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();

                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
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