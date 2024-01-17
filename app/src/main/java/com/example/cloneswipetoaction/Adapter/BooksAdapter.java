package com.example.cloneswipetoaction.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.cloneswipetoaction.Model.Book;
import com.example.cloneswipetoaction.R;
import com.example.cloneswipetoaction.SwipeToAction;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private List<Book> items;

    public class ViewHolder extends SwipeToAction.ViewHolder<Book> {
        public TextView titleView;
        public TextView authorView;
        public View imageView;
        public View revealLeftView;
        public View revealRightView;
        public Book data;

        public ViewHolder(View v) {
            super();

            titleView = v.findViewById(R.id.title);
            authorView = v.findViewById(R.id.author);
            imageView = v.findViewById(R.id.image);
            revealLeftView = v.findViewById(R.id.reveal_left);
            revealRightView = v.findViewById(R.id.reveal_right);
        }

        @Override
        public void onItemSelected() {
            // Xử lý khi item được chọn
        }

        @Override
        public void onItemClear() {
            // Xử lý khi item được clear
        }

        public void setSwipeListener(SwipeToAction.SwipeListener<Book> bookSwipeListener) {
        }
    }

    public BooksAdapter(List<Book> items, RequestManager with) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book item = items.get(position);
        holder.titleView.setText(item.getTitle());
        holder.authorView.setText(item.getAuthor());
        holder.imageView.setImageURI(Uri.parse(item.getImageUrl()));

        // Thiết lập sự kiện swipe
        holder.setSwipeListener(new SwipeToAction.SwipeListener<Book>() {
            @Override
            public boolean swipeLeft(Book itemData) {
                // Xử lý khi swipe sang trái
                holder.revealRightView.setVisibility(View.GONE);
                holder.revealLeftView.setVisibility(View.VISIBLE);
                return true; // true nếu muốn hiển thị hiệu ứng swipe, ngược lại là false
            }

            @Override
            public boolean swipeRight(Book itemData) {
                // Xử lý khi swipe sang phải
                holder.revealLeftView.setVisibility(View.GONE);
                holder.revealRightView.setVisibility(View.VISIBLE);
                return true; // true nếu muốn hiển thị hiệu ứng swipe, ngược lại là false
            }

            @Override
            public void onClick(Book itemData) {
                // Xử lý khi item được click
            }

            @Override
            public void onLongClick(Book itemData) {
                // Xử lý khi item được long click
            }
        });

        holder.data = item;
    }
}
