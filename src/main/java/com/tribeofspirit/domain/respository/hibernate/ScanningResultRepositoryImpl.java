package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.User;
import com.tribeofspirit.domain.respository.ScanningResultRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Repository
public class ScanningResultRepositoryImpl extends BaseRepositoryImpl<FaceScanningResult> implements ScanningResultRepository {
    @Override
    public FaceScanningResult getLatestResult(String weixinOpenId) {
        Criteria criteria = createCriteria();
        criteria.add(Property.forName("weixinOpenId").eq(weixinOpenId));
        criteria.addOrder(Order.desc("createTime"));
        List<FaceScanningResult> results = criteria.list();
        if(results != null && results.size() > 0){
            return results.get(0);
        }
        return null;
    }
}
