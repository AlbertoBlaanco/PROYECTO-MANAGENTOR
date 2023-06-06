package com.example.managentorapp.listadoProp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.managentorapp.R;
import com.example.managentorapp.addProperty.view.addPropertyActivity;
import com.example.managentorapp.dialogs.FilterDialog;
import com.example.managentorapp.entitities.FilterModel;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.listadoProp.model.ListadoPropModel;
import com.example.managentorapp.listadoProp.presenter.LstPropPresenter;
import com.example.managentorapp.lstPropAlquiladas.view.LstAlquiladasActivity;
import com.example.managentorapp.lstcitas.view.ListaCitasActivity;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.viewProfile.View.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstPropActivity extends AppCompatActivity implements ListadoPropContract.View, FilterDialog.DataSendListener, NavigationView.OnNavigationItemSelectedListener {
    private LstPropPresenter lstPropPresenter;
    private RecyclerView recyclerView;

    private FloatingActionButton addBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_prop);
        initComponents();
        initPresenter();
        initData();
    }

    public void initComponents(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView leftIcon = findViewById(R.id.left_icon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                FilterDialog visitDialog = new FilterDialog();
                visitDialog.setFilter(LstPropActivity.this);
                visitDialog.show(fm, "showDialog");

            }
        });


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




        recyclerView = (RecyclerView) findViewById(R.id.photo_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        addBtn = findViewById(R.id.add_button);




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), addPropertyActivity.class);
                startActivity(intent);
            }
        });
   }
    public void initPresenter(){
        lstPropPresenter = new LstPropPresenter(this);
    }
    public void initData(){

        lstPropPresenter.getListadoProp("");
    }

    @Override
    public void onSuccessListadoProp(ArrayList<Propiedad> lstPropiedad) {
        LstPropAdapter adapter = new LstPropAdapter(getBaseContext(),lstPropiedad);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFilterChose(FilterModel filterModel) {

        Gson gson = new Gson();
        String json = gson.toJson(filterModel);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Propiedad>> call = apiService.getFilterProp(requestBody);
        call.enqueue(new Callback<ArrayList<Propiedad>>() {
            @Override
            public void onResponse(Call<ArrayList<Propiedad>> call, Response<ArrayList<Propiedad>> response) {
                ArrayList<Propiedad> lstFilterProp = response.body();
                LstPropAdapter adapter = new LstPropAdapter(getBaseContext(),lstFilterProp);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Propiedad>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"¡Vaya! parece que no se ha encontrado ningun inmueble con esas características" , Toast.LENGTH_SHORT).show();
            }
        });
    }





    @Override
    public void onFailureListadoProp(String err) {

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
                Intent intent = new Intent(this,ProfileActivity.class);
                //int idUser = getIntent().getExtras().getInt("idUser");
                //intent.putExtra("idUser",idUser);
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