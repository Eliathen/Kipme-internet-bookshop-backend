create or replace table ADDRESS
(
    ID             BIGINT auto_increment,
    BUILDINGNUMBER VARCHAR(255),
    CITY           VARCHAR(255),
    FLATNUMBER     VARCHAR(255),
    POSTALCODE     VARCHAR(255),
    STREET         VARCHAR(255),
    VOIVODESHIP    VARCHAR(255),
    primary key (ID)
);

create or replace table AUTHOR
(
    ID      INTEGER auto_increment,
    NAME    VARCHAR(255),
    SURNAME VARCHAR(255),
    primary key (ID)
);

create or replace table BASKET
(
    ID          BIGINT auto_increment,
    MODIFIED_AT TIMESTAMP,
    primary key (ID)
);

create or replace table CATEGORY
(
    ID   INTEGER auto_increment,
    NAME VARCHAR(255),
    primary key (ID)
);

create or replace table COVER
(
    ID   BIGINT auto_increment,
    DATA LONGBLOB,
    NAME VARCHAR(255),
    TYPE VARCHAR(255),
    primary key (ID)
);

create or replace table DELIVERY_METHOD
(
    ID    INTEGER auto_increment,
    NAME  VARCHAR(255),
    PRICE DECIMAL(19, 2),
    primary key (ID)
);

create or replace table DELIVERY
(
    ID                BINARY(16) not null,
    DELIVERYPRICE     DECIMAL(19, 2),
    ADDRESS_ID        BIGINT,
    DELIVERYMETHOD_ID INTEGER,
    primary key (ID),
    constraint FK9NLPXMDTFR1Y303CI4SN2073S
        foreign key (ADDRESS_ID) references ADDRESS (ID),
    constraint FKD5XGFHBRFHH215A7DTS9TM88P
        foreign key (DELIVERYMETHOD_ID) references DELIVERY_METHOD (ID)
);

create or replace table LANGUAGE
(
    ID   INTEGER auto_increment,
    NAME VARCHAR(255),
    primary key (ID)
);

create or replace table BOOK
(
    ID             BIGINT auto_increment,
    ADDED_AT       TIMESTAMP not null,
    DESCRIPTION    TEXT,
    IS_AVAILABLE   BOOLEAN   not null,
    ISBN           VARCHAR(255),
    PRICE          DECIMAL(19, 2),
    PUBLISHED_YEAR INTEGER,
    QUANTITY       INTEGER,
    TITLE          VARCHAR(255),
    CATEGORY_ID    INTEGER   not null,
    COVER_ID       BIGINT,
    LANGUAGE_ID    INTEGER   not null,
    primary key (ID),
    constraint UK_BI5LX9JTV1F52IDRMC0CK8YSX
        unique (ISBN),
    constraint FK7U5S0GG93ANYJS5QJ5UCWQRU8
        foreign key (COVER_ID) references COVER (ID),
    constraint FKE4PSGWI6953WVBQXRUNA75YAX
        foreign key (CATEGORY_ID) references CATEGORY (ID),
    constraint FKNSLQ42LYLKXCSGXH7GLFTK8UO
        foreign key (LANGUAGE_ID) references LANGUAGE (ID)
);

create or replace table BASKET_ITEM
(
    ID              BIGINT auto_increment,
    QUANTITY        INTEGER,
    BASKETENTITY_ID BIGINT,
    BOOK_ID         BIGINT,
    primary key (ID),
    constraint FKG475T2O3IGA9VBCOPUPQGBIQY
        foreign key (BASKETENTITY_ID) references BASKET (ID),
    constraint FKSWP9T9950078PR65YJC6LF2IH
        foreign key (BOOK_ID) references BOOK (ID)
);

create or replace table BOOK_AUTHORS
(
    BOOK_ID   BIGINT  not null,
    AUTHOR_ID INTEGER not null,
    constraint FKAEWHMHQEBVGX3NT9R01G0FD90
        foreign key (BOOK_ID) references BOOK (ID),
    constraint FKFSGSDD0O6CYLCB4RUSRVTXUK5
        foreign key (AUTHOR_ID) references AUTHOR (ID)
);

create or replace table PAYMENT_METHOD
(
    ID     INTEGER auto_increment,
    METHOD VARCHAR(255),
    PRICE  DECIMAL(19, 2),
    TYPE   VARCHAR(255),
    primary key (ID)
);

create or replace table PAYMENT
(
    ID            BINARY(16)  not null,
    AMOUNT        DECIMAL(19, 2),
    CREATEDAT     TIMESTAMP,
    PAYMENTSTATUS VARCHAR(255),
    PAYMENT_ID    INTEGER not null,
    primary key (ID),
    constraint FKC38PETI80J673MNRV9MSJ13WD
        foreign key (PAYMENT_ID) references PAYMENT_METHOD (ID)
);

create or replace table PUBLISHER
(
    ID            INTEGER auto_increment,
    PUBLISHERCITY VARCHAR(255),
    PUBLISHERNAME VARCHAR(255),
    primary key (ID)
);

create or replace table BOOK_PUBLISHERS
(
    BOOK_ID      BIGINT  not null,
    PUBLISHER_ID INTEGER not null,
    constraint FK2E2XHF8TXGHTVHYWE8OJKJW5Q
        foreign key (PUBLISHER_ID) references PUBLISHER (ID),
    constraint FKSTMSOKVWXT8XQWIFTLQ0GECJL
        foreign key (BOOK_ID) references BOOK (ID)
);

