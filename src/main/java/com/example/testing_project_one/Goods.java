package com.example.testing_project_one;

/**
 * @author Ivan 12.09.2022
 */
public class Goods {
    private String name;
    private int amount;
    private int cost_in;
    private int cost_out;
    private int profit;

    public Goods(String name, int amount, int cost_in, int cost_out) {
        this.name = name;
        this.amount = amount;
        this.cost_in = cost_in;
        this.cost_out = cost_out;
        //this.profit = profit;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost_in() {
        return cost_in;
    }

    public void setCost_in(int cost_in) {
        this.cost_in = cost_in;
    }

    public int getCost_out() {
        return cost_out;
    }

    public void setCost_out(int cost_out) {
        this.cost_out = cost_out;
    }

}