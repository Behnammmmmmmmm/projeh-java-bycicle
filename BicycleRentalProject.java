package bicycle.rental.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.print.DocFlavor;

public class BicycleRentalProject {

    static Scanner in = new Scanner(System.in);
    static ArrayList<Bike> bikes = new ArrayList<Bike>(); // motorha
    static ArrayList<User> users = new ArrayList<User>(); // karbaran

    public static int menu() {
        System.out.println("Menu-- >");
        System.out.println("1.charkh haye hay dar dastres .");
        System.out.println("2.charkh haye keraye dade shode.");
        System.out.println("3.Bargardan charkh.");
        System.out.println("4.keraye dadan charkh.");
        System.out.println("5.Ezafe kardan charkh.");
        System.out.println("6.Ezafe kardan karbar.");
        System.out.println("7.List charkhha.");
        System.out.println("8.List karbarha.");
        System.out.println("9.Exit");
        System.out.println("10.Load Data.");
        System.out.println("Adad gozine mored nazar ra entekhab konid : \n\n");
        int nummber = in.nextInt();
        int s = selectMenu(nummber); // khandan menu
        return s;
    }

    public static int selectMenu(int n) // entekhabe menu
    {
        // be tebgh proje shomare gozari kardim va har shomare tabe makhsoos khod ra farakhani mikond
        in.nextLine(); // khandan enter bad az adad
        switch (n) {
            case 1:
                showReturnedBikes();
                break;
            case 2:
                showRentedBikes();
                break;

            case 3:
                returnBike();
                SaveBikes(bikes);
                SaveUsers(users);
                break;
            case 4:
                rentBike();
                SaveBikes(bikes);
                SaveUsers(users);
                break;
            case 5:
                addBike();
                SaveBikes(bikes);
                break;
            case 6:
                addUser();
                SaveUsers(users);
                break;
            case 7:
                showBikes();
                break;
            case 8:
                showUsers();
                break;
            case 9:
                return 0;
            case 10:
                try {
                    loadData();
                    System.out.println("Data loaded successfuly!");
                } catch (Exception e) {
                    System.out.println("Error reading data from file!");
                }

        }
        return -1;
    }

    // search dar bine userha
    public static int searchUsers(ArrayList<User> users, String userName) {
        for (int i = 0; i < users.size(); i++) { // hame karbaran ra jostojo mikonad
            if (users.get(i).getUserName().equals(userName)) {  // agar karbar bar asase username yaft shod
                return i; // index an ra barmigardanad
            }
        }
        return -1; // dar ghire in soorat -1 bar migardanad
    }

    //search dar bine motorha bar asase serial
    public static int searchBikes(ArrayList<Bike> bikes, String serial) {
        for (int i = 0; i < bikes.size(); i++) { // hame motorha ra jostojo mikonad
            if (bikes.get(i).getSerialNumber().equals(serial)) { // agar motor bar asase serial yaft shod
                return i;// index an ra barmigardanad
            }
        }
        return -1;// dar ghire in soorat -1 bar migardanad
    }

    //5
    public static void addBike() {
        System.out.println("Enter Bike Serial : ");
        String SerialNum = in.nextLine(); // daryafte serial
        int f = searchBikes(bikes, SerialNum); // search mikonad
        if (f == -1) { // agar motor ba serial tekrari nabood
            System.out.println("1.ElectricBike 2.RoadBike 3.Bike");
            int selection = in.nextInt(); // entekhane noe motor
            in.nextLine(); // khandan enter bad az adad
            if (selection == 1) { // agar ElectricBike bood
                System.out.println("Charge 1.Yes 2.No");
                selection = in.nextInt(); // khandane noe charge
                in.nextLine(); // khandan enter bad az adad
                ElectricBike eb = new ElectricBike(false, false, SerialNum); // sakhte object az ElectricBike
                if (selection == 1) { // charge yes
                    eb.setCharge(true);
                } else if (selection == 2) {
                    eb.setCharge(false); // charge no
                }
                bikes.add(eb); // ezafe kardan motor az noe ElectricBike
            } else if (selection == 2) {
                RoadBike rb = new RoadBike(false, SerialNum);  // sakhte object az RoadBike
                bikes.add(rb); // ezafe kardan motor az noe RoadBike
            } else if (selection == 3) {
                Bike b = new Bike(false, SerialNum); // ezafe kardan motor az noe Bike
                bikes.add(b);
            }
        } else { // dar ghire in soorat
            System.out.println("The bike exits!"); // motor mojood ast
        }

    }

