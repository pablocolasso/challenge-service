package com.example.challenge.service;

import com.example.challenge.dto.ClaimDto;
import com.example.challenge.dto.ClaimResponseDto;

public interface StepsService {
    ClaimResponseDto registerClaim(ClaimDto claim);
}
