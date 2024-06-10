package com.example.challenge.service;

import com.example.challenge.dto.BillStatusDto;
import java.util.Map;

public interface BillService {
    BillStatusDto getBillStatus(Long id, Map<String, String> metadata);

}
