import java.util.*;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public synchronized boolean bookTicket(String name) {
        if (availableSeats > 0) {
            System.out.println(name + " successfully booked a seat. Remaining seats: " + (--availableSeats));
            return true;
        } else {
            System.out.println(name + " failed to book a seat. No seats available.");
            return false;
        }
    }
}

class Passenger extends Thread {
    private TicketBookingSystem system;
    private String passengerName;

    public Passenger(TicketBookingSystem system, String name, int priority) {
        this.system = system;
        this.passengerName = name;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookTicket(passengerName);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);
        List<Passenger> passengers = new ArrayList<>();

        passengers.add(new Passenger(system, "VIP_1", Thread.MAX_PRIORITY));
        passengers.add(new Passenger(system, "VIP_2", Thread.MAX_PRIORITY));
        passengers.add(new Passenger(system, "Regular_1", Thread.NORM_PRIORITY));
        passengers.add(new Passenger(system, "Regular_2", Thread.NORM_PRIORITY));
        passengers.add(new Passenger(system, "Regular_3", Thread.NORM_PRIORITY));
        passengers.add(new Passenger(system, "Regular_4", Thread.MIN_PRIORITY));

        for (Passenger p : passengers) {
            p.start();
        }

        for (Passenger p : passengers) {
            try {
                p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
