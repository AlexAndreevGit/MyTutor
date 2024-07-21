package com.myTutor.controller;

import com.myTutor.model.DTOs.CoversionResultDTO;
import com.myTutor.service.ExRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

//@RestController means that the object that is returned will be converted to JSON object
@RestController
public class CurrencyController {

    private final ExRateService exRateService;

    public CurrencyController(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @GetMapping("/api/convert")
    public ResponseEntity<CoversionResultDTO> convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount
    ){
        BigDecimal result = exRateService.convert(from, to, amount);


        return ResponseEntity.ok(new CoversionResultDTO(
                from,
                to,
                amount,
                result
        ));
    }


}
