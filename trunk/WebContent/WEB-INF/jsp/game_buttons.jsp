<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div>
	<button onclick="performAction('START_TURN')"
		<c:if test="${!strActions.contains('START_TURN')}">disabled="disabled"</c:if>>Ходить</button>
	<button onclick="performAction('END_TURN')"
		<c:if test="${!strActions.contains('END_TURN')}">disabled="disabled"</c:if>>Закончить
		Ход</button>
	<button onclick="performAction('BUY_PROPERTY')"
		<c:if test="${!strActions.contains('BUY_PROPERTY')}">disabled="disabled"</c:if>>Купить</button>
	<button onclick="performAction('DEAL')"
		<c:if test="${!strActions.contains('DEAL')}">disabled="disabled"</c:if>>Сделка</button>
	<button onclick="performAction('FINISH_GAME')"
		<c:if test="${!strActions.contains('FINISH_GAME')}">disabled="disabled"</c:if>>Сдаться</button>
	<button onclick="performAction('PAY_RENT')"
		<c:if test="${!strActions.contains('PAY_RENT')}">disabled="disabled"</c:if>>Уплатить
		Аренду</button>
	<button onclick="performAction('PLEDGE_PROPERTY')"
		<c:if test="${!strActions.contains('PLEDGE_PROPERTY')}">disabled="disabled"</c:if>>Заложить
		Собственность</button>
	<button onclick="performAction('PAY_BACK')"
		<c:if test="${!strActions.contains('PAY_BACK')}">disabled="disabled"</c:if>>Выкупить
		Собственность</button>
	<button onclick="performAction('BUILD')"
		<c:if test="${!strActions.contains('BUILD')}">disabled="disabled"</c:if>>Построить</button>
	<button onclick="performAction('UPGRADE_BUILDING')"
		<c:if test="${!strActions.contains('UPGRADE_BUILDING')}">disabled="disabled"</c:if>>Поднять
		Уровень Здания</button>
	<button onclick="performAction('SELL_BUILDING')"
		<c:if test="${!strActions.contains('SELL_BUILDING')}">disabled="disabled"</c:if>>Опустить
		Уровень Здания</button>
	<button onclick="performAction('BETRAYAL')"
		<c:if test="${!strActions.contains('BETRAYAL')}">disabled="disabled"</c:if>>Сдать
		Беглеца</button>

	<c:if test="${player.isJailed()}">
		<div id="jailActions">
			<button onclick="performAction('ESCAPE')"
				<c:if test="${!strActions.contains('ESCAPE')}">disabled="disabled"</c:if>>Сбежать</button>
			<button onclick="performAction('PAY_BAIL')"
				<c:if test="${!strActions.contains('PAY_BAIL')}">disabled="disabled"</c:if>>Заплатить
				залог</button>
			<button onclick="performAction('SERVE_JAIL_TERM')"
				<c:if test="${!strActions.contains('SERVE_JAIL_TERM')}">disabled="disabled"</c:if>>Отсидеть</button>
		</div>
	</c:if>

	<c:if test="${activePlayers.size() == 0 }">
		<form action="game_over.action" method="get">
			<button type="submit" value="GAME_OVER">Закрыть Сессию</button>
		</form>
	</c:if>
</div>

