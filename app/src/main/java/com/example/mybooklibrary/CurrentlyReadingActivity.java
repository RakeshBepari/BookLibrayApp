package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingActivity extends AppCompatActivity {

    private RecyclerView CurrentlyReadingRecView;
    private ConstraintLayout CurrentlyReadingParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        CurrentlyReadingRecView=findViewById(R.id.CurrentlyReadingRecView);
        CurrentlyReadingParent=findViewById(R.id.CurrentlyReadingParent);

//        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.currentlyReadingBooks,CurrentlyReadingParent);
        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.currentlyReadingBooks);
        CurrentlyReadingRecView.setAdapter(adapter);
        CurrentlyReadingRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}