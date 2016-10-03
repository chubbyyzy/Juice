package com.tribeofspirit.domain.service;

import com.tribeofspirit.domain.model.Task;
import com.tribeofspirit.domain.model.attribute.ExpressionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Author : gonwang
 * Create time : 2015/12/20.
 */
public interface TaskService extends BaseService<Task> {

    List<Task> pick(ExpressionType expressionType);

    void createFromFile(File file) throws IOException;
}
