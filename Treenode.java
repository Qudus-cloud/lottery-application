public class Treenode {
    int itemID;                // Stores the unique Item ID
    String name ;               //store the name o fthe shop item
    double cost ;               //store the cost of the shop item
    Treenode left;               // Reference to the left node
    Treenode right;              // Refrence to the right node 


    Treenode(int itemID, String name, double cost) {         // Constructor to create a Treenode
        this.itemID = itemID;    // Assigns the item ID to the node
        this.name = name;        // Assigns the item name to the node
        this.cost= cost;         // Assigns the item cost to the node
        this.left = null;    // Set next as null (no next node yet)
        this.right =null;     // Set next as null
    }
}
    

