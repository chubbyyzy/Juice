package com.tribeofspirit.external.sightcorp;

import com.tribeofspirit.external.sightcorp.entity.Person;

import java.io.File;

/**
 * Author : gonwang
 * Create time : 22.10.2015.
 */
public interface FaceRecognition {

    Person detect(File file);
}
