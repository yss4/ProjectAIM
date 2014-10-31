package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import com.cse308.projectaim.hibernate.types.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.log4j.Logger;

public class ProjectAIMPopulator {

    private Logger logger = Logger.getLogger(this.getClass());
    private Random rand = new Random();
    private final String usernames[] = {
        "Leman Akoglu",
        "Leo Bachmair",
        "Hussein Badr",
        "Michael Bender",
        "Jing Chen",
        "Yejin Choi",
        "Rezaul Chowdhury",
        "Samir Das",
        "Ahmad Esmaili",
        "Mike Ferdman",
        "Paul Fodor",
        "Anshul Gandhi",
        "Jie Gao",
        "Phillipa Gill",
        "Xianfeng Gu",
        "Himanshu Gupta",
        "Rob Johnson",
        "Arie Kaufman",
        "Rob Kelly",
        "Michael Kifer",
        "Annie Liu",
        "Yanni Liu",
        "Long Lu",
        "Richard McKenna",
        "Klaus Mueller",
        "Luis Ortiz",
        "Donald Porter",
        "Hong Qin",
        "C.R. Ramakrishnan",
        "I.V. Ramakrishnan",
        "Dimitris Samaras",
        "Tony Scarlatos",
        "R. Sekar",
        "Vyas Sekar",
        "Radu Sion",
        "Steve Skiena",
        "Scott Smolka",
        "Eugene Stark",
        "Scott Stoller",
        "Allen Tannenbaum",
        "Michael Tashbook",
        "Anita Wasilewska",
        "Larry Wittie",
        "Jennifer Wong",
        "Erez Zadok",
        "Rong Zhao"
    };
    private final String evaluators[] = {
        "Ahmad Esmaili",
        "Rob Kelly",
        "Richard McKenna",
        "R. Sekar",
        "Steve Skiena",
        "Scott Smolka",
        "Scott Stoller",
        "Jennifer Wong",
        "Rong Zhao"
    };
    private final String cicMembers[] = {
        "Arie Kaufman",
        "Leo Bachmair"
    };
    private final String peoDescriptions[] = {
        "Establish themselves as practicing professionals or engage in advanced study in computer science, information technology, or related areas.",
        "Advance professionally through organized training or self-learning in areas related to computer science and information technology."
    };
    private final String courseOutcomeDescriptions[] = {
        "An ability to define and use discrete structures such as functions, relations, and sets.",
        "An ability to compute with recursion as a basic paradigm.",
        "An ability to use logic and basic proof techniques, such as mathematicalinduction.",
        "An ability to define and use abstract models of computation such as finite and push-down automata, and analyze their relative expressive power.",
        "An ability to define, use, and convert between abstract machine models andformal languages.",
        "Knowledge of the power and inherent limitations of algorithmic computation",
        "An ability to define and use abstract models of computation such as finite and push-down automata, and analyze their relative expressive power.",
        "An ability to define, use, and convert between abstract machine models and formal languages.",
        "Knowledge of the power and inherent limitations of algorithmic computation.",
        "An ability to define and use classical combinatorial algorithms for problems such as sorting, shortest paths and minimum spanning trees.", // BS:CS Student Outcome #1
        "An ability to perform project planning, requirements analysis, and system/test design.",
        "Knowledge of computational intractability and NP completeness.", // BS:CS Student Outcome #2
        "An ability to write procedural programs using variable declarations, conditionals, loops, and simple method calls.",
        "An ability to program in an object oriented language, using concepts such as object classes, encapsulation, inheritance, and polymorphism.",
        "An ability to use fundamental data structures such as arrays.",
        "An ability to program in an object oriented language, using concepts such as object classes, encapsulation, inheritance, and polymorphism.",
        "An ability to program using sophisticated features of object oriented programming.",
        "An ability to define and use data types, and use algorithmic design.",
        "An ability to apply techniques of object-oriented programming in the context of large-scale programs.",
        "An ability to develop and evaluate programs in C.",
        "An ability to program in assembly language and understand the relationship to C programming.",
        "Knowledge of key aspects of efficient execution of high-level programs.",
        "An ability to apply techniques of object-oriented programming in the context of large-scale programs.",
        "An ability to use recursion to solve programming problems.",
        "An ability to design database management systems through E/R design and the theory of normalization.",
        "Knowledge of fundamental concepts underlying modern operating systems, including virtual memory and multiprogramming.",
        "An ability to program in different language paradigms and evaluate their relative benefits.",
        "An understanding of the key concepts in the implementation of common features of programming languages.",
        "Knowledge of the conceptual foundations of computer network and layered protocol architecture.",
        "Knowledge of different types of computer networks, such as WANs, LANs, wireless networks, and circuit-packet-switched networks, "
        + "and between different paradigms of network applications (peer-to-peer/client-server).",
        "An understanding of processor organization and the memory hierarchy.",
        "An understanding of the design principles of instruction set architecture in terms of the programming flexibility, hardware complexity, and implementation "
        + "efficiency of complex versus reduced instruction set computers.",
        "Knowledge of implementation techniques such as computer arithmetic, memory hierarchy, pipelining, and disk I/O.", // BS:CS Student Outcome #3
        "An ability to work as a team to produce software systems that meet specifications while satisfying an implementation schedule.", // BS:CS Student Outcome #4
        "An understanding of ethical and social issues arising from computing and information systems.",
        "An understanding of legal, philosophical, political and economic issues related to computers.",
        "An understanding of ethical issues in software design and computing.", // BS:CS Student Outcome #5
        "An ability to communicate with a range of audiences.",
        "An ability to give effective oral presentations.",
        "An ability to produce professional quality oral/written presentations of system designs, reviews, and project demonstrations.", // BS:CS Student Outcome #6
        "An ability to discuss the benefits offered by computing technology in many different areas and the risks and problems associated with these technologies.", // BS:CS Student Outcome #7
        "An ability to program with sound code structure and use systematic software debugging and testing techniques.",
        "An understanding the importance of programming style and modularity to the construction and evolution of robust software.",
        "An ability to use programming tools such as syntax-directed editors, debuggers, documentation generators and testing frameworks.",
        "An understanding of the importance of programming style and modularity to the construction and evolution of robust software.",
        "An ability to use programming tools such as syntax-directed editors, debuggers, execution profilers, documentation generators, and revision-control systems.",
        "An ability to use relational query languages",
        "Working knowledge of, and ability to use, the components of operating systems, including file systems, the I/O subsystem, and the CPU scheduler.",
        "Knowledge of, and ability to use, language features used in current programming languages.",
        "An understanding of the Internet architecture and the TCP/IP protocol suite, and details of representative protocols at the application, transport network and data link layers.",
        "An ability to use current network programming technology.", // BS:CS StudentOutcome #8
        "An understanding of the importance of time and memory efficiency in algorithm design.",
        "An understanding of the importance of time and memory efficiency in algorithm and data structure design.",
        "An ability to perform worst-case asymptotic algorithm analysis", // BS:CS StudentOutcome #9
        "An ability to construct software modules consisting of several hundred lines of code.",
        "An ability to use current design principles to systematically design, code, debug, and test programs of about two thousand lines of code",
        "AN ability to apply techniques of object-oriented programming in the context of large-scale programs",
        "An ability to design and implement a database system, via a significant project.",
        "An ability to design and implement simplified versions of the main modules of operating systems." // BS:CS StudentOutcome #10
    };
    private final String studentOutcomeDescriptions[] = {
        "An ability to apply knowledge of computing and mathematics appropriate to the discipline",
        "An ability to analyze a problem, and identify and define the computing requirements appropriate to its solution",
        "An ability to design, implement, and evaluate a computer-based system, process, component or program to meet desired needs",
        "An ability to function effectively on teams to accomplish a common goal",
        "An understanding of professional, ethical, legal, security and social issues and responsibilities",
        "An ability to communicate effectively with a range of audiences",
        "An ability to analyze the local and global impact of computing on individuals, organizations, and society",
        "An ability to use current techniques, skills, and tools necessary for computing practice",
        "An ability to apply mathematical foundations, algorithmic principles, and computer science theory in the modeling and design of computer-based systems in a way that demonstrates comprehension of the tradeoffs involved in design choices",
        "An ability to apply design and development principles in the construction of software systems of varying complexity."
    };
    private final Map<String, String> courseDescriptions = new HashMap<String, String>() {
        {
            put("CSE114", "Computer Science I");
            put("CSE160", "Computer Science A: Honors");
            put("CSE214", "Computer Science II");
            put("CSE215", "Foundations of Computer Science");
            put("CSE219", "Computer Science III");
            put("CSE220", "Systems-Level Programming");
            put("CSE260", "Computer Science B: Honors");
            put("CSE300", "Technical Communications");
            put("CSE303", "Introduction to the Theory of Computation");
            put("CSE305", "Principles of Databases");
            put("CSE306", "Operating Systems");
            put("CSE307", "Principles of Programming Languages");
            put("CSE308", "Software Engineering");
            put("CSE310", "Data Communication and Networks");
            put("CSE312", "Legal, Social, and Ethical Issues");
            put("CSE320", "Computer Organization and Architecture");
            put("CSE350", "Theory of Computation");
            put("CSE373", "Analysis of Algorithms");
        }
    };
    private List<AIMFileWrapper> sampleFiles;
    private AIMStore aimStore;
    private List<List> createEntities;
    private List<List> deleteEntities;
    private List<PEO> peos;
    private List<User> users;
    private List<Semester> semesters;
    private List<Course> courses;
    private List<CourseOutcome> courseOutcomes;
    private List<DegreeProgram> degreePrograms;
    private List<CourseOffering> courseOfferings = new ArrayList<CourseOffering>();
    private List<StudentOutcome> studentOutcomes = new ArrayList<StudentOutcome>();

