package com.tribeofspirit.domain.service;

/**
 * Author : gonwang
 * Create time : 31.10.2015.
 */
public interface BaseService<T> {

    Result<T> save(T t);

    T get(Long id);

    Result delete(Long toDeleteId);

}
