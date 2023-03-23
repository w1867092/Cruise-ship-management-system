/**
 COPYRIGHT (C) Kumod Hansidu Munasingha Arachchi - 20210336 - W1867092- kumod.20210336@iit.ac.lk. ALL Rights Reserved.
 Task I  Cruise Ship Passenger controller
 Software Development II Coursework L4 Sem2
 @author Kumod Hansidu Munasingha Arachchi
 @version 1  2022-04-03
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cabin[] cabinList = new Cabin[12];
        initialiseCabinsList(cabinList);
        while (true){
            cruiseMenu(cabinList);
        }
    }

    private static void initialiseCabinsList(Cabin[] cabinList) {                         // empty all cabins in the beginning
        for (int i = 0; i < 12; i++) {
            cabinList[i] = new Cabin();
            cabinList[i].initialiseCustomerList();
        }
    }

    private static void cruiseMenu(Cabin[] cabinList){                                    // Cruise Ship Passenger controller menu

            Scanner kscanner = new Scanner(System.in);
        System.out.println("  ");
        System.out.println("^~~~~~- Welcome to Cruise Ship Passenger controller -~~~~~^");

        System.out.println(" A :- Add customer to cabin ");
        System.out.println(" V :- View all cabins ");
        System.out.println(" E :- Display Empty cabins ");
        System.out.println(" D :- Delete customer from cabin ");
        System.out.println(" F :- Find cabin from customer name ");
        System.out.println(" S :- Store program data into file ");
        System.out.println(" L :- Load program data from file ");
        System.out.println(" O :- View passengersOrdered alphabetically by name ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("  ");
        System.out.println("           Enter above letter what you want to do");
        System.out.println("  ");

            String GetInput = kscanner.next().toLowerCase(Locale.ROOT);

            switch (GetInput){
                case "a": addCustomer(cabinList);
                    break;
                case "v": ViewAllCabins(cabinList);
                    break;
                case "e": DisplayEmptyCabins(cabinList);
                    break;
                case "d": DeleteCustomerFromCabin(cabinList);
                    break;
                case "f": FindCabinFromCustomerName(cabinList);
                    break;
                case "t": DisplayPassengersExpenses (cabinList);
                    break;
                case "s": StoreProgramDataIntoFile(cabinList);
                    break;
                case "l": LoadProgramDataFromFile();
                    break;
                case "o": ViewPassengersOrderedAlphabeticallyByName(cabinList);
                    break;

                default:
                    System.out.println("  Invalid Input !!! ");

            }
        }

    private static boolean isShipFull(Cabin[] cabinArray) {                                  // check all cabins ara full
        for(Cabin cabin : cabinArray) {
            if(!cabin.Full()) {
                return false;
            }
        }
        return true;
    }


    private static void addCustomer(Cabin[] cabinArray) {                                // add Customer to cabin
        Scanner scanner = new Scanner(System.in);
        while (true){
            try{
                System.out.println("  Enter Cabin number between (0 - 11)  ");
                System.out.println("Enter the cabin number :- ");
                int cabinNo = scanner.nextInt();
                if (cabinNo == 12){
                    break;
                }
                if (0 <= cabinNo && cabinNo < 12 && !cabinArray[cabinNo].Full()){
                    int passengerNo = 1;
                    while (true) {
                        boolean endLoop = false;
                        System.out.println("Adding passenger " + passengerNo);
                        System.out.println("Enter First name :- ");
                        String firstName = scanner.next();
                        System.out.println("Enter Surname :- ");
                        String surname = scanner.next();
                        System.out.println("Enter Expenses :- ");
                        double expenses = scanner.nextDouble();
                        for (int i = 0; i < 3; i++ ){
                            if(cabinArray[cabinNo].getCustomerList()[i].getFirstName().equals("e")) {
                                cabinArray[cabinNo].getCustomerList()[i].setFirstName(firstName);
                                cabinArray[cabinNo].getCustomerList()[i].setSurname(surname);
                                cabinArray[cabinNo].getCustomerList()[i].setExpenses(expenses);
                                passengerNo++;
                                if(passengerNo == 4) {
                                    endLoop = true;
                                    break;
                                }
                                System.out.println("Do you want to add another passenger into this same cabin, (cabin number " + cabinNo + ")?");
                                System.out.println("Press 'Y' to add another passenger and Press any other key to exit and go to the previous menu.");
                                scanner.nextLine();
                                String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
                                if(!userInput.equals("y")) {
                                    endLoop = true;
                                }
                                break;
                            }
                        }
                        if(endLoop) {
                            System.out.println("Leaving cabin " + cabinNo + "...");
                            break;
                        }
                    }
                } else if(cabinArray[cabinNo].Full()) {
                    System.out.println("Try another cabin.");
                } else {
                    System.out.println("Enter a number between  0 - 12");
                }
            } catch (InputMismatchException x) {
                System.out.println("Enter a number between  0 - 12");
                break;
            }
        }
    }

    public static void ViewAllCabins(Cabin[] cabins) {                                      // View All Cabins what are empty and full
        for(int i = 0; i < 12; i++) {
            if(!cabins[i].Empty()) {
                System.out.println("----------------------------------------");
                System.out.println("\nCabin " + i + " : ");
                for (int j = 0; j < 3; j++) {
                    Passenger passenger = cabins[i].getCustomerList()[j];
                    if (!passenger.getFirstName().equals("e")) {
                        System.out.println("First Name = " + passenger.getFirstName());
                        System.out.println("Surname    = " + passenger.surname);
                        System.out.println("Expenses   = " + passenger.getExpenses() + "\n");
                        System.out.println("----------------------------------------");
                    }
                }
            } else {
                System.out.println(" The Cabin "+i+" is  empty  \n");
            }
        }
        System.out.println();
    }

    public static void DisplayEmptyCabins(Cabin[] cabins) {                               // Display only Empty Cabins
        if(!isShipFull(cabins)) {
            for(int i = 0; i < 12; i++) {
                if(!cabins[i].Full()) {
                    for(int j = 0; j < 3; j++) {
                        if(cabins[i].getCustomerList()[j].getFirstName().equals("e")) {
                            System.out.println("Cabin " + i + " has empty seats.");
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("No empty cabins detected");
        }
    }

    public static void DeleteCustomerFromCabin(Cabin[] cabins) {                           // Delete Customer From Cabin
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer name :- ");
        String input = scanner.nextLine();
        for(int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                if(cabins[i].getCustomerList()[j].getFirstName().toLowerCase(Locale.ROOT).equals(input.toLowerCase(Locale.ROOT))) {
                    cabins[i].getCustomerList()[j].setFirstName("e");
                    cabins[i].getCustomerList()[j].setSurname("e");
                    cabins[i].getCustomerList()[j].setExpenses(0);
                    System.out.println(" * Passenger Success fully Deleted * ");
                    break;
                }
            }
        }
    }

    public static void FindCabinFromCustomerName(Cabin[] cabins) {                            // Find Cabin FromCustomer Name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer name :-");
        String input = scanner.nextLine();
        boolean customerAvailable = false;
        for(int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                if(cabins[i].getCustomerList()[j].getFirstName().toLowerCase(Locale.ROOT).equals(input.toLowerCase(Locale.ROOT))) {
                    System.out.println(" Cabin Number :- " + i);
                    customerAvailable = true;
                    break;
                }
            }
        }
        if(!customerAvailable){
            System.out.println("Invalid passenger name");
        }
    }

    public static void DisplayPassengersExpenses(Cabin[] cabins) {                           // Display Passengers Expenses
        System.out.println("----------------------------------------------");
        double total = 0;
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 3; j++) {
                if(!(cabins[i].getCustomerList()[j].getExpenses() == 0)) {
                    System.out.println("Cabin number : "+i+" Passenger name : "+cabins[i].getCustomerList()[j].getFirstName() + "   -   total bill /=" + cabins[i].getCustomerList()[j].getExpenses());
                    total += cabins[i].getCustomerList()[j].getExpenses();
                }
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println(" * Passengers Total Cost  =   " + total);
        System.out.println("----------------------------------------------");
    }

    public static void StoreProgramDataIntoFile(Cabin[] cabins) {                           // Store program data into a text file
        try {
            FileWriter myWriter = new FileWriter("Data.txt");
            for (int i = 0; i < 12; i++) {
                myWriter.write("Cabin : " + i);
                myWriter.write(System.lineSeparator());
                for (int j = 0; j < cabins[j].customerList.length; j++) {
                    myWriter.write("Passenger " + j + " : ");
                    myWriter.write("{FirstName : " + cabins[i].customerList[j].getFirstName());
                    myWriter.write(", Surname : " + cabins[i].customerList[j].getSurname());
                    myWriter.write(", Expenses : " + cabins[i].customerList[j].getExpenses() + "}");
                    myWriter.write(System.lineSeparator());
                }
            }
            myWriter.close();
            System.out.println("Ship data saved!");
        } catch (IOException ex ) {
            System.out.println("Error!!! IOException " + ex );
        }
        System.out.println("-------------------------------");
    }

    public static void LoadProgramDataFromFile() {                                         // Load data from the text file
        try {
            File file = new File("Data.txt");
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }
            System.out.println("----------------------------------------------");
        } catch (IOException ex) {
            System.out.println("Error!!!  IOException " + ex );
        }
    }

    public static void ViewPassengersOrderedAlphabeticallyByName(Cabin[] cabinArray) {            // View Passengers Alphabetical order on name
        Passenger[] passengerArray = new Passenger[36];
        int index = 0;
        for (Cabin cabin : cabinArray) {
            for (int j = 0; j < 3; j++) {
                if (!cabin.getCustomerList()[j].getFirstName().equals("e")) {
                    passengerArray[index] = cabin.getCustomerList()[j];
                    index++;
                }
            }
        }

        Passenger tempPassenger = null;
        for (int i = 0; i < passengerArray.length; i++) {
            for (int j = i + 1; j < passengerArray.length; j++) {
                if (passengerArray[i] != null && passengerArray[j] != null) {
                    if (passengerArray[i].getFirstName().compareTo(passengerArray[j].getFirstName()) > 0) {
                        tempPassenger = passengerArray[i];
                        passengerArray[i] = passengerArray[j];
                        passengerArray[j] = tempPassenger;
                    }
                }
            }
        }

        System.out.println(" Passengers in Alphabetic order :- ");
        for (Passenger s : passengerArray) {
            if (s != null && !s.getFirstName().equals("e")) {
                System.out.println(s.getFirstName() + " ");
            }
        }
    }
}