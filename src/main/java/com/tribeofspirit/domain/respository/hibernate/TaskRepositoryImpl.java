package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.model.Task;
import com.tribeofspirit.domain.respository.TaskRepository;
import org.springframework.stereotype.Repository;

/**
 * Author : gonwang
 * Create time : 2015/12/20.
 */
@Repository
public class TaskRepositoryImpl extends BaseRepositoryImpl<Task> implements TaskRepository {
}
