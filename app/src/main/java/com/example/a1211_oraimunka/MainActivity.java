package com.example.a1211_oraimunka;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText mennyisegEditText;
    private EditText darab_arEditText;
    private EditText kategoraEditText;
    private Button cancelButton;
    private Button addButton;
    private Button showAddFormButton;
    private LinearLayout formLinearLayout;
    private ListView itemListView;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;
    private RetrofitApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        // API interfész létrehozása
        apiService = RetrofitClient.getInstance().create(RetrofitApiService.class);
        loadPeople();

        showAddFormButton.setOnClickListener(view -> {
            formLinearLayout.setVisibility(View.VISIBLE);
            showAddFormButton.setVisibility(View.GONE);
        });

        cancelButton.setOnClickListener(view -> {
            formLinearLayout.setVisibility(View.GONE);
            showAddFormButton.setVisibility(View.VISIBLE);
        });

        addButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            int mennyiseg = Integer.parseInt(mennyisegEditText.getText().toString());
            int darabAr = Integer.parseInt(darab_arEditText.getText().toString());
            String kategoria = kategoraEditText.getText().toString();

            // Új elem létrehozása
            Item newItem = new Item(0, name, mennyiseg, darabAr, kategoria);
            addItem(newItem);
        });
    }

    public void init() {
        nameEditText = findViewById(R.id.nameEditText);
        mennyisegEditText = findViewById(R.id.mennyisegEditText);
        darab_arEditText = findViewById(R.id.darab_arEditText);
        kategoraEditText = findViewById(R.id.kategoraEditText);
        formLinearLayout = findViewById(R.id.formLinearLayout);
        cancelButton = findViewById(R.id.cancelButton);
        addButton = findViewById(R.id.addButton);
        showAddFormButton = findViewById(R.id.showAddFormButton);
        itemListView = findViewById(R.id.itemListView);
        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList, this);
        itemListView.setAdapter(itemAdapter);
    }

    public void loadPeople() {
        apiService.getAllItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.clear();
                    itemList.addAll(response.body());
                    itemAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Fail to load the item list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error loading the item list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addItem(Item item) {
        apiService.creatItem(item).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.add(response.body());
                    itemAdapter.notifyDataSetChanged();
                    formLinearLayout.setVisibility(View.GONE);
                    showAddFormButton.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to add item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error adding item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
