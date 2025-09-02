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
import java.util.UUID;


@Service
public class RandomService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResultRepository resultRepository;

    public ResultResponse obtener() {
        // 1. Consumir la API externa
        ResponseEntity<Random> response = restTemplate.exchange(
                "https://randomuser.me/api",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Random>() {}
        );

        Random random = response.getBody();

        // 2. Mapear a ResultResponse (igual que hacías en el controlador)
        Results r = random.getResults().get(0); // asumo que trae al menos 1
        ResultResponse resultResponse = new ResultResponse();

        resultResponse.setDni(UUID.randomUUID().toString());
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

        // 3. Guardar en BD
        return resultRepository.save(resultResponse);
    }


    public ResultResponse guardar(ResultResponse resultResponse) {
        return resultRepository.save(resultResponse);
    }

    public ResultResponse buscarPorId(String  id){
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
