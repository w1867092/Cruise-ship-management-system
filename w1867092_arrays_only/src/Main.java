/**
 COPYRIGHT (C) Kumod Hansidu Munasingha Arachchi - 20210336 - W1867092- kumod.20210336@iit.ac.lk. ALL Rights Reserved.
 Task I  Cruise Ship Passenger controller
 Software Development II Coursework L4 Sem2
 @author Kumod Hansidu Munasingha Arachchi
 @version 1  2022-03-30
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
        Scanner kScanner = new Scanner(System.in);
        String[] Cruise_Ship = new String[12];
        initialise(Cruise_Ship);
        while (true){
            ControlCabins(Cruise_Ship);
        }
    }

    public static void initialise (String[] Cruise_Ship) {                                        // empty all cabins in the beginning

        for (int i = 0; i < 12; i++) {
            Cruise_Ship[i] = "e";

        }
    }

    public static void ControlCabins(String[] Cruise_Ship) {                                       // Cruise Ship Passenger controller menu
        Scanner kScanner = new Scanner(System.in);
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
        String GetInput = kScanner.next().toLowerCase(Locale.ROOT);

        switch (GetInput){
            case "a": addCustomer(Cruise_Ship);
                break;
            case "v": ViewAllCabins(Cruise_Ship);
                break;
            case "e": DisplayEmptyCabins(Cruise_Ship);
                break;
            case "d": DeleteCustomerFromCabin(Cruise_Ship);
                break;
            case "f": FindCabinFromCustomerName(Cruise_Ship);
                break;
            case "s": StoreProgramDataIntoFile(Cruise_Ship);
                break;
            case "l": LoadProgramDataFromFile();
                break;
            case "o": ViewPassengersOrderedAlphabeticallyByName(Cruise_Ship);
                break;

            default:
                System.out.println("  Invalid Input !!! ");

        }

    }

    private static void ViewPassengersOrderedAlphabeticallyByName(String[] cruise_ship) {         // View Passengers Alphabetical order on name
        String tempName = null;
        for (int i = 0; i < cruise_ship.length; i++) {
            for (int j = i+1 ; j < cruise_ship.length; j++) {
                if (cruise_ship[i].compareTo(cruise_ship[j]) > 0){
                    tempName = cruise_ship[i];
                    cruise_ship[i] = cruise_ship[j];
                    cruise_ship[j] = tempName;
                }
            }
        }
        System.out.println("The names in the alphabetical order are :- ");
        for (String s : cruise_ship) {
            if (Objects.equals(s, "e")) {
            } else {
                System.out.println(s + " ");
            }
        }
    }

    private static void LoadProgramDataFromFile() {                                     // Load data from the text file
        try {
            File file = new File("Passenger.txt");
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }
            System.out.println("----------------------------------------------");
        } catch (IOException x) {
            System.out.println("Error! " + x );
        }

    }

    private static void StoreProgramDataIntoFile(String[] cruise_ship) {                  // Store program data into a text file
        try {
            FileWriter myWriter = new FileWriter("Passenger.txt");
            for (int i = 0; i < 12; i++) {
                if(!cruise_ship[i].equals("e")) {
                    myWriter.write("Cabin : " + i + "    " + "Passengers Name : ");
                    myWriter.write(cruise_ship[i]);
                } else{
                    myWriter.write("Cabin : " + i );
                    myWriter.write("  is Empty cabin");
                }
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Data successfully saved");
        } catch (IOException x ) {
            System.out.println("Error!!" + x );
        }
    }

    private static void FindCabinFromCustomerName(String[] cruise_ship) {                     // Find Cabin FromCustomer Name
        Scanner scanner = new Scanner(System.in);
        boolean ExistingPassenger = false;
        System.out.println("Enter passenger name :- ");
        String passenger = scanner.next().toLowerCase(Locale.ROOT);
        for (int i=0; i<12; i++){
            if (cruise_ship[i].toLowerCase(Locale.ROOT).equals(passenger)){
                System.out.println("Cabin Number :- " + i);
                ExistingPassenger = true;
                break;
            }
        }
        if (!ExistingPassenger){
            System.out.println("Pleases Check Passenger's name \n Try a again !!!");
        }
    }

    private static void DeleteCustomerFromCabin(String[] cruise_ship) {                           // Delete Customer From Cabin
        Scanner kScanner = new Scanner(System.in);
        boolean ExistingPassenger = false;
        System.out.println("Enter passenger name :- ");
        String passenger = kScanner.next().toLowerCase(Locale.ROOT);
        for (int i=0; i<12; i++){
            if (cruise_ship[i].toLowerCase(Locale.ROOT).equals(passenger)){
                cruise_ship[i] = "e";
                ExistingPassenger = true;
                System.out.println(" * Passenger Success fully Deleted *");
                break;
            }
        }
        if (!ExistingPassenger){
            System.out.println("Pleases Check Passenger's name \n Try a again !!!");
        }
    }

    private static void DisplayEmptyCabins(String[] cruise_ship) {                                  // Display only Empty Cabins
        System.out.println("  ~-All Empty Cabin-~  ");
        for (int i = 0; i < 12; i++) {
            if (cruise_ship[i].equals("e")){
                System.out.println("Cabin "+ i + " is Empty");
            }
        }
    }

    private static void ViewAllCabins(String[] cruise_ship) {                                       // View All Cabins what are empty and full
        for (int i = 0; i < 12; i++) {
            if (cruise_ship[i].equals("e")){
                System.out.println("Cabin "+ i + " is Empty");
            }
            else {
                System.out.println("The Cabin number : "+ i +"   Passenger name :- "+ cruise_ship[i]);
            }
        }
    }

    private static void addCustomer(String[] cruise_ship) {                                       // add Customer to cabin
        Scanner kScanner = new Scanner(System.in);
        System.out.println("  Enter Cabin number between (0 - 11) , Terminate and go to the menu  enter ''12'' ");
        while (true){
            try {
                System.out.println("Enter Cabin Number :- ");
                int CabinNumber = kScanner.nextInt();

                if (0<=CabinNumber && CabinNumber<=12){

                    if (CabinNumber==12){
                        break;
                    }
                    if (cruise_ship[CabinNumber].equals("e")){
                        System.out.println("Enter passenger Name :- ");
                        String passengerName = kScanner.next();
                        System.out.println("passenger  ''"+passengerName+"''  Success fully added to the cabin  ");
                        System.out.println("  ");
                        cruise_ship[CabinNumber] = passengerName;
                    }
                    else {
                        System.out.println("Cabin already booked \n Enter another Cabin number");
                    }
                }
                else {
                    System.out.println("  Invalid Input !!!");
                }
            }catch (InputMismatchException y){
                System.out.println("Please enter Cabin number between (0 - 11) , Terminate and go to the menu  enter ''12''  ");
                break;
            }
        }
    }
}