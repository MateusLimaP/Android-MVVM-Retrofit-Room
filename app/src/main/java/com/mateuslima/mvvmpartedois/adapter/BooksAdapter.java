package com.mateuslima.mvvmpartedois.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mateuslima.mvvmpartedois.R;
import com.mateuslima.mvvmpartedois.data.network.response.BooksResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    private List<BooksResponse> booksResponseList;
    private OnClickBook onClickBook;

    public BooksAdapter(OnClickBook onClickBook, List<BooksResponse> booksResponseList) {
        this.onClickBook = onClickBook;
        this.booksResponseList = booksResponseList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_livros, parent, false);
        return new MyViewHolder(view, onClickBook);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BooksResponse livro = booksResponseList.get(position);

        holder.textName.setText(livro.getVolumeInfo().getTitle());
        holder.textDescription.setText(livro.getVolumeInfo().getDescription());
        if (livro.getVolumeInfo().getImageLink() != null) {
            Picasso.get().load(livro.getVolumeInfo().getImageLink().getUrlLowImage()).into(holder.imageBook);
        }

        if (livro.isFavorite())
            holder.favorite.setImageResource(R.drawable.ic_favorite);
        else
            holder.favorite.setImageResource(R.drawable.ic_favorite_grey);


    }

    @Override
    public int getItemCount() {
        return booksResponseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private ImageView imageBook, favorite;
        private TextView textName, textDescription;
        OnClickBook onClickBook;

        public MyViewHolder(@NonNull View itemView, final OnClickBook onClickBook) {
            super(itemView);

            imageBook = itemView.findViewById(R.id.imageLivro);
            textName = itemView.findViewById(R.id.nomeLivro);
            textDescription = itemView.findViewById(R.id.descricaoLivro);
            favorite = itemView.findViewById(R.id.imageFavorite);
            this.onClickBook = onClickBook;
            favorite.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.imageFavorite)
                onClickBook.onClickFavorite(booksResponseList.get(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnClickBook{
        void onClickFavorite(BooksResponse book, int positon);
    }
}
