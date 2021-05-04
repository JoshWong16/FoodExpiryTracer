package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class activity2 extends AppCompatActivity {
    private Button button1;
    private Button Add_food;
    private Button Add_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        Add_food= (Button) findViewById(R.id.button2);
        Add_date = (Button) findViewById (R.id.button3);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity3();
            }
        });
        Add_food.setOnClickListener (new View.OnClickListener(){
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
        Add_date.setOnClickListener (new View.OnClickListener(){
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
        });  }

    public void openactivity3() {
        Intent intent = new Intent(this, activity3_myitems.class);
        startActivity(intent);
    }


}