    public ProjectAIMPopulator() {
        // Retrieve the AIMStore:
        aimStore = AIMStore.getInstance();

        // Create collections for the AIMEntities:
        peos = new ArrayList<PEO>();
        users = new ArrayList<User>();
        courseOutcomes = new ArrayList<CourseOutcome>();
        semesters = new ArrayList<Semester>();
        degreePrograms = new ArrayList<DegreeProgram>();
        courses = new ArrayList<Course>();
        sampleFiles = new ArrayList<AIMFileWrapper>();
        studentOutcomes = new ArrayList<StudentOutcome>();

        // Store all collections in yet another collection:
        createEntities = new ArrayList<List>() {
            {
                add(users);
                add(degreePrograms);
                add(peos);
                add(courses);
                add(semesters);
                add(courseOfferings);
                add(courseOutcomes);
                add(studentOutcomes);
            }
        };

        deleteEntities = new ArrayList<List>();
        /*        deleteEntities = new ArrayList<List>() {
         {
         add(degreePrograms);
         add(peos);
         add(courses);
         add(courseOutcomes);
         add(users);
         //add(semesters);
         }
         };
         */
        // Initialize all the data
        initAll();
    }

    public void purgeDatabase() {
        for (List<AIMEntity> entityList : deleteEntities) {
            for (AIMEntity entity : entityList) {
                try {
                    if (entity instanceof Semester) {
                        logger.info("Deleting Semester: " + ((Semester) entity).getDisplayName());
                    } else {
                        logger.info("Deleting " + entity.toString() + "....");
                    }
                    aimStore.delete(entity);
                } catch (ProjectAimException ex) {
                    logger.error("Can't delete " + entity.toString() + ": " + ex.getMessage());
                }
            }
        }
    }

