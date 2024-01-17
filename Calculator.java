import java.util.Scanner;

public class Calculator{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Complex Console Calculator");
        System.out.println("Enter an Expression or Type 'exit' to quit");

        while(true){
            System.out.println("Expression :");
            String expression = scanner.nextLine();

            if(expression.equalsIgnoreCase("exit")){
                System.out.println("Existing Calculator, GoodBye!");
                break;
            }
            try{
                double result = evaluateExpression(expression);
                System.out.println("Result :"+ result);
            }catch(Exception e){
                System.out.println("Error" + e.getMessage());

            }

        }
        scanner.close();


    }

    private static double evaluateExpression(String expression){

        expression = expression.replaceAll("\\s", "");
        char[] tokens = expression.toCharArray();

        return basicAddition(tokens);
    }
    private static double basicAddition(char[] tokens){
        double result = 0;
        StringBuilder currentNumber = new StringBuilder();
        char currentOperator = '+';

        for (char token : tokens){
            if(Character.isDigit(token) || token == '.') {
                currentNumber.append(token);
            }else{
                result = performOperation(result,Double.parseDouble(currentNumber.toString()), currentOperator);
                currentNumber = new StringBuilder();
                currentOperator = token;
            }
        }
        result = performOperation(result, Double.parseDouble(currentNumber.toString()), currentOperator);

        return result;
    }

    private static double performOperation(double result, double operand, char operator){

        switch (operator) {
            case '+':
            return result + operand;

            case '-':
            return result - operand;

            case '*':
            return result * operand;

            case '/':
                if(operand != 0){
                    return result / operand;
                }else{
                    throw new ArithmeticException("Division by Zero");
                }
            
        
            default:
                throw new IllegalArgumentException("Invalid Operator :" + operator);
            
        }
    }
    
}