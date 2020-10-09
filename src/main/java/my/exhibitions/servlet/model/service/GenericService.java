package my.exhibitions.servlet.model.service;

import my.exhibitions.servlet.util.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, D> {
    void create(D dto);

    Optional<T> findById(Long id);

    List<T> findAll();

    Pageable<T> findAll(Integer pageId, Integer total);

    void update(Long entityId);

    void delete(Long id);
}
