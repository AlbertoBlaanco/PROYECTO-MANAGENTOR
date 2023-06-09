package com.example.managentorapp.verDetalles.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.managentorapp.R;
import com.example.managentorapp.addProperty.view.addPropertyPhotos;
import com.example.managentorapp.dialogs.RentDialog;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Imagenes;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.lowActivities.AddInterestedClient;
import com.example.managentorapp.lowActivities.EditarActivity;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.presenter.DetallesPresenter;
import com.example.managentorapp.verDetalles.verDetallesContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerDetallesActivity extends AppCompatActivity implements  RentDialog.ClientChosenListener,verDetallesContract.View{
    private TextView address, city, state, zipcode, furnished, bedroom, bathroom, squareMeters,
            price, deposit, elevator, aditionalCommentsTxt, aditionalComments, owner;

    private ImageView defaultPhoto;

    private RecyclerView recyclerViewInterested;
    private ArrayList<Cliente> clientesInteresados;

    private Button button_edit_property, rent_property,button_edit_photos;
    private ImageButton leftPhoto, rightPhoto;

    private FloatingActionButton  add_interested_button;
    private FragmentManager fm;
    private int idInmueble;
    private RentDialog rentDialog;

    private  ArrayList<Imagenes> photos;

    private int photoIndex;


    private DetallesPresenter verDetallesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalles);
        idInmueble = getIntent().getExtras().getInt("idInmueble");
        initComponents();
        initPresenter();
        initData(idInmueble);
    }

    public void initComponents(){
        address = findViewById(R.id.address_view);
        city = findViewById(R.id.city_view);
        state = findViewById(R.id.state_view);
        zipcode = findViewById(R.id.zipcode_view);
        furnished = findViewById(R.id.furnished_view);
        bedroom = findViewById(R.id.bedroom_view);
        bathroom = findViewById(R.id.bathroom_view);
        squareMeters =findViewById(R.id.square_meters_view);
        price = findViewById(R.id.price_view);
        //deposit = findViewById(R.id.deposit_view);
        elevator = findViewById(R.id.elevator_view);
        //aditionalCommentsTxt = findViewById(R.id.aditional_comments_txt);
        aditionalComments = findViewById(R.id.aditional_comments_view);
        owner = findViewById(R.id.owner_view);
        defaultPhoto = findViewById(R.id.property_photo_view);
        rent_property = findViewById(R.id.rent_property);
        button_edit_property = findViewById(R.id.button_edit_property);
        add_interested_button = findViewById(R.id.add_interested_button);
        button_edit_photos = findViewById(R.id.button_edit_photos);
        leftPhoto = findViewById(R.id.left_photo);
        leftPhoto.setOnClickListener(v -> changePhoto(false));
        rightPhoto = findViewById(R.id.right_photo);
        rightPhoto.setOnClickListener(v -> changePhoto(true));
        fm = getSupportFragmentManager();

        recyclerViewInterested = findViewById(R.id.interested_people_reyclerview);
        recyclerViewInterested.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LstPropActivity.class);

                startActivity(intent);
            }
        });

        button_edit_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), addPropertyPhotos.class);
                intent.putExtra("idInmueble",idInmueble);
                startActivity(intent);
            }
        });

    }
    public void initPresenter(){
        verDetallesPresenter = new DetallesPresenter(this);
    }
    public void initData(int idInmueble){
        verDetallesPresenter.getDetalles(idInmueble);
    }

    @Override
    public void onSuccessverDetalles(Propiedad property) {
        address.setText(property.getDireccion());
        city.setText(property.getCiudad());
        state.setText(property.getEstado());
        zipcode.setText(String.valueOf(property.getZipcode()));
        furnished.setText(property.getFurnished());
        bedroom.setText(String.valueOf(property.getNumHab()));
        bathroom.setText(String.valueOf(property.getNumBanio()));
        squareMeters.setText(String.valueOf(property.getMetros()));
        int price = (int) property.getPrecio();
        this.price.setText(String.valueOf(price));
        elevator.setText(property.isAscensor() ? "Si" : "No");
        if (property.getComentariosAdicionales() == null || property.getComentariosAdicionales().isEmpty()) {
            aditionalCommentsTxt.setVisibility(View.GONE);
            aditionalComments.setVisibility(View.GONE);
        }
        aditionalComments.setText(property.getComentariosAdicionales());
        owner.setText(property.getPropietario());
        /*if(property.getImagen() != null && !property.getImagen().isEmpty()){
            Picasso.get().load(property.getImagen()).into(defaultPhoto);
        }*/
        ApiInterface apiService2 = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Imagenes>> call2 = apiService2.getImagenes(property.getId_propiedad());
        call2.enqueue(new Callback<ArrayList<Imagenes>>() {
            @Override
            public void onResponse(Call<ArrayList<Imagenes>> call, Response<ArrayList<Imagenes>> response) {
                photos = response.body();
                photoIndex = 0;
                //Picasso.get().load(String.valueOf(photos.get(photoIndex).getURL())).into(defaultPhoto);
                Glide.with(getBaseContext())
                        .load(photos.get(photoIndex).getURL())
                        .into(defaultPhoto);
            }

            @Override
            public void onFailure(Call<ArrayList<Imagenes>> call, Throwable t) {

            }
        });







        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Cliente>> call = apiService.getCliInt(property.getId_propiedad());
        call.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                clientesInteresados = response.body();
                ClientesInteresadosAdapter clientAdapter = new ClientesInteresadosAdapter(getBaseContext(), clientesInteresados,fm);
                recyclerViewInterested.setAdapter(clientAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {
                //Toast.makeText(getBaseContext(),"No ha funcionado",Toast.LENGTH_LONG).show();
            }
        });



        button_edit_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idInmueble",idInmueble);
                startActivity(intent);
            }
        });




        add_interested_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddInterestedClient.class);
                intent.putExtra("idInmueble",idInmueble);
                startActivity(intent);
            }
        });

        if(!property.getEstado().equals("Alquilado")){
            rent_property.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clientesInteresados != null ){
                        rentDialog = new RentDialog(clientesInteresados, idInmueble);
                        //rentDialog.setClientChosen(this);
                        rentDialog.show(fm, "showDialog");

                    }else{
                        Toast.makeText(getBaseContext(), "No hay clientes para alquilar la propiedad...", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            rent_property.setVisibility(View.INVISIBLE);
        }





    }

    private void changePhoto(boolean isRight) {
        if (isRight) {
            this.photoIndex += 1;
            if (photoIndex > this.photos.size()) {
                this.photoIndex = 0;
            }
        } else {
            this.photoIndex -= 1;
            if (photoIndex < 0) {
                this.photoIndex = 0;
            }
        }
        try {
            Picasso.get().load(String.valueOf(photos.get(photoIndex).getURL())).into(defaultPhoto);
        } catch (IndexOutOfBoundsException ignored) {
        }

    }


    @Override
    public void onFailureverDetalles(String err) {

    }


    @Override
    public void onClientChosen(int clientPosition) {

    }
}