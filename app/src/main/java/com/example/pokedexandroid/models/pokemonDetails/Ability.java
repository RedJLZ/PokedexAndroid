package com.example.pokedexandroid.models.pokemonDetails;

public class Ability {
    private Species ability;
    private boolean is_hidden;
    private int slot;

    public Species getAbility() {
        return ability;
    }

    public void setAbility(Species ability) {
        this.ability = ability;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}

