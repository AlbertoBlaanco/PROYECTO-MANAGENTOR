package com.example.managentorapp.listadoProp.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Imagenes;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstPropAdapter extends RecyclerView.Adapter<LstPropAdapter.ViewHolder> {
    private ArrayList<Propiedad> dataset;
    private Context mContext;
    private  ArrayList<Imagenes> photos;

    private int photoIndex;

    public LstPropAdapter(Context mContext, ArrayList<Propiedad> lstRestaurantes){
        this.dataset = lstRestaurantes;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lstprop,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Propiedad r = dataset.get(position);

        holder.direccion.setText(r.getDireccion());
        holder.precio.setText(String.valueOf(r.getPrecio()));
        holder.hab.setText(String.valueOf(r.getNumHab()));
        holder.banio.setText(String.valueOf(r.getNumBanio()));
        holder.ascensor.setText(r.isAscensor() ? "Si" : "No");
        holder.metros.setText(String.valueOf(r.getMetros()));
        holder.detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, VerDetallesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idInmueble",r.getId_propiedad());
                mContext.startActivity(intent);

            }
        });



        ApiInterface apiService2 = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Imagenes>> call2 = apiService2.getImagenes(r.getId_propiedad());
        call2.enqueue(new Callback<ArrayList<Imagenes>>() {
            @Override
            public void onResponse(Call<ArrayList<Imagenes>> call, Response<ArrayList<Imagenes>> response) {
                photos = response.body();
                photoIndex = 0;
                //Picasso.get().load(String.valueOf(photos.get(photoIndex).getURL())).into(defaultPhoto);
                Glide.with(mContext)
                        .load(photos.get(photoIndex).getURL())
                        .into(holder.photo);
            }

            @Override
            public void onFailure(Call<ArrayList<Imagenes>> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView direccion,precio,hab,banio,ascensor,metros;
        private ImageView photo;

        private Button detalles;



        public ViewHolder(View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.property_address);
            precio = itemView.findViewById(R.id.price);
            hab = itemView.findViewById(R.id.bedroom_number);
            banio = itemView.findViewById(R.id.bathroom_number);
            photo = itemView.findViewById(R.id.item_photo);
            ascensor = itemView.findViewById(R.id.elevator_yes_no);
            detalles = itemView.findViewById(R.id.property_details);
            metros = itemView.findViewById(R.id.square_meters_home);
        }
    }
}
