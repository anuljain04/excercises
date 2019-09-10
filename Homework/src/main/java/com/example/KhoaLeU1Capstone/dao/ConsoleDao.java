package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.Console;

import java.util.List;

public interface ConsoleDao {

    Console addConsole(Console console);

    Console getConsole(int consoleId);

    List<Console> getAllConsoles();

    List<Console> getConsolesByManufacturer(String manufacturer);

    void updateConsole(Console console);

    void deleteConsole(int consoleId);
}
