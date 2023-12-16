
package bicycle.rental.project;


public class ElectricBike extends Bike{
    private boolean charge;

    public ElectricBike(boolean charge, boolean isRented, String SerialNumber) {
        super(isRented, SerialNumber);
        this.charge = charge;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public boolean getCharge()
    {
        return charge;
    }


    @Override

    public String toString() {
        String chrg = (charge == true ? "Yes" : "No");
        return "SerialNumber = " + SerialNumber + "  Type = ElectricBike" + "  Charge = " + chrg;
    }

    @Override
    public String getType() {
        return "ElectricBike";
    }

}
