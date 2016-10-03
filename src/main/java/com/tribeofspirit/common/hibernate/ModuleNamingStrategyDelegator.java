package com.tribeofspirit.common.hibernate;

import org.hibernate.cfg.naming.NamingStrategyDelegate;
import org.hibernate.cfg.naming.NamingStrategyDelegator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.io.Serializable;

/**
 * Author : gonwang
 * Create Time : 05.07.2015.
 */
public class ModuleNamingStrategyDelegator  implements NamingStrategyDelegator, Serializable {

    private LocalSessionFactoryBean localSessionFactoryBean;

    public void setLocalSessionFactoryBean(LocalSessionFactoryBean localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
        localSessionFactoryBean.getConfiguration().setNamingStrategyDelegator(this);
    }

    @Override
    public NamingStrategyDelegate getNamingStrategyDelegate(boolean isHbm) {
        return new ModuleNamingStrategyDelegate();
    }
}
