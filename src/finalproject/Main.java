/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.sound.midi.*;
import java.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author curtis
 */
public class Main extends JFrame {

  public static void main(String[] args)
  {
      int [] myArray; 
      int screenWidth = 1366;
      int screenHeight =780;
      int inputSize = 315;
      int inputHeight = 60;
      int textInputSize=150;
      int buttonSize=75;
      myArray = new int[8];
      
      final JFrame frame = new JFrame("");
      frame.setSize(screenWidth,screenWidth);
      frame.setLocationRelativeTo(null);
      frame.setLayout(null);
      
      JPanel topPane = new JPanel();
      topPane.setBounds(0,0,screenWidth,screenHeight);
      topPane.setLayout(null);
      topPane.setBackground(Color.white);
   
      final JLabel textLabel = new JLabel();
      textLabel.setText("Please enter your name");
      textLabel.setBounds((screenWidth-inputSize)/2,275,inputSize,inputHeight);
      Font labelFont = textLabel.getFont();
      textLabel.setFont(new Font(labelFont.getName(),Font.PLAIN, 30));
      topPane.add(textLabel);
      
      //Button Stuff
      //
      //
      JButton noteButton = new JButton();
      ImageIcon play = new ImageIcon("Play.jpg");
      Image img = play.getImage();
      Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      play = new ImageIcon(newimg);
      File imageCheck = new File("Play.jpg");
      if(imageCheck.exists())
      {
          System.out.println("Image file found");
      }
      else
      {
          System.out.println("Image file not found");
      }
      noteButton.setIcon(play);
      Border emptyBorder = BorderFactory.createEmptyBorder();
      noteButton.setBorder(emptyBorder);
      noteButton.setBounds((screenWidth-buttonSize)/2,400,buttonSize,buttonSize);
      noteButton.setBackground(Color.white);
      topPane.add(noteButton);
      
      //Input Field
      //
      //
      JTextField inputField = new JTextField(20);
      inputField.setBounds((screenWidth-textInputSize)/2,350, textInputSize,inputHeight/2);
      inputField.setFont(new Font(labelFont.getName(),Font.PLAIN, 20));
      topPane.add(inputField);
      
      
      //Finalize
      frame.getContentPane().add(topPane);
      frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      MidiHelper myMidiHelper = new MidiHelper();
      frame.getRootPane().getInputMap(
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke("ENTER"), "clickENTER");
        frame.getRootPane().getActionMap().put("clickENTER", new AbstractAction() {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                try {
                    myMidiHelper.playSomething(input);
                } catch (MidiUnavailableException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                frame.repaint();
                
            }
        });
      noteButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
            try { 
                String input = inputField.getText();
                myMidiHelper.playSomething( input);
                
                frame.repaint();
                } catch (javax.sound.midi.MidiUnavailableException exception) {
                System.out.println(exception);
                System.exit(1);
                } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
        });
  }
}
  
        

