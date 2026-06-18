import java.io.*;
import java.util.HashMap;

/*
 * The SchedulerSystem class manages the entire operation scheduler system.
 *
 * It stores all health professionals and allows operations such as:
 * - Adding health professionals
 * - Displaying professionals
 * - Accessing a professional's diary
 * - Saving system data to a file
 * - Loading system data from a file
 *
 * A HashMap is used to store health professionals where:
 * Key   -> Professional ID
 * Value -> HealthProfessional object
 *
 * This allows fast lookup of professionals by their ID.
 *
 * The class implements Serializable so the whole system
 * can be saved and restored from disk.
 */
public class SchedulerSystem implements Serializable {

    // HashMap used to store all health professionals in the system
    // Key: Integer (Professional ID)
    // Value: HealthProfessional object
    private HashMap<Integer, HealthProfessional> professionals;

    // Keeps track of the next available ID for new professionals
    private int nextId;

    /*
     * Constructor for the SchedulerSystem class.
     * Initializes the HashMap and sets the starting ID.
     */
    public SchedulerSystem() {
        professionals = new HashMap<>();
        nextId = 1;
    }

    /*
     * Adds a new health professional to the system.
     *
     * A unique ID is automatically assigned and stored
     * as the key in the HashMap.
     */
    public void addProfessional(String name, String profession, String location) {

        // Create a new HealthProfessional object
        HealthProfessional hp = new HealthProfessional(nextId, name, profession, location);

        // Store it in the HashMap
        professionals.put(nextId, hp);

        // Inform the user that the professional was added
        System.out.println("Health Professional added with ID: " + nextId);

        // Increment ID for the next professional
        nextId++;
    }

    /*
     * Displays all health professionals currently stored in the system.
     */
    public void displayProfessionals() {

        // Check if the system contains any professionals
        if (professionals.isEmpty()) {
            System.out.println("No health professionals found.");
            return;
        }

        System.out.println("\n--- Health Professionals ---");

        // Loop through all professionals in the HashMap
        for (HealthProfessional hp : professionals.values()) {
            System.out.println(hp);
        }
    }

    /*
     * Retrieves a specific health professional using their ID.
     * This is used when accessing a diary or performing operations
     * on a particular professional.
     */
    public HealthProfessional getProfessional(int id) {
        return professionals.get(id);
    }

    /*
     * Checks whether a health professional exists in the system.
     *
     * Returns true if the ID exists, otherwise false.
     * This helps prevent errors when the user enters an invalid ID.
     */
    public boolean professionalExists(int id) {
        return professionals.containsKey(id);
    }

    /*
     * Saves the entire system data to a file.
     *
     * This includes:
     * - All health professionals
     * - Their diaries and appointments
     * - The next available ID
     */
    public void saveData(String filename) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {

            // Write the HashMap of professionals to the file
            out.writeObject(professionals);

            // Save the next ID so it continues correctly after loading
            out.writeInt(nextId);

            System.out.println("Data saved successfully to " + filename);

        } catch (IOException e) {

            // Handle errors during file saving
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /*
     * Loads system data from a file.
     *
     * This restores:
     * - All stored health professionals
     * - Their diaries and appointments
     * - The next available ID
     */
    @SuppressWarnings("unchecked")
    public void loadData(String filename) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {

            // Read the HashMap of professionals from the file
            professionals = (HashMap<Integer, HealthProfessional>) in.readObject();

            // Restore the next ID value
            nextId = in.readInt();

            System.out.println("Data loaded successfully from " + filename);

        } catch (FileNotFoundException e) {

            // If the save file does not exist
            System.out.println("Save file not found.");

        } catch (IOException | ClassNotFoundException e) {

            // Handle other loading errors
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}