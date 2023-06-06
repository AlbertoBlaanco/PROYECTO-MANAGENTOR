package com.example.managentorapp.viewProfile.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Usuario;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.lstPropAlquiladas.view.LstAlquiladasActivity;
import com.example.managentorapp.lstcitas.view.ListaCitasActivity;
import com.example.managentorapp.viewProfile.Presenter.ViewProfilePresenter;
import com.example.managentorapp.viewProfile.ViewProfileContract;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements ViewProfileContract.View, NavigationView.OnNavigationItemSelectedListener {

    private TextView user_name_data,apellido,fecha_nac,Dni,Pass,empresa,telefono,ciudad,email,direccion;
    private ViewProfilePresenter viewProfilePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
        initPresenter();
        initData(getIntent().getExtras().getInt("idUser"));
    }


    public void initComponents(){
        user_name_data = findViewById(R.id.user_name_data);
        apellido = findViewById(R.id.apellido);
        fecha_nac = findViewById(R.id.fecha_nac);
        Dni = findViewById(R.id.Dni);
        Pass = findViewById(R.id.Pass);
        empresa = findViewById(R.id.empresa);
        telefono = findViewById(R.id.telefono);
        ciudad = findViewById(R.id.ciudad);
        email = findViewById(R.id.email);
        direccion = findViewById(R.id.direccion);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView leftIcon = findViewById(R.id.left_icon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    public void initPresenter(){
                viewProfilePresenter = new ViewProfilePresenter(this);
    }

    public void initData(int idUser){
        viewProfilePresenter.getProfile(idUser);
    }


    @Override
    public void onSuccessProfile(Usuario user) {
        user_name_data.setText(user.getNombre());
        apellido.setText(user.getApellido());
        fecha_nac.setText(user.getFecha_Nac());
        Dni.setText(user.getDNI());
        Pass.setText(user.getPassword());
        empresa.setText(user.getEmpresa());
        telefono.setText(user.getTelefono());
        ciudad.setText(user.getCiudad());
        email.setText(user.getEmail());
        direccion.setText(user.getDireccion());
    }

    @Override
    public void onFailureProfile(String err) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(this, LstPropActivity.class));
                break;
            case R.id.nav_calendar:
                startActivity(new Intent(this, ListaCitasActivity.class));
                break;
            case R.id.nav_rent_history:
                startActivity(new Intent(this, LstAlquiladasActivity.class));


                break;
            case R.id.nav_logout:
                finish();
                System.exit(0);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}