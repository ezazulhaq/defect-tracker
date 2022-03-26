package com.haa.defecttracker.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryDecry {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String decoded = "Haa@1234";
        String encoded = encoder.encode(decoded);
        System.out.println(encoded);
    }

}
