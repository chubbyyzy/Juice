package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.HuangAlmanac;
import com.tribeofspirit.domain.model.attribute.ExpressionType;
import com.tribeofspirit.domain.respository.BaseRepository;
import com.tribeofspirit.domain.respository.HuangAlmanacRepository;
import com.tribeofspirit.domain.respository.ScanningResultRepository;
import com.tribeofspirit.domain.service.ScanningResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Service
public class ScanningResultServiceImpl extends BaseServiceImpl<FaceScanningResult> implements ScanningResultService{

    ScanningResultRepository scanningResultRepository;

    @Autowired
    private HuangAlmanacRepository huangAlmanacRepository;

    @Autowired
    public ScanningResultServiceImpl(ScanningResultRepository scanningResultRepository) {
        super(scanningResultRepository);
        this.scanningResultRepository = scanningResultRepository;

    }

    @Override
    public FaceScanningResult prepare(Long id) {

        FaceScanningResult result = get(id);

//        String tabooAndAppropriate = "宜:逛街购物 聊天吹牛 遛娃  忌:跑步 真心告白 倒垃圾";
        String tabooAndAppropriate = buildTabooAndAppropriate(result);

        result.setTabooAndAppropriate(tabooAndAppropriate);

        return result;
    }

    @Override
    public FaceScanningResult getLatestResult(String weixinOpenId) {
        return scanningResultRepository.getLatestResult(weixinOpenId);
    }

    private String buildTabooAndAppropriate(FaceScanningResult result) {

        ExpressionType expressionType = result.getMaxExpression();

        int expressionLevel = result.getMaxExpressionLevel();

        List<HuangAlmanac> appropriateList = huangAlmanacRepository.listTop(expressionType, expressionLevel, 1, 3);

        List<HuangAlmanac> tabooList = huangAlmanacRepository.listTop(expressionType, expressionLevel, 2, 3);

        StringBuilder stringBuilder = new StringBuilder();
        if (appropriateList != null && appropriateList.size() > 0) {
            stringBuilder.append("宜: ");
            for (HuangAlmanac huangAlmanac : appropriateList) {
                stringBuilder.append(huangAlmanac.getMessage()).append(" ");
            }
        }
        if (tabooList != null && tabooList.size() > 0) {
            stringBuilder.append("    忌: ");
            for (HuangAlmanac huangAlmanac : tabooList) {
                stringBuilder.append(huangAlmanac.getMessage()).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
