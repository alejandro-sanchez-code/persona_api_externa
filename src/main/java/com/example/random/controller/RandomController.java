package com.example.random.controller;


import com.example.random.dto.Random;
import com.example.random.dto.Results;
import com.example.random.dto.response.ResultResponse;
import com.example.random.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RandomController {

    @Autowired
    private RandomService randomService;

    @GetMapping("/persona")
    public ResponseEntity<ResultResponse> get() {
        return new ResponseEntity<>(randomService.obtener(), HttpStatus.OK);
    }


    @GetMapping("/listar/{id}")
    public ResultResponse listar(@PathVariable String id) {
        return randomService.buscarPorId(id);
    }


    @GetMapping("/listar")
    public List<ResultResponse> listarTodo() {
        return randomService.listarTodo();
    }

    @GetMapping("/buscar/{genero}")
    public List<ResultResponse> buscarGenero(@PathVariable String genero) {
        return randomService.buscarGenero(genero);
    }

    @GetMapping("/filtrar/{edad}")
    public List<ResultResponse> buscarPorEdad(@PathVariable Integer edad) {
        return randomService.listarPorEdad(edad);
    }

    @PostMapping("/guardar")
    public ResultResponse guardar(@RequestBody ResultResponse resultResponse){
        return randomService.guardar(resultResponse);
    }
}