create or replace table SALE
(
    ID         BIGINT auto_increment,
    END_AT     TIMESTAMP,
    IS_ACTIVE  BOOLEAN,
    SALE_UNIT  VARCHAR(255),
    CREATED_AT TIMESTAMP,
    VALUE      DECIMAL(19, 2),
    primary key (ID)
);

create or replace table BOOK_SALE
(
    SALE_ID BIGINT not null,
    BOOK_ID BIGINT not null,
    constraint FKD88DV7TAVNEEKTIB32GI4XMAM
        foreign key (SALE_ID) references SALE (ID),
    constraint FKSEK6RD87IGFIWF63ET3PKAIV9
        foreign key (BOOK_ID) references BOOK (ID)
);

create or replace table SUBCATEGORY
(
    ID          INTEGER auto_increment,
    NAME        VARCHAR(255),
    CATEGORY_ID INTEGER,
    primary key (ID),
    constraint FKBUVNMAYI3E42CE3L8RY9L91SM
        foreign key (CATEGORY_ID) references CATEGORY (ID)
);

create or replace table BOOK_SUBCATEGORY
(
    SUBCATEGORY_ID BIGINT  not null,
    BOOK_ID        INTEGER not null,
    constraint FKOMP9LYWT8XKF6O64J2DN9Q095
        foreign key (SUBCATEGORY_ID) references BOOK (ID),
    constraint FKR5P6RY0C5GRH1U2LAAN6H3IMF
        foreign key (BOOK_ID) references SUBCATEGORY (ID)
);

create or replace table USER
(
    ID              BIGINT auto_increment,
    EMAIL           VARCHAR(255),
    ENABLED         BOOLEAN,
    NAME            VARCHAR(255),
    PASSWORD        VARCHAR(255),
    PHONENUMBER     VARCHAR(255),
    ROLE            VARCHAR(255) not null,
    SURNAME         VARCHAR(255),
    BASKETENTITY_ID BIGINT,
    primary key (ID),
    constraint UK_E6GKQUNXAJVYXL5UCTPL2VL2P
        unique (EMAIL),
    constraint FK2TSYF1GEW3X47U5EII333A6GW
        foreign key (BASKETENTITY_ID) references BASKET (ID)
);

create or replace table FAVOURITE_BOOKS
(
    USER_ID BIGINT not null,
    BOOK_ID BIGINT not null,
    constraint FK3MJ4HKC3ICC9FHC9KIPJT8V22
        foreign key (USER_ID) references USER (ID),
    constraint FKRUYRM7BUVA4CBEQIP5IUA0C7M
        foreign key (BOOK_ID) references BOOK (ID)
);

create or replace table OPINION
(
    ID          INTEGER auto_increment,
    DATE        TIMESTAMP,
    DESCRIPTION TEXT,
    RATING      DOUBLE,
    BOOK_ID     BIGINT,
    USER_ID     BIGINT,
    primary key (ID),
    constraint FKO0VV1T1W4MIIIV6OGKJ9G516F
        foreign key (USER_ID) references USER (ID),
    constraint FKRAY7B4WN7AG5GFV430E6MP08G
        foreign key (BOOK_ID) references BOOK (ID)
);

create or replace table PROCUREMENT
(
    ID          BINARY(16) not null,
    FULLPRICE   DECIMAL(19, 2),
    ORDERDATE   TIMESTAMP,
    ORDERSTATUS VARCHAR(255),
    DELIVERY_ID BINARY(16),
    PAYMENT_ID  BINARY(16),
    USER_ID     BIGINT,
    ADDRESS_ID  BIGINT,
    primary key (ID),
    constraint FK6F3743EAMIOVYBEMCOQM957GD
        foreign key (USER_ID) references USER (ID),
    constraint FKAIUTVL3WJ1JWY2QYWAAY1HBD0
        foreign key (ADDRESS_ID) references ADDRESS (ID),
    constraint FKERMULVSRV3YWGBVHG4DKH4C6C
        foreign key (PAYMENT_ID) references PAYMENT (ID),
    constraint FKRXRU0FTKCNNN92YRBMOU71781
        foreign key (DELIVERY_ID) references DELIVERY (ID)
);

create or replace table BOOK_ORDERS
(
    BOOK_ID  BIGINT not null,
    ORDER_ID BINARY(16) not null,
    constraint FK2T11ETIFLRDNCGQNKB13F3IMT
        foreign key (ORDER_ID) references PROCUREMENT (ID),
    constraint FKEHNRENUXBADA6S54H204KDRC3
        foreign key (BOOK_ID) references BOOK (ID)
);

create or replace table ORDER_POSITION
(
    ID             BIGINT auto_increment,
    PRICE          DECIMAL(19, 2),
    ORDER_ID       BINARY(16),
    ORDEREDBOOK_ID BIGINT,
    primary key (ID),
    constraint FK7W7U3AY191IHOPQ8T6J0H64H8
        foreign key (ORDER_ID) references PROCUREMENT (ID),
    constraint FKAYY0WNSYC5H8CMUPJ6P5VNNG7
        foreign key (ORDEREDBOOK_ID) references BOOK (ID)
);

create or replace table USERS_ADDRESSES
(
    ADDRESS_ID BIGINT not null,
    USER_ID    BIGINT not null,
    constraint FK16GCQ4Y2OX7FHQ13W55XHHV57
        foreign key (USER_ID) references USER (ID),
    constraint FKB40QHFHR8JSW4JHXB5S7NNM96
        foreign key (ADDRESS_ID) references ADDRESS (ID)
);
