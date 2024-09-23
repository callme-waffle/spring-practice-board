package com.plitsoft.ojt.user.repository;

import com.plitsoft.ojt.user.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class userRepository {

    @Autowired
    private EntityManager em;

    public Long save(User user) {
        em.persist( user );
        return user.getId();
    }

    public User find( Long id ) {
        return em.find( User.class, id );
    }

    public List<User> findByName( String name ) {
        return em.createQuery( "select u from User where u.name = name", User.class )
                .setParameter( "name", name )
                .getResultList();
    }

    public List<User> findByEmail( String email ) {
        return em.createQuery( "select u from User where u.email = email", User.class )
                .setParameter( "email", email )
                .getResultList();
    }
}
