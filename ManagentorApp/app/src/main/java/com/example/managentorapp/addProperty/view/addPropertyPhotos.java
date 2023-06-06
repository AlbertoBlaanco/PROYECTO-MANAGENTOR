package com.example.managentorapp.addProperty.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.ListenableWorker;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.managentorapp.R;
import com.example.managentorapp.entitities.Imagenes;
import com.example.managentorapp.listadoProp.view.LstPropActivity;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.view.VerDetallesActivity;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addPropertyPhotos extends AppCompatActivity {
    private ImageAdapter photoAdapter;
    private Data mOutputData;
    private ArrayList<Imagenes> finalPhotos = new ArrayList<>();
    private final ArrayList<Imagenes> propertyPhotos = new ArrayList<>();
    private String currentPhotoPath;
    private Button save_photos;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 1001;

    private int idInmueble;

    private ArrayList<String> lstImagenesDecoded = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property_photos);
        initComponents();
        idInmueble = getIntent().getExtras().getInt("idInmueble");

    }

    public void initComponents() {

        ImageView rightIcon = findViewById(R.id.right_icon);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VerDetallesActivity.class);
                intent.putExtra("idInmueble",idInmueble);
                startActivity(intent);
            }
        });

        this.propertyPhotos.add(new Imagenes(false, BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.add_photo)));
        RecyclerView recyclerView = findViewById(R.id.properties_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
        photoAdapter = new ImageAdapter(getBaseContext(),lstImagenesDecoded, propertyPhotos, new PhotoListener() {
            @Override
            public void addPhoto() {
                openImageIntent();
            }

        });
        recyclerView.setAdapter(photoAdapter);
        photoAdapter.notifyDataSetChanged();
        save_photos = findViewById(R.id.save_property);

        save_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalPhotos = propertyPhotos;
                finalPhotos.remove(0);
                if (finalPhotos.isEmpty()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getBaseContext()).create();
                    alertDialog.setTitle("¡No has seleccionado ninguna foto!");
                    alertDialog.setMessage("¿Está seguro de que desea guardar la propiedad sin fotos?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                            ((dialog, which) -> {
                                dialog.dismiss();
                            }));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            (dialog, which) -> {
                                dialog.dismiss();

                            });

                    alertDialog.show();
                } else {

                    for (int i = 0; i < lstImagenesDecoded.size(); i++) {
                        uploadImage(lstImagenesDecoded.get(i));
                    }

                }
            }

        });
    }



    private void openImageIntent() {

        //Select from gallery
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        galleryIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);




        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
        chooser.putExtra(Intent.EXTRA_TITLE, "Seleccione una foto");


        chooser.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        chooser.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);



        startActivityForResult(chooser, GALLERY_REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1 || resultCode != RESULT_OK && data == null) {
            Toast.makeText(getBaseContext(), "No has seleccionado una foto...",
                    Toast.LENGTH_LONG).show();
            return;
        }
        int originalSize = this.propertyPhotos.size();


        if (data == null) {
            createPhotoFromCamera();
        } else {
            Uri selectedImage = data.getData();

            if (data.getClipData() == null) {
                createSinglePhoto(data, requestCode, resultCode);
            } else {
                selectMultiplePhotosFromGallery(data);
            }
        }

        photoAdapter.notifyItemRangeInserted(originalSize, this.propertyPhotos.size());
    }


    private void createSinglePhoto(Intent data, int requestCode, int resultCode) {
        try {
            Imagenes newPhoto = createPhoto(data.getData());
            this.propertyPhotos.add(newPhoto);

            if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();

                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                String base64Image = convertBitmapToBase64(bitmap);
                base64Image = base64Image.replaceAll("\\s+", "");
                lstImagenesDecoded.add(base64Image);
                //uploadImage(base64Image);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    private void createPhotoFromCamera() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        Uri uri = mediaScanIntent.getData();
        try {
            Imagenes newPhoto = createPhoto(uri);
            this.propertyPhotos.add(newPhoto);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

    }

    private void selectMultiplePhotosFromGallery(Intent data) {
        ClipData clipData = data.getClipData();

        for (int i = 0; i < clipData.getItemCount(); i++) {
            Uri uri = clipData.getItemAt(i).getUri();
            try {
                Imagenes newPhoto = createPhoto(uri);
                this.propertyPhotos.add(newPhoto);

            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    private Imagenes createPhoto(Uri uri) throws IOException {
        if (getBaseContext() == null) {
            throw new NullPointerException("El contexto es nulo...");
        }
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),
                uri);
        return new Imagenes(false, bitmap, uri.toString());
    }



    private void uploadImage(String base64Image) {
        base64Image = base64Image.replaceAll("\\s+", "");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiService.uploadImage(base64Image);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                    Intent intent = new Intent(getBaseContext(), LstPropActivity.class);
                    startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Intent intent = new Intent(getBaseContext(), LstPropActivity.class);
                startActivity(intent);
            }
        });
    }


}