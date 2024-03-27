package com.example.myloginpage;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import java.util.ArrayList;
import java.util.HashMap;


public class Install extends Fragment implements AdapterView.OnItemSelectedListener, Toolbar.OnMenuItemClickListener {

    Spinner spinnerLocation,spinnerSystem,spinnerRoom;
    ArrayList<String> locationList = new ArrayList<>();
    ArrayList<String> systemList = new ArrayList<>();
    ArrayList<String> roomList = new ArrayList<>();
    ArrayAdapter<String> LocationAdapter;
    ArrayAdapter<String> systemAdapter;
    ArrayAdapter<String> roomAdapter;
    RequestQueue requestQueue;
    TextView textView5,textView6,textview7,textView8;
JSONArray jsonArray;
    TableRow tableRow1;
    LinearLayout lin,lin1,lin2;
    TableLayout tableLayout1,tableLayout;
    SharedPrefrencesHelper sharedPrefrencesHelper;
    Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_install, container, false);

        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();

        tableLayout1= view.findViewById(R.id.table1);
        requestQueue = Volley.newRequestQueue(getActivity());
        spinnerLocation=view.findViewById(R.id.slocation);
        spinnerSystem= view.findViewById(R.id.ssubsystem);
        spinnerRoom=view.findViewById(R.id.sroom);

        lin=view.findViewById(R.id.lin);
        lin1=view.findViewById(R.id.lin1);
        lin2=view.findViewById(R.id.lin2);
        lin1.setVisibility(View.GONE);
        lin.setVisibility(View.GONE);
        lin2.setVisibility(View.GONE);
        String url = "https://sandpit.assettrack.cx/api_android/get_api.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jsonArray = response.getJSONArray("data");
                    locationList.add(new String("Choose Location"));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String locationName = jsonObject.optString("LocationName");
                        locationList.add(locationName);
                        LocationAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, locationList);
                        LocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerLocation.setAdapter(LocationAdapter);

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
        spinnerLocation.setOnItemSelectedListener(this);
        spinnerSystem.setOnItemSelectedListener(this);
        spinnerRoom.setOnItemSelectedListener(this);
        return  view;

    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        if (adapterView.getId() == R.id.slocation) {
            systemList.clear();


            String selectedCountry = spinnerLocation.getSelectedItem().toString();
            String url = "https://sandpit.assettrack.cx/api_android/api_subsystem.php?LocationName=" + selectedCountry;
            requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        jsonArray = response.getJSONArray("asset");
                        systemList.add(new String("Choose Subsystem"));
                        lin.setVisibility(View.VISIBLE);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cityName = jsonObject.optString("systemName");
                            systemList.add(cityName);
                            systemAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, systemList);
                            systemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerSystem.setAdapter(systemAdapter);
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


        if(adapterView.getId() == R.id.ssubsystem){
            roomList.clear();

            tableLayout1.removeAllViews();

            String selectloc1 = spinnerLocation.getSelectedItem().toString().trim();
            String selectedRoom = spinnerSystem.getSelectedItem().toString().trim();
            String url = "https://sandpit.assettrack.cx/api_android/get_room.php?LocationName="+selectloc1+ "&systemName=" + selectedRoom;
            requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        jsonArray = response.getJSONArray("data");
                        roomList.add(new String("Choose Type(optional)"));
                        lin1.setVisibility(View.VISIBLE);
                        lin2.setVisibility(View.VISIBLE);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String roomName = jsonObject.optString("assetType");
                            roomList.add(roomName);
                            roomAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, roomList);
                            roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerRoom.setAdapter(roomAdapter);
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


        if (adapterView.getId() == R.id.sroom) {
            tableLayout1.removeAllViews();


            String selectloc = spinnerLocation.getSelectedItem().toString().trim();
            String selectedRoom = spinnerSystem.getSelectedItem().toString().trim();
            String selecttype = spinnerRoom.getSelectedItem().toString().trim();
            String url = "https://sandpit.assettrack.cx/api_android/get_or_idata.php?LocationName="+selectloc+ "&systemName=" +selectedRoom + "&assetType=" +selecttype;
            requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String getName = jsonObject.getString("dwgTag");
                            String spectag = jsonObject.getString("assetTag");
                            String asset = jsonObject.getString("assetType");
                            String status = jsonObject.getString("assetStatus");
                            // getData.add(getName);
                            textView8 = new TextView(getActivity());
                            textView8.setText(id);
                            tableRow1 = new TableRow(getActivity());
                            tableRow1.setPadding(20, 20, 20, 20);
                            tableRow1.setLayoutParams(new TableLayout.LayoutParams(
                                   TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
                            tableRow1.setGravity(Gravity.CENTER);
                            textView5 = new TextView(getActivity());
                            textView5.setText(getName);
                            textView5.setMaxWidth(250);
                            textView5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));



                            textView5.setGravity(Gravity.CENTER);
                           textView5.setPadding(10, 10, 10, 10);
                            tableRow1.addView(textView5);
                            textView6 = new TextView(getActivity());
                            textView6.setText(spectag);
                            textView6.setMaxWidth(250);
                            textView6.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));