    //6
    public static void addUser() {
        System.out.println("Enter username : ");
        String name = in.nextLine(); // daryagte username
        User usr = new User(name); // sakht obj az user
        if (searchUsers(users, name) == -1) { // agar user tekrari nabood
            users.add(usr); //add kardan user
        } else {
            System.out.println("User exist!");
        }
    }

    //8
    public static void showUsers() {
        int c = 1; // shomre gozar baraye chap
        if (users.size() > 0) { // agar user ezafe shode bood
            for (User user : users) { // namayeshe userha ba halghe
                System.out.println(c++ + "." + user.getUserName());
            }
        } else { // dar ghire in soorat
            System.out.println("There is no user"); // user vojood nadarad
        }
    }

    //7
    public static void showBikes() {
        int c = 1; // mele showUsers asts
        if (bikes.size() > 0) {
            for (Bike bike : bikes) {
                System.out.println(c++ + "." + bike.toString());
            }
        } else {
            System.out.println("Thre is no bike");
        }
    }

    //4
    public static void rentBike() {
        System.out.println("Enter username : ");
        String userName = in.nextLine(); // khandan username
        int f = searchUsers(users, userName); // tekrari nabashad dar karbaran
        if (f != -1) { // agar tekrari nabood
            System.out.println("Enter bike serial number : ");
            String serial = in.nextLine(); // khandan serial
            int fBike = searchBikes(bikes, serial); // // jostojo dar motorha ke tekrari nabashad

            if (fBike != -1) {
                if (bikes.get(fBike).getIsRented() == false) { // agar ejare nadadim
                    bikes.get(fBike).setIsRented(true); // ejare ra true mikonad
                    users.get(f).addBike(bikes.get(fBike)); // ezafe kardan motor be karbar morede nazar
                } else { // yani motor ejare dade shode
                    System.out.println("The bike already rented!");
                }
            } else { // chenin motori vojood nadarad
                System.out.println("Not found bike with this serial!");
            }
        } else {
            System.out.println("User not found!"); // karbar peyda nashod
        }
    }

