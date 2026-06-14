package com.biblioteca.gemer.Mappers;

import com.biblioteca.gemer.DTO.JuegosDTO;
import com.biblioteca.gemer.Model.Juegos;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface JuegosDTOMapper extends Converter<JuegosDTO, Juegos> {

    @Override
    Juegos convert(JuegosDTO source);
}
