package taibahcs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.*;
import static taibahcs.TaibahCS.input;

public class TaibahCS {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Course> allCourses = new ArrayList<>();
    public static ArrayList<Convener> allConveners = new ArrayList<>();
    public static ArrayList<Lecturers> allLecturers = new ArrayList<>();
    public static ArrayList<TAs> allTAs = new ArrayList<>();
    public static ArrayList<Course> AllocateCourse = new ArrayList<>();

    // course convert to arrayList method
    public static void courseConvertToList(String fileName) throws IOException {
        try {
            // String file_name = "Courses.txt";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    // StringTokenizer class allows to break a string into tokens
                    StringTokenizer st = new StringTokenizer(line, ",");
                    String cCode = st.nextToken();
                    String cName = st.nextToken();
                    int cHours = Integer.parseInt(st.nextToken());
                    Course course = new Course(cCode, cName, cHours);
                    allCourses.add(course);
                } // End if
            } // End while
            br.close();
        } // End try
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } // End catch
    }// End method

    // convener convert to arrayList method
    public static void convenerConvertToList(String fileName) throws IOException {
        try {
            // String file_name = "Conveners.txt";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    // StringTokenizer class allows to break a string into tokens
                    StringTokenizer st = new StringTokenizer(line, "*");
                    int cID = Integer.parseInt(st.nextToken());
                    String fName = st.nextToken();
                    String lName = st.nextToken();
                    String aRank = st.nextToken();
                    String aSpe = st.nextToken();
                    Convener convener = new Convener(cID, fName, lName, aRank, aSpe);
                    allConveners.add(convener);
                } // End if
            } // End while
            br.close();
        } // End try
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } // End catch
    }// End method

    // Lecturers convert to arrayList method
    public static void lecturersConvertToList(String fileName) throws IOException {
        try {
            // String file_name = "Lecturers.txt";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    // StringTokenizer class allows to break a string into tokens
                    StringTokenizer st = new StringTokenizer(line, "*");
                    int cID = Integer.parseInt(st.nextToken());
                    String fName = st.nextToken();
                    String lName = st.nextToken();
                    String aRank = st.nextToken();
                    String aSpe = st.nextToken();
                    int maxNoCourse = Integer.parseInt(st.nextToken());
                    int qouCreditHours = Integer.parseInt(st.nextToken());
                    Lecturers lecturers = new Lecturers(cID, fName, lName, aRank, aSpe, maxNoCourse, qouCreditHours);
                    allLecturers.add(lecturers);
                } // End if
            } // End while
            br.close();
        } // End try
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } // End catch
    }// End method

    // TAs convert to arrayList method
    public static void TAsConvertToList(String fileName) throws IOException {
        try {
            // String file_name = "TAs.txt";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    // StringTokenizer class allows to break a string into tokens
                    StringTokenizer st = new StringTokenizer(line, "*");
                    int cID = Integer.parseInt(st.nextToken());
                    String fName = st.nextToken();
                    String lName = st.nextToken();
                    String aRank = st.nextToken();
                    String aSpe = st.nextToken();
                    int maxNoCourse = Integer.parseInt(st.nextToken());
                    int qouCreditHours = Integer.parseInt(st.nextToken());
                    TAs tas = new TAs(cID, fName, lName, aRank, aSpe, maxNoCourse, qouCreditHours);
                    allTAs.add(tas);
                } // End if
            } // End while
            br.close();
        } // End try
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } // End catch
    }// End method

    // Method to print anyِArrayList
    public static void printArrayList(ArrayList<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //Convener Operation 
    public static void ConOperation(Convener con) throws IOException {
        Convener ctmp = null;

        for (Convener c : allConveners) {
            if (c.getFacultyID() == con.getFacultyID()) {
                ctmp = c;
                System.out.println(ctmp);
                break;
            }
        }
        if (ctmp != null) {
            int choice;
            do {
                System.out.println("Allocate Course to Member - Enter 1");
                System.out.println("Drop course from member - Enter 2");
                System.out.println("Print Peport - Enter 3");
                System.out.println("Exit - Enter 4");
                System.out.println("Back to main menu - Enter 5");
                System.out.println();
                System.out.println("Enter your choice from menu: ");
                choice = input.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        ctmp.PrintCources();
                        ctmp.PrintLec();
                        ctmp.PrintTa();
                        ctmp.PrintUnallocatCourse();
                        System.out.println();
                        System.out.println("Enter course code : ");
                        String cCode = input.next();
                        System.out.println("Enter member id : ");
                        int mId = input.nextInt();
                        System.out.println();
                        if (mId <= 6 && mId > 0) {
                            ctmp.AssignCourseToLec(cCode, mId);
                        }
                        if (mId <= 13 & mId >= 7) {
                            ctmp.AssignCourseToTA(cCode, mId);
                        }
                        break;
                    case 2:
                        ctmp.PrintCources();
                        ctmp.PrintLec();
                        ctmp.PrintTa();
                        System.out.println();
                        System.out.println("Member Type ?\nLecturer - Enter 1 \nTAs - Enter 2");
                        int tp = input.nextInt();
                        System.out.println();
                        if (tp == 1) {
                            ctmp.DropfromLec();
                        } else {
                            ctmp.DropfromTa();
                        }
                        break;
                    case 3:
                        System.out.println("Print Report ");
                        ctmp.Display();
                        break;
                    case 4:
                        exit(0);
                        break;
                    case 5:
                        main(null);
                        break;
                    default:
                        System.out.println("Invalid choice! Try Again... ");
                        break;
                }
            } while (choice != 5);
        } else {
            System.out.println("Convener not found ");
            return;
        }

    }// end convenerOperation method

    //AddConvenerData
    public static void AddConData(Convener ctmp) {
        String cCode;
        int memId, convId;
        Course Ctmp = null;
        Lecturers Ltmp = null;
        TAs Ttmp = null;

        System.out.println("");
        printArrayList(allCourses);
        System.out.println();
        System.out.println("Enter course code ");
        cCode = input.next();
        System.out.println();

        for (Course c : allCourses) {
            if (c.getCourseCode().equals(cCode)) {
                Ctmp = c;
                break;
            }
        }

        printArrayList(allLecturers);
        System.out.println("****************************************************");
        printArrayList(allTAs);
        System.out.println();

        System.out.println("Enter member id ");
        memId = input.nextInt();
        System.out.println();

        if (memId <= 6 && memId > 0) {
            for (Lecturers L : allLecturers) {
                if (L.getFacultyID() == memId) {
                    Ltmp = L;
                    break;
                }
            }
        }
        if (memId <= 13 && memId >= 7) {
            for (TAs T : allTAs) {
                if (T.getFacultyID() == memId) {
                    Ttmp = T;
                    break;
                }
            }
        }

        AllocateCourse.add(Ctmp);
        ctmp.addCourseToMyList(Ctmp);
        allCourses.remove(Ctmp);
        System.out.println();

        if (memId <= 6 && memId > 0) {
            ctmp.addMemmberToMyList(Ltmp);
        }
        if (memId <= 13 & memId >= 7) {
            ctmp.addMemmberToMyList(Ttmp);
        }
        System.out.println("Data added successfully");
        System.out.println();
    }//end of AddConvenerData

    public static void main(String[] args) throws IOException {

        // Method to read the data from the files and print statment to show the data that read form files
        courseConvertToList("Courses.txt");
        for (Course course : allCourses) {
            System.out.println(course.toString());
        }
        System.out.println("*********************************************");

        convenerConvertToList("Conveners.txt");
        for (Convener convener : allConveners) {
            System.out.println(convener.toString());
        }
        System.out.println("*********************************************");

        lecturersConvertToList("Lecturers.txt");
        for (Lecturers lecturers : allLecturers) {
            System.out.println(lecturers.toString());
        }
        System.out.println("*********************************************");

        TAsConvertToList("TAs.txt");
        for (TAs tas : allTAs) {
            System.out.println(tas.toString());
        }

        int choice, ID, en;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\nConvener - Enter 1");
            System.out.println("Lecturer - Enter 2");
            System.out.println("Teacher Assistant - Enter 3");
            System.out.println("Exit - Enter 4");
            System.out.println();
            System.out.println("Enter your choice from Menu: ");
            choice = input.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Please enter the ID : ");
                    ID = input.nextInt();
                    System.out.println();
                    if (ID >= 14 && ID < 19) {
                        System.out.println("\nAdd data - Enter 1");
                        System.out.println("Make operation - Enter 2");
                        System.out.println();
                        System.out.println("Enter your choice from Menu: ");
                        en = input.nextInt();
                        System.out.println();
                        if (en == 1) {
                            for (Convener c : allConveners) {
                                if (c.getFacultyID() == ID) {
                                    AddConData(c);
                                    break;
                                }
                            }
                        } else if (en == 2) {
                            for (Convener c : allConveners) {
                                if (c.getFacultyID() == ID) {
                                    ConOperation(c);
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Invalid choice! try again...");
                            en = input.nextInt();
                            System.out.println();
                        }
                    } else {
                        System.out.println("Convener is not found, Try Again");
                    }

                    break;
                case 2:
                    System.out.println("Please enter the ID : ");
                    ID = input.nextInt();
                    System.out.println();
                    if (ID >= 1 && ID < 7) {
                        for (Lecturers L : allLecturers) {
                            if (L.getFacultyID() == ID) {
                                System.out.println(L.toString());
                                break;
                            }
                        }
                    } else {
                        System.out.println("Lecturers is not found, Try Again");
                    }
                    break;
                case 3:
                    System.out.println("Please enter the ID : ");
                    ID = input.nextInt();
                    System.out.println();
                    if (ID >= 7 && ID < 14) {
                        for (TAs T : allTAs) {
                            if (T.getFacultyID() == ID) {
                                System.out.println(T.toString());
                                break;
                            }
                        }
                    } else {
                        System.out.println("Teacher Assistant is not found, Try Again");
                    }
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! try again...");
            }
        } while (choice != 4);
    }

}

