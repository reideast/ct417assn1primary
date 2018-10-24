package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.joda.time.Years;

import java.util.HashSet;

/**
 * Defines a student who is attending an academic program
 * Defined by their name, student id, and date of birth. Can generate a user's login
 * Instances of this class will be added to a {@link Course} as a student is registered to belong to that cohort
 * Instances of this class will be added to a {@link Module} as a student enrolls in individual modules
 */
public class Student {
    private String firstName;
    private String lastName;
    private DateTime dob;
    private String id;

    // A list of modules this student has enrolled in, intended to be a read-only property (publicly)
    // It may be modified by modules and courses, but no outside classes
    // This is enforced via the "no access modifier specified" rather than public/private,
    // restricting access to only other classes within this package
    private HashSet<Module> enrolledModules;

    // List of courses this student belongs to, similarly is read-only to any outside classes
    private HashSet<Course> registeredCourses;

    public Student(String firstName, String lastName, DateTime dob, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.id = id;

        this.enrolledModules = new HashSet<>();
        this.registeredCourses = new HashSet<>();
    }

    /**
     * Generate a student username by "concatenating their name and age"
     * @return A generated student username
     */
    public String getUsername() {
            return firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase() + this.getAge();
    }

    /**
     * Get a student's list of modules they're taking, but as a clone to prevent modification
     * @return Array of modules
     */
    public Module[] getEnrolledModules() {
        return enrolledModules.toArray(new Module[0]);
    }

    /**
     * Get a student's course they're a part of (clone)
     * @return Array of courses
     */
    public Course[] getRegisteredCourses() {
        return registeredCourses.toArray(new Course[0]);
    }

    /**
     * Pseudo-private mutator methods meant to only be used by other classes in this package
     */
    void addModule(Module module) {
        enrolledModules.add(module);
    }
    boolean removeModule(Module module) {
        return enrolledModules.remove(module);
    }
    void addCourse(Course course) {
        registeredCourses.add(course);
    }
    boolean removeCourse(Course course) {
        return registeredCourses.remove(course);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Generate student's age based on their date of birth and Now
     * @return Student age
     */
    public int getAge() {
        // Year calculation is adjusted for Daylight Savings time via method explained here: https://stackoverflow.com/a/17959137/5271224
        return Years.yearsBetween(dob.toLocalDate(), today().toLocalDate()).getYears();
    }

    /**
     * A helper method to abstract away finding the current date/time
     * Allows for proper mocking of a constructor w/o resorting to bytecode-rewriting powermock
     * @return Now
     */
    protected DateTime today() {
        return new DateTime();
    }

    public DateTime getDob() {
        return dob;
    }

    public void setDob(DateTime dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
