package com.monopoly.action;

import com.monopoly.action.jail.EscapeAction;
import com.monopoly.action.jail.PayBailAction;
import com.monopoly.action.jail.ServeJailTermAction;

public enum ActionType {
	START_TURN {
		@Override
		public Action create() {
			return new StartTurnAction();
		}
	},
	END_TURN {
		@Override
		public Action create() {
			return new EndTurnAction();
		}
	},
	FINISH_GAME {
		@Override
		public Action create() {
			return new FinishGameAction();
		}
	},
	BUY_PROPERTY {
		@Override
		public Action create() {
			return new BuyPropertyAction();
		}
	},
	PAY_RENT {
		@Override
		public Action create() {
			return new PayRentAction();
		}
	},
	PLEDGE_PROPERTY {
		@Override
		public Action create() {
			return new PledgePropertyAction();
		}
	},
	PAY_BACK {
		@Override
		public Action create() {
			return new PayBackAction();
		}
	},
	DEAL {
		@Override
		public Action create() {
			return new DealAction();
		}
	},
	BUILD {
		@Override
		public Action create() {
			return new BuildAction();
		}
	},
	UPGRADE_BUILDING {
		@Override
		public Action create() {
			return new UpgradeBuildingAction();
		}
	},
	SELL_BUILDING {
		@Override
		public Action create() {
			return new SellBuildingLevelAction();
		}
	},
	WAIT {
		@Override
		public Action create() {
			return new WaitAction();
		}
	},
	BETRAYAL {
		@Override
		public Action create() {
			return new BetrayalActioin();
		}
	},
	ESCAPE {
		@Override
		public Action create() {
			return new EscapeAction();
		}
	},
	PAY_BAIL {
		@Override
		public Action create() {
			return new PayBailAction();
		}
	},
	SERVE_JAIL_TERM {
		@Override
		public Action create() {
			return new ServeJailTermAction();
		}
	};

	abstract public Action create();
}