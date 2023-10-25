package com.example.pokedexandroid.pokeApi;

import com.example.pokedexandroid.models.PokemonList;
import com.example.pokedexandroid.models.pokemonDetails.PokemonFull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonServices {
    @GET("pokemon")
    Call<PokemonList> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("api/v2/pokemon/{id}")
    Call<PokemonFull> find(@Path("id") String id);
}
