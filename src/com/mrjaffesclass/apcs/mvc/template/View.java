package com.mrjaffesclass.apcs.mvc.template;
import com.mrjaffesclass.apcs.messenger.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * MVC Template
 * This is a template of an MVC framework used by APCS for the 
 * LandMine project (and others)
 * @author Roger Jaffe
 * @version 1.0
 * 
 */
public class View extends javax.swing.JFrame implements MessageHandler {

  private final Messenger mvcMessaging;
  //pButtonarray is an array of buttons which are the game board.
  private JButton pButtonarray[][] ;
  
  
  /**
   * Creates a new view
   * @param messages mvcMessaging object
   */
  public View(Messenger messages) {
    mvcMessaging = messages;   // Save the calling controller instance
    initComponents();           // Create and init the GUI components
  }
  
  /**
   * Initialize the model here and subscribe
   * to any required messages
   */
  public void init() {
    // Subscribe to messages here
    mvcMessaging.subscribe("model:gridSizeChanged", this);
    mvcMessaging.subscribe("model:numMinesChanged", this);
    mvcMessaging.subscribe("model:StartGame", this);
    mvcMessaging.subscribe("model:hitASafeSpot", this);
    mvcMessaging.subscribe("model:hitABomb", this);
   
       
  }
  
  @Override//this is how the messaging to the controller works 
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by view: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by view: "+messageName+" | No data sent");
    }
    if (messageName.equals("model:StartGame")) {
        StartGame(Integer.parseInt(jLabel8.getText()));
    }
    else if (messageName.equals("model:gridSizeChanged")) {
      jLabel8.setText(messagePayload.toString());
      //jButton1.setText("xyz");
      
    }
    else if (messageName.equals("model:numMinesChanged")) {
      jLabel10.setText(messagePayload.toString());
      //jButton1.setText("xyz");
      
    }
    else if (messageName.equals("model:hitABomb")) {
        hitABomb(((MessagePayload)messagePayload).getField(),((MessagePayload)messagePayload).getDirection());
    }
    else if (messageName.equals("model:hitASafeSpot")) {
        hitASafeSpot(((MessagePayload)messagePayload).getField(),((MessagePayload)messagePayload).getDirection());
    }
    else
    {
      //jLabel10.setText(messagePayload.toString());      
    }
  }

  private void StartGame(int gameSize){
    Container contentPane;
    JPanel infoPane;
      
    pButtonarray = new JButton [gameSize] [gameSize];
    contentPane = getContentPane ();
    //if (infoPane != null)
    //{
    //    contentPane.remove (infoPane);
   // }
        //infoPane = new JPanel ();
        //infoPane.setLayout(new java.awt.BorderLayout());
        infoPane = jPanel1;
        jPanel1.removeAll();
        //infoPane.setLayout (null); //setting layout to null so i can use absolute positioning with coordinates

        //jPanel1.setLayout (null); //setting layout to null so i can use absolute positioning with coordinates
        //Button grid 
        int ppositionx = 25, ppositiony = 0;
        
        for (int row = 0 ; row < gameSize ; row++)
        {
            ppositiony = ppositiony + 23;
            ppositionx = 25;
            for (int x = 0 ; x < gameSize ; x++)
            {
                pButtonarray [x] [row] = new JButton ();
                pButtonarray [x] [row].setOpaque (false);
                pButtonarray [x] [row].setBounds (ppositionx, ppositiony, 25, 25);
                pButtonarray [x] [row].setPreferredSize(new Dimension(25, 25));
                //pButtonarray [x] [row].addActionListener (this);
                
                pButtonarray [x] [row].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                       gameButtonActionPerformed(evt);
                    }
                });

                pButtonarray [x] [row].putClientProperty ("px", new Integer (x));
                pButtonarray [x] [row].putClientProperty ("prow", new Integer (row));
                infoPane.add (pButtonarray [x] [row]);
                //contentPane.add (infoPane);
                ppositionx = ppositionx + 23;
            }
        }

//        pButtonarray [gameSize-1] [gameSize-1] = new JButton ();
//        pButtonarray [gameSize-1] [gameSize-1].setOpaque (false);
//        pButtonarray [gameSize-1] [gameSize-1].setBounds (ppositionx, ppositiony, 25, 25);
//        //pButtonarray [x] [row].addActionListener (this);
//
//        pButtonarray [gameSize-1] [gameSize-1].addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//               gameButtonActionPerformed(evt);
//            }
//        });
//
//        pButtonarray [gameSize-1] [gameSize-1].putClientProperty ("px", new Integer (gameSize-1));
//        pButtonarray [gameSize-1] [gameSize-1].putClientProperty ("prow", new Integer (gameSize-1));
//        infoPane.add (pButtonarray [gameSize-1] [gameSize-1]);
//
        infoPane.add(new JLabel());  // This was a test to get last button to show up but it fixed the problem.  Hmmm.
        contentPane.add (infoPane);
        validate ();
    
  }
  
  private void hitABomb(int x, int y) {
    ImageIcon ii = new ImageIcon("src\\com\\mrjaffesclass\\apcs\\mvc\\template\\explo.jpg");
    pButtonarray[x][y].setIcon(ii);
  }

  private void hitASafeSpot(int x, int y) {
    ImageIcon ii = new ImageIcon("src\\com\\mrjaffesclass\\apcs\\mvc\\template\\house.jpg");
    pButtonarray[x][y].setIcon(ii);
  }

  
  /**
   * Instantiate an object with the field number that was clicked (1 or 2) and
   * the direction that the number should go (up or down)
   * @param fieldNumber 1 or 2 for the field being modified
   * @param direction this.UP (1) or this.DOWN (-1), constants defined above
   * @return the HashMap payload to be sent with the message
   */
  private MessagePayload createPayload(int fieldNumber, int direction) {
    MessagePayload payload = new MessagePayload(fieldNumber, direction);
    return payload;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startButton.setText("Start New Game");
        startButton.setToolTipText("Click me to see the View send a message to the Controller to let it know that the button was pushed");
        startButton.setActionCommand("button1Clicked");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonbuttonClickActionPerformed(evt);
            }
        });

        jLabel1.setText("Land Mine!");

        jLabel7.setText("Game size");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("jLabel8");

        jLabel9.setText("#Mines");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("jLabel10");

        jLabel11.setText("Game Settings");

        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(startButton))
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(startButton))
                        .addGap(15, 15, 15)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void startButtonbuttonClickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonbuttonClickActionPerformed
    // Send a message to the controller!
    mvcMessaging.notify("view:buttonClick", null, true);
    mvcMessaging.notify("view:newGameClicked", null, true);
  }//GEN-LAST:event_startButtonbuttonClickActionPerformed

  private void gameButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                         
    // Send a message to the controller!
    JButton btn = (JButton) evt.getSource ();
    
    //getting coordinates
    int pCol = ((Integer) btn.getClientProperty ("px")).intValue ();
    int pRow = ((Integer) btn.getClientProperty ("prow")).intValue ();
    mvcMessaging.notify("view:gameButtonClick", createPayload(pCol, pRow), true);

    //pButtonarray [px] [prow].setIcon (image);

  }                                                        

  /**
   * @param args the command line arguments
   */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
