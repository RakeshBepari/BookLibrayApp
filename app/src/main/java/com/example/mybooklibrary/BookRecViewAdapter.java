package com.example.mybooklibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.example.mybooklibrary.BookActivity.Book_ID;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    public static final String seeAllBooks = "SeeAllBooks";
    public static final String currentlyReadingBooks = "CurrentlyReadingBooks";
    public static final String FavoriteBooks = "Favorites";
    public static final String alreadyReadBooks = "AlreadyReadbooks";
    public static final String wishList = "WishList";

    private static final String TAG = "BookRecViewAdapter";
    private ArrayList<Book> books= new ArrayList<>();
    private Context mContext;
    private String parentActivity;
//    private View view;

    //    public BookRecViewAdapter(Context mContext, String parentActivity,View view) {
//        this.mContext = mContext;
//        this.parentActivity = parentActivity;
//        this.view=view;
//    }

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    /**
     * Sets the Book Name,Author,ShortDes
     * Expands and collapse the information of the book
     * Directs to the detailed book page by clicking on the book
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtBookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext,BookActivity.class);
                intent.putExtra(Book_ID,books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShorDes());

        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);

            if(parentActivity.equals(seeAllBooks)){
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals(currentlyReadingBooks)){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String bookNameToBeRemoved=books.get(position).getName();
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete the book"+books.get(position).getName()+" from the list");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromCurrentlyReadingBooks(books.get(position))){
//                                    Snackbar.make(mContext,view,bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", BaseTransientBottomBar.LENGTH_INDEFINITE)
//                                            .setAction("Dismiss", new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//
//                                                }
//                                            })
//                                            .setActionTextColor(Color.RED)
//                                            .show();
                                    Toast.makeText(mContext, bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(mContext, "Something went wrong Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }



            else if(parentActivity.equals(alreadyReadBooks)){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String bookNameToBeRemoved=books.get(position).getName();
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete the book"+books.get(position).getName()+" from the list");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromAlreadyReadBooks(books.get(position))){
//                                    Snackbar.make(mContext,view,bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", BaseTransientBottomBar.LENGTH_INDEFINITE)
//                                            .setAction("Dismiss", new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//
//                                                }
//                                            })
//                                            .setActionTextColor(Color.RED)
//                                            .show();
                                    Toast.makeText(mContext, bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(mContext, "Something went wrong Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }


            else if(parentActivity.equals(FavoriteBooks)){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String bookNameToBeRemoved=books.get(position).getName();
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete the book"+books.get(position).getName()+" from the list");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromFavoriteBooks(books.get(position))){
//                                    Snackbar.make(mContext,view,bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", BaseTransientBottomBar.LENGTH_INDEFINITE)
//                                            .setAction("Dismiss", new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//
//                                                }
//                                            })
//                                            .setActionTextColor(Color.RED)
//                                            .show();
                                    Toast.makeText(mContext, bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(mContext, "Something went wrong Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }


            else if(parentActivity.equals(wishList)){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String bookNameToBeRemoved=books.get(position).getName();
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete the book"+books.get(position).getName()+" from the list");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromWishListBooks(books.get(position))){
//                                    Snackbar.make(mContext,view,bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", BaseTransientBottomBar.LENGTH_INDEFINITE)
//                                            .setAction("Dismiss", new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//
//                                                }
//                                            })
//                                            .setActionTextColor(Color.RED)
//                                            .show();
                                    Toast.makeText(mContext, bookNameToBeRemoved+" REMOVED SUCCESSFULLY FROM THE LIST", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(mContext, "Something went wrong Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }

        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    /**
     * Sets the books for which the recycler view show the list in the recycler view
     * @param books
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    /**
     * The down arrow on click listener sets the value of is expanded and
     * notify data set change so that the card can be expanded in the
     * onBindViewHolder and same for the collapsing of the info
     */
    public class ViewHolder extends RecyclerView.ViewHolder{


        private MaterialCardView parent;
        private ImageView imgBook;
        private TextView txtBookName;
        private ImageView btnDownArrow,btnUpArrow;
        private RelativeLayout collapsedRelativeLayout,expandedRelativeLayout;
        private TextView txtAuthor,txtShortDesc;

        private TextView btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent=itemView.findViewById(R.id.parent);
            imgBook=itemView.findViewById(R.id.imgBook);
            txtBookName=itemView.findViewById(R.id.txtBookName);

            btnDownArrow=itemView.findViewById(R.id.btnDownArrow);
            btnUpArrow=itemView.findViewById(R.id.btnUpArrow);
            expandedRelativeLayout=itemView.findViewById(R.id.expandedRelativeLayout);
            txtAuthor=itemView.findViewById(R.id.textAuthor);
            txtShortDesc=itemView.findViewById(R.id.txtShortDesc);

            btnDelete=itemView.findViewById(R.id.btnDelete);

            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book=books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book=books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
