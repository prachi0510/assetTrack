package com.example.myloginpage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;


public class interiorStd extends Fragment implements Toolbar.OnMenuItemClickListener {

    TableLayout tableLayout;
    JSONArray jsonArray;
    RequestQueue requestQueue;
    Button button,btn,back;
    SharedPrefrencesHelper sharedPrefrencesHelper;
    TextView textView,textView1,textView2,textView3,textView4;
    LinearLayout linearLayout,linearLayout1,linearLayout2,linearLayout3;
    boolean click = false;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_interior_std, container, false);
        tableLayout = view.findViewById(R.id.table);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();
        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
        requestQueue = Volley.newRequestQueue(getActivity());
        linearLayout =view.findViewById(R.id.linear2);
        linearLayout1 =view.findViewById(R.id.linear5);
        linearLayout2 =view.findViewById(R.id.linear3);
        linearLayout3=view.findViewById(R.id.linear4);
        linearLayout.setVisibility(View.GONE);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        linearLayout3.setVisibility(View.GONE);

        textView= view.findViewById(R.id.tv2);
        textView1 = view.findViewById(R.id.lvd);
        textView2 = view.findViewById(R.id.mvd);
        textView3 = view.findViewById(R.id.tvd);
        textView4 = view.findViewById(R.id.dtd);

        button = view.findViewById(R.id.next);
        back=view.findViewById(R.id.back);
        btn =view.findViewById(R.id.btn);
        btn.setTag(1);
        Bundle bundle=getArguments();
        String lead = bundle.getString("lead");
        String leadv =bundle.getString("leadv");
        String conf = bundle.getString("config");
        String midv =bundle.getString("midv");
        String trailv = bundle.getString("trailv");
        String date = bundle.getString("date");
        String chkid=bundle.getString("chkid");
        String leadid= bundle.getString("leadid1");
        String leadvid= bundle.getString("leadvid1");
        String leadid1= bundle.getString("leadid");
        String leadvid1= bundle.getString("leadvid");
        String trailid = bundle.getString("trailid");
        String trailvid = bundle.getString("traivlid");
        String midvid = bundle.getString("midvid");

        textView.setText(lead);
        if(conf.equals("2")) {
            textView1.setText(leadv);
            textView3.setText(trailv);
            textView4.setText(date);
        } else if (conf.equals("3")) {
            textView1.setText(leadv);
            textView2.setText(midv);
            textView3.setText(trailv);
            textView4.setText(date);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  int  status  =(Integer) v.getTag();
                if(status==1) {
                    if(conf.equals("2")) {
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.VISIBLE);
                        linearLayout3.setVisibility(View.VISIBLE);
                    } else if (conf.equals("3")) {
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.VISIBLE);
                        linearLayout3.setVisibility(View.VISIBLE);
                    }
                    v.setTag(0);
                }else{
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);

                    v.setTag(1);
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("config", conf);
                bundle.putString("lead", leadv);
                bundle.putString("mid", midv);
                bundle.putString("trail", trailv);
                bundle.putString("leadid1",leadid );
                bundle.putString("leadid",leadid1);
                bundle.putString("trailid",trailid);
                bundle.putString("midvid",midvid);
                bundle.putString("leadvid",leadvid1);
                bundle.putString("traivlid",trailvid);

                Fragment fragment= new vehicleSelfTest();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click) {
                    String leadv = textView1.getText().toString();
                    String midv = textView2.getText().toString();
                    String trailv = textView3.getText().toString();
                    String date = textView4.getText().toString();
                    bundle.putString("lead", lead);
                    bundle.putString("leadv", leadv);
                    bundle.putString("midv", midv);
                    bundle.putString("trailv", trailv);
                    bundle.putString("config", conf);
                    bundle.putString("date", date);
                    bundle.putString("chkid", chkid);
                    bundle.putString("leadid1", leadid);
                    bundle.putString("leadid",leadid1);
                    bundle.putString("trailid",trailid);
                    bundle.putString("midvid",midvid);
                    bundle.putString("leadvid",leadvid1);
                    bundle.putString("traivlid",trailvid);
                    Fragment fragment = new interiorStd1();
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();

                }else {
                    Toast.makeText(getActivity().getBaseContext(),"please select status of checklist", Toast.LENGTH_SHORT).show();
                }
            }
        });


        String url = "https://assettrack.cx/android_api/interiorstd.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String istd= jsonObject.optString("stdDesc");
                        String id = jsonObject.optString("id");
                        TableRow tableRow =new TableRow(getActivity());
                        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.WRAP_CONTENT,
                                TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                        tableRow.setPadding(20, 5, 20, 5);
                        TextView textView = new TextView(getActivity());
                        Button button = new Button(getActivity());
                        button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        Button button1 = new Button(getActivity());
                        button1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        textView.setWidth(530);

                        textView.setText(istd);
                       // textView.setGravity(Gravity.CENTER);
                        tableRow.addView(textView);
                        TextView textView2 = new TextView(getActivity());
                        textView2.setText(" ");
                        tableRow.addView(textView2);

                        button.setBackgroundResource(R.drawable.yesbutton);

                        button.setGravity(Gravity.CENTER);
                        button.setTextColor(Color.WHITE);
                        button.setText("Yes");
                        tableRow.addView(button);
                        TextView textView1 = new TextView(getActivity());
                        textView1.setText(" ");
                        tableRow.addView(textView1);

                        button1.setBackgroundResource(R.drawable.nobutton);

                        button1.setGravity(Gravity.CENTER);
                        button1.setTextColor(Color.WHITE);
                        button1.setText("No");
                        tableRow.addView(button1);
                        tableLayout.addView(tableRow);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                button.setEnabled(false);
                                button.setBackgroundColor(Color.parseColor("#76b290"));
                                button1.setEnabled(true);
                                button1.setBackgroundColor(Color.parseColor("#dc3545"));
                                HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                                String uid = user.get(sharedPrefrencesHelper.EMAIL);
                                if(conf.equals("2")) {
                                    click=true;
                                    String url ="https://assettrack.cx/android_api/setstatus.php?chklistID=" + chkid + "&lrv_id=" +leadid+ "&stdID=" + id + "&updatedBy=" + uid + "&stdStatus=1";

                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            //Toast.makeText(getActivity().getBaseContext(),response,Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                            // Toast.makeText(getActivity().getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    requestQueue.add(stringRequest1);
                                }
                                else if (conf.equals("3")) {
                                    click=true;
                                    String url ="https://assettrack.cx/android_api/setstatus.php?chklistID=" + chkid + "&lrv_id=" +leadid+ "&stdID=" + id + "&updatedBy=" + uid + "&stdStatus=1";


                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            // Toast.makeText(getActivity().getBaseContext(),response,Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                            // Toast.makeText(getActivity().getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    requestQueue.add(stringRequest1);

                                }
                            }
                        });

                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                button1.setEnabled(false);
                                button1.setBackgroundColor(Color.parseColor("#eb7f87"));
                                button.setEnabled(true);
                                button.setBackgroundColor(Color.parseColor("#1a8754"));
                                HashMap<String, String> user = sharedPrefrencesHelper.getuserdetail();
                                String uid = user.get(sharedPrefrencesHelper.EMAIL);
                                if(conf.equals("2")) {
                                    click=true;
                                    String url ="https://assettrack.cx/android_api/setstatus.php?chklistID=" + chkid + "&lrv_id=" +leadid+ "&stdID=" + id + "&updatedBy=" + uid + "&stdStatus=0";

                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            //Toast.makeText(getActivity().getBaseContext(),response,Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                            // Toast.makeText(getActivity().getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    requestQueue.add(stringRequest1);
                                }
                                else if (conf.equals("3")) {
                                    click=true;
                                    String url ="https://assettrack.cx/android_api/setstatus.php?chklistID=" + chkid + "&lrv_id=" +leadid+ "&stdID=" + id + "&updatedBy=" + uid + "&stdStatus=0";


                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            // Toast.makeText(getActivity().getBaseContext(),response,Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                            // Toast.makeText(getActivity().getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    requestQueue.add(stringRequest1);

                                }

                            }
                        });

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

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
            fm.replace(R.id.frame1, fragment2).addToBackStack(null).commit();

        }else if (item.getItemId() == R.id.OSC) {

            Fragment fragment1 = new Install();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame1, fragment1).addToBackStack(null).commit();
        }
        return  true;
    }
}