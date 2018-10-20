package net.andreweast.ct417;

import org.joda.time.DateTime;
import org.joda.time.Years;

public class Student {
    private String firstName;
    private String lastName;
    private DateTime dob;
    private String id;
    // TOOD: Modules
    // TODO: Courses?


    public Student(String firstName, String lastName, DateTime dob, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.id = id;
    }

    /**
     * Generate a student username by "concatenating their name and age"
     * @return A generate student's username
     */
    public String getUsername() {
            return firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase() + this.getAge();
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
