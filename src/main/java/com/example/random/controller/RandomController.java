package com.example.random.controller;


import com.example.random.dto.Random;
import com.example.random.dto.Results;
import com.example.random.dto.response.ResultResponse;
import com.example.random.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomController {

    @Autowired
    private RandomService randomService;

    @GetMapping("/persona")
    public ResponseEntity<ResultResponse> get() {
        Random random = randomService.obtener();

        ResultResponse resultResponse = new ResultResponse();


        List<Results> rest = random.getResults();

        for (Results r : rest) {
            resultResponse.setGenero(r.getGender());
            resultResponse.setCelular(r.getCell());
            resultResponse.setEmail(r.getEmail());
            resultResponse.setTelefono(r.getPhone());
            resultResponse.setNat(r.getNat());
            resultResponse.setNombre(r.getName().getTitle() + " " + r.getName().getFirst());
            resultResponse.setCiudadYPais(r.getLocation().getStreet().getName()
                    + " " + r.getLocation().getStreet().getNumber()
                    + ", " + r.getLocation().getCity()
                    + ", " + r.getLocation().getCountry());
            resultResponse.setDireccion(r.getLocation().getStreet().getName());
            resultResponse.setCodigoPostal(r.getLocation().getPostcode());
            resultResponse.setUsername(r.getLogin().getUsername());
            resultResponse.setClave(r.getLogin().getPassword());
            resultResponse.setZonaHoraria(r.getLocation().getTimezone().getOffset()
                    + " || " + r.getLocation().getTimezone().getDescription());
            resultResponse.setEdad(r.getDob().getAge());
        }
        randomService.guardar(resultResponse);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResultResponse listar(@PathVariable Integer id) {
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
}