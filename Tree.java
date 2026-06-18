public class Tree {
    private Treenode root;
    public Tree(){
        root =null; //Tree start empty
    }

                // ADD ITEM 
    public void addItem(int id, String name, double cost){   //public method to add a new item to the tree
        root = addRecursive(root, id ,name,cost); // call the tecursive method and updates the root
    }
    // Recursive method that inserts a new node in the correect position based on itemID
    private Treenode addRecursive(Treenode current, int id ,String name, double cost){
        if (current == null){ // if currnet position is empty
            return new Treenode(id,name,cost); // create a new node here
        }
        else if (id< current.itemID){ //if itemID is smaller
            current.left = addRecursive(current.left ,id, name ,cost); // go to the left subtree       
        }
        else if (id> current.itemID){ //if itemID is larger
            current.right = addRecursive(current.right ,id, name ,cost); // go to the right subtree  
        }
        else {   // if itemID already exists
            System.out.println("item ID already exists");  // do not insert
        }     
        return current; // Return the unchanged node pointer
    }

                 // IN-ORDER PRINT
        // print the tree in ascending order of item ID
        public void printInOrder(){  
            if(root == null){    //check if tree is empty
                System.out.println("Tree is empty");
             }else{
                printInOrderRecursive(root); //Start recursive in-order traversal
             }
        } 
        // Recursive in-order traversal: Left -> Root -> Right
        private void printInOrderRecursive(Treenode node){
            if(node != null){
                printInOrderRecursive(node.left); //visit left sebtree
                System.out.println(node.itemID +"|"+ node.name + "|£" +node.cost); //print current node data
                printInOrderRecursive(node.right); //visit right subtree
        }
    }

                // PREORDER
    // prints the tree using preorder traversal.
    public void printPreOrder() {
        printPreOrderRecursive(root);
    }   
    // Recursive preorder traversal: Root -> left -> Right 
    private void printPreOrderRecursive(Treenode node){
        if(node != null){
            System.out.println(node.itemID + "|" +node.name +"|£"+ node.cost); // print current node first
            printPreOrderRecursive(node.left); // Traverse left subtree
            printPreOrderRecursive(node.right); // Traverse right suntree
        }
    }  
             // POSTORDER
    // prints the tree using postorder traversal.
    public void printPostOrder() {
        printPostOrderRecursive(root);
    }   
    // Recursive postorder traversal:  left -> Right -> Root 
    private void printPostOrderRecursive(Treenode node){
        if(node != null){ 
            printPostOrderRecursive(node.left); // Traverse left subtree
            printPostOrderRecursive(node.right); // Traverse right suntree
            System.out.println(node.itemID + "|" +node.name +"|£"+ node.cost); // print current node last
        }
    }    
             // Search
     // searches for an item using its item ID.
    public Treenode search (int id){
        return searchRecursive(root, id);  //Starts recursive searvch from the root     
    }      
    private Treenode searchRecursive(Treenode node, int id){
        if(node == null || node.itemID == id){  // if tree is empty or item is found 
            return node;
        }
        if (id < node.itemID){ // if serach ID is smaller
        return searchRecursive(node.left, id); //search left subtree
        }
        return searchRecursive(node.right,id);// otherwise search right subtree
    }   
    
            //TOTAL COST
    // calculate the total cost of all items in the tree.
    public double totalCost() {
        return totalCostRecursive(root);       
    }    
    private double totalCostRecursive(Treenode node){  // Recursively adds the cost of every node.
        if(node == null){ // base case :empty subtree
            return 0;
        }
        //add current node cost+ left +right subtree cost
        return node.cost +totalCostRecursive(node.left)+ totalCostRecursive(node.right);
    }    

               // DELETE ITEM
    // Removes an item from the tree using its item ID           
    public void deleteItem(int id){
        root = deleteRecursive(root, id);
    }           
    private Treenode deleteRecursive (Treenode current, int id){
        // if item does not exist
        if (current == null) {
            System.out.println("item not found.");
            return null;    
        }
        // traverse left subtree
        if (id < current.itemID) {
            current.left = deleteRecursive(current.left, id);
        }
        // traverse right subtree
        else if (id > current.itemID) {
            current.right = deleteRecursive(current.right, id);
        }
        //node found
        else{
            //case 1 ; node has no children
            if (current.left == null && current.right ==null) {
                return null;      
            }
            // case2: Node has one child 
            if (current.left == null) {
                return current.right;    
            }
            if (current.right == null) {
                return current.left; 
        }
        // Case 3: Node has two children
        // Find smallest node in the right subtree
        Treenode smallest = findSmallest(current.right);

        //Replace current node data with smallest node data
        current.itemID = smallest.itemID;
        current.name = smallest.name;
        current.cost = smallest.cost;

        // Delete the duplicate node from right subtree
        current.right =deleteRecursive(current.right, smallest.itemID);
        }    
        return current;       
    }
private Treenode findSmallest(Treenode node){
    return(node.left == null) ? node : findSmallest(node.left);
}
}
