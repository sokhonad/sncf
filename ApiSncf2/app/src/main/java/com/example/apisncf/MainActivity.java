package com.example.apisncf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerfrom, spinnerto;
    Button btn_send;
    private static final String[] path = {"75056", "76540", "76351", "13055", "69123","31555","06088","44109","67482","59350","72181"};
    private static final String[] paths = {"Paris", "Rouen", "Le Havre", "Marseille", "Lyon","Toulouse","Nice","Nantes","Strasbourg","Lille","Le Mans"};
    private String from,fromVille, to,toVille;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        from = "";
        to = "";
        fromVille ="";
        toVille = "";
        //--------------------------------------------
        btn_send = findViewById(R.id.btn_send);
        spinnerfrom = (Spinner)findViewById(R.id.sipnner_from);
        spinnerto = (Spinner)findViewById(R.id.sipnner_to);
        //--------------------------------------------
        ArrayAdapter<String>adapterfrom = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,paths);
        adapterfrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String>adapterto = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,paths);
        adapterto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //--------------------------------------------
        spinnerfrom.setAdapter(adapterfrom);
        spinnerfrom.setOnItemSelectedListener(this);
        //----------------
        spinnerto.setAdapter(adapterto);
        spinnerto.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinfrom = (Spinner)parent;
        Spinner spinto = (Spinner)parent;
        if(spinfrom.getId() == R.id.sipnner_from){
            if((String) parent.getItemAtPosition(position) == "Paris"){
                from = "75056";
                fromVille = "Paris";
            }else if((String) parent.getItemAtPosition(position) =="Rouen"){
                from = "76540";
                fromVille = "Rouen";
            }else if((String) parent.getItemAtPosition(position) =="Le Havre"){
                from = "76351";
                fromVille = "Le Havre";
            }else if((String) parent.getItemAtPosition(position) =="Marseille"){
                from = "13055";
                fromVille = "Marseille";
            }else if((String) parent.getItemAtPosition(position) =="Lyon"){
                from = "69123";
                fromVille = "Lyon";
            }else if((String) parent.getItemAtPosition(position) =="Toulouse"){
                from = "31555";
                fromVille = "Toulouse";
            }else if((String) parent.getItemAtPosition(position) =="Nice"){
                from = "06088";
                fromVille = "Nice";
            }else if((String) parent.getItemAtPosition(position) =="Nantes"){
                from = "44109";
                fromVille = "Nantes";
            }else if((String) parent.getItemAtPosition(position) =="Strasbourg"){
                from = "67482";
                fromVille = "Strasbourg";
            }else if((String) parent.getItemAtPosition(position) =="Lille"){
                from = "59350";
                fromVille = "Lille";
            }else if((String) parent.getItemAtPosition(position) =="Le Mans"){
                from = "72181";
                fromVille = "Le Mans";
            }
            Log.v("item", "from : "+(String) parent.getItemAtPosition(position));
            //from = (String) parent.getItemAtPosition(position);
        }
        if(spinto.getId() == R.id.sipnner_to){
            Log.v("item","to : "+ (String) parent.getItemAtPosition(position));
            if((String) parent.getItemAtPosition(position) == "Paris"){
                to = "75056";
                toVille = "Paris";
            }else if((String) parent.getItemAtPosition(position) =="Rouen"){
                to = "76540";
                toVille = "Rouen";
            }else if((String) parent.getItemAtPosition(position) =="Le Havre"){
                to = "76351";
                toVille = "Le Havre";
            }else if((String) parent.getItemAtPosition(position) =="Marseille"){
                to = "13055";
                toVille = "Marseille";
            }else if((String) parent.getItemAtPosition(position) =="Lyon"){
                to = "69123";
                toVille = "Lyon";
            }else if((String) parent.getItemAtPosition(position) =="Toulouse"){
                to = "31555";
                toVille = "Toulouse";
            }else if((String) parent.getItemAtPosition(position) =="Nice"){
                to = "06088";
                toVille = "Nice";
            }else if((String) parent.getItemAtPosition(position) =="Nantes"){
                to = "44109";
                toVille = "Nantes";
            }else if((String) parent.getItemAtPosition(position) =="Strasbourg"){
                to = "67482";
                toVille = "Strasbourg";
            }else if((String) parent.getItemAtPosition(position) =="Lille"){
                to = "59350";
                toVille = "Lille";
            }else if((String) parent.getItemAtPosition(position) =="Le Mans"){
                to = "72181";
                toVille = "Le Mans";
            }
        }

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fromVille.equals(toVille)){
                    Intent intent = new Intent(getBaseContext(), DataActivity.class);
                    intent.putExtra("from", from);
                    intent.putExtra("fromVille", fromVille);
                    intent.putExtra("to", to);
                    intent.putExtra("toVille", toVille);
                    startActivity(intent);
                }else{
                    Toast.makeText(v.getContext(), "Your origin and destination points are the same", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Choose cities :", Toast.LENGTH_SHORT).show();
    }
}
