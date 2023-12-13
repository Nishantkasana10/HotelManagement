import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

class Room {
    private String name;
    private String contact;
    public String gender;
    private Food[] food;
    private boolean booked;
    private int foodIndex;
    
      String getName() {
        return name;
    }

      String getContact() {
        return contact;
    }
    
    String getGender() {
        return gender;
    }
     Food[] getFood() {
        return food;
    }

    Room() {
        this.name = "";
        this.contact = "";
        this.gender = "";
        this.food = new Food[4];
        this.booked = false;
        this.foodIndex = 0;
    }

    Room(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.food = new Food[4];
        this.booked = true;
    }

    boolean isBooked() {
        return booked;
    }

    void displayFeatures() {
        System.out.println("Room Details:");
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Gender: " + gender);
    }

    void displayBill() {
        double roomCharge = 1000.0; 
        double foodChargePerItem = 80.0; 

        double totalBill = roomCharge;

        for (Food item : food) {
            if (item != null) {
                totalBill += item.getCost(foodChargePerItem);
            }
        }

        System.out.println("\nBilling Details:");
        System.out.println("Room Charge: Rs" + roomCharge);

        for (int i = 0; i < food.length; i++) {
            if (food[i] != null) {
                System.out.println("Food Item " + (i + 1) + " - Quantity: " + food[i].getQuantity() + " - Cost: Rs" + food[i].getCost(foodChargePerItem));
            }
        }

        System.out.println("Total Bill: Rs" + totalBill);
    }

void orderFood() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.80\n2.Pasta\t\tRs.80\n3.Noodles\tRs.80\n");
        System.out.print("How Many Items You Want to order : ");
        int n = sc.nextInt();

        for (int j = 1; j <= n; j++) {
            System.out.print("Please select the item number : ");
            int itemNumber = sc.nextInt();

            if (itemNumber > 0 && itemNumber <= 3) {
                System.out.print("Enter quantity for food item : ");
                int quantity = sc.nextInt();

                // Use the current foodIndex to store each new Food instance
                food[foodIndex] = new Food(itemNumber, quantity);
                foodIndex++;
            } else {
                System.out.println("Invalid item number. Please enter a number between 1 and 3.");
                return;
            }
        }

        System.out.println("Food order placed successfully!");
    }
}

class Food {
    private int itemNumber;
    private int quantity;

    Food(int itemNumber, int quantity) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }

    int getQuantity() {
        return quantity;
    }

    double getCost(double foodChargePerItem) {
        return quantity * foodChargePerItem;
    }
}

public class HotelManagement {
     private static final String FILE_PATH = "hotel_data.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Room[] rooms = new Room[60]; 
        String gender;

        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1. Display room availability");
            System.out.println("2. Book a room");
            System.out.println("3. Order food");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            System.out.println();
            System.out.print("Please Enter Your Choice : ");
            int choice = sc.nextInt();

            if(choice == 1){
                System.out.print("Enter room number (total 60 rooms worth Rs 1000 each): ");
                    int roomNumber = sc.nextInt();
                    if (roomNumber >= 1 && roomNumber <= 60) {
                        if (rooms[roomNumber - 1] != null) {
                            rooms[roomNumber - 1].displayFeatures();
                        } else {
                            System.out.println("Room is not booked.");
                        }
                    } else {
                        System.out.println("Invalid room number");
                    }
                   }
                  else if(choice == 2){
                    System.out.print("Enter room number (total 60 rooms worth Rs 1000 each):");
                    int roomNum = sc.nextInt();
                    if (roomNum >= 1 && roomNum <= 60) {
                        if (rooms[roomNum - 1] == null) {
                            System.out.print("Enter customer name: ");
                            sc.nextLine(); 
                            String customerName = sc.nextLine();
                            System.out.print("Enter contact number: ");
                            String contactNumber = sc.next();
                            if (contactNumber.length() == 10 && contactNumber.matches("\\d+")) {
                            System.out.print("Enter gender (m/f/o): ");
                            gender = sc.next();
                            if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("o")){
                                rooms[roomNum - 1] = new Room(customerName, contactNumber, gender);
                                System.out.println("Room booked successfully!");
                            }
                            else{
                                System.out.println("Please enter correct gender");
                            }
                            }
                            else{
                                System.out.println("Enter Invalid Phone number (Must be 10 digits)!");
                                System.out.println("\nPress enter to continue");
                            } 
                        }                       
                         else {
                            System.out.println("Room is already booked.");
                        }
                    } 
                    else {
                        System.out.println("Invalid room number");
                    }
                }
                  else if(choice ==3){
                    System.out.print("Enter room number:");
                    int room;
                    if (sc.hasNextInt()) {
                        room = sc.nextInt();
                    if (room >= 1 && room <= 60) {
                        if (rooms[room - 1] != null) {
                            rooms[room - 1].orderFood();
                        }
                        else{
                            System.out.println("Room is not booked");
                        }
                    } else {
                        System.out.println("Invalid room number");
                    }
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer room number");
                        sc.next(); 
                    }
                  }
                  else if(choice == 4){
                    System.out.print("Enter room number:");
                    int roomNo = sc.nextInt();
                    if (roomNo >= 1 && roomNo <= 60) {
                        if (rooms[roomNo - 1] != null) {
                            rooms[roomNo - 1].displayBill();
                            rooms[roomNo - 1] = new Room();
                            System.out.println("Checkout successful!");
                            System.out.println("*********Thanks For Coming**********");
                        }
                        else {
                            System.out.println("Room is not booked ");
                        } 
                    } else {
                        System.out.println("Invalid room number");
                    }
                   // After displaying the bill, write the details to a text file
                writeBillToFile(roomNo, rooms[roomNo - 1]);
                  }
                  else if(choice==5){
                   
                    sc.close();
                    System.exit(0);
                    
                  }
                  else{
                   System.out.print("\nPlease enter correct choice!! \n \n");
                  }
                 
            }
        }
        private static void writeBillToFile(int roomNumber, Room room) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter(FILE_PATH, true));
                writer.println("Room Number: " + roomNumber);
        
                double roomCharge = 1000.0;
                double foodChargePerItem = 80.0;
                double totalBill = roomCharge;
        
                for (Food item : room.getFood()) {
                    if (item != null) {
                        totalBill += item.getCost(foodChargePerItem);
                    }
                }
        
                writer.println("Total Bill: Rs" + totalBill);
                writer.println("*********Thanks For Coming**********");
                writer.println(); // Add a new line for the next entry
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
        
}