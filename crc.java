package Javaprgm;
import java.util.Scanner;

public class crc {

	static String datastream;
	static String generator="10011";

	public static void main (String[] args) {
		Scanner sc= new Scanner(System.in);
		
		// At the Sender
		System.out.println("--At the Sender--\n Enter data stream:");
		String datastream=sc.nextLine(); // Accept input data stream from the user
		int datalen=datastream.length(); // Calculate the length of the data stream
		int genlen=generator.length(); // Calculate the length of the generator polynomial
		
		int data[]=new int[datalen + genlen - 1]; // Create an array to store the data bits
		int codeword[]=new int[datalen + genlen - 1]; // Create an array to store the final codeword
		int div[]=new int[generator.length()]; // Create an array to store the divisor polynomial
		
		// Convert the input data stream and generator polynomial from String to integer arrays
		for(int i=0;i<datastream.length();i++)
			data[i]=Integer.parseInt(datastream.charAt(i)+"");
		for(int i=0;i<generator.length();i++)
			div[i]=Integer.parseInt(generator.charAt(i)+"");
		
		// Calculate CRC and get the final codeword
		codeword = calculateCrc(data, div, datalen);
		
		System.out.println("The CRC (Final Codeword) code is:");
		// Display the final codeword
		for(int i=0;i<datastream.length();i++)
			codeword[i]=Integer.parseInt(datastream.charAt(i)+"");
		for(int i=0;i<data.length;i++)
			System.out.println(codeword[i]);
		
		System.out.println("\n");
		
		// At the receiver
		System.out.println("--At the receiver---\n Enter Received codeword:");
		datastream = sc.nextLine(); // Accept received codeword from the user
		data = new int[datastream.length() + generator.length() - 1]; // Resize data array
		
		// Convert the received codeword from String to integer array
		for(int i=0;i<datastream.length();i++)
			data[i]=Integer.parseInt(datastream.charAt(i)+"");
		
		// Recalculate CRC for received codeword
		codeword= calculateCrc(data , div, datalen);
		
		boolean valid = true;
		// Check if any remainder exists, indicating error in transmission
		for(int i=0;i<codeword.length;i++)
			if(codeword[i]==1) {
				valid = false;
				break;
			}
		
		// Display result based on validity of received data
		if(valid)
			System.out.println("Data stream is valid. No error occurred");	
		else
			System.out.println("Data stream is not valid. Error occurred");
		
		sc.close(); // Close scanner
	}

	// Method to calculate CRC
	public static int[] calculateCrc(int[] divrem, int[] divisor, int len) {
		for(int i=0;i<len;i++) {
			if(divrem[i]==1)
				for(int j=0;j<divisor.length;j++)
                   divrem[i+j] ^= divisor[j]; // Perform XOR operation to get remainder
		}
		
		// Display intermediate remainder
		for(int i=0;i<divisor.length;i++)
			System.out.println(divrem[i]);
		
		return divrem; // Return final remainder
	}
}
