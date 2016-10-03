package com.tribeofspirit.domain.service;

import com.tribeofspirit.domain.model.HuangAlmanac;

import java.io.File;
import java.io.IOException;

/**
 * Author : gonwang
 * Create time : 2015/12/5.
 */
public interface HuangAlmanacService  extends BaseService<HuangAlmanac>{

    public void createFromFile(File file) throws IOException;
}
