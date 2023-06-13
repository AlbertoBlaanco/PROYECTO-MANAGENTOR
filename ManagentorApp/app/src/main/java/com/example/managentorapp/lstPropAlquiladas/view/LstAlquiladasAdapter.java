package com.example.managentorapp.lstPropAlquiladas.view;

import android.content.Context;
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


                Call<ArrayList<Imagenes>> call2 = apiService.getImagenes(r.getId_inmueble());
                call2.enqueue(new Callback<ArrayList<Imagenes>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Imagenes>> call, Response<ArrayList<Imagenes>> response) {
                        photos = response.body();
                        photoIndex = 0;

                        Glide.with(mContext)
                                .load(photos.get(photoIndex).getURL())
                                .into(holder.photo);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Imagenes>> call, Throwable t) {

                    }
                });

                Call<Cliente> call3 = apiService.getCliente(r.getId_cliente());
                call3.enqueue(new Callback<Cliente>() {
                    @Override
                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                        Cliente cli = response.body();
                        holder.clientName.setText(cli.getNombreCli());
                    }

                    @Override
                    public void onFailure(Call<Cliente> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {

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
}
