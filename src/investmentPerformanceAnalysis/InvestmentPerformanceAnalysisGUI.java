package src.investmentPerformanceAnalysis;

import src.model.Investment;
import src.model.InvestmentEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvestmentPerformanceAnalysisGUI {
    private JFrame frame;
    private InvestmentEntry investmentEntry;

    public InvestmentPerformanceAnalysisGUI(InvestmentEntry investmentEntry) {
        this.investmentEntry = investmentEntry;
        initializeFrame();
        addInvestmentTable();
    }

    private void initializeFrame() {
        frame = new JFrame("Investment Performance Analysis");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());
    }

    private void addInvestmentTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Type");
        model.addColumn("Total Amount");

        Map<String, Double> sumsByType = calculateSumsByType();
        for (Map.Entry<String, Double> entry : sumsByType.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private Map<String, Double> calculateSumsByType() {
        Map<String, Double> sums = new HashMap<>();
        List<Investment> investments = investmentEntry.getInvestments();
        for (Investment investment : investments) {
            String type = investment.getType(); // Assuming getType() method exists
            double amount = investment.getMoney(); // Assuming getAmount() method exists
            sums.merge(type, amount, Double::sum);
        }
        return sums;
    }

    public void showGUI() {
        frame.setVisible(true);
    }
}
