package com.example.random.dto.response;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "usuarios_random")
@Entity
@Getter
@Setter
public class ResultResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String genero;
    private String telefono;
    private String celular;
    private String email;
    private Integer edad;
    private String ciudadYPais;
    private String direccion;
    private String codigoPostal;
    private String username;
    private String clave;
    private String zonaHoraria;

    private String valorNuevo;
}
