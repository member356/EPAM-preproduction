package com.epam.khrypushyna.task8;

import com.epam.khrypushyna.task8.sequence.FinderThread;
import com.epam.khrypushyna.task8.simple.number.SimpleNumberThread;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Task8Test {

    @Test
    public void shouldFindAllSimpleNumbersWhenCallFinder() {
        SimpleNumberThread finder = new SimpleNumberThread(0, 12);
        List<Integer> expectedList = new ArrayList<>();
        List<Integer> actualList = new ArrayList<>();
        expectedList.add(2);
        expectedList.add(3);
        expectedList.add(5);
        expectedList.add(7);
        expectedList.add(11);
        try {
            actualList = finder.call();
        } catch (Exception e) {
            fail();
        }
        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldFindAllSimpleNumbersWhenCallFinderUsingExecutor() {
        SimpleNumberThread finder = new SimpleNumberThread(12, 30);
        List<Integer> expectedList = new ArrayList<>();
        List<Integer> actualList = new ArrayList<>();
        expectedList.add(13);
        expectedList.add(17);
        expectedList.add(19);
        expectedList.add(23);
        expectedList.add(29);
        try {
            actualList = finder.call();
        } catch (Exception e) {
            fail();
        }
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testFindTheLongestSequenceOfSameBytesInAFile() throws InterruptedException {
        String expected = "abcdabcd";
        FinderThread thread = new FinderThread(new File("test.txt"));
        thread.start();
        thread.join();
        String  actual = thread.getResult();
        assertEquals(expected, actual);
    }

}
