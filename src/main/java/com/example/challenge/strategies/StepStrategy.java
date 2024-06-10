package com.example.challenge.strategies;

import java.util.Map;

public interface StepStrategy {
    boolean executeStep(Long userId, Map<String, String> metadta);
}
