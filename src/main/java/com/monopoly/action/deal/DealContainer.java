package com.monopoly.action.deal;

import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;

import java.util.*;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Created by Roma on 24.11.2014.
 */
public class DealContainer {
	private int askMoney;
	private int giveMoney;
	private Set<Property> askProperties = new LinkedHashSet<>();;
	private Set<Property> giveProperties = new LinkedHashSet<>();
	private Player source;
	private Player target;

	public DealContainer() {
	}

	public DealContainer(Player source, Player target) {
		this.source = source;
		this.target = target;
	}

	public Deal createDeal() {
		Deal deal = new EmptyDeal(source, target);
		if (getAskMoney() != 0) {
			deal = new AskMoneyDeal(deal, getAskMoney());
		}
		if (getGiveMoney() != 0) {
			deal = new GiveMoneyDeal(deal, getGiveMoney());
		}
		if (!isEmpty(getAskProperties())) {
			deal = new AskPropertyDeal(deal, getAskProperties());
		}
		if (!isEmpty(getGiveProperties())) {
			deal = new GivePropertyDeal(deal, getGiveProperties());
		}
		return deal;
	}

	public void setSource(Player source) {
		this.source = source;
	}

	public Player getSource() {
		return source;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public Player getTarget() {
		return target;
	}

	public int getAskMoney() {
		return askMoney;
	}

	public void setAskMoney(int askMoney) {
		this.askMoney = askMoney;
	}

	public int getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(int giveMoney) {
		this.giveMoney = giveMoney;
	}

	public void addAskProperty(Property askProperty) {
		if (askProperty != null) {
			this.askProperties.add(askProperty);
		}
	}

	public void addGiveProperty(Property giveProperty) {
		if (giveProperty != null) {
			this.giveProperties.add(giveProperty);
		}
	}

	public void setAskProperties(Set<Property> askProperties) {
		this.askProperties = askProperties;
	}

	public List<Property> getAskProperties() {
		return new ArrayList<>(askProperties);
	}

	public void setGiveProperties(Set<Property> giveProperties) {
		this.giveProperties = giveProperties;
	}

	public List<Property> getGiveProperties() {
		return new ArrayList<>(giveProperties);
	}

	public String message() {
		StringBuilder message = new StringBuilder();
		message.append(source.getName()).append(" ").append("Предлагает\n").append("Денег: ").append(getGiveMoney())
				.append("\n").append("Собственность: ").append(getGiveProperties()).append("\n").append("Просит\n")
				.append("Денег: ").append(getAskMoney()).append("\n").append("Собственность: ")
				.append(getAskProperties()).append("\n");
		return message.toString();
	}
}