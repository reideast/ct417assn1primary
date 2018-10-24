package net.andreweast.ct417;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Defines a university module
 * Identified by its the module name
 * Contains reference to the students who have enrolled for instruction
 * Instances of this class will be added to a {@link Course}
 */
public class Module {
    private String moduleName;
    private ArrayList<Student> enrolledStudents;

    // List of courses this Module is a member of, and can only be accessed as a read-only list from outside this package
    private ArrayList<Course> associatedCourses;

    public Module(String moduleName) {
        this.moduleName = moduleName;
        this.enrolledStudents = new ArrayList<>();

        this.associatedCourses = new ArrayList<>();
    }

    public void enrollStudent(Student newStudent) throws DuplicateRegistrationException {
        if (enrolledStudents.contains(newStudent)) {
            throw new DuplicateRegistrationException("Student is already registered for this module");
        }

        enrolledStudents.add(newStudent);

        newStudent.addModule(this);
    }

    public void enrollStudents(Collection<? extends Student> newStudents) throws DuplicateRegistrationException {
        for (Student newStudent : newStudents) {
            if (enrolledStudents.contains(newStudent)) {
                throw new DuplicateRegistrationException("A student is already registered for this module");
            }
            newStudent.addModule(this);
        }

        enrolledStudents.addAll(newStudents);
    }

    public void removeStudent(Student dropout) throws RegistrationDoesNotExistException {
        if (!enrolledStudents.remove(dropout)) {
            throw new RegistrationDoesNotExistException("Student has not been registered for this module");
        }
        dropout.removeModule(this);
    }

    /**
     * Get the list of students in the module
     * Use a method that shallow-clones the list of enrolled students in order to keep the public API of this class protecting the private data
     * @return Student[] list of enrolled students
     */
    public Student[] getModuleRoster() {
        return enrolledStudents.toArray(new Student[0]);
    }

    /**
     * Get the courses that this Module has been associated with
     * @return Array of courses
     */
    public Course[] getAssociatedCourses() {
        return associatedCourses.toArray(new Course[0]);
    }

    /**
     * Pseudo-private mutator methods meant to only be used by other classes in this package
     */
    void addCourse(Course course) {
        associatedCourses.add(course);
    }
    boolean removeCourse(Course course) {
        return associatedCourses.remove(course);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleSize() {
        return enrolledStudents.size();
    }
}
