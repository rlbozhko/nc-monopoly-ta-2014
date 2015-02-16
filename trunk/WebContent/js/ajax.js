var currentTimer;
var currentButtons;
var currentChat;
var currentBoard;
var currentPlayerInfo;
var isDialogVisible;
var isWarningVisible;
var isSelectPropertyVisible;
var isDealVisible;
var isSelectPlayerVisible;

function updateTimer() {
	$.ajax({
		url : "game_timer.action",
		type : "GET",
		ifModified : true,
		success : function(data, status, jqXHR) {
			if (jqXHR.status != '204') {
				if (data != currentTimer) {
					currentTimer = data;
					$("#timer").html("<p>Осталось времени: " + data + "</p>");					
				}
			}
		}
	})
};

function updateButtons() {
	$.ajax({
		url : "game_buttons.action",
		type : "GET",
		ifModified : true,
		success : function(data, status, jqXHR) {
			if (jqXHR.status != '204') {
				if (data != currentButtons) {
					currentButtons = data;
					$("#buttons").html(data);
					// alert("inside buttons");
				}
			}
		}
	})
};

function updateChat() {
	$.ajax({
		url : "game_chat.action",
		type : "GET",
		ifModified : true,
		success : function(data, status, jqXHR) {
			if (jqXHR.status != '204') {
				if (data != currentChat) {
					currentChat = data;
					$("#chatbox").html(data);
					chatHover();
				}
			}
		}
	})
};

function chatHover() {
	if (!$('#chatbox').is(":hover")) {
		document.getElementById("chatbox").scrollTop = document
				.getElementById("chatbox").scrollHeight;
	}
}

function updateBoard() {
	$.ajax({
		url : "game_board.action",
		type : "GET",
		data : "URL",
		success : function(data, status, jqXHR) {
			if (jqXHR.status != '204') {
				if (data != currentBoard) {
					currentBoard = data;
					$("#board").html(data);
				}
			} else {

			}
		}
	})
};

function updateJoinedPlayers() {
	$.ajax({
		url : "join_game_players.action",
		type : "GET",
		data : "URL",
		success : function(data, status, jqXHR) {
			if (jqXHR.status != '204') {
				if (data != currentBoard) {
					currentBoard = data;
					$("#joined_players").html(data);
				}
			} else {

			}
		}

	})
};

function updatePlayerInfo() {
	$.ajax({
		url : "game_player_info.action",
		type : "GET",
		data : "URL",
		success : function(data, status, jqXHR) {
			if (jqXHR.status != '204') {
				if (data != currentPlayerInfo) {
					currentBoard = data;
					$("#player_info").html(data);
				}
			} else {

			}
		}

	})
};

function performAction(actionType) {
	$.get("a_action_type.action?actionType=" + actionType, function(data) {
		return false;
	});
};

function updateYesNoDialog() {
	if (!isDialogVisible) {
		$.ajax({
			url : "game_dialog.action",
			type : "GET",
			ifModified : true,
			success : function(data, status, jqXHR) {
				if (jqXHR.status != '204') {
					isDialogVisible = true;
					$("#yn_dialog").html(data);
					$("#yn_dialog").show();
				} else {
					// $("#yn_dialog").html("<p>No YN Dialog</p>");
				}
			}

		});
	}
};

function yesNoDialog(answer) {
	$.ajax({
		url : "a_dialog.action",
		type : "GET",
		ifModified : true,
		data : {
			"isAnswer" : answer
		},
		success : function(data, status) {
			$("#yn_dialog").hide("fast", function() {
				$("#yn_dialog").html("");
			})
			isDialogVisible = false;
			return false;
		}
	})
};

function updateWarning() {
	if (!isWarningVisible) {
		$.ajax({
			url : "game_warning.action",
			type : "GET",
			ifModified : true,
			success : function(data, status, jqXHR) {
				if (jqXHR.status != 204) {
					isWarningVisible = true;
					$("#warning").html(data);
					$("#warning").show();
				} else {
					// $("#warning").html("<p>No Warning</p>");
				}
			}
		});
	}
};

function okWarning() {
	$("#warning").hide(100, function() {
		$("#warning").html("");
	});
	$("#warning").html("");
	isWarningVisible = false;
};

