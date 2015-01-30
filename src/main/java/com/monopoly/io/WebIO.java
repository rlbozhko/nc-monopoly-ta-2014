package com.monopoly.io;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.monopoly.action.Action;
import com.monopoly.action.deal.Deal;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

public class WebIO implements IO {
	private Player player;

	private volatile boolean selectPlayerRequest;
	private Object selectPlayerLock = new Object();
	private Player selectedPlayer;

	private volatile boolean selectPropertyRequest;
	private Object selectPropertyLock = new Object();
	private SelectPropertyHelper selectPropertyHelper;

	private volatile boolean createDealRequest;
	private Object createDealLock = new Object();
	private Deal createdDeal;

	private Queue<String> messages = new LinkedList<>();

	private Queue<String> warnings = new LinkedList<>();

	private Queue<YesNoDialog> yesNoDialogs = new LinkedList<>();

	public WebIO(Player player) {
		this.player = player;
	}

	@Override
	public Player getOwner() {
		return player;
	}

	@Override
	public Player selectPlayer() {
		synchronized (selectPlayerLock) {
			selectPlayerRequest = true;
			while (selectPlayerRequest) {
				try {
					selectPlayerLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return selectedPlayer;
	}

	@Override
	public boolean hasSelectPlayerRequest() {
		synchronized (selectPlayerLock) {
			return selectPlayerRequest;
		}
	}

	@Override
	public void setSelectedPlayer(Player selectedPlayer) {
		synchronized (selectPlayerLock) {
			this.selectedPlayer = selectedPlayer;
			selectPlayerRequest = false;
			selectPlayerLock.notifyAll();
		}
	}

	@Override
	public Property selectProperty(Player targetPlayer) {
		synchronized (selectPropertyLock) {
			selectPropertyHelper = new SelectPropertyHelper(targetPlayer);
			selectPropertyRequest = true;
			while (selectPropertyRequest) {
				try {
					selectPropertyLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Property property = selectPropertyHelper.getProperty();
			selectPropertyHelper = null;
			return property;
		}
	}
	
	@Override
	public boolean hasSelectPropertyRequest() {
		synchronized (selectPropertyLock) {
			return selectPropertyRequest;
		}
	}

	@Override
	public SelectPropertyHelper getSelectPropertyHelper() {
		synchronized (selectPropertyLock) {
			return selectPropertyHelper;
		}
	}

	@Override
	public Deal dealDialog(Player otherPlayer) {
		synchronized (createDealLock) {
			createDealRequest = true;
			while (createDealRequest) {
				try {
					createDealLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Deal result = createdDeal;
			createdDeal = null;
			return result;
		}
	}

	@Override
	public void setCreatedDeal(Deal deal) {
		synchronized (createDealLock) {
			this.createdDeal = deal;
			createDealRequest = false;
			createDealLock.notifyAll();
		}
	}

	@Override
	public boolean hasCreateDealRequest() {
		synchronized (createDealLock) {
			return createDealRequest;
		}
	}

	@Override
	public boolean yesNoDialog(String message) {
		YesNoDialog dialog = new YesNoDialog(message);
		synchronized (yesNoDialogs) {
			yesNoDialogs.add(dialog);
			synchronized (dialog) {
				while (!dialog.isAnswered()) {
					try {
						dialog.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return dialog.getAnswer();
			}
		}
	}

	@Override
	public boolean hasYesNoDialog() {
		synchronized (yesNoDialogs) {
			return !yesNoDialogs.isEmpty();
		}
	}

	@Override
	public YesNoDialog getYesNoDialog() {
		synchronized (yesNoDialogs) {
			return yesNoDialogs.peek();
		}
	}

	@Override
	public void showMessage(String message) {
		synchronized (messages) {
			messages.add(message);
		}
	}

	@Override
	public String getLastMessage() {
		synchronized (messages) {
			return messages.peek();
		}
	}

	@Override
	public Queue<String> getAllMessages() {
		return messages;
	}

	@Override
	public boolean hasMessage() {
		synchronized (messages) {
			return !messages.isEmpty();
		}
	}

	@Override
	public void showWarning(String message) {
		synchronized (warnings) {
			warnings.add(message);
			showMessage("!!!  " + message + "  !!!");
		}
	}

	@Override
	public String getWarning() {
		synchronized (warnings) {
			return warnings.poll();
		}
	}

	@Override
	public boolean hasWarning() {
		synchronized (warnings) {
			return !warnings.isEmpty();
		}
	}

	public class SelectPropertyHelper {
		private Property property;
		private Player owner;
		
		public SelectPropertyHelper(Player owner) {
			this.owner = owner;
		}
		
		public Player getOwner() {
			return owner;
		}

		public void setProperty(Property property) {
			synchronized (selectPropertyLock) {
				this.property = property;
				selectPropertyRequest = false;
				this.notifyAll();
			}
		}

		public Property getProperty() {
			return property;
		}
	}

	public class YesNoDialog {
		private String message;
		private volatile boolean answer;
		private volatile boolean answered;

		public YesNoDialog(String message) {
			this.message = message;
			answered = false;
		}

		public String getMessage() {
			return message;
		}

		public void setAnswer(boolean answer) {
			synchronized (yesNoDialogs) {
				synchronized (this) {
					this.answer = answer;
					answered = true;
					yesNoDialogs.remove(this);
					this.notifyAll();
				}
			}
		}

		public boolean getAnswer() {
			synchronized (this) {
				return answer;
			}
		}

		public boolean isAnswered() {
			synchronized (this) {
				return answered;
			}
		}
	}

	@Override
	public void showDice() {
		// TODO Auto-generated method stub

	}

	@Override
	public void performAction(final Action action) {
		if (hasAvailableAction(action)) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					action.performAction(player);
				}
			});
		}
	}

	private boolean hasAvailableAction(Action action) {
		List<Action> actions = GameSession.getInstance().getActionController().getAvailableActions(player);
		return actions.contains(action);
	}
}
