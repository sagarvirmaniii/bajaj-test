package com.bhfl.service;

import com.bhfl.dto.BfhlRequest;
import com.bhfl.dto.BfhlResponse;
import com.bhfl.util.ConcatStringUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String FULL_NAME = "sagar_virmani";
    private static final String EMAIL = "sagarvirmni@gmail.com";
    private static final String ROLL_NUMBER = "2310991635";
    private static final LocalDate QUALIFIER_DATE = LocalDate.of(2026, 6, 24);
    private static final DateTimeFormatter USER_ID_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Override
    public BfhlResponse process(BfhlRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request body must not be null");
        }

        List<String> data = request.getData();
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;

        for (String item : data) {
            if (item == null) {
                specialCharacters.add(null);
                continue;
            }

            if (isNumeric(item)) {
                BigInteger number = new BigInteger(item);
                sum = sum.add(number);
                if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetOnly(item)) {
                alphabets.add(item.toUpperCase(Locale.ROOT));
            } else {
                specialCharacters.add(item);
            }
        }

        return BfhlResponse.builder()
                .success(true)
                .userId(buildUserId())
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(sum.toString())
                .concatString(ConcatStringUtil.buildAlternatingReverse(alphabets))
                .build();
    }

    private static boolean isNumeric(String value) {
        return !value.isBlank() && value.chars().allMatch(Character::isDigit);
    }

    private static boolean isAlphabetOnly(String value) {
        return !value.isBlank() && value.chars().allMatch(Character::isLetter);
    }

    private static String buildUserId() {
        return FULL_NAME + "_" + QUALIFIER_DATE.format(USER_ID_DATE_FORMAT);
    }
}
