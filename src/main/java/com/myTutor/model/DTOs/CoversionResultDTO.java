package com.myTutor.model.DTOs;

import java.math.BigDecimal;

public record CoversionResultDTO(String from, String to, BigDecimal amount, BigDecimal result) {
}
