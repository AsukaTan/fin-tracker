import src.Manipulate.ManipulateExpenseGUI;
import src.Manipulate.ManipulateInvestmentGUI;
import src.model.ExpenseEntry;
import src.model.InvestmentEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class MainMenuGUI {

    private JFrame frame;
    private JPanel panel;
    private ExpenseEntry expenseEntry;
    private InvestmentEntry investmentEntry;

    public MainMenuGUI() {
        loadExpenseEntryData();
        loadInvestmentEntryData();

        initializeFrame();
        initializePanel();
        createButtons();
        setupActionListeners();
        addPanelToFrame();
        setupCloseOperation();
    }

    private void initializeFrame() {
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
    }

    private void initializePanel() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    private void createButtons() {
        JButton button1 = new JButton("Manipulate Expenses");
        JButton button2 = new JButton("Manipulate Investments");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        Dimension buttonSize = new Dimension(1000, 200);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
    }

    private void setupActionListeners() {
        JButton button1 = (JButton) panel.getComponent(0);
        button1.addActionListener(e -> {
            ManipulateExpenseGUI manipulateExpenseGUI = new ManipulateExpenseGUI(expenseEntry);
            manipulateExpenseGUI.showGUI();
        });

        JButton button2 = (JButton) panel.getComponent(1);
        button2.addActionListener(e -> {
            ManipulateInvestmentGUI manipulateInvestmentGUI = new ManipulateInvestmentGUI(investmentEntry);
            manipulateInvestmentGUI.showGUI();
        });

        JButton button3 = (JButton) panel.getComponent(2);
        button3.addActionListener(e -> System.out.println("Button 3 clicked"));

        JButton button4 = (JButton) panel.getComponent(3);
        button4.addActionListener(e -> System.out.println("Button 4 clicked"));
    }
    private void addPanelToFrame() {
        frame.add(panel);
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    private void saveExpenseEntryData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("expenseEntry.dat"))) {
            oos.writeObject(expenseEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadExpenseEntryData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("expenseEntry.dat"))) {
            expenseEntry = (ExpenseEntry) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            expenseEntry = new ExpenseEntry(); // Create new if not found
        }
    }

    private void saveInvestmentEntryData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("investmentEntry.dat"))) {
            oos.writeObject(investmentEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadInvestmentEntryData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("investmentEntry.dat"))) {
            investmentEntry = (InvestmentEntry) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            investmentEntry = new InvestmentEntry(); // Create new if not found
        }
    }

    private void setupCloseOperation() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveExpenseEntryData();
                saveInvestmentEntryData();
            }
        });
    }
}
