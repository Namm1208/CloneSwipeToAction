package com.example.cloneswipetoaction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private YourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<String> dataList = generateSampleData();


        adapter = new YourAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    // Phương thức để tạo dữ liệu mẫu
    private List<String> generateSampleData() {
        List<String> data = new ArrayList<>();
        data.add("Book 1");
        data.add("Book 2");
        data.add("Book 3");

        return data;
    }
}