class Course {

    private String courseCode;
    private String courseName;
    private int creditHours;
    private boolean allocated = false;

    public Course() {

    }

    public Course(String courseCode, String courseName, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setAllocatedToCourse(boolean allocated) {
        this.allocated = allocated;
    }

    public boolean isAllocatedToCourse() {
        return allocated;
    }

    @Override
    public String toString() {
        return "Course{" + "courseCode : " + courseCode + ", courseName : " + courseName
                + ", creditHours : " + creditHours + " allocated : " + isAllocatedToCourse() + '}';
    }

}

class FacultyMember {

    private int FacultyID;
    private String firstName;
    private String lastName;
    private String academicRank;
    private String academicSpecialization;

    public FacultyMember() {

    }

    public FacultyMember(int FacultyID, String firstName, String lastName, String academicRank, String academicSpecialization) {
        this.FacultyID = FacultyID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicRank = academicRank;
        this.academicSpecialization = academicSpecialization;
    }

    public void setFacultyID(int FacultyID) {
        this.FacultyID = FacultyID;
    }

    public int getFacultyID() {
        return FacultyID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank = academicRank;
    }

    public String getAcademicRank() {
        return academicRank;
    }

    public void setAcademicSpecialization(String academicSpecialization) {
        this.academicSpecialization = academicSpecialization;
    }

