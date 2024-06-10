package com.example.challenge.service;

import com.example.challenge.dto.BillStatusDto;
import com.example.challenge.dto.ServiceStatusDto;
import java.util.Map;

public interface ServiceStatusService {
    ServiceStatusDto getServiceStatus(Long id, Map<String, String> metadata);

}
