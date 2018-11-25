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
        
        String username = input.getLine();
        
        System.out.print("password:");
        
        String username = input.getLine();
        /*
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqlE) {}
        */
        // Do resident log-in things
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
        
        if (action == 1 || action == 2){        
            takeApplicantAction(action);
        }
        
        switch (action){
            // case 1: checkAvailability(); -> Query 
            case 2: fillBookingForm();
            case 3: return;

        }
    }
    
    public static void fillBookingForm(){
    
        System.out.println("Please fill out the following information:");
        
        
    
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
        
        switch (action){
            // case 1: 
            // case 2:
            // case 3:
            // case 4: runReports();
            case 5: return;
        }
    
    }
    
        
    public static void takeApplicationAction(int action){
        
        /*
        The applicant should fill up a form with all the required details 
        including the options the available type of housing that he/ she 
        can choose from. Also, he should be able to list 3 preferences and 
        preferred roommate if he/she likes to.
        */
    
    }
    
    // public static void printResidentTop(){}
    
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
    
    public static void adminAction(int action){
        /*
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
        */
        
        if (action == 5){
            repeat(10, " ");
            System.out.println("1. Housing department reports");
            repeat(10, " ");
            System.out.println("2. Applicants Reports");
            repeat(10, " ");
            System.out.println("3. Resident Reports");
            repeat(10, " ");
            System.out.println("4. Maintenance department reports");

            int queryChoice = getAction(4);
            
        } else {
            
        
        }
        
    }
    
    public static void repeat(int num, String str){
        
        for (int i = 0; i <= num; i++){
            System.out.print(str);
        }
        
        System.out.println();
    }
   
   
}