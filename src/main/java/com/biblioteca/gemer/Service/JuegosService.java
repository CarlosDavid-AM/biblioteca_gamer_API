package com.biblioteca.gemer.Service;

import com.biblioteca.gemer.Enums.APIError;
import com.biblioteca.gemer.Enums.EstadoEnum;
import com.biblioteca.gemer.Enums.PlataformaEnum;
import com.biblioteca.gemer.Exceptions.JuegosExceptions;
import com.biblioteca.gemer.Model.Juegos;
import com.biblioteca.gemer.Repository.JuegosRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JuegosService {

    private final JuegosRepository repository;

    @Autowired
    public JuegosService(JuegosRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Get all registered games.")
    public List<Juegos> getAllGames() {
        return repository.findAll();
    }

    public Juegos getGameById(Long id) {
        Optional<Juegos> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new JuegosExceptions(APIError.GAME_NOT_FOUND);
        }

        return result.get();
    }

    public Juegos saveGame(Juegos juegos) {
        if (Objects.nonNull(juegos.getId())) {
            throw new JuegosExceptions(APIError.GAME_WITH_SAME_ID);
        }

        return repository.save(juegos);
    }

    public Juegos updateGame(Juegos juegos, Long id) {
        if (getGameById(id) == null) {
            throw new JuegosExceptions(APIError.GAME_NOT_FOUND);
        }

        juegos.setId(id);

        return repository.save(juegos);
    }

    @Tool(description = "Deleted one registered game.")
    public void deleteGame(Long id) {
        if (getGameById(id) == null) {
            throw new JuegosExceptions(APIError.GAME_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    // Custom Methods
    @Tool(description = "Get all games registered by your state.")
    public List<Juegos> getGamesByEstate(EstadoEnum estadoEnum) {
        return repository.findByEstado(estadoEnum);
    }

    @Tool(description = "Get all the games registered by your platform.")
    public List<Juegos> getGamesByPlatform(PlataformaEnum plataforma) {
        return repository.findByPlataforma(plataforma);
    }

    @Tool(description = "Get all games registered by state and by platform.")
    public List<Juegos> getGamesByEstateAndPlatform(EstadoEnum estado, PlataformaEnum plataforma) {
        return repository.findByEstadoAndPlataforma(estado, plataforma);
    }

}
