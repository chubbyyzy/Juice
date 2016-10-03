package com.tribeofspirit.external.sightcorp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribeofspirit.external.sightcorp.entity.Person;
import com.tribeofspirit.external.sightcorp.entity.Persons;
import com.tribeofspirit.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : gonwang
 * Create time : 22.10.2015.
 */
@Component
public class FaceRecognitionImpl implements FaceRecognition {

    public static final String API_KEY_PARAM_NAME = "app_key";

    public static final String CLIENT_ID_PARAM_NAME = "client_id";

    public static final String PHOTO_PARAM_NAME = "img";

    private String url;

    private String appKey;

    private String clientId;

    @Value("#{sightcorp['url']}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("#{sightcorp['app_key']}")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Value("#{sightcorp['client_id']}")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Person detect(File file) {

        Map<String, String> config = prepareConfig();

        Map<String, File> photos = preparePhotos(file);

        String detectResult = HttpClientUtils.postSSLRequest(url, config, photos);

        try {
            Persons persons = parse(detectResult);
            if(hasValidPerson(persons)){

                Person person = persons.getPersons().get(0);
                person.setImageName(file.getName());

                return person;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean hasValidPerson(Persons persons) {
        return persons != null
                && persons.getPersons() != null
                && persons.getPersons().size() > 0;
    }

    private Persons parse(String detectResult) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(detectResult,
                Persons.class);
    }

    private Map<String, File> preparePhotos(File file) {

        Map<String, File> photos = new HashMap<>();

        photos.put(PHOTO_PARAM_NAME, file);

        return photos;
    }

    private Map<String, String> prepareConfig() {

        Map<String, String> config = new HashMap<>();

        config.put(API_KEY_PARAM_NAME, appKey);
        config.put(CLIENT_ID_PARAM_NAME, clientId);

        return config;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        FaceRecognition faceRecognition = context.getBean(FaceRecognition.class);
        Person result = faceRecognition.detect(new File("c:\\upload\\myjFYGFs9oF5dWOr4N9Ory1X5PIJYRmk.jpg"));
        System.out.println(result);
    }




}
