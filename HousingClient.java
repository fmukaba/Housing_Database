import java.util.*;

public class HousingClient {

    public static void main(String args[]){
    
        printMainMenu();
            
    }
    
    public static void printMainMenu(){
        repeat(36, "*");
        
        System.out.println("Welcome to the Housing System");        
        repeat(10, " ");
        System.out.println("1. Resident Login");
        repeat(10, " ");
        System.out.println("2. Applicant Registration/Apply");
        repeat(10, " ");
        System.out.println("3. Admin");
        repeat(10, " ");
        System.out.println("4. Quit");
        
        repeat(36, "*");
        
        int action = promptAction(4);
        
        switch (action){
            case 1: residentLogIn();
            case 2: printApplicantTop();
            case 3: printAdminTop();
            case 4: return;
        
        }
    }
    
    public static void residentLogIn(){
        
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
    
    public static void printApplicantTop(){
        /*
        a. Users to check the availability of apartments in a particular category. 
           It shows a list with all the available apartments for users to check 
           availability of apartments in a particular category.
        b. Submit Booking request- fill up application and choose one of the available 
           housing categories
        c. Receive confirmation message
        d. After receiving confirmation can login to Residents login option in the main menu
        */
        
        repeat(36, "*");
        
        System.out.println("Welcome Applicant!");
        repeat(10, " ");
        System.out.println("1. Check availability");
        repeat(10, " ");
        System.out.println("2. Submit booking request");
        repeat(10, " ");
        System.out.println("3. Quit");
        
        // repeat(36, "*");
        
        int action = promptAction(3);
        
        switch (action){
            // case 1: checkAvailability(); -> Query 
            case 2: fillBookingForm();
            case 3: return;

        }
    }
    
    public static void checkAvailability(){
        // run query
        // print results
        
        System.out.println("The following housing options are available: ");
        
        // go back to previous screen
    }
    
    public static void fillBookingForm(){
    
        System.out.println("Please fill out the following information:\n");
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("User information");
        System.out.print("Username: ");
        String username = input.nextLine(); // check if taken?
                
        System.out.print("\nPassword: ");
        String password = input.nextLine();
        
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
        
        System.out.println("Housing preferences"); // how to handle 1, 2, 3?
        
        System.out.print("\nHow many bedrooms?");
        int bedrooms = input.nextInt();
        
        System.out.print("\nWhat type of housing?"); // how to handle input?
        String type = input.nextLine(); // translate input
        
        System.out.print("\nWhich building?");
        int building_number = input.nextInt();
        
        // add information to database, assign application number?
        // have them pay fee
        return; // go back to applicant page
    }
    
    public static void printAdminTop(){
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
        System.out.println("6. Quit");
        
        repeat(36, "*");
        
        int action = promptAction(6);
        
        switch (action){
            // case 1: 
            // case 2:
            // case 3:
            // case 4: 
            case 5: printReportsTop();
            case 6: return;
        }
    }
    
    public static void printReportsTop(){
    
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
        
        int action = promptAction(5);
        
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int action = 0;
        
        while (!valid){
            action = input.nextInt();
            
            if (action == 5){
                valid = true;
            }
            System.out.println("Please enter a valid action: ");
        }
    
        return action;
        
        switch (action){
            // case 1: 
            // case 2:
            // case 3:
            // case 4: runReports();
            case 5: return;
        }
    
    }
    
    public static int promptAction(int max){
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        int value = 0;
        
        while (!valid){
            System.out.println("Please select an option: ");
            if (input.hasNextInt()){
                value = input.nextInt();
            }
            
            if (value > 0 && value <= max){
                System.out.println(value);
                valid = true;
            }
        }
        return value;
    }
    
    public static void repeat(int num, String str){
        
        for (int i = 0; i <= num; i++){
            System.out.print(str);
        }
        
        System.out.println();
    }
   
    public static int getAction(int max){
    
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int action = 0;
        
        while (!valid){
            action = input.nextInt();
            
            if (action > 0 && action <= max){
                valid = true;
            }
            System.out.println("Please enter a valid action from 1 to " + max);
        }
    
        return action;
    }
   
   
}
