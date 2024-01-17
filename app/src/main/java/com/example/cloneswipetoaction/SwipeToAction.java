package com.example.cloneswipetoaction;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneswipetoaction.Model.Book;

public class SwipeToAction {

    public SwipeToAction(RecyclerView recyclerView, SwipeListener<Book> bookSwipeListener) {

    }

    public ItemTouchHelper.Callback getCallback() {
        return null;
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

        public ViewHolder() {
            super();
            View v = null;
            super(v);

            ViewGroup vg = (ViewGroup) v;
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
