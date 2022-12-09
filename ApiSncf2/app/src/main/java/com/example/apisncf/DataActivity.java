package com.example.apisncf;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class DataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView txt_depart,txt_arrive;
    String url_All ="https://api.sncf.com/v1/coverage/sncf/stop_areas/stop_area:OCE:SA:87391003/departures?datetime=20200110T143355";
    String url_data = "https://api.sncf.com/v1/coverage/sncf/journeys?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        // Get all componenets from layout activity
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        txt_depart = findViewById(R.id.txt_depart);
        txt_arrive = findViewById(R.id.txt_arrive);
        // create a layout manager for the recycler
        layoutManager = new LinearLayoutManager(this);
        // set the layout manager
        recyclerView.setLayoutManager(layoutManager);
        // fix the size of the recycler
        recyclerView.setHasFixedSize(true);
        // seting the txt_depart/arrive data
        txt_depart.setText(getIntent().getStringExtra("fromVille"));
        txt_arrive.setText(getIntent().getStringExtra("toVille"));
        // luncing the getdata function on the creating of the activity
        getData();
    }

    public void getData(){
        // get data from mainactivity with intent
        final String depart = getIntent().getStringExtra("from");
        final String arrive = getIntent().getStringExtra("to");
        Log.d(String.valueOf(DataActivity.this),"depart: "+depart.toString());
        // fix the rest of the data in the url
        String url = url_data;
        url += "from=admin:fr:" + depart.toString();
        url += "&to=admin:fr:" + arrive.toString();
        url += "&datetime=";
        url += android.text.format.DateFormat.format("yyyyMMdd", new java.util.Date());
        url += "T";
        url += android.text.format.DateFormat.format("HHmmss", new java.util.Date());
        url += "&min_nb_journeys=25";
        Log.d(String.valueOf(DataActivity.this),"url: "+url.toString());
        // creating a GET request to get data from the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // affiche le resutat response
                Log.d(String.valueOf(DataActivity.this),"response: "+response.toString());
                try {
                    //creating a list of objects sncf
                    ArrayList<Sncf> ListSncf = new ArrayList<Sncf>();
                    //creating a json object from the response
                    JSONObject jsonObject = new JSONObject(response);
                    // getting the list "journeys" from the response
                    JSONArray journeys = jsonObject.getJSONArray("journeys");
                    // loop on the journeys response
                    for(int i=0;i<journeys.length();i++){
                        // creating a json object from the journys response
                        JSONObject jo = journeys.getJSONObject(i);
                        // creating a Sncf object
                        Sncf sn = new Sncf();
                        // adding data to the object
                        // getting the correct date format
                        StringTokenizer tokensdepart = new StringTokenizer(jo.getString("departure_date_time"), "T");
                        StringTokenizer tokensarrive = new StringTokenizer(jo.getString("arrival_date_time"), "T");
                        String dateDepart = tokensdepart.nextToken();
                        String heureDepart = tokensdepart.nextToken();
                        String dateArrive = tokensarrive.nextToken();
                        String heureArrive = tokensarrive.nextToken();
                        sn.setDepart(getIntent().getStringExtra("fromVille").toString());
                        sn.setArrive(getIntent().getStringExtra("toVille").toString());
                        sn.setDateDepart(dateDepart + " à "+heureDepart);
                        sn.setDateArrive(dateArrive + " à "+ heureArrive);
                        // adding the objects to the sncf list
                        ListSncf.add(sn);
                    }
                    // fixing the recycler adapter with the data list
                    adapter = new recyclerAdapter(ListSncf, DataActivity.this);
                    //setting our recycler to the adapter created
                    recyclerView.setAdapter(adapter);

                    Log.d(String.valueOf(DataActivity.this),"jsonObject: "+journeys.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //txt_test.setText(error.toString());
                Log.d(String.valueOf(DataActivity.this),"error: "+error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // setting the headers for the authentification in the SNCF API
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "f6ce7953-edd1-4ae3-8598-2d39b7b7a2fe");
                headers.put("api-version", "1.0");
                return headers;
            }
        };
        // Creating a request queue wich will put our request in a queue to be treated
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // adding our reequest in the request queue
        requestQueue.add(stringRequest);
    }

}
