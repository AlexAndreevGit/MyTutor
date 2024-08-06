package com.myTutor.service.impl;

import com.myTutor.config.ForexApiConfig;
import com.myTutor.model.DTOs.ExRatesDTO;
import com.myTutor.model.entity.ExRateEntity;
import com.myTutor.repo.ExRateRepository;
import com.myTutor.service.ExRateService;
import com.myTutor.service.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

//REST_5 define all the needed methods in the ExRateServiceImpl

@Service
public class ExRateServiceImpl implements ExRateService {

    private final Logger LOGGER = LoggerFactory.getLogger(ExRateServiceImpl.class);

    private final ExRateRepository exRateRepository;
    private final RestClient restClient;
    private final ForexApiConfig forexApiConfig;

    public ExRateServiceImpl(ExRateRepository exRateRepository, RestClient restClient, ForexApiConfig forexApiConfig) {
        this.exRateRepository = exRateRepository;
        this.restClient = restClient;
        this.forexApiConfig = forexApiConfig;
    }

    @Override
    public boolean hasInitialisedExRates() {
        return exRateRepository.count() > 0;
    }

    //with the restClent we send a http query and we get a JSON response
    @Override
    public ExRatesDTO fetchExRates() {
        return restClient
                .get() //we want that the restClient make a get query
                .uri(forexApiConfig.getUrl(), forexApiConfig.getKey()) //make the get query to this url. (Replace "app_id" with the key)
                .accept(MediaType.APPLICATION_JSON)// we tell to the website "oppenExchangeAPI" that we want the response to be in a Json format
                .retrieve() // we call the "retrieve()" method . Then step_1. in "img->Rest_Explaind" is happening and we wait for step_2.
                .body(ExRatesDTO.class); //
    }
    //Postman -> https://openexchangerates.org/api/latest.json?app_id=e1c2f30d79644bc798c585b49408fbfc&prettyprint=true&show_alternative=false

    @Override
    public void updateRates(ExRatesDTO exRatesDTO) {
        LOGGER.info("Updating {} rates.", exRatesDTO.rates().size());

        if (!forexApiConfig.getBase().equals(exRatesDTO.base())) {
            throw new IllegalArgumentException("the exchange rates that should be updated are not based on " + forexApiConfig.getBase() + "but rather on" + exRatesDTO.base());
        }

        exRatesDTO.rates().forEach((currency, rate) -> {   // go through the whole "currency, rate" map

            ExRateEntity exRateEntity = new ExRateEntity();

            ExRateEntity exRateEntity1 = exRateRepository.findByCurrency(currency).orElse(null);  // if we have such entyty take the existing one
            //ExRateEntity exRateEntity = exRateRepository.findByCurrency(currency).orElse(null);

            if (exRateEntity1 == null) {
                ExRateEntity exRateEntity2 = new ExRateEntity();      //create new entity if we don have one
                exRateEntity2.setRate(rate);
                exRateEntity2.setCurrency(currency);
                exRateEntity = exRateEntity2;
            }

            //if it existed in the database the update the rate
            exRateEntity.setRate(rate);

            //save in the database
            exRateRepository.save(exRateEntity);

        });

    }

    @Override
    public Optional<BigDecimal> findExRate(String from, String to) {

        if (Objects.equals(from, to)) {
            return Optional.of(BigDecimal.ONE);
        }

        //USD/BGN=x
        //USD/EUR=y

        //USD = x * BGN
        //USD = y * EUR

        //EUR/BGN = x / y

        //if the base currency then return 1
        Optional<BigDecimal> fromOpt = forexApiConfig.getBase().equals(from) ?
                Optional.of(BigDecimal.ONE) :
                exRateRepository.findByCurrency(from).map(ExRateEntity::getRate);

        Optional<BigDecimal> toOpt = forexApiConfig.getBase().equals(to) ?
                Optional.of(BigDecimal.ONE) :
                exRateRepository.findByCurrency(to).map(ExRateEntity::getRate);

        if (fromOpt.isEmpty() || toOpt.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(toOpt.get().divide(fromOpt.get(), 2, RoundingMode.HALF_DOWN));
        }
    }

    @Override
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        return findExRate(from, to)
                .orElseThrow(() -> new ObjectNotFoundException("Conversion from " + from + "to" + to + "not possible."))
                .multiply(amount);
    }

}
