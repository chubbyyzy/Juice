package com.tribeofspirit.common.hibernate;

import com.tribeofspirit.domain.model.BaseEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

public class SessionInterceptorDefault extends EmptyInterceptor {

    public boolean onSave(
            Object entity, Serializable id, final Object[] state, String[] propertyNames, Type[] types) {

        if (!(entity instanceof BaseEntity)) {
            return false;
        }

        boolean updated = false;

        for (int i = 0; i < propertyNames.length; i++) {

            if ("creator".equals(propertyNames[i]) || "modifier".equals(propertyNames[i])) {

                Long userId = getUserId();
                if (userId == null) continue;
                state[i] = userId.toString();
                updated = true;

            } else if ("createTime".equals(propertyNames[i]) || "modifyTime".equals(propertyNames[i])) {

                state[i] = new Date();
                updated = true;
            }
        }
        return updated;
    }

    public boolean onFlushDirty(
            Object entity, Serializable id, final Object[] currentState,
            final Object[] previousState, String[] propertyNames, Type[] types) {

        if (!(entity instanceof BaseEntity)) {
            return false;
        }

        boolean updated = false;
        for (int i = 0; i < propertyNames.length; i++) {

            if ("modifier".equals(propertyNames[i])) {

                Long userId = getUserId();
                if (userId == null) continue;
                currentState[i] = userId.toString();
                updated = true;

            } else if ("modifyTime".equals(propertyNames[i])) {

                currentState[i] = new Date();
                updated = true;
            }
        }
        return updated;
    }


    private Long getUserId() {

        return 1L;
    }
}
