package org.example.marketObserver;

import org.example.marketObserver.company.CompanyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.BlockingQueue;

@Component
public class ProducerQueue {

    private BlockingQueue companyQueue;
    private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(ProducerQueue.class);

    @Autowired
    public ProducerQueue(BlockingQueue companyQueue, RestTemplate restTemplate) {
        this.companyQueue = companyQueue;
        this.restTemplate = restTemplate;
    }

    public void fillQueue() {
        CompanyInfo[] CompaniesInfo = restTemplate.getForEntity("https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571"
                , CompanyInfo[].class).getBody();
        log.info(CompaniesInfo[0].toString());
        log.info("Размер есть " + CompaniesInfo.length);
        try {
            for (CompanyInfo companyInfo : CompaniesInfo) {
                if (companyInfo.isEnabled()) {
                    companyQueue.add(companyInfo.getSymbol());
                    System.out.println("Положили компанию в очередь - " + companyInfo.getSymbol());
                }

            }
        }
        catch (Exception e){
            log.error("Ошибка" + e.getMessage());

        }
    }


}
