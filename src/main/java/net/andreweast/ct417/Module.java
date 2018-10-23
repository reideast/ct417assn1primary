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

    // TODO: // Implementation note: This class DOESN'T keep a list of courses it belongs to in order to avoid circular hierarchy

    public Module(String moduleName) {
        this.moduleName = moduleName;
        this.enrolledStudents = new ArrayList<>();
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
