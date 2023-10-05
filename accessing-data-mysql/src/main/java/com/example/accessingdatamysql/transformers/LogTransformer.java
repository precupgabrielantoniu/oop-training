package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.LogDTO;
import com.example.accessingdatamysql.entity.Log;
import org.springframework.stereotype.Component;

@Component
public class LogTransformer implements Transformer<Log, LogDTO> {

    @Override
    public LogDTO fromEntity(Log log) {
        LogDTO logDTO = new LogDTO();
        logDTO.setDate(log.getDate());
        logDTO.setMessage(logDTO.getMessage());
        return logDTO;
    }

    @Override
    public Log fromDTO(LogDTO logDTO) {
        Log log = new Log();
        log.setDate(logDTO.getDate());
        log.setMessage(logDTO.getMessage());
        return log;
    }
}
