package com.example.managentorapp.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.verDetalles.view.ClientesInteresadosAdapter;

import java.util.ArrayList;
import java.util.List;

public class RentDialog extends DialogFragment {

	private RecyclerView clientRecycler;
	private ClientesInteresadosAdapter clientAdapter;
	private List<Cliente> clients = new ArrayList<>();
	private int idInmueble;

	public interface ClientChosenListener {
		void onClientChosen(int clientPosition);
	}

	public void setClientChosen(ClientChosenListener dpdfe) {
		this.clientChosen = dpdfe;
	}

	ClientChosenListener clientChosen;

	public RentDialog(List<Cliente> clients, int idInmueble) {
		this.clients = clients;
		this.idInmueble = idInmueble;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_rent_property, container,
									 false);

		clientRecycler = view.findViewById(R.id.client_rent_recycler);
		clientRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
		SimpleClientAdapter clientAdapter = new SimpleClientAdapter(getContext(), this.clients, clientChosen, idInmueble);
		clientRecycler.setAdapter(clientAdapter);

		return view;
	}
}
