import java.util.Scanner;

public class InvoiceApp
{
    public static void main(String[] args)
    {
        // welcome the user to the program
        System.out.println("Welcome to the Invoice Total Calculator");
        System.out.println();  // print a blank line

		// create a Scanner object named sc
		Scanner sc = new Scanner(System.in);

        // this is the beginning to continuing calculations until told to stop
		// I left this as "y"  because it just needed to be some arbitrary value that's not "n"
        String choice = "y";
        // these variables are here to produce session totals for the number of invoices, 
        // the average total for invoices, and the average discount applied; they get called at the end of the session
        int invoices = 0;
        double avgTotal = 0;
        double avgDiscount = 0;
        
        while (!choice.equalsIgnoreCase("n"))
        {
		    // get the invoice subtotal from the user
            System.out.print("Enter subtotal:   ");
        	double subtotal = sc.nextDouble();

        	// calculate the discount amount and total
        	double discountPercent= 0.0;
        	if (subtotal >= 500)
        		discountPercent = .25;
        	else if (subtotal >= 200)
        		discountPercent = .2;
        	else if (subtotal >= 100)
        		discountPercent = .1;
        	else
        		discountPercent = 0.0;
        	double discountAmount = subtotal * discountPercent;
        	double total = subtotal - discountAmount;

        	//counts the number of invoices
        	invoices = invoices + 1;
        	//keeps a running aggregate of invoice totals to be divided appropriate at session end
        	avgTotal = avgTotal + total;
        	//more or less the same as above
        	avgDiscount = avgDiscount + discountPercent;
        	
            // display the discount amount and total
        	String message = "Discount percent: " + (discountPercent * 100) + " %" + "\n" //This is extraneous, but it really irked me to have it displaying a fraction here.
        	               + "Discount amount:  " + discountAmount + "\n"
                           + "Invoice total:    " + total + "\n";
        	System.out.println(message);

        	// see if the user wants to continue
        	System.out.print("Continue? (y/n): ");
        	choice = sc.next();
        	System.out.println();
		}
        
        // displays user session data
        String endmessage = "Invoices processed: " + invoices + "\n"
        				  + "Average invoice total: " + (avgTotal / invoices) + "\n" 
        				  + "Average discount " + ((avgDiscount / invoices) * 100) + "%" + "\n";
        System.out.print(endmessage);
    }
}