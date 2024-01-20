package com.example.cloneswipetoaction;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneswipetoaction.Model.Book;

public class SwipeToAction {

    private final RecyclerView recyclerView;
    private final SwipeListener<Book> bookSwipeListener;

    public SwipeToAction(RecyclerView recyclerView, SwipeListener<Book> bookSwipeListener) {
        this.recyclerView = recyclerView;
        this.bookSwipeListener = bookSwipeListener;
        attachSwipe();
    }

    public ItemTouchHelper.Callback getCallback() {
        return new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // Implement your logic for movement flags here
                return 0;
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // Implement your logic for item move here
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Implement your logic for item swipe here
            }
        };
    }

    private void attachSwipe() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(getCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public interface SwipeListener<T> {
        boolean swipeLeft(T itemData);
        boolean swipeRight(T itemData);
        void onClick(T itemData);
        void onLongClick(T itemData);
    }

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {
        public T data;
        public View front;
        public View revealLeft;
        public View revealRight;

        public ViewHolder(View itemView, SwipeListener<T> itemSwipeListener) {
            super(itemView);

            ViewGroup vg = (ViewGroup) itemView;
            front = vg.findViewWithTag("front");
            revealLeft = vg.findViewWithTag("reveal-left");
            revealRight = vg.findViewWithTag("reveal-right");

            int childCount = vg.getChildCount();
            if (front == null) {
                if (childCount < 1) {
                    throw new RuntimeException("You must provide a view with tag='front'");
                } else {
                    front = vg.getChildAt(childCount - 1);
                }
            }

            if (revealLeft == null || revealRight == null) {
                if (childCount < 2) {
                    throw new RuntimeException("You must provide at least one reveal view.");
                } else {
                    // set next to last as revealLeft view only if no revealRight was found
                    if (revealLeft == null && revealRight == null) {
                        revealLeft = vg.getChildAt(childCount - 2);
                    }

                    // if there are enough children assume the revealRight
                    int i = childCount - 3;
                    if (revealRight == null && i > -1) {
                        revealRight = vg.getChildAt(i);
                    }
                }
            }

            setClickListener(itemSwipeListener);
        }

        private void setClickListener(final SwipeListener<T> itemSwipeListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemSwipeListener != null) {
                        itemSwipeListener.onClick(data);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemSwipeListener != null) {
                        itemSwipeListener.onLongClick(data);
                        return true;
                    }
                    return false;
                }
            });
        }

        public View getFront() {
            return front;
        }

        public View getRevealLeft() {
            return revealLeft;
        }

        public View getRevealRight() {
            return revealRight;
        }

        public T getItemData() {
            return data;
        }

        public abstract void onItemSelected();

        public abstract void onItemClear();
    }
}
