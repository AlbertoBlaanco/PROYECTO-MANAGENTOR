package com.example.managentorapp.lstcitas.view;

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

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaCitasAdapter extends RecyclerView.Adapter<ListaCitasAdapter.ViewHolder> {
    private ArrayList<Cita> dataset;
    private Context mContext;

    public ListaCitasAdapter(Context mContext, ArrayList<Cita> lstCitas){
        this.dataset = lstCitas;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar_event,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cita r = dataset.get(position);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");


        Date fecha = null;
        try {
            fecha = formatoFecha.parse(r.getDia());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);





        holder.eventTitle.setText(r.getMotivo());
        holder.eventAddress.setText(r.getLugar());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Cliente> call = apiService.getCliente(r.getIdCliente());
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cli = response.body();
                holder.client.setText(cli.getNombreCli());
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });

        holder.day.setText(String.valueOf(dia));
        holder.hour.setText(r.getHora());




    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventTitle, eventAddress, client, hour, day;



        public ViewHolder(View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventAddress = itemView.findViewById(R.id.event_address);
            hour = itemView.findViewById(R.id.event_hour);
            day = itemView.findViewById(R.id.event_date);
            client = itemView.findViewById(R.id.event_people);
        }
    }
}
