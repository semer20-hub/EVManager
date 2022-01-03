package com.semer.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText nom,local,date;
    Button modif,delete;

    String _id,_nom,_local,_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        nom=findViewById(R.id.name_event);
        local=findViewById(R.id.local_event);
        date=findViewById(R.id.date_event);
        modif=findViewById(R.id.modif_btn);
        delete=findViewById(R.id.delete_btn);
        DBConnections db = new DBConnections(this);

        getAndSetIntentData();

        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = new Event(Integer.parseInt(_id),nom.getText().toString(),local.getText().toString(),date.getText().toString());
                db.updateEvent(event);
                startActivity(new Intent(EditActivity.this,Dashboard.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = new Event(Integer.parseInt(_id),nom.getText().toString(),local.getText().toString(),date.getText().toString());
                db.deleteEventById(event);
                startActivity(new Intent(EditActivity.this,Dashboard.class));

            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") &&
                getIntent().hasExtra("locale") && getIntent().hasExtra("date")){
            //Getting Data from Intent
            _id = getIntent().getStringExtra("id");
            _nom = getIntent().getStringExtra("nom");
            _local = getIntent().getStringExtra("locale");
            _date = getIntent().getStringExtra("date");

            //Setting Intent Data
            nom.setText(_nom);
            local.setText(_local);
            date.setText(_date);
            Log.d("stev", _nom+" "+_local+" "+_date);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}