// CS 331 Project Assignment Part B
// Jena Lovejoy, Franck Mukaba, Simone Ray
// Due Wednesday, November 28 2018
// MaintenanceRequestDue represents individual maintenance requests filed

public class MaintenanceRequestDue {

    String user_name;
    String buildNum;
    String aptNum;
    String submDate;
    String dateCompleted;
    String comments;

    public MaintenanceRequestDue(String name, String buildNum, String aptNum, String submDate, String dateCompleted,
                                 String comments) {
        this.user_name = name;
        this.buildNum = buildNum;
        this.aptNum = aptNum;
        this.submDate = submDate;
        this.dateCompleted = dateCompleted;
        this.comments = comments;
    }

    // Accessors to retrieve maintenance information
    public String getName() {
        return this.user_name;

    }

    public String getBuilding() {
        return this.buildNum;

    }

    public String getAptNum() {
        return this.aptNum;

    }

    public String getSubDate() {
        return this.submDate;

    }

    public String getDateCompleted() {
        return this.dateCompleted;

    }

    public String getComm() {
        return this.comments;

    }

}
