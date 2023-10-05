package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.LogDTO;

public interface LogService {
    LogDTO saveLog(LogDTO logDTO);
}
