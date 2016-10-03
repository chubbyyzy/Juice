package com.tribeofspirit.domain.service;

import com.tribeofspirit.domain.model.FaceScanningResult;

/**
 * Created by Zea Zhang on 11/1/15.
 */
public interface ScanningResultService extends BaseService<FaceScanningResult> {

    FaceScanningResult prepare(Long aLong);

    FaceScanningResult getLatestResult(String weixinOpenId);
}
