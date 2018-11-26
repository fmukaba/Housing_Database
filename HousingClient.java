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

    public static void residentLogIn() {

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
            System.out.println("1. Get availability and enter housing preference");
            repeat(10, " ");
            System.out.println("2. Submit booking request");
            repeat(10, " ");
            System.out.println("3. Back to previous menu\n");

            // repeat(36, "*");

            action = promptAction(3);

            switch (action) {
                case 1:
                    checkAvailability();
                    break;
                case 2:
                    fillBookingForm();
                    break;
                case 3:
                    return;

            }
        }
    }

    // TASK 1
    public static void checkAvailability() throws SQLException {
        // run query
        // print results

        System.out.println("The following housing options are available: ");
        ArrayList<HousingUnit> hu = hs.checkAvailability(); // returns array of strings
        System.out.printf("%d. %5d %5d %10s", "Index", "Building No.", "# of bedrooms", "Type");
        int index = 1;
        for (HousingUnit h : hu) {
            System.out.println(String.format("%d. %15d %15d %15s", index, h.getBuilding(), h.getBedrooms(), h.getType()));
            index++;
        }

        // can totally be changed -- just wanted to think it out
        String s1 = readEntry("List the index of your first preference:");
        String s2 = readEntry("List the index of your second preference:");
        String s3 = readEntry("List the index of your third preference");

        //construct array of preferences to be sent to backend

        //go back to previous screen
    }

    public static void fillBookingForm() {

        System.out.println("Please fill out the following information:\n");

        Scanner input = new Scanner(System.in);

        System.out.println("User information");
        System.out.print("Username: ");
        String username = input.nextLine(); // check if taken?

        System.out.print("\nPassword: ");
        String password = input.nextLine(); // check twice for same password?

        System.out.print("\nStudent ID: ");
        String ID = input.nextLine();

        System.out.print("\nName: ");
        String name = input.nextLine();

        System.out.print("\nGender: ");
        String gender = input.nextLine();

        System.out.print("\nStudent status: ");
        String student_status = input.nextLine();

        System.out.print("\nMarital status: ");
        String marital_status = input.nextLine();

        System.out.print("\nAddress: ");
        String address = input.nextLine();

        System.out.print("\nPhone number: ");
        String Phone_number = input.nextLine();

        System.out.print("\nCollege: "); // validation?
        String college = input.nextLine();

        System.out.print("\nDepartment: "); // validation?
        String department = input.nextLine();

        System.out.print("\nMajor: "); // validation?
        String major = input.nextLine();

        System.out.print("\nFamily head's SSN: ");
        String family_headSSN = input.nextLine();

        System.out.println("\nHousing preferences"); // how to handle 1, 2, 3?

        System.out.print("\nWhich building?");
        int building_number = input.nextInt();

        System.out.print("\nHow many bedrooms?");
        int bedrooms = input.nextInt();

        System.out.print("\nWhat type of housing?"); // how to handle input?
        String type = input.nextLine(); // translate input

        System.out.print("\nRoommate ID: ");
        int roommate_Id = input.nextInt();

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

    public static void printReportsTop() {
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

    static void centerText(String text, int width) {
        int cushion = (width - text.length())/2;
        for (int i = 0; i < cushion; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }


}
