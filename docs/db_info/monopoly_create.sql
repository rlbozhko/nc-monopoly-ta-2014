/* 

Пример реализации реляционной модели 
EAV/CR – Entity-Attribute-Value with Classes and Relationships 
(Сущность-Атрибут-Значение с Классами и Отношениями)." 

*/

drop table OBJTYPE CASCADE CONSTRAINTS;
drop table ATTRTYPE CASCADE CONSTRAINTS;
drop table OBJECTS CASCADE CONSTRAINTS;
drop table ATTRIBUTES CASCADE CONSTRAINTS;
drop table OBJREFERENCE CASCADE CONSTRAINTS;

-- Таблица описаний объектных типов
CREATE TABLE OBJTYPE
  (
    OBJECT_TYPE_ID NUMBER(20) NOT NULL ENABLE,
    PARENT_ID      NUMBER(20),
    CODE           VARCHAR2(20) NOT NULL UNIQUE,
    NAME           VARCHAR2(200 BYTE),
    DESCRIPTION    VARCHAR2(1000 BYTE),
    CONSTRAINT CON_OBJECT_TYPE_ID PRIMARY KEY (OBJECT_TYPE_ID),
    CONSTRAINT CON_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ON DELETE CASCADE ENABLE
  );

COMMENT ON TABLE OBJTYPE IS 'Таблица описаний объектных типов'; 
COMMENT ON COLUMN OBJTYPE.OBJECT_TYPE_ID IS 'Идентификатор объектного типа';
COMMENT ON COLUMN OBJTYPE.PARENT_ID IS 'ссылка на идентификатор родительского объектного типа';
COMMENT ON COLUMN OBJTYPE.CODE IS 'название объектного типа в английской кодировке';
COMMENT ON COLUMN OBJTYPE.NAME IS 'название объектного типа в национальной кодировке (для GUI)';
COMMENT ON COLUMN OBJTYPE.DESCRIPTION IS 'разверное описание объектного типа в национальной кодировке (для GUI)';


/* 
При переходе от UML-диаграмме к EAV-модели рекомендуется:
1) каждый класс - это строка в таблице OBJTYPE с именем класса в колонке OBJTYPE.NAME
2) связь типа "обобщение" между классами можно сохранить в колонки OBJTYPE.OBJECT_TYPE_ID и OBJTYPE.PARENT_ID,
где OBJTYPE.OBJECT_TYPE_ID - идентификатор класса-наследника, OBJTYPE.PARENT_ID - идентификатор класса-родителя
3) связь типа "агрегатная ассоциация" между классами можно сохранить в колонки OBJTYPE.OBJECT_TYPE_ID и OBJTYPE.PARENT_ID,
где OBJTYPE.OBJECT_TYPE_ID - идентификатор класса типа "частное", OBJTYPE.PARENT_ID - идентификатор класса типа "целое"

*/
-- Таблица описаний атрибутных типов
CREATE TABLE ATTRTYPE (
    ATTR_ID      		NUMBER(20) NOT NULL ENABLE,
    OBJECT_TYPE_ID 		NUMBER(20) NOT NULL ENABLE,
	OBJECT_TYPE_ID_REF 	NUMBER(20),
    CODE         		VARCHAR2(20),
    NAME         		VARCHAR2(200 BYTE),
    CONSTRAINT CON_ATTR_ID PRIMARY KEY (ATTR_ID),
    CONSTRAINT CON_ATTR_OBJECT_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ENABLE,
	CONSTRAINT CON_ATTR_OBJECT_TYPE_ID_REF FOREIGN KEY (OBJECT_TYPE_ID_REF) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ENABLE
);
 
COMMENT ON TABLE ATTRTYPE 
	IS 'Таблица описаний атрибутных типов';
COMMENT ON COLUMN ATTRTYPE.OBJECT_TYPE_ID
	IS 'ссылка на идентификатор объектного типа класса, который характеризует данный атрибутный тип';
COMMENT ON COLUMN ATTRTYPE.OBJECT_TYPE_ID_REF 
	IS 'ссылка на идентификатор объектного типа класса, который может находтся в любой кратности с объектным типом класса из ATTRTYPE.OBJECT_TYPE_ID';
COMMENT ON COLUMN ATTRTYPE.CODE 
	IS 'название атрибутного типа в английской кодировке';
COMMENT ON COLUMN ATTRTYPE.NAME 
	IS 'название атрибутного типа в национальной кодировке (для GUI)';

-- Таблица описаний экземпляров объектов
CREATE TABLE OBJECTS (
    OBJECT_ID      NUMBER(20) NOT NULL ENABLE,
    PARENT_ID      NUMBER(20),
    OBJECT_TYPE_ID NUMBER(20) NOT NULL ENABLE,
    NAME           VARCHAR2(2000 BYTE),
    DESCRIPTION    VARCHAR2(4000 BYTE),
    CONSTRAINT CON_OBJECTS_ID PRIMARY KEY (OBJECT_ID),
    CONSTRAINT CON_PARENTS_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE DEFERRABLE ENABLE,
    CONSTRAINT CON_OBJ_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ENABLE
);

COMMENT ON TABLE OBJECTS IS 'Таблица описаний экземпляров объектов';

