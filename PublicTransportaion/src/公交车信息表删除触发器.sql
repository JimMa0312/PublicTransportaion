use bus
go
/*删除班线记录的触发器*/
create trigger bus_delete on Bus_information
instead of delete as
declare @panduan int
select @panduan=planning_type
from SID where Bus_No in(select Bus_No from deleted)
if @panduan=1
begin
raiserror('该班线正在被使用，您不能将其删除！',16,1)
rollback transaction
end
else
begin
delete from Route_Planning where Bus_No in(select Bus_No from deleted)
delete from Bus_information where Bus_No in(select Bus_No from deleted)
print('温馨提示：该条班线已被成功删除，其对应线路明细也被删除。')
end
go
/*删除车辆记录的触发器*/
create trigger car_delete on Car_information
instead of delete as
declare @panduan int
select @panduan=planning_type
from SID where License_Plate in(select License_Plate from deleted)
if @panduan=1
begin
raiserror('该车辆正在运营使用，您不能将其删除！',16,1)
rollback transaction
end
else
begin
delete from SID where License_Plate in(select License_Plate from deleted)
delete from Car_information where License_Plate in(select License_Plate from deleted)
print('您已成功删除该车辆，以及其所有相关信息。')
end
go
/*删除站点记录触发器*/
create trigger station_delete on Station_information
instead of delete as
declare @panduan int,@count int
select @count=count(*) from SID where Station_ID in(select Station_ID from deleted)
select @panduan=planning_type
from SID where Station_ID in(select Station_ID from deleted)
if @count=0
begin
delete from Map_information where Sta_Station_ID in(select Station_ID from deleted)
delete from Station_information where Station_ID in(select Station_ID from deleted)
print('您已成功删除该站点记录,并同时清除了包含该站点的“站点信息记录。”')
end
else if @panduan=1
begin
raiserror('该站点正在运营使用，您不能将其删除！',16,1)
rollback transaction
end
/*声明：无须在Map表上添加级联删除触发器，出于严谨考虑管理员不能对Map表直接进
行删除操作，因为Station表中站点信息的存在是Map表中站点信息存在的前提条件，如
果管理员想要删除一个站点，那么该站点在Map表中的相关信息则都要删除，这是完全符
合逻辑的，并且不会造成任何信息丢失或冗余。然而反过来就不合适，如果在Map表上添
加级联删除操作的触发器，仅删除一条记录则会抹去两个站点的所有信息，这样的操作
是不安全且不严谨的。而且Map表中有可能会剩余包含刚刚删除站点的其它记录，那么这
些记录完全是冗余的。*/
go
/*删除计划的触发器*/
create trigger Route_delete on Route_Planning
instead of delete as
declare @panduan int
select @panduan=planning_type
from SID where Bus_No in(select Bus_No from deleted)
if @panduan=1
begin
raiserror('该线路计划正在运营使用，您不能将其删除！',16,1)
rollback transaction
end
else
begin
delete from Route_Planning where Bus_No in(select Bus_No from deleted)
print('您已经成功删除了该线路计划！但动态信息中仍留有该线路计划的记录，
以待重新启用。如果您确实不想在使用该线路，请在动态信息表中删除含有该
线路计划的记录。')
end
go
/*动态信息表SID的删除触发器*/
create trigger SID_delete on SID
instead of delete as
declare @panduan int
select @panduan=planning_type from SID where Bus_No in(select Bus_No from deleted)
if @panduan=1
begin
raiserror('该条动态信息所在的计划正在被使用，您不能将其删除！',16,1)
rollback transaction
end
else
begin
delete from SID where  Bus_No in(select Bus_No from deleted)
print('您已成功删除该条动态信息记录，但其相对应的其它各项信息
需要您预先手动逐个删除！')
end
go
