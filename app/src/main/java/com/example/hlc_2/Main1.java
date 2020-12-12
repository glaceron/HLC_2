package com.example.hlc_2;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hlc_2.databinding.ActivityMain1Binding;

public class Main1 extends AppCompatActivity implements View.OnClickListener
{
    private ActivityMain1Binding binding;
    private  Contador contador;
    private  MyCountDownTimer miContadorDescendente;
    private AlertDialog.Builder popup;
    private MediaPlayer mp;
    private static final int PAUSA = 3;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        contador = new  Contador (0, PAUSA);
        binding.tiempo.setText(PAUSA + ":00");
        binding.botonMenos.setOnClickListener(this);
        binding.botonMas.setOnClickListener(this);
        binding.botonComenzar.setOnClickListener(this);
        binding.botonReiniciarCafes.setOnClickListener(this);
        binding.switch1.setOnClickListener(this);



        this.mp = MediaPlayer.create(this, R.raw.audio);
        this.popup=new AlertDialog.Builder(this);
    }

    @Override
    public void onClick(View view)
    {

        if (view == binding.botonMenos)
        {
            binding.tiempo.setText(contador.disminuirTiempo());
        }
        if (view == binding.botonMas)
        {
            binding.tiempo.setText(contador.aumentarTiempo());
        }
        if (view == binding.switch1)
        {
            if(binding.switch1.isChecked())
            {
                binding.switch1.setText("Ascendente");
            }
            else
            {
                binding.switch1.setText("Descendente");
            }
        }
        if (view == binding.botonComenzar)
        {

            if(binding.switch1.isChecked())
            {
                CountUpTimer timer = new CountUpTimer(contador.getTiempo() * 60 * 1000)
                {

                    @Override
                    public void onTick(int second)
                    {

                    }
                };
                timer.start();
                binding.botonMenos.setEnabled(false);
                binding.botonMas.setEnabled(false);
                binding.botonComenzar.setEnabled(false);
                binding.switch1.setEnabled(false);
                binding.botonReiniciarCafes.setEnabled(false);
            }
            if(!binding.switch1.isChecked())
            {
                miContadorDescendente = new MyCountDownTimer(contador.getTiempo() * 60 * 1000, 1000);
                //miContadorDescendente = new MyCountDownTimer(contador.getTiempo() * 1000, 1000);

                miContadorDescendente.start();
                binding.botonMenos.setEnabled(false);
                binding.botonMas.setEnabled(false);
                binding.botonComenzar.setEnabled(false);
                binding.switch1.setEnabled(false);
                binding.botonReiniciarCafes.setEnabled(false);
            }
        }
        if(view == binding.botonReiniciarCafes)
        {
            contador.resetCafes();
            binding.cuenta.setText(String.valueOf(contador.getCafes()));
            binding.botonMenos.setEnabled(true);
            binding.botonMas.setEnabled(true);
            binding.botonComenzar.setEnabled(true);
            binding.switch1.setEnabled(true);
        }
    }
    public abstract class CountUpTimer extends CountDownTimer
    {
        private static final long INTERVAL_MS = 1000;
        private final long duration;

        protected CountUpTimer(long durationMs)
        {
            super(durationMs, INTERVAL_MS);
            this.duration = durationMs;
        }

        public abstract void onTick(int second);

        @Override
        public void onTick(long msUntilFinished)
        {
            long minutos, segundos;
            int second = (int) ((duration - msUntilFinished) / 1000);
            minutos = second / 60;
            segundos = second % 60;
            binding.tiempo.setText(minutos + ":" + String.format("%02d",segundos));
            onTick(second);
        }

        @Override
        public void onFinish()
        {
            onTick(duration / 1000);
            //binding.tiempo.setText("Pausa terminada!!");
            Toast.makeText(Main1.this, "Pausa terminada", Toast.LENGTH_SHORT).show();
            binding.tiempo.setText(contador.getTiempo() + ":00");
            binding.botonReiniciarCafes.setEnabled(true);
            mp.start();
            binding.cuenta.setText(contador.aumentarCafes());
            if (contador.getCafes() < 3)
            {
                binding.botonMenos.setEnabled(true);
                binding.botonMas.setEnabled(true);
                binding.botonComenzar.setEnabled(true);
                binding.switch1.setEnabled(true);

            }
            else if (contador.getCafes() == 3)
            {
                popup.setTitle("Fin!!!");
                popup.setMessage("Te has tomado 10 cafes");
                popup.setPositiveButton("Ok", null);
                popup.show();
            }
        }
    }



    public class MyCountDownTimer extends CountDownTimer
    {
        public MyCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override public void onTick (long millisUntilFinished)
        {
            long minutos, segundos;

            minutos = (millisUntilFinished / 1000) / 60;
            segundos = (millisUntilFinished / 1000) % 60;
            binding.tiempo.setText(minutos + ":" + String.format("%02d",segundos));

        }

        @Override
        public void onFinish()
        {
            //binding.tiempo.setText("Pausa terminada!!");
            Toast.makeText(Main1.this, "Pausa terminada", Toast.LENGTH_SHORT).show();
            binding.tiempo.setText(contador.getTiempo() + ":00");
            binding.botonReiniciarCafes.setEnabled(true);
            mp.start();
            binding.cuenta.setText(contador.aumentarCafes());
            if(contador.getCafes() < 10)
            {
                binding.botonMenos.setEnabled(true);
                binding.botonMas.setEnabled(true);
                binding.botonComenzar.setEnabled(true);
                binding.switch1.setEnabled(true);
            }
            else if(contador.getCafes() == 10)
            {
                popup.setTitle("Fin!!!");
                popup.setMessage("Te has tomado 10 cafes");
                popup.setPositiveButton("Ok", null);
                popup.show();
            }
        }
    }
}