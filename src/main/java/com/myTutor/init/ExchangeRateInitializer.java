package com.myTutor.init;

import com.myTutor.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//We need the component annotation so spring knows what to do with it
//CommandLineRunner is an interface from spring boot
//The commondLineRunner ist started once when the application is started
@Component
public class ExchangeRateInitializer implements CommandLineRunner {


    private final ExRateService exRateService;

    public ExchangeRateInitializer(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    //the run method is called so we initialize the exchange rates
    @Override
    public void run(String... args) throws Exception {

        //if no initialised exchange rates then fetch the information through REST
//        if (!exRateService.hasInitialisedExRates()){
//            exRateService.updateRates(exRateService.fetchExRates());
//        }
    }

}
