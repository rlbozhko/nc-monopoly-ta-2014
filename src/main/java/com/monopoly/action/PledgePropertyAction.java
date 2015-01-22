package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 11.12.2014.
 */
    /*
    * 8.     Заложить
    8.1. Игрок выбирает Собственность для Залога. На Собственности не должно быть построено дополнительных
        Зданий и она не должна быть уже Заложена.
    8.2. Устанавливается Сумма, которую выплатит Банк (Кредит), и количество ходов, через которое Игроку необходимо
        вернуть эту Сумму + Проценты. Сумма Кредита не должна превышать 0,7 * Стоимости Собственности.
        Количество ходов через которое необходимо вернуть Кредит не должно превышать 5.
        Проценты зависят от количества ходов (например 10% за ход).
    8.3. Банк переводит Сумму Кредита Игроку
    8.4. Игрок возвращается к возможным действиям
    */

public class PledgePropertyAction implements Action {
    public static final double MAX_PERCENT_FROM_PROPERTY = 0.7;
    public static final int MAX_TURNS_TO_PAY_BACK = 1;
    public static final double MAX_PLEDGE_PERCENT_PER_TURN = 0.1;

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);

        PropertyCell property = (PropertyCell) selectPropertyToPledge(player, playerIO);
        if (property != null) {        	
            //выбирается игроком
            int turnsToPayBack = MAX_TURNS_TO_PAY_BACK;
            int payment = (int) (property.getPrice() * MAX_PERCENT_FROM_PROPERTY);
            double pledgePercent = MAX_PLEDGE_PERCENT_PER_TURN;
            //

            if (playerIO.yesNoDialog("Хотите заложить " + property.getName() + " за $" + payment + ".\n" +
                    " На " + turnsToPayBack + " ходов")) {
                player.addMoney(payment);
                property.setStatus(PropertyStatus.PLEDGED);
                property.setTurnsToPayBack(turnsToPayBack);
                property.setPledgePercent(pledgePercent);
                property.setPayBackMoney(payment);
                playerIO.showMessage("Вы заложили " + property.getName() + " за $" + payment + ".\n" +
                        " На " + turnsToPayBack + " ходов");
            }
        }
    }

    private Property selectPropertyToPledge(Player player, IO playerIO) {
        Property property = playerIO.selectProperty(player);
        if (property != null) {
            if (property.isPledged()) {
                playerIO.showMessage("Нельзя заложить уже заложеную собственность!!!");
            } else if (property.hasBuilding()) {
                playerIO.showMessage("Нельзя заложить собственность с постройками!!!");
            } else {
                return property;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Pledge Property";
    }
}
