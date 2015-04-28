package noTitle;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class CharTest {
	public static void main(String [] arg)throws Exception{
		String rowArray;
	     Scanner inputFile = new Scanner(new FileReader("C:/Users/zhangj/Desktop/log.txt"));
	     int maze_r = inputFile.nextInt();
	     int maze_c = inputFile.nextInt();
	     int counter = 0;
	        int[][] theMaze = null;
			while(inputFile.hasNextLine()){

	         rowArray = inputFile.nextLine();
	       System.out.print("\n"+rowArray);


	          char index;
	            for(int ind = 0; ind == (maze_c-1);ind++){

	                 index = rowArray.charAt(ind);
	                 System.out.print(index);
	                 if(index == ' ')
	                     theMaze[counter][ind] = 0;
	                 if(index == '+')
	                     theMaze[counter][ind] = 1;
	                 if(index == 'M')
	                     theMaze[counter][ind] = 2;
	                  if(index == 'X')
	                     theMaze[counter][ind] = 3;

	       }
	        }
	        System.out.print(Arrays.toString(theMaze));

	}
}
