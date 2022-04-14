package com.example.mybooklibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static final String  ALL_BOOKS="all_books";
    private static final String ALREADY_READ_BOOKS="already_read_books";
    private static final String CURRENTLY_READING_BOOKS="currently_reading_books";
    private static final String FAVORITES_BOOKS="favorite_books";
    private static final String WISHLIST="wish_list_books";


    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> CurrentlyReadingBooks;
//    private static ArrayList<Book> AlreadyReadBooks;
//    private static ArrayList<Book> WishlistBooks;
//    private static ArrayList<Book> FavoriteBooks;

    /**
     * used for storing the data locally using shared preferences
     * The Array list of books is converted to a json file using Gson API
     * and then it is stored locally in String format using sharedpreferences then
     * while retrieving the Array list the json is again converted to Array list of books
     * with the help of a Type format variable in which we define the type of Array list
     * and the interaction is done with the sharedpreferences
     */
    private Utils(Context context){

        sharedPreferences=context.getSharedPreferences("Books_alternate_db",Context.MODE_PRIVATE);

        if(getAllBooks()==null){
            initdata();
        }

        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson=new Gson();

        if(getCurrentlyReadingBooks()==null){
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getAlreadyReadBooks()==null){
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getWishlistBooks()==null){
            editor.putString(WISHLIST,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getFavoriteBooks()==null){
            editor.putString(FAVORITES_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }


    public static Utils getInstance(Context context){
        if(instance==null){
            instance=new Utils(context);
        }
        return instance;
    }


    private void initdata() {

        ArrayList<Book> books=new ArrayList<>();
        books.add(new Book(1,"Ramayana","Valmiki","https://images-na.ssl-images-amazon.com/images/I/91S9-+lV75L.jpg",
                1000, "Story of King Ram","The Ramayana is one of the largest ancient epics in world literature." +
                " It consists of nearly 24,000 verses (mostly set in the Shloka/Anustubh meter), divided into seven kāṇḍas, the first and the seventh being later additions. " +
                "It belongs to the genre of Itihasa, narratives of past events (purāvṛtta), interspersed with teachings on the goals of human life." +
                " Scholars' estimates for the earliest stage of the text range from the 7th to 4th centuries BCE, with later stages extending up to the 3rd century CE." +
                " The Ramayana was an important influence on later Sanskrit poetry and Hindu life and culture" +
                " The characters Rama, Sita, Lakshmana, Bharata, Hanuman, and Ravana are all fundamental to the cultural consciousness of the South Asian nations of India," +
                " Nepal, Sri Lanka and the South-East Asian countries of Cambodia, Indonesia, Malaysia and Thailand." +
                " Its most important moral influence was the importance of virtue, in the life of a citizen and in the ideals of the formation of a state or of a functioning society."));

        books.add(new Book(2,"Mahabharat","Vyasa",
                "https://images-na.ssl-images-amazon.com/images/I/51BY892gRGL._SX258_BO1,204,203,200_.jpg", 13000, "A struggle between two groups of cousins in the Kurukshetra War.",
                "The Mahābhārata is the longest epic poem known and has been described as \"the longest poem ever written\". Its longest version consists of over 100,000 śloka or over 200,000 individual verse lines (each shloka is a couplet), and long prose passages." +
                        " At about 1.8 million words in total, the Mahābhārata is roughly ten times the length of the Iliad and the Odyssey combined, or about four times the length of the Rāmāyaṇa." +
                        " W. J. Johnson has compared the importance of the Mahābhārata in the context of world civilization to that of the Bible, the Quran, the works of Homer, Greek drama, or the works of William Shakespeare." +
                        " Within the Indian tradition it is sometimes called the fifth Veda."));

        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson= new Gson();
        editor.putString(ALL_BOOKS,gson.toJson(books));
        editor.commit();

    }




    public ArrayList<Book> getAllBooks() {

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALL_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getWishlistBooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(WISHLIST,null),type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(FAVORITES_BOOKS,null),type);
        return books;
    }

    public Book getCurrentBookById(int id){
        ArrayList<Book> books =getAllBooks();
        if(books!=null){
            for (Book b:books){
                if(b.getId()==id){
                    return b;
                }
            }
        }
        return null;
    }



    /**
     * adds to already read books array list and return true if added successfully
     * @param book
     * @return
     */
    public boolean addToAlreadyReadBooks(Book book){
        ArrayList<Book> books=getAlreadyReadBooks();
        if(books!=null){
            if(books.add(book)){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Gson gson=new Gson();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }

        }
        return false;
//        return AlreadyReadBooks.add(book);
    }

    /**
     * adds to Wish list Books array list and return true if added successfully
     * @param book
     * @return
     */
    public boolean addToWishlistBooks(Book book){
        ArrayList<Book> books=getWishlistBooks();
        if(books!=null){
            if(books.add(book)){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Gson gson=new Gson();
                editor.remove(WISHLIST);
                editor.putString(WISHLIST,gson.toJson(books));
                editor.commit();
                return true;
            }

        }
        return false;
//        return WishlistBooks.add(book);
    }

    /**
     * adds to Favorite Books array list and return true if added successfully
     * @param book
     * @return
     */
    public boolean addToFavoriteBooks(Book book){
        ArrayList<Book> books=getFavoriteBooks();
        if(books!=null){
            if(books.add(book)){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Gson gson=new Gson();
                editor.remove(FAVORITES_BOOKS);
                editor.putString(FAVORITES_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }

        }
        return false;
//        return FavoriteBooks.add(book);
    }

    /**
     * adds to Currently Reading Books array list and return true if added successfully
     * @param book
     * @return
     */
    public boolean addToCurrentlyReadingBooks(Book book){
        ArrayList<Book> books=getCurrentlyReadingBooks();
        if(books!=null){
            if(books.add(book)){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Gson gson=new Gson();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }

        }
        return false;
//        return CurrentlyReadingBooks.add(book);
    }



    /**
     * Removes the book passed from the Currently Reading Books
     * and returns true if removed successfully
     * @param book
     * @return
     */
    public boolean removeFromCurrentlyReadingBooks(Book book){
        ArrayList<Book> books=getCurrentlyReadingBooks();
        if(books!=null){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)){
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        Gson gson=new Gson();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
//        return CurrentlyReadingBooks.remove(book);
    }

    /**
     * Removes the book passed from the Already Read Books
     * and returns true if removed successfully
     * @param book
     * @return
     */
    public boolean removeFromAlreadyReadBooks(Book book){
        ArrayList<Book> books=getAlreadyReadBooks();
        if(books!=null){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)){
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        Gson gson=new Gson();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
//        return AlreadyReadBooks.remove(book);
    }

    /**
     * Removes the book passed from the Favorite Books
     * and returns true if removed successfully
     * @param book
     * @return
     */
    public boolean removeFromFavoriteBooks(Book book){
        ArrayList<Book> books=getFavoriteBooks();
        if(books!=null){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)){
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        Gson gson=new Gson();
                        editor.remove(FAVORITES_BOOKS);
                        editor.putString(FAVORITES_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
//        return FavoriteBooks.remove(book);
    }

    /**
     * Removes the book passed from the Wish List Books
     * and returns true if removed successfully
     * @param book
     * @return
     */
    public boolean removeFromWishListBooks(Book book){
        ArrayList<Book> books=getWishlistBooks();
        if(books!=null){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)){
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        Gson gson=new Gson();
                        editor.remove(WISHLIST);
                        editor.putString(WISHLIST,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return false;
//        return WishlistBooks.remove(book);
    }

}
