package com.example.KhoaLeU1Capstone.viewmodel;

import java.util.Objects;

public class GameViewModel {

    private int gameId;
    private String title;
    private String esrbRating;
    private String description;
    private double gamePrice;
    private String studio;
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
        GameViewModel that = (GameViewModel) o;
        return getGameId() == that.getGameId() &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getEsrbRating(), that.getEsrbRating()) &&
                Objects.equals(getGamePrice(), that.getGamePrice()) &&
                Objects.equals(getStudio(), that.getStudio()) &&
                Objects.equals(getGameQuantity(), that.getGameQuantity());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getEsrbRating(), getGamePrice(), getStudio(), getGameQuantity()
        );
    }
}
