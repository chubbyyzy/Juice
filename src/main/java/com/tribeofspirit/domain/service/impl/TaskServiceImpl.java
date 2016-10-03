package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.model.HuangAlmanac;
import com.tribeofspirit.domain.model.Task;
import com.tribeofspirit.domain.model.attribute.EffectLevel;
import com.tribeofspirit.domain.model.attribute.ExpressionType;
import com.tribeofspirit.domain.model.attribute.TaskType;
import com.tribeofspirit.domain.respository.TaskRepository;
import com.tribeofspirit.domain.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Author : gonwang
 * Create time : 2015/12/20.
 */
@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        super(taskRepository);
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        File file = new File("d:\\task.txt");
        TaskService taskService = applicationContext.getBean(TaskService.class);
        taskService.createFromFile(file);
    }

    @Override
    public List<Task> pick(ExpressionType expressionType) {
        return null;
    }

    @Override
    public void createFromFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            if (StringUtils.isNotEmpty(line)) {
                processTasks(line);
            }
        }
    }

    private void processTasks(String line) {

        line = line.replaceAll("\\\t\\\t", "\t \t");
        String[] cells = line.split("\\\t");
        buildTask(cells, 0, ExpressionType.ANGER);
        buildTask(cells, 1, ExpressionType.SADNESS);
        buildTask(cells, 2, ExpressionType.FEAR);
        buildTask(cells, 3, ExpressionType.DISGUST);
        buildTask(cells, 4, ExpressionType.SURPRISE);
        buildTask(cells, 5, ExpressionType.HAPPINESS);
        buildTask(cells, 6, ExpressionType.NEUTRAL);
    }

    private void buildTask(String[] cells, int i, ExpressionType expressionType) {

        if (cells.length - 5 < i) return ;

        if (StringUtils.isBlank(cells[4+i])) return;

        if (!cells[4+i].trim().equals("1")) return;

        TaskType taskType = TaskType.valueOfTitle(cells[2]);
        EffectLevel effectLevel = EffectLevel.valueOfTitle(cells[3]);
        Task task = new Task();
        task.setDescription(cells[1]);
        task.setType(taskType);
        task.setEffectLevel(effectLevel);
        task.setExpressionType(expressionType);

        save(task);
    }
}
