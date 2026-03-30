package week2;

public class ParkingLot {
    private String[] spots = new String[500];

    public int parkVehicle(String plate) {
        int hash = Math.abs(plate.hashCode()) % 500;
        int i = hash;

        while (spots[i] != null) {
            i = (i + 1) % 500;
            if (i == hash) {
                return -1;
            }
        }

        spots[i] = plate;
        return i;
    }

    public void exitVehicle(int spotIndex) {
        if (spotIndex >= 0 && spotIndex < spots.length) {
            spots[spotIndex] = null;
        }
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        String car1 = "ABC-123";
        String car2 = "XYZ-789";

        int index1 = lot.parkVehicle(car1);
        int index2 = lot.parkVehicle(car2);

        System.out.println("Vehicle " + car1 + " parked at spot: " + index1);
        System.out.println("Vehicle " + car2 + " parked at spot: " + index2);

        lot.exitVehicle(index1);
        System.out.println("Spot " + index1 + " is now free.");
    }
}
