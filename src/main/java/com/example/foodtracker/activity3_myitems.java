package com.example.foodtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static java.util.Collections.sort;

public class activity3_myitems extends AppCompatActivity {
    private ImageButton back2;
    private ImageButton home2;

    public static final String ITEM_KEY = "item";
    public static final String DATE_KEY = "date";
    public static final String TAG = "test";

    ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> displayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_myitems);
        back2 = (ImageButton) findViewById (R.id.imageButton5);

        listView = (ListView) findViewById(R.id.listview1);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity5();
            }
        });

       /* home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity6();
            }
        }); */



        db.collection("FoodCollection").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                //Add data from database into FoodList as custom object "FoodItem"
                ArrayList<FoodItem> FoodList = new ArrayList<>();
                for (QueryDocumentSnapshot doc : value) {
                    FoodItem newData = doc.toObject(FoodItem.class);
                    FoodList.add(newData);
                }

                sort(FoodList);

                //Make new list of strings for displaying in app
                displayList = new ArrayList<>();
                for (FoodItem foodItem : FoodList){
                    displayList.add(foodItem.getItem() + ": " + foodItem.getStringDate());
                }

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList);

        listView.setAdapter(arrayAdapter);

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