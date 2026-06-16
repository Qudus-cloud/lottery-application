import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Lottery {
    Set<Integer> userNumbers; //set  to store user's chosen numbers (must be uniques)
    Set<Integer> drawNumbers;  // set to store the randomly generated draw numbers (must be uniques)
    
    // minimum and maximum values fro a valid lottery number 
    int minRange = 1;
    int maxRange = 10;
    int NPT =6;  // NPT - Number per ticket
    int TC =2;  // TC - ticket cost per week
    Random r = new Random(); // random object for generating draw number
    
    // constructor
    public Lottery(){
        // create empty set for user annd draw numbers
        userNumbers = new HashSet<>();
        drawNumbers = new HashSet<>();
    }
    //Allows the user to set a custom range e.g. 1 to 21
    public boolean setRange(int min, int max){

        //check if the range contains at least 6 different numbers
        if ((max-min+1)< NPT) {
            return false;
        }
        // save the range
        minRange = min;
        maxRange = max;
        clearSets();  // clear existing sets so old numbers don't conflict with the new range
        return true; // range was valid 
    }
    // Adds one user number into the userNumbers set after validation
    public boolean addUserNumber(int number){
        //Check if number is inside the allowed range
        if(number< minRange || number>maxRange){
            return false;
        }
        //Make sure user can't add more than 6 numbers
        if (userNumbers.size() >= NPT) {
            return false;
        }
        return userNumbers.add(number);// Add to set
    }
     // Generates 6 unique random numbers for the draw
    public void generateDrawNumbers() {
        drawNumbers.clear(); //Clear previous draw results so each draw is fresh
        while(drawNumbers.size() <NPT){ //Keep generating numbers until we have 6 unique ones
            int num = r.nextInt(maxRange - minRange + 1 ) +minRange; //Generate a random number within the range minRange..maxRange (inclusive)
            drawNumbers.add(num); //Add it to the set
        }
    }
     // Calculates winnings by finding how many numbers match between userNumbers and drawNumbers
    public int calculateWinnings(){
        Set<Integer> intersection = new HashSet<>(userNumbers);// Create a COPY of userNumbers
        intersection.retainAll(drawNumbers);//Perform intersection
        int matches = intersection.size();//Count how many matches

        // Return prize based on how many matches
        switch (matches) {
            case 3:
                return 25;
            case 4:
                return 100;
            case 5:
                return 1000;
            case 6:
                return 1000000;
            default:
                return 0;
        }
    }
    // Runs the lottery for multiple weeks using the same user numbers each week
    public int runMultipleWeeks(int weeks){
        int totalWinnings =0; // start total winnings at 0

        for( int i =1; i <= weeks; i++){ // Repeat the draw for each week
            generateDrawNumbers(); //Generate a new set of draw numbers for this week
            totalWinnings += calculateWinnings(); // Calculate winnings for this week and add to total 
        }
        return totalWinnings; // Return total winnings after all weeks
    }
    public void clearSets(){ // clears the user and draw sets
        userNumbers.clear(); // Remove all user numbers
        drawNumbers.clear(); //Remove all draw numbers
    }
    public static void main(String[] args) { // preogram starts 
        Scanner scanner = new Scanner(System.in); // Create scanner to read user input
        Lottery lottery = new Lottery(); // Create Lottery object so we can use the methods
        boolean running = true; //Keep the program running until user exits
        while (running) {
            // print menu options
            System.out.println("\n------LOTTERY MENU -------");
            System.out.println("1. Set lottery numbver range");
            System.out.println("2. Enter lottery numbers");
            System.out.println("3. Run one draw");
            System.out.println("4. Run lottery for multiple weeks");
            System.out.println("choose option:");

            int choice = scanner.nextInt(); // Read user chioce
             
            //Run correct action based on choice
            switch (choice) {
                case 1:
                    // Range setting option
                    System.out.println("Enter minimum number");
                    int min =scanner.nextInt();
                    System.out.println("Enter maximum number");
                    int max = scanner.nextInt();
                     
                    // Validate and set range
                    if (lottery.setRange(min, max)){
                        System.out.println("Range set successfully");
                    } else{
                        System.out.println("invalid range. Must allow at least 6 numbers.");
                    }
                    break;

                case 2:
                    // user enters their 6 numbers
                    lottery.clearSets();
                    System.out.println("Enter 6 unique lottery numbers");
                    
                    // keep asking until 6 unique lottery numbers has been stored
                    while (lottery.userNumbers.size() < 6){
                        int num = scanner.nextInt();

                        // If invalid or duplicate, addUserNumber returns false
                        if (!lottery.addUserNumber(num)) {
                            System.out.println("invalid or duplicate number.");
                        }
                    }
                    //Display user set
                    System.out.println("Your numbers:"+ lottery.userNumbers);
                    break;

                case 3:   
                    //Run one draw
                    lottery.generateDrawNumbers();
                    int winnings = lottery.calculateWinnings();
                    
                    //Display results
                    System.out.println("Draw numbers: " + lottery.drawNumbers);
                    System.out.println("You won £" + winnings);
                    break; 

                case 4:
                    //Rum multiple 
                    System.out.print("Enter number of weeks: ");
                    int weeks = scanner.nextInt();

                    int totalWon = lottery.runMultipleWeeks(weeks); // run simulation 
                    int totalSpent = weeks * 2 ; // calcualting spending
                    
                    //Display tools
                    System.out.println("Total winnings: £" + totalWon);
                    System.out.println("Total spent: £" + totalSpent);
                     
                    // profit/loss message
                    if (totalWon > totalSpent) {
                        System.out.println("You made a PROFIT!");
                    } else {
                        System.out.println("You made a LOSS.");
                    }
                    break;    

                case 5:
                    // exit program
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");// invalid menu choice
             } 
        } 
        scanner.close(); // close scanner
    }
}    

            
                
            
            
        
   
