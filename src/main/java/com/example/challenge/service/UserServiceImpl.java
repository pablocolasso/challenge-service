package com.example.challenge.service;

import com.example.challenge.exceptions.NotFoundException;
import com.example.challenge.dto.UserDto;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getUserValidation(Long id, Map<String, String> metadata) {
        try {
            return UserDto.builder()
                    .id(id)
                    .name("John")
                    .email("john@gmail.com")
                    .phone("1234567890")
                    .build();
        } catch (NotFoundException e) {
            throw new NotFoundException("User not found with id: " + id);
        }
    }
}
