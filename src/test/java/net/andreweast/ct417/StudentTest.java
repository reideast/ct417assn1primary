package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class StudentTest {

    /**
     * Test that username is composed correctly from name and student age
     * Using a Mockito spy: Since username is a concatenation of the user's name and age, the username will change every year
     * A spy allows mocking just the "Now" method of student and keep this test from failing next year, when the age changes!
     */
    @Test
    public void getUsername() {
        DateTime testingDate = new DateTime(2018, 10, 20, 12, 0);

        DateTime dob = new DateTime(1998, 1, 10, 0, 0);
        Student student = Mockito.spy(new Student("John", "Doe", dob, "33445566"));
        when(student.today()).thenReturn(testingDate);

        assertThat(student.getUsername(), is("jdoe20"));
    }

    /**
     * Test age calculations, using Joda's {@link DateTimeUtils} to ensure that the date stays the same
     */
    @Test
    public void getAge() {
        // Freezing time with Joda from Mark Needham: https://markhneedham.com/blog/2008/09/24/testing-with-joda-time/
        DateTime testingDate = new DateTime(2018, 10, 20, 12, 0);
        DateTimeUtils.setCurrentMillisFixed(testingDate.getMillis());

        // Age is correct number of years when student's birthday has already passed this year
        DateTime dob = new DateTime(1998, 1, 10, 0, 0);
        Student student = new Student("John", "Doe", dob, "33445566");
        assertThat(student.getAge(), is(20));

        // Age increases when one more year has passed
        DateTime nextYear = testingDate.plusYears(1);
        DateTimeUtils.setCurrentMillisFixed(nextYear.getMillis());
        assertThat(student.getAge(), is(21));

        // Un-freeze time
        DateTimeUtils.setCurrentMillisSystem();
    }
}
