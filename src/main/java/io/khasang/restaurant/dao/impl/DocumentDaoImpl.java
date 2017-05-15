package io.khasang.restaurant.dao.impl;

import io.khasang.restaurant.dao.DocumentDao;
import io.khasang.restaurant.entity.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Override
    public List<Document> findByName(String name) {
        return (List<Document>) sessionFactory.getCurrentSession()
                .createQuery("from Document as d where d.name = ?")
                .setParameter(0, name)
                .list();
    }

}
