package com.funda.springboot.fundament.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping //anotacion para aceptar todas las solicitudes dentro del metodo a nivel de http
    @ResponseBody//responder un cuerpo a nivel de servicio
    public ResponseEntity<String> function() { //retorno de respuesta
      return new ResponseEntity<>("Hello From Controller", HttpStatus.OK);//mandamos un mensaje por el localhost de puerto 8081
    }
}
