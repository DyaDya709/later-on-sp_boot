package ru.practicum.user;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserDbRepositoryImpl implements UserRepository{

    private final EntityManager entityManager;

    public UserDbRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> searchByEmailDomain(String domain) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.like(root.get("email"), "%"+domain));
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}
