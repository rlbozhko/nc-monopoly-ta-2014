delete objects;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (1,NULL,1,'Session',NULL);
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (39,1,'132354651',NULL); -- id
  --Board
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (2,1,2,'Board',NULL);
    --Cells
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (31,2,6,'START',NULL);
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,31,'0',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (32,31,10,'START',NULL);      
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (3,2,3,'c1m1','c1m1 descr');   
      --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,3,'1000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,3,'100',NULL); -- baseRent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (15,3,'Property 1',NULL); -- description
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,3,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,3,'MONOPOLY1',NULL); -- monopoly
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (18,3,'c1m1',NULL); -- name
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,3,'1000',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,3,'0.10',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,3,'2',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,3,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,3,'5',NULL); -- turnsToPayBack
      --
       --Building
     INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (9,3,5,'CASTLE','Description');
        -- atr
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (24,9,'1',NULL); -- currentLevel
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (25,9,'400',NULL); -- currentPrice
       -- INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (26,9,'descr',NULL); -- description
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (27,9,'5',NULL); -- maxLevel
       -- INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (28,9,'name',NULL); -- name
        INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (29,9,'200',NULL); -- primaryCost
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (4,2,3,'c2m1',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (5,2,6,'RandomEvent',NULL);
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,5,'5',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (24,5,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (25,5,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (26,5,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (27,5,10,'MONEY','-300');
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (28,5,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (29,5,10,'RANDOM_MONEY',NULL);
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (6,2,3,'c3m1',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (7,2,6,'Jail',NULL);
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,7,'10',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (30,7,10,'JAIL',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (8,2,3,'c1m2',NULL);
      --Building
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (34,8,5,'CASTLE',NULL);
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (10,2,3,'c2m2',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (11,2,3,'c3m2',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (12,2,6,'GoToJail',NULL);
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,12,'0',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (33,5,10,'GO_TO_JAIL',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (13,2,3,'c1m3',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (14,2,3,'c2m3',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (15,2,3,'c3m3',NULL);
    --Players
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (16,2,4,'Player 1',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (17,2,4,'Player 2',NULL);
      --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (3,17,'0',NULL); -- doublesCount
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (4,17,'CLEAN',NULL); -- jailStatus
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (5,17,'0',NULL); -- jailTerm
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (6,17,'false',NULL); -- extraTurn
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (7,17,'false',NULL); -- offerADeal
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (8,17,'false',NULL); -- payRent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (9,17,'10',NULL); -- position      
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
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (99,NULL,7,'User 2',NULL);
--atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (35,99,'user2@mail.ua',NULL); -- email
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (36,99,'6623453454',NULL); -- hash
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (37,99,'userName2',NULL); -- name
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (38,99,'541234',NULL); -- password
  --
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (19,NULL,7,'User 2',NULL);
    
  -- UserIOMap  
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (22,1,8,'User 1 - IO 1',NULL);
  --atr
    INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (30,22,18); --user_ref
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (23,1,8,'User 2 - IO 2',NULL);
  --atr
    INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (30,23,99); --user_ref
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

--------------------------------------------------------------
-- TEST Users
--------------------------------------------------------------
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (500,NULL,7,'Petrucho','');
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (35,500,'petya@test.ua',NULL); -- email
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (38,500,'123',NULL); -- password
--
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (501,NULL,7,'Vasya_32','');
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (35,501,'vasya@test.ua',NULL); -- email
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (38,501,'123',NULL); -- password
--
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (502,NULL,7,'Anka iz tanka','');
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (35,502,'anna@test.ua',NULL); -- email
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (38,502,'123',NULL); -- password
--


-----------------------------------------------------------
--New Board
-----------------------------------------------------------

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (100,NULL,1,'New Session',NULL);
  --atr
  INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (39,100,'0',NULL); -- id
  --Board
  INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (101,100,2,'Board','New Board');
    --Cells
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (102,101,6,'Начало','Тут все начинается');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,102,'0',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (150,102,10,'MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (103,101,3,'c1m1','c1m1 desc');   
      --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,103,'1000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,103,'100',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,103,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,103,'MONOPOLY1',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,103,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,103,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,103,'1',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,103,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,103,'0',NULL); -- turnsToPayBack
      --          
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (104,101,6,'Шанс','Случайное событие');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,104,'2',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (151,104,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (152,104,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (153,104,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (154,104,10,'EXTRA_TURN',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (155,104,10,'MOVE',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (156,104,10,'RANDOM_MONEY',NULL);
      --
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (105,101,3,'c2m1','c2m1 desc');
      --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,105,'1000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,105,'100',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,105,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,105,'MONOPOLY1',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,105,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,105,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,105,'3',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,105,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,105,'0',NULL); -- turnsToPayBack
      --          
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (106,101,3,'c3m1','c3m1 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,106,'1000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,106,'100',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,106,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,106,'MONOPOLY1',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,106,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,106,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,106,'4',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,106,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,106,'0',NULL); -- turnsToPayBack
      --          
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (107,101,3,'c1m9','c1m9 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,107,'2000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,107,'250',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,107,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,107,'MONOPOLY9',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,107,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,107,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,107,'5',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,107,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,107,'0',NULL); -- turnsToPayBack
      --          
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (108,101,3,'c1m2','c1m2 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,108,'1500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,108,'300',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,108,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,108,'MONOPOLY2',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,108,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,108,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,108,'6',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,108,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,108,'0',NULL); -- turnsToPayBack
      --          
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (109,101,3,'c2m2','c2m2 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,109,'1500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,109,'300',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,109,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,109,'MONOPOLY2',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,109,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,109,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,109,'7',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,109,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,109,'0',NULL); -- turnsToPayBack
      -- 
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (110,101,6,'Казино','Фортуна может улыбнуться');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,110,'8',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (157,110,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (141,101,3,'c3m2','c3m2 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,141,'1500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,141,'300',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,141,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,141,'MONOPOLY2',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,141,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,141,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,141,'9',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,141,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,141,'0',NULL); -- turnsToPayBack
      -- 
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (111,101,6,'Тюрьма','Это место лучше пройти мимо');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,111,'10',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (158,111,10,'JAIL',null);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (112,101,3,'c1m3','c1m3 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,112,'3000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,112,'300',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,112,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,112,'MONOPOLY3',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,112,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,112,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,112,'11',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,112,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,112,'0',NULL); -- turnsToPayBack
      -- 
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (113,101,3,'c2m3','c2m3 desc');
     --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,113,'3000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,113,'300',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,113,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,113,'MONOPOLY3',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,113,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,113,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,113,'12',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,113,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,113,'0',NULL); -- turnsToPayBack
      -- 
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (114,101,6,'Шанс','Случайное событие');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,114,'13',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (159,114,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (160,114,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (161,114,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (162,114,10,'EXTRA_TURN',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (163,114,10,'MOVE',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (164,114,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (115,101,3,'c3m3','c3m3 desc');
     --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,115,'3000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,115,'300',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,115,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,115,'MONOPOLY3',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,115,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,115,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,115,'14',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,115,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,115,'0',NULL); -- turnsToPayBack
      -- 
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (116,101,3,'c2m9','c3m3 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,116,'2000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,116,'250',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,116,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,116,'MONOPOLY9',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,116,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,116,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,116,'15',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,116,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,116,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (117,101,6,'Шанс','Случайное событие');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,117,'16',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (165,117,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (166,117,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (167,117,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (168,117,10,'EXTRA_TURN',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (169,117,10,'MOVE',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (170,117,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (118,101,3,'c1m4','c1m4 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,118,'3300',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,118,'330',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,118,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,118,'MONOPOLY4',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,118,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,118,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,118,'17',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,118,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,118,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (119,101,6,'Ипподром','Главное поставить на фаворита');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,119,'18',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (171,119,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (120,101,3,'c2m4','c2m4 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,120,'3300',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,120,'330',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,120,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,120,'MONOPOLY4',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,120,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,120,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,120,'19',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,120,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,120,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (121,101,6,'Бесплатная стоянка','Можете передохнуть');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,121,'20',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (172,121,10,'FREE',null);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (122,101,3,'c1m5','c1m5 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,122,'3500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,122,'350',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,122,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,122,'MONOPOLY5',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,122,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,122,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,122,'21',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,122,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,122,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (123,101,6,'Шанс','Случайное событие');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,123,'22',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (173,123,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (174,123,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (175,123,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (176,123,10,'EXTRA_TURN',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (178,123,10,'MOVE',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (179,123,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (124,101,3,'c2m5','c2m5 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,124,'3500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,124,'350',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,124,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,124,'MONOPOLY5',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,124,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,124,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,124,'23',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,124,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,124,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (125,101,6,'Событие Хода','Переместит вперед или назад');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,125,'24',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (180,125,10,'MOVE',null);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (126,101,3,'c3m9','c3m9 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,126,'2000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,126,'250',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,126,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,126,'MONOPOLY9',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,126,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,126,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,126,'25',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,126,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,126,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (127,101,3,'c1m6','c1m6 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,127,'4000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,127,'400',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,127,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,127,'MONOPOLY6',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,127,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,127,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,127,'26',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,127,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,127,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (128,101,6,'Шанс','Случайное событие');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,128,'27',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (181,128,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (182,128,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (183,128,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (184,128,10,'EXTRA_TURN',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (185,128,10,'MOVE',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (186,128,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (129,101,3,'c2m6','c2m6 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,129,'4000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,129,'400',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,129,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,129,'MONOPOLY6',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,129,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,129,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,129,'28',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,129,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,129,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (130,101,3,'c3m6','c3m6 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,130,'4000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,130,'400',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,130,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,130,'MONOPOLY6',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,130,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,130,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,130,'29',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,130,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,130,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (131,101,6,'В Тюрьму','Отправляйтесь в тюрьму');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,131,'30',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (187,131,10,'GO_TO_JAIL',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (132,101,3,'c1m7','c1m7 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,132,'4200',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,132,'420',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,132,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,132,'MONOPOLY7',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,132,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,132,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,132,'31',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,132,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,132,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (133,101,6,'Бильярдная','Тут много азартных игроков');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,133,'32',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (188,133,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (134,101,3,'c2m7','c2m7 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,134,'4200',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,134,'420',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,134,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,134,'MONOPOLY7',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,134,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,134,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,134,'33',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,134,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,134,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (135,101,3,'c3m7','c3m7 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,135,'4200',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,135,'420',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,135,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,135,'MONOPOLY7',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,135,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,135,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,135,'34',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,135,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,135,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (136,101,3,'c4m9','c4m9 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,136,'2000',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,136,'250',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,136,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,136,'MONOPOLY9',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,136,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,136,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,136,'35',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,136,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,136,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (137,101,3,'c1m8','c1m8 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,137,'4500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,137,'450',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,137,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,137,'MONOPOLY8',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,137,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,137,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,137,'36',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,137,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,137,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (138,101,6,'Шанс','Случайное событие');
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (2,138,'37',NULL); -- position
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (189,138,10,'GO_TO_JAIL',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (190,138,10,'RANDOM_MONEY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (191,138,10,'EMERGENCY',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (192,138,10,'EXTRA_TURN',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (193,138,10,'MOVE',NULL);
      INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (194,138,10,'RANDOM_MONEY',NULL);
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (139,101,3,'c2m8','c2m8 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,139,'4500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,139,'450',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,139,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,139,'MONOPOLY8',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,139,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,139,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,139,'38',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,139,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,139,'0',NULL); -- turnsToPayBack
      --  
    INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (140,101,3,'c3m8','c3m8 desc');
    --atr
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (13,140,'4500',NULL); -- basePrice
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (14,140,'450',NULL); -- baseRent      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (16,140,'5',NULL); -- maxLevel
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (17,140,'MONOPOLY8',NULL); -- monopoly      
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (19,140,'0',NULL); -- payBackMoney
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (20,140,'0',NULL); -- pledgePercent
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (21,140,'39',NULL);  -- position
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (22,140,'UNPLEDGED',NULL); -- status
      INSERT INTO attributes (attr_id, object_id, value, date_value) VALUES (23,140,'0',NULL); -- turnsToPayBack
      --  
    
    
    
    
    
    
    
    