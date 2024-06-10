package com.example.challenge.controller;

import com.example.challenge.dto.ClaimDto;
import com.example.challenge.dto.ClaimResponseDto;
import com.example.challenge.service.StepsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Services Controller")
@RestController
@RequiredArgsConstructor
public class ServicesController {

    private final StepsService stepsService;

    @ApiOperation(value = "Registrar transacción")
    @PostMapping(path = "/claim", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClaimResponseDto> registrarTransaccion(@RequestBody ClaimDto claim) {
        return ResponseEntity.ok(stepsService.registerClaim(claim));
    }

}
