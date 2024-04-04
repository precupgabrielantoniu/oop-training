package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.LogDTO;
import com.example.accessingdatamysql.entity.Log;
import com.example.accessingdatamysql.repository.LogRepository;
import com.example.accessingdatamysql.transformers.LogTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.sql.Date;
import java.time.DateTimeException;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogTransformer logTransformer;

    @Override
    public LogDTO saveLog(LogDTO logDTO) throws InvalidAttributesException {
        if(logDTO.getMessage() == null){
            throw new IllegalArgumentException("Logger message is null.");
        }
        if(logDTO.getMessage() == ""){
            throw new InvalidAttributesException("Logger message is empty.");
        }
        if(logDTO.getDate().after(new Date(2025, 3, 24))){
            throw new DateTimeException("Data is after 24/03/2025");
        }
        Log log = logTransformer.fromDTO(logDTO);
        Log savedLog = logRepository.save(log);
        return logTransformer.fromEntity(savedLog);
    }
}
