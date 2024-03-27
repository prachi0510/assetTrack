package com.example.myloginpage;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import com.example.myloginpage.Install;
import com.example.myloginpage.PDC;
import com.example.myloginpage.R;
import com.example.myloginpage.SharedPrefrencesHelper;
import com.example.myloginpage.pdcoverview;
import com.example.myloginpage.settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class generalSTD extends Fragment implements Toolbar.OnMenuItemClickListener {
    TextView textView,textView1,textView2,textView3,textView4;
    TableLayout tableLayout;
    TableRow tableRow;

   JSONArray jsonArray;

   RequestQueue requestQueue;
    Button btn, btn1,next,back;

    LinearLayout linear1,linear2,linear3,linear4,lsp1,lsp2;
    SharedPrefrencesHelper sharedPrefrencesHelper;
   boolean click =false;
   Toolbar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view= inflater.inflate(R.layout.fragment_general_s_t_d,container,false);
        sharedPrefrencesHelper = new SharedPrefrencesHelper(getActivity());
        sharedPrefrencesHelper.checklogin();

        toolbar=view.findViewById(R.id.my_toolbar);
        toolbar.setLogo(R.drawable.assynt_logo_glyph_white);
        toolbar.setTitle("     Crosslinx Transit Solution");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);


        requestQueue = Volley.newRequestQueue(getActivity());
       textView=view.findViewById(R.id.tv2);
        linear1=view.findViewById(R.id.linear2);
        linear2=view.findViewById(R.id.linear3);
        linear3=view.findViewById(R.id.linear5);
        linear4=view.findViewById(R.id.linear4);

        textView1 = view.findViewById(R.id.lvd);
        textView2 = view.findViewById(R.id.mvd);
        textView3 = view.findViewById(R.id.tvd);
        textView4 = view.findViewById(R.id.dtd);


        tableLayout = view.findViewById(R.id.table);

        btn =view.findViewById(R.id.btn);
        btn.setTag(1);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        linear1.setVisibility(View.GONE);
        linear2.setVisibility(View.GONE);
        linear3.setVisibility(View.GONE);
        linear4.setVisibility(View.GONE);

        Bundle bundle=getArguments();
        String lead = bundle.getString("lead");
        String leadv =bundle.getString("leadv");
        String conf = bundle.getString("config");
        String midv =bundle.getString("midv");
        String trailv = bundle.getString("trailv");
        String date = bundle.getString("date");
        String leadid= bundle.getString("leadid1");
        String leadvid= bundle.getString("leadvid1");
        String leadid1= bundle.getString("leadid");
        String leadvid1= bundle.getString("leadvid");
        String trailid = bundle.getString("trailid");
        String trailvid = bundle.getString("traivlid");
        String midvid = bundle.getString("midvid");
        String chkid =bundle.getString("chkid");





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


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(click) {
    String leadv = textView1.getText().toString();
    String midv = textView2.getText().toString();
    String trailv = textView3.getText().toString();
    String date = textView4.getText().toString();
    bundle.putString("lead", lead);
    bundle.putString("leadv", leadv);
    bundle.putString("chkid", chkid);
    bundle.putString("midv", midv);
    bundle.putString("trailv", trailv);
    bundle.putString("config", conf);
    bundle.putString("date", date);
    bundle.putString("leadid1", leadid);
    bundle.putString("leadid",leadid1);
    bundle.putString("trailid",trailid);
    bundle.putString("midvid",midvid);
    bundle.putString("leadvid",leadvid1);
    bundle.putString("traivlid",trailvid);
    Fragment fragment = new cleaningSTD();
    fragment.setArguments(bundle);
    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.frame1, fragment).addToBackStack(null).commit();
}else {
            Toast.makeText(getActivity().getBaseContext(),"please select status of checklist", Toast.LENGTH_SHORT).show();
        }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.putString("config", conf);
                    bundle.putString("leadid",leadid1);
                    bundle.putString("trailid",trailid);
                     bundle.putString("midvid",midvid);
                     bundle.putString("leadvid",leadvid1);
                    bundle.putString("traivlid",trailvid);

                    bundle.putString("lead", leadv);
                    bundle.putString("mid", midv);
                    bundle.putString("trail", trailv);

             
                Fragment fragment= new pdcoverview();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit();

            }
        });

     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             final  int  status  =(Integer) v.getTag();
             if(status==1) {
             if(conf.equals("2")) {
                     linear1.setVisibility(View.VISIBLE);
                     linear2.setVisibility(View.VISIBLE);
                     linear4.setVisibility(View.VISIBLE);
                    // Toast.makeText(getActivity().getBaseContext(),leadid,Toast.LENGTH_SHORT).show();
             } else if (conf.equals("3")) {
                     linear1.setVisibility(View.VISIBLE);
                     linear2.setVisibility(View.VISIBLE);
                     linear3.setVisibility(View.VISIBLE);
                     linear4.setVisibility(View.VISIBLE);
                 Toast.makeText(getActivity().getBaseContext(),leadvid,Toast.LENGTH_SHORT).show();
             }
             v.setTag(0);
         }else{
             linear1.setVisibility(View.GONE);
             linear2.setVisibility(View.GONE);
             linear3.setVisibility(View.GONE);
             linear4.setVisibility(View.GONE);

             v.setTag(1);
             }


         }
     });

        String url = "https://assettrack.cx/android_api/getgeneralstd.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.optString("id");
                        String general = jsonObject.optString("stdDesc");
                        TableRow tableRow =new TableRow(getActivity());

                        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
                        tableRow.setPadding(10, 5, 5, 5);
                        TextView textView = new TextView(getActivity());
                       // textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        Button button = new Button(getActivity());
                        button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        Button button1 = new Button(getActivity());
                        button1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        textView.setWidth(530);

                        textView.setText(general);

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

                                      }
                                  }, new Response.ErrorListener() {
                                      @Override
                                      public void onErrorResponse(VolleyError error) {

                                          AlertDialog.Builder builder1= new AlertDialog.Builder(getActivity());
                                          builder1.setCancelable(true);
                                          //builder.setTitle("DialogBox");
                                          builder1.setMessage(url + error);
                                          builder1.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  dialog.cancel();
                                              }
                                          });
                                          builder1.show();

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