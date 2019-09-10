package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.Game;
import com.example.KhoaLeU1Capstone.model.Tshirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TshirtDaoJdbcTemplateImpl implements TshirtDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_T_SHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";

    private static final String SELECT_T_SHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_ALL_T_SHIRTS_SQL =
            "select * from t_shirt";

    private static final String  SELECT_T_SHIRTS_BY_COLOR_SQL =
            "select * from t_shirt where color = ?";

    private static final String  SELECT_T_SHIRTS_BY_SIZE_SQL =
            "select * from t_shirt where size = ?";

    private static final String UPDATE_T_SHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";

    private static final String DELETE_T_SHIRT_SQL =
            "delete from t_shirt where t_shirt_id =  ?";

    @Autowired
    public TshirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Tshirt addTshirt(Tshirt tshirt) {

        jdbcTemplate.update(
                INSERT_T_SHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getTshirtPrice(),
                tshirt.getTshirtQuantity());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        tshirt.setTshirtId(id);

        return tshirt;

    }

    @Override
    public Tshirt getTshirt(int id) {

        try {
            return jdbcTemplate.queryForObject(
                    SELECT_T_SHIRT_SQL,
                    this::mapRowToTshirt,
                    id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Tshirt> getAllTshirts() {

        return jdbcTemplate.query(
                SELECT_ALL_T_SHIRTS_SQL,
                this::mapRowToTshirt);
    }

    @Override
    public List<Tshirt> getTshirtsByColor(String color) {

        return jdbcTemplate.query(
                SELECT_T_SHIRTS_BY_COLOR_SQL,
                this::mapRowToTshirt,
                color);
    }

    @Override
    public List<Tshirt> getTshirtsBySize(String size) {

        return jdbcTemplate.query(
                SELECT_T_SHIRTS_BY_SIZE_SQL,
                this::mapRowToTshirt,
                size);
    }

    @Override
    public void updateTshirt(Tshirt tshirt) {

        jdbcTemplate.update(
                UPDATE_T_SHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getTshirtPrice(),
                tshirt.getTshirtQuantity(),
                tshirt.getTshirtId());
    }

    @Override
    public void deleteTshirt(int tshirtId) {

        jdbcTemplate.update(DELETE_T_SHIRT_SQL, tshirtId);

    }

    private Tshirt mapRowToTshirt(ResultSet rs, int rowNum) throws SQLException {
        Tshirt tshirt = new Tshirt();
        tshirt.setTshirtId(rs.getInt("t_shirt_id"));
        tshirt.setSize(rs.getString("size"));
        tshirt.setColor(rs.getString("color"));
        tshirt.setDescription(rs.getString("description"));
        tshirt.setTshirtPrice(rs.getDouble("price"));
        tshirt.setTshirtQuantity(rs.getInt("quantity"));

        return tshirt;
    }
}