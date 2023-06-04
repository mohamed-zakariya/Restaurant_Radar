package com.example.zmrs_project;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JunitTest {
    @Test
    public boolean testEmailFormat(String email) {

        String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(pattern);
    }


}
