package com.example.baith2_b2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> countryNames;  // Thay đổi sang ArrayList
    private ArrayList<Integer> countryFlags;  // Thay đổi sang ArrayList

    // Constructor nhận ArrayList thay vì mảng
    public MyAdapter(Context context, ArrayList<String> countryNames, ArrayList<Integer> countryFlags) {
        this.context = context;
        this.countryNames = countryNames;
        this.countryFlags = countryFlags;
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }

    @Override
    public Object getItem(int position) {
        return countryNames.get(position);
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
            view = inflater.inflate(R.layout.spinner_item, null);
        }

        ImageView flagImage = view.findViewById(R.id.flagImage);
        TextView countryName = view.findViewById(R.id.countryName);
        Button removeButton = view.findViewById(R.id.removeButton);

        flagImage.setImageResource(countryFlags.get(position));
        countryName.setText(countryNames.get(position));

        // Xử lý khi bấm nút remove
        removeButton.setOnClickListener(v -> {
            countryNames.remove(position);
            countryFlags.remove(position);
            notifyDataSetChanged();  // Cập nhật lại adapter
        });

        return view;
    }
}
