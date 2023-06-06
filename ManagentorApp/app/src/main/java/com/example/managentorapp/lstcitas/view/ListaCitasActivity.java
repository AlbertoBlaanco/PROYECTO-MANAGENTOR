package com.example.managentorapp.lstcitas.view;

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
import com.example.managentorapp.addProperty.view.addPropertyActivity;
import com.example.managentorapp.dialogs.FilterDialog;
import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.listadoProp.presenter.LstPropPresenter;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.listadoProp.view.LstPropAdapter;
import com.example.managentorapp.lstPropAlquiladas.view.LstAlquiladasActivity;
import com.example.managentorapp.lstcitas.ListadoCitasContract;
import com.example.managentorapp.lstcitas.presenter.LstCitasPresenter;
import com.example.managentorapp.viewProfile.View.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class ListaCitasActivity extends AppCompatActivity implements ListadoCitasContract.View, NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private LstCitasPresenter lstCitasPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_citas);
        initComponents();
        initPresenter();
        initData();
    }

    public void initComponents(){
        recyclerView = (RecyclerView) findViewById(R.id.citas_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

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
        lstCitasPresenter = new LstCitasPresenter(this);
    }
    public void initData(){

        lstCitasPresenter.getListadoCitas("");
    }

    @Override
    public void onSuccessListadoCitas(ArrayList<Cita> lstCitas) {
        ListaCitasAdapter adapter = new ListaCitasAdapter(getBaseContext(),lstCitas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailureListadoCitas(String err) {

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
                // int idUser = getIntent().getExtras().getInt("idUser");
                // intent.putExtra("idUser",idUser);
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