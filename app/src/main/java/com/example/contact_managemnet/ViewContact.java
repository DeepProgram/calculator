package com.example.contact_managemnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.contact_managemnet.views.MyTextView;

import java.util.ArrayList;

public class ViewContact extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String value = extras.getString("id");
            userID = value;
            dbHelper = new MyDatabaseHelper(this);
            database = dbHelper.getWritableDatabase();

            MyTextView name = findViewById(R.id.contact_name);
            MyTextView phone_no = findViewById(R.id.contact_numbers_holder);
            MyTextView email = findViewById(R.id.contact_emails_holder);
            MyTextView country = findViewById(R.id.contact_addresses_holder);
            MyTextView birthdate = findViewById(R.id.birthdate_holder);

            ArrayList<String> userInfo = dbHelper.getRowById(value);

            name.setText(userInfo.get(0));
            phone_no.setText(userInfo.get(1));
            email.setText(userInfo.get(2));
            country.setText(userInfo.get(3));
            birthdate.setText(userInfo.get(4));
        }

        ImageButton deleteButton = findViewById(R.id.DeleteContactID);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteRowById(userID);
                Intent intentObj = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intentObj);
            }
        });




    }
}