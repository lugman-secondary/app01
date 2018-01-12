package com.example.lugman.app_01_pelota;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button ayuda,facil,dificil,normal;
    TextView puntos;
    int record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ayuda=(Button) findViewById(R.id.ayuda);
        facil=(Button) findViewById(R.id.facil);
        dificil=(Button) findViewById(R.id.dificil);
        normal=(Button) findViewById(R.id.medio);

        puntos =  (TextView) findViewById(R.id.textView);

//        golpeo=MediaPlayer.create(this,android.R.raw.)

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
                        intent.putExtra("DIFICULTAD",1);
                        Log.d("CLick","3");
                        startActivityForResult(intent,121);
                        break;
                    case R.id.medio:
                        intent.putExtra("DIFICULTAD",2);
                        startActivityForResult(intent,121);
                        break;
                    case R.id.dificil:
                        intent.putExtra("DIFICULTAD",3);
                        startActivityForResult(intent,121);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode  == 121 &&  resultCode == RESULT_OK){
            int toques;
            toques = data.getExtras().getInt("TOQUES");
            if (toques > record){
                record = toques;
                puntos.setText("PuntuaciónRecord  \n "+toques);
                guarfaremosReord();

            }else {
                String puntuancion = "Toques hechos "+toques;
                Toast mi_toas = Toast.makeText(this,puntuancion,Toast.LENGTH_LONG);
                mi_toas.setGravity(Gravity.CENTER,0,0);
                mi_toas.show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        leerRecord();

    }

    private void ayuda() {
        Intent i =  new Intent(MainActivity.this,Ayuda.class);
        startActivity(i);
    }


    public void guarfaremosReord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mi_editor = datos.edit();
        mi_editor.putInt("RECORD",record);
        mi_editor.apply();
    }
    public void leerRecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        record=datos.getInt("RECORD",0);
        puntos.setText("Puntuación  \n "+record);

    }
}
