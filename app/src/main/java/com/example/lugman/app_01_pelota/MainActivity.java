package com.example.lugman.app_01_pelota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Gestion.class);
                switch (view.getId()){
                    case R.id.facil:
                        intent.putExtra("DIFICULTAD",3);
                        Log.d("CLick","3");

                        startActivity(intent);
                        break;
                    case R.id.medio:
                        intent.putExtra("DIFICULTAD",3);
                        startActivity(intent);
                        break;
                    case R.id.dificil:
                        intent.putExtra("DIFICULTAD",3);
                        startActivity(intent);
                        break;
                default:
                    Log.d("CLick","Default");

                }
            }
        };

        facil.setOnClickListener(listener);
        normal.setOnClickListener(listener);
        dificil.setOnClickListener(listener);
    }

    private void ayuda() {
        Intent i =  new Intent(MainActivity.this,Ayuda.class);
        startActivity(i);
    }
}
