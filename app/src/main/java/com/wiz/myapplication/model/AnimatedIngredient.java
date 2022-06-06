package com.wiz.myapplication.model;

import com.wiz.myapplication.model.Ingredient;

public class AnimatedIngredient {
    Ingredient ingredient ;
    boolean played = false;
    int position ;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AnimatedIngredient(Ingredient ingredient, boolean played, int position) {
        this.ingredient = ingredient;
        this.played = played;
        this.position = position;
    }

    public Ingredient getIngredient() {
        return ingredient;

    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
}
