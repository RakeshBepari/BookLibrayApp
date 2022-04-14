package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String Book_ID="Bookid";

    private RelativeLayout relView;
    private ImageView detailImgBook;
    private Button btnAddToCurrentlyReading,btnAddToWishlist,btnAddToAlreadyRead,btnAddtoFavorites;
    private TextView detailBookName,detailAuthor,detailPages,detailLongDesc;
    private TextView detailSetName,detailSetAuthor,detailSetPages,detailSetLongDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initviews();

        Intent intent=getIntent();

        if(intent!=null){
            int bookId=intent.getIntExtra(Book_ID,-1);
            if(bookId!=-1){
                Book incomingbook=Utils.getInstance(this).getCurrentBookById(bookId);
                if (incomingbook!=null){
                    System.out.println(incomingbook.getLongDes());
                    setdata(incomingbook);
                    handleAlreadyReadBooks(incomingbook);
                    handleWantToReadBooks(incomingbook);
                    handleFavoriteBooks(incomingbook);
                    handleCurrentlyReadingBooks(incomingbook);

                }
            }
        }
    }




    /**
     * Checks if the selected book exist in CurrentlyReadingBooks
     * Enable and Disable Add_to_Currently_Reading_Books Button
     * Adds the currently selected book to the Currently Reading Books list
     * @param book
     */
    private void handleCurrentlyReadingBooks(final Book book) {

        ArrayList<Book> CurrentlyReadingBooks= Utils.getInstance(this).getCurrentlyReadingBooks();

        Boolean exitsInCurrentlyReadingBooks=false;

        for(Book b: CurrentlyReadingBooks){
            if(b.getId()==book.getId()){
                exitsInCurrentlyReadingBooks=true;
            }
        }

        if(exitsInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }
        else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "Successfully added to Currently Reading list", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(BookActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }



    /**
     * Checks if the selected book exist in FavoriteBooklist
     * Enable and Disable Add_to_Favorite Button
     * Adds the currently selected book to the Favorite list
     * @param book
     */
    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks= Utils.getInstance(this).getFavoriteBooks();

        Boolean exitsInFavoriteBooks=false;

        for(Book b: favoriteBooks){
            if(b.getId()==book.getId()){
                exitsInFavoriteBooks=true;
            }
        }

        if(exitsInFavoriteBooks){
            btnAddtoFavorites.setEnabled(false);
        }
        else{
            btnAddtoFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Successfully added to Favorites list", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(BookActivity.this,FavoriteActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Checks if the selected book exist in WishlistBook
     * Enable and Disable Add_to_Wish_list Button
     * Adds the currently selected book to the Wish List
     * @param book
     */
    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks= Utils.getInstance(this).getWishlistBooks();

        Boolean exitsInWantToReadBooks=false;

        for(Book b: wantToReadBooks){
            if(b.getId()==book.getId()){
                exitsInWantToReadBooks=true;
            }
        }

        if(exitsInWantToReadBooks){
            btnAddToWishlist.setEnabled(false);
        }
        else{
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWishlistBooks(book)){
                        Toast.makeText(BookActivity.this, "Successfully added to Wish List list", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(BookActivity.this,WishlistActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /**
     * Checks if the selected book exist in AlreadyReadBooks
     * Enable and Disable Add_to_Already_Read_Books Button
     * Adds the currently selected book to the already read book
     * @param book
     */
    private void handleAlreadyReadBooks(final Book book) {
         ArrayList<Book> alreadyReadBooks= Utils.getInstance(this).getAlreadyReadBooks();

         Boolean existsInAlreadyReadBooks=false;

         for(Book b: alreadyReadBooks){
             if(b.getId()==book.getId()){
                 existsInAlreadyReadBooks=true;
             }
         }

         if(existsInAlreadyReadBooks){
             btnAddToAlreadyRead.setEnabled(false);
         }
         else{
             btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(Utils.getInstance(BookActivity.this).addToAlreadyReadBooks(book)){
                         Toast.makeText(BookActivity.this, "Successfully added to already read list", Toast.LENGTH_SHORT).show();
                         Intent intent= new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                         startActivity(intent);
                     }
                     else{
                         Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
    }

    /**
     * Sets the data of the book clicked like the pic,name,author,description
     * @param book
     */

    private void setdata(Book book) {
        detailSetName.setText(book.getName());
        detailSetAuthor.setText(book.getAuthor());
        detailSetPages.setText(String.valueOf(book.getPages()));
        detailSetLongDesc.setText(book.getLongDes());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(detailImgBook);
    }

    private void initviews() {

        relView=findViewById(R.id.relView);

        detailImgBook=findViewById(R.id.detailImgBook);

        btnAddToCurrentlyReading=findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWishlist=findViewById(R.id.btnAddToWishlist);
        btnAddToAlreadyRead=findViewById(R.id.btnAddToAlreadyReadList);
        btnAddtoFavorites=findViewById(R.id.btnAddToFavorites);

        detailBookName=findViewById(R.id.detailBookName);
        detailAuthor=findViewById(R.id.detailAuthor);
        detailPages=findViewById(R.id.detailPages);
        detailLongDesc=findViewById(R.id.detailLongDesc);

        detailSetName=findViewById(R.id.detailSetName);
        detailSetAuthor=findViewById(R.id.detailSetAuthor);
        detailSetPages=findViewById(R.id.detailSetPages);
        detailSetLongDesc=findViewById(R.id.detailSetLongDesc);
    }
}