package com.example.zmrs_project.test;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JunitTest {
    @Test
    public boolean testEmailFormat(String email) {

        String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(pattern);
    }
    @Test
    public void testIsLettersOnly() {
        JunitTest validator = new JunitTest();
        assertFalse(validator.isLettersOnly("123456")); // expects false
        assertTrue(validator.isLettersOnly("abc")); // expects true
        assertFalse(validator.isLettersOnly("a1b2c3")); // expects false
        assertTrue(validator.isLettersOnly("HelloWorld")); // expects true
    }
    public boolean isLettersOnly(String search){
            for (int i = 0; i < search.length(); i++) {
                if (Character.isDigit(search.charAt(i))) {
                    return false;
                }
            }
            return true;
    }


}
