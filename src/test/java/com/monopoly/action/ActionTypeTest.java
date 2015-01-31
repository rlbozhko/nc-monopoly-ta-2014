package com.monopoly.action;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.monopoly.action.jail.EscapeAction;
import com.monopoly.action.jail.PayBailAction;
import com.monopoly.action.jail.ServeJailTermAction;

public class ActionTypeTest {

	@Test
	public void testCreateWait() {
		Action action = ActionType.valueOf("WAIT").create();
		assertTrue(action instanceof WaitAction);
	}

	@Test
	public void testCreateStartTurn() {
		Action action = ActionType.valueOf("START_TURN").create();
		assertTrue(action instanceof StartTurnAction);
	}

	@Test
	public void testCreateEndTurn() {
		Action action = ActionType.valueOf("END_TURN").create();
		assertTrue(action instanceof EndTurnAction);
	}

	@Test
	public void testCreateFinishGame() {
		Action action = ActionType.valueOf("FINISH_GAME").create();
		assertTrue(action instanceof FinishGameAction);
	}

	@Test
	public void testCreateBuyProperty() {
		Action action = ActionType.valueOf("BUY_PROPERTY").create();
		assertTrue(action instanceof BuyPropertyAction);
	}

	@Test
	public void testCreatePayRent() {
		Action action = ActionType.valueOf("PAY_RENT").create();
		assertTrue(action instanceof PayRentAction);
	}

	@Test
	public void testCreatePledge() {
		Action action = ActionType.valueOf("PLEDGE_PROPERTY").create();
		assertTrue(action instanceof PledgePropertyAction);
	}

	@Test
	public void testCreatePayBack() {
		Action action = ActionType.valueOf("PAY_BACK").create();
		assertTrue(action instanceof PayBackAction);
	}

	@Test
	public void testCreateDeal() {
		Action action = ActionType.valueOf("DEAL").create();
		assertTrue(action instanceof DealAction);
	}

	@Test
	public void testCreateBuild() {
		Action action = ActionType.valueOf("BUILD").create();
		assertTrue(action instanceof BuildAction);
	}

	@Test
	public void testCreateUpgradeBuilding() {
		Action action = ActionType.valueOf("UPGRADE_BUILDING").create();
		assertTrue(action instanceof UpgradeBuildingAction);
	}

	@Test
	public void testCreateSellBuilding() {
		Action action = ActionType.valueOf("SELL_BUILDING").create();
		assertTrue(action instanceof SellBuildingLevelAction);
	}

	@Test
	public void testCreatBetrayal() {
		Action action = ActionType.valueOf("BETRAYAL").create();
		assertTrue(action instanceof BetrayalActioin);
	}

	@Test
	public void testCreateEscape() {
		Action action = ActionType.valueOf("ESCAPE").create();
		assertTrue(action instanceof EscapeAction);
	}

	@Test
	public void testCreatePayBail() {
		Action action = ActionType.valueOf("PAY_BAIL").create();
		assertTrue(action instanceof PayBailAction);
	}

	@Test
	public void testCreateServeJailTerm() {
		Action action = ActionType.valueOf("SERVE_JAIL_TERM").create();
		assertTrue(action instanceof ServeJailTermAction);
	}
}
