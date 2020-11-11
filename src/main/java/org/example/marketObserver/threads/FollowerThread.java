package org.example.marketObserver.threads;

import org.example.marketObserver.company.CompanyInfo;
import org.example.marketObserver.company.PriceInfo;
import org.example.marketObserver.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BlockingQueue;

@Component
public class FollowerThread implements Runnable {

    @Autowired
    private BlockingQueue<String> companyQueue;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyDao companyDao;
    @Override
    public void run() {
        try {
            while(!companyQueue.isEmpty()) {
                String companySymbol = companyQueue.take();
                PriceInfo priceInfo = restTemplate.getForEntity(
                        "https://sandbox.iexapis.com/stable/stock/" +
                                companySymbol +
                                "/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571"
                        , PriceInfo.class).getBody();
                System.out.println("Информация о компании: ");
                System.out.println(priceInfo.toString());

                companyDao.update(priceInfo);
                System.out.println("------------------------------------------------------->");


            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
