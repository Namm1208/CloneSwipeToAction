package com.example.cloneswipetoaction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cloneswipetoaction.Adapter.BooksAdapter;
import com.example.cloneswipetoaction.Model.Book;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BooksAdapter adapter;
    SwipeToAction swipeToAction;

    List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Khởi tạo danh sách sách mẫu (điều này cần thay đổi theo nhu cầu của bạn)
        initializeBooks();

        // Khởi tạo adapter
        adapter = new BooksAdapter(books, Glide.with(this));

        // Thiết lập SwipeToAction
        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Book>() {
            @Override
            public boolean swipeLeft(Book itemData) {
                // Xử lý khi swipe sang trái
                showSnackbar("Swiped Left: " + itemData.getTitle());
                return true; // true nếu bạn muốn hiển thị hiệu ứng swipe, ngược lại là false
            }

            @Override
            public boolean swipeRight(Book itemData) {
                // Xử lý khi swipe sang phải
                showSnackbar("Swiped Right: " + itemData.getTitle());
                return true; // true nếu bạn muốn hiển thị hiệu ứng swipe, ngược lại là false
            }

            @Override
            public void onClick(Book itemData) {
                // Xử lý khi item được click
                showSnackbar("Clicked: " + itemData.getTitle());
            }

            @Override
            public void onLongClick(Book itemData) {
                // Xử lý khi item được long click
                showSnackbar("Long Clicked: " + itemData.getTitle());
            }
        });

        // Bổ sung ItemTouchHelper vào SwipeToAction
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToAction.getCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);
    }

    private void initializeBooks() {
        // Thêm các sách mẫu vào danh sách
        // Điều này cần thay đổi tùy thuộc vào cách bạn quản lý dữ liệu sách
        for (int i = 1; i <= 20; i++) {
            books.add(new Book("Book " + i, "Author " + i, "https://example.com/book" + i + ".jpg"));
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }
}
