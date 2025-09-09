package com.example.library.dao;

import com.example.library.model.Book;
import com.example.library.jpa.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookDAO extends GenericDAO<Book, Long> {
    public BookDAO() { super(Book.class); }

    @Override
    protected EntityManager getEntityManager() {
        return JPAUtil.getEntityManager();
    }

    public List<Book> findByTitle(String q) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b WHERE LOWER(b.title) LIKE :q", Book.class)
                    .setParameter("q", "%" + q.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
