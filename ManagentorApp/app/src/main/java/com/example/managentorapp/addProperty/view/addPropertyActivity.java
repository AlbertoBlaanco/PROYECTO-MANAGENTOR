package com.example.managentorapp.addProperty.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.view.LstPropActivity;

import java.io.Serializable;

public class addPropertyActivity extends AppCompatActivity {

  private EditText address1,address2,city,zipcode,bedroom,bathroom,squareMeters;
  private Spinner spinner,spinner_property_state;
  private Button lessBedroom,lessBathroom,plusBedroom,plusBathroom,continueBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);
        initComponents();

    }

    public void initComponents(){


        /* EDIT TEXT*/
        address1 = findViewById(R.id.address_first_line);
        address2 = findViewById(R.id.address_second_line);
        city = findViewById(R.id.city);
        zipcode = findViewById(R.id.zipcode);
        squareMeters = findViewById(R.id.m2);
        bedroom = findViewById(R.id.bedroom);
        bathroom = findViewById(R.id.bathroom);



        spinner = (Spinner) findViewById(R.id.spinner_property_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.property_types, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner_property_state = (Spinner) findViewById(R.id.spinner_property_state);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.property_types_state, android.R.layout.simple_spinner_item);
        spinner_property_state.setAdapter(adapter2);


        /*BUTTONS*/
        lessBedroom = findViewById(R.id.btn_less_bedroom);
        lessBathroom = findViewById(R.id.btn_less_bathroom);
        plusBedroom = findViewById(R.id.btn_plus_bedroom);
        plusBathroom = findViewById(R.id.btn_plus_bathroom);
        continueBtn = findViewById(R.id.continue_step_one);



        lessBedroom.setOnClickListener(v -> {
            String bedroomNumber = minusOneEditText(bedroom);
            bedroom.setText(bedroomNumber);
        });
        plusBedroom.setOnClickListener(v -> {
            String bedroomNumber = addOneEditText(bedroom);
            bedroom.setText(bedroomNumber);

        });
        lessBathroom.setOnClickListener(v -> {
            String bathroomNumber = minusOneEditText(bathroom);
            bathroom.setText(bathroomNumber);
        });
        plusBathroom.setOnClickListener(v -> {
            String bathroomNumber = addOneEditText(bathroom);

            bathroom.setText(bathroomNumber);
        });
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LstPropActivity.class);
                startActivity(intent);
            }
        });

    }


    public void initData(){

       if(isValidForm()){
           Propiedad property = new Propiedad();

            String address = getAddress(address1, address2);
            int bedroomNumber = Integer.parseInt(bedroom.getText().toString());
            int bathroomNumber = Integer.parseInt(bathroom.getText().toString());
            int m2 = Integer.parseInt(squareMeters.getText().toString());

            property.setNumHab(bedroomNumber);
            property.setNumBanio(bathroomNumber);
            property.setMetros(m2);
            property.setDireccion(address);
            property.setCiudad(city.getText().toString());
            property.setZipcode(Integer.parseInt(zipcode.getText().toString()));

           property.setEstado(spinner_property_state.getSelectedItem().toString());
           property.setFurnished(spinner.getSelectedItem().toString());

            Bundle bundle = new Bundle();
            bundle.putSerializable("Property", property);
            Intent intent = new Intent(getBaseContext(),addPropertyActivity2.class);
            intent.putExtra("Property", (Serializable) property);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Algunos de los campos son incorrectos.", Toast.LENGTH_SHORT).show();
        }


    }


    public static String minusOneEditText(EditText text) {
        int number = 0;
        try {
            number = Integer.parseInt(text.getText().toString());
        } catch (NumberFormatException ignored) {
        }
        return String.valueOf(number - 1);
    }

    public static String addOneEditText(EditText text) {
        int number = 0;
        try {
            number = Integer.parseInt(text.getText().toString());
        } catch (NumberFormatException ignored) {
        }
        return String.valueOf(number + 1);
    }

    private String getAddress(EditText address1, EditText address2) {
        if (address1 == null || address1.getText().toString().isEmpty()) {
//            throw new IllegalArgumentException("La dirección es un campo obligatorio");
        }
        String addressFormatted = address1.getText().toString();
        if (address2 != null && !address2.getText().toString().isEmpty()) {
            addressFormatted = String.format("%s, %s", address1.getText().toString(),
                    address2.getText().toString());
        }
        return addressFormatted;
    }

    private boolean isValidForm() {
        EditText address1 = findViewById(R.id.address_first_line);
        EditText address2 = findViewById(R.id.address_second_line);
        EditText city = findViewById(R.id.city);
        EditText zipcode = findViewById(R.id.zipcode);
        EditText squareMeters = findViewById(R.id.m2);
        EditText bedroom = findViewById(R.id.bedroom);
        EditText bathroom = findViewById(R.id.bathroom);

        return isEditTextValid(address1) && isEditTextValid(address2) && isEditTextValid(city)
                 && isEditTextValid(zipcode) && isEditTextValid(squareMeters)
                && isEditTextValid(bedroom) && isEditTextValid(bathroom);
    }

    private boolean isEditTextValid(EditText editText) {
        String input = editText.getText().toString();
        return input != null && !input.isEmpty() && !containsInvalidCharacters(input);
    }

    private boolean containsInvalidCharacters(String input) {
        String regex = "^[a-zA-Z0-9 .-]*$";
        return !input.matches(regex);
    }

}