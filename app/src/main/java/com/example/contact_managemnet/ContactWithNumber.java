package com.example.contact_managemnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContactWithNumber extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_with_number);
//        TextView textView = findViewById(R.id.contact_name);
//        textView.setText("System");
        Toast toast = Toast.makeText(ContactWithNumber.this, "Toasting",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.contact_holder){
            TextView name = findViewById(R.id.contact_name);
            System.out.println("---------------------");
            System.out.println(name.getText().toString());
            System.out.println("---------------------");
        }
    }
}