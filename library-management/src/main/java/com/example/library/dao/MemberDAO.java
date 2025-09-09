package com.example.library.dao;

import com.example.library.model.Member;
import com.example.library.jpa.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MemberDAO extends GenericDAO<Member, Long> {
    public MemberDAO() { super(Member.class); }

    @Override
    protected EntityManager getEntityManager() { return JPAUtil.getEntityManager(); }

    public List<Member> findByName(String q) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Member m WHERE LOWER(m.name) LIKE :q", Member.class)
                    .setParameter("q", "%" + q.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
