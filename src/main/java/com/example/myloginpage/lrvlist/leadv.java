package com.example.myloginpage.lrvlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myloginpage.R;
import com.example.myloginpage.SharedPrefrencesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class leadv extends Fragment {

    TableLayout tabLayout,tableLayout,tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10,tb11,tb12;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    TextView textView,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13;
    SharedPrefrencesHelper sharedPrefrencesHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leadv, container, false);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        tabLayout=view.findViewById(R.id.table);
        textView=view.findViewById(R.id.txt1);
        tv1= view.findViewById(R.id.txt2);
        tv2= view.findViewById(R.id.txt3);
        tv3= view.findViewById(R.id.txt4);
        tv4= view.findViewById(R.id.txt5);
        tv5= view.findViewById(R.id.txt6);
        tv6= view.findViewById(R.id.txt7);
        tv7= view.findViewById(R.id.txt8);
        tv8= view.findViewById(R.id.txt9);
        tv9= view.findViewById(R.id.txt10);
        tv10= view.findViewById(R.id.txt11);
        tv11= view.findViewById(R.id.txt12);
        tv12= view.findViewById(R.id.txt13);
        tv13= view.findViewById(R.id.txt14);
        tableLayout = view.findViewById(R.id.table1);
        tb1=view.findViewById(R.id.table2);

        tb3=view.findViewById(R.id.table4);
        tb4=view.findViewById(R.id.table5);
        tb5=view.findViewById(R.id.table6);
        tb6=view.findViewById(R.id.table7);
        tb7=view.findViewById(R.id.table8);
        tb8=view.findViewById(R.id.table9);
        tb9=view.findViewById(R.id.table10);
        tb10=view.findViewById(R.id.table11);
        tb11=view.findViewById(R.id.table12);
        tb12=view.findViewById(R.id.table13);
        tb2=view.findViewById(R.id.table14);

        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail1();
        String chkid = user.get(sharedPrefrencesHelper.chklistID);
        HashMap<String, String> user1 = sharedPrefrencesHelper.lrvdetails();
        String congf = user1.get(sharedPrefrencesHelper.CONGF);
        String lrvid = user1.get(sharedPrefrencesHelper.LRVNOL);
        HashMap<String, String> user2 = sharedPrefrencesHelper.lrvdetails1();
        String congf1 = user2.get(sharedPrefrencesHelper.CONGF);
        String lrvid1 = user2.get(sharedPrefrencesHelper.LRVNOL);
        if(congf.equals("2")) {
            requestQueue = Volley.newRequestQueue(getActivity());
            String url = "https://assettrack.cx/android_api/lrvchklist.php?chklistID=" + chkid + "&lrvno=" + lrvid;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String desc = jsonObject.optString("stdDesc");
                            String cdesc = jsonObject.optString("categoryDesc");
                            String stdstatus = jsonObject.optString("stdStatus");
                            String lv = jsonObject.optString("LeadV");
                            textView.setText("Lead Vehicle:  " + lv);
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextSize(20);
                            TableRow tableRow = new TableRow(getActivity());
                            tableRow.setPadding(10, 10, 10, 10);
                            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.WRAP_CONTENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                            tableRow.setGravity(Gravity.CENTER);

                            if (cdesc.equals("This consists meets")) {
                                tv1.setText(cdesc);
                                tv1.setGravity(Gravity.CENTER);
                             //  tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                               textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                TextView textView4 = new TextView(getActivity());
                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                //textView2.setGravity(Gravity.CENTER);

                                textView2.setWidth(650);
                                //textView2.setHeight(100);
                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                                //textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tableLayout.addView(tableRow);


                            }
                            else if (cdesc.equals("Cab Interior")) {
                                tv2.setText(cdesc);
                                tv2.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb1.addView(tableRow);

                            }
                            else if (cdesc.equals("Passenger compartment")) {
                                tv3.setText(cdesc);
                                tv3.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb3.addView(tableRow);
                            } else if (cdesc.equals("Interior & Exterior")) {
                                tv4.setText(cdesc);
                                tv4.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                              //  textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb4.addView(tableRow);

                            } else if (cdesc.equals("Exterior")) {
                                tv5.setText(cdesc);
                                tv5.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb5.addView(tableRow);

                            } else if (cdesc.equals("Functionality")) {
                                tv6.setText(cdesc);
                                tv6.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                              //  textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb6.addView(tableRow);

                            } else if (cdesc.equals("Amenities")) {
                                tv7.setText(cdesc);
                                tv7.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb7.addView(tableRow);

                            } else if (cdesc.equals("Systems auto-checks")) {
                                tv8.setText(cdesc);
                                tv8.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb8.addView(tableRow);

                            } else if (cdesc.equals("Equipment & Safety")) {
                                tv9.setText(cdesc);
                                tv9.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb9.addView(tableRow);

                            } else if (cdesc.equals("Regulatory Lighting ")) {

                                tv10.setText(cdesc);
                                tv10.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb10.addView(tableRow);
                            } else if (cdesc.equals("Equipment")) {
                                tv11.setText(cdesc);
                                tv11.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                             //   textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb11.addView(tableRow);

                            } else if (cdesc.equals("Appearance & Covers")) {
                                tv12.setText(cdesc);
                                tv12.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                             //   textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb12.addView(tableRow);

                            } else if (cdesc.equals("Braking")) {
                                tv13.setText(cdesc);
                                tv13.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb2.addView(tableRow);

                            }


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
        } else if (congf1.equals("3")) {
            requestQueue = Volley.newRequestQueue(getActivity());
            String url = "https://assettrack.cx/android_api/lrvchklist.php?chklistID=" + chkid + "&lrvno=" + lrvid1;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String desc = jsonObject.optString("stdDesc");
                            String cdesc = jsonObject.optString("categoryDesc");
                            String stdstatus = jsonObject.optString("stdStatus");
                            String lv = jsonObject.optString("LeadV");
                            textView.setText("Lead Vehicle:  " + lv);
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextSize(20);
                            TableRow tableRow = new TableRow(getActivity());
                            tableRow.setPadding(0, 10, 10, 10);
                            tableRow.setGravity(Gravity.CENTER);

                            if (cdesc.equals("This consists meets")) {
                                tv1.setText(cdesc);
                                tv1.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                              //  textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tableLayout.addView(tableRow);


                            } else if (cdesc.equals("Cab Interior")) {
                                tv2.setText(cdesc);
                                tv2.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                             //   textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb1.addView(tableRow);

                            } else if (cdesc.equals("Passenger compartment")) {
                                tv3.setText(cdesc);
                                tv3.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                              //  textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb3.addView(tableRow);
                            } else if (cdesc.equals("Interior & Exterior")) {
                                tv4.setText(cdesc);
                                tv4.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb4.addView(tableRow);

                            } else if (cdesc.equals("Exterior")) {
                                tv5.setText(cdesc);
                                tv5.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(600);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb5.addView(tableRow);

                            } else if (cdesc.equals("Functionality")) {
                                tv6.setText(cdesc);
                                tv6.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb6.addView(tableRow);

                            } else if (cdesc.equals("Amenities")) {
                                tv7.setText(cdesc);
                                tv7.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                             //   textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb7.addView(tableRow);

                            } else if (cdesc.equals("Systems auto-checks")) {
                                tv8.setText(cdesc);
                                tv8.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                              //  textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb8.addView(tableRow);

                            } else if (cdesc.equals("Equipment & Safety")) {
                                tv9.setText(cdesc);
                                tv9.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                             //   textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb9.addView(tableRow);

                            } else if (cdesc.equals("Regulatory Lighting ")) {

                                tv10.setText(cdesc);
                                tv10.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                               // textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb10.addView(tableRow);
                            } else if (cdesc.equals("Equipment")) {
                                tv11.setText(cdesc);
                                tv11.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                           //     textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb11.addView(tableRow);

                            } else if (cdesc.equals("Appearance & Covers")) {
                                tv12.setText(cdesc);
                                tv12.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                            //    textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb12.addView(tableRow);

                            } else if (cdesc.equals("Braking")) {
                                tv13.setText(cdesc);
                                tv13.setGravity(Gravity.CENTER);
                                TextView textView2 = new TextView(getActivity());
                                TextView textView3 = new TextView(getActivity());
                                TextView textView4 = new TextView(getActivity());
                                textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                                if (stdstatus.equals("1")) {
                                    textView3.setBackgroundResource(R.drawable.check1);

                                } else if (stdstatus.equals("0")) {
                                    textView3.setBackgroundResource(R.drawable.cancle1);

                                }
                                textView2.setText(desc);
                                textView2.setWidth(650);

                                textView4.setText("  ");

                                textView3.setGravity(Gravity.CENTER);
                             //   textView2.setBackgroundResource(R.drawable.textline);
                                tableRow.addView(textView2);
                                tableRow.addView(textView4);
                                tableRow.addView(textView3);
                                tb2.addView(tableRow);

                            }


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

        return view;
    }
}