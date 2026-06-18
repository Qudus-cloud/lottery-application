import java.io.Serializable;
import java.util.ArrayList;

public class Diary implements Serializable {

    private ArrayList<Appointment> appointments;

    // Constructor
    public Diary() {
        appointments = new ArrayList<>();
    }

    // Add a new appointment
    public void addAppointment(Appointment appointment) {

        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment added successfully.");
        } else {
            System.out.println("Invalid appointment.");
        }
    }

    // Delete an appointment
    public void deleteAppointment(int index) {

        if (index >= 0 && index < appointments.size()) {
            appointments.remove(index);
            System.out.println("Appointment deleted successfully.");
        } else {
            System.out.println("Invalid appointment number.");
        }
    }

    // Display appointments in the console
    public void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            System.out.println("Appointment " + (i + 1) + ":");
            System.out.println(appointments.get(i));
            System.out.println();
        }
    }

    // Return appointments as text (used by GUI)
    public String getAppointmentsText() {

        if (appointments == null || appointments.isEmpty()) {
            return "No appointments found.";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < appointments.size(); i++) {

            sb.append("Appointment ").append(i + 1).append("\n");
            sb.append("-----------------------------\n");
            sb.append(appointments.get(i).toString()).append("\n\n");
        }

        return sb.toString();
    }

    // Get all appointments
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    // Check if diary is empty
    public boolean isEmpty() {
        return appointments.isEmpty();
    }

    // Number of appointments
    public int size() {
        return appointments.size();
    }
}