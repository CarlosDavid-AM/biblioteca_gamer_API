package com.biblioteca.gemer.Mappers;

import com.biblioteca.gemer.DTO.JuegosDTO;
import com.biblioteca.gemer.Model.Juegos;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListJuegosMapper extends Converter<List<Juegos>, List<JuegosDTO>>{

    @Override
    List<JuegosDTO> convert(List<Juegos> source);
}
