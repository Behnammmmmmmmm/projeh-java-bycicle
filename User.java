package bicycle.rental.project;

import java.util.ArrayList;

public class User {

    private String userName;
    private ArrayList<Bike> rentedList;

    public User(String userName) {
        this.userName = userName;
        rentedList = new ArrayList<Bike>();
    }

    private static int searchBikes(ArrayList<Bike> bikes, String serial) {
        for (int i = 0; i < bikes.size(); i++) {
            if (bikes.get(i).getSerialNumber().equals(serial)) {
                return i;
            }
        }
        return -1;
    }

    public void addBike(Bike bike) {
        if (rentedList.size() < 3) {
            rentedList.add(bike);
            System.out.println("Rented Successful!");
        } else {
            System.out.println(userName + " has 3 bikes!");
        }
    }

    public boolean deleteBike(Bike bike) {
        int f = searchBikes(rentedList, bike.getSerialNumber());
        if (f == -1) {
            return false;
        }
        System.out.println("Returned Successful!");
        rentedList.remove(f);
        return true;
    }

    public Bike getBike(int index)
    {
        return rentedList.get(index);
    }

    public int getSizeOfBikes()
    {
        return rentedList.size();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
