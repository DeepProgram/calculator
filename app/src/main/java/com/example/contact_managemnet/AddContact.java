package com.example.contact_managemnet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddContact extends AppCompatActivity implements View.OnClickListener{
    final Calendar myCalendar= Calendar.getInstance();
    private EditText editBirthDate;
    private EditText editFirstName;
    private EditText editSurname;
    private EditText editPhoneNo;
    private EditText editEmail;
    private EditText editCountry;


    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        editBirthDate=(EditText) findViewById(R.id.EditBirthDateID);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddContact.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editFirstName = findViewById(R.id.EditFirstNameID);
        editSurname = findViewById(R.id.EditSurNameID);
        editPhoneNo = findViewById(R.id.EditPhoneNumberID);
        editEmail = findViewById(R.id.EditEmailID);
        editCountry = findViewById(R.id.EditCountryID);
        editBirthDate = findViewById(R.id.EditBirthDateID);

        Button saveContactButton = findViewById(R.id.SaveContactID);
        saveContactButton.setOnClickListener(this);
    }
    private void updateLabel(){
        String myFormat="dd-MM-yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editBirthDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.SaveContactID){
            long insertedRow = dbHelper.insertData(editFirstName.getText().toString(),editSurname.getText().toString(),
                    editPhoneNo.getText().toString(), editEmail.getText().toString(),
                    editCountry.getText().toString(), editBirthDate.getText().toString());
            Intent intentObj = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intentObj);
        }
    }
}