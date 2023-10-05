package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.LogDTO;
import com.example.accessingdatamysql.entity.Log;
import com.example.accessingdatamysql.repository.LogRepository;
import com.example.accessingdatamysql.transformers.LogTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogTransformer logTransformer;

    @Override
    public LogDTO saveLog(LogDTO logDTO) {
        Log log = logTransformer.fromDTO(logDTO);
        Log savedLog = logRepository.save(log);
        return logTransformer.fromEntity(savedLog);
    }
}
