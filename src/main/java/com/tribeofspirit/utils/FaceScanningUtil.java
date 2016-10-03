package com.tribeofspirit.utils;

import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.attribute.Ethnicity;
import com.tribeofspirit.domain.model.attribute.Gender;
import com.tribeofspirit.domain.model.attribute.MoodType;

import com.tribeofspirit.external.sightcorp.entity.Person;

import java.util.Calendar;

/**
 * Created by Zea Zhang on 11/15/15.
 */
public class FaceScanningUtil {

    public static FaceScanningResult convertPersonToFaceScanningResult(Person person)
    {
        if(person==null)
            return null;

        FaceScanningResult faceScanningResult = new FaceScanningResult();
        faceScanningResult.setAge(new Integer(person.getAge().getValue()));
        faceScanningResult.setAngerScore(new Integer(person.getExpressions().getAnger().getValue()));
        faceScanningResult.setDisgustScore(new Integer(person.getExpressions().getDisgust().getValue()));
        faceScanningResult.setEthnicity(Enum.valueOf(Ethnicity.class, person.getEthnicity().getValue()));
        faceScanningResult.setFearScore(new Integer(person.getExpressions().getFear().getValue()));
        faceScanningResult.setHappinessScore(new Integer(person.getExpressions().getHappiness().getValue()));
        faceScanningResult.setNeutralScore(new Integer(person.getExpressions().getNeutral().getValue()));
        faceScanningResult.setSadnessScore(new Integer(person.getExpressions().getSadness().getValue()));
        faceScanningResult.setSuppriseScore(new Integer(person.getExpressions().getSurprise().getValue()));
        faceScanningResult.setMood(Enum.valueOf(MoodType.class,person.getMood().getValue()));
        faceScanningResult.setMoodScore(new Integer(person.getMood().getConfidence()));
        faceScanningResult.setGender(Enum.valueOf(Gender.class,person.getGender().getValue()));
        faceScanningResult.setCreateTime(Calendar.getInstance().getTime());
        return faceScanningResult;
    }

}
