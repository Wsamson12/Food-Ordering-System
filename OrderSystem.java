/*=============================================================================
|Programmer:  Wong Ho Shun Sang
|Language:  Java
|To Compile:  javac OrderSystem.java
|To Run:  java OrderSystem
|Module:  ITP4510 - Data Structures and Algorithms: Concepts and Implementation 
|Course:  IT114105- Higher Diploma in Software Engineering
|Created:  13-November-2022 (by    )
|Description:  To create a  prototype program to simulate the ordering of the food.   
+===========================================================================*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class OrderSystem {
  private static Scanner sc;
  private static LinkedList orders;
  private static int nextGuestID = 9000;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    orders = new LinkedList();

    while (true) {
      try {
        System.out.print("Please input your member ID [input 0 for guest]: ");
        int memberId = sc.nextInt();
        if (memberId < 0) {
          break; // quite program
        } else if (memberId == 0) {
          inputOrder(memberId);
        } else if (memberId == 9999) {
          adminFunc(memberId);
        } else {
          inputOrder(memberId);
        }
      } catch (InputMismatchException e) {
        System.out.println("Input Error");
        sc.next();
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage());
      }
    }

    System.out.println("Have a nice day!!!");
  }

  public static void inputOrder(int memberId) throws InvalidInputException {
    if ((memberId < 8001 && memberId != 0) || ((memberId > 8199 && memberId < 8200) && memberId != 0) // check member ID
                                                                                                      // range
        || (memberId > 8999 && memberId != 0)) {
      throw new InvalidInputException("Invalid member ID! Please input again.");
    }

    // memberId = 0 is Guest
    if (memberId == 0) {
      memberId = nextGuestID;
      nextGuestID++;
    }
    System.out.println("----------------- Food Menu ----------------");
    System.out.println("Set A : Chicken Salad");
    System.out.println("Set B : Grilled Ribeye Steak");
    System.out.println("Set C : Angel Hair Pasta with Shrimp");
    System.out.println("Set D : Grilled Fish and Potatoes");
    System.out.print("Select food:");
    String food = sc.next().toUpperCase();

    // check Food Menu
    if (!food.equals("A") && !food.equals("B") && !food.equals("C") && !food.equals("D")) {
      throw new InvalidInputException("Invalid input! Please input again.");
    }

    // add food order
    FoodOrder newOrder = new FoodOrder(memberId, food);
    orders.add(newOrder);
    System.out.println("Order added: " + newOrder);
  }

  public static void adminFunc(int memberId) throws InvalidInputException {
    System.out.println("----------------- Admin Function ----------------");
    System.out.println("1 : Print order list");
    System.out.println("2 : Remove order");
    int selection = sc.nextInt();
    if (selection != 1 && selection != 2) {
      throw new InvalidInputException("Invalid input! Please input again.");
    }

    if (selection == 1) {                            // select 1. print orders
      System.out.println("--------------------------------------");
      System.out.println(orders);
      System.out.println("--------------------------------------");
      System.out.println("Total outstanding order:" + orders.count());
    }
    else {                                           // select 2 . remove order
      System.out.print("Enter Member ID:");
      memberId = sc.nextInt();

      try {
        orders.remove(memberId);
        System.out.println("Order with Member ID " + memberId + " removed.");
      } catch (EmptyListException e) {
        System.out.println(e.getMessage());
      }
    }
  }

}