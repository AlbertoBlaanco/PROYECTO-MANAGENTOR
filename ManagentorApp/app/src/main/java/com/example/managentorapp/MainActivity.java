package com.example.managentorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.login.LoginActivity;
import com.example.managentorapp.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);

    }
}