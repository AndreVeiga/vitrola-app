package com.aulas.mobile.trabalhodois;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aulas.mobile.trabalhodois.activity.Home;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private final String MESSAGE = "Seu nome, por favor!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
    }

    public void openScreenMain(View v){
        if(name.getText() == null || name.getText().toString().isEmpty()){
            Toast.makeText(this, MESSAGE, Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, Home.class);
        intent.putExtra("name", name.getText().toString());
        startActivity(intent);
    }
}