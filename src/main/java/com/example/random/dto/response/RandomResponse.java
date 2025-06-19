package com.example.random.dto.response;

import com.example.random.dto.Results;

import java.util.List;

public class RandomResponse {

    private List<ResultResponse> results;

    public List<ResultResponse> getResults() {
        return results;
    }

    public void setResults(List<ResultResponse> results) {
        this.results = results;
    }
}
