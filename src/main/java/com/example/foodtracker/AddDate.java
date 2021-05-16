package com.example.foodtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class AddDate extends AppCompatActivity {
    private Button date;
    private ImageButton back1;
    private ImageButton home1;
    private FoodItem foodItem = new FoodItem();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String TAG = "test";
    public static final String ITEM_KEY = "item";
    public static final String DATE_KEY = "date";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
        date= (Button) findViewById (R.id.button9);
        back1 = (ImageButton) findViewById (R.id.imageButton4);
        date.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startCropperActivity();
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity3();
            }
        });

        //Get the Food string typed into the "Add Food" activity
        Intent intent = getIntent();
        String newFood = intent.getStringExtra("FoodString");
        foodItem.setItem(newFood);
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
        saveItem();
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
                String dateText = inputText1.getText().toString();
                try {
                    foodItem.setStringDate(dateText);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                openactivity7();
            }
        });

        dialog1.show();
    }

    private void startCropperActivity() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void saveItem() throws ParseException {

        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(ITEM_KEY, foodItem.getItem());
        dataToSave.put(DATE_KEY, foodItem.getDate());

        Log.d(TAG, "Food Item: " + foodItem.getItem());
        Log.d(TAG, "Food Date: " + foodItem.getStringDate());
        db.collection("FoodCollection").document(foodItem.getItem()).set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document has been saved");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Document was not saved");
                Log.w(TAG, e);
            }
        });
    }
}