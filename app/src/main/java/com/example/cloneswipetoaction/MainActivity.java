package com.example.cloneswipetoaction;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneswipetoaction.Adapter.MyAdapter;
import com.example.cloneswipetoaction.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        recyclerView = findViewById(R.id.recyclerView); // Sửa đổi id thành recyclerView
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        generateItem();
    }

    private void generateItem() {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itemList.add(new Item("pie 0" + (i + 1),
                    "100000",
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Fphotos%2Ffood&psig=AOvVaw1AwfyRavgs2pfNTHWduS5_&ust=1705336526923000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCNCn8uKn3YMDFQAAAAAdAAAAABAD"));
        }
        adapter = new MyAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
    }
}