//                            textView6.setWidth(200);
//                            textView6.setHeight(250);
                            textView6.setGravity(Gravity.CENTER);
                            textView6.setPadding(10, 10, 10, 10);
                            tableRow1.addView(textView6);
                            textview7 = new TextView(getActivity());
                            textview7.setText(asset);
                            textview7.setMaxWidth(250);
                            textview7.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));




                            textview7.setPadding(10, 10, 10, 10);
//                            textview7.setWidth(200);
//                            textview7.setHeight(250);
                            textview7.setGravity(Gravity.CENTER);
                            tableRow1.addView(textview7);
                            Button button = new Button(getActivity());
                            button.setMaxWidth(250);
                            button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));





//                            button.setWidth(150);
//                            button.setHeight(150);
                            button.setGravity(Gravity.CENTER);
                          button.setPadding(30, 20, 20, 20);
                            if (status.equals("1")) {
                                button.setText("None");
                               button.setTextColor(BLACK);
                                button.setBackgroundColor(Color.parseColor("#0dcaf0"));
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String url1 = "https://sandpit.assettrack.cx/api_android/update.php?installStatus=" + status + "&id=" + id;
                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response1) {
                                                button.setText("Install");
                                                button.setBackgroundColor(Color.parseColor("#fec008"));

                                            }

                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                        requestQueue.add(stringRequest);


                                        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                                        String id1 = user.get(sharedPrefrencesHelper.EMAIL);
                                       // Toast.makeText(getActivity().getBaseContext(), id1,Toast.LENGTH_SHORT).show();

                                        String url2 = "https://sandpit.assettrack.cx/api_android/updatestatus.php?updatedBy=" + id1 + "&id=" + id;
                                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response2) {
                                                if(response2.equals("1")){
                                                   Toast.makeText(getActivity().getBaseContext(), "Updated successfulley",Toast.LENGTH_SHORT).show();
                                               }

                                            }

                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                        requestQueue.add(stringRequest1);




                                    }

                                });
                            } else if (status.equals("3")) {
                                button.setText("Install");
                                button.setBackgroundColor(Color.parseColor("#fec008"));
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String url2 = "https://sandpit.assettrack.cx/api_android/update1.php?installStatus=" + status + "&id=" + id;
                                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response2) {
                                                button.setText("None");
                                                button.setBackgroundColor(Color.parseColor("#0dcaf0"));

                                            }

                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                        requestQueue.add(stringRequest1);

                                        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                                        String id1 = user.get(sharedPrefrencesHelper.EMAIL);
                                        // Toast.makeText(getActivity().getBaseContext(), id1,Toast.LENGTH_SHORT).show();

                                        String url3 = "https://sandpit.assettrack.cx/api_android/updatestatus.php?updatedBy=" + id1 + "&id=" + id;
                                        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response2) {
                                                if(response2.equals("1")){
                                                    Toast.makeText(getActivity().getBaseContext(), "Updated successfulley",Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                        requestQueue.add(stringRequest3);

                                    }
                                });

                            } else {
                                button.setText("Tested");
                                button.setBackgroundColor(Color.parseColor("#63aa8a"));
                            }
                            tableRow1.addView(button);
                            tableLayout1.addView(tableRow1);
                            tableLayout1.requestLayout();
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


    @Override
    public void onNothingSelected (AdapterView < ? > adapterView){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

