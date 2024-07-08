package com.myTutor.model.DTOs;

import java.math.BigDecimal;
import java.util.Map;

public record ExRatesDTO(String base, Map<String, BigDecimal> rates) {

    //encapsulation of the whole exchange rate
    //in this record we will have a base currency as USD and a map "currency" : "exchange rate"

    /*

    {
            "base": "USD",
            "rates": {
        "AED": 3.673,
                ...
                "BGN": 1.824663,
                ...
                "EUR": 0.932821,
                ...
        }
    }

   */
}
