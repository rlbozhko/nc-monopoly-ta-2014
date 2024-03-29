package com.monopoly.board.player;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.cells.*;
import com.monopoly.entity.PlayerEntity;
import com.monopoly.game.session.GameSession;

import java.util.List;

public class Player implements MoneyOperations, MoveOperations, PropertyOperations {
	private int position;
	private int lastPosition;
	private String name;
	private Status status;
	private Wallet wallet;
	private boolean payRent;
	private int jailTerm;
	private Status jailStatus;
	private boolean offerADeal;
	private PlayerTimer timer;
	private int doublesCount;
	private String playerColor;
	private boolean extraTurn;
	private boolean buildPerformed;

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

	public Player(PlayerEntity entity) {
		this.position = entity.getPosition();
		this.lastPosition = entity.getLastPosition();
		this.name = entity.getName();
		this.wallet = new Wallet();
		this.wallet.addMoney(entity.getWallet());
		this.status = entity.getStatus();
		this.payRent = entity.isPayRent();
		this.jailStatus = entity.getJailStatus();
		this.jailTerm = entity.getJailTerm();
		this.offerADeal = entity.isOfferADeal();
		this.doublesCount = entity.getDoublesCount();
		this.playerColor = entity.getPlayerColor();
		this.extraTurn = entity.isExtraTurn();
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

	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
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

		if (isNextCircle(position)) {
			GameSession.getInstance().getBoard().performStartEvent();
		}

		if (position < 0) {
			this.position = (boardSize + position) % boardSize;
		} else {
			this.position = position % boardSize;
		}

		Cell currentCell = getCurrentCell();

		ActionUtils.sendMessageToAll(this.getName() + " перешел на ячейку " + currentCell.getName());
		if (CellType.EVENT_CELL == currentCell.getCellType()) {
			((EventCell) currentCell).getEvent().performEvent();
		} else if (CellType.PROPERTY_CELL == currentCell.getCellType()) {
			Property property = (Property) currentCell;
			Player owner = propertyManager.getPropertyOwner(property);
			if (!property.isPledged() && null != owner && !this.equals(owner)) {
				this.setPayRent(true);
			}
		}
	}

	@Override
	public Cell getCurrentCell() {
		return GameSession.getInstance().getBoard().getCells().get(getPosition());
	}

	private List<Property> getProperties() {
		return GameSession.getInstance().getPropertyManager().getPlayerProperties(this);
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

	public boolean hasProperty() {
		return !GameSession.getInstance().getPropertyManager().getPlayerProperties(this).isEmpty();
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

	public boolean hasExtraTurn() {
		return extraTurn;
	}

	public void setExtraTurn(boolean extraTurn) {
		this.extraTurn = extraTurn;
	}

	public boolean isBuildPerformed() {
		return buildPerformed;
	}

	public void setBuildPerformed(boolean build) {
		this.buildPerformed = build;
	}
}
