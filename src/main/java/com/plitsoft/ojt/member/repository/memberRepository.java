package com.plitsoft.ojt.member.repository;

import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.common.MemberFilter;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class memberRepository {

    @Autowired
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find( Member.class, id );
    }

    public List<Member> findAll(Map<MemberFilter, String> filters) {
        StringBuilder query_builder = new StringBuilder("select m from Member m where ");

        int cnt = 0;
        for ( MemberFilter key: MemberFilter.values() ) {
            cnt++;
            query_builder.append( String.format("m.%s = :%s and ", key.name(), filters.get(key) ) );
        }

        if (cnt == 0) throw new IllegalArgumentException("Empty filter is unavailable");
        query_builder.substring(0, query_builder.length() - 5);

        return em.createQuery(query_builder.toString()).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.memberName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}
