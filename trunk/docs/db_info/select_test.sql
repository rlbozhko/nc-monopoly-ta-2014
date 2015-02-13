--session
select ses.object_id, ses.name, ses.description id from objects ses
where ses.object_type_id = 1 and  
  ses.description = '132354651';
--board
select board.object_id id 
from objects board
where   
  board.parent_id = '1' and
  board.object_type_id = 2;
--user_io_map
select ses.name id, user_io_map.name user_io_map, users.name users, ios.name ios
from objects ses, attributes id_atr, objects user_io_map, objects users, 
  objreference user_ref, objects ios
where ses.object_type_id = 1 and
  id_atr.attr_id = 39 and
  id_atr.value = '132354651' and
  ses.object_id = id_atr.object_id and
  user_io_map.parent_id = ses.object_id and
  user_io_map.object_type_id = 8 and
  user_ref.attr_id = 30 and
  user_ref.object_id = user_io_map.object_id and
  users.object_id = user_ref.reference and
  ios.object_type_id = 9 and
  ios.parent_id = user_io_map.object_id;

--users
select users.name name, u_email.value email,
  u_password.value password, users.description "hash"
from objects users, attributes u_email,attributes u_password  
where users.object_type_id = 7 and
  u_email.attr_id = 35 and
  u_password.attr_id = 38 and 
  u_email.object_id = users.object_id and
  u_password.object_id = users.object_id;
----
select users.object_id, users.name name, u_email.value email,
  u_password.value password, users.description "hash"
from objects users, attributes u_email,attributes u_password  
where users.object_type_id = 7 and
  u_email.attr_id = 35 and
  u_password.attr_id = 38 and 
  u_email.object_id = users.object_id and
  u_password.object_id = users.object_id and
  u_email.value = 'petya@test.ua' and --var
  u_password.value = '123'; -- var
----
--update user
update objects
set name = 'new Name' --var
where object_id = 500; --var

update objects
set description = 'new hash' --var
where object_id = 500; --var

update attributes email
set email.value = 'new@email.com' --var
where email.object_id = 500 and --var
  email.attr_id = 35; 

update attributes password
set password.value = '321' --var
where password.object_id = 500 and   --var
  password.attr_id = 38;
--

--delete user
delete from objects where objects.name = 'insert';

select users.object_id, users.name name, u_email.value email,
  u_password.value password, users.description "hash"
from objects users, attributes u_email,attributes u_password  
where users.object_type_id = 7 and
  u_email.attr_id = 35 and
  u_password.attr_id = 38 and 
  u_email.object_id = users.object_id and
  u_password.object_id = users.object_id and
  users.object_id = 500; -- var

--board_event_cells
select event_cells.name event_cell, event.name event, event.description event_value
from objects event_cells, objects event
where       
  event_cells.object_type_id = 6 and
  event_cells.parent_id = 2 and
  event.object_type_id = 10 and
  event.parent_id = event_cells.object_id
  order by event_cell;
--
-- board_event_cells 2
select event_cells.name event_cell, event.name event, 
  event.description event_value, position.value position
from objects event_cells, objects event, attributes position
where       
  event_cells.object_type_id = 6 and
  event_cells.parent_id = 2 and
  event.object_type_id = 10 and
  event.parent_id = event_cells.object_id  and
  position.attr_id = 2 and
  position.object_id = event_cells.object_id
  order by position;
--
-- board_property_cells
select property_cells.name name, basePrice.value basePrice,
  baseRent.value baseRent, description.value description, maxLevel.value maxLevel,
  monopoly.value monopoly, payBackMoney.value payBackMoney,
  pledgePercent.value pledgePercent, position.value position, status.value status,
  turnsToPayBack.value turnsToPayBack
from objects property_cells, attributes basePrice, attributes baseRent, 
  attributes description, attributes maxLevel,attributes monopoly,
  attributes payBackMoney, attributes pledgePercent,
  attributes position, attributes status, attributes turnsToPayBack
where       
  property_cells.object_type_id = 3 and
  property_cells.parent_id = 2 and  
  basePrice.attr_id = 13 and
  basePrice.object_id = property_cells.object_id and
  baseRent.attr_id = 14 and
  baseRent.object_id = property_cells.object_id and
  description.attr_id = 15 and
  description.object_id = property_cells.object_id and
  maxLevel.attr_id = 16 and
  maxLevel.object_id = property_cells.object_id and
  monopoly.attr_id = 17 and
  monopoly.object_id = property_cells.object_id and  
  payBackMoney.attr_id = 19 and
  payBackMoney.object_id = property_cells.object_id and
  pledgePercent.attr_id = 20 and
  pledgePercent.object_id = property_cells.object_id and
  position.attr_id = 21 and
  position.object_id = property_cells.object_id and
  status.attr_id = 22 and
  status.object_id = property_cells.object_id and
  turnsToPayBack.attr_id = 23 and
  turnsToPayBack.object_id = property_cells.object_id 
  order by position;
---
--new board_property_cells
select property_cells.name property_cells
from objects property_cells
where       
  property_cells.object_type_id = 3 and
  property_cells.parent_id = 2;
  
  ---building
select property_cells.object_id property_cell_id, building.name building_type,  
  currentLevel.value currentLevel, currentPrice.value currentPrice, maxLevel.value maxLevel,
  primaryCost.value primaryCost
from objects property_cells, objects building, attributes currentLevel, 
  attributes currentPrice, attributes maxLevel, attributes primaryCost
