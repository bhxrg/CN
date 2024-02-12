//write a java prgrm for congestion control using leaky bucket algorithm.
package com.program;
import java.util.Scanner;

public class LeakyBucket {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int incoming,outgoing,buck_size,n,time=1,store=0;
		System.out.println("Enter bucket size , outgoing rate and Number of Packets");
		buck_size= sc.nextInt(); //read bucket size
		outgoing=sc.nextInt(); //read outgoing rate
		n=sc.nextInt();// read no of packets to be simulated
		
		//simulation loop for handling incoming packets
		while(n!=0)
		{
			System.out.println("Enter the incoming packet size at time:"+(time++));
			incoming=sc.nextInt();
			//display incoming packet size
			System.out.println("Incoming packet size is : "+incoming);
			//check if incoming fits in the bucket
			if(incoming<=(buck_size - store))
			{
				//if it fits,add it to the bucket
				store+= incoming;
			}
			else
			{
				//if it doesnt fit,consider excess dropped
				int pktdrop=incoming-(buck_size-store);
				System.out.println("Dropped "+ pktdrop + " no of packets");
				System.out.println("Bucket buffer size is 10 out of "+ buck_size);
				store=buck_size;//reset the bucket size to its maximum
				
			}
			//subtract outgoing packets from the bucket
			store=store-outgoing;
			if(store<0)
			{
				store=0;
				System.out.println("Empty Buffer");
			}
			//display the buckets that are left out after outgoing
			System.out.println("After outgoing:"+store+"packets left out of"+buck_size+"in buffer\n");
			n--;
		}
		sc.close();
		
	}

}
