package com.example.baith1_b2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private String[] countryNames;
    private int[] countryFlags;  // Array of drawable resources (country flags)

    public MyAdapter(Context context, String[] countryNames, int[] countryFlags) {
        super();
        this.context = context;
        this.countryNames = countryNames;
        this.countryFlags = countryFlags;
    }

    @Override
    public int getCount() {
        return countryNames.length;
    }

    @Override
    public Object getItem(int position) {
        return countryNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_spinner2, null);
        }

        ImageView flagImage = view.findViewById(R.id.flagImage);
        TextView countryName = view.findViewById(R.id.countryName);

        flagImage.setImageResource(countryFlags[position]);
        countryName.setText(countryNames[position]);

        return view;
    }
}