    public String getAcademicSpecialization() {
        return academicSpecialization;
    }

    @Override
    public String toString() {
        return "Faculty ID : " + FacultyID + ", firstName : " + firstName + ", lastName : " + lastName
                + ", academicRank : " + academicRank + ", academicSpecialization : " + academicSpecialization;
    }

}

class Convener extends FacultyMember {

    private ArrayList<Course> courses;
    private ArrayList<Lecturers> LM;
    private ArrayList<TAs> TM;

    public Convener() {
        courses = new ArrayList<>();
        LM = new ArrayList<>();
        TM = new ArrayList<>();
    }

    public Convener(int FacultyID, String firstName, String lastName, String academicRank, String academicSpecialization) {
        super(FacultyID, firstName, lastName, academicRank, academicSpecialization);
        courses = new ArrayList<>();
        LM = new ArrayList<>();
        TM = new ArrayList<>();
    }

    public void addCourseToMyList(Course course) {
        for (Course conC : courses) {
            if (conC.getCourseCode().equals(course.getCourseCode()))// course found in list dont add it 
            {
                System.out.println("course Already exist in list cannot add again  ");
                return;
            }
        }
        //course not found add it to list 
        courses.add(course);
    }

    // add new lecturer or TA to list of convener members
    public void addMemmberToMyList(FacultyMember m) {
        if (m instanceof Lecturers) {
            for (Lecturers Lec : LM) {
                if (m.getFacultyID() == Lec.getFacultyID()) // lec found in list dont add it 
                {
                    System.out.println("Lecturers  Already exist in list  cannot add again  ");
                    return;
                }
            }
            //Lecturers not found , add it to list 
            Lecturers L = (Lecturers) m;//(Lecturers)convert object m from FacultyMember to Lecturers
            LM.add(L);
        } else {
            for (TAs ts : TM) {
                if (m.getFacultyID() == ts.getFacultyID()) // course found in list dont add it 
                {
                    System.out.println("Teacher assistant   Already exist in list  cannot add again  ");
                    return;
                }
            }
            //Teacher assistant not found , add it to list 
            TAs T = (TAs) m;
            TM.add(T);
        }
    }

