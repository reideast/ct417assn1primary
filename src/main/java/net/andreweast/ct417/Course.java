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
    private String courseName;
    private DateTime startDate;
    private DateTime endDate;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Module> courseModules;

    public Course(String courseName, DateTime startDate, DateTime endDate) {
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseModules = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    public void addModule(Module module) throws DuplicateRegistrationException {
        if (courseModules.contains(module)) {
            throw new DuplicateRegistrationException("Module has already been added to this course");
        }

        courseModules.add(module);

        module.addCourse(this);
    }

    public void addModules(Collection<? extends Module> modules) throws DuplicateRegistrationException {
        for (Module module : modules) {
            if (courseModules.contains(module)) {
                throw new DuplicateRegistrationException("Module has already been added to this course");
            }
            module.addCourse(this);
        }
        courseModules.addAll(modules);
    }

    public void removeModule(Module module) throws RegistrationDoesNotExistException {
        if (!courseModules.remove(module)) {
            throw new RegistrationDoesNotExistException("Module was not previously added to this course");
        }
        module.removeCourse(this);
    }

    public Module[] getModuleList() {
        return courseModules.toArray(new Module[0]);
    }

    public void registerStudent(Student newStudent) throws DuplicateRegistrationException {
        if (registeredStudents.contains(newStudent)) {
            throw new DuplicateRegistrationException("Student is already registered for this course");
        }

        registeredStudents.add(newStudent);

        newStudent.addCourse(this);
    }

    public void registerStudents(Collection<? extends Student> newStudents) throws DuplicateRegistrationException {
        for (Student newStudent : newStudents) {
            if (registeredStudents.contains(newStudent)) {
                throw new DuplicateRegistrationException("A student is already registered for this course");
            }
            newStudent.addCourse(this);
        }

        registeredStudents.addAll(newStudents);
    }

    public void removeStudent(Student dropout) throws RegistrationDoesNotExistException {
        if (!registeredStudents.remove(dropout)) {
            throw new RegistrationDoesNotExistException("Student has not been registered for this course");
        }
        dropout.removeCourse(this);
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
