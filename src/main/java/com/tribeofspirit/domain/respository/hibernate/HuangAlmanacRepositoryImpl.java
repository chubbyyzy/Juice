package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.model.HuangAlmanac;
import com.tribeofspirit.domain.model.attribute.ExpressionType;
import com.tribeofspirit.domain.respository.BaseRepository;
import com.tribeofspirit.domain.respository.HuangAlmanacRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author : gonwang
 * Create time : 2015/12/4.
 */
@Repository
public class HuangAlmanacRepositoryImpl extends BaseRepositoryImpl<HuangAlmanac> implements HuangAlmanacRepository {

    @Override
    public List<HuangAlmanac> listTop(ExpressionType expressionType, int expressionLevel, int tabooOrAppropriate, int maxResults) {
        Criteria criteria = createCriteria();
        criteria.add(Property.forName("expressionType").eq(expressionType));
        criteria.add(Property.forName("expressionLevel").eq(expressionLevel));
        criteria.add(Property.forName("tabooOrAppropriate").eq(tabooOrAppropriate));
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
}
