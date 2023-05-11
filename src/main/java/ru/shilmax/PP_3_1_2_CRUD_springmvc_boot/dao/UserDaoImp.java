package ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        if (findById(id) != null) {
            entityManager.remove(user);
        } else {
            throw new NullPointerException("There is no such id = " + id);
        }
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Long findByEmail(String email) {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email", User.class)
                    .setParameter("email", email);
            return query.getSingleResult().getId();
        } catch (NoResultException ignored) {
            return 0L;
        }
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

}
