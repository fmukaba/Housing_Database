// CS 331 Project Assignment Part B
// Jena Lovejoy, Franck Mukaba, Simone Ray
// Due Wednesday, November 28 2018
// HousingUnit represents individual apartments or suites available to students

import java.util.Objects;

public class HousingUnit {
    private int building;       // Building number
    private int bedrooms;       // Number of bedrooms
    private String type;        // Type of housing unit
    private boolean married;    // True indicates married couples allowed
    private int price;          // Price of the housing unit

    // Creates default housing unit with null values
    public HousingUnit() {
        this.building = -1;
        this.bedrooms = -1;
        this.type = "NULL";
        this.married = false;
        this.price = -1;
    }

    // Creates housing unit with given attributes
    public HousingUnit(int building, int bedrooms, String type, boolean married, int price) {
        this.building = building;
        this.bedrooms = bedrooms;
        this.type = type;
        this.married = married;
        this.price = price;
    }

    //GETTERS
    public int getBuilding() {
        return building;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public String getType() {
        return type;
    }

    public boolean getMarried() { return married; }

    public int getPrice() { return price; }

    //SETTERS
    public void setBuilding(int building) {
        this.building = building;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingUnit that = (HousingUnit) o;
        return building == that.building &&
                bedrooms == that.bedrooms &&
                married == that.married &&
                price == that.price &&
                Objects.equals(type, that.type);
    }

    @Override
    public String toString() {
        return "HousingUnit{" +
                "building=" + building +
                ", bedrooms=" + bedrooms +
                ", type='" + type + '\'' +
                ", married=" + married +
                ", price=" + price +
                '}';
    }
}
