package com.example.managentorapp.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Imagenes;
import com.example.managentorapp.entitities.Usuario;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private TextView registerBtn;
    private Button loginButton;

    private Usuario user = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initComponents();
    }

    public void initComponents(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.BtnRegister);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData(email.getText().toString(),password.getText().toString());
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    public void initData(String email, String password){
        user.setEmail(email);
        user.setPassword(password);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Usuario> call = apiService.login(user);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Usuario userRespuesta = response.body();
                    if(userRespuesta != null){
                        // Para guardar los datos
                        SharedPreferences sharedPref = getSharedPreferences("myPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("ID_USER", userRespuesta.getIdEmple());
                        editor.apply();

                        Intent intent = new Intent(getBaseContext(), LstPropActivity.class);
                        //intent.putExtra("idUser",userRespuesta.getIdEmple());
                        startActivity(intent);




                    }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "¡Vaya, parece que ese usuario no existe. Intentalo de nuevo!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
