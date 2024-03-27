package com.example.myloginpage;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
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


public class pdcoverview extends Fragment implements Toolbar.OnMenuItemClickListener{
TextView textView,textView1,textView2,tv,tv2,tv3,tv4,tv5,txt1,tx,tx1,tx2;
Button btn,btn1,btn2,btn3,update,btn4,btn5;
LinearLayout linear1,linear2,linear3,linear4,lsp1,lsp2,ln,ln1,ln2,ln3,ln4,ln5;
TableLayout tableLayout,tableLayout1,tableLayout2,tbl,tbl1,tbl2;
TableRow tr,tr1,tr2;
  int chid1,chidT1,chidM1;
    private int clickCount = 0;
    private boolean stopLoop = false;
Spinner spinnerleadv, spinnertrailv, spinnerleadv3car, spinnermidv3car, spinnertrailv3car;
    ArrayList<String> lvehicleList = new ArrayList<>();
    ArrayList<String> mvehicleList = new ArrayList<>();
    ArrayList<String> tvehiclelistList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    ArrayAdapter<String> lvehicleAdapter;
    ArrayAdapter<String> mvehicleAdapter;
    ArrayAdapter<String> tvehicleAdapter;
    JSONArray jsonArray,jsonArray1,jsonArray2;
    RequestQueue requestQueue;
    SharedPrefrencesHelper sharedPrefrencesHelper;
    Toolbar toolbar;
   boolean count = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view=inflater.inflate(R.layout.fragment_pdcoverview,container,false);


        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        requestQueue = Volley.newRequestQueue(getActivity());
        textView = view.findViewById(R.id.tvd);
        textView1 = view.findViewById(R.id.dtd);
        textView2 = view.findViewById(R.id.lvd);
        tv=view.findViewById(R.id.mvd);
        tv2=view.findViewById(R.id.tv1);
        tv3=view.findViewById(R.id.txt6);
        tv4=view.findViewById(R.id.txt7);
        tv5=view.findViewById(R.id.txt8);
        txt1=view.findViewById(R.id.txt5);
        tx = view.findViewById(R.id.tx1);
        tx1 = view.findViewById(R.id.tx2);
        tx2= view.findViewById(R.id.tx3);

        btn = view.findViewById(R.id.btn);
        btn.setTag(1);
        btn1= view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3=view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn4.setTag(1);
        btn5 = view.findViewById(R.id.btn5);
        update=view.findViewById(R.id.update);
        update.setTag(1);

        tableLayout = view.findViewById(R.id.table);
        tableLayout1 = view.findViewById(R.id.table1);
        tableLayout2 = view.findViewById(R.id.table2);
        tbl=view.findViewById(R.id.tbl);
        tbl1=view.findViewById(R.id.tbl1);
        tbl2=view.findViewById(R.id.tbl2);
        tr = view.findViewById(R.id.tr1);
        tr1= view.findViewById(R.id.tr2);
        tr2= view.findViewById(R.id.tr3);

        linear1=view.findViewById(R.id.linear2);
        linear2=view.findViewById(R.id.linear3);
        linear3=view.findViewById(R.id.linear4);
        linear4=view.findViewById(R.id.linear5);
        lsp1 =view.findViewById(R.id.linearsp1);
        lsp2=view.findViewById(R.id.linearsp2);
        ln=view.findViewById(R.id.ln);
        ln1 = view.findViewById(R.id.ln2);
        ln2 = view.findViewById(R.id.ln6);
        ln3 = view.findViewById(R.id.lin4);
        ln4 = view.findViewById(R.id.ln5);
        ln5 = view.findViewById(R.id.line);

        spinnerleadv =view.findViewById(R.id.sleadV);
        spinnertrailv =view.findViewById(R.id.strailv);
        spinnerleadv3car=view.findViewById(R.id.sleadv3car);
        spinnermidv3car =view.findViewById(R.id.smidv3car);
        spinnertrailv3car =view.findViewById(R.id.strailv3car);




        linear1.setVisibility(View.GONE);
        linear2.setVisibility(View.GONE);
        linear3.setVisibility(View.GONE);
        linear4.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        lsp1.setVisibility(View.GONE);
        lsp2.setVisibility(View.GONE);
        ln.setVisibility(View.GONE);
        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
        ln3.setVisibility(View.GONE);
        ln4.setVisibility(View.GONE);
        ln5.setVisibility(View.GONE);


        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
        String uid = user.get(sharedPrefrencesHelper.EMAIL);


