import java.util.Scanner;    // import scanner
public class Treemain {
    public static void main(String[] args) {
        Tree tree =new Tree();    // create a Tree object to store shop items
        Scanner sc = new Scanner(System.in); // create scanner object for user input
        int choice;    // variable to store the user's menu chioce
        do{
            // Display menu options
            System.out.println("\n---- SHOP ITEM MENU ----");
            System.out.println("1. Add Item ");
            System.out.println("2. Print Items(in order) ");
            System.out.println("3. Find Item");
            System.out.println("4. Calculate Total Cost");
            System.out.println("5. Remove Item");
            System.out.println("6. Preorder Traversal");
            System.out.println("7. Postorder Traversal");
            System.out.println("0. Exit");
            System.out.println("choose option");
            choice =sc.nextInt();  // Read user's menu choice

            //perform action based on user's choice
            switch(choice){
                case 1:
                    // prompt user to enter item ID
                    System.out.println("Enter Item ID");
                    int id = sc.nextInt();
                    sc.nextLine();    // Clear input buffer

                    //prompt user to enter item name
                    System.out.println("Enter item Name");
                    String name = sc.nextLine();
                    
                    //prompt user to enter item cost
                    System.out.println("Enter item cost");
                    double cost = sc.nextDouble();

                    // Add item to the tree
                    tree.addItem(id, name, cost);
                    break;

                case 2:
                    // Print items in ascending order of item ID
                    tree.printInOrder();
                    break;
                    
                case 3:
                    // Prompt user to enter item ID to search
                    System.out.println("Enter item ID to search");
                    int searchId =sc.nextInt();
                    Treenode result = tree.search(searchId);  // search for item and display result
                    
                    if(result == null){
                        System.out.println("item not found");
                    } else {
                        System.out.println(result.itemID +"|"+ result.name +"|£" + result.cost);
                    }
                        break;
                    case 4:
                        // calculate and display total cost of all items
                        System.out.println("Total cost:£"+ tree.totalCost());
                        break;
                    case 5:
                        // prompt user to enter item ID to remove
                        System.out.println("enter item ID to remove:");
                        tree.deleteItem(sc.nextInt());
                        break;
                    case 6:
                        // Display preoder traversal
                        tree.printPreOrder();
                        break;
                    case 7:
                        // Display postorder traversal
                        tree.printPostOrder();
                        break;
                    case 0:
                        // Exit message
                        System.out.println("Exiting program ..");
                        break;
                    default:
                        // Handle invalid menu input
                        System.out.println("Invalid option. ");                    
                    }

                }while(choice != 0); // loop ends when user chooses 0
                
                // close the scanner to free resources
                sc.close();
            }
        }

    
    

