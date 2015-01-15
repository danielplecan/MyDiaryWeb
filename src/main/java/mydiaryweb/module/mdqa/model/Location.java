package mydiaryweb.module.mdqa.model;


public class Location {
    private int id;
    private String location;
    private String proximity;
    private String address;
    private String type;
    private String vehicleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProximity() {
        return proximity;
    }

    public void setProximity(String proximity) {
        this.proximity = proximity;
    }

    public String toString(){
        return "Location: " + location + " Proximity: " + proximity;
    }

    public String stringify(){
        return getLocation() + " " + getProximity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location1 = (Location) o;

        if (id != location1.id) return false;
        if (location != null ? !location.equals(location1.location) : location1.location != null) return false;
        if (proximity != null ? !proximity.equals(location1.proximity) : location1.proximity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (proximity != null ? proximity.hashCode() : 0);
        return result;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
