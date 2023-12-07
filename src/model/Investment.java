package src.model;

import java.io.Serializable;

public class Investment implements Serializable{
    private int money;
    private String type;

    public Investment(int money, String type){
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
}