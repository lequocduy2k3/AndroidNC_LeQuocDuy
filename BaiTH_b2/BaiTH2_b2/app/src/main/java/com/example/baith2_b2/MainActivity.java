package com.example.baith2_b2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner countrySpinner;
    ArrayList<String> countryNames;
    ArrayList<Integer> countryFlags;
    MyAdapter adapter;
    ImageView selectedFlagImage;
    int selectedFlag = R.drawable.default_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countrySpinner = findViewById(R.id.spinner);

        // Khởi tạo danh sách quốc gia và cờ
        countryNames = new ArrayList<>(Arrays.asList("USA", "Canada", "Japan", "Germany", "Brazil"));
        countryFlags = new ArrayList<>(Arrays.asList(R.drawable.usa, R.drawable.canada, R.drawable.japan,
                R.drawable.germany, R.drawable.brazil));

        // Khởi tạo adapter
        adapter = new MyAdapter(this, countryNames, countryFlags);
        countrySpinner.setAdapter(adapter);

        // Thêm một quốc gia mới bằng dialog
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> showAddCountryDialog());
    }

    private void showAddCountryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_country, null);

        EditText countryNameInput = dialogView.findViewById(R.id.countryNameInput);
        ImageView countryFlagImage = dialogView.findViewById(R.id.countryFlagImage);
        Button selectFlagButton = dialogView.findViewById(R.id.selectFlagButton);

        // Lấy ảnh từ gallery
        selectFlagButton.setOnClickListener(v -> {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);
        });

        builder.setView(dialogView)
                .setTitle("Add New Country")
                .setPositiveButton("Add", (dialog, which) -> {
                    String newCountryName = countryNameInput.getText().toString();
                    // Sử dụng cờ mặc định nếu người dùng không chọn ảnh từ thư viện
                    int newCountryFlag = R.drawable.default_flag;
                    countryNames.add(newCountryName);
                    countryFlags.add(newCountryFlag);
                    adapter.notifyDataSetChanged();  // Cập nhật adapter
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            selectedFlagImage.setImageURI(selectedImage);  // Cập nhật ImageView trong dialog
            selectedFlag = R.drawable.default_flag;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}