package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;
import java.util.Arrays;
import java.util.Random;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;

  // Model's data variables
  private int gridSize;
  private int numMines;
  private boolean[][] mineGrid;
  

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {  //model gets the information from the controller 
    mvcMessaging.subscribe("view:changeButton", this);
    mvcMessaging.subscribe("view:newGameClicked", this);
    mvcMessaging.subscribe("view:gameButtonClick", this);
  
    setGridSize(8);//sets grid size
    setNumMines(10); //sets the mines
    randomizeMines(getNumMines()); 
  }
  
  public void randomizeMines(int number) {
    mineGrid = new boolean[getGridSize()][getGridSize()]; //gets the grid from the view to set the landmines
    for (int i=0; i<getGridSize(); i++)
    {
        for (int j=0; j<getGridSize(); j++)
        {
            mineGrid[i][j] = false;
        }
    }
    //Arrays.fill(mineGrid,Boolean.FALSE);
    Random randomGenerator = new Random(); //places the mines randomly into the arrays
    for (int idx = 1; idx <= number; ++idx){
        int x = randomGenerator.nextInt(getGridSize());
        int y = randomGenerator.nextInt(getGridSize());
        mineGrid[x][y] = Boolean.TRUE;
    }
    mvcMessaging.notify("model:StartGame", gridSize, true);
    
  }
  
  
  
   //uses booleans to see iof there is a mine or not, if there is a mine it will come up true 
  //from the for loop above when it sets the random mines, uses booleans and calls mines "true"
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    
    if (messageName == "view:gameButtonClick") {
        MessagePayload payload = (MessagePayload)messagePayload;
        int pCol = payload.getField();
        int pRow = payload.getDirection();
        if (mineGrid[pCol][pRow] == true) {
            //hit a bomb
            mvcMessaging.notify("model:hitABomb", new MessagePayload(pCol, pRow), true);
        }
        else {
            //hit a safe spot
            mvcMessaging.notify("model:hitASafeSpot", new MessagePayload(pCol, pRow), true);
        }
    }
    else if ("view:newGameClicked".equals(messageName)) {
        randomizeMines(getNumMines());
    } 
   
     
    
    }
        
  
    
  

  /**
   * Getter function for variable 1
   * @return Value of gridSize
   */
  public int getGridSize() {
    return gridSize;
  }

  /**
   * Setter function for variable 1
   * @param v New value of gridSize
   */
  public void setGridSize(int v) {
    gridSize = v;
    // When we set a new value to variable 1 we need to also send a
    // message to let other modules know that the variable value
    // was changed
    mvcMessaging.notify("model:gridSizeChanged", gridSize, true);
  }
  
  /**
   * Getter function for variable 1
   * @return Value of numMines
   */
  public int getNumMines() {
    return numMines;
  }
  
  /**
   * Setter function for variable 2
   * @param v New value of variable 2
   */
  public void setNumMines(int v) {
    numMines = v;
    // When we set a new value to variable 2 we need to also send a
    // message to let other modules know that the variable value
    // was changed
    mvcMessaging.notify("model:numMinesChanged", numMines, true);
  }  

    private void appear(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  
      
  
  }


