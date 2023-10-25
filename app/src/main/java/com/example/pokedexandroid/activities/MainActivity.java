package com.example.pokedexandroid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pokedexandroid.R;
import com.example.pokedexandroid.models.Pokemon;
import com.example.pokedexandroid.models.PokemonList;
import com.example.pokedexandroid.pokeApi.PokemonServices;
import com.example.pokedexandroid.viewModel.ListPokemonAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEMON";
    private RecyclerView recyclerView;
    private ListPokemonAdapter listPokemonAdapter;

    private int offset;

    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        listPokemonAdapter = new ListPokemonAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy >= 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if (aptoParaCargar) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            Toast.makeText(MainActivity.this, "Cargando Pokemones!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Se llegó al final de la pokedex");
                            aptoParaCargar = false;
                            offset += 40;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {
        PokemonServices services = retrofit.create(PokemonServices.class);
        Call<PokemonList> pokemonRespuestaCall = services.obtenerListaPokemon(40, offset);
        pokemonRespuestaCall.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {
                    PokemonList pokemonRespuesta = response.body();
                    Log.d("TAG", response.body().getResults().toString());
                    List<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    listPokemonAdapter.adicionarListPokemon(listaPokemon);
                } else {
                    Log.e(TAG, "on response " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, "on Failure" + t.getMessage());
            }
        });
    }
}
