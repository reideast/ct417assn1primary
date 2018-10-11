package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StudentTest {

    @Test
    public void getUsername() {
        Student foo = new Student("John", "Doe", 20, new DateTime(), "33445566");

        assertThat(foo.getUsername(), is("jdoe20"));
    }
}
