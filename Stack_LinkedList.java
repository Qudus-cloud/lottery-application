class Stack_LinkedList {

    Node top;   // top of stack (acts like head of linked list)

    // Constructor
    Stack_LinkedList() {
        top = null;   // initially stack is empty
    }
// PUSH Operation
    void push(int value) {

        Node newNode = new Node(value);  // create new node

        newNode.next = top;              // new node points to old top

        top = newNode;                   // top now points to new node

        System.out.println(value + " pushed into stack");
    }

    //POP Operation
        int pop() {

        if (top == null) {                 // stack empty
            System.out.println("Stack Underflow");
            return -1;
        }

        int popped = top.data;             // store top value

        top = top.next;                    // move top to next node

        return popped;
    }

    //PEEK Operation

        int peek() {

        if (top == null) {
            System.out.println("Stack is empty");
            return -1;
        }

        return top.data;
    }

    // Is Empty Operation

        boolean isEmpty() {
        return top == null;
    }
}