where       
  property_cells.object_type_id = 3 and
  property_cells.parent_id = 2 and
  building.object_type_id = 5 and
  building.parent_id = property_cells.object_id and 
  currentLevel.attr_id = 24 and
  currentLevel.object_id = building.object_id and
  currentPrice.attr_id = 25 and
  currentPrice.object_id = building.object_id and
  maxLevel.attr_id = 27 and
  maxLevel.object_id = building.object_id and
  primaryCost.attr_id = 29 and
  primaryCost.object_id = building.object_id;

---
--new board_property_cells
select property_cells.object_id object_id, property_cells.name name, basePrice.value basePrice,
  baseRent.value baseRent, property_cells.description description, maxLevel.value maxLevel,
  monopoly.value monopoly, payBackMoney.value payBackMoney,
  pledgePercent.value pledgePercent, position.value position, status.value status,
  turnsToPayBack.value turnsToPayBack
from objects property_cells, attributes basePrice, attributes baseRent, 
  attributes maxLevel,attributes monopoly,
  attributes payBackMoney, attributes pledgePercent,
  attributes position, attributes status, attributes turnsToPayBack
where       
  property_cells.object_type_id = 3 and
  property_cells.parent_id = 101 and  
  basePrice.attr_id = 13 and
  basePrice.object_id = property_cells.object_id and
  baseRent.attr_id = 14 and
  baseRent.object_id = property_cells.object_id and  
  maxLevel.attr_id = 16 and
  maxLevel.object_id = property_cells.object_id and
  monopoly.attr_id = 17 and
  monopoly.object_id = property_cells.object_id and  
  payBackMoney.attr_id = 19 and
  payBackMoney.object_id = property_cells.object_id and
  pledgePercent.attr_id = 20 and
  pledgePercent.object_id = property_cells.object_id and
  position.attr_id = 21 and
  position.object_id = property_cells.object_id and
  status.attr_id = 22 and
  status.object_id = property_cells.object_id and
  turnsToPayBack.attr_id = 23 and
  turnsToPayBack.object_id = property_cells.object_id; 
  order by position;
  
--
--new board_event_cells
select event_cells.object_id, event_cells.name name, event_cells.description description, 
  position.value position
from objects event_cells, attributes position
where       
  event_cells.object_type_id = 6 and 
  event_cells.parent_id = 101 and 
  position.attr_id = 2 and
  position.object_id = event_cells.object_id;  
--
--new board_events
select event_cells.object_id event_cell_id /*del*/, event.name event, 
  event.description event_description
from objects event_cells, objects event
where       
  event_cells.object_type_id = 6 and --del
  event_cells.parent_id = 101 and --var
  event.object_type_id = 10 and
  event.parent_id = event_cells.object_id;
  
--
--new cells_events
select event.name event_type, 
  event.description event_description
from objects event_cells, objects event
where         
  event_cells.object_id = 104 and
  event.object_type_id = 10 and
  event.parent_id = event_cells.object_id;
  
--


select property_cells.name p_name, basePrice.value basePrice,
				  baseRent.value baseRent, property_cells.description description, maxLevel.value maxLevel, 
				  monopoly.value monopoly, payBackMoney.value payBackMoney, 
				  pledgePercent.value pledgePercent, position.value position, status.value status, 
				  turnsToPayBack.value turnsToPayBack 
				from objects property_cells, attributes basePrice, attributes baseRent, 
				  attributes maxLevel,attributes monopoly, 
				  attributes payBackMoney, attributes pledgePercent, 
				  attributes position, attributes status, attributes turnsToPayBack 
				where 
					 property_cells.object_type_id = 3 and	property_cells.parent_id = 101 and
					 basePrice.attr_id = 13 and	basePrice.object_id = property_cells.object_id and
					 baseRent.attr_id = 14 and	baseRent.object_id = property_cells.object_id and
					 maxLevel.attr_id = 16 and	maxLevel.object_id = property_cells.object_id and
					 monopoly.attr_id = 17 and	monopoly.object_id = property_cells.object_id and
					 payBackMoney.attr_id = 19 and	payBackMoney.object_id = property_cells.object_id and
					 pledgePercent.attr_id = 20 and	pledgePercent.object_id = property_cells.object_id and
					 position.attr_id = 21 and	position.object_id = property_cells.object_id and
					 status.attr_id = 22 and	status.object_id = property_cells.object_id and
					 turnsToPayBack.attr_id = 23 and	turnsToPayBack.object_id = property_cells.object_id;
           
----
-- Player
----

select player.object_id, player.name name, doublesCount.value doublesCount,
  jailStatus.value jailStatus, jailTerm.value jailTerm,
  extraTurn.value extraTurn, offerADeal.value offerADeal,
  payRent.value payRent, position.value position, status.value status,
  wallet.value wallet, player.description playerColor
from objects player, attributes doublesCount, attributes jailStatus,
  attributes jailTerm,attributes extraTurn,attributes offerADeal,
  attributes payRent,attributes position,attributes status,
  attributes wallet
where         
  player.object_type_id = 4 and  
  player.parent_id = 2 and -- var
  doublesCount.attr_id = 3 and	doublesCount.object_id = player.object_id and
  jailStatus.attr_id = 4 and	jailStatus.object_id = player.object_id and
  jailTerm.attr_id = 5 and	jailTerm.object_id = player.object_id and
  extraTurn.attr_id = 6 and	extraTurn.object_id = player.object_id and
  offerADeal.attr_id = 7 and	offerADeal.object_id = player.object_id and
  payRent.attr_id = 8 and	payRent.object_id = player.object_id and
  position.attr_id = 9 and	position.object_id = player.object_id and
  status.attr_id = 11 and	status.object_id = player.object_id and
  wallet.attr_id = 12 and	wallet.object_id = player.object_id;
  
  
  
  
  ;
  
  
  
  
  
  
  
