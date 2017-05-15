package io.khasang.restaurant.dao;

import io.khasang.restaurant.entity.Document;

import java.util.List;

public interface DocumentDao extends BasicDao<Document> {

    /**
     * Create document at database
     *
     * @param document - document
     * @return document
     */
    Document addDocument(Document document);

    /**
     * Receive document from database by name
     *
     * @param name - document name
     * @return list of documents
     */
    List<Document> findByName(String name);
}