    public void AssignCourseToLec(String courseId, int LecId) {
        Course assCoTo = null;
        Lecturers toMemAss = null;
        //search for Lec member    
        for (Lecturers Lec : LM) {
            if (Lec.getFacultyID() == LecId) // Lecturers found  
            {
                toMemAss = Lec;
                break;
            }
        }
        //search for Course
        for (Course C : courses) {
            if (C.getCourseCode().equals(courseId)) // course found  
            {
                assCoTo = C;
                break;
            }
        }
        //if not found course or memmber cannot assign course 
        if (toMemAss == null || assCoTo == null) {
            System.out.println("cannot assign course to memmber (course or memmber not found");
            return;
        }
        //course and member founded assign course 
        assCoTo.setAllocatedToCourse(true);
        toMemAss.setAssignedCourses(assCoTo);
    }

    public void DropfromLec() {
        int LecId;
        String CoId;
        Course dCo = null;
        Lecturers dMem = null;
        System.out.println("Enter Lecturer id to drop course from his list ");
        LecId = input.nextInt();
        System.out.println("Enter Course code to drop  from Lecturer List ");
        CoId = input.next();
        //search for lecturer member    
        for (Lecturers Lec : LM) {
            if (Lec.getFacultyID() == LecId) // Lecturers found  
            {
                dMem = Lec;
                break;
            }
        }
        //search for Course
        for (Course Cr : courses) {
            if (Cr.getCourseCode().equals(CoId)) // Course found  
            {
                dCo = Cr;
                break;
            }
        }
        //if not found course or memmber cannot drop course 
        if (dMem == null || dCo == null) {
            System.out.println("cannot Drop course from  memmber (course or memmber not found");
            return;
        }
        //course and member founded drop course
        boolean result = dMem.dropCourse(dCo);
        if (result) {
            dCo.setAllocatedToCourse(false);
        }
    }

    public void AssignCourseToTA(String courseId, int TaId) {
        Course assCoTo = null;
        TAs toMemAss = null;
        //search for Ta member    
        for (TAs Tec : TM) {
            if (Tec.getFacultyID() == TaId) // Ta found  
            {
                toMemAss = Tec;
                break;
            }
        }
        //search for Course
        for (Course Cr : courses) {
            if (Cr.getCourseCode().equals(courseId)) // course found  
            {
                assCoTo = Cr;
                break;
            }
        }
        //if not found course or memmber cannot assign course 
        if (toMemAss == null || assCoTo == null) {
            System.out.println("cannot assign  course to memmber (course or memmber not found");
            return;
        }
        //course and member founded assign course 
        assCoTo.setAllocatedToCourse(true);
        toMemAss.setAssignedCourses(assCoTo);
    }

