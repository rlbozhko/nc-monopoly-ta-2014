package com.monopoly.action.deal;

/**
 * Created by Roma on 18.12.2014.
 */
public class AskMoneyDeal extends WrapperDeal {
    private int money;

    public AskMoneyDeal(Deal deal, int money) {
        this.money = money;
        this.deal = deal;
    }

    @Override
    public String message() {
        StringBuilder message = new StringBuilder();
        message.append("Требует ")
                .append("Денег: ").append(money).append("<br>");
        return super.message() + message.toString();
    }

    @Override
    public void performDeal() {
        super.performDeal();
        getTarget().subtractMoney(money);
        getSource().addMoney(money);        
    }

    @Override
    public boolean isValid() {
        boolean result = true;
        if (getTarget().getMoney() < money) {
            getTargetIO().showWarning("У Вас не достаточно денег");
            result = false;
        }
        return deal.isValid() && result;
    }
}
