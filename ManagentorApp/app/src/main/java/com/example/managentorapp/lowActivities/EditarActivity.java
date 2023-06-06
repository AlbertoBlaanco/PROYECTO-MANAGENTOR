package com.example.managentorapp.lowActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.lstPropAlquiladas.view.LstAlquiladasActivity;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarActivity extends AppCompatActivity {


    private EditText address, city, state, zipcode, bedroom, bathroom, squareMeters,
            price, deposit, aditionalComments, ownerName, ownerPhone;

    private ToggleButton elevator;

    private Button saveProperty, lessBedroom, plusBedrooom, lessBathroom, plusBathroom,
            lessDeposit, plusDeposit;



    private int idInmueble;

    private Spinner spinner_furnished_edit;



    private final Propiedad property = new Propiedad();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        initComponents();
    }

    public void initComponents(){


        idInmueble = getIntent().getExtras().getInt("idInmueble");

        address = findViewById(R.id.address_edit);
        city = findViewById(R.id.city_edit);
        state = findViewById(R.id.state_edit);
        zipcode = findViewById(R.id.zipcode_edit);

        squareMeters = findViewById(R.id.square_meters_edit);
        price = findViewById(R.id.price_edit);
        aditionalComments = findViewById(R.id.aditional_comments_edit);
        elevator = findViewById(R.id.elevator_toggle_button);
        ownerName = findViewById(R.id.client_name_edit);
        ownerPhone = findViewById(R.id.client_phone_edit);
        saveProperty = findViewById(R.id.save_property);


        bedroom = findViewById(R.id.bedroom_edit);
        lessBedroom = findViewById(R.id.btn_less_bedroom);
        lessBedroom.setOnClickListener(v -> bedroom.setText(minusOneEditText(bedroom)));
        plusBedrooom = findViewById(R.id.btn_plus_bedroom);
        plusBedrooom.setOnClickListener(v -> bedroom.setText(addOneEditText(bedroom)));

        bathroom = findViewById(R.id.bathroom_edit);
        lessBathroom = findViewById(R.id.btn_less_bathroom);
        lessBathroom.setOnClickListener(v -> bathroom.setText(minusOneEditText(bathroom)));
        plusBathroom = findViewById(R.id.btn_plus_bathroom);
        plusBathroom.setOnClickListener(v -> bathroom.setText(addOneEditText(bathroom)));

        deposit = findViewById(R.id.deposit_edit);
        lessDeposit = findViewById(R.id.btn_less_deposit);
        lessDeposit.setOnClickListener(v -> deposit.setText(minusOneEditText(deposit)));
        plusDeposit = findViewById(R.id.btn_plus_deposit);
        plusDeposit.setOnClickListener(v -> deposit.setText(addOneEditText(deposit)));




        spinner_furnished_edit = (Spinner) findViewById(R.id.spinner_furnished_edit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.property_types, android.R.layout.simple_spinner_item);
        spinner_furnished_edit.setAdapter(adapter);


        asignarValores(idInmueble);
        spinner_furnished_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                property.setFurnished(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

        saveProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 property.setId_propiedad(idInmueble);
                 property.setDireccion(address.getText().toString());
                 property.setCiudad(city.getText().toString());
                 property.setNumHab(Integer.parseInt(bedroom.getText().toString()));
                 property.setNumBanio(Integer.parseInt(bathroom.getText().toString()));
                 property.setAscensor(elevator.isChecked());
                 property.setMetros(Integer.parseInt(squareMeters.getText().toString()));

                 property.setComentariosAdicionales(aditionalComments.getText().toString());
                 property.setPrecio(Integer.parseInt(price.getText().toString()));

                 property.setPropietario(ownerName.getText().toString());
                 property.setEstado(state.getText().toString());
                 property.setZipcode(Integer.parseInt(zipcode.getText().toString()));

                 Gson gson = new Gson();
                 String json = gson.toJson(property);

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<Void> call = apiService.editPropiedad(property);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(getBaseContext(), VerDetallesActivity.class);
                        intent.putExtra("idInmueble",idInmueble);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditarActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



            }
        });




    }



    public void asignarValores(int idInmueble){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Propiedad> call = apiService.getProp(idInmueble);
        call.enqueue(new Callback<Propiedad>() {
            @Override
            public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
                Propiedad property = response.body();

                address.setText(property.getDireccion());
                city.setText(property.getCiudad());
                state.setText(property.getEstado());
                zipcode.setText(String.valueOf(property.getZipcode()));
                //furnished.setText(property.getFurnished());
                bedroom.setText(String.valueOf(property.getNumHab()));
                bathroom.setText(String.valueOf(property.getNumBanio()));
                squareMeters.setText(String.valueOf(property.getMetros()));
                int price2 = (int) property.getPrecio();
                price.setText(String.valueOf(price2));
                elevator.setChecked(property.isAscensor());
                //deposit.setText(String.valueOf(property.getDeposit()));
                aditionalComments.setText(property.getComentariosAdicionales());
                ownerName.setText(property.getPropietario());
            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {

            }
        });
    }







    public  String addOneEditText(EditText text) {
        int number = 0;
        try {
            number = Integer.parseInt(text.getText().toString());
        } catch (NumberFormatException ignored) {
        }
        return String.valueOf(number + 1);
    }

    public  String minusOneEditText(EditText text) {
        int number = 0;
        try {
            number = Integer.parseInt(text.getText().toString());
        } catch (NumberFormatException ignored) {
        }
        return String.valueOf(number - 1);
    }

}