package com.example.pokedexandroid.models.pokemonDetails;

import java.util.List;
public class PokemonFull {

        private List<Ability> abilities;
        private int base_experience;
        private List<Species> forms;
        private List<GameIndex> game_indices;
        private float height;
        private List<Object> held_items;
        private int id;
        private boolean is_default;
        private String location_area_encounters;
        private List<Move> moves;
        private String name;
        private int order;
        private List<Object> past_abilities;
        private List<Object> past_types;
        private Species species;
        private Sprites sprites;
        private List<Stat> stats;
        private List<Type> types;
        private float weight;

        public PokemonFull(List<Ability> abilities, int base_experience, List<Species> forms, List<GameIndex> game_indices, float height, List<Object> held_items, int id, boolean is_default, String location_area_encounters, List<Move> moves, String name, int order, List<Object> past_abilities, List<Object> past_types, Species species, Sprites sprites, List<Stat> stats, List<Type> types, float weight) {
                this.abilities = abilities;
                this.base_experience = base_experience;
                this.forms = forms;
                this.game_indices = game_indices;
                this.height = height;
                this.held_items = held_items;
                this.id = id;
                this.is_default = is_default;
                this.location_area_encounters = location_area_encounters;
                this.moves = moves;
                this.name = name;
                this.order = order;
                this.past_abilities = past_abilities;
                this.past_types = past_types;
                this.species = species;
                this.sprites = sprites;
                this.stats = stats;
                this.types = types;
                this.weight = weight;
        }

        public List<Ability> getAbilities() {
                return abilities;
        }

        public void setAbilities(List<Ability> abilities) {
                this.abilities = abilities;
        }

        public int getBase_experience() {
                return base_experience;
        }

        public void setBase_experience(int base_experience) {
                this.base_experience = base_experience;
        }

        public List<Species> getForms() {
                return forms;
        }

        public void setForms(List<Species> forms) {
                this.forms = forms;
        }

        public List<GameIndex> getGame_indices() {
                return game_indices;
        }

        public void setGame_indices(List<GameIndex> game_indices) {
                this.game_indices = game_indices;
        }

        public float getHeight() {
                return height;
        }

        public void setHeight(float height) {
                this.height = height;
        }

        public List<Object> getHeld_items() {
                return held_items;
        }

        public void setHeld_items(List<Object> held_items) {
                this.held_items = held_items;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public boolean isIs_default() {
                return is_default;
        }

        public void setIs_default(boolean is_default) {
                this.is_default = is_default;
        }

        public String getLocation_area_encounters() {
                return location_area_encounters;
        }

        public void setLocation_area_encounters(String location_area_encounters) {
                this.location_area_encounters = location_area_encounters;
        }

        public List<Move> getMoves() {
                return moves;
        }

        public void setMoves(List<Move> moves) {
                this.moves = moves;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getOrder() {
                return order;
        }

        public void setOrder(int order) {
                this.order = order;
        }

        public List<Object> getPast_abilities() {
                return past_abilities;
        }

        public void setPast_abilities(List<Object> past_abilities) {
                this.past_abilities = past_abilities;
        }

        public List<Object> getPast_types() {
                return past_types;
        }

        public void setPast_types(List<Object> past_types) {
                this.past_types = past_types;
        }

        public Species getSpecies() {
                return species;
        }

        public void setSpecies(Species species) {
                this.species = species;
        }

        public Sprites getSprites() {
                return sprites;
        }

        public void setSprites(Sprites sprites) {
                this.sprites = sprites;
        }

        public List<Stat> getStats() {
                return stats;
        }

        public void setStats(List<Stat> stats) {
                this.stats = stats;
        }

        public List<Type> getTypes() {
                return types;
        }

        public void setTypes(List<Type> types) {
                this.types = types;
        }

        public float getWeight() {
                return weight;
        }

        public void setWeight(float weight) {
                this.weight = weight;
        }
}
