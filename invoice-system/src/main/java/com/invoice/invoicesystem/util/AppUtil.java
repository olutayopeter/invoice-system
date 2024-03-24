package com.invoice.invoicesystem.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
@RequiredArgsConstructor
@Slf4j
public class AppUtil {

    @Value("${app.allowed.characters}")
    private String allowedCharacters;

    @Value("${app.allowed.characters.length}")
    private String allowedCharactersLength;

    @Value("${app.invoice.prefix}")
    private String invoicePrefix;

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        sb.append(invoicePrefix);

        for (int i = 0; i < Integer.parseInt(allowedCharactersLength) - 3; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            sb.append(allowedCharacters.charAt(randomIndex));
        }

        return sb.toString();
    }
}
