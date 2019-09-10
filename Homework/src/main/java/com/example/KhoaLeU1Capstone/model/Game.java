package com.example.KhoaLeU1Capstone.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class Game {

    @NotNull
    private int gameId;
    private String title;
    private String esrbRating;
    private String description;
    @Positive
    @NotNull
    private double gamePrice;
    private String studio;
    @Positive
    @NotNull
    private int gameQuantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(double gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getGameQuantity() {
        return gameQuantity;
    }

    public void setGameQuantity(int gameQuantity) {
        this.gameQuantity = gameQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getGameId() == game.getGameId() &&
                Objects.equals(getTitle(), game.getTitle()) &&
                Objects.equals(getEsrbRating(), game.getEsrbRating()) &&
                Objects.equals(getDescription(), game.getDescription()) &&
                Objects.equals(getGamePrice(), game.getGamePrice()) &&
                Objects.equals(getStudio(), game.getStudio()) &&
                Objects.equals(getGameQuantity(), game.getGameQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getEsrbRating(), getDescription(), getGamePrice(), getStudio(), getGameQuantity());
    }
}
