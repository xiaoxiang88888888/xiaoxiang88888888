/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012-2-16 15:40:47                           */
/*==============================================================*/


drop index area_AK on area;

drop table if exists area;

drop index dictionary_AK on dictionary;

drop table if exists dictionary;

drop index dictionary_type_AK on dictionary_type;

drop table if exists dictionary_type;

drop index menu_AK on menu;

drop table if exists menu;

drop index module_AK on module;

drop table if exists module;

drop index operation_AK on operation;

drop table if exists operation;

drop index permission_AK on permission;

drop table if exists permission;

drop index resource_AK on resource;

drop table if exists resource;

drop index role_AK on role;

drop table if exists role;

drop table if exists role_permission;

drop index role_type_AK on role_type;

drop table if exists role_type;

drop table if exists role_user;

drop index user_AK on user;

drop table if exists user;

drop index user_group_AK on user_group;

drop table if exists user_group;

drop table if exists user_group_map;

/*==============================================================*/
/* Table: area                                                  */
/*==============================================================*/
create table area
(
   id                   int(12) not null auto_increment comment 'id',
   area_id              varchar(64) not null comment '地区编号',
   parent_id            varchar(64) not null comment '父id',
   code                 varchar(64) comment '地区编码',
   name                 varchar(300) comment '地区名称',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   gmt_create           datetime comment '创建时间',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   primary key (id)
);

alter table area comment '地区';

/*==============================================================*/
/* Index: area_AK                                               */
/*==============================================================*/
create unique index area_AK on area
(
   area_id
);

/*==============================================================*/
/* Table: dictionary                                            */
/*==============================================================*/
create table dictionary
(
   id                   int(12) not null auto_increment comment 'id',
   data_id              varchar(64) comment '字典编码',
   name                 varchar(300) comment '数据名称',
   value                varchar(64) comment '字典值',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   dt_id                varchar(64) comment '字典类型标识',
   modifier             varchar(64) default 'sys' comment '修改人',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   primary key (id)
);

alter table dictionary comment '数据字典';

/*==============================================================*/
/* Index: dictionary_AK                                         */
/*==============================================================*/
create unique index dictionary_AK on dictionary
(
   data_id
);

