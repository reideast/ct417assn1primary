package net.andreweast.ct417;

import org.joda.time.DateTime;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private DateTime dob;
    private String id;
    // TOOD: Modules
    // TODO: Courses?


    public Student(String firstName, String lastName, int age, DateTime dob, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dob = dob;
        this.id = id;
    }

    /**
     * Generate a student username by "concatenating their name and age"
     * @return A generate student's username
     */
    public String getUsername() {
            return firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase() + age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
