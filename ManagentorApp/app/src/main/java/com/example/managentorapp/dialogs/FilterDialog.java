package com.example.managentorapp.dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;


import com.example.managentorapp.R;
import com.example.managentorapp.entitities.FilterModel;
import com.example.managentorapp.listadoProp.view.LstPropActivity;


public class FilterDialog extends DialogFragment
		implements AdapterView.OnItemSelectedListener {

	private EditText minPrice, maxPrice, minSquareMeters, maxSquareMeters,
			bedroomNumber, bathroomNumber;
	private RadioGroup radioGroup,radioGroupElevator;
	private Button applyFilter;
	private final FilterModel filterModel = new FilterModel();
	private Spinner spinner;


	int orderBy = 0;

	public interface DataSendListener {
		void onFilterChose(FilterModel filterModel);
	}

	DataSendListener filter;

	public void setFilter(DataSendListener dpdfe) {
		this.filter = dpdfe;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_filter, container,
									 false);
		this.minPrice = view.findViewById(R.id.filter_min_price);
		this.maxPrice = view.findViewById(R.id.filter_max_price);
		this.minSquareMeters = view.findViewById(R.id.filter_min_squaremeters);
		this.maxSquareMeters = view.findViewById(R.id.filter_max_squaremeters);
		this.bedroomNumber = view.findViewById(R.id.filter_bedroom);
		this.bathroomNumber = view.findViewById(R.id.filter_bathroom);
		this.radioGroup = view.findViewById(R.id.order_by);
		this.spinner = view.findViewById(R.id.spinner_property_type);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.property_types_filter, android.R.layout.simple_spinner_item);
		spinner.setAdapter(adapter);
		radioGroupElevator = view.findViewById(R.id.has_elevator);

		int id = radioGroupElevator.getCheckedRadioButtonId();
		if (id == R.id.yes) {
			this.filterModel.setAscensor(true);
		} else if (id == R.id.no) {
			this.filterModel.setAscensor(false);
		}

		ImageView rightIcon = view.findViewById(R.id.right_icon);
		rightIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), LstPropActivity.class);
				startActivity(intent);
			}
		});



		this.radioGroup.setOnCheckedChangeListener((rGroup, checkedId) -> {

			int radioBtnID = rGroup.getCheckedRadioButtonId();
			View radioB = rGroup.findViewById(radioBtnID);
			this.orderBy = radioGroup.indexOfChild(radioB);

		});
		this.applyFilter = view.findViewById(R.id.button_apply_filter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				filterModel.setTipo(adapterView.getItemAtPosition(i).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});




		this.applyFilter.setOnClickListener(v -> {

			Editable minPrice = this.minPrice.getText();
			Editable maxPrice = this.maxPrice.getText();
			Editable minSquareMeters = this.minSquareMeters.getText();
			Editable maxSquareMeters = this.maxSquareMeters.getText();
			Editable bedroom = this.bedroomNumber.getText();
			Editable bathroom = this.bathroomNumber.getText();

			if (minPrice != null && !minPrice.toString().isEmpty()) {
				filterModel.setMinPrice(Integer.parseInt(minPrice.toString()));
			}
			if (maxPrice != null && !maxPrice.toString().isEmpty()) {
				filterModel.setMaxPrice(Integer.parseInt(maxPrice.toString()));
			}
			if (minSquareMeters != null && !minSquareMeters.toString().isEmpty()) {
				filterModel.setMinSquareMeters(Integer.parseInt(minSquareMeters.toString()));
			}
			if (maxSquareMeters != null && !maxSquareMeters.toString().isEmpty()) {
				filterModel.setMaxSquareMeters(Integer.parseInt(maxSquareMeters.toString()));
			}
			if (bedroom != null && !bedroom.toString().isEmpty()) {
				filterModel.setBedroomNumber(Integer.parseInt(bedroom.toString()));
			}
			if (bathroom != null && !bathroom.toString().isEmpty()) {
				filterModel.setBathroomNumber(Integer.parseInt(bathroom.toString()));
			}

			switch (orderBy) {
				case 0:
					filterModel.setMenoscaros(true);
					filterModel.setMascaros(false);
					break;
				case 1:
					filterModel.setMascaros(true);
					filterModel.setMenoscaros(false);
					break;


			}

			filter.onFilterChose(filterModel);

			this.dismiss();

		});
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}
