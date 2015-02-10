package com.monopoly.io;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.deal.Deal;
import com.monopoly.board.cells.Property;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

public class WebIO implements IO {
	private Player player;

	private volatile boolean selectPlayerRequest;
	private volatile boolean processSelectPlayerRequest;
	private Object selectPlayerLock = new Object();
	private Player selectedPlayer;

	private volatile boolean selectPropertyRequest;	
	private Object selectPropertyLock = new Object();
	private SelectPropertyHelper selectPropertyHelper;

	private volatile boolean createDealRequest;	
	private Object createDealLock = new Object();
	private Player dealTarget;
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
			while (processSelectPlayerRequest) {
				try {
					selectPlayerLock.wait();
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
			processSelectPlayerRequest = true;
			selectPlayerRequest = true;
			while (selectPlayerRequest) {
				try {
					selectPlayerLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Player result = selectedPlayer;
			selectedPlayer = null;
			selectPlayerLock.notifyAll();
			processSelectPlayerRequest = false;
			return result;
		}
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
			while (selectPropertyHelper != null) {
				try {
					selectPropertyLock.wait();
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
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
			selectPropertyLock.notifyAll();
			
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
			while (dealTarget != null) {
				try {
					createDealLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			createDealRequest = true;
			dealTarget = otherPlayer;
			while (createDealRequest) {
				try {
					createDealLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Deal result = createdDeal;
			createdDeal = null;
			dealTarget = null;
			createDealLock.notifyAll();
			return result;
		}
	}

	@Override
	public Player getDealTarget() {
		synchronized (createDealLock) {
			return dealTarget;
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
		}
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
				selectPlayerLock.notifyAll();
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
		Dice dice = Dice.getInstance();
		ActionUtils.sendMessageToAll(player.getName() + " бросил кости: " + dice.getFaceDie1() + " "
				+ dice.getFaceDie2());
	}

	@Override
	public void performAction(final ActionType actionType) {
		if (hasAvailableAction(actionType)) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					actionType.create().performAction(player);
				}
			}).start();
		}
		System.out.println("IO.performAction(" + actionType + ")");
	}

	private boolean hasAvailableAction(ActionType actionType) {
		List<ActionType> actions = GameSession.getInstance().getActionController().getAvailableActions(player);
		return actions.contains(actionType);
	}
}