    public void populateDatabase() {
        for (List<AIMEntity> entityList : createEntities) {
            for (AIMEntity entity : entityList) {
                try {
                    AIMEntity e = aimStore.retrieve(entity);
                    aimStore.update(entity);
                    logger.info("Updated " + entity.toString());
                } catch (ProjectAimException ex) {
                    try {
                        aimStore.create(entity);
                        logger.info("Created " + entity.toString());
                    } catch (Exception exx) {
                        logger.error("Can't create/update " + entity.toString() + ": " + exx.getMessage());
                    }
                }
            }
        }
    }
    
    public final void initAll() {
        initUsers();
        initDegreePrograms();
        initPEOs();
        initCourses();
        initSemesters();
        initCourseOfferings();
        initCourseOutcomes();
        initStudentOutcomes();
    }

    public void initUsers() {
        for (String username : usernames) {
            User user = new User();
            user.setUsername(username);
            user.setEmailAddress(username.toLowerCase().replaceAll(" ", ".") + "@cs.stonybrook.edu");
            user.setPassword(new StringBuilder(username.toLowerCase().replaceAll(" ", "")).reverse().toString());
            user.setCicMemberStatus(Arrays.asList(cicMembers).contains(username));
            user.setEvaluatorStatus(Arrays.asList(evaluators).contains(username));
            users.add(user);
        }
    }

