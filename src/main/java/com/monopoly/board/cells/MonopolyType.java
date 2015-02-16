package com.monopoly.board.cells;

public enum MonopolyType {
	MONOPOLY1 {
		@Override
		public String getColor() {
			return "#008000";
		}
	},
	MONOPOLY2 {
		@Override
		public String getColor() {
			return "#B22222";
		}
	},
	MONOPOLY3 {
		@Override
		public String getColor() {
			return "#6495ED";
		}
	},
	MONOPOLY4 {
		@Override
		public String getColor() {
			return "#9400D3";
		}
	},
	MONOPOLY5 {
		@Override
		public String getColor() {
			return "#A52A2A";
		}
	},
	MONOPOLY6 {
		@Override
		public String getColor() {
			return "#FFFF00";
		}
	},
	MONOPOLY7 {
		@Override
		public String getColor() {
			return "#FF1493";
		}
	},
	MONOPOLY8 {
		@Override
		public String getColor() {
			return "#FF8C00";
		}
	},
	MONOPOLY9 {
		@Override
		public String getColor() {
			return "#808080";
		}
	};

	abstract public String getColor();
}
