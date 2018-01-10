package com.example.lugman.app_01_pelota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button ayuda,facil,dificil,normal;

        ayuda=(Button) findViewById(R.id.ayuda);
        facil=(Button) findViewById(R.id.facil);
        dificil=(Button) findViewById(R.id.dificil);
        normal=(Button) findViewById(R.id.medio);


        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ayuda();
            }
        });
    }

    private void ayuda() {
        Intent i =  new Intent(MainActivity.this,Ayuda.class);
        startActivity(i);
    }
}
