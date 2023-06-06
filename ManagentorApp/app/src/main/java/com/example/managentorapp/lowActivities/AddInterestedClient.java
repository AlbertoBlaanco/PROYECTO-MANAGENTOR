package com.example.managentorapp.lowActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInterestedClient extends AppCompatActivity {

    private EditText clientName, clientPhone;
    private Button save;

    private Cliente client = new Cliente();
    private int idInmueble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_interested_client);
        initComponents();
    }

    public void initComponents(){
        idInmueble = getIntent().getExtras().getInt("idInmueble");
        clientName = findViewById(R.id.client_name_edit);
        clientPhone = findViewById(R.id.client_phone_edit);
        save = findViewById(R.id.save_client);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VerDetallesActivity.class);
                intent.putExtra("idInmueble",idInmueble);
                startActivity(intent);
            }
        });
    }

    public void initData(){

        this.client.setNombreCli(clientName.getText().toString());
        this.client.setTelefonoCli(Integer.parseInt(clientPhone.getText().toString()));
        this.client.setIdInmueble(idInmueble);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiService.addClientInt(client);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Intent intent = new Intent(getBaseContext(), VerDetallesActivity.class);
                intent.putExtra("idInmueble",idInmueble);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}