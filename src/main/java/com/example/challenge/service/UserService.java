package com.example.challenge.service;

import com.example.challenge.dto.UserDto;
import java.util.Map;

public interface UserService {

    UserDto getUserValidation(Long id, Map<String, String> metadata);
}
