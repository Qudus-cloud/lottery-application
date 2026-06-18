public class RPNCalculator {
    //method to evaluate an RPN expression
    int evaluate (String expression) {
        Stack_LinkedList sc = new Stack_LinkedList(); //create a object sc
        String[] tokens =expression.split(","); //split the expression with commas

        //loop through each token
        for( String token : tokens){
            token =token.trim(); //Romove extra spaces

            //check if token is a number 
            if (token.matches("-?\\d+")){
                int number =Integer.parseInt(token); //convert strings to integer
                sc.push(number);//push number onto sc
            }

        //otherwise, token should be an operator
        else if (token.equals("+") || token. equals("-") 
            || token.equals("*") || token.equals("/")){

            //check if there are enough operands
            // if empty , there are not enough operands to perform the operation
            if (sc.isEmpty()){
                System.out.println("Error:");
                return -1;
            }
            // pop the second operand from the stack
            int operand2 =sc.pop();

            // check if the sc is empty before popping the first operand
            if(sc.isEmpty()){
                System.out.println("Error");
                return -1;
            }
            //pop the first operand from the sc
            int operand1 = sc.pop();
            int result = 0; //varible to store the result of the operation

            //perform operation
            switch (token){
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 -operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;            
            }
            // push result back onto sc
            sc.push(result);
        }
        // invalid token
        else{
            System.out.println("Invalid token:" + token);
            return -1;
        }
    }
    // Final result should be the only value in sc
    int finalResult = sc.pop();
    
    if(!sc.isEmpty()) {
        System.out.println("Error :invalid RPN expression");
        return -1;
    }
    // return the result of the RPN expression
    return finalResult;
    }
}
