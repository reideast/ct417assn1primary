package net.andreweast.ct417;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Defines an academic course programme
 * Specified by its name, the start date, and end date of the programme of instruction
 * Contains references to the students registered for this course
 * Along with the modules that are taught as a part of this course
 */
public class Course {
    protected String courseName;
    protected DateTime startDate;
    protected DateTime endDate;
    protected ArrayList<Student> registeredStudents;
    protected ArrayList<Module> courseModules;

    public Course(String courseName, DateTime startDate, DateTime endDate) {
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseModules = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    public void addModule(Module module) {
        courseModules.add(module);
    }

    public void addModules(Collection<? extends Module> modules) {
        courseModules.addAll(modules);
    }

    public boolean removeModule(Module module) {
        return courseModules.remove(module);
    }

    public Module[] getModuleList() {
        return courseModules.toArray(new Module[0]);
    }

    public void registerStudent(Student newStudent) {
        registeredStudents.add(newStudent);
    }

    public void registerStudents(Collection<? extends Student> newStudents) {
        registeredStudents.addAll(newStudents);
    }

    public boolean removeStudent(Student dropout) {
        return registeredStudents.remove(dropout);
    }

    /**
     * Get the list of students in the course, using clone to prevent external modification
     * @return Student[] list of registered students
     */
    public Student[] getCourseRoster() {
        return registeredStudents.toArray(new Student[0]);
    }

    public int getCourseSize() {
        return registeredStudents.size();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
