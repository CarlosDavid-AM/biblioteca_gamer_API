package com.biblioteca.gemer.Controller;

import com.biblioteca.gemer.DTO.JuegosDTO;
import com.biblioteca.gemer.Enums.EstadoEnum;
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

    @GetMapping("/juegos/estado/{estado}")
    public ResponseEntity<List<Juegos>> obtenerJuegosPorEstado(@PathVariable EstadoEnum estado) {
        List<Juegos> response = service.getGamesByEstate(estado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/juegos/plataforma/{plataforma}")
    public ResponseEntity<List<Juegos>> obtenerJuegosPorPlataforma(@PathVariable PlataformaEnum plataforma) {
        List<Juegos> response = service.getGamesByPlatform(plataforma);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/juegos/estado-plataforma/{estado}/{plataforma}")
    public ResponseEntity<List<Juegos>> obtenerJuegosPorEstadoAndPlataforma(@PathVariable EstadoEnum estado, @PathVariable PlataformaEnum plataforma) {
        List<Juegos> response = service.getGamesByEstateAndPlatform(estado, plataforma);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Se actualizó la llamada a service.saveGame dentro del endpoint POST para que coincida con la nueva firma del
     * metodo, extrayendo los datos del DTO recibido en el cuerpo de la petición.
     * Razón: Mantener la compatibilidad de tu API REST externa mientras el servicio interno ahora es compatible con las Tools de la IA.
     */
    @PostMapping("/juegos")
    public ResponseEntity<JuegosDTO> guardarJuego(@Valid @RequestBody JuegosDTO juegos) {
        JuegosDTO response = service.saveGame(juegos.getNombre(), juegos.getImagenUrl(), juegos.getPlataforma(), juegos.getEstado());

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
