package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddDate extends AppCompatActivity {
    private Button date;
    private ImageButton back1;
    private ImageButton home1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
        date= (Button) findViewById (R.id.button9);
        back1 = (ImageButton) findViewById (R.id.imageButton1); //button 1 for back, button 2 for home
        home1 = (ImageButton) findViewById (R.id.imageButton2);
        date.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent1 = new Intent ();
                    intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity3();
            }
        });

        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity4();
            }
        });
    }
    public void openactivity3() {
        Intent intent = new Intent(this, activity2.class);
        startActivity(intent);
    }

    public void openactivity4() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}