package com.mateuslima.mvvmpartedois.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mateuslima.mvvmpartedois.R;
import com.mateuslima.mvvmpartedois.data.db.model.Favorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    private List<Favorite> favoriteList;
    private OnClickFavorite onClickFavorite;

    public FavoriteAdapter(OnClickFavorite onClickFavorite, List<Favorite> favoriteList) {
        this.onClickFavorite = onClickFavorite;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_livros, parent, false);
        return new MyViewHolder(view, onClickFavorite);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Favorite favorite = favoriteList.get(position);

        holder.textName.setText(favorite.getBookName());
        holder.textDescription.setText(favorite.getBookDescription());
        if (favorite.getBookImageUrl() != null) {
            Picasso.get().load(favorite.getBookImageUrl()).into(holder.imageBook);
        }

        holder.favorite.setImageResource(R.drawable.ic_favorite);


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageBook, favorite;
        private TextView textName, textDescription;
        OnClickFavorite onClickFavorite;

        public MyViewHolder(@NonNull View itemView, OnClickFavorite onClickFavorite) {
            super(itemView);

            imageBook = itemView.findViewById(R.id.imageLivro);
            textName = itemView.findViewById(R.id.nomeLivro);
            textDescription = itemView.findViewById(R.id.descricaoLivro);
            favorite = itemView.findViewById(R.id.imageFavorite);
            this.onClickFavorite = onClickFavorite;
            favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.imageFavorite) {
                onClickFavorite.onClickFavorite(favoriteList.get(getAdapterPosition()));
                favoriteList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            }

        }
    }

    public interface OnClickFavorite{
        void onClickFavorite(Favorite favorite);
    }
}
