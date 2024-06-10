package com.example.challenge.strategies;

import com.example.challenge.Enum.StatusServiceEnum;
import com.example.challenge.dto.ServiceStatusDto;
import com.example.challenge.exceptions.NotFoundException;
import com.example.challenge.exceptions.TechnicalException;
import com.example.challenge.service.ServiceStatusService;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServiceStatusCentralElectricStrategy implements StepStrategy {

    private final ServiceStatusService serviceStatusService;

    @Override
    public boolean executeStep(Long userId, Map<String, String> metadata) {
        if (userId == null || metadata == null) {
            log.error("UserId and metadata cannot be null");
            throw new IllegalArgumentException("UserId and metadata cannot be null");
        }
        try {
            log.info("[ServiceStatusStrategy] - Start getting bill status for user {}", userId);
            ServiceStatusDto serviceStatus = serviceStatusService.getServiceStatus(userId, metadata);
            log.info("[ServiceStatusStrategy] - End bill status for user {} is {}", userId, serviceStatus.toString());

            return isValidForClaim(serviceStatus);

        } catch (NotFoundException e) {
            log.info("Bill status not found for user {}", userId);
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error getting bill status for user {}", userId, e);
            throw new TechnicalException(e.getMessage());
        }
    }

    private static boolean isValidForClaim(ServiceStatusDto serviceStatus) {
        LocalDateTime fourHoursAgo = LocalDateTime.now().minusHours(4);

        return Objects.nonNull(serviceStatus) &&
                (StatusServiceEnum.INACTIVE).equals(serviceStatus.getStatus()) &&
                Objects.nonNull(serviceStatus.getInterruptedService()) &&
                serviceStatus.getInterruptedService().getInterruptedAt().isBefore(fourHoursAgo);
    }

}