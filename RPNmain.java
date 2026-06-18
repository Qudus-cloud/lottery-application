import java.util.Scanner; //import scanner for user input
public class RPNmain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //create scanner object to read input from keyboard
        RPNCalculator main = new RPNCalculator(); //create Rpncalculator object
        boolean running = true; //variable to control menu loop

        //loop until user chooses to exit
        while (running) {
            // display menu options
            System.out.println("\n------ RPN CalculatorMenu ----- ");
            System.out.println("1. View Instructions");
            System.out.println("2. Run RPN Calculator");
            System.out.println("3. Exit");
            System.out.println("Enter choice");

            //Read user's menu chioce
            int choice = sc.nextInt();
            sc.nextLine(); //clear input buffer

            //handle menu choice
            switch(choice){
                
                case 1 : //option1 : instructions
                System.out.println("\nInstruction:");
                System.out.println(". Enter numbers and operators separated by commas");
                System.out.println(".supported operators: + - * /");
                System.out.println(".Example:4,6,+");
                break;

                //options :Run calculator
                case 2:
                    System.out.println("\nEnter RPN expressioin:");
                    String expression = sc.nextLine();

                    // call RPN calculator logic
                    int result = main.evaluate(expression);

                    //display result if evaluation was successful
                    if(result != -1){
                        System.out.println("Result:" +result);
                    }
                    break;

                    default:  //invalid menu option
                    System.out.println("invalid chioce. please try again.");
            }
        }
         //close scanner 
         sc.close();
    }
}
