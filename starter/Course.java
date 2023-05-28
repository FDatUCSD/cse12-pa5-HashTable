/*
 * Name: Farris Danish
 * PID: A17401247
 * email: fbinsyahrilakmar@ucsd.edu
 * Sources used: Write-up, JDK 17 documentation
 *
 * This file contains the implementation of Hashset for the application of
 * enrollment of classes
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * This class contains implementation of the Hashset for the application
 * of class enrollment
 */
public class Course {

    private static final String NULL_STUDENT_MESSAGE = "Student cannot be null";
    private static final String NULL_ARG_MESSAGE = "Arguments cannot be null";
    private static final String LESS_THAN_ZERO_MESSAGE =
        "Capacity cannot be less than zero";
    /* Instance variables */
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department, number, description;

    /**
     * This is the constructor for the course
     * @param department the department of this course
     * @param number the number for this course
     * @param description the description of this course
     * @param capacity the maximum number of students for this course
     */
    public Course(
        String department,
        String number,
        String description,
        int capacity
    ) {
        if (department == null || number == null || description == null) {
            throw new IllegalArgumentException(NULL_ARG_MESSAGE);
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_MESSAGE);
        }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = new HashSet<>(capacity);
    }

    /**
     * This method returns the department name of this course
     * @return the department name of this course
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * This method returns the number of the course
     * @return the number of this course
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * This method returns the description of the course
     * @return the description of the course
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method returns the maximum number of students that can enroll
     * in the course
     * @return the capacity of the course
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * This method enrolls a student into the course. If the course is full
     * return false
     * @param student the student to be enrolled
     * @return true if successful, false otherwise
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(NULL_STUDENT_MESSAGE);
        }
        if (enrolled.contains(student)) return false;

        if (isFull()) return false;

        enrolled.add(student);
        return true;
    }

    /**
     * This method drops the student from the course
     * @param student the student to be dropped
     * @return true if successful, false otherwise
     */
    public boolean drop(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(NULL_STUDENT_MESSAGE);
        }

        if (!enrolled.contains(student)) return false;

        enrolled.remove(student);
        return true;
    }

    /**
     * This method drops all students from the course
     */
    public void cancel() {
        this.enrolled.clear();
    }

    /**
     * This method returns true if the course is at capacity
     * @return true is full, false otherwise
     */
    public boolean isFull() {
        return this.enrolled.size() == this.capacity;
    }

    /**
     * This method returns the amount of students enrolled in this course
     * @return the number of students in this course
     */
    public int getEnrolledCount() {
        return this.enrolled.size();
    }

    /**
     * This methods return the number of seats left in the course
     * @return the number of available seats left in the course
     */
    public int getAvailableSeats() {
        return this.capacity - this.enrolled.size();
    }

    /**
     * This method returns a shallow copy of the HashSet of student
     * @return a shallow copy of enrollment list
     */
    @SuppressWarnings("unchecked")
    public HashSet<Student> getStudents() {
        return (HashSet<Student>) this.enrolled.clone();
    }

    /**
     * Turns the collection of enrolled students to a sorted list and returns
     * the sorted list
     * @return the sorted array list of enrolled students
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> roster = new ArrayList<>(enrolled);
        Collections.sort(roster);
        return roster;
    }

    /**
     * This method returns a string representation of the course in the format
     * department number [capacity] description
     * @return string representation of the course
     */
    public String toString() {
        String output = String.format(
            "%s %s [%d] %s",
            getDepartment(),
            getNumber(),
            getCapacity(),
            getDescription()
        );

        return output;
    }
}
