package com.tribeofspirit.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static CloseableHttpClient createSSLClientDefault(){

        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {

            logger.error("Failed to create SSL HTTP Client.", e);

        }

        return  HttpClients.createDefault();
    }

    public static JsonNode sendSSLRequest(String url) {

        CloseableHttpClient httpClient = HttpClientUtils.createSSLClientDefault();

        HttpUriRequest uriRequest = new HttpGet(url);

        CloseableHttpResponse httpResponse = null;

        try {

            httpResponse = httpClient.execute(uriRequest);

            return new ObjectMapper().readTree(httpResponse.getEntity().getContent());

        } catch (IOException e) {

            logger.error("Failed to send SSL HTTP request.", e);

            return null;

        } finally {

            IOUtils.closeQuietly(httpClient);

            IOUtils.closeQuietly(httpResponse);
        }
    }

    public static void requestFile(String request, Path saveToPath) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpUriRequest uriRequest = new HttpGet(request);

        CloseableHttpResponse httpResponse = null;

        try {

            httpResponse = httpClient.execute(uriRequest);

            Files.copy(httpResponse.getEntity().getContent(), saveToPath);

        } catch (IOException e) {

            logger.error("", e);

        } finally {

            IOUtils.closeQuietly(httpClient);

            IOUtils.closeQuietly(httpResponse);
        }
    }

    public static JsonNode postSSLRequest(String url, String body) {

        long start = System.currentTimeMillis();

        CloseableHttpClient httpClient = HttpClientUtils.createSSLClientDefault();

        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse httpResponse = null;

        try {

            HttpEntity reqEntity = new StringEntity(body, ContentType.APPLICATION_JSON);

            httpPost.setEntity(reqEntity);

            httpResponse = httpClient.execute(httpPost);

            return new ObjectMapper().readTree(httpResponse.getEntity().getContent());

        } catch (IOException e) {

            logger.error("Failed to post ssl request.", e);
            return null;

        } finally {

            IOUtils.closeQuietly(httpResponse);
            IOUtils.closeQuietly(httpClient);

            logger.debug("Post ssl request spend time : {}", System.currentTimeMillis() - start);
        }
    }

    public static String postSSLRequest(String url, Map<String, String> parameters, Map<String, File> multipartFiles) {

        long start = System.currentTimeMillis();

        CloseableHttpClient httpClient = HttpClientUtils.createSSLClientDefault();

        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse httpResponse = null;

        try {

            HttpEntity reqEntity = buildMultipartEntity(parameters, multipartFiles);

            httpPost.setEntity(reqEntity);

            httpResponse = httpClient.execute(httpPost);

            return IOUtils.toString(httpResponse.getEntity().getContent());

        } catch (IOException e) {

            logger.error("Failed to post ssl request.", e);
            return null;

        } finally {

            IOUtils.closeQuietly(httpResponse);

            IOUtils.closeQuietly(httpClient);

            logger.debug("Post ssl request spend time : {}", System.currentTimeMillis() - start);
        }

    }

    private static HttpEntity buildMultipartEntity(Map<String, String> parameters, Map<String, File> multipartFiles) {

        MultipartEntityBuilder multipartEntityBuilder =
                MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        for (Map.Entry<String, String> parameter : parameters.entrySet()) {

            StringBody valueBody = new StringBody(parameter.getValue(), ContentType.TEXT_PLAIN);

            multipartEntityBuilder.addPart(parameter.getKey(), valueBody);
        }

        for (Map.Entry<String, File> multipartFile : multipartFiles.entrySet()) {

            FileBody fileBody = new FileBody(multipartFile.getValue());

            multipartEntityBuilder.addPart(multipartFile.getKey(), fileBody);
        }

        return multipartEntityBuilder.build();
    }

}
