package com.example.managentorapp.addProperty.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Imagenes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

	private final LayoutInflater inflater;
	private ArrayList<Imagenes> propertyPhotos = new ArrayList<>();
	private ArrayList<String> lstImgCodificadas = new ArrayList<>();
	private final PhotoListener photoListener;
	private final Context context;

	public ImageAdapter(Context context, ArrayList<String> lstImgCodificadas,ArrayList<Imagenes> propertyPhotos, PhotoListener photoListener) {
		this.inflater = LayoutInflater.from(context);
		this.propertyPhotos = propertyPhotos;
		this.photoListener = photoListener;
		this.lstImgCodificadas = lstImgCodificadas;
		this.context = context;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private final ImageButton mainPhoto;
		private final ImageButton delete;
		private final ImageButton propertyPhoto;
		private final ConstraintLayout adapterLayout;
		private final LinearLayout photoLayout;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			mainPhoto = itemView.findViewById(R.id.check_button);
			delete = itemView.findViewById(R.id.remove_photo);
			propertyPhoto = itemView.findViewById(R.id.property_photo);
			adapterLayout = itemView.findViewById(R.id.adapter_constraint_layout);
			photoLayout = itemView.findViewById(R.id.photo_layout);
		}
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.adapter_property_photo, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		if (position == 0) {
			holder.propertyPhoto.setOnClickListener(v -> photoListener.addPhoto());
			holder.delete.setVisibility(View.INVISIBLE);
			holder.mainPhoto.setVisibility(View.INVISIBLE);
			holder.adapterLayout.setVisibility(View.INVISIBLE);
		} else {
			Bitmap image = propertyPhotos.get(position).getPhoto();
			if (image != null) {
				holder.propertyPhoto.setImageBitmap(image);
			} else {
				Picasso.get().load(String.valueOf(propertyPhotos.get(position))).into(holder.propertyPhoto);
			}
			holder.mainPhoto.setBackgroundResource(R.drawable.grey_check);
			holder.mainPhoto.setOnClickListener(v -> updateMainPhoto(holder, position));
			holder.delete.setOnClickListener(v -> deletePhoto(position));
		}

	}

	private void deletePhoto(int position) {
		this.notifyItemRemoved(position);
		this.propertyPhotos.remove(position);
		this.lstImgCodificadas.remove(position - 1);
		notifyItemRangeChanged(position, this.propertyPhotos.size());

	}

	private void updateMainPhoto(@NonNull ViewHolder holder, int position) {

		Imagenes photo = propertyPhotos.get(position);
		if (photo.isMain()) {
			photo.setMain(false);
			holder.mainPhoto.setBackgroundResource(R.drawable.grey_check);
			holder.photoLayout.setBackgroundResource(0);
		} else {
			photo.setMain(true);
			holder.mainPhoto.setBackgroundResource(R.drawable.green_check);
			holder.photoLayout.setBackgroundResource(R.drawable.layout_border_color);
		}
		for (int i = 0; i < propertyPhotos.size(); i++) {
			if (i != position) {
				propertyPhotos.get(i).setMain(false);
			}
		}
		propertyPhotos.set(position, photo);
	}

	@Override
	public int getItemCount() {
		return this.propertyPhotos.size();
	}

}
