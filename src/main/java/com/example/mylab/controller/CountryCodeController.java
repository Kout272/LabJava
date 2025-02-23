package com.example.mylab.controller;

import com.example.mylab.service.CountryCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country-code")
public class CountryCodeController {
    @Autowired
    private CountryCodeService countryCodeService;

    @GetMapping("/code/{countryName}")
    public String getCode(@PathVariable String countryName) {
        return countryCodeService.getCodeByCountry(countryName);
    }

    @GetMapping("/country/{code}")
    public String getCountry(@PathVariable String code) {
        return countryCodeService.getCountryByCode(code);
    }
}