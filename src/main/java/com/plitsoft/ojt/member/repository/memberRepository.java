package com.plitsoft.ojt.member.repository;

import com.plitsoft.ojt.member.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class memberRepository {

    @Autowired
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id ) {
        return em.find( Member.class, id );
    }

    public List<Member> findByName(String name ) {
        return em.createQuery( "select m from Member m where m.name = :name", Member.class )
                .setParameter( "name", name )
                .getResultList();
    }

    public List<Member> findByEmail(String email ) {
        return em.createQuery( "select m from Member m where m.email = :email", Member.class )
                .setParameter( "email", email )
                .getResultList();
    }
}
