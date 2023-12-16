
package bicycle.rental.project;


public class Bike {
    protected boolean isRented ;
    protected String SerialNumber ;

    public boolean getIsRented() {
        return isRented;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }

    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    public Bike(boolean isRented, String SerialNumber) {
        this.isRented = isRented;
        this.SerialNumber = SerialNumber;
    }

    public Bike (Bike b)
    {
        this.isRented = b.getIsRented();
        this.SerialNumber = b.getSerialNumber();
    }

    public String getType()
    {
        return "Bike";
    }

    @Override
    public String toString() {
        return "SerialNumber = " + SerialNumber + "  Type = Bike";
    }

}

