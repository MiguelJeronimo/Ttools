package com.example.TibiaTools.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityAboutBinding;

public class About extends AppCompatActivity {

    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        String urlApp = "https://play-lh.googleusercontent.com/118SsYd1X2DyQRhNPYRRNjl96V4j1x_c-iGvOljJjO6nRhbtnTDrzW73SxQMVU5f-A=w240-h480-rw";
        String urlCoffe = "https://es.ewebinar.com/hubfs/ko-fi%20logo.png";
        ImageButton coffe = binding.getRoot().findViewById(R.id.imageButtonCoffe);
        ImageButton app = binding.getRoot().findViewById(R.id.imageButtonApp);
        Glide.with(getApplicationContext())
                        .load(urlCoffe)
                        .centerCrop()
                        .into(coffe);
        Glide.with(getApplicationContext())
                .load(urlApp)
                .centerCrop()
                .into(app);
        app.setOnClickListener(v -> {
            Intent application = new Intent(Intent.ACTION_VIEW);
            application.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.miguel.shoppinglistpro"));
            startActivity(application);
        });
        coffe.setOnClickListener(v->{
            Intent coffeDonation = new Intent(Intent.ACTION_VIEW);
            coffeDonation.setData(Uri.parse("https://ko-fi.com/selursan"));
            startActivity(coffeDonation);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}