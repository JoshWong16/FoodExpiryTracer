package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class activity3_myitems extends AppCompatActivity {
    private ImageButton back2;
    private ImageButton home2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_myitems);
        back2 = (ImageButton) findViewById (R.id.imageButton12); //button 12 for back, button 13 for home
        home2 = (ImageButton) findViewById (R.id.imageButton13);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity5();
            }
        });

        home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity6();
            }
        });
    }
    public void openactivity5() {
        Intent intent = new Intent(this, activity2.class);
        startActivity(intent);
    }

    public void openactivity6() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}