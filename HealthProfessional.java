import java.io.Serializable;

/*
 * The HealthProfessional class represents a medical staff member
 * in the hospital operation scheduler system.
 *
 * Examples of health professionals include doctors, nurses,
 * physiotherapists, etc.
 *
 * Each health professional has their own electronic diary
 * where patient appointments are stored.
 *
 * The class implements Serializable so that health professional
 * objects and their diaries can be saved to and loaded from a file.
 */
public class HealthProfessional implements Serializable {

    // Unique ID used to identify each health professional
    private int id;

    // Name of the health professional
    private String name;

    // Profession of the health professional
    // Examples: Doctor, Nurse, Physiotherapist
    private String profession;

    // Work location or office of the professional
    // Examples: Ward A, Room 203, Surgery Unit
    private String location;

    // Each health professional has their own diary
    // which stores their appointments
    private Diary diary;

    /*
     * Constructor for creating a new HealthProfessional object.
     *
     * When a new professional is created, a new empty diary
     * is automatically created for them.
     */
    public HealthProfessional(int id, String name, String profession, String location) {

        // Assign values to the class variables
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.location = location;

        // Create a new diary for this health professional
        this.diary = new Diary();
    }

    /*
     * Returns the ID of the health professional.
     * This is used to identify professionals when
     * adding or viewing appointments.
     */
    public int getId() {
        return id;
    }

    /*
     * Returns the diary associated with the health professional.
     * This allows the program to access and manage appointments
     * for this specific professional.
     */
    public Diary getDiary() {
        return diary;
    }

    /*
     * The toString() method returns a formatted string
     * representing the health professional's information.
     *
     * This method is automatically used when the object
     * is printed using System.out.println().
     */
    @Override
    public String toString() {
        return "ID: " + id +
               " | Name: " + name +
               " | Profession: " + profession +
               " | Location: " + location;
    }
}