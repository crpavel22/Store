package com.example.dao;

import com.example.entity.Request;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
@Transactional(readOnly = true)
public class StorageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Request request){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Serializable save = session.save(request);
        tx.commit();

    }

    public Request retrieve(String id ){
        Session session = sessionFactory.openSession();
        return session.get(Request.class,id);
    }

    public void update(Request request) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(request);
        tx.commit();
    }

    public void delete(Request request) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(request);

        tx.commit();
    }

}
