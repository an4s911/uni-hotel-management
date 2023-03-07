public class HotelAnas {
    // The FLOORPLAN is a 2D array, in which the rows are floors,
    // and the columns are the rooms.
    // Each room is represented by a boolean value,
    // where true means it is closed and false means it is open.
    public static boolean[][] FLOORPLAN = new boolean[6][8];

    public static void main(String[] args) {
        initRooms(); // Initialize the rooms to have every other room booked by default

        System.out.println("---HOTEL MANAGEMENT SYSTEM---");
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
}
