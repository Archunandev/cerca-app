package com.example.splash;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;


public class MechanicActivity extends AppCompatActivity {



    EditText inputsearchm;
    RecyclerView mechanicrecycle;
    FirebaseRecyclerOptions<Mechanicuser> options;
    FirebaseRecyclerAdapter<Mechanicuser,Mechanicholder>adapter;
    DatabaseReference Databaseref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);

        Databaseref = FirebaseDatabase.getInstance().getReference().child("car");

        inputsearchm = findViewById(R.id.inputsearchm);
        mechanicrecycle = findViewById(R.id.mechanicrecycle);
        mechanicrecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mechanicrecycle.setHasFixedSize(true);

        LoadData();


    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<Mechanicuser>().setQuery(Databaseref,Mechanicuser.class).build();
        adapter = new FirebaseRecyclerAdapter<Mechanicuser, Mechanicholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Mechanicholder holder, int position, @NonNull Mechanicuser model) {

                holder.textViewm1.setText(model.getLocationm());
                holder.textViewm2.setText(model.getNamem());
                holder.textViewm3.setText(model.getPhonem());
                Picasso.get().load(model.getPhotom()).into(holder.imageViewm);


            }

            @NonNull
            @Override
            public Mechanicholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mechanicdata,parent,false);
                return new Mechanicholder(v);
            }
        };

        adapter.startListening();
        mechanicrecycle.setAdapter(adapter);


    }

}
