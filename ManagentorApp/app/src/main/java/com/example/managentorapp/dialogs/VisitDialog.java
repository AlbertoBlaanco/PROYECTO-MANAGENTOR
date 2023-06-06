package com.example.managentorapp.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.entitities.ClientPropertyInfo;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitDialog extends DialogFragment
		implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	private LocalTime selectedVisitTime;
	private LocalDate selectedVisitDate;
	private EditText visitDate, visitHour;
	private final ClientPropertyInfo clientPropertyInfo;


	public VisitDialog(ClientPropertyInfo clientPropertyInfo) {
		this.clientPropertyInfo = clientPropertyInfo;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_add_event, container,
									 false);

		visitDate = view.findViewById(R.id.visit_date);
		visitHour = view.findViewById(R.id.visit_hour);
		EditText reason = view.findViewById(R.id.reason);
		Button createVisit = view.findViewById(R.id.create_visit);

		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		visitDate.setHint(String.format("%s/%s/%s", day, month, year));
		visitDate.setOnClickListener(l -> {

			DatePickerDialog picker = new DatePickerDialog(getContext(), this,
														   year, month - 1, day);
			picker.show();
		});



		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		visitHour.setHint(String.format("%s:%s", hour, minutes));
		visitHour.setOnClickListener(v -> {

			TimePickerDialog picker = new TimePickerDialog(getContext(),
														   this, hour, minutes,
														   true);
			picker.show();

		});

		createVisit.setOnClickListener(v -> {
			Cita cita = new Cita();

			try {

				cita.setMotivo(reason.getText().toString());
				cita.setIdCliente(clientPropertyInfo.getClientId());
				cita.setidInmueble(clientPropertyInfo.getPropertyId());
				cita.setDia(selectedVisitDate.toString());
				cita.setHora(selectedVisitTime.toString());

				/*if (reason == null || reason.getText() == null || reason.getText().toString().isEmpty()) {
					throw new IllegalArgumentException("Es necesario indicar un motivo");
				}
				if (selectedVisitDate == null) {
					throw new IllegalArgumentException("Es necesario indicar la fecha de la cita");
				}
				if (selectedVisitTime == null) {
					throw new IllegalArgumentException("Es necesario indicar la hora de la cita");
				}*/

				ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
				Call<Propiedad> call = apiService.getProp(clientPropertyInfo.getPropertyId());
				call.enqueue(new Callback<Propiedad>() {
					@Override
					public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
						Propiedad p = response.body();
						cita.setLugar(p.getDireccion());

						ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
						Call<Void> call2 = apiService.addCita(cita);
						call2.enqueue(new Callback<Void>() {
							@Override
							public void onResponse(Call<Void> call, Response<Void> response) {
								Toast.makeText(getContext(), "Se ha guardado el evento correctamente",
										Toast.LENGTH_LONG).show();
								dismiss();
							}

							@Override
							public void onFailure(Call<Void> call, Throwable t) {

							}
						});

					}

					@Override
					public void onFailure(Call<Propiedad> call, Throwable t) {

					}
				});



			} catch (IllegalArgumentException e) {
				Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
		});

		return view;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		month = month + 1;
		visitDate.setText(String.format("%s/%s/%s", dayOfMonth, month, year));
		this.selectedVisitDate = LocalDate.of(year, month, dayOfMonth);
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

		visitHour.setText(String.format("%s:%s", hourOfDay, minute));
		this.selectedVisitTime = LocalTime.of(hourOfDay, minute);
	}

}
