package downloadTimer;


import java.util.Scanner;

public class DownloadTimer2 {
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the Download Time Estimator");
		
		String choice = "y";
		
		while (!choice.equalsIgnoreCase("n"))
		{
	// Input
			System.out.print("Please enter the size of your file in MB: "); //add an option for GB
			
			double fileSize = scan.nextDouble();
		
			System.out.print("Please enter the advertised speed of your connection in MB/second: ");
		
			double connectionSpeed = scan.nextDouble();
	
			double timeRaw = (fileSize/connectionSpeed);
			
			int timeSeconds = (int) timeRaw;
	// Input
	// The Math
			long[] timeMathOut;
			timeMathOut = mathTime(timeSeconds);
	// The Math	
	// Output (Modified klt) - this started out life as my teacher's suggestion for how to handle outputting dynamically.
			StringBuffer kltOutputBuffer = new StringBuffer(); 
			kltOutputBuffer.append("Your download should finish in just about");
			String[] timeMathOutLabel = {"hour","minute","second"};
			for (int i=0; i < timeMathOut.length; i++) {
				if (timeMathOut[i] > 0) {
					kltOutputBuffer.append(" " + timeMathOut[i] + " " + timeMathOutLabel[i]);
					if (timeMathOut[i] > 1) kltOutputBuffer.append("s");
					if (timeMathOut[i] > 0 && i==1) kltOutputBuffer.append(" and");
				}
			}
			kltOutputBuffer.append(".");
			System.out.println(kltOutputBuffer);
	// Output (Modified klt)
	// Moralizing
			System.out.print("Would you like to compare your actual results to those predicted? (y/n)");
			String choiceMoralizing = scan.next();
			if (choiceMoralizing.equalsIgnoreCase("y")) {
				moralizingTime(connectionSpeed, fileSize, scan);
			}
	// Moralizing
	// Again?
			System.out.print("Would you like to start again? (y/n): ");
			choice = scan.next();
	// Again?	

		}
		scan.close();
	}	



	public static long[] mathTime (int timeSeconds) { 
		final int SECONDS_PER_HOUR = 3600;
		long hours   = timeSeconds/SECONDS_PER_HOUR;
		long minutes = (timeSeconds%SECONDS_PER_HOUR)/60;
		long seconds = (timeSeconds%SECONDS_PER_HOUR)%60;
		long[] timeMath;
		timeMath = new long[3];
		timeMath[0] = hours;
		timeMath[1] = minutes;
		timeMath[2] = seconds;
		return timeMath;
	}
	
	/* I added this so that the program felt like it had an actual purpose. Telling the user exactly what I want
	 * is an unsatisfying solution; even if it can't make sense of whatever garbage, it feels like it should be
	 * able to accept reasonable inputs as long as the required numbers are in there somewhere. Jim convinced
	 * me that this was fine as-is with an also-unsatisfying argument about 'the real world' rather than
	 * explain how to parse scanner input to me.
	 */
	
	public static void moralizingTime (double connectionSpeed, double fileSize, Scanner scan) {
		int reconstitutedTime[];
		reconstitutedTime = new int[3];
		System.out.println("How long did your download take? One number for each value, please.");
		System.out.println("For example, 2 hours 35 mintues 2 seconds would be '2 35 2'."); // add robustness against stupid inputs
		reconstitutedTime[0] = scan.nextInt();
		reconstitutedTime[1] = scan.nextInt();
		reconstitutedTime[2] = scan.nextInt();
		int reconstitutedTimeOut = (reconstitutedTime[0] * 3600) +
				(reconstitutedTime[1] * 60) + reconstitutedTime[2];
		double reconstitutedConnectionSpeed = fileSize/reconstitutedTimeOut;
		if (reconstitutedConnectionSpeed >= connectionSpeed) { //actually, this might be a candidate for a string buffer
			System.out.print("At ");
			System.out.printf("%.3f", reconstitutedConnectionSpeed); // is there some way of doing this in one line rather than three?
			System.out.println(" MB/s, you're getting what you paid for or more.");
		}
		else { 
			System.out.print("At ");
			System.out.printf("%.3f", reconstitutedConnectionSpeed);
			System.out.println("MB/s, you're not getting the bandwidth that you paid for.");
		}

	}
}