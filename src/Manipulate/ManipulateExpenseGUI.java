package src.Manipulate;

import src.model.Expense;
import src.model.ExpenseEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManipulateExpenseGUI implements Manipulate {

    private JFrame frame;
    private ExpenseEntry expenseEntry;
    private JTable expensesTable;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private JTextField amountField;
    private JTextField descField;

    public ManipulateExpenseGUI(ExpenseEntry expenseEntry) {
        this.expenseEntry = expenseEntry;

        initializeFrame();
        initializeTableModelAndTable();
        createPanelAndComponents();
        addPanelToFrame();
        initializeList();
    }

    @Override
    public void initializeFrame() {
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }

    @Override
    public void initializeTableModelAndTable() {
        tableModel = new DefaultTableModel(new Object[]{"Amount", "Description"}, 0);
        expensesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(expensesTable);
        frame.add(scrollPane);
    }

    @Override
    public void createPanelAndComponents() {
        panel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        amountField = new JTextField(10); // for expense amount
        descField = new JTextField(20); // for expense description

        addButton.addActionListener(e -> addActionListener());
        deleteButton.addActionListener(e -> deleteActionListener());

        JButton returnButton = new JButton("Return to Main");
        returnButton.addActionListener(e -> returnActionListener());

        panel.add(amountField);
        panel.add(descField);
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
            String description = descField.getText();
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a description");
                return;
            }
            Expense newExpense = new Expense(amount, description);
            expenseEntry.addExpense(newExpense);
            updateList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount");
        }
    }

    public void deleteActionListener() {
        int selectedRow = expensesTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            expenseEntry.removeExpenseAt(selectedRow);
        }
    }

    public void returnActionListener() {
        frame.setVisible(false);
    }

    public void updateList() {
        tableModel.setRowCount(0);
        for (Expense expense : expenseEntry.getExpenses()) {
            tableModel.addRow(new Object[]{expense.getMoney(), expense.getType()});
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

