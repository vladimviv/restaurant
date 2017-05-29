package io.khasang.restaurant.controller;

import io.khasang.restaurant.entity.Document;
import io.khasang.restaurant.entity.DocumentItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DocumentControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/document";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String GET_ID = "/get/id/";
    private final String DELETE = "/delete/";
    private final String ALL = "/all";


    @Test
    public void addDocument() {
        Document document = createDocument();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Document> responseEntity = restTemplate.exchange(
                ROOT + GET_ID + "{id}",
                HttpMethod.GET,
                null,
                Document.class,
                document.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Document resultDocument = responseEntity.getBody();
        assertNotNull(resultDocument);
        assertEquals(document.getDescription(), resultDocument.getDescription());
    }

    @Test
    public void updateDocument(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        Document document =  createDocument();

        document.setName("IceArrow");
        HttpEntity<Document> httpEntity = new HttpEntity<>(document, httpHeaders);

        Document resultUpdatedDocument = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Document.class).getBody();
        assertNotNull(resultUpdatedDocument);
        assertNotNull(resultUpdatedDocument.getId());
        assertEquals(document.getName(), resultUpdatedDocument.getName());
    }

    @Test
    public void deleteDocument(){
        Document document = createDocument();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                document.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        ResponseEntity<Document> checkDocumentExist = restTemplate.exchange(
                ROOT + GET_ID + "{id}",
                HttpMethod.GET,
                null,
                Document.class,
                document.getId()
        );

        assertNull(checkDocumentExist.getBody());
    }

    @Test
    public void getAllDocuments(){
        RestTemplate restTemplate = new RestTemplate();
        createDocument();
        createDocument();

        ResponseEntity<List<Document>> responseEntity = restTemplate.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Document>>() {
                }
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    private Document createDocument() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Document document = documentPrefill();
        HttpEntity<Document> httpEntity = new HttpEntity<>(document, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        Document result = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Document.class).getBody();
        assertNotNull(result);
        assertEquals("Fireball", result.getName());
        assertNotNull(result.getId());
        return result;
    }

    private Document documentPrefill() {
        Document document = new Document();
        document.setName("Fireball");
        document.setDescription("Medium size");

        DocumentItem item = new DocumentItem();
        item.setName("2 mana");
        item.setPrice(new BigDecimal(BigInteger.valueOf(10)));


        DocumentItem item2 = new DocumentItem();
        item2.setName("3 mana");
        item2.setPrice(new BigDecimal(BigInteger.valueOf(6)));

        List<DocumentItem> documentItemList = new ArrayList<>();
        documentItemList.add(item);
        documentItemList.add(item2);

        document.setDocumentItems(documentItemList);

        return document;
    }


}
