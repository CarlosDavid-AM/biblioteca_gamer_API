package com.biblioteca.gemer.Controller;

import com.biblioteca.gemer.Enums.PlataformaEnum;
import com.biblioteca.gemer.Model.Juegos;
import com.biblioteca.gemer.Service.JuegosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@CrossOrigin
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

    @GetMapping("/juegos/{id}")
    public ResponseEntity<Juegos> obtenerJuegosPorId(@Min(1) @PathVariable Long id) {
        Juegos response = service.getGameById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/juegos/terminados")
    public ResponseEntity<List<Juegos>> obtenerJuegosTerminados() {
        List<Juegos> response = service.getFinishedGames();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/juegos/pendientes")
    public ResponseEntity<List<Juegos>> obtenerJuegosPendientes() {
        List<Juegos> response = service.getPendingGames();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/juegos/plataforma/{plataforma}")
    public ResponseEntity<List<Juegos>> obtenerJuegosPorPlataforma(@PathVariable PlataformaEnum plataforma) {
        List<Juegos> response = service.getGamesByPlatform(plataforma);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/juegos")
    public ResponseEntity<Juegos> guardarJuego(@Valid @RequestBody Juegos juegos) {
        Juegos response = service.saveGame(juegos);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/juegos/{id}")
    public ResponseEntity<Juegos> actualizarJuego(@Valid @RequestBody Juegos juegos, @Min(1) @PathVariable Long id) {
        Juegos response = service.updateGame(juegos, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/juegos/{id}")
    public ResponseEntity<Void> eliminarJuegosPorId(@Min(1) @PathVariable Long id) {
        service.deleteGame(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
