package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
        testModule = new Module("CT101");
    }

    @Test
    public void studentKnowsListOfModules() throws Exception {
        Module secondModule = new Module("CT102");

        testModule.enrollStudent(students.get(0));
        assertThat(Arrays.asList(students.get(0).getEnrolledModules()), hasItem(testModule));
        assertThat(Arrays.asList(students.get(0).getEnrolledModules()), not(hasItem(secondModule)));

        secondModule.enrollStudent(students.get(0));
        assertThat(Arrays.asList(students.get(0).getEnrolledModules()), hasItem(testModule));
        assertThat(Arrays.asList(students.get(0).getEnrolledModules()), hasItem(secondModule));
    }

    @Test
    public void getModuleRoster() throws Exception {
        testModule.enrollStudent(students.get(0));
        testModule.enrollStudent(students.get(1));
        testModule.enrollStudent(students.get(2));
        Student[] enrolled = testModule.getModuleRoster();

        StringBuilder studentList = new StringBuilder();
        for (Student student : enrolled) {
            studentList.append(student.getFirstName()).append(' ').append(student.getLastName()).append(", ");
        }

        assertThat(studentList.toString(), containsString("Jane Doe"));
        assertThat(studentList.toString(), containsString("Mary Doe"));
        assertThat(studentList.toString(), containsString("John Doe"));
        assertThat(studentList.toString(), not(containsString("Lisa Doe")));
    }

    @Test
    public void enrollStudent() throws Exception {
        assertThat(testModule.getModuleSize(), is(0));
        testModule.enrollStudent(students.get(0));
        assertThat(testModule.getModuleSize(), is(1));

        try {
            testModule.enrollStudent(students.get(0));
            fail("Expected duplicate registration error");
        } catch (DuplicateRegistrationException ignored) {}
    }

    @Test
    public void enrollStudentList() throws Exception {
        assertThat(testModule.getModuleSize(), is(0));
        testModule.enrollStudents(students);
        assertThat(testModule.getModuleSize(), is(students.size()));

        try {
            testModule.enrollStudent(students.get(2));
            fail("Expected duplicate registration error");
        } catch (DuplicateRegistrationException ignored) {}
    }

    @Test
    public void removeStudent() throws Exception {
        testModule.enrollStudent(students.get(0));
        testModule.enrollStudent(students.get(1));
        testModule.enrollStudent(students.get(2));
        assertThat(testModule.getModuleSize(), is(3));

        testModule.removeStudent(students.get(2));
        assertThat(testModule.getModuleSize(), is(2));

        // Delete student not in module throws an exception
        try {
            testModule.removeStudent(students.get(3));
            fail("Expected exception when student does was never registered for module");
        } catch (RegistrationDoesNotExistException ignored) {}
        assertThat(testModule.getModuleSize(), is(2));

        // Cannot remove a student again
        try {
            testModule.removeStudent(students.get(2));
            fail("Expected exception when student has already been removed");
        } catch (RegistrationDoesNotExistException ignored) {}
        assertThat(testModule.getModuleSize(), is(2));

        testModule.removeStudent(students.get(0));
        testModule.removeStudent(students.get(1));
        assertThat(testModule.getModuleSize(), is(0));
    }
}
