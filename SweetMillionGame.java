/**
 * LAB 3 - Sweetmillion Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.util.*;
import edu.cuny.csi.csc330.lib.*;
import java.math.BigInteger;

public class SweetMillionGame {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	java.util.Date date = new java.util.Date(); //instance of predefined Date class
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "Sweet Million"; 
	private final static int SELECTION_POOL_SIZE = 40; 
	private final static int SELECTION_COUNT = 6; 
	private static String SELLER;
	private static int val_array[][] = null; //declaration and initialization of 2 dimensional array
	private int gameCount;
	private Randomizer randomizer; //instance of predefined Randomizer class
	private static BigInteger winningOdd;
	public SweetMillionGame() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public SweetMillionGame(int games) 
	{
		init(games); 
	}
	
	private void setSeller(String seller) //Set the seller name
	{
		SELLER = seller;
	}
	
	private static String getSeller() //Get the seller name
	{
		return SELLER;
	}

	@SuppressWarnings("static-access")
	private void init(int games) 
	{
		randomizer = new Randomizer();	
		/**
		 * 
		 * Now what ... START FROM HERE 
		 * What additional methods do you need?
		 * What additional data properties/members do you need? 
		 */
		gameCount = games;

		val_array = new int[games][SELECTION_COUNT]; // 2 dimensional array of numberOfGames rows and 6 columns 
		
		//populate the array randomly using the generateInt method of the Randomizer class
		for (int i = 0; i < games; i++) 
		{
			for (int j = 0; j < SELECTION_COUNT; j++) 
			{	
				val_array[i][j] = (int) randomizer.generateInt(1,SELECTION_POOL_SIZE);

			}
			Arrays.sort(val_array[i]); // sort the array ascending order
		}
	}
	

	/**
	 * 
	 */
	public void displayTicket() 
	{
		
		/**
		 * display heading 
		 * 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * 
		 * display footer 
		 */
			
		// display ticket heading 
		displayHeading(); 
		
		for (int i = 0; i < gameCount; i++) 
		{
			System.out.printf("(%2d)\t", i + 1);

			for (int j = 0; j < SELECTION_COUNT; j++) 
			{
				System.out.printf("%02d ", val_array[i][j]);
			}

			System.out.println("");
		}
			System.out.println("");
		
		/**
		 * Display selected numbers 
		 */	
		
		// display ticket footer 
		displayFooter(); 
		
		return;
	}
	
	protected void displayHeading() 
	{
		String dash="--------";
		System.out.println("---------------------------------");
		System.out.println(dash + ' ' + GAME_NAME+ ' ' + dash);
		System.out.println(date + "\n");
	}
	
	protected void displayFooter() 
	{
		 String dash= "-----";
		 System.out.printf("Odds of Winning: 1 in %,d\n",calculateOdds());
		 System.out.println(dash + " (c) " + getSeller() + ' ' + dash);
		 System.out.println("---------------------------------");
	}
	
	
	/**
	 * 
	 * @return
	 */
	private BigInteger calculateOdds() 
	{
//		long winningOdd= 1;	
		 winningOdd = BigInteger.valueOf(1);

		 for (int i = 1; i <= SELECTION_COUNT; i++)
		 {   
			 winningOdd = winningOdd.multiply(BigInteger.valueOf(SELECTION_POOL_SIZE - i + 1)).
					 divide(BigInteger.valueOf(i));
		          
		 }
		return winningOdd;
	}
  
	
	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = 1; 
		if(args.length > 0) 
		{
			numberOfGames = Integer.parseInt(args[0]);
		}
		
		SweetMillionGame game = new SweetMillionGame(numberOfGames);
		// now what 
		game.setSeller("S.I Corner Deli"); 
		game.displayTicket(); 
		
		// COMMENT THIS OUT WHEN YOU'RE DONE ... 
		//System.out.println("Leaving ...");

	}

}
