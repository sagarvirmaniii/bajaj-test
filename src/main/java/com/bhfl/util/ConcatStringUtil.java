package com.bhfl.util;

import java.util.Collection;

public final class ConcatStringUtil {

    private ConcatStringUtil() {
    }

    public static String buildAlternatingReverse(Collection<String> alphabetStrings) {
        if (alphabetStrings == null || alphabetStrings.isEmpty()) {
            return "";
        }

        StringBuilder collected = new StringBuilder();
        for (String value : alphabetStrings) {
            if (value == null) {
                continue;
            }
            value.chars()
                    .filter(Character::isLetter)
                    .forEach(character -> collected.append((char) character));
        }

        String reversed = collected.reverse().toString();
        StringBuilder result = new StringBuilder(reversed.length());
        for (int index = 0; index < reversed.length(); index++) {
            char current = reversed.charAt(index);
            result.append(index % 2 == 0
                    ? Character.toUpperCase(current)
                    : Character.toLowerCase(current));
        }
        return result.toString();
    }
}
