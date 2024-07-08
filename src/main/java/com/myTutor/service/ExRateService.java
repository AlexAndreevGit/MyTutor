package com.myTutor.service;


import com.myTutor.model.DTOs.ExRatesDTO;

import java.math.BigDecimal;
import java.util.Optional;

public interface ExRateService {

    boolean hasInitialisedExRates();

    ExRatesDTO fetchExRates();

    void updateRates(ExRatesDTO exRatesDTO);

    Optional<BigDecimal> findExRate(String from, String to);

    BigDecimal convert(String from, String to, BigDecimal amount);
}