    public void initPEOs() {
        String idCode = "[BS:CS]#";
        int sequence = 0;
        for (String desc : peoDescriptions) {
            PEO peo = new PEO();
            peo.setDescription(desc);
            peo.setShortName((desc.split(" ")[0]));
            peo.setId(idCode + sequence);
            peo.setSequence(sequence++);
            peos.add(peo);
        }
    }

    public void initCourses() {
        int userindex;
        for (String courseName : courseDescriptions.keySet()) {
            Course course = new Course();
            course.setId(courseName);
            course.setName(courseDescriptions.get(courseName));
            course.setDegreePrograms(new HashSet(degreePrograms));
            userindex = rand.nextInt(users.size());
            course.setPrimaryCourseCoordinator(users.get(userindex));
            for (int i = 0; i < rand.nextInt(5); i++, userindex = rand.nextInt(users.size())) {
                Set<User> alts = course.getAlternateCourseCoordinator();
                while (alts.contains(users.get(userindex))) {
                    userindex = rand.nextInt(users.size());
                }
                alts.add(users.get(userindex));
            }
            courses.add(course);
        }
    }

    public void initSemesters() {
        for (int i = 2000; i < 2020; i++) {
            for (int j = 1; j <= 4; j++) {
                String term;
                Calendar startDate = Calendar.getInstance();
                startDate.set(Calendar.YEAR, i);
                Calendar endDate = Calendar.getInstance();
                endDate.set(Calendar.YEAR, i);
                switch (j) {
                    case 3:
                        term = "Fall";
                        startDate.set(Calendar.MONTH, Calendar.SEPTEMBER);
                        startDate.set(Calendar.DAY_OF_MONTH, 1);
                        endDate.set(Calendar.MONTH, Calendar.DECEMBER);
                        endDate.set(Calendar.DAY_OF_MONTH, 15);
                        break;
                    case 4:
                        term = "Winter";
                        startDate.set(Calendar.MONTH, Calendar.JANUARY);
                        startDate.set(Calendar.DAY_OF_MONTH, 1);
                        endDate.set(Calendar.MONTH, Calendar.FEBRUARY);
                        endDate.set(Calendar.DAY_OF_MONTH, 15);
                        break;
                    case 1:
                        term = "Spring";
                        startDate.set(Calendar.MONTH, Calendar.MARCH);
                        startDate.set(Calendar.DAY_OF_MONTH, 1);
                        endDate.set(Calendar.MONTH, Calendar.JUNE);
                        endDate.set(Calendar.DAY_OF_MONTH, 15);
                        break;
                    case 2:
                        term = "Summer";
                        startDate.set(Calendar.MONTH, Calendar.JULY);
                        startDate.set(Calendar.DAY_OF_MONTH, 1);
                        endDate.set(Calendar.MONTH, Calendar.AUGUST);
                        endDate.set(Calendar.DAY_OF_MONTH, 15);
                        break;
                    default:
                        term = "Unknown";
                }
                Date start = startDate.getTime();
                Date end = endDate.getTime();
                SemesterPK spk = new SemesterPK();
                spk.setYear(i);
                spk.setTerm(j);
                Semester semester = new Semester();
                semester.setSemester(spk);
                semester.setDisplayName(term + " " + i);
                semester.setStartDate(start);
                semester.setEndDate(end);
                semesters.add(semester);
            }
        }
    }

