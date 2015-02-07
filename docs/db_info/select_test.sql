--session
select ses.name, id_atr.value id from objects ses, attributes id_atr
where ses.object_type_id = 1 and
  id_atr.attr_id = 39 and
  id_atr.value = '132354651' and
  ses.object_id = id_atr.object_id;
--board
select ses.name id, board.name board 
from objects ses, attributes id_atr, objects board
where ses.object_type_id = 1 and
  id_atr.attr_id = 39 and
  id_atr.value = '132354651' and
  ses.object_id = id_atr.object_id and
  board.parent_id = ses.object_id and
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
select users.name user_obj_name, u_email.value email, u_hash.value "hash",
  u_name.value "name",u_password.value "password"
from objects users, attributes u_name, attributes u_email,attributes u_password,
  attributes u_hash
where users.object_type_id = 7 and
  u_email.attr_id = 35 and
  u_hash.attr_id = 36 and
  u_name.attr_id = 37 and
  u_password.attr_id = 38 and 
  u_email.object_id = users.object_id and
  u_hash.object_id = users.object_id and
  u_name.object_id = users.object_id and
  u_password.object_id = users.object_id;
  
