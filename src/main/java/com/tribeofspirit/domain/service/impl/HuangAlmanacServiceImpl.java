package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.model.HuangAlmanac;
import com.tribeofspirit.domain.model.attribute.ExpressionType;
import com.tribeofspirit.domain.respository.HuangAlmanacRepository;
import com.tribeofspirit.domain.service.HuangAlmanacService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Author : gonwang
 * Create time : 2015/12/5.
 */
@Service
public class HuangAlmanacServiceImpl extends BaseServiceImpl<HuangAlmanac> implements HuangAlmanacService {

    private HuangAlmanacRepository huangAlmanacRepository;

    @Autowired
    public HuangAlmanacServiceImpl(HuangAlmanacRepository huangAlmanacRepository) {
        super(huangAlmanacRepository);
        this.huangAlmanacRepository = huangAlmanacRepository;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        File file = new File("d:\\ha.txt");
        HuangAlmanacService huangAlmanacService = applicationContext.getBean(HuangAlmanacService.class);
        huangAlmanacService.createFromFile(file);
    }

    public void createFromFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            if (StringUtils.isNotEmpty(line)) {
                processHuangAlmanacs(line);
            }
        }
    }

    private void processHuangAlmanacs(String line) {

/*
        StringTokenizer tokenizer = new StringTokenizer(line, "\t");
        List<String> cellList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            cellList.add(tokenizer.nextToken());
        }
*/
        line = line.replaceAll("\\\t\\\t", "\t \t");
        String[] cells = line.split("\\\t");

        buildExpressionHuangAlmanac(cells, 0, ExpressionType.ANGER);
        buildExpressionHuangAlmanac(cells, 1, ExpressionType.SADNESS);
        buildExpressionHuangAlmanac(cells, 2, ExpressionType.FEAR);
        buildExpressionHuangAlmanac(cells, 3, ExpressionType.DISGUST);
        buildExpressionHuangAlmanac(cells, 4, ExpressionType.SURPRISE);
        buildExpressionHuangAlmanac(cells, 5, ExpressionType.HAPPINESS);
        buildExpressionHuangAlmanac(cells, 6, ExpressionType.NEUTRAL);
    }

    private void buildExpressionHuangAlmanac(String[] cells, int index, ExpressionType expressionType) {

        if (StringUtils.isNotBlank(cells[3*index + 1])) {
            int ta = getTabooOrAppropriate(cells[3*index + 1]);
            HuangAlmanac huangAlmanac = new HuangAlmanac(expressionType, 3, ta, cells[0]);
            huangAlmanacRepository.saveOrUpdate(huangAlmanac);
        }

        if (StringUtils.isNotBlank(cells[3*index + 2])) {
            int ta = getTabooOrAppropriate(cells[3*index + 2]);
            HuangAlmanac huangAlmanac = new HuangAlmanac(expressionType, 2, ta, cells[0]);
            huangAlmanacRepository.saveOrUpdate(huangAlmanac);
        }

        if (StringUtils.isNotBlank(cells[3*index + 3])) {
            int ta = getTabooOrAppropriate(cells[3*index + 3]);
            HuangAlmanac huangAlmanac = new HuangAlmanac(expressionType, 1, ta, cells[0]);
            huangAlmanacRepository.saveOrUpdate(huangAlmanac);
        }
    }

    private static int getTabooOrAppropriate(String cell) {
        return cell.equals("宜") ? 1 : 2;
    }
}