    //3
    public static void returnBike() {
        System.out.println("Enter username : ");
        String userName = in.nextLine(); // khandan username
        int f = searchUsers(users, userName); // tekrari nabashad dar karbaran
        if (f != -1) { // agar tekrari nabood
            System.out.println("Enter bike serial number : ");
            String serial = in.nextLine(); // khandan serial
            int fBike = searchBikes(bikes, serial);// jostojo dar motorha ke tekrari nabashad

            if (fBike != -1) {
                if (bikes.get(fBike).getIsRented() == true) { // agar ejare dade shode bood
                    bikes.get(fBike).setIsRented(false); // motor ejarei dar dastras hast
                    users.get(fBike).deleteBike(new Bike(false, serial)); // pak kardan az list karbari ke ejare karede bood
                } else {
                    System.out.println("The bike not rented!"); // ejare nadade boodim aslan
                }
            } else {
                System.out.println("Not found bike with this serial!");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    //2
    public static void showRentedBikes() {
        boolean check = false;
        if (bikes.size() > 0) {
            int c = 1; // shomardan tedad motorha
            for (Bike bike : bikes) {
                if (bike.getIsRented() == true) {
                    System.out.println(c++ + "." + bike);
                    check = true; // yani hade aghal yek motor ejareii darim
                }
            }
            if (check == false) {
                System.out.println("Thre is no rented bike!");// motor ejareii nadarim
            }
        } else {
            System.out.println("There is no bike"); // kolan motor nadaim
        }
    }

    //1
    public static void showReturnedBikes() {
        int c = 1;  // shomardan tedad motorha
        boolean check = false;
        if (bikes.size() > 0) {
            for (Bike bike : bikes) {
                if (bike.getIsRented() == false) {
                    System.out.println(c++ + "." + bike);
                    check = true; // yani hade aghal yek motor ejareii darim
                }
            }
            if (check == false) {
                System.out.println("Thre is no returned bike!"); // motor dardastres nadarim
            }
        } else {
            System.out.println("There is no bike");  // kolan motor nadaim
        }
    }

    public static void SaveBikes(ArrayList<Bike> bikes) {

        try {
            FileWriter myWriter = new FileWriter("bikes.txt"); // dorost kardan file motorha
            for (Bike bike : bikes) {
                myWriter.write(bike.getSerialNumber() + "," + bike.getIsRented() + "," + bike.getType()); // neveshtan etelat motor
                if (bike instanceof ElectricBike) { // agar motor az noe Electric bashad
                    ElectricBike eb = (ElectricBike) bike;
                    myWriter.write("," + eb.getCharge()); // noe charge ra minevisad dar file
                }
                myWriter.write("\r\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void SaveUsers(ArrayList<User> users) {

        try {
            FileWriter myWriter = new FileWriter("users.txt"); // file users.txt ra ijad mikonad
            for (User user : users) {
                myWriter.write(user.getUserName()); // neveshtane username
                int index = 0;
                int size = user.getSizeOfBikes();
                for (int i = 0; i < size; i++) { // in halghe barye neveshtane eteleate motorhaye har karbar ast
                    Bike bikeInfo = user.getBike(index++);
                    myWriter.write("@" + bikeInfo.getSerialNumber() + "," + bikeInfo.getIsRented() + "," + bikeInfo.getType());
                    if (bikeInfo instanceof ElectricBike) { // agar motor elictric bashad on ra dar file lahaz mikonad
                        ElectricBike eb = (ElectricBike) bikeInfo;
                        myWriter.write("," + eb.getCharge()); // neveshtane charge
                    }
                }

                myWriter.write("\r\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void loadData() throws FileNotFoundException {
        //Part1 Load Users
        Scanner scan = new Scanner(new File("users.txt"));
        int c = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] mainInfo = line.split("@");
            users.add(new User(mainInfo[0]));
            for (int i = 1; i < mainInfo.length; i++) {
                String[] bikeInfo = mainInfo[i].split(",");
                if (bikeInfo[2].equals("Bike")) {
                    Bike tBike = new Bike(Boolean.parseBoolean(bikeInfo[1]), bikeInfo[0]);
                    users.get(c).addBike(tBike);

                } else if (bikeInfo[2].equals("RoadBike")) {
                    RoadBike tRB = new RoadBike(Boolean.parseBoolean(bikeInfo[1]), bikeInfo[0]);
                    users.get(c).addBike(tRB);
                } else {
                    ElectricBike ebt = new ElectricBike(Boolean.parseBoolean(bikeInfo[3]), Boolean.parseBoolean(bikeInfo[1]), bikeInfo[0]);
                    users.get(c).addBike(ebt);
                }
            }
            c++;
        }

        //Part2 Load bikes
        Scanner scanBike = new Scanner(new File("bikes.txt"));
        while (scanBike.hasNextLine()) {
            String line = scanBike.nextLine();
            String[] bikeInfo = line.split(",");
            if (bikeInfo[2].equals("Bike")) {
                Bike tBike = new Bike(Boolean.parseBoolean(bikeInfo[1]), bikeInfo[0]);
                bikes.add(tBike);

            } else if (bikeInfo[2].equals("RoadBike")) {
                RoadBike tRB = new RoadBike(Boolean.parseBoolean(bikeInfo[1]), bikeInfo[0]);
                bikes.add(tRB);
            } else {
                ElectricBike ebt = new ElectricBike(Boolean.parseBoolean(bikeInfo[3]), Boolean.parseBoolean(bikeInfo[1]), bikeInfo[0]);
                bikes.add(ebt);
            }
        }
    }

    public static void main(String[] args) {

        while (true) {
            int selection = menu(); // entekhab menu
            if (selection == 0) { // yani agar karbar kharej shod
                break;
            }
        }


    }

}
