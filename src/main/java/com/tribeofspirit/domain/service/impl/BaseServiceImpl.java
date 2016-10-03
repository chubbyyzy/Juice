package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.respository.BaseRepository;
import com.tribeofspirit.domain.service.BaseService;
import com.tribeofspirit.domain.service.Result;

/**
 * Author : gonwang
 * Create time : 31.10.2015.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public Result<T> save(T t) {
        baseRepository.saveOrUpdate(t);
        return Result.success(t);
    }

    @Override
    public T get(Long id) {
        return baseRepository.get(id);
    }

    @Override
    public Result delete(Long toDeleteId) {

        baseRepository.delete(toDeleteId);
        return Result.success();
    }
}
