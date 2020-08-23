package codes;
import java.io.File;
import java.util.Scanner;

public class Main{

	private static Cell[][] board = new Cell[9][9];
	  public static void solve(int x, int y, int number)
	  {
	    board[x][y].setNumber(number);
	    //turning off the potential number for each cell in the column
	    for(int i = 0; i < 9; i++)
	    {
	      if(i != x)
	        board[i][y].turnOffPotential(number);
	    }
	    //turn off the potential number for each cell in the row
	    for(int i = 0; i < 9; i++)
	    {
	      if(i != y)
	        board[x][i].turnOffPotential(number);
	    }
	    //turn off the potential number for all items in the box.
	    for(int i = 0; i < 9; i++)
	    {
	      for(int j = 0; j < 9; j++)
	      {
	        if(board[i][j].getBoxID() == board[x][y].getBoxID() && (i!=x && j !=y))
	          board[i][j].turnOffPotential(number);
	      }
	    }
	  }
	  public static void display()
	  {
	    //loop through whole board and display the data
	    for(int x = 0; x < 9; x++)
	    {
	      for(int y = 0; y < 9; y++)
	      {
	        if(y == 3 || y == 6)
	          System.out.print("| ");
	        System.out.print(board[x][y].getNumber() + " ");
	      }
	      System.out.println();
	      if(x == 2 || x == 5)
	        System.out.println("---------------------");
	    }
	  }
	  public static void displayPotentials()
	  {
	    //loop through whole board and display potentails for each cell
	    for(int x = 0; x < 9; x++)
	    {
	      for(int y = 0; y < 9; y++)
	      {
	        if(y == 3 || y == 6)
	          System.out.print("| ");
	        board[x][y].showPotential();
	      }
	      System.out.println();
	      if(x == 2 || x == 5)
	        System.out.println("---------------------");
	    }
	  }
	  public static void setBoxIDs()
	  {
	    //set the box id
	    for(int x = 0; x < 9; x++)
	    {
	      for(int y = 0; y < 9; y++)
	      {
	        board[x][y].setBoxID( (x/3)*3 + y/3  +1 );
	      }
	    }
	  }
	  public static void loadPuzzle(String filename) throws Exception
	  {
	    //file stream data
	    File infile = new File(filename);
	    Scanner input = new Scanner(infile);
	    for(int x = 0; x < 9; x++)
	      for(int y = 0; y < 9; y++)
	      {
	        int data = input.nextInt();
	        if(data != 0)
	         solve(x, y, data );
	      }
	  }
	  
