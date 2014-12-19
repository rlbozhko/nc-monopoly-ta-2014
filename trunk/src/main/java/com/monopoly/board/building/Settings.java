package com.monopoly.board.building;

public class Settings implements AvailableBuilding {

	private String name;
	private String description;
	private int maxLevel;
	
	public Settings (BuildingType type){
				
		switch (type) {
		
		case CLUB:
			this.setName("Club");
			this.setDescription("Увеличивают шансы Диверсии заказанной Владельцем");
			this.setMaxLevel(5);
			break;
			
		case PARK:
			this.setName("Park");
			this.setDescription("Добавляет к данной ячейке"
					+ "случайное Событие из списка Шанс");
			this.setMaxLevel(5);
			break;
			
		case MARKET:
			this.setName("Market");
			this.setDescription("При попадании на Данную "
			+ "Собственность/Монополию Владельцу зачисляются доп средства");
			this.setMaxLevel(5);
			break;
			
		case LAW_DEPARTMENT:
			this.setName("Law department");
			this.setDescription("Уменьшается количество ходов при отсидке в тюрьме."
			+ "Игрок может владеть только Одним Юр.отделом");
			this.setMaxLevel(5);
			break;
			
		case CASTLE:
			this.setName("Castle");
			this.setDescription("Добавляют к Стоимости Аренды больше, но Стоят тоже больше");
			this.setMaxLevel(5);
			break;
			
		default:
			throw new RuntimeException("Not supported building type " + type.toString());
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
		
}
