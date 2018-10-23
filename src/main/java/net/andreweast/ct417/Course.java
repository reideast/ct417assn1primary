package net.andreweast.ct417;

import java.util.ArrayList;
import java.util.Collection;

public class Course {
    protected String moduleName;
    protected ArrayList<Student> enrolledStudents;

    public Course(String moduleName) {
        this.moduleName = moduleName;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enrollStudent(Student newStudent) {
        enrolledStudents.add(newStudent);
    }

    public void enrollStudentList(Collection<? extends Student> newStudents) {
        enrolledStudents.addAll(newStudents);
    }

    public boolean removeStudent(Student dropout) {
        return enrolledStudents.remove(dropout);
    }

    /**
     * Get the list of students in the course
     * Use a method that shallow-clones the list of enrolled students in order to keep the public API of this class protecting the private data
     * @return Student[] list of enrolled students
     */
    public Student[] getCourseRoster() {
        return enrolledStudents.toArray(new Student[0]);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getCourseSize() {
        return enrolledStudents.size();
    }
}
