package com.endtoend.bfit.websecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoding {
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
