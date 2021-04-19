package com.godxvincent.httpreactivelearning.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Genera setters
@Data
// Genera un constructor con todos los atributos
@AllArgsConstructor
// Genera un constructor sin atributos
@NoArgsConstructor
public class GreetingRequest {

    private String name;
}
