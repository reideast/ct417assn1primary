package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CourseTest {
    private Course testCourse;
    private static ArrayList<Student> students;

    @BeforeClass
    public static void setup() {
        students = new ArrayList<>();
        final DateTime dob = new DateTime();
        students.add(new Student("Jane", "Doe", dob, "12345"));
        students.add(new Student("John", "Doe", dob, "12346"));
        students.add(new Student("Mary", "Doe", dob, "12347"));
        students.add(new Student("Lisa", "Doe", dob, "12348"));
    }

    @Before
    public void init() {
        testCourse = new Course("CT123");
    }

    @Test
    public void enrollStudent() {
        assertThat(testCourse.getCourseSize(), is(0));
        testCourse.enrollStudent(students.get(0));
        assertThat(testCourse.getCourseSize(), is(1));
    }

    @Test
    public void enrollStudentList() {
        assertThat(testCourse.getCourseSize(), is(0));
        testCourse.enrollStudentList(students);
        assertThat(testCourse.getCourseSize(), is(students.size()));
    }

    @Test
    public void removeStudent() {
        testCourse.enrollStudent(students.get(0));
        testCourse.enrollStudent(students.get(1));
        testCourse.enrollStudent(students.get(2));
        assertThat(testCourse.getCourseSize(), is(3));

        assertThat(testCourse.removeStudent(students.get(2)), is(true));
        assertThat(testCourse.getCourseSize(), is(2));

        // Delete student not in course has no effect: No exception or change, but does returns false
        assertThat(testCourse.removeStudent(students.get(3)), is(false));
        assertThat(testCourse.getCourseSize(), is(2));

        // Cannot remove a student again
        assertThat(testCourse.removeStudent(students.get(2)), is(false));
        assertThat(testCourse.getCourseSize(), is(2));

        assertThat(testCourse.removeStudent(students.get(0)), is(true));
        assertThat(testCourse.removeStudent(students.get(1)), is(true));
        assertThat(testCourse.getCourseSize(), is(0));
    }
}