/*==============================================================*/
/* Table: dictionary_type                                       */
/*==============================================================*/
create table dictionary_type
(
   id                   int(12) not null auto_increment comment 'id',
   dt_id                varchar(64) comment '字典类型标识',
   parent_id            varchar(64) comment '父标识',
   name                 varchar(300) comment '字典类型名称',
   is_sys               numeric(2,0) comment '是否系统数据字典',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   gmt_create           datetime comment '创建时间',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table dictionary_type comment '数据字典类型';

/*==============================================================*/
/* Index: dictionary_type_AK                                    */
/*==============================================================*/
create unique index dictionary_type_AK on dictionary_type
(
   dt_id
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int(12) not null auto_increment comment 'id',
   menu_id              varchar(64) comment '菜单编号',
   parent_id            varchar(64) comment '父id',
   code                 varchar(300) comment '菜单编码',
   name                 varchar(300) comment '菜单名称',
   target               numeric(2,0) comment '打开方式',
   menu_icon            varchar(300) comment '图标',
   hidden               numeric(2,0) comment '是否隐藏',
   menu_type            numeric(2,0) comment '类型',
   url                  varchar(300) comment '地址',
   is_expand            numeric(2,0) comment '是否展开',
   tab_name             varchar(64) comment '页签标题',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   resource_id          varchar(64) comment '资源编号',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table menu comment '菜单';

/*==============================================================*/
/* Index: menu_AK                                               */
/*==============================================================*/
create unique index menu_AK on menu
(
   menu_id
);

/*==============================================================*/
/* Table: module                                                */
/*==============================================================*/
create table module
(
   id                   int(12) not null auto_increment comment 'id',
   module_id            varchar(64) comment '模块编号',
   parent_id            varchar(64) comment '父id',
   code                 varchar(300) comment '模块编码',
   name                 varchar(300) comment '模块名称',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table module comment '模块';

/*==============================================================*/
/* Index: module_AK                                             */
/*==============================================================*/
create unique index module_AK on module
(
   module_id
);

/*==============================================================*/
/* Table: operation                                             */
/*==============================================================*/
create table operation
(
   id                   int(12) not null auto_increment comment 'id',
   operation_id         varchar(64) comment '操作编号',
   code                 varchar(300) comment '操作编码',
   name                 varchar(300) comment '操作名称',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   permission_id        varchar(64) comment '权限编号',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table operation comment '操作';

/*==============================================================*/
/* Index: operation_AK                                          */
/*==============================================================*/
create unique index operation_AK on operation
(
   operation_id
);

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   int(12) not null auto_increment comment 'id',
   permission_id        varchar(64) comment '权限编号',
   code                 varchar(300) comment '权限编码',
   name                 varchar(300) comment '权限名称',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   resource_id          varchar(64) comment '资源编号',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   primary key (id)
);

alter table permission comment '权限
';

/*==============================================================*/
/* Index: permission_AK                                         */
/*==============================================================*/
create unique index permission_AK on permission
(
   permission_id
);

/*==============================================================*/
/* Table: resource                                              */
/*==============================================================*/
create table resource
(
   id                   int(12) not null auto_increment comment 'id',
   resource_id          varchar(64) comment '资源编号',
   code                 varchar(300) comment '资源标识',
   name                 varchar(300) comment '资源名称',
   type                 numeric(2,0) comment '资源类型',
   url                  varchar(500) comment '访问地址',
   allow                numeric(2,0) comment '是否可用',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   module_id            varchar(64) comment '模块编号',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table resource comment '资源';

/*==============================================================*/
/* Index: resource_AK                                           */
/*==============================================================*/
create unique index resource_AK on resource
(
   resource_id
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int(12) not null auto_increment comment 'id',
   role_id              varchar(64) comment '角色编号',
   code                 varchar(300) comment '角色编码',
   name                 varchar(300) comment '角色名称',
   creater_id           varchar(64) comment '创建人ID',
   creater_name         varchar(300) comment '创建人名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   role_type_id         varchar(64) comment '角色类型编号',
   creator              varchar(64) default 'sys' comment '创建人',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   primary key (id)
);

alter table role comment '角色';

/*==============================================================*/
/* Index: role_AK                                               */
/*==============================================================*/
create unique index role_AK on role
(
   role_id
);

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   id                   int(12) not null auto_increment comment 'id',
   role_id              varchar(64) comment '角色编号',
   permission_id        varchar(64) comment '权限编号',
   gmt_create           datetime comment '创建时间',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table role_permission comment '角色权限';

/*==============================================================*/
/* Table: role_type                                             */
/*==============================================================*/
create table role_type
(
   id                   int(12) not null auto_increment comment 'id',
   role_type_id         varchar(64) comment '角色类型编号',
   parent_id            varchar(64) comment '父id',
   code                 varchar(300) comment '角色类型编码',
   name                 varchar(300) comment '角色类型名称',
   type                 numeric(2,0) comment '管理还是业务型',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   gmt_create           datetime comment '创建时间',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table role_type comment '角色类型';

/*==============================================================*/
/* Index: role_type_AK                                          */
/*==============================================================*/
create unique index role_type_AK on role_type
(
   role_type_id
);

/*==============================================================*/
/* Table: role_user                                             */
/*==============================================================*/
create table role_user
(
   id                   int(12) not null auto_increment comment 'id',
   role_id              varchar(64) comment '角色编号',
   user_id              varchar(64) comment '用户编号',
   gmt_create           datetime comment '创建时间',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table role_user comment '用户角色';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int(12) not null auto_increment comment 'id',
   user_id              varchar(64) comment '用户编号',
   account              varchar(300) comment '用户帐号',
   password             varchar(300) comment '用户密码',
   enable               numeric(2,0) comment '是否禁用',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   gmt_create           datetime comment '创建时间',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table user comment '用户';

/*==============================================================*/
/* Index: user_AK                                               */
/*==============================================================*/
create unique index user_AK on user
(
   user_id
);

/*==============================================================*/
/* Table: user_group                                            */
/*==============================================================*/
create table user_group
(
   id                   int(12) not null auto_increment comment 'id',
   group_id             varchar(64) comment '用户组编号',
   parent_id            varchar(64) comment '父id',
   code                 varchar(300) comment '用户组编码',
   name                 varchar(300) comment '用户组名称',
   order_no             numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   gmt_create           datetime comment '创建时间',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table user_group comment '用户组';

/*==============================================================*/
/* Index: user_group_AK                                         */
/*==============================================================*/
create unique index user_group_AK on user_group
(
   group_id
);

/*==============================================================*/
/* Table: user_group_map                                        */
/*==============================================================*/
create table user_group_map
(
   id                   int(12) not null auto_increment comment 'id',
   group_id             varchar(64) comment '用户组编号',
   user_id              varchar(64) comment '用户编号',
   gmt_create           datetime comment '创建时间',
   is_deleted           varchar(1) default 'n' comment '是否删除',
   modifier             varchar(64) default 'sys' comment '修改人',
   gmt_modified         datetime comment '修改时间',
   creator              varchar(64) default 'sys' comment '创建人',
   primary key (id)
);

alter table user_group_map comment '用户_用户组';

