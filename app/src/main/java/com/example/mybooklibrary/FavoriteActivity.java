package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView FavoritesListRecView;
    private ConstraintLayout favoriteParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        FavoritesListRecView=findViewById(R.id.FavoriteListRecView);
        favoriteParent=findViewById(R.id.favoriteParent);

//        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.FavoriteBooks,favoriteParent);
        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.FavoriteBooks);
        FavoritesListRecView.setAdapter(adapter);
        FavoritesListRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}