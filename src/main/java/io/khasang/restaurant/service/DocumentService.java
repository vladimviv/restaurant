package io.khasang.restaurant.service;


import io.khasang.restaurant.entity.Document;

import java.util.List;

public interface DocumentService {

    /**
     * Create document at database
     *
     * @param document - document for creation
     * @return document
     */
    Document addDocument(Document document);

    /**
     * Receive all documents
     *
     * @return all documents from database
     */
    List<Document> getDocumentList();

    /**
     * Delete specified document by id
     *
     * @param id - id of document for deleting
     */
    Document deleteDocument(long id);

    /**
     * Receive document by id
     *
     * @param id - id document
     * @return document
     */
    Document getDocumentById(long id);

    /**
     * Update document
     *
     * @param document - document from request for update
     * @return updated document
     */
    Document updateDocument(Document document);

    /**
     * Receive document from database by name
     *
     * @param name - document name
     * @return list of documents
     */
    List<Document> getDocumentListByName(String name);
}
