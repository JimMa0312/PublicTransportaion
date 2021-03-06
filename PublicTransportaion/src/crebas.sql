/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     2016/12/2 14:49:36                           */
/*==============================================================*/
create database Bus
go

use [Bus]

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Mao_information') and o.name = 'FK_MAO_INFO_NODENO1_STATION_')
alter table Mao_information
   drop constraint FK_MAO_INFO_NODENO1_STATION_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Mao_information') and o.name = 'FK_MAO_INFO_NODENO2_STATION_')
alter table Mao_information
   drop constraint FK_MAO_INFO_NODENO2_STATION_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Route_Planning') and o.name = 'FK_ROUTE_PL_PLANNING_BUS_INFO')
alter table Route_Planning
   drop constraint FK_ROUTE_PL_PLANNING_BUS_INFO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SID') and o.name = 'FK_SID_LISTEN_ST_STATION_')
alter table SID
   drop constraint FK_SID_LISTEN_ST_STATION_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SID') and o.name = 'FK_SID_PLANED_CA_CAR_INFO')
alter table SID
   drop constraint FK_SID_PLANED_CA_CAR_INFO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SID') and o.name = 'FK_SID_PLANED_RO_BUS_INFO')
alter table SID
   drop constraint FK_SID_PLANED_RO_BUS_INFO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Bus_information')
            and   type = 'U')
   drop table Bus_information
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Car_information')
            and   type = 'U')
   drop table Car_information
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Mao_information')
            and   name  = 'NodeNo2_FK'
            and   indid > 0
            and   indid < 255)
   drop index Mao_information.NodeNo2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Mao_information')
            and   name  = 'NodeNo1_FK'
            and   indid > 0
            and   indid < 255)
   drop index Mao_information.NodeNo1_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Mao_information')
            and   type = 'U')
   drop table Mao_information
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Route_Planning')
            and   name  = 'Planning2_FK'
            and   indid > 0
            and   indid < 255)
   drop index Route_Planning.Planning2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Route_Planning')
            and   type = 'U')
   drop table Route_Planning
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SID')
            and   name  = 'Planed_Car_FK'
            and   indid > 0
            and   indid < 255)
   drop index SID.Planed_Car_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SID')
            and   name  = 'Listen_station_FK'
            and   indid > 0
            and   indid < 255)
   drop index SID.Listen_station_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SID')
            and   name  = 'planed_route_FK'
            and   indid > 0
            and   indid < 255)
   drop index SID.planed_route_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SID')
            and   type = 'U')
   drop table SID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Station_information')
            and   type = 'U')
   drop table Station_information
go

if exists (select 1
            from  sysobjects
           where  id = object_id('admin_information')
            and   type = 'U')
   drop table admin_information
go

/*==============================================================*/
/* Table: Bus_information                                       */
/*==============================================================*/
create table Bus_information (
   Bus_No               varchar(10)           not null,
   Time_Start           varChar(20)             not null,
   Time_End             varChar(20)            not null,
   Time_Lag             varchar(10)             not null,
   constraint PK_BUS_INFORMATION primary key nonclustered (Bus_No)
)
go

/*==============================================================*/
/* Table: Car_information                                       */
/*==============================================================*/
create table Car_information (
   License_Plate        varchar(9)           not null,
   Einge_id             char(18)             not null,
   Frame_id             char(10)             not null,
   Bus_tyoe             varchar(20)          not null,
   Can_population       int                  not null,
   Bus_Chair            int                  not null,
   constraint PK_CAR_INFORMATION primary key nonclustered (License_Plate)
)
go

/*==============================================================*/
/* Table: Mao_information                                       */
/*==============================================================*/
create table Mao_information (
   Station_ID           varchar(10)             not null,
   Sta_Station_ID       varchar(10)             not null,
   Station_distence2    float(8)             not null,
   Station_State2       int                  not null,
   constraint PK_MAO_INFORMATION primary key nonclustered (Station_ID, Sta_Station_ID)
)
go

/*==============================================================*/
/* Index: NodeNo1_FK                                            */
/*==============================================================*/
create index NodeNo1_FK on Mao_information (
Station_ID ASC
)
go

/*==============================================================*/
/* Index: NodeNo2_FK                                            */
/*==============================================================*/
create index NodeNo2_FK on Mao_information (
Sta_Station_ID ASC
)
go

/*==============================================================*/
/* Table: Route_Planning                                        */
/*==============================================================*/
create table Route_Planning (
   Bus_No               varchar(10)           not null,
   UpStream             text                 not null,
   DownStream           text                 not null
)
go

/*==============================================================*/
/* Index: Planning2_FK                                          */
/*==============================================================*/
create index Planning2_FK on Route_Planning (
Bus_No ASC
)
go

/*==============================================================*/
/* Table: SID                                                   */
/*==============================================================*/
create table SID (
   Bus_No               varchar(10)           not null,
   Station_ID           varchar(10)             not null,
   License_Plate        varchar(9)           not null,
   Engine_start         varChar(25)             not null,
   GPS                  text                 not null,
   CarrOut_Date         varChar(25)             not null,
   Line_Layer           int                  not null,
   constraint PK_SID primary key nonclustered (Bus_No, Station_ID, License_Plate)
)
go

/*==============================================================*/
/* Index: planed_route_FK                                       */
/*==============================================================*/
create index planed_route_FK on SID (
Bus_No ASC
)
go

/*==============================================================*/
/* Index: Listen_station_FK                                     */
/*==============================================================*/
create index Listen_station_FK on SID (
Station_ID ASC
)
go

/*==============================================================*/
/* Index: Planed_Car_FK                                         */
/*==============================================================*/
create index Planed_Car_FK on SID (
License_Plate ASC
)
go

/*==============================================================*/
/* Table: Station_information                                   */
/*==============================================================*/
create table Station_information (
   Station_ID           varchar(10)             not null,
   Station_Name         varchar(50)          not null,
   Station_Address      varchar(50)          not null,
   Station_GPS_X          float                 not null,
   Station_GPS_Y          float                 not null,
   constraint PK_STATION_INFORMATION primary key nonclustered (Station_ID)
)
go

/*==============================================================*/
/* Table: admin_information                                     */
/*==============================================================*/
create table admin_information (
   COntrol_Id           char(10)             not null,
   Control_PWD          varchar(60)          not null,
   Control_Limit        int                  not null,
   Tel                  varchar(20)          not null,
   Name                 varchar(10)          null,
   Give_Name            varchar(20)          null,
   gender               int                  null,
   constraint PK_ADMIN_INFORMATION primary key nonclustered (COntrol_Id)
)
go

alter table Mao_information
   add constraint FK_MAO_INFO_NODENO1_STATION_ foreign key (Station_ID)
      references Station_information (Station_ID)
go

alter table Mao_information
   add constraint FK_MAO_INFO_NODENO2_STATION_ foreign key (Sta_Station_ID)
      references Station_information (Station_ID)
go

alter table Route_Planning
   add constraint FK_ROUTE_PL_PLANNING_BUS_INFO foreign key (Bus_No)
      references Bus_information (Bus_No)
go

alter table SID
   add constraint FK_SID_LISTEN_ST_STATION_ foreign key (Station_ID)
      references Station_information (Station_ID)
go

alter table SID
   add constraint FK_SID_PLANED_CA_CAR_INFO foreign key (License_Plate)
      references Car_information (License_Plate)
go

alter table SID
   add constraint FK_SID_PLANED_RO_BUS_INFO foreign key (Bus_No)
      references Bus_information (Bus_No)
go

