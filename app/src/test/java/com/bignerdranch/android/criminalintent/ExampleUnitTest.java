package com.bignerdranch.android.criminalintent;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testStudyCalenar() {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        String date = simpleDateFormat.format(new Date());

        System.out.println(date);
        int a = 10;
    }
}