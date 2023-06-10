package com.example.managentorapp.dialogs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleClientAdapter extends RecyclerView.Adapter<SimpleClientAdapter.ViewHolder> {

	private final LayoutInflater inflater;
	private List<Cliente> visitors = new ArrayList<>();
	private final Context context;
	private final FragmentManager fm;
	private final RentDialog.ClientChosenListener clientChosen;
	private int idInmueble;

	public SimpleClientAdapter(Context context, List<Cliente> visitors,
                               RentDialog.ClientChosenListener clientChosenListener, int idInmueble) {
		this.inflater = LayoutInflater.from(context);
		this.visitors = visitors;
		this.context = context;
		fm = ((FragmentActivity) context).getSupportFragmentManager();
		this.idInmueble = idInmueble;
		this.clientChosen = clientChosenListener;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private final TextView visitorName, visitorPhone;
		private final CardView visitorCard;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			visitorName = itemView.findViewById(R.id.client_name_simple);
			visitorPhone = itemView.findViewById(R.id.client_phone_simple);
			visitorCard = itemView.findViewById(R.id.client_to_rent);
		}
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.adapter_client_simple, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

		Cliente client = visitors.get(position);
		holder.visitorName.setText(client.getNombreCli());
		holder.visitorPhone.setText(String.valueOf(client.getTelefonoCli()));

		holder.visitorCard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
						.setTitleText("¿Seguro que " + client.getNombreCli() + " va a alquilar el piso? ")
						.setContentText("¿Lo juras?")
						.setConfirmText("Alquilar")
						.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(SweetAlertDialog sDialog) {
								sDialog.dismissWithAnimation();
								Alquilados alquilados = new Alquilados();
								alquilados.setId_cliente(client.getIdCliente());
								alquilados.setId_inmueble(idInmueble);
								Calendar cal = Calendar.getInstance();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String formattedDate = sdf.format(cal.getTime());
								alquilados.setFecha(formattedDate);
								Propiedad prop = new Propiedad();
								prop.setEstado("Alquilado");
								prop.setId_propiedad(idInmueble);



								ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
								Call<Void> call = apiService.addAlquilados(alquilados);
								call.enqueue(new Callback<Void>() {
									@Override
									public void onResponse(Call<Void> call, Response<Void> response) {
										Call<Void> call2 = apiService.updateEstado(prop);
										call2.enqueue(new Callback<Void>() {
											@Override
											public void onResponse(Call<Void> call, Response<Void> response) {
												Intent intent = new Intent(context, VerDetallesActivity.class);
												intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
												intent.putExtra("idInmueble",idInmueble);
												context.startActivity(intent);
											}

											@Override
											public void onFailure(Call<Void> call, Throwable t) {

											}
										});
									}

									@Override
									public void onFailure(Call<Void> call, Throwable t) {

									}
								});
							}
						})
						.setCancelButton("Cancelar", new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(SweetAlertDialog sDialog) {
								sDialog.dismissWithAnimation();
							}
						}).show();





			}
		});

		/*holder.visitorCard.setOnClickListener(v -> {
			clientChosen.onClientChosen(position);
		});*/
	}

	@Override
	public int getItemCount() {
		return this.visitors.size();
	}
}
