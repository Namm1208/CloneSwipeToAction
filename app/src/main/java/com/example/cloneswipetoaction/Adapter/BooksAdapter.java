package com.example.cloneswipetoaction.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneswipetoaction.Model.Book;
import com.example.cloneswipetoaction.R;
import com.example.cloneswipetoaction.SwipeToAction;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private final List<Book> items;
    private final SwipeToAction.SwipeListener<Book> bookSwipeListener;

    public BooksAdapter(List<Book> items, SwipeToAction.SwipeListener<Book> bookSwipeListener) {
        this.items = items;
        this.bookSwipeListener = bookSwipeListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view, bookSwipeListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book item = items.get(position);
        holder.bindData(item);
    }

    public static class ViewHolder extends SwipeToAction.ViewHolder<Book> {
        public TextView titleView;
        public TextView authorView;
        public View imageView;

        public ViewHolder(View itemView, SwipeToAction.SwipeListener<Book> bookSwipeListener) {
            super(itemView, bookSwipeListener);

            titleView = itemView.findViewById(R.id.title);
            authorView = itemView.findViewById(R.id.author);
            imageView = itemView.findViewById(R.id.image);
        }

        public void bindData(Book item) {
            data = item;

            titleView.setText(item.getTitle());
            authorView.setText(item.getAuthor());

            // Load ảnh sử dụng Glide hoặc thư viện tương tự
            // Glide.with(itemView).load(item.getImageUrl()).into(imageView);
        }

        @Override
        public void onItemSelected() {
            // Xử lý khi item được chọn
        }

        @Override
        public void onItemClear() {
            // Xử lý khi item được clear
        }
    }
}
