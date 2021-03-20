package com.mateuslima.mvvmpartedois.ui.books;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.mateuslima.mvvmpartedois.R;
import com.mateuslima.mvvmpartedois.adapter.BooksAdapter;
import com.mateuslima.mvvmpartedois.data.db.model.Favorite;
import com.mateuslima.mvvmpartedois.data.network.response.BooksResponse;
import com.mateuslima.mvvmpartedois.util.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment implements BooksAdapter.OnClickBook {

    private BooksViewModel viewModel;
    private SearchView searchView;
    private ImageView imageSearchBook;
    private RecyclerView recyclerLivros;
    private ProgressBar progressBar;
    private BooksAdapter adapter;
    private List<BooksResponse> clearBooks = new ArrayList<>();


    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_books, container, false);
        initUi(view);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
        ).get(BooksViewModel.class);

        searchBook();


        viewModel.getBooksResponseList().observe(getViewLifecycleOwner(), new Observer<Resource<List<BooksResponse>>>() {
            @Override
            public void onChanged(Resource<List<BooksResponse>> listResource) {

                switch (listResource.getStatus()){
                    case CARREGANDO:
                        showProgressBar();
                        hideImageSearchBook();
                        break;
                    case SUCESSO:
                        hideProgressBar();
                        showBooks(listResource.getData());
                        break;
                }


            }
        });

        viewModel.getFavoriteList().observe(getViewLifecycleOwner(), new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favoriteList) {
                // retrieve favorite list to compare with book database

            }
        });



        return view;
    }

    private void initUi(View view){
        searchView = view.findViewById(R.id.searchView);
        recyclerLivros = view.findViewById(R.id.recycler_livros);
        progressBar = view.findViewById(R.id.progressMain);
        imageSearchBook = view.findViewById(R.id.imageSearchBook);
    }

    private void searchBook(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showBooks(clearBooks);
                viewModel.pesquisarLivro(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showBooks(List<BooksResponse> booksResponseList){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new BooksAdapter(this, booksResponseList);
        recyclerLivros.setLayoutManager(layoutManager);
        recyclerLivros.setAdapter(adapter);
        recyclerLivros.setHasFixedSize(true);

    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void hideImageSearchBook(){
        imageSearchBook.setVisibility(View.INVISIBLE);
    }

    private void showImageSearchBook(){
        imageSearchBook.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickFavorite(BooksResponse book, int position) {
        if (book.isFavorite()) {
            book.setFavorite(false);
            viewModel.removeFavorite(book);
        }
        else {
            book.setFavorite(true);
            viewModel.addFavorite(book);
        }
        adapter.notifyItemChanged(position);

    }
}
