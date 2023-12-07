import src.Manipulate.ManipulateExpenseGUI;
import src.Manipulate.ManipulateInvestmentGUI;
import src.model.ExpenseEntry;
import src.model.InvestmentEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI {

    private JFrame frame;

    public MainMenuGUI() {
        //init data
        ExpenseEntry expenseEntry = new ExpenseEntry();
        InvestmentEntry investmentEntry = new InvestmentEntry();

        // Creating the Frame
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        // Creating the panel with FlowLayout
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Creating buttons
        JButton button1 = new JButton("Manipulate Expenses");
        JButton button2 = new JButton("Manipulate Investments");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        // Setting the preferred size of buttons
        Dimension buttonSize = new Dimension(1000, 200);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);

        // Adding buttons to the panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        // Action listeners for buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulateExpenseGUI manipulateExpenseGUI = new ManipulateExpenseGUI(expenseEntry);
                manipulateExpenseGUI.showGUI();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulateInvestmentGUI manipulateInvestmentGUI = new ManipulateInvestmentGUI(investmentEntry);
                manipulateInvestmentGUI.showGUI();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 3 clicked");
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 4 clicked");
            }
        });

        // Adding panel to frame
        frame.add(panel);
    }

    public void showGUI() {
        // Setting the frame visibility
        frame.setVisible(true);
    }
}

