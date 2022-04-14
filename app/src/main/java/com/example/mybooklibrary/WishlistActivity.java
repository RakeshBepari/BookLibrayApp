package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView WishlistRecView;
    private ConstraintLayout wishlistParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        WishlistRecView=findViewById(R.id.WishlistRecView);
        wishlistParent=findViewById(R.id.wishlistParent);

//        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.wishList,wishlistParent);
        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.wishList);
        WishlistRecView.setAdapter(adapter);
        WishlistRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getWishlistBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}