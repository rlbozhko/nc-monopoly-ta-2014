package com.monopoly.board.player;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.cells.*;
import com.monopoly.game.session.GameSession;

import java.util.List;

public class Player implements MoneyOperations, MoveOperations, PropertyOperations {
	private int position;
	private int lastPosition;
	private String name;
	private Status status;
	private Wallet wallet;
	private boolean payRent;
	private int jailTerm = 0;
	private Status jailStatus;
	private boolean offerADeal;
	private PlayerTimer timer;
	private int doublesCount;

	public Player(String name) {
		position = 0;
		lastPosition = 0;
		this.name = name;
		this.wallet = new Wallet();
		status = Status.WAIT;
		payRent = false;
		jailStatus = Status.CLEAN;
		timer = new PlayerTimer(this);
	}

	public Status getJailStatus() {
		return jailStatus;
	}

	public void setJailStatus(Status jailStatus) {
		this.jailStatus = jailStatus;
	}

	public int getJailTerm() {
		return jailTerm;
	}

	public void subtractJailTerm() {
		this.jailTerm -= 1;
	}

	public void setJailTerm(int jailTerm) {
		this.jailTerm = jailTerm;
	}

	@Override
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isJailed() {
		return getJailStatus() == Status.JAILED;
	}

	@Override
	public int getLastPosition() {
		return lastPosition;
	}

	@Override
	public void goToPosition(int position) {
		int boardSize = GameSession.getInstance().getBoard().getCells().size();
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		this.lastPosition = this.position;
		boolean nextCircle = isNextCircle(position);
		this.position = position % boardSize;
		if (nextCircle) {
			GameSession.getInstance().getBoard().performStartEvent();
		}

		if (hasPledgedProperty()) {
			pledgedPropertyCheck();
		}

		Cell currentCell = GameSession.getInstance().getBoard().getCells().get(this.getPosition());
		if (CellType.EVENT_CELL == currentCell.getCellType()) {
			((EventCell) currentCell).getEvent().performEvent();
		} else if (CellType.PROPERTY_CELL == currentCell.getCellType()) {
			Property property = (Property) currentCell;
			Player owner = propertyManager.getPropertyOwner(property);
			if (null != owner && !this.equals(owner)) {
				this.setPayRent(true);
			}
		}
	}

	@Override
	public Cell getCurrentCell() {
		return GameSession.getInstance().getBoard().getCells().get(getPosition());
	}

	private void pledgedPropertyCheck() {
		for (Property property : getProperties()) {
			if (property.isPledged()) {
				property.decrementTurnsToPayBack();
				property.risePayBackMoney();
				propertyWarning(property);
			}
		}
	}

	private List<Property> getProperties() {
		return GameSession.getInstance().getPropertyManager().getPlayerProperties(this);
	}

	private void propertyWarning(Property property) {
		if (property.getTurnsToPayBack() == 0) {
			ActionUtils.getPlayerIO(this).showWarning(
					"ВНИМАНИЕ!!!\n" + "Срок погашения заема истек для " + ((Cell) property).getName() + ".\n"
							+ "Если вы не погасите задолженность, то по окончанию хода начнется аукцион");
		}
	}

	private boolean isNextCircle(int position) {
		return (position > GameSession.getInstance().getBoard().getCells().size());
	}

	@Override
	public void addMoney(int money) {
		wallet.addMoney(money);
	}

	@Override
	public boolean subtractMoney(int money) {		
		return wallet.subtractMoney(money);		
	}

	@Override
	public int getMoney() {
		return wallet.getMoney();
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean buyProperty(PropertyCell propertyCell) {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		if (subtractMoney(propertyCell.getPrice())) {			
			propertyManager.setPropertyOwner(this, propertyCell);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPledgedProperty() {
		for (Property property : getProperties()) {
			if (property.isPledged()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPayRent() {
		return payRent;
	}

	@Override
	public void setPayRent(boolean payRent) {
		this.payRent = payRent;
	}

	public static class PlayerBuilder {
		private int position;
		private int lastPosition;
		private String name;
		private Status status;
		private int money;
		private boolean payRent;
		private Status jailStatus;

		public PlayerBuilder(String name) {
			this.name = name;
			status = Status.WAIT;
			jailStatus = Status.CLEAN;
		}

		public Player getPlayer() {
			Player player = new Player(name);
			player.position = position;
			player.lastPosition = lastPosition;
			player.status = status;
			player.addMoney(money);
			player.payRent = payRent;
			player.jailStatus = jailStatus;

			return player;
		}

		public PlayerBuilder lastPosition(int lastPosition) {
			this.lastPosition = lastPosition;
			return this;
		}

		public PlayerBuilder position(int position) {
			this.position = position;
			return this;
		}

		public PlayerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PlayerBuilder payRent(boolean payRent) {
			this.payRent = payRent;
			return this;
		}

		public PlayerBuilder status(Status status) {
			this.status = status;
			return this;
		}

		public PlayerBuilder money(int money) {
			this.money = money;
			return this;
		}

		public PlayerBuilder jailStatus(Status jailStatus) {
			this.jailStatus = jailStatus;
			return this;

		}
	}

	@Override
	public boolean isOfferADeal() {
		return offerADeal;
	}

	@Override
	public void setOfferADeal(boolean offerADeal) {
		this.offerADeal = offerADeal;		
	}
	
	@Override
	public void addTime() {
		timer.addTime();
	}

	@Override
	public void resetTimer() {
		timer.reset();		
	}

	@Override
	public boolean hasRamainingTime() {
		return timer.hasRamainingTime();
	}

	@Override
	public long getRemainingTime() {		
		return timer.getRemainingTime();
	}

	@Override
	public void startTimer() {
		timer.start();
		
	}
	
	@Override
	public void incrementDoublesCount() {
		doublesCount++;
	}
	
	@Override
	public int getDoublesCount() {
		return doublesCount;
	}
	
	@Override
	public void resetDoublesCount() {
		doublesCount = 0;
	}

	@Override
	public boolean isTimerStarted() {		
		return timer.isStarted();
	}
}
