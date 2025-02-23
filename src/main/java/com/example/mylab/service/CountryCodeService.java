package com.example.mylab.service;

import com.example.mylab.model.Country;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryCodeService {
    private List<Country> countryList;

    public CountryCodeService() {
        countryList = new ArrayList<>();
        loadCountryCodes();
    }

    private void loadCountryCodes() {
        String url = "https://www.ixbt.com/mobile/country_code.html";
        try {
            Document document = Jsoup.connect(url).get();
            Elements rows = document.select("table tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");
                if (columns.size() >= 3) {
                    String countryName = columns.get(1).text();
                    String countryCode = columns.get(2).text();
                    countryList.add(new Country(countryName, countryCode));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCodeByCountry(String countryName) {
        return countryList.stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName))
                .map(Country::getCode)
                .findFirst()
                .orElse(null);
    }

    public String getCountryByCode(String code) {
        return countryList.stream()
                .filter(country -> country.getCode().equals(code))
                .map(Country::getName)
                .findFirst()
                .orElse(null);
    }

    public List<Country> getAllCountries() {
        return countryList;
    }
}