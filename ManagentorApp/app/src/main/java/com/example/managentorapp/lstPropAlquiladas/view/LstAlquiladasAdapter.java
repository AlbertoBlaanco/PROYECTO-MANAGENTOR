package com.example.managentorapp.lstPropAlquiladas.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Cliente;
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
                Picasso.get().load(prop.getImagen()).into(holder.photo);
            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {

            }
        });

        Call<Cliente> call2 = apiService.getCliente(r.getId_cliente());

        call2.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cli = response.body();
                holder.clientName.setText(cli.getNombreCli());
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });

        holder.rentDate.setText(r.getFecha());


        //holder.direction.setText(direction);
        //holder.clientName.setText(rentedClient.getName());



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
