package com.monopoly.action.deal;

/**
 * Created by Roma on 18.12.2014.
 */
public class GiveMoneyDeal extends WrapperDeal {
    private int money;

    public GiveMoneyDeal(Deal deal, int money) {
        this.money = money;
        this.deal = deal;
    }

    @Override
    public String message() {
        StringBuilder message = new StringBuilder();
        message.append("Предлагает ")
                .append("Денег: ").append(money).append("\n");
        return super.message() + message.toString();
    }

    @Override
    public void performDeal() {
        super.performDeal();
        getTarget().addMoney(money);
        getSource().subtractMoney(money);
    }

    @Override
    public boolean isValid() {
        boolean result = true;
        if (getSource().getMoney() < money) {
            getSourceIO().showMessage("У Вас не достаточно денег!");
            result = false;
        }
        return deal.isValid() && result;
    }
}
