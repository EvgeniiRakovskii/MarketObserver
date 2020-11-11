package org.example.marketObserver.threads;

import org.example.marketObserver.company.CompanyInfo;
import org.example.marketObserver.company.PriceInfo;
import org.example.marketObserver.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
public class DisplayThread implements Runnable{

    @Autowired
    CompanyDao companyDao;

    @Override
    public void run() {
        List<PriceInfo> list = companyDao.getCompanies();
        System.out.println("5 самых дорогих компаний");
        for (PriceInfo priceInfo:list) {
            System.out.println(priceInfo.toString());
        }

    }
}
