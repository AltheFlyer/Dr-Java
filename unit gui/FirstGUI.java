import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * 
 * 
 * 
 */

public class FirstGUI {
    
    static JPanel topPanel;
    static JPanel buttonPanel;
    static JLabel textLabel;
    static JTextField textArea;
    
    static JButton firstButton;
    static JButton secondButton;
    static JButton thirdButton;
    
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Click!");
        
        mainWindow.setSize(300, 150);
        
        mainWindow.setResizable(true);
        
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        GridLayout mainLayout = new GridLayout(2, 1);
        
        mainWindow.setLayout(mainLayout);
        
        topPanel = new JPanel();
        buttonPanel = new JPanel();
        textLabel = new JLabel("1");
        textArea = new JTextField("12");
        
        firstButton = new JButton("First");
        secondButton = new JButton("Second");
        thirdButton = new JButton("Third");
        
        firstButton.addActionListener(new FirstButtonListener());
        secondButton.addActionListener(new SecondButtonListener());
        thirdButton.addActionListener(new ThirdButtonListener());
        
        topPanel.add(textLabel);
        topPanel.add(textArea);
        
        buttonPanel.add(firstButton);
        buttonPanel.add(secondButton);
        buttonPanel.add(thirdButton);
        
        mainWindow.add(topPanel);
        mainWindow.add(buttonPanel);
        
        mainWindow.setVisible(true);
    }
    
    static class FirstButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int textNumber = Integer.parseInt(textArea.getText());
            int labelNumber = Integer.parseInt(textLabel.getText());
            textArea.setText((labelNumber + textNumber) + "");
        }
    }
    
    static class SecondButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            textArea.setText("11");
        }
    }
    
    static class ThirdButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            textArea.setText("111");
        }
    }
}