package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ModuleTest {
    private Module testModule;
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
        testModule = new Module("CT123");
    }

    @Test
    public void getModuleRoster() {
        testModule.enrollStudent(students.get(0));
        testModule.enrollStudent(students.get(1));
        testModule.enrollStudent(students.get(2));
        Student[] enrolled = testModule.getModuleRoster();

        StringBuilder studentList = new StringBuilder();
        for (Student student : enrolled) {
            studentList.append(student.firstName).append(' ').append(student.lastName).append(", ");
        }

        assertThat(studentList.toString(), containsString("Jane Doe"));
        assertThat(studentList.toString(), containsString("Mary Doe"));
        assertThat(studentList.toString(), containsString("John Doe"));
        assertThat(studentList.toString(), not(containsString("Lisa Doe")));
    }

    @Test
    public void enrollStudent() {
        assertThat(testModule.getModuleSize(), is(0));
        testModule.enrollStudent(students.get(0));
        assertThat(testModule.getModuleSize(), is(1));
    }

    @Test
    public void enrollStudentList() {
        assertThat(testModule.getModuleSize(), is(0));
        testModule.enrollStudentList(students);
        assertThat(testModule.getModuleSize(), is(students.size()));
    }

    @Test
    public void removeStudent() {
        testModule.enrollStudent(students.get(0));
        testModule.enrollStudent(students.get(1));
        testModule.enrollStudent(students.get(2));
        assertThat(testModule.getModuleSize(), is(3));

        assertThat(testModule.removeStudent(students.get(2)), is(true));
        assertThat(testModule.getModuleSize(), is(2));

        // Delete student not in module has no effect: No exception or change, but does returns false
        assertThat(testModule.removeStudent(students.get(3)), is(false));
        assertThat(testModule.getModuleSize(), is(2));

        // Cannot remove a student again
        assertThat(testModule.removeStudent(students.get(2)), is(false));
        assertThat(testModule.getModuleSize(), is(2));

        assertThat(testModule.removeStudent(students.get(0)), is(true));
        assertThat(testModule.removeStudent(students.get(1)), is(true));
        assertThat(testModule.getModuleSize(), is(0));
    }
}
