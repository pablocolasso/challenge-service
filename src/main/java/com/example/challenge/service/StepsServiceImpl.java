package com.example.challenge.service;

import com.example.challenge.dto.ClaimDto;
import com.example.challenge.dto.ClaimResponseDto;
import com.example.challenge.dto.StepDto;
import com.example.challenge.strategies.BillStatusApiBillingStrategy;
import com.example.challenge.strategies.BillStatusInternetProviderStrategy;
import com.example.challenge.strategies.ServiceStatusCentralElectricStrategy;
import com.example.challenge.strategies.StepStrategy;
import com.example.challenge.strategies.UserStrategy;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StepsServiceImpl implements StepsService {

    private final Map<String, StepStrategy> strategyMap = new HashMap<>();

    private final UserStrategy userValidationApiUsersStrategy;
    private final BillStatusApiBillingStrategy billStatusApiBillingStrategy;
    private final BillStatusInternetProviderStrategy billStatusInternetProviderStrategy;
    private final ServiceStatusCentralElectricStrategy serviceStatusCentralElectricStrategy;

    public StepsServiceImpl(UserStrategy userValidationApiUsersStrategy,
                            BillStatusApiBillingStrategy billStatusApiBillingStrategy,
                            BillStatusInternetProviderStrategy billStatusInternetProviderStrategy,
                            ServiceStatusCentralElectricStrategy serviceStatusCentralElectricStrategy) {
        this.userValidationApiUsersStrategy = userValidationApiUsersStrategy;
        this.billStatusApiBillingStrategy = billStatusApiBillingStrategy;
        this.billStatusInternetProviderStrategy = billStatusInternetProviderStrategy;
        this.serviceStatusCentralElectricStrategy = serviceStatusCentralElectricStrategy;
        initializeStrategyMap();
    }

    @PostConstruct
    private void initializeStrategyMap() {
        strategyMap.put("user_validation-api_users", userValidationApiUsersStrategy);
        strategyMap.put("bill_status-api_billing", billStatusApiBillingStrategy);
        strategyMap.put("bill_status-internet_provider", billStatusInternetProviderStrategy);
        strategyMap.put("service_status-central_electric", serviceStatusCentralElectricStrategy);
    }

    @Override
    public ClaimResponseDto registerClaim(ClaimDto claim) {
        for (StepDto step : claim.getSteps()) {
            log.info("Executing step: {}", step.getName());
            String key = step.getName() + "-" + step.getTargetSystem();
            StepStrategy strategy = strategyMap.get(key);
            boolean result = strategy.executeStep(claim.getUserId(), step.getMetadata());
            log.info("Step {} executed with result: {}", step.getName(), result);
            if (!result) {
                return ClaimResponseDto.builder()
                        .claimId(claim.getClaimId())
                        .status("REJECTED")
                        .causaError("Step " + step.getName() + " and target system " + step.getTargetSystem() + " failed")
                        .build();
            }
        }
        log.info("Claim {} approved", claim.getClaimId());
        //Here we should save the claim in the database
        return ClaimResponseDto.builder()
                .claimId(claim.getClaimId())
                .status("APPROVED")
                .build();
    }

}
