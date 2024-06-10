package com.example.challenge.service;

import com.example.challenge.dto.BillStatusDto;
import com.example.challenge.exceptions.NotFoundException;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Override
    public BillStatusDto getBillStatus(Long id, Map<String, String> metadata) {
        try {
            return BillStatusDto.builder()
                    .id(id)
                    .validStatus(true)
                    .build();
        } catch (NotFoundException e) {
            throw new NotFoundException("User not found with id: " + id);
        }
    }
}
