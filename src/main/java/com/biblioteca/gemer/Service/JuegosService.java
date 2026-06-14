package com.biblioteca.gemer.Service;

import com.biblioteca.gemer.DTO.JuegosDTO;
import com.biblioteca.gemer.Enums.APIError;
import com.biblioteca.gemer.Enums.EstadoEnum;
import com.biblioteca.gemer.Enums.PlataformaEnum;
import com.biblioteca.gemer.Exceptions.JuegosExceptions;
import com.biblioteca.gemer.Model.Juegos;
import com.biblioteca.gemer.Repository.JuegosRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JuegosService {

    private final JuegosRepository repository;
    private ConversionService conversionService;

    @Autowired
    public JuegosService(JuegosRepository repository, ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
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

    /**
     * Se define parámetros individuales en el metodo anotado con @Tool, Spring AI genera un esquema de función mucho
     * más claro para el modelo de IA (parámetros nombre, imagenUrl, etc., en lugar de un objeto juegos).
     * Esto asegura que la inyección de argumentos sea precisa y evita que Jackson intente deserializar una lista en un objeto único.
     */
    @Tool(description = "Create a new game. | If they don't say what game it is, then create any game with fake data.")
    public JuegosDTO saveGame(String nombre, String imagenUrl, PlataformaEnum plataforma, EstadoEnum estado) {
        JuegosDTO juegosDTO = new JuegosDTO();
        juegosDTO.setNombre(nombre);
        juegosDTO.setImagenUrl(imagenUrl);
        juegosDTO.setPlataforma(plataforma);
        juegosDTO.setEstado(estado != null ? estado : EstadoEnum.OBTENIDO);

        Juegos transformed = conversionService.convert(juegosDTO, Juegos.class);
        Juegos result = repository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(result, JuegosDTO.class);
    }

    @Tool(description = "Update an existing game.")
    public JuegosDTO updateGame(String nombre, String imagenUrl, PlataformaEnum plataforma, EstadoEnum estado, Long id) {
        getGameById(id);

        JuegosDTO juegosDTO = new JuegosDTO();
        juegosDTO.setNombre(nombre);
        juegosDTO.setImagenUrl(imagenUrl);
        juegosDTO.setPlataforma(plataforma);
        juegosDTO.setEstado(estado != null ? estado : EstadoEnum.OBTENIDO);

        Juegos transformed = conversionService.convert(juegosDTO, Juegos.class);
        Objects.requireNonNull(transformed).setId(id);
        Juegos result = repository.save(transformed);
        return conversionService.convert(result, JuegosDTO.class);
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
