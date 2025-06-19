package com.example.random.repository;

import com.example.random.dto.response.ResultResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository <ResultResponse, Integer> {



}
