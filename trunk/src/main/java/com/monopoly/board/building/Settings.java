package com.monopoly.board.building;

public class Settings {

	private String name;
	private String description;
	private int maxLevel;
	private BonusType bonusType;
	
	public Settings (BuildingType type){
				
		switch (type) {
		
		case CLUB:
			this.setName("Club");
			this.setDescription("Увеличивают шансы Диверсии заказанной Владельцем");
			this.setMaxLevel(5);
			this.setBonusType(BonusType.INCREASE_SABOTAGE);
			break;
			
		case PARK:
			this.setName("Park");
			this.setDescription("Добавляет к данной ячейке"
					+ "случайное Событие из списка Шанс");
			this.setMaxLevel(5);
			this.setBonusType(BonusType.ADD_EVENT);
			break;
			
		case MARKET:
			this.setName("Market");
			this.setDescription("При попадании на Данную "
			+ "Собственность/Монополию Владельцу зачисляются доп средства");
			this.setMaxLevel(5);
			this.setBonusType(BonusType.ADD_MONEY_TO_OWNER);
			break;
			
		case LAW_DEPARTMENT:
			this.setName("Law department");
			this.setDescription("Уменьшается количество ходов при отсидке в тюрьме."
			+ "Игрок может владеть только Одним Юр.отделом");
			this.setMaxLevel(5);
			this.setBonusType(BonusType.DECREASE_JAIL_TERM);
			break;
			
		case CASTLE:
			this.setName("Castle");
			this.setDescription("Добавляют к Стоимости Аренды больше, но Стоят тоже больше");
			this.setMaxLevel(5);
			this.setBonusType(BonusType.INCREASE_PAY_RENT);
			break;
			
		default:
			throw new RuntimeException("Not supported building type " + type.toString());
		}
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	private void setDescription(String description) {
		this.description = description;
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	private void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	public BonusType getBonusType() {
		return bonusType;
	}

	private void setBonusType(BonusType bonusType) {
		this.bonusType = bonusType;
	}
	
}
