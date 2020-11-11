package org.example.marketObserver;

import org.example.marketObserver.company.CompanyInfo;
import org.example.marketObserver.threads.DisplayThread;
import org.example.marketObserver.threads.FollowerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//implements CommandLineRunner
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {

    @Autowired
    private ProducerQueue producer;
    @Autowired
    private FollowerThread followerThread;

    @Autowired
    private DisplayThread displayThread;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, DataSource dataSource) throws Exception {
        return args -> {
            producer.fillQueue();
            Thread thread = new Thread(followerThread);
            thread.start();
            thread.join();
            thread = new Thread(displayThread);
            thread.start();
        };
    }

    @Bean
    public BlockingQueue<String> concurrentCollection() {
        return new ArrayBlockingQueue(50);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public DataSource mysqlDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost/stock?" +
//                "useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("feralkun");
////                jdbcTemplate.update("INSERT INTO companies(name, volume, price, previousPrice) VALUES (?, ?, ?, ?)", "test", 111, 1.1, 2.2);
//        return dataSource;
//    }
}
/*    @Bean
    public CommandLineRunner run(WebClient webClient) throws Exception {
        return args -> {
            CompanyInfo[] result = webClient.get().retrieve()
                    .bodyToMono(CompanyInfo[].class).block();

            log.info(result[0].toString());
            log.info("Размер есть " + result.length);
        };
    }*/
/*    @Bean
    public WebClient stockApiClient() {
       return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .baseUrl("https://sandbox.iexapis.com/stable/ref-data/symbols" +
                        "?token=Tpk_ee567917a6b640bb8602834c9d30e571")
                .build();
    }*/