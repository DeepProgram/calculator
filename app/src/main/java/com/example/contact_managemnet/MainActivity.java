package com.example.contact_managemnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contact_managemnet.views.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView contactLayout;

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactLayout = findViewById(R.id.ContactListID);

        dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        ArrayList<List<String>>userInfoList = dbHelper.getAllRowNameAndNumber();

        ListAdapter adapter = new ListAdapter(this, R.layout.activity_contact_with_number, userInfoList);

        contactLayout.setAdapter(adapter);

        ImageButton addContactButton = findViewById(R.id.AddContactID);
        addContactButton.setOnClickListener(this);

//        contactLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast toast = Toast.makeText(getApplicationContext(),"Adapter Clicked",Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.AddContactID){
            Intent intentObj = new Intent(getBaseContext(),AddContact.class);
            startActivity(intentObj);
        }

    }
}