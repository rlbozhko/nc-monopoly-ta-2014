package com.monopoly.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyStatus;

@Service
public class GameService {

	public List<Property> getPledgedProperty(List<Property> propertyList) {
		List<Property> pledgetPropertiesList = new ArrayList<Property>();
		for (Property each : propertyList) {
			if(each.getStatus() == PropertyStatus.PLEDGED) {
				pledgetPropertiesList.add(each);
			}
		}
			return pledgetPropertiesList;
	}

}
