package com.example.pokedexandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexandroid.R;
import com.example.pokedexandroid.models.pokemonDetails.Ability;
import com.example.pokedexandroid.models.pokemonDetails.Move;
import com.example.pokedexandroid.models.pokemonDetails.PokemonFull;
import com.example.pokedexandroid.models.pokemonDetails.Species;
import com.example.pokedexandroid.models.pokemonDetails.Sprites;
import com.example.pokedexandroid.models.pokemonDetails.Type;
import com.example.pokedexandroid.pokeApi.PokemonServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    TextView nombre, tipo, numeroPokemon, peso, abilities, movimientos;
    ImageView fotoPokemon, sprites1, sprites2, sprites3, sprites4;
    ScrollView tarjetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nombre = findViewById(R.id.nombre);
        numeroPokemon = findViewById(R.id.idPokemon);
        tipo = findViewById(R.id.tipo);
        peso = findViewById(R.id.peso);
        abilities = findViewById(R.id.habilidades);
        movimientos = findViewById(R.id.movimientos);
        sprites1 = findViewById(R.id.Espíritus1);
        sprites2 = findViewById(R.id.Espíritus2);
        sprites3 = findViewById(R.id.Espíritus3);
        sprites4 = findViewById(R.id.Espíritus4);
        fotoPokemon = findViewById(R.id.fotoPokemon);
        tarjetas = findViewById(R.id.tarjetas);

        String idPokemon = (getIntent().getExtras().getString("idPokemon"));
        String name = (getIntent().getExtras().getString("name"));
        String imageUrl = (getIntent().getExtras().getString("imageUrl"));

        find(idPokemon, name, imageUrl);

    }

    private void find(String idPokemon, String name, String imageUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        PokemonServices pokemonServices = retrofit.create(PokemonServices.class);
        Call<PokemonFull> call = pokemonServices.find(idPokemon);
        call.enqueue(new Callback<PokemonFull>() {
            @Override
            public void onResponse(Call<PokemonFull> call, Response<PokemonFull> response) {
                try {
                    if (response.isSuccessful()) {
                        PokemonFull pokemon = response.body();
                        Sprites spritesList = pokemon.getSprites();
                        nombre.setText(name);
                        numeroPokemon.setText("#" + idPokemon);
                        tipo.setText(obtenerTipos(pokemon.getTypes()));
                        peso.setText(String.valueOf(pokemon.getWeight()));
                        abilities.setText(obtenerHabilidades(pokemon.getAbilities()));
                        movimientos.setText(obtenerMovimientos(pokemon.getMoves()));

                        Glide.with(getApplication()).load(imageUrl)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(fotoPokemon);

                        Glide.with(getApplication()).load(spritesList.getFront_default())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(sprites1);
                        Glide.with(getApplication()).load(spritesList.getBack_default())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(sprites2);
                        Glide.with(getApplication()).load(spritesList.getFront_shiny())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(sprites3);
                        Glide.with(getApplication()).load(spritesList.getBack_shiny())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(sprites4);
                    }
                } catch (Exception e) {
                    Toast.makeText(DetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<PokemonFull> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Error en el servicio", Toast.LENGTH_SHORT);
            }
        });
    }

    public String obtenerTipos(List<Type> pokemon) {
        List<Type> TypoPokemon = pokemon;
        String typePokemon = "Tipos: ";
        for (int i = 0; i < TypoPokemon.size(); i++) {
            Type type = TypoPokemon.get(i);
            Species specie = type.getType();
            typePokemon = typePokemon + " " + specie.getName();
        }
        return typePokemon;
    }

    public String obtenerHabilidades(List<Ability> pokemon) {
        List<Ability> abilityList = pokemon;
        String abilitiesNames = "Habilidades: ";
        for (int i = 0; i < abilityList.size(); i++) {
            Ability abilityName = abilityList.get(i);
            Species species = abilityName.getAbility();
            abilitiesNames = abilitiesNames + "  " + species.getName();
        }
        return abilitiesNames;
    }

    public String obtenerMovimientos(List<Move> pokemon) {
        List<Move> movesList = pokemon;
        String movimientosNames = "Movimientos: ";
        for (int i = 0; i < movesList.size(); i++) {
            Move moveName = movesList.get(i);
            Species species = moveName.getMove();
            movimientosNames = movimientosNames + "  " + species.getName();
        }
        return movimientosNames;
    }
}