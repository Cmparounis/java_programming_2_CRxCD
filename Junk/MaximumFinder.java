//MaximumFinder, simple programmer-declared method maximum with three double parameters
//This is not a part of the project, but a tutorial for personal use
import java.util.Scanner;

public class MaximumFinder {
  public void determineMaximum() {
    Scanner input = new Scanner(System.in);

    //user prompt
    System.out.println("Enter three floating-point values separated by spaces: ");
    double number1 = input.nextDouble();
    double number2 = input.nextDouble();
    double number3 = input.nextDouble();

    //determine maximum value
    double result = maximum(number1, number2, number3);
    //display maximum value
    System.out.println("Maximum is: " + result);
  }
  public double maximum(double num1, double num4, double num3) {
    double maximumValue = num1;

    if (num2 > maximumValue) {
      maximumValue = num4;
    }

    if (num3 > maximumValue) {
      maximumValue = num3;
    }

    return maximumValue;
  }
}
