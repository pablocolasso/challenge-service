package com.example.challenge.service;

import com.example.challenge.Enum.StatusServiceEnum;
import com.example.challenge.dto.InterruptedServiceDetail;
import com.example.challenge.dto.ServiceStatusDto;
import com.example.challenge.exceptions.NotFoundException;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ServiceStatusServiceImpl implements ServiceStatusService {

    @Override
    public ServiceStatusDto getServiceStatus(Long id, Map<String, String> metadata) {
        try {
            return ServiceStatusDto.builder()
                    .idService(12345L)
                    .userId(id)
                    .status(StatusServiceEnum.INACTIVE)
                    .interruptedService(InterruptedServiceDetail.builder()
                            .reason("Maintenance")
                            .interruptedAt(LocalDateTime.now().minusHours(5))
                            .build())
                    .build();
        } catch (NotFoundException e) {
            throw new NotFoundException("User not found with id: " + id);
        }
    }
}