function selectProperty(accept) {
	propertyId = $("#select_property_choice").val();
	$.ajax({
		url : "a_select_property.action",
		type : "GET",
		ifModified : true,
		data : {
			"isAccept" : accept,
			"propertyId" : propertyId
		},
		success : function(data, status) {
			$("#select_property").hide(0, function() {
				$("#select_property").html("");
			});
			isSelectPropertyVisible = false;
		}
	})
};

function updateSelectProperty() {
	if (!isSelectPropertyVisible) {
		$.ajax({
			url : "game_select_property.action",
			type : "GET",
			ifModified : true,
			success : function(data, status, jqXHR) {
				if (jqXHR.status != '204') {
					isSelectPropertyVisible = true;
					$("#select_property").html(data);
					$("#select_property").show();
				} else {
					// $("#select_property").html("<p>No Select Property</p>");
				}
			}
		});
	}
};

function createDeal(accept, dealTargetName) {
	askMoney = $("input[name=askMoney]").serialize();
	giveMoney = $("input[name=giveMoney]").serialize();
	askPropertiesIDs = $("select[name=askPropertiesIDs]").serialize();
	givePropertiesIDs = $("select[name=givePropertiesIDs]").serialize();
	$("#test").html(deal);

	$.ajax({
		url : "a_deal_target.action",
		type : "GET",
		ifModified : true,
		data : askPropertiesIDs + "&" + givePropertiesIDs + "&" + askMoney
				+ "&" + giveMoney + "&dealTargetName=" + dealTargetName
				+ "&isAccept=" + accept,
		processData : false,
		success : function(data, status) {
			$("#deal").hide("fast", function() {
				$("#deal").html("");
			});
			isDealVisible = false;			
			return false;
		}
	})
};

function updateDeal() {
	if (!isDealVisible) {
		$.ajax({
			url : "game_deal.action",
			type : "GET",
			ifModified : true,
			success : function(data, status, jqXHR) {
				if (jqXHR.status != '204') {
					isDealVisible = true;
					$("#deal").html(data);
					$("#deal").show();
				} else {
					// $("#deal").html("<p>No Deal</p>");
				}
			}

		});
	}
};

function selectPlayer(accept) {
	selectedPlayerName = $("select[name=selectedPlayerName]").val();
	$.ajax({
		url : "a_select_player.action",
		type : "GET",
		ifModified : true,
		data : {
			"isAccept" : accept,
			"selectedPlayerName" : selectedPlayerName
		},
		success : function(data, status) {
			$("#select_player").hide("fast", function() {
				$("#select_player").html("");
			});
			isSelectPlayerVisible = false;
			return false;
		}
	})
};

function updateSelectPlayer() {
	if (!isSelectPlayerVisible) {
		$.ajax({
			url : "game_select_player.action",
			type : "GET",
			ifModified : true,
			success : function(data, status, jqXHR) {
				if (jqXHR.status != '204') {
					isSelectPlayerVisible = true;
					$("#select_player").html(data);
					$("#select_player").show();
				} else {
					// $("#select_player").html("<p>No Select Player</p>");
				}
			}
		});
	}
};

function checkIndexStatus() {
	$.ajax({
		url : "a_status_check.action",
		type : "GET",
		ifModified : true,
		success : function(data, status, jqXHR) {
			if (data == "CREATING") {
				window.location.replace("join_game.action");
			}
		},
		error : function() {
			window.location.replace("signin.action");
		}
	});
};

function checkJoinStatus() {
	$.ajax({
		url : "a_status_check.action",
		type : "GET",
		ifModified : true,
		success : function(data, status, jqXHR) {
			if (data == "RUN") {
				window.location.replace("game_test.action");
			} else if (data == "NOT_EXISTS") {
				window.location.replace("index.action");
			}
		},
		error : function() {
			window.location.replace("signin.action");
		}
	});
};

function checkGameStatus() {
	$.ajax({
		url : "a_status_check.action",
		type : "GET",
		ifModified : true,
		success : function(data, status, jqXHR) {
			if (data == "CREATING") {
				window.location.replace("join_game.action");
			}
			// else if (data == "NOT_EXISTS") {
			// window.location.replace("index.action");
			// }
		},
		error : function() {
			window.location.replace("signin.action");
		}
	});
};
