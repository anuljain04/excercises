package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.Game;

import java.util.List;

public interface GameDao {

    Game addGame(Game game);

    Game getGame(int gameId);

    List<Game> getAllGames();

    List<Game> getGamesByTitle(String title);

    List<Game> getGamesByEsrbRating(String esrbRating);

    List<Game> getGamesByStudio(String studio);

    void updateGame(Game game);

    void deleteGame(int gameId);
}
