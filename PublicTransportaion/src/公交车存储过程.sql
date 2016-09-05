create procedure new_admin (@id char(10),@pwd varchar(50),@limit int) as insert into admin_information values(@id,@pwd,@limit)
go			--管理员信息表插入记录的存储过程
create procedure new_bus (@bus_no varchar(1),@time_s datetime,@time_e datetime,@time_l datetime) as insert into Bus_information values(@bus_no,@time_s,@time_e,@time_l)
go			--班线信息表插入记录的存储过程
create procedure new_car (@license_p varchar(9),@engine_id char(18),@frame_id char(10),@bus_t varchar(20),@can_p int,@bus_c int)
as insert into Car_infomation values(@license_p,@engine_id,@frame_id,@bus_t,@can_p,@bus_c)
go			--车辆信息插入
create procedure new_mao(@station_id char(10),@sta_station char(10),@station_distance2 real,@station_state2 int)
as insert into Map_information values(@station_id,@sta_station,@station_distance2,@station_state2)
go			--站点间信息表插入
create procedure new_plan (@bus_bus_no varchar(1),@bus_no varchar(1),@upstream text,@downstream text)
as insert into Route_Planning values(@bus_bus_no,@bus_no,@upstream,@downstream)
go			--添加计划的存储过程
create procedure new_sid (@bus_no varchar(1),@station_id char(10),@license_p varchar(9),@engine_s datetime,@gps text,@carout_date datetime,@line_layer int)
as insert into SID values(@bus_no,@station_id,@license_p,@engine_s,@gps,@carout_date,@line_layer)
go			--添加SID的存储过程
create procedure new_station (@staion_id char(10),@station_name varchar(10),@station_adress varchar(20),@station_gps text)
as insert into Station_information values (@staion_id,@station_name,@station_adress,@station_gps)
go			--添加站点信息
