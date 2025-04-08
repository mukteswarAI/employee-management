package org.ems.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.security.SecureRandom;


public class CustomIdGenerator implements IdentifierGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        StringBuilder sb = new StringBuilder("DEPT-");
        for (int i = 0; i < 6; i++) {  // 6-character random string
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

}