    public void DropfromTa() {
        int TaId;
        String CoId;
        Course dCo = null;
        TAs dMem = null;
        System.out.println("Enter TAs id to drop course from his list ");
        TaId = input.nextInt();
        System.out.println("Enter Course code to drop from TAs List ");
        CoId = input.next();
        //search for Ta member    
        for (TAs Tec : TM) {
            if (Tec.getFacultyID() == TaId) // Ta found  
            {
                dMem = Tec;
                break;
            }
        }
        //search for Course
        for (Course Cr : courses) {
            if (Cr.getCourseCode().equals(CoId)) // course found  
            {
                dCo = Cr;
                break;
            }
        }
        //if not found course or memmber cannot drop course 
        if (dMem == null || dCo == null) {
            System.out.println("cannot Drop course from  memmber (course or memmber not found");
            return;
        }
        //course and member founded drop course 
        boolean result = dMem.dropCourse(dCo);
        if (result) {
            dCo.setAllocatedToCourse(false);
        }
    }

    public void PrintCources() {
        for (Course C : courses) {
            System.out.println(C);
        }
    }

    public void PrintUnallocatCourse() {
        for (Course C : courses) {
            if (C.isAllocatedToCourse() == false) {
                System.out.println(C.toString());
            }
        }
    }

    public void PrintMembers() {
        System.out.println("**********Members Belong To Me*********");
        PrintLec();
        System.out.println("***************************************");
        PrintTa();
    }

    public void PrintLec() {
        for (Lecturers L : LM) {
            System.out.println(L);
        }
    }

    public void PrintTa() {
        for (TAs T : TM) {
            System.out.println(T);
        }
    }

    public void Display() {

        System.out.println(toString());
        System.out.println();

        if (courses == null) {
            System.out.println("There is no courses belong to me");
            System.out.println("There Unallocated courses");
            PrintUnallocatCourse();
        } else {
            PrintCources();
            System.out.println("There Unallocated courses");
            PrintUnallocatCourse();
        }
        if (LM == null && TM == null) {
            System.out.println("There is no members belong to me");
        } else {
            PrintMembers();
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Convener : " + super.toString() + "\n";
    }

}

class Lecturers extends FacultyMember {

    private int maximumNumberOfCourses;
    private int quotaOfCreditHours;
    private Course[] assignedCourses;
    private int count = 0;
    private int currHours = 0;

    public Lecturers() {
        this.maximumNumberOfCourses = 0;
        this.quotaOfCreditHours = 0;
        this.assignedCourses = new Course[maximumNumberOfCourses];
    }

    public Lecturers(int FacultyID, String firstName, String lastName, String academicRank, String academicSpecialization,
            int maximumNumberOfCourses, int quotaOfCreditHours) {
        super(FacultyID, firstName, lastName, academicRank, academicSpecialization);
        this.maximumNumberOfCourses = maximumNumberOfCourses;
        this.quotaOfCreditHours = quotaOfCreditHours;
        this.assignedCourses = new Course[maximumNumberOfCourses];
    }

    public void setMaximumNumberOfCourses(int maximumNumberOfCourses) {
        this.maximumNumberOfCourses = maximumNumberOfCourses;
    }

    public int getMaximumNumberOfCourses() {
        return maximumNumberOfCourses;
    }

    public void setQuotaOfCreditHours(int quotaOfCreditHours) {
        this.quotaOfCreditHours = quotaOfCreditHours;
    }

