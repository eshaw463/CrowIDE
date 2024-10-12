//Most up to date version likely on IntJ

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;

public class Main {
  public static JTextArea display = null;

  public static void main(String[] args) {
    JFrame frame = new JFrame("CrowIDE");
    frame.setSize(960, 540);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);


    JPanel mainpanel = new JPanel();
    mainpanel.setBackground(new Color(20, 20, 20));
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    mainpanel.setLayout(grid);

    //images
    JPanel panel = new JPanel();
    panel.setBackground(new Color(20, 20, 20));
    panel.setLayout(new FlowLayout());
    gbc.gridx = 0;
    gbc.gridy = 0;
    mainpanel.add(panel, gbc);



    JPanel middlePanel = new JPanel();
    middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

    display = new JTextArea(40, 90);
    display.setEditable(true);
    JScrollPane scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    display.setBackground(new Color(20, 20, 20));
    display.setForeground(Color.white);
    display.setCaretColor(Color.cyan);
    //display.setFont(Font.getFont(Font));



    //text
    JPanel panel2 = new JPanel();
    panel2.setBackground(new Color(20, 20, 20));
    panel2.setLayout(new FlowLayout());
    gbc.gridx = 0;
    gbc.gridy = 1;
    mainpanel.add(panel2, gbc);

    //  cd C:\\Users\\jaspe\\Documents\\IdeaProjects\\CrowIDE\\  ;
    String target = "b.cpp";

    JLabel label = new JLabel(target);
    label.setForeground(new Color(255, 255, 255));
    label.setFont(new Font("SansSerif", Font.PLAIN, 20));

    //buttons
    JPanel panel3 = new JPanel();
    panel3.setBackground(new Color(20, 20, 20));
    panel3.setLayout(new FlowLayout());
    gbc.gridx = 0;
    gbc.gridy = 2;
    mainpanel.add(panel3, gbc);

    JButton button = new JButton();
    button.setText("Save");
    button.setForeground(Color.orange);
    button.setBackground(new Color(20, 20, 20));

    JButton button2 = new JButton();
    button2.setText("Load");
    button2.setForeground(Color.cyan);
    button2.setBackground(new Color(20, 20, 20));

    JButton button3 = new JButton();
    button3.setText("Compile");
    button3.setForeground(Color.white);
    button3.setBackground(new Color(20, 20, 20));

    JButton button4 = new JButton();
    button4.setText("Run");
    button4.setForeground(Color.white);
    button4.setBackground(new Color(20, 20, 20));

    JButton button5 = new JButton();
    button5.setText("C & R");
    button5.setForeground(Color.green);
    button5.setBackground(new Color(20, 20, 20));

    //display
    panel.add(scroll);
    panel2.add(label);
    panel3.add(button);
    panel3.add(button2);
    panel3.add(button3);
    panel3.add(button4);
    panel3.add(button5);

    frame.add(mainpanel);

    // Button functions

    ActionListener BL = new ActionListener() {//save button
      public void actionPerformed(ActionEvent e) {
        try {
          BufferedWriter wr = new BufferedWriter(new FileWriter(target));

          wr.write(display.getText());

          wr.close();
        } catch (IOException eee) {
          eee.printStackTrace();
        }

      }
    };

    ActionListener BL2 = new ActionListener() {//load button
      public void actionPerformed(ActionEvent e) {
        String loader = "";
        try {
          BufferedReader re = new BufferedReader(new FileReader(target));

          String in = "";
          while ((in = re.readLine()) != null) {
            loader = loader + in + "\n";
          }

          display.setText(loader);

          re.close();
        } catch (IOException eeee) {
          eeee.printStackTrace();
        }

      }
    };


    ActionListener BL3 = new ActionListener() {//compile button
      public void actionPerformed(ActionEvent e) {

        String[] argsy = new String[] {"g++", "b.cpp", "-o", "b"};
        try {

          Process proc = new ProcessBuilder(argsy).start();


          BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
          String line = "";
          while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
          }

        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }

      }
    };

    ActionListener BL4 = new ActionListener() {//run button
      public void actionPerformed(ActionEvent e) {

        // only does run command to make it less confusing if something errors
        // plus you get experience doing each thing individually

        String[] argsy2 = new String[] {"./b"};

        try {

          Process proc2 = new ProcessBuilder(argsy2).start();

          BufferedReader reader = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
          String line = "";
          while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
          }

        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }


      }
    };

    ActionListener BL5 = new ActionListener() {//button macro for 3 actions
      public void actionPerformed(ActionEvent e) {


        try{

          String[] argsy = new String[] {"g++", "b.cpp", "-o", "b"};
          try {

            Process proc = new ProcessBuilder(argsy).start();


            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
              System.out.print(line + "\n");
            }

          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }


          String[] argsy2 = new String[] {"./b"};

          try {

            Process proc2 = new ProcessBuilder(argsy2).start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
              System.out.print(line + "\n");
            }

          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        } catch(Exception E) {
          E.printStackTrace(); // keeps the code running even if there's an error
        }
      }
    };


    button.addActionListener(BL);
    button2.addActionListener(BL2);
    button3.addActionListener(BL3);
    button4.addActionListener(BL4);
    button5.addActionListener(BL5);
  }

}
