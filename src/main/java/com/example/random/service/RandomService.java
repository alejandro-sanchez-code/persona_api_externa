package com.example.random.service;

import com.example.random.dto.Random;
import com.example.random.dto.Results;
import com.example.random.dto.response.RandomResponse;
import com.example.random.dto.response.ResultResponse;
import com.example.random.repository.ResultRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class RandomService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResultRepository resultRepository;

    public Random obtener(){
        ResponseEntity<Random> response = restTemplate
                .exchange("https://randomuser.me/api",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Random>() {
                        });
        return response.getBody();
    }

    public ResultResponse guardar(ResultResponse resultResponse){
        return resultRepository.save(resultResponse);
    }

    public ResultResponse buscarPorId(Integer id){
        return resultRepository.findById(id).orElse(null);
    }
    public List<ResultResponse> listarTodo (){
        return resultRepository.findAll();
    }
    public List<ResultResponse> buscarGenero (String genero){
        List <ResultResponse> listaGenero = new ArrayList<>();

        List<ResultResponse> lista = listarTodo();
        for (ResultResponse i : lista){
            if(i.getGenero().equals(genero)){
                listaGenero.add(i);
            }
        }
        return listaGenero;
    }

    public List<ResultResponse> listarPorEdad(Integer edad){
        List<ResultResponse> listaEdad = new ArrayList<>();
        List<ResultResponse> listarTodo = listarTodo();

        for (ResultResponse l: listarTodo){
            if (l.getEdad() >= edad){
                listaEdad.add(l);
            }
        }
        return listaEdad;
    }
}