        Bundle bundle = getArguments();
        String congf = bundle.getString("config");
        String leadid = bundle.getString("leadid");
        String trailid = bundle.getString("trailid");
        String leadvid = bundle.getString("leadvid");
        String trailvid = bundle.getString("traivlid");
        String midvid = bundle.getString("midvid");
        String chkid = bundle.getString("chkid");



        Bundle bundle1 = getArguments();
        String leadid2 = bundle1.getString("leadid2");
        String trailid2 = bundle1.getString("trailid2");
        String leadvid2 = bundle1.getString("leadvid2");
        String trailvid2 = bundle1.getString("traivlid2");
        String midvid2 = bundle1.getString("midvid2");



        if(congf.equals("2")) {
            sharedPrefrencesHelper.createSession2(leadid,trailid,congf);
            String msg = bundle.getString("lead");
            String msg1 = bundle.getString("trail");
            String msg2 = bundle.getString("date");
           chid1=0;
           chidT1=0;
            if(chid1 == 0){
                btn1.setBackgroundColor(Color.parseColor("#df4654"));
            }
            if(chidT1 == 0){
                btn2.setBackgroundColor(Color.parseColor("#df4654"));
            }

            textView.setText(msg);
            textView1.setText(msg1);
            textView2.setText(msg2);
            btn1.setText(msg);
            btn2.setText(msg1);
            btn1.setText("Lead Vehicle(" +msg+ ")");
            btn2.setText("Trail Vehicle(" +msg1+ ")");
            tv2.setText("LRV Config Details added! Click the Lead/Trail vehicle to update the standard(s) whichever applicable.");
        }
        else if (congf.equals("3")) {
            sharedPrefrencesHelper.createSession3(leadvid,midvid,trailvid,congf);
            String msg = bundle.getString("lead");
            String msg1 = bundle.getString("trail");
            String msg2 = bundle.getString("date");
            String msg3 = bundle.getString("mid");
            chid1=0;
            chidT1=0;
            chidM1=0;
            if(chid1 == 0){
                btn1.setBackgroundColor(Color.parseColor("#df4654"));
            }
            if(chidT1 == 0){
                btn2.setBackgroundColor(Color.parseColor("#df4654"));
            }
            if(chidM1 == 0){
                btn3.setBackgroundColor(Color.parseColor("#df4654"));
            }
            btn1.setText(msg);
            btn2.setText(msg1);
            btn3.setText(msg3);
            textView.setText(msg);
            textView1.setText(msg1);
            textView2.setText(msg2);
            tv.setText(msg3);

            btn1.setText("Lead Vehicle(" +msg+ ")");
            btn2.setText("Trail Vehicle(" +msg1+ ")");
            btn3.setText("Mid Vehicle(" +msg3+ ")");
            btn3.setVisibility(View.VISIBLE);
            tv2.setText("LRV Config Details added! Click the Lead/Mid/Trail vehicle to update the standard(s) whichever applicable.");

        }
        if(congf.equals("2")) {
            tx.setText(leadid);
            tx2.setText(trailid);
        }
        else if (congf.equals("3")) {
            tx.setText(leadvid);
            tx2.setText(trailvid);
            tx1.setText(midvid);
        }


