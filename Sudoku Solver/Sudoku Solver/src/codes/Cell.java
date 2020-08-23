	package codes;
	
	  public class Cell {
		
		  private int number;
		  private int boxID;
		  private boolean[] potential = {false,true,true,true,true,true,true,true,true,true};

		  private boolean VisitedCell;

		  //turns off potential for invalid guesses
		  public void turnOffPotentialsBefore(int number)
		  {
			  for(int x = number; x >0; x--)
				  potential[x] = false;
		  }
		  //resets board
		  public void reset()
		  {
		  		for(int x = 1; x < 10; x++)
		  			potential[x] = true;
		  		number = 0;
		  }

		  //gives the cell a number and and boxID
		  public void SudokuSolverCell()
		  {
		    number = 0;
		    boxID = 0;
		  }
		  public int getNumber() {
		    return number;
		  }
		  //used to set the number at the cell and turn potential off
		  public void setNumber(int number) {
		    this.number = number;
		   for(int x = 0; x<9; x++)
		   { 
		    if(x != number)
		    potential[x] = false;
		   }
		  }
          
		  //checks if the cell has been visited
		  public boolean getVisitedCell() {
		    return VisitedCell;
		  }
		  
		  public void setVisitedCell(boolean VisitedCell) {
		    this.VisitedCell = VisitedCell;
		  }

		  //returns boxID
		  public int getBoxID() {
		    return boxID;
		  }

		  public void setBoxID(int boxID) {
		    this.boxID = boxID;
		  }

		  //returns all of the potentials at the cell
		  public boolean[] getPotential() {
		    return potential;
		  }

		  //returns a specific potential in a cell at index z
		  public boolean getPotential(int z)
		  {
		    boolean possible = potential[z];
		    return possible;
		  }

		  
		  public void setPotential(boolean[] potential) {
		    this.potential = potential;
		  }
          
		  //turn potential off at a number
		  public void turnOffPotential(int number)
		  {
		    potential[number] = false;
		  }
		  //display all of the potentials
		  public void showPotential()
		  {
		    for(int x = 1; x < 10; x++)
		      System.out.print(x + ":" +potential[x] + " ");
		  }


		  //return the potential of the index 
		  public boolean canBe(int number)
		  {
		    if(number<10 && number > 0)
		      return potential[number];
		    else return false;
		  }

		  //return the fist potential in the cell
		  public int getFirstPotential()
		  {
		    for (int x = 1; x < 10; x++)
		      if(potential[x] == true)
		      return x;
		    return -1;
		  }

		  //return second potential in the cell
		  public int getSecondPotential()
		  {
		    boolean firstPotentialFound = false;
		    for (int x = 1; x < 10; x++)
		    {
		      if(potential[x] == true && firstPotentialFound == false)
		      {
		        firstPotentialFound = true;
		      }
		      else if(potential[x] == true && firstPotentialFound == true)
		      {
		        return x;
		      }
		    }
		    return -1;
		  }

		  //counts the amount of potentials in the cell
		  public int numberOfPotentials()
		  {
		    int count = 0;
		    //if a potential is found then increase the counter
		    for(int x = 1; x < 10; x++)
		      if(potential[x] == true)
		      count++;
		    return count;
		  }
	
	  }