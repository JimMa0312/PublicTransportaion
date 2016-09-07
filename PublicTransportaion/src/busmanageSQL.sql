/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     2016/9/2 9:26:00                             */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Map_information') and o.name = 'FK_Map_INFO_HAVE1_STATION_')
alter table Map_information
   drop constraint FK_Map_INFO_HAVE1_STATION_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Map_information') and o.name = 'FK_Map_INFO_HAVE2_STATION_')
alter table Map_information
   drop constraint FK_Map_INFO_HAVE2_STATION_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Route_Planning') and o.name = 'FK_ROUTE_PL_PLANNING_BUS_INFO')
alter table Route_Planning
   drop constraint FK_ROUTE_PL_PLANNING_BUS_INFO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Route_Planning') and o.name = 'FK_ROUTE_PL_PLANNING2_BUS_INFO')
alter table Route_Planning
   drop constraint FK_ROUTE_PL_PLANNING2_BUS_INFO
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
           where  id    = object_id('Map_information')
            and   name  = 'have2_FK'
            and   indid > 0
            and   indid < 255)
   drop index Map_information.have2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Map_information')
            and   name  = 'have1_FK'
            and   indid > 0
            and   indid < 255)
   drop index Map_information.have1_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Map_information')
            and   type = 'U')
   drop table Map_information
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Route_Planning')
            and   name  = 'Planning_FK'
            and   indid > 0
            and   indid < 255)
   drop index Route_Planning.Planning_FK
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
   Time_Start           time             not null,
   Time_End             time             not null,
   Time_Lag             int             not null,
   constraint PK_BUS_INFORMATION primary key nonclustered (Bus_No)
)
go

/*==============================================================*/
/* Table: Car_information                                        */
/*==============================================================*/
create table Car_information (
   License_Plate        varchar(9)           not null,
   Einge_id             char(18)             not null,
   Frame_id             char(10)             not null,
   Bus_type             varchar(20)          not null,
   Can_population       int                  not null,
   Bus_Chair            int                  not null,
   constraint PK_CAR_information primary key nonclustered (License_Plate)
)
go

/*==============================================================*/
/* Table: Map_information                                       */
/*==============================================================*/
create table Map_information (
   Station_ID           char(10)             not null,
   Sta_Station_ID       char(10)             not null,
   Station_distence2    float(8)             not null,
   Station_State2       int                  not null,
   constraint PK_Map_INFORMATION primary key nonclustered (Station_ID, Sta_Station_ID)
)
go

/*==============================================================*/
/* Index: have1_FK                                              */
/*==============================================================*/
create index have1_FK on Map_information (
Station_ID ASC
)
go

/*==============================================================*/
/* Index: have2_FK                                              */
/*==============================================================*/
create index have2_FK on Map_information (
Sta_Station_ID ASC
)
go

/*==============================================================*/
/* Table: Route_Planning                                        */
/*==============================================================*/
create table Route_Planning (
   Bus_No               varchar(10)           null,
   UpStream             text                 not null,
   DownStream           text                 not null
)
go

/*==============================================================*/
/* Index: Planning2_FK                                          */
/*==============================================================*/
create index Planning_FK on Route_Planning (
Bus_No ASC
)
go

/*==============================================================*/
/* Index: Planning_FK                                           */
/*==============================================================*/

/*==============================================================*/
/* Table: SID                                                   */
/*==============================================================*/
create table SID (
   Bus_No               varchar(10)           not null,
   Station_ID           char(10)             not null,
   License_Plate        varchar(9)           not null,
   Engine_start         time            	 not null,
   GPS                  text                 not null,
   CarrOut_Date         date             not null,
   Line_Layer           int                  not null,
   planning_type			int					 not null
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
   Station_ID           char(10)             not null,
   Station_Name         varchar(10)          not null,
   Station_Address      varchar(20)          not null,
   Station_GPS          text                 not null,
   constraint PK_STATION_INFORMATION primary key nonclustered (Station_ID)
)
go

/*==============================================================*/
/* Table: admin_information                                     */
/*==============================================================*/
create table admin_information (
   COntrol_Id           char(10)             not null,
   Control_PWD          varchar(50)          not null,
   Control_Limit        int                  not null,
   constraint PK_ADMIN_INFORMATION primary key nonclustered (COntrol_Id)
)
go

alter table Map_information
   add constraint FK_Map_INFO_HAVE1_STATION_ foreign key (Station_ID)
      references Station_information (Station_ID)
go

alter table Map_information
   add constraint FK_Map_INFO_HAVE2_STATION_ foreign key (Sta_Station_ID)
      references Station_information (Station_ID)
go

alter table Route_Planning
   add constraint FK_ROUTE_PL_PLANNING2_BUS_INFO foreign key (Bus_No)
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

