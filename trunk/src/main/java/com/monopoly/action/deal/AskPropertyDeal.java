package com.monopoly.action.deal;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;

import java.util.List;

/**
 * Created by Roma on 18.12.2014.
 */
public class AskPropertyDeal extends WrapperDeal {
    private List<Property> properties;

    public AskPropertyDeal(Deal deal, List<Property> properties) {
        this.properties = properties;
        this.deal = deal;
    }

    @Override
    public String message() {
        StringBuilder message = new StringBuilder();
        message.append("Требует ")
                .append("Собственность: ").append(properties).append(" ");
        return super.message() + message.toString();
    }

    @Override
    public void performDeal() {
        super.performDeal();
        PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();

        for (Property property : properties) {
            propertyManager.setPropertyOwner(getSource(),property);
        }
    }

    @Override
    public boolean isValid() {
        boolean result = true;
        for (Property property : properties) {
            if (property.isPledged()) {
                getSourceIO().showWarning("Собственность " + ((Cell) property).getName() + " заложена. Сделка отменяется");
                result = false;
                break;
            }
        }
        return deal.isValid() && result;
    }
}
