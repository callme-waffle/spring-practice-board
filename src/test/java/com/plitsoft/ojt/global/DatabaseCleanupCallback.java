package com.plitsoft.ojt.global;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class DatabaseCleanupCallback implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        SpringExtension.getApplicationContext( extensionContext )
                .getBean( DatabaseCleanup.class ).cleanUp();
    }
}
