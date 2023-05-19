/*
 * Name: Farris Danish
 * PID: A17401247
 * email: fbinsyahrilakmar@ucsd.edu
 * Sources used: Java 17 JDK
 *
 * This file tests methods from the three classes.
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.junit.*;

/**
 * This class contains unit tests for the methods from the 3 classes
 */
public class CustomTester {

    // Test variables
    // Student
    private Student st1, st2;
    private Object nonSt;

    // Course
    private Course cse12;
    private Course tutorial;

    // Sanctuary
    private Sanctuary sanctPartial;

    /**
     * This sets up the test fixture. Invoked before every test.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        st1 = new Student("Noah", "Ali", "A12345678");
        st2 = new Student("Yasmeen", "Ahmad", "A12345679");
        nonSt = new Object();

        cse12 = new Course("CSE", "12", "Data Structure", 1);

        tutorial = new Course("CSE", "999", "Tutorial for 3 people", 3);
        tutorial.enrolled.add(new Student("A", "A", "A"));
        tutorial.enrolled.add(new Student("B", "B", "B"));
        tutorial.enrolled.add(new Student("C", "C", "C"));

        sanctPartial = new Sanctuary(50, 2);
        sanctPartial.sanctuary.put("Dog", 20);
        sanctPartial.sanctuary.put("Cat", 20);
    }

    // ----------------------Student-------------------------
    /**
     * Test if false because null
     */
    @Test
    public void testStudentEquals() {
        assertEquals("false because not student", false, st1.equals(nonSt));
    }

    /**
     * Test if false because not equal
     */
    @Test
    public void testStudentEqualsFalse() {
        assertEquals("false because not equal", false, st1.equals(st2));
    }

    /**
     * Test if true if equals
     */
    @Test
    public void testStudentEqualsTrue() {
        assertEquals(
            "true because equal",
            true,
            st1.equals(new Student("Noah", "Ali", "A12345678"))
        );
    }

    @Test
    public void testStudentEqualsNull() {
        assertEquals("false because o is null", false, st1.equals(null));
    }

    @Test
    public void testStudentGetLastName() {
        assertEquals("return Ali", "Ali", st1.getLastName());
    }

    @Test
    public void testStudentGetFirstName() {
        assertEquals("returns Noah", "Noah", st1.getFirstName());
    }

    @Test
    public void testStudentGetPID() {
        assertEquals("returns A12345678", "A12345678", st1.getPID());
    }

    @Test
    public void testStudentCompareTo() {
        String str1 = "A12345678";
        String str2 = "A12345699";
        assertEquals(
            "return -1 because this is smaller",
            str1.compareTo(str2),
            st1.compareTo(new Student("Noah", "Ali", "A12345699"))
        );
    }

    @Test
    public void testStudentCompareToLarger() {
        String str1 = "A12345678";
        String str2 = "A12345617";
        assertEquals(
            "return 1 because this is larger",
            str1.compareTo(str2),
            st1.compareTo(new Student("Noah", "Ali", "A12345617"))
        );
    }

    // -----------------------Course---------------------------
    @Test
    public void testCourseGetDepartment() {
        assertEquals("return CSE", "CSE", cse12.getDepartment());
    }

    @Test
    public void testCourseGetNumber() {
        assertEquals("return 12", "12", cse12.getNumber());
    }

    @Test
    public void testCourseGetDescription() {
        assertEquals(
            "return Data Structure",
            "Data Structure",
            cse12.getDescription()
        );
    }

    @Test
    public void testCourseGetCapacity() {
        assertEquals("returns 1", 1, cse12.getCapacity());
    }

    @Test
    public void testCourseEnroll() {
        assertTrue("add 1 student", cse12.enroll(st1));
        assertFalse("false because full", cse12.enroll(st2));
    }

    @Test
    public void testCourseDrop() {
        cse12.enrolled.add(st1);
        assertFalse("false because student is not enrolled", cse12.drop(st2));
    }

    @Test
    public void testCourseCancel() {
        tutorial.cancel();
        assertEquals("size should be 0", 0, tutorial.enrolled.size());
        assertEquals("department unchanged", "CSE", tutorial.getDepartment());
        assertEquals("number unchanged", "999", tutorial.getNumber());
        assertEquals("capacity unchanged", 3, tutorial.getCapacity());
        assertEquals(
            "description unchanged",
            "Tutorial for 3 people",
            tutorial.getDescription()
        );
    }

    @Test
    public void testGetEnrolledCount() {
        Course course1 = new Course("CSE", "888", "test1", 5);
        for (int i = 0; i < 3; i++) {
            course1.enroll(new Student("Student" + i, "st", "A1"));
        }
        assertEquals("should return 3", 3, course1.getEnrolledCount());
    }

    @Test
    public void testGetAvailableSeats() {
        Course course1 = new Course("CSE", "888", "test1", 5);
        for (int i = 0; i < 3; i++) {
            course1.enroll(new Student("Student" + i, "st", "A1"));
        }
        assertEquals("Should return 2", 2, course1.getAvailableSeats());
    }

    @Test
    public void testCourseGetRoster() {
        ArrayList<Student> arr = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arr.add(new Student("Student" + i, "st", "A1"));
        }
        Collections.sort(arr);
        Course course1 = new Course("CSE", "888", "test1", 5);
        for (int i = 0; i < 3; i++) {
            course1.enroll(new Student("Student" + i, "st", "A1"));
        }
    }

    // ----------------------------Sanctuary----------------------------
    @Test
    public void testSanctConstructor() {
        HashMap<String, Integer> hm = new HashMap<>(3);
        Sanctuary sanct1 = new Sanctuary(50, 3);
        assertEquals("Should be 50", 50, sanct1.getMaxAnimals());
        assertEquals("should be 3", 3, sanct1.getMaxSpecies());
        assertEquals(hm, sanct1.sanctuary);
    }

    /**
     * The species is already in the sanctuary but adding num will overflow it
     */
    @Test
    public void testSanctRescuePartial() {
        assertEquals((Integer) 20, (Integer) sanctPartial.sanctuary.get("Dog"));
        assertEquals(5, sanctPartial.rescue("Dog", 15));
        assertEquals((Integer) 30, (Integer) sanctPartial.sanctuary.get("Dog"));
        assertEquals((Integer) 20, (Integer) sanctPartial.sanctuary.get("Cat"));
    }

    /**
     * Add another species when species is already at max but still have space
     * for same species
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        assertEquals(10, sanctPartial.rescue("Goat", 10));
        assertEquals(
            10,
            sanctPartial.getMaxAnimals() - sanctPartial.getTotalAnimals()
        );
    }

    @Test
    public void testSanctReleasePartial() {
        assertEquals((Integer) 20, (Integer) sanctPartial.sanctuary.get("Dog"));
        sanctPartial.release("Dog", 10);
        assertEquals((Integer) 10, (Integer) sanctPartial.sanctuary.get("Dog"));
    }

    @Test
    public void testSanctReleaseTooMany() {
        assertThrows(
            IllegalArgumentException.class,
            () -> sanctPartial.release("Dog", 30)
        );
    }
}