    public void initCourseOutcomes() {
        for (String description : courseOutcomeDescriptions) {
            CourseOutcome courseOutcome = new CourseOutcome();
            courseOutcome.setDescription(description);
            courseOutcome.setRationale("Not Available");
            courseOutcome.setAssessed(false);
            courseOutcomes.add(courseOutcome);
        }
    }

    public void initDegreePrograms() {
        DegreeProgram degreeProgram = new DegreeProgram();
        degreeProgram.setDegreeProgramId("BS:CS");
        degreeProgram.setDepartment("CEAS");
        degreeProgram.setDescription("Bachelor's of Science in Computer Science");
        degreeProgram.setPEOs(new HashSet(peos));
        degreeProgram.setUsers(new HashSet(users));
        degreePrograms.add(degreeProgram);
    }

    public void initCourseOfferings() {
        int userIndex, semesterIndex, numFiles;
        for (Course course : courses) {
            CourseOffering co = new CourseOffering();
            co.setCourse(course);
            userIndex = rand.nextInt(users.size());
            co.setInstructor(users.get(userIndex));
            semesterIndex = rand.nextInt(semesters.size());
            co.setSemester(semesters.get(semesterIndex));

            for (int i = 0; i < 5; i++) {
                AIMFileWrapper file = new AIMFileWrapper();
                byte[] b = new byte[20];
                new Random().nextBytes(b);
                file.setBytes(b);
                switch (i) {
                    case 0:
                        file.setFileName(course.getName() + ".cic_report.txt");
                        co.setCicReport(file);
                        break;
                    case 1:
                        file.setFileName(course.getName() + ".coordinator_report.txt");
                        co.setCourseCoordinatorReport(file);
                        break;
                    case 2:
                        file.setFileName(course.getName() + ".eos_report.txt");
                        co.setEndOfSemesterReport(file);
                        break;
                    case 3:
                        file.setFileName(course.getName() + ".lecture_schedule.txt");
                        co.setLectureSchedule(file);
                        break;
                    case 4:
                        file.setFileName(course.getName() + ".syllabus.txt");
                        co.setSyllabus(file);
                        break;
                }
            }
            List<AIMFileWrapper> fileList = new ArrayList<AIMFileWrapper>();
            numFiles = rand.nextInt(5);
            for (int j = 0; j < numFiles; j++) {
                AIMFileWrapper file = new AIMFileWrapper();
                byte[] b = new byte[20];
                new Random().nextBytes(b);
                file.setBytes(b);
                file.setFileName(course.getName() + ".lecture_note" + j + ".txt");
                fileList.add(file);
            }
            co.setLectureNotes(fileList);
            courseOfferings.add(co);
        }
    }

    public void initStudentOutcomes() {
        int i = 1;
        for (String outcome : studentOutcomeDescriptions) {
            StudentOutcome so = new StudentOutcome();
            so.setSequence(i);
            so.setShortName("BS:CS Student Outcome #" + i);
            so.setStudentOutcomeId("BS:CS Student Outcome #" + i++);
            so.setDescription(outcome);
            so.setDegreeProgram(degreePrograms.get(0));
            studentOutcomes.add(so);
        }
    }    
}
