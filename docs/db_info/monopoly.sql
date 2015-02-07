/* 

������ ���������� ����������� ������ 
EAV/CR � Entity-Attribute-Value with Classes and Relationships 
(��������-�������-�������� � �������� � �����������)." 

*/

drop table OBJTYPE CASCADE CONSTRAINTS;
drop table ATTRTYPE CASCADE CONSTRAINTS;
drop table OBJECTS CASCADE CONSTRAINTS;
drop table ATTRIBUTES CASCADE CONSTRAINTS;
drop table OBJREFERENCE CASCADE CONSTRAINTS;

-- ������� �������� ��������� �����
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

COMMENT ON TABLE OBJTYPE IS '������� �������� ��������� �����'; 
COMMENT ON COLUMN OBJTYPE.OBJECT_TYPE_ID IS '������������� ���������� ����';
COMMENT ON COLUMN OBJTYPE.PARENT_ID IS '������ �� ������������� ������������� ���������� ����';
COMMENT ON COLUMN OBJTYPE.CODE IS '�������� ���������� ���� � ���������� ���������';
COMMENT ON COLUMN OBJTYPE.NAME IS '�������� ���������� ���� � ������������ ��������� (��� GUI)';
COMMENT ON COLUMN OBJTYPE.DESCRIPTION IS '��������� �������� ���������� ���� � ������������ ��������� (��� GUI)';


/* 
��� �������� �� UML-��������� � EAV-������ �������������:
1) ������ ����� - ��� ������ � ������� OBJTYPE � ������ ������ � ������� OBJTYPE.NAME
2) ����� ���� "���������" ����� �������� ����� ��������� � ������� OBJTYPE.OBJECT_TYPE_ID � OBJTYPE.PARENT_ID,
��� OBJTYPE.OBJECT_TYPE_ID - ������������� ������-����������, OBJTYPE.PARENT_ID - ������������� ������-��������
3) ����� ���� "���������� ����������" ����� �������� ����� ��������� � ������� OBJTYPE.OBJECT_TYPE_ID � OBJTYPE.PARENT_ID,
��� OBJTYPE.OBJECT_TYPE_ID - ������������� ������ ���� "�������", OBJTYPE.PARENT_ID - ������������� ������ ���� "�����"

*/
-- ������� �������� ���������� �����
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
	IS '������� �������� ���������� �����';
COMMENT ON COLUMN ATTRTYPE.OBJECT_TYPE_ID
	IS '������ �� ������������� ���������� ���� ������, ������� ������������� ������ ���������� ���';
COMMENT ON COLUMN ATTRTYPE.OBJECT_TYPE_ID_REF 
	IS '������ �� ������������� ���������� ���� ������, ������� ����� �������� � ����� ��������� � ��������� ����� ������ �� ATTRTYPE.OBJECT_TYPE_ID';
COMMENT ON COLUMN ATTRTYPE.CODE 
	IS '�������� ����������� ���� � ���������� ���������';
COMMENT ON COLUMN ATTRTYPE.NAME 
	IS '�������� ����������� ���� � ������������ ��������� (��� GUI)';

-- ������� �������� ����������� ��������
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

COMMENT ON TABLE OBJECTS IS '������� �������� ����������� ��������';

-- ������� �������� �������� ��������� ����������� ��������
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

COMMENT ON TABLE ATTRIBUTES IS '������� �������� ��������� ����������� ��������';
COMMENT ON COLUMN ATTRIBUTES.VALUE IS '�������� �������� ���������� ������� � ���� ������ ��� �����';
COMMENT ON COLUMN ATTRIBUTES.DATE_VALUE IS '�������� �������� ���������� ������� � ���� ����';

-- ������� �������� ������ "������� ����������" ����� ������������ ��������
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

COMMENT ON TABLE OBJREFERENCE IS '������� �������� ������ ����� ������������ ��������';
COMMENT ON COLUMN OBJREFERENCE.ATTR_ID IS '������ �� ���������� ��� ��� ������������� ����� ����� ������������ ��������';
COMMENT ON COLUMN OBJREFERENCE.OBJECT_ID IS '������ �� ��������� 1-�� ������� ������������� �����';
COMMENT ON COLUMN OBJREFERENCE.REFERENCE IS '������ �� ��������� 2-�� ������� ������������� �����';