package com.example.pokedexandroid.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexandroid.R;
import com.example.pokedexandroid.activities.DetailActivity;
import com.example.pokedexandroid.models.Pokemon;

import java.util.List;

public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    private List<Pokemon> dataset;
    private Context context;

    public ListPokemonAdapter(Context context, List<Pokemon> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pokemonNumber = position + 1;
        Pokemon pokemon = dataset.get(position);
        holder.nombreTextView.setText(pokemon.getName());
        holder.number.setText(String.valueOf(pokemonNumber));

        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonNumber + ".png";
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();


                bundle.putString("idPokemon", String.valueOf(pokemonNumber));
                bundle.putString("name", String.valueOf(pokemon.getName()));
                bundle.putString("imageUrl", imageUrl);

                Intent iconIntent = new Intent(view.getContext(), DetailActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView nombreTextView;

        private TextView number;
        private CardView tarjeta;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = itemView.findViewById(R.id.FotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            number = itemView.findViewById(R.id.number);
            tarjeta = itemView.findViewById(R.id.tarjeta);

        }
    }

    public void adicionarListPokemon(List<Pokemon> listPokemon) {
        dataset.addAll(listPokemon);
        notifyDataSetChanged();
    }
}
