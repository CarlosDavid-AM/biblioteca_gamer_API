package com.biblioteca.gemer.Controller;

import com.biblioteca.gemer.Model.Juegos;
import com.biblioteca.gemer.Service.JuegosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JuegosController {

    private final JuegosService service;

    @Autowired
    public JuegosController(JuegosService service) {
        this.service = service;
    }

    @GetMapping("/juegos")
    public ResponseEntity<List<Juegos>> obtenerJuegos() {
        List<Juegos> response = service.getAllGames();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/juegos")
    public ResponseEntity<Juegos> guardarJuego(@RequestBody Juegos juegos) {
        Juegos response = service.saveGame(juegos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
