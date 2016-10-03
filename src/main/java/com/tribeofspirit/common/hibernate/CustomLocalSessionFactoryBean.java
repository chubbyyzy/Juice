package com.tribeofspirit.common.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.naming.NamingStrategyDelegator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

/**
 * Author : gonwang
 * Create Time : 05.07.2015.
 */
public class CustomLocalSessionFactoryBean extends LocalSessionFactoryBean implements InitializingBean {

    private NamingStrategyDelegator namingStrategyDelegator;

    public void setNamingStrategyDelegator(NamingStrategyDelegator namingStrategyDelegator) {
        this.namingStrategyDelegator = namingStrategyDelegator;
    }

    @Override
    protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
        if (namingStrategyDelegator != null) {
            sfb.setNamingStrategyDelegator(namingStrategyDelegator);
        }
        return sfb.buildSessionFactory();
    }

}
