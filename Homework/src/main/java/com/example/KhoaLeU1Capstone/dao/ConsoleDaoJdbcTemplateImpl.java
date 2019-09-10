package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CONSOLE_SQL =
            "insert into console (model, manufacturer, memory_amount, processor, price, quantity) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_CONSOLE_SQL =
            "select * from console where console_id = ?";

    private static final String SELECT_ALL_CONSOLES_SQL =
            "select * from console";

    private static final String  SELECT_CONSOLES_BY_MANUFACTURER_SQL =
            "select * from console where manufacturer = ?";

    private static final String UPDATE_CONSOLE_SQL =
            "update console set model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ? where console_id = ?";

    private static final String DELETE_CONSOLE_SQL =
            "delete from console where console_id =  ?";

    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Console addConsole(Console console) {

        jdbcTemplate.update(
                INSERT_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getConsolePrice(),
                console.getConsoleQuantity());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        console.setConsoleId(id);

        return console;

    }

    @Override
    public Console getConsole(int id) {

        try {
            return jdbcTemplate.queryForObject(
                    SELECT_CONSOLE_SQL,
                    this::mapRowToConsole,
                    id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Console> getAllConsoles() {

        return jdbcTemplate.query(
                SELECT_ALL_CONSOLES_SQL,
                this::mapRowToConsole);
    }

    @Override
    public List<Console> getConsolesByManufacturer(String manufacturer) {

        return jdbcTemplate.query(
                SELECT_CONSOLES_BY_MANUFACTURER_SQL,
                this::mapRowToConsole,
                manufacturer);
    }

    @Override
    public void updateConsole(Console console) {

        jdbcTemplate.update(
                UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getConsolePrice(),
                console.getConsoleQuantity(),
                console.getConsoleId());
    }

    @Override
    public void deleteConsole(int consoleId) {

        jdbcTemplate.update(DELETE_CONSOLE_SQL, consoleId);

    }

    private Console mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Console console = new Console();
        console.setConsoleId(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemoryAmount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setConsolePrice(rs.getDouble("price"));
        console.setConsoleQuantity(rs.getInt("quantity"));

        return console;
    }
}
