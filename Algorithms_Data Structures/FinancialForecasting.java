import java.util.Scanner;

public class FinancialForecasting {

    public static double calculateFutureValue(double presentValue, double growthRate,  int years){
        if(years==0){
            return presentValue;
        }

        return (1 + growthRate) * calculateFutureValue(presentValue, growthRate, years-1);
    }
    public static void  main(String args[]){
        System.out.println("--- Financial Forecasting Tool ---");

        double startingAmount = 1000.00;
        double growthRate = 0.05;
        int years = 10;
        double futureValue = calculateFutureValue(startingAmount, growthRate, years);
        System.out.printf("Projected value after %d years: $%.2f\n", years, futureValue);
    }
}
