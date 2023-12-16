
package bicycle.rental.project;


public class RoadBike extends Bike{

    public RoadBike(boolean isRented, String SerialNumber) {
        super(isRented, SerialNumber);
    }

    @Override
    public String toString() {
        return "SerialNumber = " + SerialNumber + "  Type = RoadBike";
    }

    @Override
    public String getType() {
        return "RoadBike";
    }

}
