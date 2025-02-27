
import java.util.Scanner;

public class Sinc_calc {
   public Sinc_calc() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);

      while(true) {
         while(true) {
            System.out.println("\nScientific Calculator - Choose an operation:");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (x!)");
            System.out.println("3. Natural Logarithm (ln(x))");
            System.out.println("4. Power Function (x^b)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int var2 = var1.nextInt();
            switch (var2) {
               case 1:
                  System.out.print("Enter a number (x): ");
                  double var3 = var1.nextDouble();
                  if (var3 < 0.0) {
                     System.out.println("Error: Square root of a negative number is not real.");
                  } else {
                     System.out.println("Result: √" + var3 + " = " + Math.sqrt(var3));
                  }
                  break;
               case 2:
                  System.out.print("Enter a number (x): ");
                  int var5 = var1.nextInt();
                  if (var5 < 0) {
                     System.out.println("Error: Factorial of a negative number is not defined.");
                  } else {
                     System.out.println("Result: " + var5 + "! = " + factorial(var5));
                  }
                  break;
               case 3:
                  System.out.print("Enter a number (x): ");
                  double var6 = var1.nextDouble();
                  if (var6 <= 0.0) {
                     System.out.println("Error: Logarithm is not defined for zero or negative values.");
                  } else {
                     System.out.println("Result: ln(" + var6 + ") = " + Math.log(var6));
                  }
                  break;
               case 4:
                  System.out.print("Enter base (x): ");
                  double var8 = var1.nextDouble();
                  System.out.print("Enter exponent (b): ");
                  double var10 = var1.nextDouble();
                  System.out.println("Result: " + var8 + "^" + var10 + " = " + Math.pow(var8, var10));
                  break;
               case 5:
                  System.out.println("Exiting the calculator. Goodbye!");
                  var1.close();
                  System.exit(0);
               default:
                  System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
         }
      }
   }

   public static long factorial(int var0) {
      long var1 = 1L;

      for(int var3 = 1; var3 <= var0; ++var3) {
         var1 *= (long)var3;
      }

      return var1;
   }
}
