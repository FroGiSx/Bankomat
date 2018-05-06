package body;

public class Config {
    private int money = 1000;
    private String pass = "qwerty";
    private int idCard = 0;

    public int getMoney() {
        return money;
    }

    public int getIdCard() {
        return idCard;
    }

    public String getPass() {
        return pass;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
    public void addMoney(int money){
        this.money += money;
    }
    public void removeMoney(int money){
        this.money -= money;
    }
}
