package io.khasang.restaurant.dao;

import io.khasang.restaurant.entity.Document;

public interface DocumentDao extends BasicDao<Document> {

    /**
     * Create document at database
     *
     * @param document - document
     * @return document
     */
    Document addDocument(Document document);
}