    public int getQuotaOfCreditHours() {
        return quotaOfCreditHours;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCurrentHour(int currentHour) {
        this.currHours = currentHour;
    }

    public int getCurrentHour() {
        return currHours;
    }

    public void setAssignedCourses(Course course) {
        if ((count < maximumNumberOfCourses) && ((currHours + course.getCreditHours()) <= quotaOfCreditHours)) {
            assignedCourses[count] = course;
            count++;
            currHours = currHours + course.getCreditHours();
            System.out.println("Course Assigned successfully");
        } else {
            if (count == maximumNumberOfCourses) {
                System.out.println("course cannot assigned you have reached max number of courses");
            } else {
                System.out.println("course cannot assigned you have reached max number of hourse");
            }
        }
    }

    public String getAssignedCourses() {
        String st = null;
        for (int i = 0; i < count; i++) {
            st = "  -  " + assignedCourses[i].getCourseName();
        }
        return st;
    }

    public boolean dropCourse(Course c) {
        for (int i = 0; i < count; i++) {
            if (c.getCourseCode().equals(assignedCourses[i].getCourseCode())) {
                for (int j = i; j < count; j++) {
                    assignedCourses[j] = assignedCourses[j + 1];
                }
                count--;
                currHours = currHours - c.getCreditHours();
                System.out.println("Course Dropped successfully");
                return true;
            }
        }
        System.out.println("course not found can't drop");
        return false;
    }

    @Override
    public String toString() {
        return "Lecturers : " + super.toString() + " maximumNumberOfCourses : " + maximumNumberOfCourses
                + " Current Cource : " + count + " quotaOfCreditHours : " + quotaOfCreditHours
                + " Current hours : " + currHours + " assignedCourses : " + getAssignedCourses() + '\n';
    }

}

class TAs extends FacultyMember {

    private int maximumNumberOfCourses;
    private int quotaOfCreditHours;
    private Course[] assignedCourses;
    private int count = 0;
    private int currHours = 0;

    public TAs() {
        this.maximumNumberOfCourses = 0;
        this.quotaOfCreditHours = 0;
        this.assignedCourses = new Course[maximumNumberOfCourses];
    }

    public TAs(int FacultyID, String firstName, String lastName, String academicRank, String academicSpecialization,
            int maximumNumberOfCourses, int quotaOfCreditHours) {
        super(FacultyID, firstName, lastName, academicRank, academicSpecialization);
        this.maximumNumberOfCourses = maximumNumberOfCourses;
        this.quotaOfCreditHours = quotaOfCreditHours;
        this.assignedCourses = new Course[maximumNumberOfCourses];
    }

    public void setMaximumNumberOfCourses(int maximumNumberOfCourses) {
        this.maximumNumberOfCourses = maximumNumberOfCourses;
    }

    public int getMaximumNumberOfCourses() {
        return maximumNumberOfCourses;
    }

    public void setQuotaOfCreditHours(int quotaOfCreditHours) {
        this.quotaOfCreditHours = quotaOfCreditHours;
    }

    public int getQuotaOfCreditHours() {
        return quotaOfCreditHours;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCurrentHour(int currentHour) {
        this.currHours = currentHour;
    }

    public int getCurrentHour() {
        return currHours;
    }

    public void setAssignedCourses(Course course) {
        if ((count < maximumNumberOfCourses) && ((currHours + course.getCreditHours()) <= quotaOfCreditHours)) {
            assignedCourses[count] = course;
            count++;
            currHours = currHours + course.getCreditHours();
            System.out.println("Course Assigned successfully");
        } else {
            if (count == maximumNumberOfCourses) {
                System.out.println("course cannot assigned you have reached max number of courses");
            } else {
                System.out.println("course cannot assigned you have reached max number of hourse");
            }
        }
    }

    public String getAssignedCourses() {
        String st = null;
        for (int i = 0; i < count; i++) {
            st = "  -  " + assignedCourses[i].getCourseName();
        }
        return st;
    }

    public boolean dropCourse(Course c) {
        for (int i = 0; i < count; i++) {
            if (c.getCourseCode().equals(assignedCourses[i].getCourseCode())) {
                for (int j = i; j < count; j++) {
                    assignedCourses[j] = assignedCourses[j + 1];
                }
                count--;
                currHours = currHours - c.getCreditHours();
                System.out.println("Course Dropped successfully");
                return true;
            }
        }
        System.out.println("course not found can't drop");
        return false;
    }

    @Override
    public String toString() {
        return "TAs : " + super.toString() + " maximumNumberOfCourses : " + maximumNumberOfCourses
                + " Current Cource : " + count + " quotaOfCreditHours : " + quotaOfCreditHours
                + " Current hours : " + currHours + " assignedCourses : " + getAssignedCourses() + '\n';
    }

}
