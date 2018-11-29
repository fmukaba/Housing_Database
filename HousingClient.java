// CS 331 Project Assignment Part B
// Jena Lovejoy, Franck Mukaba, Simone Ray
// Due Wednesday, November 28 2018
// HousingClient provides the user-facing interface,
// allowing for addition to or retrieval from the Housing database

import java.sql.*;
import java.util.*;

public class HousingClient {

    private static HousingSystem hs = null;

    public static void main(String args[]) throws ClassNotFoundException {
        try {
            hs = new HousingSystem();
            printMainMenu();
        } catch (SQLException e) {
            System.out.println("A database error occurred: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    // Displays the top menu and allows users to pick initial high-level options
    public static void printMainMenu() throws SQLException {

        int action = 0;
        while (action != 4) {

            repeat(42, "*");
            formatString("Welcome to the Housing System", 7, " ");
            formatString("1. Resident Login", 4, " ");
            formatString("2. Applicant Registration/Apply", 4, " ");
            formatString("3. Admin", 4, " ");
            formatString("4. Quit\n", 4, " ");
            repeat(42, "*");

            action = readInt("Please select an option: ", 4);

            switch (action) {
                case 1:
                    residentLogIn();
                    break;

                case 2:
                    printApplicantTop();
                    break;

                case 3:
                    printAdminTop();
                    break;

                case 4:
                    System.out.println("\nQuitting.\n");
                    repeat(42, "*");
                    return;

            }
        }
    }

    // Displays the user log-in screen
    public static void residentLogIn() throws SQLException{

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter username: ");
        String username = input.nextLine();

        System.out.print("Password:");
        String password = input.nextLine();

        boolean exists = hs.checkResident(username, password);

        if(exists) {
            System.out.println("\nHello resident! \nWe're sorry, this page is not available at this time.\nPlease try again later.");
            return;
        } else {
            System.out.println("You are not a resident.");
            return;
        }
    }

    // Displays the applicant options and accepts an action
    public static void printApplicantTop() throws SQLException {
        int action = 0;
        while (action != 2) {
            System.out.println();
            repeat(42, "*");
            formatString("Welcome Applicant!", 12, " ");
            formatString("1. Get started!", 4, " ");
            formatString("2. Back to previous menu\n", 4, " ");

            repeat(42, "*");

            action = readInt("Please select an option: ", 2);

            switch (action) {
                case 1:
                    getPreferences();
                    action = 2;
                    break;
                case 2:
                    return;
            }
        }
    }

    // TASK 1:
    // Displays available housing options and accepts the user housing preferences
    public static void getPreferences() throws SQLException {

        repeat(42, "*");

        System.out.println("The following housing options are available: ");
        ArrayList<HousingUnit> hu = hs.checkAvailability(); // returns array of available units
        ArrayList<HousingUnit> preferences = new ArrayList<>();

        if (hu.isEmpty()) { // No available units
            System.out.println("We're sorry, there are no available housing options at this time.\n" +
                    "Please fill out the application below, and we will add you to the waitlist.\n");
            HousingUnit nullUnit = new HousingUnit();
            preferences.addAll(Arrays.asList(nullUnit, nullUnit, nullUnit)); // dummy units to be processed with application
        } else { // If housing units available
            // Prints out available housing
            System.out.printf("%-8s %-15s %-18s %-10s %-27s %s\n", "Index", "Building #", "# Bedrooms", "Type", "Allows married couples", "Price ($)");
            int index = 0;
            for (HousingUnit h : hu) {
                index++;
                System.out.printf("%-8d %-15s %-18s %-10s %-27s %s %s", index, h.getBuilding(), h.getBedrooms(), h.getType(), h.getMarried(), h.getPrice(), "\n");
            }

            int pref1;
            int pref2 = 1;
            int pref3 = 1;
            // Obtains unique preferences
            System.out.println("\nPlease enter the index of your preference(s): ");
            pref1 = readInt("Top choice: ", index);

            if (index > 1) {
                pref2 = readInt("Second choice: ", index);
                while (pref1 == pref2) {
                    System.out.println("Please enter a different value.");
                    pref2 = readInt("Second choice: ", index);
                }
            }


            if (index > 2) {
                pref3 = readInt("Third choice: ", index);
                while (pref1 == pref3 || pref2 == pref3) {
                    System.out.println("Please enter a different value");
                    pref3 = readInt("Third choice: ", index);
                }
            }

            // Construct array of preferences to be sent to backend
            preferences.addAll(Arrays.asList(hu.get(pref1 - 1), hu.get(pref2 - 1), hu.get(pref3 - 1)));
        }

        // Obtain applicant information, validating input
        System.out.println("Please fill out the following information:\n");
        System.out.println("User information");

        String username = readString("Username: ");
        String password = readString("Password: ");

        String SID = readString("Student ID: ");
        while (!(SID.length() == 9 && isInteger(SID))){ // validates SID
            System.out.println("Please enter a valid ID");
            SID = readString("Student ID: ");
        }

        String name = readString("Name: ");
        String phoneNumber = readString("Phone number: ");

        String gender = readString("Gender(Enter M for male, F for female, O for other): ");
        while(!(gender.equals("F") || gender.equals("M") || gender.equals("O"))) {
            System.out.println("Please enter a valid gender");
            gender = readString("Gender(Enter M for male, F for female, O for other): ");
        }

        int student_status_int = readInt("Student status (1 if student, 2 if not): ", 2);
        int marital_status_int = readInt("Marital status (1 if married, 2 if single): ", 2);
        String homeAddress = readString("Address: ");
        String college = readString("College: ");
        String department = readString("Department: ");
        String major = readString("Major: ");
        String familyHeadSSN = readString("Family head's SID (enter your own if it's you)" +
                "\nNote, family head must already be a resident or we cannot process your application: ");

        while (!(familyHeadSSN.length() == 9 && isInteger(familyHeadSSN))){ // validates SSN
            System.out.println("Please enter a valid ID");
            familyHeadSSN = readString("Family head's SSN: ");
        }

        String roommate = readString("If you have a roommate preference, enter it here: ");

        // Translate the input values into boolean values
        boolean marital_status = false;
        if (marital_status_int == 1) {
            marital_status = true;
        }

        boolean student_status = true;
        if (student_status_int == 2) {
            student_status = false;
        }

        try {
            hs.createUser(SID, username, password, name, gender, student_status, marital_status, homeAddress,
                    phoneNumber, college, department, major, familyHeadSSN);

        } catch (SQLException e) {
            System.out.println("A database error occurred. Make sure all information is inputted correctly." +
                    "\nHints: Make sure you have not registered before (email housing@bellevuecollege.edu to retrieve " +
                    "your username and password). Make sure family head registers before all other family members. ");
            System.out.println("Would you like to restart your application?");
            int reset = readInt("1 for yes, 2 for no: ", 2);
            if (reset == 1){
                getPreferences();
            } else {
                printApplicantTop();
            }
        }

        String newAddress = hs.bookHousing(SID, preferences, roommate);

        if (newAddress != null) {
            System.out.println("Congratulations! You are now a Bellevue College resident.");
            System.out.println("Your new address is " + newAddress);
            System.out.println("Next time you enter the portal you can log in as a resident.");
        } else { // If no units are available, add applicant to the waitlist
            System.out.println("You've been added to the waitlist. \nWe will reach out when a unit is available.");
        }

    }

    // Displays all admin options and accepts further action options
    public static void printAdminTop() throws SQLException {

        int action = 0;
        while (action != 6) {

            repeat(42, "*");
            System.out.println("Welcome to Bellevue College Housing System");
            formatString("Administrators Staff", 10, " ");
            formatString("1. Manage Residents", 4, " ");
            formatString("2. Manage Applicants", 4, " ");
            formatString("3. Demographic Studies", 4, " ");
            formatString("4. Manage Maintenance orders", 4, " ");
            formatString("5. Administrative Reports", 4, " ");
            formatString("6. Back to previous menu\n", 4, " ");
            repeat(42, "*");

            Scanner input = new Scanner(System.in);
            boolean valid = false;

            System.out.println("Please select an option: ");

            while (!valid) {
                action = input.nextInt();

                if (action == 5 || action == 6) {
                    valid = true;
                } else if (action > 6 || action < 1) {
                    System.out.println("Please enter a valid action: ");
                } else {
                    System.out.println("We're sorry, this page is not available at this time.\nPlease try again later");
                    System.out.println("Please select an option: ");
                }
            }

            switch (action) {
                case 5:
                    printReportsTop();
                    break;
                case 6:
                    return;
            }
        }
        return;
    }

    // Displays the reports menu and accepts user action choice
    public static void printReportsTop() throws SQLException {
        int action = 0;

        while (action != 5) {
            repeat(42, "*");
            formatString("Administrative Reports", 10, " ");
            formatString("1. Housing department reports", 4, " ");
            formatString("2. Applicants Reports", 4, " ");
            formatString("3. Resident Reports", 4, " ");
            formatString("4. Maintenance department reports", 4, " ");
            formatString("5. Back to main menu\n", 4, " ");

            repeat(42, "*");

            Scanner input = new Scanner(System.in);
            boolean valid = false;

            System.out.println("Please select an option: ");

            while (!valid && input.hasNextInt()) {
                action = input.nextInt();

                if (action == 4 || action == 5) {
                    valid = true;
                } else if (action > 5 || action < 1){
                    System.out.println("Please enter a valid action: ");
                } else {
                    System.out.println("We're sorry, this page is not available at this time.\nPlease try again later");
                    System.out.println("Please select an option: ");
                }
            }

            ArrayList<MaintenanceRequestDue> m_report =  hs.runReports();

            switch (action) {
                case 4:
                    showReports(m_report);
                case 5:
                    return;
            }
        }
    }

    // TASK 2:
    // Prints the current maintenance results
    public static void showReports(ArrayList<MaintenanceRequestDue> m_report) {

        repeat(42, "*");
        System.out.println("Active maintenance requests: \n");
        System.out.printf("%-20s %-15s %-16s %-20s %-20s\n", "Tenant", "Building #", "Apartment #", "Submission date", "Comments");

        for (MaintenanceRequestDue request: m_report){
            System.out.printf("%-20s %-15s %-16s %-20s %-20s\n",  request.getName(), request.getBuilding(), request.getAptNum(), request.getSubDate(), request.getComm());
        }

        System.out.println();
        return;
    }

    // Manages user input for integer-specific values
    public static int readInt(String prompt, int max) {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        int value = 0;

        while (!valid) {
            System.out.print(prompt);
            if (input.hasNext()) {
                value = input.nextInt();
                if (value > 0 && value <= max) {
                    valid = true;
                } else {
                    System.out.println("Please enter valid input");
                }
            } else {
                input.next();
                System.out.println("Please enter valid input");
            }
        }
        System.out.println();
        return value;
    }

    // Manages user input for string-specific input
    public static String readString(String prompt) {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        String output = "";

        System.out.print(prompt);

        while (!valid) {
            if (input.hasNext()) {
                output = input.nextLine();
                valid = true;
            } else {
                System.out.println("Please enter valid input");
            }
        }
        System.out.println();
        return output;
    }

    // Repeats a given character to a given amount
    public static void repeat(int num, String str){
        for (int i = 0; i <= num; i++) {
            System.out.print(str);
        }

        System.out.println("\n");
    }

    // Formats a string, adding a certain amount of buffer to center or indent
    static void formatString(String text, int num, String str){
        for (int i = 0; i < num; i++) {
            System.out.print(str);
        }

        System.out.println(text);
    }

    // Returns if a string is comprised of all integers
    public static boolean isInteger(String str){
        int length = str.length();
        int value = 0;
        for (int i = 0; i < length; i++){
            value = str.charAt(i);
            if (value < 48 || value > 57){
                return false;
            }
        }
        return true;
    }
}
