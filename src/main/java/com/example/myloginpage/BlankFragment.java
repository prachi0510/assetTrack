package com.example.myloginpage;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class BlankFragment extends Fragment {
    TextView textView,textView1,textView2,tv,tv2,tv3,tv4,tv5,txt1,tx,tx1,tx2;
    Button btn,btn1,btn2,btn3,update,btn4,btn5;
    LinearLayout linear1,linear2,linear3,linear4,lsp1,lsp2,ln,ln1,ln2,ln3,ln4,ln5;
    TableLayout tableLayout,tableLayout1,tableLayout2,tbl,tbl1,tbl2;
    TableRow tr,tr1,tr2;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=  inflater.inflate(R.layout.fragment_blank, container, false);



        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.logo3);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);

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
        // String congf = bundle.getString("config");
//        String leadid = bundle.getString("leadid");
//        String trailid = bundle.getString("trailid");
//        String leadvid = bundle.getString("leadvid");
//        String trailvid = bundle.getString("traivlid");
//        String midvid = bundle.getString("midvid");
        String chkid = bundle.getString("chkid");

//        Bundle bundle1 = getArguments();
//        String leadid2 = bundle1.getString("leadid2");
//        String trailid2 = bundle1.getString("trailid2");
//        String leadvid2 = bundle1.getString("leadvid2");
//        String trailvid2 = bundle1.getString("traivlid2");
//        String midvid2 = bundle1.getString("midvid2");





//            String msg = bundle.getString("lead");
//            String msg1 = bundle.getString("trail");
//            String msg2 = bundle.getString("date");
//
//            textView.setText(msg);
//            textView1.setText(msg1);
//            textView2.setText(msg2);
//            btn1.setText(msg);
//            btn2.setText(msg1);
//            btn1.setText("Lead Vehicle(" +msg+ ")");
//            btn2.setText("Trail Vehicle(" +msg1+ ")");
//            tv2.setText("LRV Config Details added! Click the Lead/Trail vehicle to update the standard(s) whichever applicable.");
        String url6 = "https://assettrack.cx/android_api/lrvstatus.php?chklistID"+chkid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url6, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String chid1 = jsonObject.optString("leadVehicleItems");
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setCancelable(true);
                        //builder.setTitle("DialogBox");
                        builder.setMessage(response.toString());
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
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                //builder.setTitle("DialogBox");
                builder.setMessage(error.toString());
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });
        requestQueue.add(jsonObjectRequest);









        return  view;

    }
}