	  public static int logic4r()
	  {
	    int changes = 0;
	    for(int x = 0; x < 9; x++)
	    {
	    	//cycling through the columns in row x
	      for(int y = 0; y < 9; y++)
	      {
	    	  //found a cell with 2 potentials (2P)
	        if(board[x][y].numberOfPotentials() == 2)
	        {
	        	//looking in the rest of the row
	          for(int j = y+1; j < 9; j++)
	          {
	        	  //found another 2P cell!
	            if(board[x][j].numberOfPotentials() == 2)
	            {
	            	//if they have the same two potentials
	              if(board[x][y].getFirstPotential() == board[x][j].getFirstPotential() && board[x][y].getSecondPotential() == board[x][j].getSecondPotential())
	              {
	                //System.out.println("4r-");
	            	  //turn off the potentials for the rest of the row
	            	  //BUT!! not these two cells
	                for(int q = 0; q < 9; q++)//cycling through the row, starting at the beginning
	                {
	                  if(q == y || q == j)//if we are at one of the two cells
	                  {}//do nothing
	                  else
	                  {
	                    //turn off potential
	                    if(board[x][q].canBe(board[x][y].getFirstPotential()))
	                    {
	                      board[x][q].turnOffPotential(board[x][y].getFirstPotential());
	                      changes++;
	                    }
	                    if(board[x][q].canBe(board[x][y].getSecondPotential()))
	                    {
	                      board[x][q].turnOffPotential(board[x][y].getSecondPotential());
	                      changes++;
	                    }
	                  }
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	    return changes;
	  }
	  
	  public static int logic4c()
	  {
	    int changes = 0;
	    for(int y = 0; y < 9; y++)
	    {
	    	//cycling through the columns in row x
	      for(int x = 0; x < 9; x++)
	      {
	    	  //found a cell with 2 potentials (2P)
	        if(board[x][y].numberOfPotentials() == 2)
	        {
	        	//looking in the rest of the row
	          for(int j = y+1; j < 9; j++)
	          {
	        	  //found another 2P cell!
	            if(board[x][j].numberOfPotentials() == 2)
	            {
	            	//if they have the same two potentials
	              if(board[x][y].getFirstPotential() == board[x][j].getFirstPotential() && board[x][y].getSecondPotential() == board[x][j].getSecondPotential())
	              {
	            	  //System.out.println("4c-");
	            	  //turn off the potentials for the rest of the row
	            	  //BUT!! not these two cells
	                for(int q = 0; q < 9; q++)//cycling through the row, starting at the beginning
	                {
	                  if(q == y || q == j)//if we are at one of the two cells
	                  {}//do nothing
	                  else
	                  {
	                    //turn off potentials
	                    if(board[x][q].canBe(board[x][y].getFirstPotential()))
	                    {
	                      board[x][q].turnOffPotential(board[x][y].getFirstPotential());
	                      changes++;
	                    }
	                    if(board[x][q].canBe(board[x][y].getSecondPotential()))
	                    {
	                      board[x][q].turnOffPotential(board[x][y].getSecondPotential());
	                      changes++;
	                    }
	                  }
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	    return changes;
	  }
	  
	  
	  public static int logic4b()
	  {
	    int changes = 0;
	    int startX = 0;
	    int startY = 0;     
	    
	    
	    //this for loop dictates the box id
	    for(int boxID = 0; boxID < 9; boxID++)
	    {     
	      //set the start position of the x and y values according to the box id
	      
	      if(boxID == 0 || boxID == 3 || boxID == 6)
	        startX = 0;
	      else if(boxID == 1 || boxID == 4 || boxID == 7)
	        startX = 3;
	      else if(boxID == 2 || boxID == 5 || boxID == 8)
	        startX = 6;
	      
	      
	      if(boxID == 0 || boxID == 1 || boxID == 2)
	        startY = 0;
	      else if(boxID == 3 || boxID == 4 || boxID == 5)
	        startY = 3;
	      else if(boxID == 6 || boxID == 7 || boxID == 8)
	        startY = 6;
	      
	      
	      //look through the box for a cell with 2 potentials
	      for(int x = startX ; x < startX + 3; x++)
	      {
	        for(int y = startY; y < startY + 3; y++)
	        {
	          //found a cell with 2 potentials (2P)
	          if(board[x][y].numberOfPotentials() == 2)
	          {
	            //indicate that the cell with 2P has been found
	            board[x][y].setVisitedCell(true);
	            
	            //looking in the box again
	            for(int i = startX; i < startX + 3; i++)
	            {
	              for(int j = startY; j < startY + 3; j++)
	              {
	                //found another 2P cell!
	                if(board[i][j].numberOfPotentials() == 2 && board[i][j].getVisitedCell() == false)       
	                {
	                  //indicate that the other cell with 2P has been found
	                  board[i][j].setVisitedCell(true);
	                   
	                 //System.out.println("4b-");
	                  
	                 //if they have the same two potentials
	                  if(board[x][y].getFirstPotential() == board[i][j].getFirstPotential() && board[x][y].getSecondPotential() == board[i][j].getSecondPotential())
	                  {
	                    //System.out.println("4b--");
	                    
	                    //turn off the potentials for the rest of the box
	                    //BUT!! not these two cells
	                    for(int q = startX ; q < startX + 3; q++)
	                    {
	                      for(int z = startY; z < startY + 3; z++)
	                      {
	                        if(board[q][z].getVisitedCell() == true)//if we are at one of the two cells that has 2 potentials
	                        {}//do nothing
	                        else
	                        {
	                          //turn off potentials
	                          if(board[q][z].canBe(board[x][y].getFirstPotential()))
	                          {
	                            //System.out.println("4b+");
	                            board[q][z].turnOffPotential(board[x][y].getFirstPotential());
	                            changes++;
	                          }
	                          if(board[q][z].canBe(board[x][y].getSecondPotential()))
	                          {
	                            //System.out.println("4b++");
	                            board[q][z].turnOffPotential(board[x][y].getSecondPotential());
	                            changes++;
	                          }
	                        }
	                        
	                      }
	                    }
	                  }
	                }
	              }
	            }
	          }  
	        }
	      }
	    }
	    
	    return changes;
	  }
	  

	  public static int onlyPotential()
	  {
	    int changes = 0;
	    
	    //look through entire sudoku
	    for(int x = 0; x < 9; x++)
	    {
	      for(int y = 0; y < 9; y++)
	      {
	        //if there is a cell that has a value of 0 and only 1 potential, then solve for the number at that cell
	        if(board[x][y].getNumber() == 0 && board[x][y].numberOfPotentials() == 1)
	        {
	          //System.out.println("Only Potential");
	          changes++;
	          solve(x,y,board[x][y].getFirstPotential());
	        }
	      }
	      
	    }
	    
	    return changes;
	    
	  }
	  
	  public static int solveRow()
	  { 
	    
	    int counter = 0;
	    int maximumSumOfRow = 45;
	    
	     
	    //these variables record the x and y positions
	    int saveX = 0;
	    int saveY = 0;
	    
	    //this the number that will be solved for
	    int number = 0;
	    
	    
	    for(int x = 0; x < 9; x++)
	    {    
	      //counter for amount of 0s and non-0s
	      int nonZeroNumbers = 0;
	      int zeroNumbers = 0;
	      //stores the sum of the column
	      int currentSumOfRow = 0;
	      
	      for(int y = 0; y < 9; y++)
	      { 
	        //if the number is not then add it to the sum and increase the non-0 counter
	        if(board[x][y].getNumber() != 0)
	        {
	          currentSumOfRow += board[x][y].getNumber();
	          nonZeroNumbers++;
	        }
	        //if the number is 0, then save the x and y coordinates of that number and increase the 0 number counter
	        if(board[x][y].getNumber() == 0)
	        {
	          saveX = x;
	          saveY = y;
	          zeroNumbers++;
	        }
	        //if there is 1 zero and 8 non-zero numbers then subtract the current sum from maximum sum of row to get the unsolved number in the row
	        if(zeroNumbers == 1 && nonZeroNumbers == 8)
	        {
	          number = maximumSumOfRow - currentSumOfRow;
	          counter++; 
	          //System.out.println("Solving Row");
	          //solve for number
	          solve(saveX,saveY,number); 
	        }
	        
	      }
	    }
	    return counter;
	  }
	  
	 
	  
	  
	  /*
	   * exact same logic as rowSolver applies, except the x and y for-loops have been switched to solve for the column
	   */ 
	  public static int solveColumn()
	  {  
	    int counter = 0;
	    
	    
	    int maximumSumOfColumn = 45;
	    
	    
	    
	    int saveX = 0;
	    int saveY = 0;
	    int number = 0;
	    for(int y = 0; y < 9; y++)
	    {  
	      int currentSumOfColumn = 0;
	      int nonZeroNumbers = 0;
	      int zeroNumbers = 0;
	      
	      for(int x = 0; x < 9; x++)
	      {
	        if(board[x][y].getNumber() != 0)
	        {
	          currentSumOfColumn += board[x][y].getNumber();
	          nonZeroNumbers++;
	        }
	        if(board[x][y].getNumber() == 0)
	        {
	          saveX = x;
	          saveY = y;
	          zeroNumbers++;
	        }
	        
	        if(zeroNumbers == 1 && nonZeroNumbers == 8)
	        {
	          number = maximumSumOfColumn - currentSumOfColumn;
	          counter++;
	          //System.out.println("Solving Column");
	          solve(saveX,saveY,number);       
	        } 
	      }
	    }
	    return counter;
	  }
	  
	  public static int solveBox()
	  {
	    int changes = 0;
	    
	    int startX = 0;
	    int startY = 0;
	   
	    int maximumBoxSum = 45;
	    
	    
	    //this for-loop dictates the box id
	    for(int boxID = 0; boxID < 9; boxID++)
	    {     
	      //set the start position of the x and y values according to the box id 
	      if(boxID == 0 || boxID == 3 || boxID == 6)
	        startX = 0;//startX;
	      else if(boxID == 1 || boxID == 4 || boxID == 7)
	        startX = 3;
	      else if(boxID == 2 || boxID == 5 || boxID == 8)
	        startX = 6;
	      
	      
	      if(boxID == 0 || boxID == 1 || boxID == 2)
	        startY = 0;//startY;
	      else if(boxID == 3 || boxID == 4 || boxID == 5)
	        startY = 3;
	      else if(boxID == 6 || boxID == 7 || boxID == 8)
	        startY = 6;
	      
	      
	      int boxSum = 0;
	      
	      //counts the number of 0 and non-0 numbers there are in the box
	      int nonZeroNumbers = 0;
	      int zeroNumbers = 0;
	      
	      //records the number that will be solved for
	      int number = 0;
	      
	      //look through the box according to the boxID
	      for(int x = startX ; x < startX + 3; x++)
	      {
	        for(int y = startY; y < startY + 3; y++)
	        {
	         
	          
	          //if the number at the cell is not 0, then add it to boxSum and increase non-0 number counter
	          if(board[x][y].getNumber() != 0)
	          {
	            boxSum += board[x][y].getNumber();
	            nonZeroNumbers++;
	          }
	          //if the number is 0 then increase the 0 number counter
	          else if(board[x][y].getNumber() == 0)
	          {
	            zeroNumbers++;
	          }
	          //if there is 1 zero and 8 non-zero numbers then subtract the current sum from maximum sum of box to get the unsolved number in the box
	          if(nonZeroNumbers == 8 && zeroNumbers == 1)
	          {
	            
	            
	            
	            number = 45 - boxSum;
	            changes++;
	            //these nested for loop look through box again to find the cell that is 0
	            for(x = startX ; x < startX + 3; x++)
	            {
	              for(y = startY; y < startY + 3; y++)
	              {
	                if(board[x][y].getNumber() == 0)
	                {
	                  //System.out.println("Solve Box");
	                  //solve
	                  solve(x,y,number);
	                }
	              }
	            }
	            
	          }
	          
	        }
	        
	      }   
	    }
	    
	    return changes; 
	  }
	  
	  
	  /*
	   * This method is used for testing purposes to see if there are any contradictions that arise due to false logic or invaild sudoku puzzle
	   */	  
	  public static boolean contradictionCheck()
	  {
	    //look through the would board and see if there are cells that have a value of 0 and no potential values
	    for(int x = 0; x < 9; x++)
	    {
	      for(int y = 0; y < 9; y++)
	      {
	        if(board[x][y].getNumber() == 0 && board[x][y].numberOfPotentials() == 0)
	        {
	          //prints coordinate of the 0
	          System.out.println(x + "," + y);
	          
	          return true;
	        }
	        
	      }
	    }
	    	    
	    return false;
	  }
	  
	  
	  
	  //solves all the logic methods from this method
	  public static int allLogic()
	  {
	      int counter = 0;
	      
	      counter += onlyPotential();
	      counter += solveBox();
	     
	      counter += logic4r();
	      counter += logic4c();
	      counter += logic4b();
	      
	      counter += solveColumn();
	      counter += solveRow();
	      
	      return counter;
	      
	    
	  }
	  
	  //This method counts the number of cells that have been solved.
	  //The puzzle is solved if this returns 81.
	  public static int cellsSolved()
	  {
		  int count = 0;
		  for(int x = 0; x < 9; x++)
			  for(int y = 0; y < 9; y++)
				  if(board[x][y].getNumber() != 0)
					  count++;

		  return count;
	  }

	  public static boolean revert(Guess guess)
	  {
		  //System.out.println("REVERTING...");
		  for(int x = 0; x<9; x++)
			  for(int y = 0; y < 9; y++)
			  {
				  //resetting the the grid
				  board[x][y].reset();

			  }

		  for(int x = 0; x<9; x++)
			  for(int y = 0; y < 9; y++)
			  {
				  //reloading the puzzle
				  if(guess.getBoardValueAt(x,y) != 0)
				  {
					  solve(x,y,guess.getBoardValueAt(x,y));
				  }
			  }

		  /*This re-establishes the board state as far as the numbers
		   * are concerned but does not further reduce any potentials
		   * that may have been affected by logic 4. Thus we need to 
		   * re-run the logic cycles until they get stuck.
		   */
		  int changes = 0;
		  do
		  {
			  changes = 0;
			  changes += allLogic();

		  }while(changes > 0);

		  /*At this point, the whole board should be as we left it at the 
		   * previous guess. However, if we generate another guess
		   * from this point, we will just generate the same guess as 
		   * before. Thus we need to turn off the potentials for that guess
		   * as this line of solving has already been explored and shown to 
		   * be faulty for every potential from the last guess down.
		   */

		  boolean potentialTurnedOff = false;
		  for(int x = 0; x<9; x++)
			  for(int y = 0; y < 9; y++)
			  {
				  if(board[x][y].getNumber() == 0 && !potentialTurnedOff)
				  {
					  //I have re-found the cell I am guessing on.
					  /*If it doesn't have a second potential then 
					   * this cell will have no further guesses possible
					   */
					  //since we already guessed this number, we need to turn
					  //off it's potential
					  potentialTurnedOff = true;
					  board[x][y].turnOffPotentialsBefore(guess.getThisGuess());


					  if(board[x][y].getFirstPotential() == -1)
					  {
						  return false;
					  }
					  else
					  {
						  return true;
					  }
				  }
			  }
		  return false;

	  }
	
	
	
	
	
	  public static void main(String[] args)throws Exception {
	    
		  Guess[] listOfGuesses = new Guess[81];
		  int numberOfGuesses = 0;  
		  boolean revertedSuccessfully = true;
		  
		//Creating each cell in the board
	    for(int x = 0; x < 9; x++)
	      for(int y = 0; y < 9; y++)
	      board[x][y] = new Cell();
	    setBoxIDs();
	    
	    //load the puzzle and display
	    //loadPuzzle("H://Rakshit Patel CS3 Semester 2//Sudoku Solver//src//codes//easy.txt");
	    //loadPuzzle("H://Rakshit Patel CS3 Semester 2//Sudoku Solver//src//codes//medium.txt");
	    loadPuzzle("H://Rakshit Patel CS3 Semester 2//Sudoku Solver//src//codes//hard.txt");
	    display();
	    
	    int counter = 0;
	    
	    
	    
	    do
	    {
	    	//loop until there are no changes being made by the logic method
	    	do
	    	{	
	    		counter = 0;  
	    		counter += allLogic();

	    		//print Contradiction Found! if the contradiction method returns true
	    		if(contradictionCheck() == true)
	    		{
	    			System.out.println("Contradiction Found!");
	    		}

	    	}while(counter > 0);



	    	//at this point, no changes were made during the logic cycles
	    	//we are checking if the puzzle is not solved and there are no contradictions
	    	//If this is the case, it means that a guess is required
	    	if(cellsSolved() < 81 && contradictionCheck() == false)// a guess is needed
	    	{
	    		boolean guessMade = false;
	    		//look for the first unsolved cell and guess the first available potential from that cell
	    		for(int x = 0; x<9; x++)
	    			for(int y = 0; y < 9; y++)
	    			{
	    				if(!guessMade && board[x][y].getNumber() == 0 && board[x][y].getFirstPotential() != -1)
	    				{
	    					display();
	    					System.out.println("Guessing:" + board[x][y].getFirstPotential() + " at " + x +"," +y);
	    					//save the board state
	    					listOfGuesses[numberOfGuesses] = new Guess(board);
	    					numberOfGuesses++;
	    					solve(x,y,board[x][y].getFirstPotential());
	    					guessMade = true;

	    				}
	    			}

	    	}
	    	//a wrong guess has been made - need to revert back to an earlier version of the program
	    	if(cellsSolved() < 81 && contradictionCheck() == true)
	    	{
	    		do 
	    		{
	    			revertedSuccessfully = revert(listOfGuesses[numberOfGuesses-1]);
	    			numberOfGuesses--;
	    		}while(revertedSuccessfully == false);
	    	}


	    }while(cellsSolved() < 81);

	     
	     
	     
	     
	    System.out.println();
	    System.out.println("___________________________");
	    System.out.println();
	    System.out.println("Solution: ");
	    System.out.println();
	    display();
	  }
}