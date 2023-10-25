package com.example.pokedexandroid.models.pokemonDetails;

import java.util.List;

public class Move {
    private Species move;
    private List<VersionGroupDetail> version_group_details;

    public Species getMove() {
        return move;
    }

    public void setMove(Species move) {
        this.move = move;
    }

// Agregar getters y setters
}
