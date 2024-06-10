package com.example.challenge.strategies;

import com.example.challenge.dto.UserDto;
import com.example.challenge.exceptions.NotFoundException;
import com.example.challenge.exceptions.TechnicalException;
import com.example.challenge.service.UserService;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserStrategy implements StepStrategy {

    private final UserService userService;

    @Override
    public boolean executeStep(Long userId, Map<String, String> metadata) {

        if (userId == null || metadata == null) {
            log.error("UserId and metadata cannot be null");
            throw new IllegalArgumentException("UserId and metadata cannot be null");
        }
        try {
            log.info("[UserStrategy] - Start getting user for userId {}", userId);
            UserDto userDto = userService.getUserValidation(userId, metadata);
            log.info("[UserStrategy] - End getting user for userId {} is {}", userId, userDto != null ? userDto.toString() : null);

            return Objects.nonNull(userService.getUserValidation(userId, metadata));

        } catch (NotFoundException e) {
            log.info("User not found for userId {}", userId);
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error getting user for userId {}", userId, e);
            throw new TechnicalException(e.getMessage());
        }
    }

}