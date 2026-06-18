class Node{                 // Defines a Node class
    int data;                // Stores the value of the node
    Node next;               // Reference to the next node

    Node(int data) {         // Constructor to create a node
        this.data = data;    // Assign value to the node
        this.next = null;    // Set next as null (no next node yet)
    }
}

