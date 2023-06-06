package com.example.managentorapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Usuario;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText nombre,apellido,fecha_nac,Dni,Pass,empresa,telefono,ciudad,email,direccion;
    private Button BtnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initComponents();

    }

    private void initComponents(){
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.Apellido);
        fecha_nac = findViewById(R.id.Fecha_Nacimiento);
        Dni = findViewById(R.id.DNI);
        Pass = findViewById(R.id.Password);
        empresa = findViewById(R.id.Empresa);
        telefono = findViewById(R.id.Telefono);
        ciudad = findViewById(R.id.Ciudad);
        email = findViewById(R.id.Email);
        direccion = findViewById(R.id.Direccion);
        BtnRegister = findViewById(R.id.register_button);

        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);

                startActivity(intent);
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidForm()){
                    initData();
                }else{
                    Toast.makeText(RegisterActivity.this, "Por favor, revisa tus campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initData(){
        Usuario user = new Usuario();
        user.setNombre(nombre.getText().toString());
        user.setNombre(apellido.getText().toString());
        user.setNombre(fecha_nac.getText().toString());
        user.setNombre(Dni.getText().toString());
        user.setNombre(Pass.getText().toString());
        user.setNombre(empresa.getText().toString());
        user.setNombre(telefono.getText().toString());
        user.setNombre(ciudad.getText().toString());
        user.setNombre(email.getText().toString());
        user.setNombre(direccion.getText().toString());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiService.insertUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "¡Vaya,parece que algo ha ido mal!", Toast.LENGTH_SHORT).show();
            }
        });



    }


    private boolean isValidForm() {
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.Apellido);
        fecha_nac = findViewById(R.id.Fecha_Nacimiento);
        Dni = findViewById(R.id.DNI);
        Pass = findViewById(R.id.Password);
        empresa = findViewById(R.id.Empresa);
        telefono = findViewById(R.id.Telefono);
        ciudad = findViewById(R.id.Ciudad);
        email = findViewById(R.id.Email);
        direccion = findViewById(R.id.Direccion);

        return isEditTextValid(nombre) && isEditTextValid(apellido) && isEditTextValid(Dni)
                && isEditTextValid(fecha_nac) && isEditTextValid(Pass) && isEditTextValid(empresa)
                && isEditTextValid(telefono) && isEditTextValid(ciudad) && isEditTextValid(email) && isEditTextValid(direccion);
    }

    private boolean isEditTextValid(EditText editText) {
        String input = editText.getText().toString();
        return input != null && !input.isEmpty() ;
    }

    private boolean containsInvalidCharacters(String input) {
        String regex = "^[a-zA-Z0-9 .-]*$";
        return !input.matches(regex);
    }
}