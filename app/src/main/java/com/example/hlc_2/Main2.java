package com.example.hlc_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hlc_2.databinding.ActivityMain2Binding;

public class Main2 extends AppCompatActivity implements View.OnClickListener {
    private ActivityMain2Binding binding;
    Intent intent;
    int code = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonLanzarNavegador.setOnClickListener(this);
        binding.botonLanzarWebView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view == binding.botonLanzarNavegador)
        {
            String enlace = String.valueOf(binding.textUrl.getText());
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(enlace));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error al lanzar el intent", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == binding.botonLanzarWebView)
        {
            intent = new Intent(this, Second2.class);
            Bundle extras = new Bundle();
            extras.putString("web",binding.textUrl.getText().toString());
            intent.putExtras(extras);
            startActivityForResult(intent,code);
        }
    }


}
