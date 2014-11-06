package com.monopoly;

/**
 * Created by Roma on 06.11.2014.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Монополия
 */
public class Monopoly {
    private String monopolyType;
    private List<Property> monopolyProperty;

    public Monopoly(String monopolyType) {
        this.monopolyType = monopolyType;
        monopolyProperty = new ArrayList<>();
    }

    public List<Property> getMonopolyProperty() {
        return monopolyProperty;
    }

    public boolean addProperty(Property property) {
        return monopolyProperty.add(property);
    }
}
