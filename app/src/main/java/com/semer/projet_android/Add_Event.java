package com.semer.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Event extends AppCompatActivity {
    EditText nom,local,date;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        nom=findViewById(R.id.name_event);
        local=findViewById(R.id.local_event);
        date=findViewById(R.id.date_event);
        add=findViewById(R.id.add_btn);

        DBConnections db = new DBConnections(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event e = new Event(nom.getText().toString(),local.getText().toString(),date.getText().toString());
                db.InsertRowEvent(e);

                Intent i = new Intent(Add_Event.this,Dashboard.class);
                startActivity(i);
            }
        });
    }
}