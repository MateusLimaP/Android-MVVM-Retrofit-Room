package com.mateuslima.mvvmpartedois.ui.favorite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mateuslima.mvvmpartedois.R;
import com.mateuslima.mvvmpartedois.adapter.FavoriteAdapter;
import com.mateuslima.mvvmpartedois.data.db.model.Favorite;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnClickFavorite {

    private RecyclerView recyclerFavorite;
    private FavoriteViewModel viewModel;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        initUi(view);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(FavoriteViewModel.class);

        viewModel.getFavoriteList().observe(getViewLifecycleOwner(), new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favoriteList) {
                showFavorites(favoriteList);
            }
        });

        return view;
    }

    private void initUi(View view){
        recyclerFavorite = view.findViewById(R.id.recycler_favorite);
    }

    private void showFavorites(List<Favorite> favoriteList){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        FavoriteAdapter adapter = new FavoriteAdapter(this, favoriteList);
        recyclerFavorite.setLayoutManager(layoutManager);
        recyclerFavorite.setAdapter(adapter);
        recyclerFavorite.setHasFixedSize(true);
    }

    @Override
    public void onClickFavorite(Favorite favorite) {
        viewModel.deleteFavorite(favorite);

    }
}
