package org.example.marketObserver.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceInfo {
    private Integer volume;
    private Integer previousVolume;
    private String companyName;
    private Double latestPrice;

    @Override
    public String toString() {
        return "PriceInfo{" +
                "volume=" + volume +
                ", companyName='" + companyName + '\'' +
                ", latestPrice=" + latestPrice +
                '}';
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(Integer previousVolume) {
        this.previousVolume = previousVolume;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

}
