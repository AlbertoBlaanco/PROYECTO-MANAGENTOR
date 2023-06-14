package com.example.managentorapp.lstPropAlquiladas.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Imagenes;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstAlquiladasAdapter extends RecyclerView.Adapter<LstAlquiladasAdapter.ViewHolder> {
    private ArrayList<Alquilados> dataset;
    private Context mContext;
    private  ArrayList<Imagenes> photos;
    private int photoIndex;

    public LstAlquiladasAdapter(Context mContext, ArrayList<Alquilados> lstRestaurantes){
        this.dataset = lstRestaurantes;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_alquiladas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alquilados r = dataset.get(position);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Propiedad> call = apiService.getProp(r.getId_inmueble());
        call.enqueue(new Callback<Propiedad>() {
            @Override
            public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
                Propiedad prop = response.body();

                holder.direction.setText(prop.getDireccion());

                // Obtener imágenes de forma asincrónica
                getImagesAsync(apiService, r.getId_inmueble(), holder);

                // Obtener cliente de forma asincrónica
                getClientAsync(apiService, r.getId_cliente(), holder);
            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {
                // Manejo de error
                Log.e("API Call", "Error retrieving property: " + t.getMessage());
            }
        });

        holder.rentDate.setText(r.getFecha());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView direction, clientName, rentDate;
        private final ImageView photo;


        public ViewHolder(View itemView) {
            super(itemView);
            direction = itemView.findViewById(R.id.rent_address);
            clientName = itemView.findViewById(R.id.rent_client);
            rentDate = itemView.findViewById(R.id.rent_date);
            photo = itemView.findViewById(R.id.rented_property_photo);
        }
    }


    private void getImagesAsync(ApiInterface apiService, int propertyId, ViewHolder holder) {
        Call<ArrayList<Imagenes>> call = apiService.getImagenes(propertyId);
        call.enqueue(new Callback<ArrayList<Imagenes>>() {
            @Override
            public void onResponse(Call<ArrayList<Imagenes>> call, Response<ArrayList<Imagenes>> response) {
                ArrayList<Imagenes> photos = response.body();
                if (photos != null && !photos.isEmpty()) {
                    loadImageAsync(holder.photo, photos.get(0).getURL());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Imagenes>> call, Throwable t) {
                // Manejo de error
                Log.e("API Call", "Error retrieving images: " + t.getMessage());
            }
        });
    }

    private void getClientAsync(ApiInterface apiService, int clientId, ViewHolder holder) {
        Call<Cliente> call = apiService.getCliente(clientId);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cli = response.body();
                if (cli != null) {
                    holder.clientName.setText(cli.getNombreCli());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejo de error
                Log.e("API Call", "Error retrieving client: " + t.getMessage());
            }
        });
    }

    private void loadImageAsync(ImageView imageView, String imageUrl) {
        // Utilizar una biblioteca de carga de imágenes asincrónica como Picasso o Coil
        Picasso.get().load(imageUrl).into(imageView);
    }
}
