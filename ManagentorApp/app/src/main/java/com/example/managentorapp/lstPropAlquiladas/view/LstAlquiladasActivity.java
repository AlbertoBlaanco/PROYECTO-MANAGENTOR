package com.example.managentorapp.lstPropAlquiladas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.managentorapp.R;
import com.example.managentorapp.dialogs.FilterDialog;
import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.lstPropAlquiladas.lstAlquiladasContract;
import com.example.managentorapp.lstPropAlquiladas.presenter.LstAlquiladasPresenter;
import com.example.managentorapp.lstcitas.view.ListaCitasActivity;
import com.example.managentorapp.viewProfile.View.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LstAlquiladasActivity extends AppCompatActivity implements lstAlquiladasContract.View , NavigationView.OnNavigationItemSelectedListener{

    private LstAlquiladasPresenter lstAlquiladasPresenter;
    private RecyclerView recyAlquiladas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_alquiladas);
        initComponents();
        initPresenter();
        initData();

    }

    public void initComponents(){
        recyAlquiladas = findViewById(R.id.rent_recycler);
        recyAlquiladas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

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
        lstAlquiladasPresenter = new LstAlquiladasPresenter(this);
    }

    public void initData(){
        lstAlquiladasPresenter.getlstPropAlquiladas("");
    }

    @Override
    public void onSuccesslstPropAlquiladas(ArrayList<Alquilados> lstPropiedad) {
        LstAlquiladasAdapter lstAlquiladasAdapter = new LstAlquiladasAdapter(getBaseContext(),lstPropiedad);
        recyAlquiladas.setAdapter(lstAlquiladasAdapter);
    }

    @Override
    public void onFailurelstPropAlquiladas(String err) {

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

            case R.id.nav_user_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                int idUser = getIntent().getExtras().getInt("idUser");
                intent.putExtra("idUser",idUser);
                startActivity(intent);
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