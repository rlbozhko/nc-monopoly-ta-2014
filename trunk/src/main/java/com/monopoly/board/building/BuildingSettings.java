package com.monopoly.board.building;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class BuildingSettings {

	private static Map <BuildingType, Settings> config = 
						new HashedMap<BuildingType, Settings>();
	private static BuildingSettings instance;
	
	private BuildingSettings (){
		this.initConfiguration();
	}
	
	public static synchronized BuildingSettings getInstance (){
		if (instance == null){
			instance = new BuildingSettings();
		}
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

}
