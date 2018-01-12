package com.example.lugman.app_01_pelota;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Gestion extends AppCompatActivity {

    private Partida partida;
    private  int dificultad;
    private int FPS = 30;
    private Handler temporizador;
    private int puntos = 0 ;
    MediaPlayer golpe,finS;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras  = getIntent().getExtras();

        dificultad =  extras.getInt("DIFICULTAD");

        partida = new Partida(getApplicationContext(),dificultad);
        setContentView(partida);

        temporizador = new Handler();
        temporizador.postDelayed(elhilo,2000);

        finS=MediaPlayer.create(this,R.raw.fin);
        golpe=MediaPlayer.create(this,R.raw.golpe);
    }
    private  Runnable elhilo =  new Runnable() {
        @Override
        public void run() {
            if (partida.movimientoBola()){
                fin();
            }else {
                partida.invalidate();
                temporizador.postDelayed(elhilo,1000/FPS);
            }
        }
    };


    public boolean onTouchEvent (MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(partida.toque(x,y)){
        golpe.start();
        puntos++;
            MediaPlayer mediaPlayer= new MediaPlayer();

        }
        return false;
    }
    public  void fin(){
        temporizador.removeCallbacks(elhilo);
        Intent in =  new Intent(Gestion.this,MainActivity.class);
        in.putExtra("TOQUES",puntos);
        setResult(RESULT_OK,in);
        finish();
        finS.start();
    }
}
