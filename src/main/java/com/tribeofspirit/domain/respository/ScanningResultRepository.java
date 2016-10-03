package com.tribeofspirit.domain.respository;


import com.tribeofspirit.domain.model.FaceScanningResult;

/**
 * Created by Zea Zhang on 11/1/15.
 */
public interface ScanningResultRepository extends BaseRepository<FaceScanningResult> {
    FaceScanningResult getLatestResult(String weixinOpenId);
}
