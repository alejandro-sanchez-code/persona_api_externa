package com.example.random.repository;

import com.example.random.dto.response.ResultResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<ResultResponse, String> {



}
