package com.example.myloginpage;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class PDC extends Fragment implements AdapterView.OnItemSelectedListener, Toolbar.OnMenuItemClickListener {
    Spinner spinnerconf, spinnerleadv, spinnertrailv, spinnerleadv3car, spinnermidv3car, spinnertrailv3car ;
    ArrayList<String> confList = new ArrayList<>();
    ArrayList<String> lvehicleList = new ArrayList<>();
    ArrayList<String> mvehicleList = new ArrayList<>();
    ArrayList<String> tvehiclelistList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    ArrayAdapter<String> lvehicleAdapter;
    ArrayAdapter<String> mvehicleAdapter;
    ArrayAdapter<String> tvehicleAdapter;
    JSONArray jsonArray,jsonArray1;
    RequestQueue requestQueue;
    TextView textView,textView1,txt,txt1,txt2,txt3,txt4;
    LinearLayout linearLayout,linearLayout1,ln1,ln2;
    Button button,reset;

    String id,id1;
    //String chkid;
    SharedPrefrencesHelper sharedPrefrencesHelper;


Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_d_c, container, false);

        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");

        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);


        requestQueue = Volley.newRequestQueue(getActivity());
        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        spinnerconf =view.findViewById(R.id.sconf);
        spinnerleadv =view.findViewById(R.id.sleadV);
        spinnertrailv =view.findViewById(R.id.strailv);
        spinnerleadv3car=view.findViewById(R.id.sleadv3car);
        spinnermidv3car =view.findViewById(R.id.smidv3car);
        spinnertrailv3car =view.findViewById(R.id.strailv3car);
        linearLayout =view.findViewById(R.id.linear1);
        linearLayout1=view.findViewById(R.id.linear2);
        ln1=view.findViewById(R.id.ln1);
        ln2=view.findViewById(R.id.ln2);
        textView=view.findViewById(R.id.txt1);
        textView1=view.findViewById(R.id.txt2);
        txt=view.findViewById(R.id.leadvehicle);
        txt1=view.findViewById(R.id.trailvehicle);
        txt2=view.findViewById(R.id.leadvehicle3car);
        txt3=view.findViewById(R.id.midvehicle3car);
        txt4=view.findViewById(R.id.trailvehicle3car);
        button= view.findViewById(R.id.submit);
        reset = view.findViewById(R.id.b_reset);
        spinnerleadv.setVisibility(View.GONE);
        spinnertrailv.setVisibility(View.GONE);
        spinnerleadv3car.setVisibility(View.GONE);
        spinnermidv3car.setVisibility(View.GONE);
        spinnertrailv3car.setVisibility(View.GONE);

        linearLayout.setVisibility(View.GONE);
        linearLayout1.setVisibility(View.GONE);
        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
        txt1.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        txt3.setVisibility(View.GONE);
        txt4.setVisibility(View.GONE);



        String url = "https://assettrack.cx/android_api/vehicletype.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    jsonArray = response.getJSONArray("data");
                    confList.add(new String("Choose Configration"));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String locationName = jsonObject.optString("vehicle");
                        confList.add(locationName);
                        vehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, confList);
                        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerconf.setAdapter(vehicleAdapter);
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
        requestQueue.add(jsonObjectRequest);
        spinnerconf.setOnItemSelectedListener(this);
        spinnerleadv.setOnItemSelectedListener(this);
        spinnertrailv.setOnItemSelectedListener(this);
        spinnerleadv3car.setOnItemSelectedListener(this);
        spinnermidv3car.setOnItemSelectedListener(this);
        spinnertrailv3car.setOnItemSelectedListener(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    String cong = spinnerconf.getSelectedItem().toString().trim();
                String conf = String.valueOf(spinnerconf.getSelectedItemId() + 1);

                if (conf.equals("2")) {

                    String leadv1 = String.valueOf(spinnerleadv.getSelectedItemId());
                    String date = textView1.getText().toString().trim();
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String uid = user.get(sharedPrefrencesHelper.EMAIL);
                    if (spinnerleadv.getSelectedItemId() > spinnertrailv.getSelectedItemId()) {
                        String tve = String.valueOf(spinnertrailv.getSelectedItemId());
                        String url2 = "https://assettrack.cx/android_api/newlrvchecklist.php?vehicleType=" + conf + "&leadVehicleID=" + leadv1 + "&trailVehicleID=" + tve + "&supervisor=" + uid;
                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject1 = new JSONObject(response);
                                    String success = jsonObject1.getString("success");

                                    jsonArray1 = jsonObject1.getJSONArray("updated");
                                    if (success.equals("1")) {

                                        for (int i = 0; i < jsonArray1.length(); i++) {
                                            JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                            String chkid = jsonObject2.getString("id").trim();
                                            sharedPrefrencesHelper.createSession1(chkid);
                                            Bundle bundle = new Bundle();
                                            String lead = spinnerleadv.getSelectedItem().toString().trim();
                                            String leadid = String.valueOf(spinnerleadv.getSelectedItemId());
                                            String trailv = spinnertrailv.getSelectedItem().toString().trim();
                                            String trailid = String.valueOf(spinnertrailv.getSelectedItemId());
                                            bundle.putString("config", conf);
                                            bundle.putString("lead", lead);
                                            bundle.putString("trail", trailv);
                                            bundle.putString("leadid", leadid);
                                            bundle.putString("trailid", trailid);
                                            bundle.putString("date", date);
                                            bundle.putString("chkid", chkid);

                                            //Toast.makeText(getActivity().getBaseContext(), chkid ,Toast.LENGTH_SHORT).show();
                                            Fragment fragment = new pdcoverview();
                                            fragment.setArguments(bundle);
                                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.frame1, fragment).commit();

                                        }

                                    }

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();


                            }
                        });
                        requestQueue.add(stringRequest1);

                    } else if (spinnerleadv.getSelectedItemId() < spinnertrailv.getSelectedItemId() || spinnerleadv.getSelectedItemId() == spinnertrailv.getSelectedItemId()) {

                        String tve = String.valueOf((spinnertrailv.getSelectedItemId() + 1));
                        String url2 = "https://assettrack.cx/android_api/newlrvchecklist.php?vehicleType=" + conf + "&leadVehicleID=" + leadv1 + "&trailVehicleID=" + tve + "&supervisor=" + uid;


                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject1 = new JSONObject(response);
                                    String success = jsonObject1.getString("success");
                                    //  Toast.makeText(getActivity().getBaseContext(),success,Toast.LENGTH_SHORT).show();

                                    jsonArray1 = jsonObject1.getJSONArray("updated");
                                    if (success.equals("1")) {

                                        for (int i = 0; i < jsonArray1.length(); i++) {
                                            JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                            String chkid = jsonObject2.getString("id").trim();
                                            sharedPrefrencesHelper.createSession1(chkid);
                                            Bundle bundle = new Bundle();
                                            String lead = spinnerleadv.getSelectedItem().toString().trim();
                                            String leadid = String.valueOf(spinnerleadv.getSelectedItemId());
                                            String trailv = spinnertrailv.getSelectedItem().toString().trim();
                                            if (spinnerleadv.getSelectedItemId() < spinnertrailv.getSelectedItemId() || spinnerleadv.getSelectedItemId() == spinnertrailv.getSelectedItemId()) {


                                                String trailid = String.valueOf(spinnertrailv.getSelectedItemId() + 1);
                                                bundle.putString("trailid", trailid);
                                            } else {
                                                String trailid = String.valueOf(spinnertrailv.getSelectedItemId());
                                                bundle.putString("trailid", trailid);
                                            }
                                            bundle.putString("config", conf);
                                            bundle.putString("lead", lead);
                                            bundle.putString("trail", trailv);
                                            bundle.putString("leadid", leadid);

                                            bundle.putString("date", date);
                                            bundle.putString("chkid", chkid);

                                            //Toast.makeText(getActivity().getBaseContext(), chkid ,Toast.LENGTH_SHORT).show();
                                            Fragment fragment = new pdcoverview();
                                            fragment.setArguments(bundle);
                                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.frame1, fragment).commit();

                                        }

                                    } else if (success.equals("2")) {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setCancelable(true);
                                        //builder.setTitle("DialogBox");
                                        builder.setMessage("Error: LRV Config Details could not be added");
                                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                                // Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();


                            }
                        });
                        requestQueue.add(stringRequest1);


                    }


                } else if (conf.equals("3")) {


                    String leadv1 = String.valueOf(spinnerleadv3car.getSelectedItemId());
                   // String tv1 = String.valueOf(spinnertrailv3car.getSelectedItemId());

                    String date = textView1.getText().toString();
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String uid = user.get(sharedPrefrencesHelper.EMAIL);
                    if (spinnerleadv3car.getSelectedItemId() > spinnermidv3car.getSelectedItemId()||spinnermidv3car.getSelectedItemId() > spinnertrailv3car.getSelectedItemId()) {
                        String mv = String.valueOf(spinnermidv3car.getSelectedItemId());

                            String tv1 = String.valueOf(spinnertrailv3car.getSelectedItemId());

                            String url1 = "https://assettrack.cx/android_api/newlrvchecklist1.php?vehicleType=" + conf + "&leadVehicleID=" + leadv1 + "&midVehicleID=" + mv + "&trailVehicleID=" + tv1 + "&supervisor=" + uid;
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonObject1 = new JSONObject(response);
                                        String success = jsonObject1.getString("success");
                                        // Toast.makeText(getActivity().getBaseContext(),success,Toast.LENGTH_SHORT).show();

                                        jsonArray1 = jsonObject1.getJSONArray("updated");
                                        if (success.equals("1")) {

                                            for (int i = 0; i < jsonArray1.length(); i++) {
                                                JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                                String chkid = jsonObject2.getString("id").trim();
                                                sharedPrefrencesHelper.createSession1(chkid);
                                                Bundle bundle = new Bundle();
                                                String lead = spinnerleadv3car.getSelectedItem().toString().trim();
                                                String trailv = spinnertrailv3car.getSelectedItem().toString().trim();
                                                String midv = spinnermidv3car.getSelectedItem().toString().trim();
                                                // Toast.makeText(getActivity(),midv,Toast.LENGTH_SHORT).show();
                                                String leadvid = String.valueOf(spinnerleadv3car.getSelectedItemId());
                                                String midvid = String.valueOf(spinnermidv3car.getSelectedItemId());
                                                // Toast.makeText(getActivity(),midvid,Toast.LENGTH_SHORT).show();
                                                String trailvid = String.valueOf(spinnertrailv3car.getSelectedItemId());
                                                bundle.putString("config", conf);
                                                bundle.putString("lead", lead);
                                                bundle.putString("trail", trailv);
                                                bundle.putString("mid", midv);
                                                bundle.putString("leadvid", leadvid);
                                                bundle.putString("traivlid", trailvid);
                                                bundle.putString("midvid", midvid);
                                                bundle.putString("date", date);
                                                bundle.putString("chkid", chkid);


                                                Fragment fragment = new pdcoverview();
                                                fragment.setArguments(bundle);
                                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.frame1, fragment).commit();
                                            }
                                        } else if (success.equals("2")) {

                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                            builder.setCancelable(true);
                                            //builder.setTitle("DialogBox");
                                            builder.setMessage("Error: LRV Config Details could not be added");
                                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                                    // Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();


                                }
                            });
                            requestQueue.add(stringRequest);

                    } else if (spinnerleadv3car.getSelectedItemId() < spinnermidv3car.getSelectedItemId() || spinnerleadv3car.getSelectedItemId() == spinnermidv3car.getSelectedItemId() || spinnermidv3car.getSelectedItemId() < spinnertrailv3car.getSelectedItemId() ||spinnermidv3car.getSelectedItemId()== spinnertrailv3car.getSelectedItemId()) {
                        String mv = String.valueOf(spinnermidv3car.getSelectedItemId() + 1);

                            String tv1 = String.valueOf(spinnertrailv3car.getSelectedItemId()+2);
                            String url1 = "https://assettrack.cx/android_api/newlrvchecklist1.php?vehicleType=" + conf + "&leadVehicleID=" + leadv1 + "&midVehicleID=" + mv + "&trailVehicleID=" + tv1 + "&supervisor=" + uid;
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonObject1 = new JSONObject(response);
                                        String success = jsonObject1.getString("success");
                                        // Toast.makeText(getActivity().getBaseContext(),success,Toast.LENGTH_SHORT).show();

                                        jsonArray1 = jsonObject1.getJSONArray("updated");
                                        if (success.equals("1")) {

                                            for (int i = 0; i < jsonArray1.length(); i++) {
                                                JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                                String chkid = jsonObject2.getString("id").trim();
                                                sharedPrefrencesHelper.createSession1(chkid);
                                                Bundle bundle = new Bundle();
                                                String lead = spinnerleadv3car.getSelectedItem().toString().trim();
                                                String trailv = spinnertrailv3car.getSelectedItem().toString().trim();
                                                String midv = spinnermidv3car.getSelectedItem().toString().trim();
                                                // Toast.makeText(getActivity(),midv,Toast.LENGTH_SHORT).show();
                                                String leadvid = String.valueOf(spinnerleadv3car.getSelectedItemId());
                                                String midvid = String.valueOf(spinnermidv3car.getSelectedItemId());
                                                // Toast.makeText(getActivity(),midvid,Toast.LENGTH_SHORT).show();
                                                String trailvid = String.valueOf(spinnertrailv3car.getSelectedItemId());
                                                bundle.putString("config", conf);
                                                bundle.putString("lead", lead);
                                                bundle.putString("trail", trailv);
                                                bundle.putString("mid", midv);
                                                bundle.putString("leadvid", leadvid);
                                                bundle.putString("traivlid", trailvid);
                                                bundle.putString("midvid", midvid);
                                                bundle.putString("date", date);
                                                bundle.putString("chkid", chkid);


                                                Fragment fragment = new pdcoverview();
                                                fragment.setArguments(bundle);
                                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.frame1, fragment).commit();
                                            }
                                        } else if (success.equals("2")) {

                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                            builder.setCancelable(true);
                                            //builder.setTitle("DialogBox");
                                            builder.setMessage("Error: LRV Config Details could not be added");
                                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                                    // Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();


                                }
                            });
                            requestQueue.add(stringRequest);
                        }
                    }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("");
                textView1.setText("");
                spinnerconf.setSelection(0);
                spinnerleadv.setSelection(0);
                spinnertrailv.setSelection(0);
                spinnerleadv3car.setSelection(0);
                spinnermidv3car.setSelection(0);
                spinnertrailv3car.setSelection(0);
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.GONE);
                ln1.setVisibility(View.GONE);
                ln2.setVisibility(View.GONE);
            }
        });


        return  view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
        String selectedCountry = spinnerconf.getSelectedItem().toString();
        if (selectedCountry.equals("2 Car Consist")) {
            txt.setVisibility(View.VISIBLE);
            if (adapterView.getId() == R.id.sconf) {
                lvehicleList.clear();

                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
                spinnerleadv.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
                // spinnerRoom.setVisibility(View.GONE);
                String url = "https://assettrack.cx/android_api/getvehiclelist.php";
                requestQueue = Volley.newRequestQueue(getActivity());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("data");
                            lvehicleList.add(new String("Choose Lead Vehicle"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String cityName = jsonObject.optString("lrv_no");
                                lvehicleList.add(cityName);
                                lvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, lvehicleList);
                                lvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerleadv.setAdapter(lvehicleAdapter);
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
                requestQueue.add(jsonObjectRequest);
            }

            if (adapterView.getId() == R.id.sleadV) {
                tvehiclelistList.clear();

                String selectedRoom = spinnerleadv.getSelectedItem().toString();
                String id = String.valueOf(spinnerleadv.getSelectedItemId());

                if (selectedRoom.equals("6200")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);

                }
                else if (selectedRoom.equals("6201")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6202")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6203")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6204")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6205")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6206")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6207")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6208")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6209")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6210")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6211")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6212")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6213")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6214")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6215")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6216")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6217")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6218")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6219")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6220")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6221")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6222")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6223")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6224")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6225")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6226")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6227")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6228")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6229")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6230")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6231")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6232")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6233")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6234")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6235")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6236")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6237")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;

                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6238")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;

                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6239")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;

                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6240")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6241")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6242")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6243")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6244")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6245")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6246")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6247")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6248")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6249")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6250")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6251")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6252")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6253")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6254")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6255")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6256")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6257")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6258")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6259")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6260")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6261")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6262")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6263")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6264")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6265")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6266")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);

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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6267")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6268")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6269")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6270")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6271")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6272")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (selectedRoom.equals("6273")) {
                    spinnertrailv.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle)"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String roomName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(roomName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
            }

            if (adapterView.getId() == R.id.strailv) {
                String selecttv = spinnertrailv.getSelectedItem().toString().trim();
                if (selecttv.equals("6200")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    //String name1 = user.get(sharedPrefrencesHelper.EMAIL);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6201")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6202")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6203")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6204")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6205")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6206")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6207")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6208")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6209")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6210")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6211")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6212")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6213")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6214")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6215")) {

                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6216")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6217")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6218")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6219")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6220")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6221")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6222")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6223")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6224")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6225")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6226")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6227")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6228")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6229")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6230")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6231")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6232")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6233")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6234")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6235")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6236")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6237")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6238")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6239")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6240")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6241")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6242")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6243")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6244")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6245")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6246")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6247")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6248")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6249")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6250")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6251")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6252")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6253")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6254")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6255")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6256")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6257")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6258")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6259")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6260")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6261")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6262")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6263")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6264")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6265")) {
                     ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6266")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6267")) {
                    textView.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);
                    textView1.setVisibility(View.VISIBLE);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6268")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6269")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6270")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6271")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6272")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6273")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                }

            }
        }
        else if (selectedCountry.equals("3 Car Consist")) {


            linearLayout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);

            if (adapterView.getId() == R.id.sconf) {


                spinnerleadv3car.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
                String url = "https://assettrack.cx/android_api/getvehiclelist.php";
                requestQueue = Volley.newRequestQueue(getActivity());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("data");
                            lvehicleList.add(new String("Choose Lead Vehicle"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String cityName = jsonObject.optString("lrv_no");
                                lvehicleList.add(cityName);
                                lvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, lvehicleList);
                                lvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerleadv3car.setAdapter(lvehicleAdapter);
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
                requestQueue.add(jsonObjectRequest);
            }


            if (adapterView.getId() == R.id.sleadv3car) {
                mvehicleList.clear();
                String leadv = spinnerleadv3car.getSelectedItem().toString().trim();
                String id = String.valueOf(spinnerleadv3car.getSelectedItemId());
                if (leadv.equals("6200")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (leadv.equals("6201")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6202")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6203")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php";
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6204")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6205")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6206")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);  }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6207")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6208")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;

                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6209")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6210")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6211")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6212")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6213")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6214")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6215")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6216")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6217")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6218")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6219")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6220")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6221")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6222")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6223")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6224")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6225")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6226")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6227")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6228")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6229")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6230")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("62031")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6232")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6233")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6234")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6235")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6236")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6237")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6238")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6239")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6240")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6241")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6242")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6243")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6244")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6245")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6246")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6247")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6248")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6249")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6250")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6251")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6252")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6253")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6254")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6255")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6256")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6257")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6258")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6259")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6260")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6261")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6262")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6263")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6264")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6265")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6266")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6267")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6268")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6269")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6270")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6271")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6272")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (leadv.equals("6273")) {
                    spinnermidv3car.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                mvehicleList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    mvehicleList.add(cityName);
                                    mvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, mvehicleList);
                                    mvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnermidv3car.setAdapter(mvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }

            }

            if (adapterView.getId() == R.id.smidv3car) {
                tvehiclelistList.clear();
                String midv = spinnermidv3car.getSelectedItem().toString().trim();
               id1 = String.valueOf(spinnerleadv3car.getSelectedItemId());
                if(spinnerleadv3car.getSelectedItemId() > spinnermidv3car.getSelectedItemId()){
                    id = String.valueOf(spinnermidv3car.getSelectedItemId());

                } else if (spinnerleadv3car.getSelectedItemId() < spinnermidv3car.getSelectedItemId()||spinnerleadv3car.getSelectedItemId() == spinnermidv3car.getSelectedItemId()) {


                    id = String.valueOf(spinnermidv3car.getSelectedItemId()+1);
                }

                if(midv.equals("6200")){
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Mid Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);
                }
                else if (midv.equals("6201")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6202")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6203")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6204")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6205")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6206")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6207")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6208")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6209")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6210")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6211")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6212")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6213")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6214")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6215")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6216")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6217")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6218")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6219")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6220")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6221")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6222")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6223")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);

                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6224")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6225")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6226")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6227")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Lead Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6228")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6229")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6230")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6231")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6232")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6233")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6234")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6235")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6236")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6237")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6238")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6239")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6240")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6241")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6242")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6243")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6244")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6245")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6246")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6247")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6248")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6249")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6250")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6251")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6252")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6253")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6254")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6255")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6256")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6257")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6258")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6259")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6260")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6261")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6262")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6263")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6264")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6265")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6266")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6267")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6268")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6269")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6270")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6271")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6272")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }
                else if (midv.equals("6273")) {
                    spinnertrailv3car.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    String url = "https://assettrack.cx/android_api/gettrailvehicle3.php?id="+id1+"&tid="+id;
                    requestQueue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("data");
                                tvehiclelistList.add(new String("Choose Trail Vehicle"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cityName = jsonObject.optString("lrv_no");
                                    tvehiclelistList.add(cityName);
                                    tvehicleAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, tvehiclelistList);
                                    tvehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnertrailv3car.setAdapter(tvehicleAdapter);
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
                    requestQueue.add(jsonObjectRequest);


                }

            }
            if (adapterView.getId() == R.id.strailv3car) {
                String selecttv = spinnertrailv3car.getSelectedItem().toString().trim();
                if (selecttv.equals("6200")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);

                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6201")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6202")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6203")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6204")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6205")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6206")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6207")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6208")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6209")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6210")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);
                   ;
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6211")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6212")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6213")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6214")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);

                } else if (selecttv.equals("6215")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6216")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6217")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6218")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6219")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6220")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6221")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6222")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6223")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6224")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);
                   ;
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6225")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6226")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6227")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6228")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6229")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6230")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6231")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6232")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6233")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6234")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6235")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6236")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6237")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6238")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6239")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6240")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6241")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6242")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6243")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6244")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6245")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6246")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6247")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6248")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6249")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6250")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6251")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6252")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6253")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6254")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6255")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6256")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6257")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6258")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6259")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6260")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6261")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6262")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6263")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6264")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);;
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6265")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6266")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6267")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6268")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6269")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6270")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6271")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6272")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                } else if (selecttv.equals("6273")) {
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                    String name = user.get(sharedPrefrencesHelper.NAME);
                    textView.setText(name);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    textView1.setText(formattedDate);
                }

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item){
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
            return  true;


    }
}