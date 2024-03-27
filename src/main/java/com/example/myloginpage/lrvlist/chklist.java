package com.example.myloginpage.lrvlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class chklist extends Fragment {

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    SharedPrefrencesHelper sharedPrefrencesHelper;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chklist, container, false);



        txt1 = view.findViewById(R.id.txt2);
        txt2 = view.findViewById(R.id.txt4);
        txt3 = view.findViewById(R.id.txt6);
        txt4 = view.findViewById(R.id.txt8);
        txt5 = view.findViewById(R.id.txt10);
        txt6 = view.findViewById(R.id.tx12);
        txt7 = view.findViewById(R.id.txt12);
        txt8 = view.findViewById(R.id.txt14);

        //tableLayout = view.findViewById(R.id.tbl1);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        requestQueue = Volley.newRequestQueue(getActivity());


        HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail1();
        String chkid = user.get(sharedPrefrencesHelper.chklistID);

        String url ="https://assettrack.cx/android_api/lrvstatuspf.php?chklistID=" + chkid;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String conf = jsonObject.optString("vehicle");
                        String lv = jsonObject.optString("leadvehicle");
                        String mv = jsonObject.optString("midvehicle");
                        String tv = jsonObject.optString("trailvehicle");
                        String sname = jsonObject.optString("supervisername");
                        String pstatus = jsonObject.optString("pdcStatus");
                        String sdate = jsonObject.optString("pdcStartDt");
                        String endate = jsonObject.optString("pdcEndDt");

                        txt1.setText(conf);
                        txt2.setText(lv);
                        if(mv.equals("null")){
                            txt3.setText("N/A");
                        }else {
                            txt3.setText(mv);
                        }

                        txt4.setText(tv);
                        txt5.setText(sname);
                        txt6.setText(sdate);
                        txt7.setText(endate);
                        txt8.setText(pstatus);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);

        String url1 ="https://assettrack.cx/android_api/lrvchklist.php?chklistID=" + chkid;

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String desc = jsonObject.optString("stdDesc");
                        String status = jsonObject.optString("stdStatus");
                        String grpid = jsonObject.optString("groupID");

                    }
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




        return view;
    }
}