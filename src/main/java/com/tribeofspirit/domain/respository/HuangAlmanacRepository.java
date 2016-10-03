package com.tribeofspirit.domain.respository;

import com.tribeofspirit.domain.model.HuangAlmanac;
import com.tribeofspirit.domain.model.attribute.ExpressionType;

import java.util.List;

/**
 * Author : gonwang
 * Create time : 2015/12/4.
 */
public interface HuangAlmanacRepository extends BaseRepository<HuangAlmanac>{

    List<HuangAlmanac> listTop(ExpressionType expressionType, int expressionLevel, int tabooOrAppropriate, int maxResults);
}
