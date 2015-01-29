package com.monopoly.io;

import java.util.LinkedList;
import java.util.Queue;

import com.monopoly.action.deal.Deal;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;

public class WebIO implements IO {
	private Player player;	

	private boolean selectPlayerRequest;	
	private Object selectPlayerLock = new Object();
	private Player selectedPlayer;

	private boolean selectPropertyRequest;
	private Object selectPropertyLock = new Object();
	private SelectPropertyHelper selectPropertyHelper;

	private boolean createDealRequest;
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
	public Property selectProperty(Player player) {
		synchronized (selectPropertyLock) {
			selectPropertyHelper = new SelectPropertyHelper();
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
	public boolean hasMessages() {
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

		public Player getPlayer() {
			return player;
		}

		public void setProperty(Property property) {
			synchronized (this) {
				this.property = property;
				selectPlayerRequest = false;
				this.notifyAll();
			}
		}

		public Property getProperty() {
			return property;
		}
	}

	public class YesNoDialog {
		private String message;
		private boolean answer;
		private boolean answered;

		public YesNoDialog(String message) {
			this.message = message;
			answered = false;
		}

		public String getMessage() {
			return message;
		}

		public void setAnswer(boolean answer) {
			synchronized (yesNoDialogs) {
				this.answer = answer;
				answered = true;
				yesNoDialogs.remove(this);
				this.notifyAll();
			}
		}

		public boolean getAnswer() {
			synchronized (yesNoDialogs) {
				return answer;
			}
		}

		public boolean isAnswered() {
			synchronized (yesNoDialogs) {
				return answered;
			}
		}
	}

	@Override
	public void showDice() {
		// TODO Auto-generated method stub
		
	}
}
