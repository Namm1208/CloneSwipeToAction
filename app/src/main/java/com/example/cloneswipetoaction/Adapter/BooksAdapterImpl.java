package com.example.cloneswipetoaction.Adapter;

import com.bumptech.glide.Glide;
import com.example.cloneswipetoaction.Model.Book;

import java.util.List;

public class BooksAdapterImpl extends BooksAdapter {
    public BooksAdapterImpl(List<Book> items) {
        super(items, Glide.with(this));
    }
}