        if(congf.equals("2")) {
            String url = "https://assettrack.cx/android_api/lrvstatus.php?chklistID=" + chkid;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                        try {
                            jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                String chid = jsonObject.optString("leadVehicleItems");
                              String   chidT = jsonObject.optString("trailVehicleItems");




                                if (Integer.parseInt(chid) > 0) {
                                    btn1.setBackgroundColor(Color.parseColor("#ffca2b"));
                                    btn1.setTextColor(Color.BLACK);

                                    update.setVisibility(View.GONE);
                                }
                                if (Integer.parseInt(chid) == 68) {
                                    btn1.setBackgroundColor(Color.parseColor("#76b290"));
                                    btn1.setTextColor(Color.BLACK);
                                    btn1.setClickable(false);
                                    update.setVisibility(View.GONE);

                                }
                                if (Integer.parseInt(chidT) > 0) {
                                    btn2.setBackgroundColor(Color.parseColor("#ffca2b"));
                                    btn2.setTextColor(Color.BLACK);
                                    update.setVisibility(View.GONE);
                                }
                                if (Integer.parseInt(chidT) == 68) {
                                    btn2.setBackgroundColor(Color.parseColor("#76b290"));
                                    btn2.setTextColor(Color.BLACK);
                                    btn2.setClickable(false);
                                    update.setVisibility(View.GONE);


                                }
                                if (Integer.parseInt(chid) == 68 && Integer.parseInt(chidT) == 68) {
                                    String url1 = "https://assettrack.cx/android_api/failedlrv.php?chklistID=" + chkid;
                                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url1, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                jsonArray1 = response.getJSONArray("data");
                                                for (int i = 0; i < jsonArray1.length(); i++) {
                                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                                    String lfail = jsonObject1.optString("leadVehicleFail");
                                                    String tfail = jsonObject1.optString("trailVehicleFail");
                                                    String majord = jsonObject1.optString("majorDefects");
                                                    if (Integer.parseInt(lfail) > 0 || Integer.parseInt(tfail) > 0) {
                                                        if (Integer.parseInt(majord) > 0) {
                                                            ln.setVisibility(View.VISIBLE);
                                                            ln2.setVisibility(View.VISIBLE);
                                                            txt1.setText("The LRV(s) NOT FIT for revenue operations.");

                                                            String url4 = "https://assettrack.cx/android_api/updatelrvinfo.php?pdcStatus=3&updatedBy=" + uid + "&chklistID=" + chkid;
                                                            JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {


                                                                }

                                                            }, new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {

                                                                }
                                                            });
                                                            requestQueue.add(jsonObjectRequest4);


                                                            btn4.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    clickCount++;
                                                                    if (clickCount == 1) {
                                                                        stopLoop = true; // Set the flag to stop the loop on the second click
                                                                    }
                                                                    final int status = (Integer) v.getTag();
                                                                    if (status == 1) {

                                                                        if(Integer.parseInt(lfail)>0){
                                                                            ln1.setVisibility(View.VISIBLE);
                                                                        }
                                                                       if(Integer.parseInt(tfail) >0) {
                                                                           ln4.setVisibility(View.VISIBLE);
                                                                       }
                                                                        String url2 = "https://assettrack.cx/android_api/stdstatus.php?chklistID=" + chkid + "&lrv_id=" + leadid;
                                                                        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
                                                                            @Override
                                                                            public void onResponse(JSONObject response) {

                                                                                try {
                                                                                    jsonArray2 = response.getJSONArray("data");

                                                                                    for (int i = 0; i < jsonArray2.length(); i++) {
                                                                                        if (stopLoop) {
                                                                                            // Exit the loop if the flag is set
                                                                                            break;
                                                                                        }
                                                                                            JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                                                                            String lrvtitle = jsonObject.optString("lrv_title");
                                                                                            String desc = jsonObject.optString("stdDesc");
                                                                                            tv3.setText(lrvtitle);
                                                                                            if (tv3.getParent() != null) {
                                                                                                ((ViewGroup) tv3.getParent()).removeView(tv3); // <- fix
                                                                                            }
                                                                                            tr.addView(tv3);
                                                                                            if (tr.getParent() != null) {
                                                                                                ((ViewGroup) tr.getParent()).removeView(tr); // <- fix
                                                                                            }
                                                                                            tableLayout.addView(tr);
                                                                                            TableRow tableRow = new TableRow(getActivity());
                                                                                            TextView ltxtf = new TextView(getActivity());

                                                                                            ltxtf.setText(desc);

                                                                                            tableRow.addView(ltxtf);
                                                                                            tbl.addView(tableRow);


                                                                                    }


                                                                                } catch (
                                                                                        JSONException e) {
                                                                                    throw new RuntimeException(e);
                                                                                }
                                                                            }

                                                                        }, new Response.ErrorListener() {
                                                                            @Override
                                                                            public void onErrorResponse(VolleyError error) {

                                                                            }
                                                                        });

                                                                        requestQueue.add(jsonObjectRequest2);

                                                                        String url3 = "https://assettrack.cx/android_api/stdstatus.php?chklistID=" + chkid + "&lrv_id=" + trailid;
                                                                        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.POST, url3, null, new Response.Listener<JSONObject>() {
                                                                            @Override
                                                                            public void onResponse(JSONObject response) {

                                                                                try {

                                                                                    jsonArray2 = response.getJSONArray("data");
                                                                                  //  if (count ==1) {


                                                                                        for (int i = 0; i < jsonArray2.length(); i++) {
                                                                                            // stolExecution=true;

                                                                                            JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                                                                            String lrvtitle = jsonObject.optString("lrv_title");
                                                                                            String desc = jsonObject.optString("stdDesc");
                                                                                            tv5.setText(lrvtitle);
                                                                                            if (tv5.getParent() != null) {
                                                                                                ((ViewGroup) tv5.getParent()).removeView(tv5); // <- fix
                                                                                            }
                                                                                            tr2.addView(tv5);
                                                                                            if (tr2.getParent() != null) {
                                                                                                ((ViewGroup) tr2.getParent()).removeView(tr2); // <- fix
                                                                                            }
                                                                                            tableLayout2.addView(tr2);
                                                                                            TableRow tableRow = new TableRow(getActivity());
                                                                                            TextView ltxtf = new TextView(getActivity());

                                                                                            ltxtf.setText(desc);
                                                                                            tableRow.addView(ltxtf);
                                                                                            tbl2.addView(tableRow);
                                                                                            break;

                                                                                      //  }
                                                                                    }

                                                                                } catch (
                                                                                        JSONException e) {
                                                                                    throw new RuntimeException(e);
                                                                                }
                                                                            }

                                                                        }, new Response.ErrorListener() {
                                                                            @Override
                                                                            public void onErrorResponse(VolleyError error) {

                                                                            }
                                                                        });

                                                                        requestQueue.add(jsonObjectRequest3);

                                                                        v.setTag(0);

                                                                    } else {


                                                                        ln3.setVisibility(View.GONE);
                                                                        ln1.setVisibility(View.GONE);
                                                                        ln4.setVisibility(View.GONE);
                                                                        v.setTag(1);

                                                                    }

                                                                }
                                                            });

                                                            btn5.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    Bundle bundle1 = new Bundle();
                                                                    bundle1.putString("conf", congf);
                                                                    Fragment fragment = new statusfail();
                                                                    fragment.setArguments(bundle1);
                                                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                                    fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();
                                                                }
                                                            });
                                                        }
                                                        else {
                                                            String url4 = "https://assettrack.cx/android_api/updatelrvinfo.php?pdcStatus=1&updatedBy=" + uid + "&chklistID=" + chkid;
                                                            JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {


                                                                }

                                                            }, new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {

                                                                }
                                                            });
                                                            requestQueue.add(jsonObjectRequest4);
                                                            ln2.setVisibility(View.VISIBLE);
                                                            txt1.setText("The LRV(s) CAN BE USED for revenue operations.");
                                                            btn5.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    Bundle bundle1 = new Bundle();
                                                                    bundle1.putString("conf", congf);
                                                                    Fragment fragment = new statusfail();
                                                                    fragment.setArguments(bundle1);
                                                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                                    fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();
                                                                }
                                                            });

                                                        }


                                                    }

                                                    else {
                                                        String url4 = "https://assettrack.cx/android_api/updatelrvinfo.php?pdcStatus=2&updatedBy=" + uid + "&chklistID=" + chkid;
                                                        JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {


                                                            }

                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        requestQueue.add(jsonObjectRequest4);
                                                        ln2.setVisibility(View.VISIBLE);
                                                        txt1.setText("The LRV(s) GOOD for revenue operations.");
                                                        btn5.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                Bundle bundle1 = new Bundle();
                                                                bundle1.putString("conf", congf);
                                                                Fragment fragment = new statusfail();
                                                                fragment.setArguments(bundle1);
                                                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                                fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();
                                                            }
                                                        });

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
                                    requestQueue.add(jsonObjectRequest1);

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
        else if (congf.equals("3")) {

            String url = "https://assettrack.cx/android_api/lrvstatus.php?chklistID=" + chkid;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String chid = jsonObject.optString("leadVehicleItems");
                            String chidT = jsonObject.optString("trailVehicleItems");
                            String chidM = jsonObject.optString("midVehicleItems");



                            if (Integer.parseInt(chid) > 0) {

                                btn1.setBackgroundColor(Color.parseColor("#ffca2b"));
                                btn1.setTextColor(Color.BLACK);
                                update.setVisibility(View.GONE);
                            }
                            if (Integer.parseInt(chid) == 68) {
                                btn1.setBackgroundColor(Color.parseColor("#76b290"));
                                btn1.setTextColor(Color.BLACK);
                                update.setVisibility(View.GONE);
                            }
                            if (Integer.parseInt(chidT) > 0) {
                                btn2.setBackgroundColor(Color.parseColor("#ffca2b"));
                                btn2.setTextColor(Color.BLACK);
                                update.setVisibility(View.GONE);
                            }
                            if (Integer.parseInt(chidT) == 68) {
                                btn2.setBackgroundColor(Color.parseColor("#76b290"));
                                btn2.setTextColor(Color.BLACK);
                                update.setVisibility(View.GONE);


                            }
                            if (Integer.parseInt(chidM) > 0) {
                                btn3.setBackgroundColor(Color.parseColor("#ffca2b"));
                                btn3.setTextColor(Color.BLACK);
                                update.setVisibility(View.GONE);
                            }
                            if (Integer.parseInt(chidM) == 68) {
                                btn3.setBackgroundColor(Color.parseColor("#76b290"));
                                btn3.setTextColor(Color.BLACK);
                                update.setVisibility(View.GONE);


                            }
                            if(Integer.parseInt(chid) == 68 && Integer.parseInt(chidT) == 68 && Integer.parseInt(chidM) == 68){

                                String url1 = "https://assettrack.cx/android_api/failedlrv.php?chklistID=" + chkid;
                                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url1, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            jsonArray1 = response.getJSONArray("data");
                                            for (int i = 0; i < jsonArray1.length(); i++) {
                                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                                String lfail = jsonObject1.optString("leadVehicleFail");
                                                String mfail = jsonObject1.optString("midVehicleFail");
                                                String tfail = jsonObject1.optString("trailVehicleFail");
                                                String majord = jsonObject1.optString("majorDefects");
                                                if (Integer.parseInt(lfail) > 0 || Integer.parseInt(tfail)>0){
                                                    if(Integer.parseInt(majord)>0) {
                                                        ln.setVisibility(View.VISIBLE);
                                                        ln2.setVisibility(View.VISIBLE);

                                                        txt1.setText("The LRV(s) NOT FIT for revenue operations.");
                                                        String url4 = "https://assettrack.cx/android_api/updatelrvinfo.php?pdcStatus=3&updatedBy=" + uid + "&chklistID=" + chkid;
                                                        JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {


                                                            }

                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        requestQueue.add(jsonObjectRequest4);
                                                        btn4.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                clickCount++;
                                                                if (clickCount == 2) {
                                                                    stopLoop = true; // Set the flag to stop the loop on the second click
                                                                }
                                                                final  int  status  =(Integer) v.getTag();
                                                                if(status==1) {

                                                                    if(Integer.parseInt(lfail)>0){
                                                                        ln1.setVisibility(View.VISIBLE);
                                                                    }
                                                                    if(Integer.parseInt(tfail) >0) {
                                                                        ln4.setVisibility(View.VISIBLE);
                                                                    }
                                                                    if(Integer.parseInt(mfail) >0) {
                                                                        ln3.setVisibility(View.VISIBLE);
                                                                    }


                                                                    String url2 = "https://assettrack.cx/android_api/stdstatus.php?chklistID=" + chkid + "&lrv_id=" +leadvid;
                                                                    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {

                                                                            try {
                                                                                jsonArray2 = response.getJSONArray("data");
                                                                                for (int i = 0; i < jsonArray2.length(); i++) {
                                                                                    JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                                                                    String lrvtitle = jsonObject.optString("lrv_title");
                                                                                    String desc = jsonObject.optString("stdDesc");
                                                                                    tv3.setText(lrvtitle);
                                                                                    if(tv3.getParent() != null) {
                                                                                        ((ViewGroup)tv3.getParent()).removeView(tv3); // <- fix
                                                                                    }
                                                                                    tr.addView(tv3);
                                                                                    if(tr.getParent() != null) {
                                                                                        ((ViewGroup)tr.getParent()).removeView(tr); // <- fix
                                                                                    }
                                                                                    tableLayout.addView(tr);
                                                                                    TableRow tableRow = new TableRow(getActivity());
                                                                                    TextView ltxtf = new TextView(getActivity());

                                                                                    ltxtf.setText(desc);
                                                                                    tableRow.addView(ltxtf);
                                                                                    tbl.addView(tableRow);

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

                                                                    requestQueue.add(jsonObjectRequest2);

                                                                    String url3 = "https://assettrack.cx/android_api/stdstatus.php?chklistID=" + chkid + "&lrv_id=" +trailvid;
                                                                    JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.POST, url3, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {

                                                                            try {

                                                                                jsonArray2 = response.getJSONArray("data");
                                                                                for (int i = 0; i < jsonArray2.length(); i++) {
                                                                                    JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                                                                    String lrvtitle = jsonObject.optString("lrv_title");
                                                                                    String desc = jsonObject.optString("stdDesc");
                                                                                    tv5.setText(lrvtitle);
                                                                                    if(tv5.getParent() != null) {
                                                                                        ((ViewGroup)tv5.getParent()).removeView(tv5); // <- fix
                                                                                    }
                                                                                    tr2.addView(tv5);
                                                                                    if(tr2.getParent() != null) {
                                                                                        ((ViewGroup)tr2.getParent()).removeView(tr2); // <- fix
                                                                                    }
                                                                                    tableLayout2.addView(tr2);
                                                                                    TableRow tableRow = new TableRow(getActivity());
                                                                                    TextView ltxtf = new TextView(getActivity());

                                                                                    ltxtf.setText(desc);
                                                                                    tableRow.addView(ltxtf);
                                                                                    tbl2.addView(tableRow);
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

                                                                    requestQueue.add(jsonObjectRequest3);
                                                                    String url4 = "https://assettrack.cx/android_api/stdstatus.php?chklistID=" + chkid + "&lrv_id=" +midvid;
                                                                    JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                                        @Override
                                                                        public void onResponse(JSONObject response) {

                                                                            try {

                                                                                jsonArray2 = response.getJSONArray("data");
                                                                                for (int i = 0; i < jsonArray2.length(); i++) {
                                                                                    JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                                                                    String lrvtitle = jsonObject.optString("lrv_title");
                                                                                    String desc = jsonObject.optString("stdDesc");
                                                                                    tv4.setText(lrvtitle);
                                                                                    if(tv4.getParent() != null) {
                                                                                        ((ViewGroup)tv4.getParent()).removeView(tv4); // <- fix
                                                                                    }
                                                                                    tr1.addView(tv4);
                                                                                    if(tr1.getParent() != null) {
                                                                                        ((ViewGroup)tr1.getParent()).removeView(tr1); // <- fix
                                                                                    }
                                                                                    tableLayout1.addView(tr1);
                                                                                    TableRow tableRow = new TableRow(getActivity());
                                                                                    TextView ltxtf = new TextView(getActivity());

                                                                                    ltxtf.setText(desc);
                                                                                    tableRow.addView(ltxtf);
                                                                                    tbl1.addView(tableRow);
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

                                                                    requestQueue.add(jsonObjectRequest4);


                                                                    v.setTag(0);

                                                                }else {
                                                                    ln1.setVisibility(View.GONE);
                                                                    ln4.setVisibility(View.GONE);
                                                                    ln3.setVisibility(View.GONE);
                                                                    v.setTag(1);
                                                                }

                                                            }
                                                        });

                                                        btn5.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {

                                                                bundle.putString("conf", congf);
                                                                Fragment fragment = new statusfail();
                                                                fragment.setArguments(bundle);
                                                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                                fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit();
                                                            }
                                                        });
                                                    }else {
                                                        String url4 = "https://assettrack.cx/android_api/updatelrvinfo.php?pdcStatus=2&updatedBy=" + uid + "&chklistID=" + chkid;
                                                        JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {


                                                            }

                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                            }
                                                        });
                                                        requestQueue.add(jsonObjectRequest4);
                                                        btn5.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {

                                                                bundle.putString("conf", congf);
                                                                Fragment fragment = new statusfail();
                                                                fragment.setArguments(bundle);
                                                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                                fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit();
                                                            }
                                                        });
                                                        ln2.setVisibility(View.VISIBLE);
                                                        txt1.setText("The LRV(s) CAN BE USED for revenue operations.");
                                                    }



                                                }
                                                else {

                                                    String url4 = "https://assettrack.cx/android_api/updatelrvinfo.php?pdcStatus=2&updatedBy=" + uid + "&chklistID=" + chkid;
                                                    JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST, url4, null, new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {


                                                        }

                                                    }, new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {

                                                        }
                                                    });
                                                    requestQueue.add(jsonObjectRequest4);

                                                    ln2.setVisibility(View.VISIBLE);
                                                    txt1.setText("The LRV(s) GOOD for revenue operations.");

                                                    btn5.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            bundle.putString("conf", congf);
                                                            Fragment fragment = new statusfail();
                                                            fragment.setArguments(bundle);
                                                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                            fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit();
                                                        }
                                                    });
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
                                requestQueue.add(jsonObjectRequest1);


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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  int  status  =(Integer) v.getTag();
                if(status==1) {
                    if (congf.equals("2")) {
                        linear1.setVisibility(View.VISIBLE);
                        linear2.setVisibility(View.VISIBLE);
                        linear3.setVisibility(View.VISIBLE);

                    }
                    else if (congf.equals("3")) {
                        linear1.setVisibility(View.VISIBLE);
                        linear2.setVisibility(View.VISIBLE);
                        linear3.setVisibility(View.VISIBLE);
                        linear4.setVisibility(View.VISIBLE);

                    }
                    v.setTag(0);
                }else {
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    v.setTag(1);
                }



            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  int  status  =(Integer) v.getTag();
                if(status==1) {
                    Bundle bundle = getArguments();
                    String congf = bundle.getString("config");
                    if (congf.equals("2")) {
                        btn1.setVisibility(View.GONE);
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        lsp1.setVisibility(View.VISIBLE);
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
                        spinnerleadv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
                                if (adapterView.getId() == R.id.sleadV) {
                                    tvehiclelistList.clear();

                                    String selectedRoom = spinnerleadv.getSelectedItem().toString();
                                    String id = String.valueOf(spinnerleadv.getSelectedItemId());




                                    if (selectedRoom.equals("6200")) {
                                        spinnertrailv.setVisibility(View.VISIBLE);
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
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                    else if (congf.equals("3")) {
                        btn1.setVisibility(View.GONE);
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        lsp2.setVisibility(View.VISIBLE);
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
                        spinnerleadv3car.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                if (adapterView.getId() == R.id.sleadv3car) {
                                    mvehicleList.clear();
                                    String leadv = spinnerleadv3car.getSelectedItem().toString().trim();
                                    String id1 = String.valueOf(spinnerleadv3car.getSelectedItemId());
                                    if (leadv.equals("6200")) {
                                        spinnermidv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;

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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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

                                        String url = "https://assettrack.cx/android_api/getmidvehicle.php?id="+id1;
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
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                        spinnermidv3car.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                if (adapterView.getId() == R.id.smidv3car) {
                                    tvehiclelistList.clear();
                                    String midv = spinnermidv3car.getSelectedItem().toString().trim();
                                    if (midv.equals("6200")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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
                                    } else if (midv.equals("6201")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6202")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6203")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6204")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6205")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6206")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6207")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6208")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6209")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6210")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6211")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6212")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6213")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6214")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6215")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6216")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6217")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6218")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6219")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6220")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6221")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6222")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6223")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6224")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6225")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6226")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6227")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6228")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6229")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6230")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6231")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6232")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6233")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6234")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6235")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6236")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6237")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6238")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6239")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6240")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6241")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6242")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6243")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6244")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6245")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6246")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6247")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6248")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6249")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6250")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6251")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6252")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6253")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6254")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6255")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6256")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6257")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6258")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6259")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6260")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6261")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6262")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6263")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6264")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6265")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6266")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6267")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6268")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6269")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6270")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6271")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6272")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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


                                    } else if (midv.equals("6273")) {
                                        spinnertrailv3car.setVisibility(View.VISIBLE);

                                        String url = "https://assettrack.cx/android_api/gettrailvehicle3.php";
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

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    v.setTag(0);
                }

                else  {

                    Bundle bundle = getArguments();
                    String congf = bundle.getString("config");
                    if(congf.equals("2")) {

                        String leadv1 = String.valueOf(spinnerleadv.getSelectedItemId());
                        String date = textView1.getText().toString().trim();
                        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                        String uid = user.get(sharedPrefrencesHelper.EMAIL);
                        if(spinnerleadv.getSelectedItemId() > spinnertrailv.getSelectedItemId()){
                            String tve = String.valueOf(spinnertrailv.getSelectedItemId());
                            String url2 = "https://assettrack.cx/android_api/newlrvchecklist.php?vehicleType=" + congf + "&leadVehicleID=" + leadv1 + "&trailVehicleID=" + tve + "&supervisor=" + uid;
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
                                                Bundle bundle = new Bundle();
                                                String lead = spinnerleadv.getSelectedItem().toString().trim();
                                                String leadid = String.valueOf(spinnerleadv.getSelectedItemId());
                                                String trailv = spinnertrailv.getSelectedItem().toString().trim();
                                                String trailid = String.valueOf(spinnertrailv.getSelectedItemId());
                                                bundle.putString("config", congf);
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

                        }
                        else if (spinnerleadv.getSelectedItemId() < spinnertrailv.getSelectedItemId() || spinnerleadv.getSelectedItemId() == spinnertrailv.getSelectedItemId() ) {

                            String tve = String.valueOf((spinnertrailv.getSelectedItemId() + 1));
                            String url2 = "https://assettrack.cx/android_api/newlrvchecklist.php?vehicleType=" + congf + "&leadVehicleID=" + leadv1 + "&trailVehicleID=" + tve + "&supervisor=" + uid;


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
                                                Bundle bundle = new Bundle();
                                                String lead = spinnerleadv.getSelectedItem().toString().trim();
                                                String leadid = String.valueOf(spinnerleadv.getSelectedItemId());
                                                String trailv = spinnertrailv.getSelectedItem().toString().trim();
                                                String trailid = String.valueOf(spinnertrailv.getSelectedItemId());
                                                bundle.putString("config", congf);
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
                                        else if (success.equals("2")) {

                                            AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
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


                    }
                    else if (congf.equals("3")) {


                        String leadv1 = String.valueOf(spinnerleadv3car.getSelectedItemId());
                        String tv1 = String.valueOf(spinnertrailv3car.getSelectedItemId());
                        String mv = String.valueOf(spinnermidv3car.getSelectedItemId());
                        String date = textView1.getText().toString();
                        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                        String uid = user.get(sharedPrefrencesHelper.EMAIL);

                        String url1 = "https://assettrack.cx/android_api/newlrvchecklist1.php?vehicleType=" + congf + "&leadVehicleID=" + leadv1 + "&midVehicleID=" +mv+ "&trailVehicleID=" +tv1+ "&supervisor=" +uid;
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject1= new JSONObject(response);
                                    String success = jsonObject1.getString("success");
                                    // Toast.makeText(getActivity().getBaseContext(),success,Toast.LENGTH_SHORT).show();

                                    jsonArray1 = jsonObject1.getJSONArray("updated");
                                    if(success.equals("1")){

                                        for (int i = 0; i < jsonArray1.length(); i++) {
                                            JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                            String chkid = jsonObject2.getString("id").trim();

                                            Bundle bundle = new Bundle();
                                            String lead = spinnerleadv3car.getSelectedItem().toString().trim();
                                            String trailv = spinnertrailv3car.getSelectedItem().toString().trim();
                                            String midv = spinnermidv3car.getSelectedItem().toString().trim();
                                            // Toast.makeText(getActivity(),midv,Toast.LENGTH_SHORT).show();
                                            String leadvid = String.valueOf(spinnerleadv3car.getSelectedItemId());
                                            String midvid = String.valueOf(spinnermidv3car.getSelectedItemId());
                                            // Toast.makeText(getActivity(),midvid,Toast.LENGTH_SHORT).show();
                                            String trailvid = String.valueOf(spinnertrailv3car.getSelectedItemId());
                                            bundle.putString("config", congf);
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
                                    }
                                    else if (success.equals("2")) {

                                        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
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
                    v.setTag(1);

                }


            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle1 = new Bundle();
                String lead = btn1.getText().toString();
                bundle1.putString("lead", lead);
                String leadv = textView.getText().toString();
                String midv = tv.getText().toString();
                String trailv = textView1.getText().toString();
                String date = textView2.getText().toString();
                bundle1.putString("leadv", leadv);
                bundle1.putString("chkid",chkid);
                bundle1.putString("midv", midv);
                bundle1.putString("trailv", trailv);
                bundle1.putString("config", congf);
                bundle1.putString("date", date);
                String leadid1 = tx.getText().toString();
                bundle1.putString("leadid1",leadid1);
                bundle1.putString("leadid",leadid);
                bundle1.putString("trailid",trailid);
                bundle1.putString("midvid",midvid);
                bundle1.putString("leadvid",leadvid);
                bundle1.putString("traivlid",trailvid);
                Fragment fragment = new generalSTD();
                fragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1=new Bundle();
                String Trail = btn2.getText().toString();
                bundle1.putString("lead", Trail);
                bundle1.putString("chkid",chkid);
                String leadv = textView.getText().toString();
                String midv = tv.getText().toString();
                String trailv = textView1.getText().toString();
                String date = textView2.getText().toString();
                bundle1.putString("leadv", leadv);
                bundle1.putString("midv", midv);
                bundle1.putString("trailv", trailv);
                bundle1.putString("config", congf);
                bundle1.putString("date", date);
                String trailid1 = tx2.getText().toString();
                bundle1.putString("leadid1",trailid1 );
                bundle1.putString("leadid",leadid);
                bundle1.putString("trailid",trailid);
                bundle1.putString("midvid",midvid);
                bundle1.putString("leadvid",leadvid);
                bundle1.putString("traivlid",trailvid);
                Fragment fragment = new generalSTD();
                fragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1=new Bundle();

                String Mid = btn3.getText().toString();
                bundle1.putString("lead", Mid);
                String leadv = textView.getText().toString();
                String midv = tv.getText().toString();
                String trailv = textView1.getText().toString();
                String date = textView2.getText().toString();
                bundle1.putString("chkid",chkid);
                bundle1.putString("leadvid",midvid);
                bundle1.putString("leadv", leadv);
                bundle1.putString("midv", midv);
                bundle1.putString("trailv", trailv);
                bundle1.putString("config", congf);
                bundle1.putString("date", date);
                String midvid1 = tx1.getText().toString();
                bundle1.putString("leadid1",midvid1 );
                bundle1.putString("trailid",trailid);
                bundle1.putString("traivlid",trailvid );
                bundle1.putString("midvid",midvid);
                bundle1.putString("leadvid",leadvid);
                bundle1.putString("traivlid",trailvid);
                Fragment fragment = new generalSTD();
                fragment.setArguments(bundle1);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();

            }
        });


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
            fm.replace(R.id.frame1, fragment2).commit();

        }else if (item.getItemId() == R.id.OSC) {

            Fragment fragment1 = new Install();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame1, fragment1).addToBackStack(null).commit();
        }
        return  true;
    }
}