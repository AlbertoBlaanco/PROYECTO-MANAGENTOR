package com.example.managentorapp.addProperty.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.view.LstPropActivity;

import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;

import com.google.gson.Gson;


import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addPropertyActivity2 extends AppCompatActivity {


    private EditText ownerName,price, depositNumber, comments;
    private Spinner furnishedSpinner;
    private Button continueButton;
    private RadioGroup radioGroup;
    private ImageButton loadContact, createContactButton;
    private LinearLayout importContact, createContact;
    private Propiedad property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property2);
        initComponents();
    }

    public void initComponents(){




        radioGroup = findViewById(R.id.has_elevator);
        price = findViewById(R.id.price);
        comments = findViewById(R.id.comments);
        ownerName = findViewById(R.id.owner_name_edit);
        continueButton =findViewById(R.id.continue_step_two);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Property", property);
                Intent intent = new Intent(getBaseContext(), addPropertyActivity.class);
                intent.putExtra("Property", (Serializable) property);
                startActivity(intent);




            }
        });

    }

    public void initData(){

        if(isValidForm()){
            this.property = (Propiedad) getIntent().getSerializableExtra("Property");
            this.property.setPrecio(Integer.parseInt(price.getText().toString()));
            this.property.setPropietario(ownerName.getText().toString());
            this.property.setComentariosAdicionales(comments.getText().toString());
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

                String viewById = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

                if (viewById.equals(getBaseContext().getResources().getString(R.string.no))) {
                    this.property.setAscensor(false);
                }else{
                    this.property.setAscensor(true);
                }
            });


            Gson gson = new Gson();
            String json = gson.toJson(property);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Void> call = apiService.addPropiedad(property);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Intent intent = new Intent(getBaseContext(), LstPropActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(addPropertyActivity2.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(this, "Algunos campos son erroneos.", Toast.LENGTH_SHORT).show();
        }


    }
    private boolean isValidForm() {
        EditText price = findViewById(R.id.price);
        EditText comments = findViewById(R.id.comments);

        return isPriceValid(price) && isEditTextValid(comments);
    }

    private boolean isEditTextValid(EditText editText) {
        String input = editText.getText().toString();
        return input != null && !input.isEmpty() && !containsInvalidCharacters(input);
    }

    private boolean isPriceValid(EditText editText) {
        String input = editText.getText().toString();
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean containsInvalidCharacters(String input) {
        String regex = "^[a-zA-Z0-9 .-]*$";
        return !input.matches(regex);
    }

}