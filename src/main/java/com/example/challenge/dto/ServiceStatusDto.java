package com.example.challenge.dto;

import com.example.challenge.Enum.StatusServiceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceStatusDto {
    private Long idService;
    private Long userId;
    private StatusServiceEnum status;
    private InterruptedServiceDetail interruptedService;

}
