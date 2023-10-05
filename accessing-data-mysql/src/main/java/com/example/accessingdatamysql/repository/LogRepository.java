package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {
}
