package com.example.mybooklibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks,btnAlreadyRead,btnWantToRead,btnCurrentlyReading,btnFavorite,btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();

        setClickListenersForButtons();

        /**
         * Initializing the Utils class so that the user can go in any activity he want
         * by clicking the respective button. If it is initialized in other activity like seeallbooks
         * and user clicks on other activity and the utils is unitialized then it will throw a null pointer exception
         */
        Utils.getInstance(this);
    }

    /**
     * Click listeners for the buttons which navigate user to the set activity
     */
    private void setClickListenersForButtons() {
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,CurrentlyReadingActivity.class);
                startActivity(intent);
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intent);
            }
        });
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AlreadyReadBookActivity.class);
                startActivity(intent);
            }
        });
        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,WishlistActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Designed and Developed for only learning purposes and testing purposes"+
                        " Visit the site for more information");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", (dialog, which) -> {
                    Intent intent= new Intent(MainActivity.this,WebsiteActivity.class);
                    intent.putExtra("url","");
                    startActivity(intent);
                });
                builder.create().show();
            }
        });
    }

    private void initviews() {
        btnAllBooks=findViewById(R.id.btnAllBooks);
        btnAlreadyRead=findViewById(R.id.btnAlreadyRead);
        btnWantToRead=findViewById(R.id.btnWantToRead);
        btnCurrentlyReading=findViewById(R.id.btnCurrentlyReading);
        btnFavorite=findViewById(R.id.btnFavorite);
        btnAbout=findViewById(R.id.btnAbout);
    }
}