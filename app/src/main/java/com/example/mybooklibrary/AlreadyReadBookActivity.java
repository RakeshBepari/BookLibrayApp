package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {
    private RecyclerView alreadyReadBooksRecView;
    private ConstraintLayout  alreadyReadParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        alreadyReadBooksRecView=findViewById(R.id.alreadyReadBooksRecView);
        alreadyReadParent=findViewById(R.id.alreadyReadParent);

//      BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.alreadyReadBooks,alreadyReadParent);
        BookRecViewAdapter adapter=new BookRecViewAdapter(this,BookRecViewAdapter.alreadyReadBooks);
        alreadyReadBooksRecView.setAdapter(adapter);
        alreadyReadBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());

    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}