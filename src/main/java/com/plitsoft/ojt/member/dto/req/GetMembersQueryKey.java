package com.plitsoft.ojt.member.dto.req;

import com.plitsoft.ojt.member.dto.common.MemberFilter;

import java.util.*;

public class GetMembersQueryKey {
    public enum keys {
        name,
        email,
        part
    }
    public static Map<MemberFilter, String> toMemberFindFilter(Map<keys, String> queries) {
        HashMap<MemberFilter, String> filterMap = new HashMap();
        for ( keys k: queries.keySet() )
            filterMap.put( MemberFilter.valueOf( k.name() ), queries.get( k ) );

        return filterMap;
    }
}