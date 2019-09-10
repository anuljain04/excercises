package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.SalesTaxRate;
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
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SALES_TAX_RATE_SQL =
            "insert into sales_tax_rate (state, rate) values (?, ?)";

    private static final String SELECT_SALES_TAX_RATE_SQL =
            "select * from sales_tax_rate where state = ?";

    private static final String SELECT_ALL_SALES_TAX_RATES_SQL =
            "select * from sales_tax_rate";

    private static final String UPDATE_SALES_TAX_RATE_SQL =
            "update sales_tax_rate set state = ?, rate = ? ";

    private static final String DELETE_SALES_TAX_RATE_SQL =
            "delete from sales_tax_rate where state =  ?";

    @Autowired
    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public SalesTaxRate addSalesTaxRate(SalesTaxRate salesTaxRate) {

        jdbcTemplate.update(
                INSERT_SALES_TAX_RATE_SQL,
                salesTaxRate.getState(),
                salesTaxRate.getRate());

//        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//
//        salesTaxRate.setState(id);

        return salesTaxRate;

    }

    @Override
    public SalesTaxRate getSalesTaxRate(String state) {

        try {
            return jdbcTemplate.queryForObject(
                    SELECT_SALES_TAX_RATE_SQL,
                    this::mapRowToSalesTaxRate,
                    state);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<SalesTaxRate> getAllSalesTaxRates() {

        return jdbcTemplate.query(
                SELECT_ALL_SALES_TAX_RATES_SQL,
                this::mapRowToSalesTaxRate);
    }

    @Override
    public void updateSalesTaxRate(SalesTaxRate salesTaxRate) {

        jdbcTemplate.update(
                UPDATE_SALES_TAX_RATE_SQL,
                salesTaxRate.getState(),
                salesTaxRate.getRate());
    }

    @Override
    public void deleteSalesTaxRate(String state) {

        jdbcTemplate.update(DELETE_SALES_TAX_RATE_SQL, state);

    }

    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getDouble("rate"));

        return salesTaxRate;
    }
}