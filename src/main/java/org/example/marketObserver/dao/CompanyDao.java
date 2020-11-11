package org.example.marketObserver.dao;

import org.example.marketObserver.company.CompanyInfo;
import org.example.marketObserver.company.PriceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CompanyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void update(PriceInfo priceInfo){
        jdbcTemplate.update("INSERT INTO companies(companyName, volume, latestPrice) VALUES (? , ?, ?) ON DUPLICATE KEY UPDATE volume = ?, latestPrice = ?", priceInfo.getCompanyName(),priceInfo.getPreviousVolume(),priceInfo.getLatestPrice(),priceInfo.getPreviousVolume(),priceInfo.getLatestPrice());
        System.out.println("Положили в бд");
    }
    public List<PriceInfo> getCompanies(){
        return jdbcTemplate.query("SELECT companyName, volume, latestPrice FROM companies ORDER BY volume DESC LIMIT 5", new BeanPropertyRowMapper(PriceInfo.class));
    }


}
