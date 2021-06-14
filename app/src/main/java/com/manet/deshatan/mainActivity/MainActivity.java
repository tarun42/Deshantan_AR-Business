package com.manet.deshatan.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manet.deshatan.BackgroundMusicService;
import com.manet.deshatan.R;
import com.manet.deshatan.dataModels.temp;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference gameRef;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference("temp");
        intent = new Intent(MainActivity.this, BackgroundMusicService.class);
        startService(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(intent);
    }

    public void PlayBackgroundSound(View view) {

    }
}