package com.demo.internetprogramming.regex;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountMatchesUnitTest {

    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("([a-z0-9_.-]+)@([a-z0-9_.-]+[a-z])");
    private static final String TEXT_CONTAINING_EMAIL_ADDRESSES = "You can contact me through: kotenko@ruslan.com, kotero.dev@gmail.com";
    private static final String TEXT_CONTAINING_THREE_EMAIL_ADDRESSES = "Valid emails are: kotenko@ruslan.com, kotero.dev@gmail.com, press@kotenko.com";
    private static final String TEXT_CONTAINING_OVERLAP_EMAIL_ADDRESSES = "Try to contact us at kotenko@ruslan.com, kotero.dev@gmail.com, press@kotenko.com";

    @Test
    public void givenContainingEmailString_whenJava8Match_thenCountMacthesFound() {
        Matcher countEmailMatcher = EMAIL_ADDRESS_PATTERN.matcher(TEXT_CONTAINING_EMAIL_ADDRESSES);

        int count = 0;
        while (countEmailMatcher.find()) {
            count++;
        }

        assertEquals(2, count);
    }

    @Test
    public void givenContainingThreeEmailsString_whenJava8Match_thenCountMacthesFound() {
        Matcher countFiveEmailsMatcher = EMAIL_ADDRESS_PATTERN.matcher(TEXT_CONTAINING_THREE_EMAIL_ADDRESSES);

        int count = 0;
        while (countFiveEmailsMatcher.find()) {
            count++;
        }

        assertEquals(3, count);
    }

    @Test
    public void givenStringWithoutEmails_whenJava8Match_thenCountMacthesNotFound() {
        Matcher noEmailMatcher = EMAIL_ADDRESS_PATTERN.matcher("Simple text without emails.");

        int count = 0;
        while (noEmailMatcher.find()) {
            count++;
        }

        assertEquals(0, count);
    }

    @Test
    public void givenStringWithOverlappingEmails_whenJava8Match_thenCountWrongMatches() {
        Matcher countOverlappingEmailsMatcher = EMAIL_ADDRESS_PATTERN.matcher(TEXT_CONTAINING_OVERLAP_EMAIL_ADDRESSES);

        int count = 0;
        while (countOverlappingEmailsMatcher.find()) {
            count++;
        }

        assertEquals(3, count);
    }
}