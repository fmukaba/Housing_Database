// APPLICANT:
// Combine check availability and Book/application
// Print out a numbered list of query results for available rooming options
// Take in user input first, second, third availability
// Take in rest of application

// USER TABLE:
// Add people who

// USERS:
// Make all residents ->

// Catch exceptions in the client

// QUERIES:
// Take Array of Housing info, format & display
// Take user input, translate to ArrayList of preference Housing Units
// Add married couples boolean
// Does applicant info match one of their preferences? If so, make it so that they are a resident there

// Need to match preference with what's available ->

// AVAILABILITY:
// Housing_unit -> if available, not full = 0; full = 1
// Showing results -> change in housing unit too
//    Building number, no of bedrooms, type, rent, married couples -> number item
//    Ask preferences 1, 2, 3 -> corresponds with numbered query results
// After: type in user information -> add that user to user table
// PRINT IT TO THE CONSOLE WITH THE INDEX FROM AN ARRAYLIST
// Then match them

// All try-catch in client

// Refactor repeat() to centerText()

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class HousingClient {

    private static HousingSystem hs = null;

    public static void main(String args[]) {
        try {
            hs = new HousingSystem();
            printMainMenu();
        } catch (SQLException e) {
        }
    }

    public static void printMainMenu() {

        int action = 0;
        while (action != 4) {

            repeat(36, "*");

            System.out.println("Welcome to the Housing System");
            repeat(10, " ");
            System.out.println("1. Resident Login");
            repeat(10, " ");
            System.out.println("2. Applicant Registration/Apply");
            repeat(10, " ");
            System.out.println("3. Admin");
            repeat(10, " ");
            System.out.println("4. Quit\n");

            action = promptAction(4);

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
                    return;

            }
        }
    }

    public static void residentLogIn() throws SQLException {

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter username: ");

        String username = input.nextLine();

        System.out.print("password:");

        String password = input.nextLine();
        /*
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqlE) {}
        */
        // Do resident log-in things -> what are they?
    }

    public static void printApplicantTop() {
        /*
        a. Users to check the availability of apartments in a particular category.
           It shows a list with all the available apartments for users to check
           availability of apartments in a particular category.
        b. Submit Booking request- fill up application and choose one of the available
           housing categories
        c. Receive confirmation message
        d. After receiving confirmation can login to Residents login option in the main menu
        */
        int action = 0;
        while (action != 3) {

            repeat(36, "*");

            System.out.println("Welcome Applicant!");
            repeat(10, " ");
            System.out.println("1. Get started!");
            repeat(10, " ");
            System.out.println("2. Back to previous menu\n");

            // repeat(36, "*");

            action = promptAction(2);

            switch (action) {
                case 1:
                    getPreferences();
                    break;
                case 3:
                    return;

            }
        }
    }

    // TASK 1
    public static ArrayList<int> getPreferences() throws SQLException {
        // run query
        // print results

<<<<<<< HEAD
        // user input -- number of bedrooms
=======
        Scanner input = new Scanner(System.in);
        int bedrooms = 0;
>>>>>>> 67de3aedd594f5bb03d9dc64bb0a2f2e63eec538

        System.out.println("The following housing options are available: ");
        ArrayList<HousingUnit> hu = hs.checkAvailability(bedrooms); // returns array of strings
        System.out.printf("%d. %5d %5d %10s", "Index", "Building No.", "# of bedrooms", "Type");
        int index = 1;
        for (HousingUnit h : hu) {
            System.out.println(String.format("%d. %15d %15d %15s", index, h.getBuilding(), h.getBedrooms(), h.getType()));
            index++;
        }

        // can totally be changed -- just wanted to think it out
        System.out.println("Please enter the index of your preferences: ");
        int pref1 = readInt("Top choice: ", index);
        int pref2 = readInt("Second choice: ", index);
        int pref3 = readInt("Third choice: ", index);

        //construct array of preferences to be sent to backend
        ArrayList<int> preferences = new ArrayList<int>(Arrays.asList(pref1, pref2, pref3));


        //go back to previous screen
    }

    public static void fillBookingForm() throws SQLException {

        System.out.println("Please fill out the following information:\n");

        Scanner input = new Scanner(System.in);

        System.out.println("User information");

        String username = readString("Username: ");
        String password = readString("Password: ");
        String SID = readString("Student ID: ");
        String name = readString("Name: ");
        String gender = readString("Gender: ");
        String student_status = readString("Student status: ");
        String marital_status = readString("Marital status: ");
        String address = readString("Address: ");
        String college = readString("College: ");
        String department = readString("Department: ");
        String major = readString("Major: ");
        String family_headSSN = readString("Family head's SSN: ");
        String roommate = readString("Roommate ID: ");

        ArrayList<int> preferences = getPreferences();

        hs.bookHousing(SID, preferences);

        // add information to database, assign application number?
        // have them pay fee
        printApplicantTop(); // go back to applicant page
    }

    public static void printAdminTop() {

        int action = 0;
        while (action != 6) {
            repeat(36, "*");

            System.out.println("Welcome to Bellevue College Housing System");
            System.out.println("Administrators Staff");

            repeat(10, " ");
            System.out.println("1. Manage Residents");
            repeat(10, " ");
            System.out.println("2. Manage Applicants");
            repeat(10, " ");
            System.out.println("3. Demographic Studies");
            repeat(10, " ");
            System.out.println("4. Manage Maintenance orders");
            repeat(10, " ");
            System.out.println("5. Administrative Reports");
            repeat(10, " ");
            System.out.println("6. Back to previous menu\n");

            Scanner input = new Scanner(System.in);
            boolean valid = false;

            while (!valid) {
                action = input.nextInt();

                if (action == 5 || action == 6) {
                    valid = true;
                } else {
                    System.out.println("Please enter a valid action: ");
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

    public static void printReportsTop() throws SQLException {
        int action = 0;

        while (action != 5) {
            repeat(36, "*");
            System.out.println("Administrative Reports");
            repeat(10, " ");
            System.out.println("1. Housing department reports");
            repeat(10, " ");
            System.out.println("2. Applicants Reports");
            repeat(10, " ");
            System.out.println("3. Resident Reports");
            repeat(10, " ");
            System.out.println("4. Maintenance department reports");
            repeat(10, " ");
            System.out.println("5. Quit");

            Scanner input = new Scanner(System.in);
            boolean valid = false;

            while (!valid) {
                action = input.nextInt();

                if (action == 4 || action == 5) {
                    valid = true;
                }
                System.out.println("Please enter a valid action: ");
            }

            switch (action) {
                // case 4:
                // runReports(); TASK TWO
                case 5:
                    return; //printAdminTop();
            }
        }
        return;

    }

    // TASK 2:
    public static void runReports() {
        System.out.println("Active maintenance requests: ");
        // Run the query, show results here
    }

    public static int promptAction(int max) {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        int value = 0;

        repeat(36, "*");

        while (!valid) {
            System.out.println("Please select an option: ");
            if (input.hasNextInt()) {
                value = input.nextInt();
            }

            if (value > 0 && value <= max) {
                System.out.println(value);
                valid = true;
            }
        }
        return value;
    }

    public static int readInt(String prompt, int max) {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        int value = 0;

        while (!valid) {
            System.out.println("Please select an option: ");
            if (input.hasNextInt()) {
                value = input.nextInt();
            }

            if (value > 0 && value <= max) {
                valid = true;
            }
        }
        return value;
    }

    public static String readString(String prompt) {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        String output = "";

        System.out.print(prompt);

        while (!valid) {
            if (input.hasNext()) {
                value = input.nextLine();
                valid = true;
            } else {
                System.out.println("Please enter valid input");
            }
        }
        System.out.println();
        return output;
    }

    public static void repeat(int num, String str) {

        for (int i = 0; i <= num; i++) {
            System.out.print(str);
        }

        System.out.println();
    }

    public static int getAction(int max) {

        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int action = 0;

        while (!valid) {
            action = input.nextInt();

            if (action > 0 && action <= max) {
                valid = true;
            }
            System.out.println("Please enter a valid action from 1 to " + max);
        }

        return action;
    }

    /*
        // Prompts the user and returns the given input
        static String readEntry(String prompt) {
            try {
                StringBuffer buffer = new StringBuffer();
                System.out.print(prompt);
                System.out.flush();
                int c = System.in.read();
                while(c != '\n' && c != -1) {
                    buffer.append((char)c);
                    c = System.in.read();
                }
                return buffer.toString().trim();
            } catch (IOException e) {
                return "";
            }
        }
    */
    static void centerText(String text, int width) {
        int cushion = (width - text.length())/2;
        for (int i = 0; i < cushion; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }
}
