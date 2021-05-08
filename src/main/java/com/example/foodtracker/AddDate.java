package com.example.foodtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        back1 = (ImageButton) findViewById (R.id.imageButton4);
       // home1 = (ImageButton) findViewById (R.id.imageButton2);
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

    /*    home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity4();
            }
        }); */
    }
    public void openactivity3() {
        Intent intent = new Intent(this, AddFood.class);
        startActivity(intent);
    }

    public void openactivity4() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openactivity7() {
        Intent intent = new Intent(this, activity3_myitems.class);
        startActivity(intent);
    }

    public void btn_showDialog1(View view) {
        final AlertDialog.Builder alert1 = new AlertDialog.Builder(AddDate.this);
        View myview1 = getLayoutInflater().inflate(R.layout.dialog_date, null);
        final EditText inputText1 = (EditText)myview1.findViewById(R.id.input1);
        Button cancel_button1 = (Button)myview1.findViewById(R.id.cancel1);
        Button add_button1 = (Button)myview1.findViewById(R.id.add1);
        alert1.setView(myview1);
        final AlertDialog dialog1 = alert1.create();
        dialog1.setCanceledOnTouchOutside(false);

        cancel_button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        add_button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            openactivity7();
            }
        });

        dialog1.show();
    }
}