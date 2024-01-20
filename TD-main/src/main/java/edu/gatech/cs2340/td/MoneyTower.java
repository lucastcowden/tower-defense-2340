package edu.gatech.cs2340.td;

import javafx.scene.image.Image;

public class MoneyTower extends AbstractTower {

    private static final int TICKS_BETWEEN_PAYOUT = 20;
    private static int payAmount = 5;
    private int currentTicks = 0;
    private String imgPath = "moneytower.png";

    private void payout() {
        if (currentTicks == TICKS_BETWEEN_PAYOUT) {
            int newMoney = GameData.getInstance().getPlayer().getMoney() + payAmount;
            GameData.getInstance().getPlayer().setMoney(newMoney);
            currentTicks = 0;
        }
        currentTicks++;
    }

    @Override
    public void attack(int x, int y) {
        super.attack(x, y);
        payout();
    }

    public void upgrade() {
        ((AbstractTower) this).setTier(2);
        imgPath = "moneytowerupgrade.png";
        ((AbstractTower) this).setImage(new Image(AbstractTower.class.getResource(
                getImage()).toString()));
        this.payAmount = 10;
    }

    public String getImage() {
        return imgPath;
    }

    @Override
    public String getName() {
        return "Money Generating Tower";
    }

    @Override
    public String getDescription() {
        return "Generates money during combat phase.";
    }

    @Override
    public int getBaseCost() {
        return 125;
    }

    @Override
    public AbstractTower clone() {
        return new MoneyTower();
    }
}