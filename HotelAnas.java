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

            switch (choice) {
                case 1:
                    System.out.println("\n--- FLOOR PLAN ---");
                    printFloorPlan();
                    break;

                case 2:
                case 3:
                    printFloorPlan();

                    // -1 at the end because humans start counting from 1 but java starts from 0
                    int floor = takeInput("Please enter the floor number: ",
                            FLOORPLAN.length, false, sc) - 1;

                    printSingleFloor(floor, false);

                    int roomNumber = takeInput("and the room number: ",
                            FLOORPLAN[0].length, false, sc) - 1;

                    if (choice == 2)
                        bookARoom(floor, roomNumber);
                    else
                        freeARoom(floor, roomNumber);

                    break;

                case 4:
                    firstFree();
                    break;

                case 5:
                    findThree();
                    break;

                case 0:
                default:
                    breakTheLoop = true;
                    break;
            }

            sc.nextLine(); // Clears the input

            System.out.println("--------------------------------");

            if (!breakTheLoop) {
                System.out.println("Press enter to continue...");
                sc.nextLine(); // Waits for the user to press enter before showing the menu again
            }

        }

        sc.close(); // Close the scanner to prevent memory leaks
    }

    public static void bookARoom(int floor, int roomNumber) {
        // This method is used to book a room in the given floor.
        // It checks if the room is already booked and prints a
        // message if it is and books the room otherwise and prints a message.

        if (FLOORPLAN[floor][roomNumber]) {
            System.out.println("\nRoom is already booked\n");
            return;
        }

        FLOORPLAN[floor][roomNumber] = true;
        System.out.println("\nRoom " + (roomNumber + 1) + " on floor " + (floor + 1) + " has been booked for you.\n");
        return;
    }

    public static void freeARoom(int floor, int roomNumber) {
        // This method is used to free a room in the given floor.
        // It checks if the room is already free and prints a
        // message if it is and free the room otherwise and prints a message.

        if (!FLOORPLAN[floor][roomNumber]) { // if false
            System.out.println("Room is already vacant.");
            return;
        }

        FLOORPLAN[floor][roomNumber] = false; // It is not free, so make it free
        System.out.println("\nRoom has been vacated.\n");
        return;
    }

    public static void firstFree() {
        // This method returns the first free room in the given range.
        // It search for the given range in each floor and returns an array
        // with the floor number and the room number.

        for (int i = 0; i < FLOORPLAN.length; i++) {
            for (int j = 0; j < FLOORPLAN[i].length; j++) {

                if (!FLOORPLAN[i][j]) {
                    System.out.println("\nFree room found in floor " + (i + 1) + " and room number " + (j + 1) + "\n");
                    return;
                }

            }
        }

        System.out.println("No free room found");
        return;
    }

    public static void findThree() {
        // This method is used to find three consecutive rooms that are open in the
        // floor plan. It then prints the floor number and the room numbers for each
        // room.

        System.out.println();
        for (int i = 0; i < FLOORPLAN.length; i++) {
            for (int j = 0; j < FLOORPLAN[i].length; j++) {

                if (!FLOORPLAN[i][j] && !FLOORPLAN[i][j + 1] && !FLOORPLAN[i][j + 2]) {
                    // checking if rooms j, j + 1 and j + 2 are open on floor i
                    System.out.println("Three rooms available in Floor " + (i + 1));
                    System.out.println("Room numbers: " + (j + 1) + ", " + (j + 2) + " and " + (j + 3));
                    System.out.println();
                    return;
                }

            }
        }

        System.out.println("No three consecutive rooms are available.");
        System.out.println();
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

    public static void printFloorPlan() {
        // Print the entire floor plan.
        System.out.println("\nX for closed rooms and empty for open rooms.\n");

        for (int i = 0; i < FLOORPLAN.length; i++) {
            printSingleFloor(i, true);
        }
    }

    public static void printSingleFloor(int floor, boolean batch) {
        // Print a single floor.

        // If batch is true that means that it is printing mutliple floors.
        // so it doesn't need the room numbers on the top. If the room number is needed,
        // then that should be implemented in the printFloorPlan method.
        if (!batch) {
            System.out.println("\n--------------------------------");
            System.out.print("Room numbers:\t");

            int roomsPerFloor = FLOORPLAN[floor].length;

            for (int i = 1; i <= roomsPerFloor; i++) {
                System.out.print("[" + i + "] ");
            }

            System.out.println();
        }

        // X for closed room and empty for open room.
        // floor + 1 because humans count from 1 but
        // java counts from 0.
        System.out.print("Floor " + (floor + 1) + ":\t");
        for (int i = 0; i < FLOORPLAN[floor].length; i++) {

            if (FLOORPLAN[floor][i]) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }

        }

        System.out.println("\n");
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
