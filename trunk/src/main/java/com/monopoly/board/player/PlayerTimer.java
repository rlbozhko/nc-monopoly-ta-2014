package com.monopoly.board.player;

import java.util.Timer;
import java.util.TimerTask;

import com.monopoly.action.ActionUtils;
import com.monopoly.action.FinishGameAction;

public class PlayerTimer {
	private static final int START_TIME = 60000;
	private static final int MAX_TIME = 60000;
	private static final int ADDIONAL_TIME = 20000;
	private volatile long timerEnd;
	private Player player;
	private Timer timer;
	private TimerTask task;
	private boolean started;

	public PlayerTimer(Player player) {
		this.player = player;
		timer = new Timer(true);
	}

	public void start() {
		timerEnd = System.currentTimeMillis() + START_TIME;
		started = true;
		task = new TimerTask() {
			@Override
			public void run() {
				if (!hasRamainingTime()) {
					new FinishGameAction().performAction(player);
					ActionUtils.getPlayerIO(player).showWarning("Ваше время истекло. Вы проиграли");
					ActionUtils.sendMessageToAll(player.getName() + " выбыл из игры");
					reset();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 500);
	}

	public void addTime() {
		long newTime = timerEnd + ADDIONAL_TIME;
		if ((newTime - System.currentTimeMillis()) > MAX_TIME) {
			timerEnd = System.currentTimeMillis() + MAX_TIME;
		} else {
			timerEnd = newTime;
		}
	}

	public void reset() {
		timer.cancel();
		started = false;
		timer = new Timer(true);
	}

	public long getRemainingTime() {
		return timerEnd - System.currentTimeMillis();
	}

	public boolean hasRamainingTime() {
		return timerEnd >= System.currentTimeMillis();
	}

	public static void main(String[] args) throws InterruptedException {
		PlayerTimer playerTimer = new PlayerTimer(new Player("Player"));
		playerTimer.start();
		System.out.println("First Timer started");
		playerTimer.addTime();
		while (playerTimer.getRemainingTime() > 55000) {
			Thread.sleep(1000);
			System.out.println(playerTimer.getRemainingTime());
		}

		playerTimer.reset();
		playerTimer.start();
		System.out.println("New Timer started");
		while (playerTimer.isStarted()) {
			Thread.sleep(1000);
			System.out.println(playerTimer.getRemainingTime());
		}
	}

	public boolean isStarted() {
		return started;
	}
}
