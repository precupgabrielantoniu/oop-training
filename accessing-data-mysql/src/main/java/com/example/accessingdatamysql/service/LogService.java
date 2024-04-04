package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.LogDTO;

import javax.naming.directory.InvalidAttributesException;

public interface LogService {
    LogDTO saveLog(LogDTO logDTO) throws InvalidAttributesException;
}
