package com.monopoly.board.building;

import java.util.HashMap;
import java.util.Map;

public class BuildingSettings {

	private static Map <BuildingType, Settings> config = 
						new HashMap<BuildingType, Settings>();
	private static BuildingSettings instance = new BuildingSettings();
	
	private BuildingSettings (){
		this.initConfiguration();
	}
	
	public static BuildingSettings getInstance (){
		return instance;
	}
	
	public void initConfiguration() {
		config.put(BuildingType.CASTLE, new Settings(BuildingType.CASTLE));
		config.put(BuildingType.CLUB, new Settings(BuildingType.CLUB));
		config.put(BuildingType.LAW_DEPARTMENT, new Settings(BuildingType.LAW_DEPARTMENT));
		config.put(BuildingType.MARKET, new Settings(BuildingType.MARKET));
		config.put(BuildingType.PARK, new Settings(BuildingType.PARK));
	}

	public Settings getSettingsByType(BuildingType type) {
		return config.get(type);
	}
	
	public void initConfigurationFromServer (){
		
		//TODO Make this when application is launching
	}
		
}
