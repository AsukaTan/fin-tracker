package src.Manipulate;

import src.model.Investment;
import src.model.InvestmentEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManipulateInvestmentGUI implements Manipulate {

    private JFrame frame;
    private InvestmentEntry investmentEntry;
    private JTable investmentsTable;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private JTextField amountField;
    private JTextField typeField;

    public ManipulateInvestmentGUI(InvestmentEntry investmentEntry) {
        this.investmentEntry = investmentEntry;

        initializeFrame();
        initializeTableModelAndTable();
        createPanelAndComponents();
        addPanelToFrame();
        initializeList();
    }

    @Override
    public void initializeFrame() {
        frame = new JFrame("Investment Tracker");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }

    @Override
    public void initializeTableModelAndTable() {
        tableModel = new DefaultTableModel(new Object[]{"Amount", "Type"}, 0);
        investmentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(investmentsTable);
        frame.add(scrollPane);
    }

    @Override
    public void createPanelAndComponents() {
        panel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        amountField = new JTextField(10); // for investment amount
        typeField = new JTextField(20); // for investment type

        addButton.addActionListener(e -> addActionListener());
        deleteButton.addActionListener(e -> deleteActionListener());

        JButton returnButton = new JButton("Return to Main");
        returnButton.addActionListener(e -> returnActionListener());

        panel.add(amountField);
        panel.add(typeField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(returnButton);
    }

    @Override
    public void addPanelToFrame() {
        frame.add(panel);
    }

    public void addActionListener() {
        try {
            int amount = Integer.parseInt(amountField.getText());
            String type = typeField.getText();
            if (type.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter an investment type");
                return;
            }
            Investment newInvestment = new Investment(amount, type);
            investmentEntry.addInvestment(newInvestment);
            updateList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount");
        }
    }

    public void deleteActionListener() {
        int selectedRow = investmentsTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            investmentEntry.removeInvestmentAt(selectedRow);
        }
    }

    public void returnActionListener() {
        frame.setVisible(false);
    }

    public void updateList() {
        tableModel.setRowCount(0);
        for (Investment investment : investmentEntry.getInvestments()) {
            tableModel.addRow(new Object[]{investment.getMoney(), investment.getType()});
        }
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    @Override
    public void initializeList() {
        updateList();
    }
}

