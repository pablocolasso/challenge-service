package com.example.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClaimDto {
    @JsonProperty(value = "claim_id")
    private String claimId;
    @JsonProperty(value = "user_id")
    private Long userId;
    private List<StepDto> steps;

}