package com.monopoly.board.events;

public class EventFactory {
	public static Event getEvent(EventType eventType, String name,
			String description) {
		switch (eventType) {

		case MOVE:
			return new MoveEvent(name, description);

		case RANDOM_MONEY:
			return new RandomMoneyEvent(name, description);

		case SKIP_TURN:
			return new SkipTurnEvent(name, description);

		case EXTRA_TURN:
			return new ExtraTurnEvent(name, description);

		case EMERGENCY:
			return new EmergencyEvent(name, description);

		case MONEY:
			return new MoneyEvent(name, description);

		default:
			throw new RuntimeException("Can't create event: " + eventType);
		}
	}
}
