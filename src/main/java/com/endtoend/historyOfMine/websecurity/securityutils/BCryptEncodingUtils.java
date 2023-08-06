package com.endtoend.historyOfMine.websecurity.securityutils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptEncodingUtils {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static BCryptPasswordEncoder getEncoder(){
        return ENCODER;
    }

    public static String encode(final String password){
        return ENCODER.encode(password);
    }

    public static boolean passwordsMatch(final String password,
                                         final String hash){
        return ENCODER.matches(password, hash);
    }
}