-- Таблица описаний значений атрибутов экземпляров объектов
CREATE TABLE ATTRIBUTES
  (
    ATTR_ID    NUMBER(20) NOT NULL ENABLE,
    OBJECT_ID  NUMBER(20) NOT NULL ENABLE,
    VALUE      VARCHAR2(4000 BYTE),
    DATE_VALUE DATE,
	CONSTRAINT CON_ATTRIBUTES_PK PRIMARY KEY (ATTR_ID,OBJECT_ID),
    CONSTRAINT CON_AOBJECT_ID FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE ENABLE,
    CONSTRAINT CON_AATTR_ID FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID) ON DELETE CASCADE ENABLE
  );  

COMMENT ON TABLE ATTRIBUTES IS 'Таблица описаний атрибутов экземпляров объектов';
COMMENT ON COLUMN ATTRIBUTES.VALUE IS 'Значение атрибута экземпляра объекта в виде строки или числа';
COMMENT ON COLUMN ATTRIBUTES.DATE_VALUE IS 'Значение атрибута экземпляра объекта в виде даты';

-- Таблица описаний связей "простая ассоциация" между экземплярами объектов
CREATE TABLE OBJREFERENCE
  (
    ATTR_ID   NUMBER(20) NOT NULL ENABLE,
    REFERENCE NUMBER(20) NOT NULL ENABLE,
    OBJECT_ID NUMBER(20) NOT NULL ENABLE,
	CONSTRAINT CON_OBJREFERENCE_PK PRIMARY KEY (ATTR_ID,REFERENCE,OBJECT_ID),
    CONSTRAINT CON_REFERENCE FOREIGN KEY (REFERENCE) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE ENABLE,
    CONSTRAINT CON_ROBJECT_ID FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE ENABLE,
    CONSTRAINT CON_RATTR_ID FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID) ON DELETE CASCADE ENABLE
  ); 

COMMENT ON TABLE OBJREFERENCE IS 'Таблица описаний связей между экземплярами объектов';
COMMENT ON COLUMN OBJREFERENCE.ATTR_ID IS 'ссылка на атрибутный тип как ассоциативная связь между экземплярами объектов';
COMMENT ON COLUMN OBJREFERENCE.OBJECT_ID IS 'ссылка на экземпляр 1-го объекта ассоциативной связи';
COMMENT ON COLUMN OBJREFERENCE.REFERENCE IS 'ссылка на экземпляр 2-го объекта ассоциативной связи';

--------------------------------------------------------------------
--------------------------------------------------------------------
---- OBJTYPE
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, CODE, NAME) VALUES ('1', 'GameSession', 'GameSession');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('2', '1', 'Board', 'Board');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('4', '2', 'Player', 'Player');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('3', '2', 'PropertyCell', 'PropertyCell');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('5', '3', 'Building', 'Building');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('6', '2', 'EventCell', 'EventCell');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, CODE, NAME) VALUES ('7', 'User', 'User');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('8', '1', 'UserIOMap', 'UserIO');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('9', '8', 'WebIO', 'WebIO');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('10', '6', 'Event', 'Event');
INSERT INTO "MONOPOLY"."OBJTYPE" (OBJECT_TYPE_ID, PARENT_ID, CODE, NAME) VALUES ('11', '9', 'Message', 'Message');
----
---- ATTRTYPE
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE, NAME) 
  VALUES ('1', '2', '4', 'currentPlayer', 'currentPlayer');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('2', '6', 'position', 'position');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('3', '4', 'doublesCount', 'doublesCount');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('4', '4', 'jailStatus', 'jailStatus');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('5', '4', 'jailTerm', 'jailTerm');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('6', '4', 'name', 'name');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('7', '4', 'offerADeal', 'offerADeal');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('8', '4', 'payRent', 'payRent');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('9', '4', 'position', 'position');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE, NAME) 
  VALUES ('10', '4', '3', 'property_ref', 'property_ref');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('11', '4', 'status', 'status');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('12', '4', 'wallet', 'wallet');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('13', '3', 'basePrice', 'basePrice');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('14', '3', 'baseRent', 'baseRent');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('15', '3', 'description', 'description');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('16', '3', 'maxLevel', 'maxLevel');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('17', '3', 'monopoly', 'monopoly');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('18', '3', 'name', 'name');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('19', '3', 'payBackMoney', 'payBackMoney');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('20', '3', 'pledgePercent', 'pledgePercent');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('21', '3', 'position', 'position');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('22', '3', 'status', 'status');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('23', '3', 'turnsToPayBack', 'turnsToPayBack');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('24', '5', 'currentLevel', 'currentLevel');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('25', '5', 'currentPrice', 'currentPrice');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('26', '5', 'description', 'description');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('27', '5', 'maxLevel', 'maxLevel');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('28', '5', 'name', 'name');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('29', '5', 'primaryCost', 'primaryCost');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE, NAME) 
  VALUES ('30', '8', '7', 'user_ref', 'user_ref');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('31', '9', 'message', 'message');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE, NAME) 
  VALUES ('32', '9', '4', 'player_ref', 'player_ref');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('33', '9', 'warning', 'warning');
--INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('34', '10', 'value', 'value');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('35', '7', 'email', 'email');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('36', '7', 'hash', 'hash');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('37', '7', 'name', 'name');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('38', '7', 'password', 'password');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('39', '1', 'id', 'id');
INSERT INTO "MONOPOLY"."ATTRTYPE" (ATTR_ID, OBJECT_TYPE_ID, CODE, NAME) VALUES ('40', '11', 'value', 'value');

commit;