package com.bank.service;

import com.bank.util.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class IBANGeneratorService {
    private static final String PREFIX = "NL69F4UB";
    private static final int RANDOM_SUFFIX_SIZE = 10;

    /**
     * Produces a random IBAN not conform to the standard. This function does also not guarantee
     * that every generated IBAN is unique
     *
     * @return the generated IBAN number
     */
    public String generateIBAN() {
        return PREFIX + RandomStringGenerator.generateRandomIntegerString(RANDOM_SUFFIX_SIZE);
    }
}
