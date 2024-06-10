package com.example.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
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
public class StepDto {
    private String name;
    @JsonProperty(value = "target_system")
    private String targetSystem;
    private Map<String, String> metadata;

}
