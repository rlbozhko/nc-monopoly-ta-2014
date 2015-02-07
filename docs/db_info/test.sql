delete objects;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (1,NULL,1,'Session',NULL);
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (39,1,'132354651',NULL); -- id
  --Board
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (2,1,2,'Board',NULL);
    --Cells
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (31,2,6,'START',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (32,31,10,'START',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (3,2,6,'c1m1',NULL);   
      --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,3,'1000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,3,'100',NULL); -- baseRent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (15,3,'Property 1',NULL); -- description
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,3,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,3,'Monopoly 1',NULL); -- monopoly
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (18,3,'c1m1',NULL); -- name
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,3,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,3,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,3,'2',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,3,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,3,'0',NULL); -- turnsToPayBack
      --
       --Building
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (9,3,5,'Building',NULL);
        -- atr
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (24,9,'1',NULL); -- currentLevel
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (25,9,'400',NULL); -- currentPrice
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (26,9,'descr',NULL); -- description
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (27,9,'5',NULL); -- maxLevel
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (28,9,'name',NULL); -- name
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (29,9,'200',NULL); -- primaryCost
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (4,2,3,'c2m1',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (5,2,6,'RandomEvent',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (24,5,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (25,5,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (26,5,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (27,5,10,'MONEY','-300');
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (28,5,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (29,5,10,'RANDOM_MONEY',NULL);
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (6,2,3,'c3m1',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (7,2,6,'Jail',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (30,7,10,'JAIL',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (8,2,6,'c1m2',NULL);
      --Building
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (34,8,5,'Building',NULL);
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (10,2,6,'c2m2',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (11,2,6,'c3m2',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (12,2,6,'GoToJail',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (33,5,10,'GO_TO_JAIL',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (13,2,6,'c1m3',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (14,2,6,'c2m3',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (15,2,6,'c3m3',NULL);
    --Players
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (16,2,4,'Player 1',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (17,2,4,'Player 2',NULL);
      --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (3,17,'0',NULL); -- doublesCount
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (4,17,'CLEAN',NULL); -- jailStatus
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (5,17,'0',NULL); -- jailTerm
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (6,17,'Player 2',NULL); -- name
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (7,17,'false',NULL); -- offerADeal
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (8,17,'false',NULL); -- payRent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (9,17,'10',NULL); -- position
      --INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (10,17,'0',NULL); -- property_ref
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (11,17,'ACTIVE',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (12,17,'5000',NULL); -- wallet
      -- property_ref
      INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (10,17,3);
      INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (10,17,4);
    ----
    -- Board.currentPlayer
    INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (1,2,17);
-- Users
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (18,NULL,7,'User 1',NULL);
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (35,18,'user1@mail.ua',NULL); -- email
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (36,18,'5423465454',NULL); -- hash
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (37,18,'userName1',NULL); -- name
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (38,18,'1234',NULL); -- password
--
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (100,NULL,7,'User 2',NULL);
--atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (35,100,'user2@mail.ua',NULL); -- email
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (36,100,'6623453454',NULL); -- hash
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (37,100,'userName2',NULL); -- name
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (38,100,'541234',NULL); -- password
  --
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (19,NULL,7,'User 2',NULL);
    
  -- UserIOMap  
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (22,1,8,'User 1 - IO 1',NULL);
  --atr
    INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (30,22,18); --user_ref
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (23,1,8,'User 2 - IO 2',NULL);
  --atr
    INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (30,23,100); --user_ref
  --IOs
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (20,22,9,'IO 1',NULL);    
      --messages
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (50,20,11,'message','message text 1');
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (51,20,11,'message','message text 2');
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (52,20,11,'message','message text 3');
      --warnings
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (53,20,11,'warning','warning text 1');
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (54,20,11,'warning','warning text 2');
      --player_ref
      INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (32,20,17);
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (21,23,9,'IO 2',NULL);
  
  
    
    
