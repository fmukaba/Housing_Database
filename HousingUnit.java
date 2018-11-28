// CS 331 Project Assignment Part B
// Jena Lovejoy, Franck Mukaba, Simone Ray
// Due Wednesday, November 28 2018
// HousingUnit represents individual apartments or suites available to students

import java.util.Objects;

public class HousingUnit {
    private int building;
    private int bedrooms;
    private String type;
    private boolean married;
    private int price;

    public HousingUnit(int building, int bedrooms, String type, boolean married, int price) {
        this.building = building;
        this.bedrooms = bedrooms;
        this.type = type;
        this.married = married;
        this.price = price;
    }

    public HousingUnit(){
        this.building = -1;
        this.bedrooms = -1;
        this.type = "null";
        this.married = false;
        this.price = -1;
    }

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

}
