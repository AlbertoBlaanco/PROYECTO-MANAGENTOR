package com.example.managentorapp.verDetalles.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managentorapp.R;
import com.example.managentorapp.dialogs.VisitDialog;
import com.example.managentorapp.entitities.ClientPropertyInfo;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Propiedad;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClientesInteresadosAdapter extends RecyclerView.Adapter<ClientesInteresadosAdapter.ViewHolder> {
    private ArrayList<Cliente> dataset;
    private Context  mContext;
    private FragmentManager fm;


    public ClientesInteresadosAdapter(Context mContext, ArrayList<Cliente> lstClientes, FragmentManager fm){

        this.dataset = lstClientes;
        this.mContext = mContext;
        this.fm = fm;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_clientesint,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente r = dataset.get(position);
        holder.visitorName.setText(r.getNombreCli());
        holder.visitorPhone.setText(String.valueOf(r.getTelefonoCli()));
        holder.addVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VisitDialog visitDialog = new VisitDialog(new ClientPropertyInfo(r.getIdCliente(), r.getIdInmueble(), r.getNombreCli(), r.getDireccionCli()));
                visitDialog.show(fm, "VisitDialog");

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView visitorName, visitorPhone;
        private final CardView visitorCard;
        private final ImageButton addVisitButton;



        public ViewHolder(View itemView) {
            super(itemView);
            visitorName = itemView.findViewById(R.id.visitor_name);
            visitorPhone = itemView.findViewById(R.id.visitor_phone);
            visitorCard = itemView.findViewById(R.id.visitor_card);
            addVisitButton = itemView.findViewById(R.id.add_visit);
        }
    }
}
