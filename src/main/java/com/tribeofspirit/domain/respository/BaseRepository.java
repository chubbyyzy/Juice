package com.tribeofspirit.domain.respository;

/**
 * Author : gonwang
 * Create time : 31.10.2015.
 */
public interface BaseRepository<T> {

    void saveOrUpdate(T bean);

    T get(Long id);

    void delete(Long toDeleteId);
}
