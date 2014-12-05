package com.monopoly.board.events;

public class EventFactory {
	public static Event getEvent(EventType type, String name, String description) {
		if (type.equals(EventType.MOVE)) {
			return new MoveEvent(name, description);
		}
		if (type.equals(EventType.RANDOM_MONEY)) {
			return new RandomMoneyEvent(name, description);
		}
		if (type.equals(EventType.SKIP_TURN)) {
			return new SkipTurnEvent(name, description);
		}
		if (type.equals(EventType.EXTRA_TURN)) {
			return new ExtraTurnEvent(name, description);
		}
		throw new RuntimeException("Bad event creation: " + type);
	}
	
	public static Event getEvent(EventType type, String name, String description, int value) {
		if (type.equals(EventType.EMERGENCY)) {
			return new EmergencyEvent(name, description, value);
		}
		if (type.equals(EventType.MONEY)) {
			return new MoneyEvent(name, description, value);
		}
		throw new RuntimeException("Bad event creation: " + type);
	}

}
