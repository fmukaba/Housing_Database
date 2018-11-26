import java.util.Objects;

public class HousingUnit {
    private int building;
    private int bedrooms;
    private String type;

    public HousingUnit(int building, int bedrooms, String type) {
        this.building = building;
        this.bedrooms = bedrooms;
        this.type = type;
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

    public void setBuilding(int building) {
        this.building = building;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingUnit that = (HousingUnit) o;
        return building == that.building &&
                bedrooms == that.bedrooms &&
                Objects.equals(type, that.type);
    }

}
