package com.gmail.morovo1988.budjet.converters;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface GenericConverter<E, D> {

    default E createFromDto(D dto) {
        throw new UnsupportedOperationException("This method does not have implementation by default");
    }

    default D createFromEntity(E entity) {
        throw new UnsupportedOperationException("This method does not have implementation by default");
    }

    default void updateFromDto(D dto, E entity) {
        throw new UnsupportedOperationException("This method does not have implementation by default");
    }

    default List<D> createFromEntities(final List<E> entities) {
        return entities.stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }

    default List<E> createFromDtos(final List<D> dtos) {
        return dtos.stream()
                .map(this::createFromDto)
                .collect(Collectors.toList());
    }
}
