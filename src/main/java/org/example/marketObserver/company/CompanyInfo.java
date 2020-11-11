package org.example.marketObserver.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyInfo {

    public String symbol;
    public Boolean isEnabled;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "symbol='" + symbol + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }


}
