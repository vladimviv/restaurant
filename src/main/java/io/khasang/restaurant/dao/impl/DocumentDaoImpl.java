package io.khasang.restaurant.dao.impl;

import io.khasang.restaurant.dao.DocumentDao;
import io.khasang.restaurant.entity.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentDaoImpl extends BasicDaoImpl<Document> implements DocumentDao {
    @Autowired
    SessionFactory sessionFactory;

    public DocumentDaoImpl(Class<Document> entityClass) {
        super(entityClass);
    }

    @Override
    public Document create(Document entity) {
        return null;
    }

    @Override
    public Document addDocument(Document document) {
       sessionFactory.getCurrentSession().save(document);
       return document;
    }
}
