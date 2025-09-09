package com.example.library.dao;

import com.example.library.model.Loan;
import com.example.library.jpa.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LoanDAO extends GenericDAO<Loan, Long> {
    public LoanDAO() { super(Loan.class); }

    @Override
    protected EntityManager getEntityManager() { return JPAUtil.getEntityManager(); }

    public List<Loan> findActiveLoans() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Loan l WHERE l.returnDate IS NULL", Loan.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
