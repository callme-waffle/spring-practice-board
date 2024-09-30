package com.plitsoft.ojt.global;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleanup {

    private List<String> tableNames = new ArrayList<>();

    @Autowired
    private EntityManager em;

    @PostConstruct // 생성자를 통해 DI 후 메서드를 실행하도록 지시
    private void findTableNames() {
        List<Object[]> tableNameList = em.createNativeQuery( "SHOW TABLES" ).getResultList();

        for ( Object[] tableName:tableNameList )
            tableNames.add( ( String ) tableName[0] );
    }

    @Transactional
    public void cleanUp() {
        System.out.println( "==========| DB CLEANUP |==========" );
        em.clear();
        em.createNativeQuery( "SET REFERENTIAL_INTEGRITY FALSE" ).executeUpdate();
        for ( String tableName : tableNames ) {
            System.out.printf( "[ Table Cleanup ] %s\n", tableName );
            em.createNativeQuery( String.format( "TRUNCATE TABLE %s", tableName ) ).executeUpdate();
        }
        em.createNativeQuery( "SET REFERENTIAL_INTEGRITY TRUE" ).executeUpdate();
        System.out.println( "==========| DB CLEANUP COMPLETE |==========" );
    }
}
