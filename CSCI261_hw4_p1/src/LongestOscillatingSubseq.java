/**
 * LongestOscillatingSubseq.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 * 
 * Purpose	Dynamic programming approach for finding the length of the longest
 * 			subsequence of numbers whose values are oscillating
 */

import java.util.Scanner;

public class LongestOscillatingSubseq {
	
	// Attributes
	
	private int[] A;
	private int[] Spos;
	private int[] Sneg;
	
	// Constructor
	
	/**
	 * Constructor for creating an instance of LongestOscillatingSubseq,
	 * used to store useful information like the input array and the two
	 * solution arrays.
	 * 
	 * @param input	The array containing the input given by user.
	 */
	public LongestOscillatingSubseq( int[] input ) {
		this.A = input;
		this.Spos = new int[input.length];
		this.Sneg = new int[input.length];
	}//end LongestOscillatingSubseq Constructor
	
	// Methods
	
	/**
	 * Algorithm for finding the longest oscillating subsequence of numbers in
	 * an array.
	 * 
	 * @param O	Object containing information needed to solve the problem, such
	 * 			as, the input array, the 'positive' solution array, and the
	 * 			'negative' solution array.
	 */
	public void longestOscSubSeq( LongestOscillatingSubseq O ) {
		for( int i = 0 ; i < O.A.length ; i++ ) {
			O.Spos[i] = 1;
			O.Sneg[i] = 1;
			for( int j = 0 ; j < i ; j++ ) {
				if( ( O.A[i] - O.A[j] ) > 0 ) {
					O.Spos[i] = Math.max( O.Sneg[j]+1, O.Spos[i] );
				}//end if
				else if( ( O.A[i] - O.A[j] ) < 0 ) {
					O.Sneg[i] = Math.max( O.Spos[j]+1, O.Sneg[i] );
				}//end else if
			}//end for j
		}//end for i
	}//end longestOscSubSeq
	
	/**
	 * Method for retrieving the length of the longest oscillating subsequence
	 * from the solution arrays.
	 * 
	 * @param solution1	The array containing the 'positive' solution.
	 * @param solution2	The array containing the 'negative' solution.
	 * 
	 * @return			The length of the longest oscillating subsequence.
	 */
	public int findMax( int[] solution1, int[] solution2 ) {
		int max1 = -1;
		for( int value : solution1 ) {
			if( value > max1 ) {
				max1 = value;
			}//end if
		}//end for
		int max2 = -1;
		for( int value : solution2 ) {
			if( value > max2 ) {
				max2 = value;
			}//end if
		}//end for
		return Math.max( max1, max2 );
	}//end findMax

	/**
	 * The main logic for the program, Reads in the data from the user, then
	 * inputs the data into the algorithm and displays the results.
	 * 
	 * @param args	Command line arguments, unused.
	 */
	public static void main( String[] args ) {
		Scanner sc = new Scanner( System.in );
		String input = sc.next();
		int size;
		try {
			size = Integer.parseInt( input );
		} catch( NumberFormatException e ) {
			System.err.printf( "%s not an int\n", input );
			e.printStackTrace( System.err );
			System.exit( 1 );
		}//end try/catch
		size = Integer.parseInt( input );
		int[] values = new int[size];
		int value;
		for( int i = 0 ; i < size ; i++ ) {
			input = sc.next();
			try {
				value = Integer.parseInt( input );
			} catch( NumberFormatException e ) {
				System.err.printf( "%s not an int\n", input );
				e.printStackTrace( System.err );
				System.exit( 1 );
			}//end try/catch
			values[i] = Integer.parseInt( input );
		}//end for
		sc.close();
		LongestOscillatingSubseq O = new LongestOscillatingSubseq( values );
		O.longestOscSubSeq( O );
		int solution = O.findMax( O.Spos, O.Sneg );
		System.out.println(solution);
	}//end main
}//end LongestOscillatingSubseq
