/**
 * 
 */

/**
 * @author Michael Stripling
 *
 */
import java.util.Scanner;
import java.text.NumberFormat;
public class MortgageCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner imput=new Scanner(System.in);
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		double down_pay, interest_rate, price, term;

		//gathering user input
		System.out.println("Mortgage Calculator \n\nPlease input the following information.");

		price = NumCheck("  Purchase Price: ");
		down_pay = NumCheck("  Percent of down payment: ");
		interest_rate = NumCheck("  Interest rate: ");
		term = NumCheck("  Term: ");
		
		
		//calculations and output
		double MonthPay = MonthlyPayment(price,down_pay,interest_rate,term);
		
		System.out.println("Monthly mortgage payment: "+currency.format(MonthPay));
		System.out.println("Total Amount Paid:        "+currency.format(Total_A_Paid(MonthPay,term,price,down_pay)));
		System.out.println("Total Interest Paid:      "+currency.format(Total_I_Paid(price, MonthPay, term, down_pay)));
	}
	
	//Monthly Payment Calculator
	public static double MonthlyPayment(double price, double down_pay, double interest_rate, double term){
		double p=price-Down_pay(price,down_pay);
		double i=interest_rate/(12*100);		
		double value=p*(i/(1-Math.pow(1+i, -(12*term))));
		return value;
	}
	
	//Total amount paid
	public static double Total_A_Paid(double Month_pay, double term, double price, double down_pay){
		return ((12*term) * Month_pay)+ Down_pay(price,down_pay);
	}
	
	//Total interest paid
	public static double Total_I_Paid(double price, double Month_pay, double term,double down_pay){
		return Total_A_Paid(Month_pay,term,price,down_pay)-price;
	}
	
	//Calculates amount financed
	public static double Down_pay(double price, double down_pay){
		return (down_pay/100) * price;
	}
	
	//Checks user input to make sure its a number and returns it
	public static double NumCheck(String text){
		boolean b=true;
		double value=0;
		Scanner imput=new Scanner(System.in);
		
		while(b){
			System.out.print(text);
			String u=imput.next();
				try {
					value= Double.parseDouble(u);
					b=false;
				}
				catch(NumberFormatException nFE) {
				    System.out.println("Please only input numbers!");
				}
		}
		return value;
	}

}
