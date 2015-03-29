package com.monopoly.board.events;

public class EventFactory {
	public static Event getEvent(EventType eventType, String description) {
		switch (eventType) {

		case MOVE:
			return new MoveEvent(description);

		case RANDOM_MONEY:
			return new RandomMoneyEvent(description);

		case SKIP_TURN:
			return new SkipTurnEvent(description);

		case EXTRA_TURN:
			return new ExtraTurnEvent(description);

		case EMERGENCY:
			return new EmergencyEvent(description);

		case MONEY:
			return new MoneyEvent(description);

		default:
			throw new RuntimeException("Can't create event: " + eventType);
		}
	}
}
