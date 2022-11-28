package com.example.contact_managemnet.views;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contact_managemnet.R;
import com.example.contact_managemnet.ViewContact;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<List<String>> {
    private int resourceLayout;
    private Context mContext;


    public ListAdapter(@NonNull Context context, int resource, ArrayList<List<String>> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        List<String> p =  getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.contact_name);
            TextView tt2 = (TextView) v.findViewById(R.id.contact_number);

            tt1.setText(p.get(1));
            tt2.setText(p.get(2));

            FrameLayout frameLayout = v.findViewById(R.id.contact_frame);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentObj = new Intent(mContext, ViewContact.class);
                    intentObj.putExtra("id", p.get(0));
                    mContext.startActivity(intentObj);
                }
            });

        }
        return v;
    }





}
