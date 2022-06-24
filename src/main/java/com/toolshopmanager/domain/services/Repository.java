package com.toolshopmanager.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
    public List<T> findAll();

    public Optional<T> findById(UUID id);

    public void save(T entity);

    public void update(UUID id, T entity);

    public void delete(UUID id);
}
