package com.springtechnicaltest.dto.mapper;


import java.util.List;

public interface EntityMapper<T , D> {

    public  D entityToDto(T object);
    public  T dtoToEntity(D object);
    public List<D> listEntityToDto(List<T> list);
    public  List<T> listDtoToEntity(List<D> list);
}
