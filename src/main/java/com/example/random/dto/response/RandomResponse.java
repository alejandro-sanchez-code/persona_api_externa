package com.example.random.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RandomResponse {

    private List<ResultResponse> results;

}
