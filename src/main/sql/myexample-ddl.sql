USE test;

DROP TABLE EntityA_EntityE_JT;

DROP TABLE EntityA_stringList;
DROP TABLE EntityA_embeddableEntityList;
DROP TABLE EntityA_stringMap;
DROP TABLE EntityA_embeddableEntityMap;

DROP TABLE EntityA;

DROP TABLE EntityB;
DROP TABLE EntityC;
DROP TABLE EntityD;
DROP TABLE EntityE;

CREATE TABLE EntityA (
	id BIGINT,
  name VARCHAR(255),
  stringField VARCHAR(255),
  intField INT,
  entityB_id BIGINT,
  entityC_id BIGINT,
  creationTime TIMESTAMP,
  PRIMARY KEY (ID)
);

CREATE TABLE EntityA_stringList (
  EntityA_ID BIGINT,
  stringValue VARCHAR(255)
);
ALTER TABLE EntityA_stringList ADD CONSTRAINT FK_EntityA_stringList_EntityA_ID FOREIGN KEY (EntityA_ID) REFERENCES EntityA(ID);

CREATE TABLE EntityA_embeddableEntityList (
  EntityA_ID BIGINT,
  stringField VARCHAR(255),
  intField INT
);
ALTER TABLE EntityA_stringList ADD CONSTRAINT FK_EntityA_embeddableEntityList_EntityA_ID FOREIGN KEY (EntityA_ID) REFERENCES EntityA(ID);

CREATE TABLE EntityA_stringMap (
  EntityA_ID BIGINT,
  keyField VARCHAR(255),
  stringValue VARCHAR(255),
  PRIMARY KEY (EntityA_ID, keyField)
);
ALTER TABLE EntityA_stringMap ADD CONSTRAINT FK_EntityA_stringMap_EntityA_ID FOREIGN KEY (EntityA_ID) REFERENCES EntityA(ID);

CREATE TABLE EntityA_embeddableEntityMap (
  EntityA_ID BIGINT,
  key_stringField VARCHAR(255),
  key_intField INT,
  value_stringField VARCHAR(255),
  value_intField INT
);
ALTER TABLE EntityA_embeddableEntityMap ADD CONSTRAINT FK_EntityA_embeddableEntityMap_EntityA_ID FOREIGN KEY (EntityA_ID) REFERENCES EntityA(ID);


CREATE TABLE EntityB (
    ID BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE EntityC (
    ID BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE EntityD (
    ID BIGINT,
    name VARCHAR(255),
    entityA_id BIGINT,
    PRIMARY KEY (ID)
);

CREATE TABLE EntityE (
    ID BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE EntityA_EntityE_JT (
    EntityA_id BIGINT,
    EntityE_id BIGINT
);
ALTER TABLE EntityA_EntityE_JT ADD CONSTRAINT FK_EntityA_EntityE_JT_EntityA_ID FOREIGN KEY (EntityA_ID) REFERENCES EntityA(ID);
ALTER TABLE EntityA_EntityE_JT ADD CONSTRAINT FK_EntityA_EntityE_JT_EntityE_ID FOREIGN KEY (EntityE_ID) REFERENCES EntityE(ID);

drop table OneToMany_BD_Map_Case21_EntityA;
drop table OneToMany_BD_Map_Case21_EntityB;

CREATE TABLE OneToMany_BD_Map_Case21_EntityA (
  id BIGINT,
  name VARCHAR(255),
  PRIMARY KEY (ID)
);

CREATE TABLE OneToMany_BD_Map_Case21_EntityB (
  id BIGINT,
  name VARCHAR(255),
  entityA_id BIGINT,
  keyField VARCHAR(255),
  PRIMARY KEY (ID)
);
ALTER TABLE OneToMany_BD_Map_Case21_EntityB ADD CONSTRAINT FK_OneToMany_BD_Map_Case21_EntityB_EntityA_ID FOREIGN KEY (entityA_id) REFERENCES OneToMany_BD_Map_Case21_EntityA(ID);

