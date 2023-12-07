package src.model;

import java.io.Serializable;

public class Expense implements Serializable {
    private int money;
    private String type;

    public Expense(int money, String type){
        this.money = money;
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "money: " + money + ", type: " + type;
    }
}
