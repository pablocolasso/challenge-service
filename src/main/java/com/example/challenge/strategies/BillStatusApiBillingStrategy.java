package com.example.challenge.strategies;

import com.example.challenge.dto.BillStatusDto;
import com.example.challenge.exceptions.NotFoundException;
import com.example.challenge.exceptions.TechnicalException;
import com.example.challenge.service.BillService;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BillStatusApiBillingStrategy implements StepStrategy {

    private final BillService billService;

    @Override
    public boolean executeStep(Long userId, Map<String, String> metadata) {
        if (userId == null || metadata == null) {
            log.error("UserId and metadata cannot be null");
            throw new IllegalArgumentException("UserId and metadata cannot be null");
        }
        try {
            log.info("[BillStatusApiBillingStrategy] - Start getting bill status for user {}", userId);
            BillStatusDto billStatus = billService.getBillStatus(userId, metadata);
            log.info("[BillStatusApiBillingStrategy] - End bill status for user {} is {}", userId, billStatus);

            return Objects.nonNull(billStatus) && billStatus.getValidStatus();

        } catch (NotFoundException e) {
            log.info("Bill status not found for user {}", userId);
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error getting bill status for user {}", userId, e);
            throw new TechnicalException(e.getMessage());
        }
    }

}