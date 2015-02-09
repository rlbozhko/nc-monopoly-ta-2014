package com.monopoly.board.events;

public enum EventType {
	EMERGENCY {
		@Override
		public Event create() {
			return new EmergencyEvent("Случился пожар");
		}

		@Override
		public Event create(String description) {
			return new EmergencyEvent(description);
		}
	},
	EXTRA_TURN {
		@Override
		public Event create() {
			return new ExtraTurnEvent("");
		}

		@Override
		public Event create(String description) {
			return new ExtraTurnEvent(description);
		}
	},
	MONEY {
		@Override
		public Event create() {
			return new MoneyEvent("прошел полный круг и Получил $200");
		}

		@Override
		public Event create(String description) {
			return new MoneyEvent(description);
		}
	},
	MOVE {
		@Override
		public Event create() {
			return new MoveEvent("");
		}

		@Override
		public Event create(String description) {
			return new MoveEvent(description);
		}
	},
	RANDOM_MONEY {
		@Override
		public Event create() {
			return new RandomMoneyEvent("");
		}

		@Override
		public Event create(String description) {
			return new RandomMoneyEvent(description);
		}
	},
	SKIP_TURN {
		@Override
		public Event create() {
			return new SkipTurnEvent("");
		}

		@Override
		public Event create(String description) {
			return new SkipTurnEvent(description);
		}
	},
	GO_TO_JAIL {
		@Override
		public Event create() {
			return new GoToJailEvent("Отправляйтесь в тюьму");
		}

		@Override
		public Event create(String description) {
			return new GoToJailEvent(description);
		}
	},
	JAIL {
		@Override
		public Event create() {
			return new JailEvent("Это место лучше пройти мимо");
		}

		@Override
		public Event create(String description) {
			return new JailEvent(description);
		}
	},
	FREE {
		@Override
		public Event create() {
			return new FreePlaceEvent("Можете передохнуть. Бесплатная стоянка");
		}

		@Override
		public Event create(String description) {			
			return new FreePlaceEvent(description);
		}
	};

	public abstract Event create();

	public abstract Event create(String description);
}
