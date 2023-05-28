/*
 * Name: Farris Danish
 * PID: A17401247
 * email: fbinsyahrilakmar@ucsd.edu
 * Sources used: Write-up, JDK 17 documentation
 *
 * This file contains the implementation of Student for the application of
 * enrollment of classes
 */

import java.util.Objects;

/**
 * This class is contains implementation of Student object which will be the
 * main type stored in the Course object.
 */
public class Student implements Comparable<Student> {

    /* Constants to avoid magic numbers */
    private static final int EQUALS_VALUE = 0;
    private static final String NULL_ARGUMENTS_MESSAGE =
        "Arguments cannot be null";
    private static final String NULL_POINTER_MESSAGE =
        "Student to compare to is null";

    /* Instance variables */
    private final String firstName, lastName;
    private final String PID;

    /* Class methods */

    /**
     * Constructor fo the Student object. Contains firstname lastname and PID
     * @param firstName first name of the student
     * @param lastName lastname of the student
     * @param PID the personal ID number of the student should start with A
     *            followed by 8 numbers (ex. A12345678)
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException(NULL_ARGUMENTS_MESSAGE);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * This method returns the first name of the student
     * @return the first name of the student
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * This method returns the last name of the student
     * @return the last name of the student
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This method returns the PID of the student
     * @return the PID of the student
     */
    public String getPID() {
        return this.PID;
    }

    /**
     * This method returns true if this student is equal to other student
     * and false otherwise
     * @param o other Student
     * @return true is equals, false otherwise
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != Student.class) return false;
        Student student = (Student) o;
        return (
            this.firstName.equals(student.firstName) &&
            this.lastName.equals(student.lastName) &&
            this.PID.equals(student.PID)
            );
    }

    /**
     * This method hashes the Student in the order firstname, lastname, PID
     * @return hash of the student
     */
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.PID);
    }

    /**
     * This method compare the this Student and other Student in the order of
     * lastname, firstname, PID
     * @param o the other Student
     * @return 0 if Students are equal, 1 if this Student follows other,
     *         -1 if this Student precedes other
     */
    public int compareTo(Student o) {
        if (o == null) {
            throw new NullPointerException(NULL_POINTER_MESSAGE);
        }
        if (this.equals(o)) return EQUALS_VALUE;
        // compare lastnames
        if (!this.lastName.equals(o.lastName)) {
            return this.lastName.compareTo(o.lastName);
        } else if (!this.firstName.equals(o.firstName)) {
            return this.firstName.compareTo(o.firstName);
        } else {
            return this.PID.compareTo(o.PID);
        }
    }
}
