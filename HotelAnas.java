import java.util.Scanner;
import java.util.InputMismatchException;

public class HotelAnas {
    // The FLOORPLAN is a 2D array, in which the rows are floors,
    // and the columns are the rooms.
    // Each room is represented by a boolean value,
    // where true means it is closed and false means it is open.
    public static boolean[][] FLOORPLAN = new boolean[6][8];

    public static void main(String[] args) {
        initRooms(); // Initialize the rooms to have every other room booked by default

        System.out.println("---HOTEL MANAGEMENT SYSTEM---");

        Scanner sc = new Scanner(System.in);

        boolean breakTheLoop = false;
        while (!breakTheLoop) {
            // Menu
            System.out.println("\n------------- MENU -------------");
            System.out.println("1. Print out the floor plan");
            System.out.println("2. Book a room");
            System.out.println("3. Free a room");
            System.out.println("4. Find first free room");
            System.out.println("5. Find three consecutive rooms");
            System.out.println("6. Print total number of occupied rooms");
            System.out.println("0. Exit");
            System.out.println("--------------------------------\n");

            int choice = takeInput("Enter your choice: ", 6, true, sc);

            if (!breakTheLoop) {
                System.out.println("Press enter to continue...");
                sc.nextLine(); // Waits for the user to press enter before showing the menu again
            }

        }

        sc.close(); // Close the scanner to prevent memory leaks
    }

    public static void initRooms() {
        // This initializes the pre defined rooms to true or false.
        // This books every other room.
        for (int i = 0; i < FLOORPLAN.length; i++) {
            for (int j = 0; j < FLOORPLAN[i].length; j++) {

                // Every other room
                if (i % 2 == 1 && j % 2 == 0 || i % 2 == 0 && j % 2 == 1) {
                    FLOORPLAN[i][j] = true;
                } else {
                    FLOORPLAN[i][j] = false;
                }

            }
        }
    }

    public static int takeInput(String prompt, int limit, boolean includeZero, Scanner sc) {
        // This method is used to take input from the user.
        // It returns the input as an integer.
        // It checks if the input is valid by checking if the input is between 0 and the
        // limit

        System.out.print(prompt);
        int input;
        try {
            input = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Only integers are allowed!");
            sc.nextLine(); // Clears the input
            return takeInput(prompt, limit, includeZero, sc);
        }

        if (input > limit || (includeZero ? input < 0 : input <= 0)) {
            // Check if the input is greater than the limit
            // or if the input is less than 0 if inlucdeZero is true (or less than or equal
            // to 0 if includeZero is false)

            System.out.println("Invalid choice!");
            return takeInput(prompt, limit, includeZero, sc);
        }

        return input;
    }
}
