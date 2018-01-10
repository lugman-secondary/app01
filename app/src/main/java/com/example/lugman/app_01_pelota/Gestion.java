package com.example.lugman.app_01_pelota;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.On

public class Gestion extends AppCompatActivity {

    private Partida partida;
    private  int dificultad;
    private int FPS = 30;
    private Handler temporizador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras  = getIntent().getExtras();

        dificultad =  extras.getInt("DIFICULTAD");

        partida = new Partida(getApplicationContext(),dificultad);
        setContentView(partida);
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
        partida.toque(x,y);
        return false;
    }
    public  void fin(){
        temporizador.removeCallbacks(elhilo);
        finish();
    }
}
