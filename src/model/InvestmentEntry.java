package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InvestmentEntry implements Serializable{
    private List<Investment> investments;

    public InvestmentEntry() {
        investments = new ArrayList<>();
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void addInvestment(Investment investment) {
        investments.add(investment);
    }

    public void removeInvestmentAt(int index) {
        if (index >= 0 && index < investments.size()) {
            investments.remove(index);
        }
    }
}
