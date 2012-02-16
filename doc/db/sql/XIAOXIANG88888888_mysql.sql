/*==============================================================*/
/* dbms name:      mysql 5.0                                    */
/* created on:     2010-8-5 10:35:05                            */
/*==============================================================*/


DROP TABLE IF EXISTS AREA;

DROP TABLE IF EXISTS awoke;

DROP TABLE IF EXISTS awoketype;

DROP TABLE IF EXISTS awoke_daymanage;

DROP TABLE IF EXISTS awoke_href_param;

DROP TABLE IF EXISTS awoke_incepters;

DROP TABLE IF EXISTS awoke_subhref;

drop table if exists awoke_subhref_param;

drop table if exists comm_annex;

drop table if exists comm_annextype;

drop table if exists comm_friendlylink;

drop table if exists comm_linktype;

drop table if exists comm_logdetail;

drop table if exists comm_logmanage;

drop table if exists comm_sysaffiche;

drop table if exists comm_sysmessage;

drop table if exists dd_dictionary;

drop table if exists dd_dictionarytype;

drop table if exists dd_exprop;

drop table if exists dd_expropdata;

drop table if exists dd_exprop_system;

drop table if exists odqe_dept;

drop table if exists odqe_employee;

drop table if exists odqe_employee_quarter;

drop table if exists odqe_organ;

drop table if exists odqe_organ_appsystem;

drop table if exists odqe_quarter;

drop table if exists pc_appsystem;

drop table if exists pc_menu;

drop table if exists pc_module;

drop table if exists pc_operation;

drop table if exists pc_permission;

drop table if exists pc_permission_constraint;

drop table if exists pc_resource;

drop table if exists pc_shortcutmenu;

drop table if exists role;

drop table if exists role_constraint;

drop table if exists role_delegate;

drop table if exists role_dept;

drop table if exists role_permission;

drop table if exists role_quarter;

drop table if exists role_type;

drop table if exists role_user;

drop table if exists role_usergroup;

drop table if exists usergroup;

drop table if exists user_appusermapp;

drop table if exists user_info;

drop table if exists user_layout;

drop table if exists user_mapp;

drop table if exists user_panel;

drop table if exists user_style;

drop table if exists user_usergroup;

/*==============================================================*/
/* table: area                                                  */
/*==============================================================*/
create table area
(
   areaid               varchar(64) not null comment '地区编号',
   are_areaid           varchar(64) comment '地区编号',
   areacode             varchar(64) comment '地区编码',
   areaname             varchar(300) comment '地区名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (areaid)
);

alter table area comment '地区';

/*==============================================================*/
/* table: awoke                                                 */
/*==============================================================*/
create table awoke
(
   awokeid              varchar(64) not null comment '提醒id',
   typeid               varchar(64) comment '分类id',
   awokename            varchar(1000) comment '提醒名称',
   messageid            varchar(64) comment '关联id',
   content              varchar(4000) comment '内容',
   sendtime             varchar(20) comment '发送时间',
   systemid             varchar(64) comment '系统id',
   senderid             varchar(64) comment '发送人id',
   sendername           varchar(200) comment '发送人名称',
   awoke_state          numeric(2,0) comment '消息状态',
   start_time           varchar(20) comment '开始时间',
   end_time             varchar(20) comment '结束时间',
   degree               numeric(4,0) comment '发送次数',
   href                 varchar(400) comment '主链接',
   grade                numeric(2,0) comment '紧急程度',
   isworkawoke          numeric(2,0) comment '是否工作提醒',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (awokeid)
);

alter table awoke comment '消息提醒';

/*==============================================================*/
/* table: awoketype                                             */
/*==============================================================*/
create table awoketype
(
   typeid               varchar(64) not null comment '分类id',
   typecode             varchar(300) not null comment '分类编码',
   typename             varchar(300) comment '分类名称',
   isviewclose          numeric(2,0) comment '是否查看关闭',
   defalut_days         numeric(10,0) comment '默认天数',
   days_type            numeric(3,0) comment '计算类型',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (typeid)
);

alter table awoketype comment '提醒分类';

/*==============================================================*/
/* table: awoke_daymanage                                       */
/*==============================================================*/
create table awoke_daymanage
(
   storeid              varchar(64) not null comment '存储id',
   year                 varchar(4) comment '年',
   month                varchar(2) comment '月',
   day                  varchar(2) comment '日',
   days_type            numeric(2,0) comment '类型',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (storeid)
);

alter table awoke_daymanage comment '工作日管理
';

/*==============================================================*/
/* table: awoke_href_param                                      */
/*==============================================================*/
create table awoke_href_param
(
   paramid              varchar(64) not null comment '参数id',
   awokeid              varchar(64) comment '提醒id',
   paramname            varchar(100) comment '参数名称',
   paramvalue           varchar(400) comment '参数值',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (paramid)
);

alter table awoke_href_param comment '消息提醒主链接参数';

/*==============================================================*/
/* table: awoke_incepters                                       */
/*==============================================================*/
create table awoke_incepters
(
   storeid              varchar(64) not null comment '存储id',
   awokeid              varchar(64) comment '提醒id',
   incepterid           varchar(64) comment '接收人id',
   inceptername         varchar(300) comment '接收人名称',
   inceptertype         numeric(2,0) comment '接收人类别',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (storeid)
);

alter table awoke_incepters comment '消息提醒接收人';

/*==============================================================*/
/* table: awoke_subhref                                         */
/*==============================================================*/
create table awoke_subhref
(
   hrefid               varchar(64) not null comment '链接id',
   awokeid              varchar(64) comment '提醒id',
   hrefname             varchar(300) comment '链接地址',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (hrefid)
);

alter table awoke_subhref comment '消息提醒子链接';

/*==============================================================*/
/* table: awoke_subhref_param                                   */
/*==============================================================*/
create table awoke_subhref_param
(
   paramid              varchar(64) not null comment '参数id',
   hrefid               varchar(64) comment '链接id',
   paramname            varchar(100) comment '参数名称',
   paramvalue           varchar(400) comment '参数值',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '描述',
   primary key (paramid)
);

alter table awoke_subhref_param comment '消息提醒子链接参数';

/*==============================================================*/
/* table: comm_annex                                            */
/*==============================================================*/
create table comm_annex
(
   annex_id             varchar(64) not null comment '附件id',
   annextype_id         varchar(64) comment '附件类型id',
   source_id            varchar(64) not null comment '源id',
   annex_name           varchar(300) comment '附件名',
   annex_path           varchar(1000) comment '附件路径',
   file_name            varchar(300) comment '附件名称',
   contenttype          varchar(64) comment '内容类型',
   annex_size           varchar(300) comment '附件大小',
   upload_date          varchar(20) comment '上传时间',
   show_image           numeric(2,0) comment '是否显示图片 ',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (annex_id)
);

alter table comm_annex comment '附件表';

/*==============================================================*/
/* table: comm_annextype                                        */
/*==============================================================*/
create table comm_annextype
(
   annextype_id         varchar(64) not null comment '附件类型id',
   annextype_code       varchar(300) comment '附件类型编码',
   annextype_name       varchar(300) comment '附件类型名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (annextype_id)
);

alter table comm_annextype comment '附件类型表';

/*==============================================================*/
/* table: comm_friendlylink                                     */
/*==============================================================*/
create table comm_friendlylink
(
   linkid               varchar(64) not null comment '链接主键',
   linktypeid           varchar(64) comment '类别id',
   linkname             varchar(300) comment '链接标题',
   linkurl              varchar(300) comment '链接url',
   userid               varchar(64) comment '录入人id',
   username             varchar(100) comment '录入人',
   inputtime            varchar(20) comment '录入时间',
   showflag             numeric(2,0) comment '是否显示',
   linkpic              numeric(2,0) comment '是否图片链接',
   picpath              varchar(300) comment '链接图片地址',
   linktype             varchar(300) comment '链接类型',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (linkid)
);

alter table comm_friendlylink comment '友情链接';

/*==============================================================*/
/* table: comm_linktype                                         */
/*==============================================================*/
create table comm_linktype
(
   linktypeid           varchar(64) not null comment '类别id',
   com_linktypeid       varchar(64) comment '类别id',
   linktypecode         varchar(300) comment '类别编码',
   linktypename         varchar(300) comment '类别名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (linktypeid)
);

alter table comm_linktype comment '链接类别';

/*==============================================================*/
/* table: comm_logdetail                                        */
/*==============================================================*/
create table comm_logdetail
(
   logdetailid          varchar(64) not null comment '详细编号',
   logid                varchar(64) comment '日志编号',
   entity_prop          varchar(300) comment '实体属性',
   entity_prop_cn       varchar(300) comment '实体属性(中文)',
   entity_prop_type     varchar(500) comment '实体属性数据类型',
   comm_vaule           varchar(4000) comment '一般实体值',
   clob_vaule           text comment 'clob实体值',
   blob_vaule           longblob comment 'blob实体值',
   before_comm_vaule    varchar(4000) comment '一般实体值(以前)',
   before_clob_vaule    text comment 'clob实体值(以前)',
   before_blob_vaule    longblob comment 'blob实体值(以前)',
   ismodify             numeric(2,0) comment '是否已修改',
   isrecord             numeric(2,0) comment '是否记录此属性',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (logdetailid)
);

alter table comm_logdetail comment '日志详细';

/*==============================================================*/
/* table: comm_logmanage                                        */
/*==============================================================*/
create table comm_logmanage
(
   logid                varchar(64) not null comment '日志编号',
   resourceid           varchar(64) comment '资源编码',
   resourcename         varchar(300) comment '功能名称',
   old_userid           varchar(64) comment '原操作人',
   old_account          varchar(64) comment '原用户帐号',
   userid               varchar(64) comment '操作人',
   account              varchar(64) comment '用户帐号',
   ip                   varchar(19) comment '访问ip',
   modulename           varchar(300) comment '模块名称',
   opdatetime           varchar(20) comment '操作时间',
   sessionid            varchar(300) comment '会话id',
   timing               numeric(21,0) comment '用时多少秒',
   logtype              varchar(64) comment '类型',
   loglevel             varchar(64) comment '日志等级 ',
   mvc                  varchar(64) comment '层次 ',
   content              text comment '内容',
   producesource        varchar(1000) comment '产生地 ',
   appid                varchar(64) comment '系统id',
   appname              varchar(64) comment '应用系统名称',
   entity_cn            varchar(300) comment '实体名称(中文)',
   entity_path          varchar(500) comment '实体类路径(全)',
   entity_pk            varchar(64) comment '实体主键',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (logid)
);

alter table comm_logmanage comment '日志管理';

/*==============================================================*/
/* table: comm_sysaffiche                                       */
/*==============================================================*/
create table comm_sysaffiche
(
   affiche_id           varchar(64) not null comment '公告编号',
   content              text comment '内容',
   title                varchar(300) comment '标题',
   sendtime             varchar(20) comment '发送时间',
   sender               varchar(64) comment '发送者',
   deadline             numeric(8,0) comment '有效期',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (affiche_id)
);

alter table comm_sysaffiche comment '系统公告';

/*==============================================================*/
/* table: comm_sysmessage                                       */
/*==============================================================*/
create table comm_sysmessage
(
   message_id           varchar(64) not null comment '公告编号',
   title                varchar(300) comment '标题',
   content              text comment '内容',
   sender               varchar(64) comment '发送者',
   receiver             varchar(64) comment '接受者',
   sendtime             varchar(20) comment '发送时间',
   state                numeric(2,0) comment '状态',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (message_id)
);

alter table comm_sysmessage comment '系统消息';

/*==============================================================*/
/* table: dd_dictionary                                         */
/*==============================================================*/
create table dd_dictionary
(
   dataid               varchar(64) not null comment '字典编码',
   dtid                 varchar(64) comment '字典类型标识',
   dataname             varchar(300) comment '数据名称',
   tablename            varchar(64) comment '映射表名',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (dataid)
);

alter table dd_dictionary comment '数据字典';

/*==============================================================*/
/* table: dd_dictionarytype                                     */
/*==============================================================*/
create table dd_dictionarytype
(
   dtid                 varchar(64) not null comment '字典类型标识',
   dd__dtid             varchar(64) comment '字典类型标识',
   dtname               varchar(300) comment '字典类型名称',
   tablefield           varchar(300) comment '类型标识(表＿字段）',
   dtscope              numeric(2,0) comment '字典范围',
   issysdatadic         numeric(2,0) comment '是否系统数据字典',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (dtid)
);

alter table dd_dictionarytype comment '数据字典类型';

/*==============================================================*/
/* table: dd_exprop                                             */
/*==============================================================*/
create table dd_exprop
(
   propid               varchar(64) not null comment '属性id',
   dataid               varchar(64) comment '字典编码',
   proptype             numeric(8,0) default 0 comment '属性类型',
   timeformat           varchar(64) comment '时间格式',
   propname             varchar(300) comment '属性名称',
   moduel               varchar(64) comment '模式',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (propid)
);

alter table dd_exprop comment '扩展属性表';

/*==============================================================*/
/* table: dd_expropdata                                         */
/*==============================================================*/
create table dd_expropdata
(
   recordid             varchar(64) not null comment '记录id',
   propid               varchar(64) comment '属性id',
   fkid                 varchar(64) comment '对应字段id',
   propdata             varchar(2000) comment '属性数据',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (recordid)
);

alter table dd_expropdata comment '扩展属性数据表';

/*==============================================================*/
/* table: dd_exprop_system                                      */
/*==============================================================*/
create table dd_exprop_system
(
   propid               varchar(64) comment '属性id',
   appid                varchar(64) comment '应用系统编号'
);

alter table dd_exprop_system comment '扩展属性系统相关表';

/*==============================================================*/
/* table: odqe_dept                                             */
/*==============================================================*/
create table odqe_dept
(
   deptid               varchar(64) not null comment '部门编号',
   organid              varchar(64) comment '机构编号',
   areaid               varchar(64) comment '地区编号',
   odq_deptid           varchar(64) comment '部门编号',
   deptcode             varchar(64) comment '部门编码',
   deptname             varchar(300) comment '部门名称',
   deptalias            varchar(300) comment '部门别名',
   depttype             varchar(64) comment '部门类型',
   deptcharacter        varchar(64) comment '部门性质',
   isvalidation         numeric(2,0) comment '是否有效',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (deptid)
);

alter table odqe_dept comment '部门';

/*==============================================================*/
/* table: odqe_employee                                         */
/*==============================================================*/
create table odqe_employee
(
   employeeid           varchar(64) not null comment '员工编号',
   quarterid            varchar(64) comment '岗位编号',
   deptid               varchar(64) comment '部门编号',
   employeecode         varchar(300) comment '员工编码',
   employeename         varchar(100) comment '员工姓名',
   simplename           varchar(100) comment '姓名简拼',
   alias                varchar(100) comment '曾用名',
   sex                  numeric(2,0) comment '性别',
   nationality          varchar(64) comment '民族',
   birthday             varchar(20) comment '出生日期',
   nativeplace          varchar(50) comment '籍贯',
   identitycard         varchar(32) comment '身份证号',
   officephone          varchar(50) comment '办公电话',
   addressphone         varchar(50) comment '住址电话',
   movephone            varchar(50) comment '移动电话',
   fax                  varchar(50) comment '传真',
   email                varchar(50) comment '电子邮件',
   address              varchar(500) comment '地址',
   postalcode           varchar(16) comment '邮政编码',
   isvalidation         numeric(2,0) comment '是否有效',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (employeeid)
);

alter table odqe_employee comment '员工';

/*==============================================================*/
/* table: odqe_employee_quarter                                 */
/*==============================================================*/
create table odqe_employee_quarter
(
   quarterid            varchar(64) comment '岗位编号',
   employeeid           varchar(64) comment '员工编号'
);

alter table odqe_employee_quarter comment '员工岗位';

/*==============================================================*/
/* table: odqe_organ                                            */
/*==============================================================*/
create table odqe_organ
(
   organid              varchar(64) not null comment '机构编号',
   odq_organid          varchar(64) comment '机构编号',
   areaid               varchar(64) comment '地区编号',
   organcode            varchar(64) comment '机构编码',
   organname            varchar(300) comment '机构名称',
   organalias           varchar(300) comment '机构别名',
   organtype            varchar(64) comment '机构类型',
   postcode             numeric(10,0) comment '邮编',
   address              varchar(500) comment '地址',
   telephone            varchar(50) comment '单位电话',
   fax                  varchar(50) comment '传真',
   isvalidation         numeric(2,0) comment '是否有效',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (organid)
);

alter table odqe_organ comment '机构';

/*==============================================================*/
/* table: odqe_organ_appsystem                                  */
/*==============================================================*/
create table odqe_organ_appsystem
(
   appid                varchar(64) comment '应用系统编号',
   organid              varchar(64) comment '机构编号'
);

alter table odqe_organ_appsystem comment '机构系统';

/*==============================================================*/
/* table: odqe_quarter                                          */
/*==============================================================*/
create table odqe_quarter
(
   quarterid            varchar(64) not null comment '岗位编号',
   odq_quarterid        varchar(64) comment '岗位编号',
   deptid               varchar(64) comment '部门编号',
   quartercode          varchar(64) comment '岗位编码',
   quartername          varchar(300) comment '岗位名称',
   quartertype          varchar(300) comment '岗位类型',
   isvalidation         numeric(2,0) comment '是否有效',
   responsibility       varchar(300) comment '职责',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (quarterid)
);

alter table odqe_quarter comment '岗位';

/*==============================================================*/
/* table: pc_appsystem                                          */
/*==============================================================*/
create table pc_appsystem
(
   appid                varchar(64) not null comment '应用系统编号',
   appcode              varchar(300) comment '应用系统编码',
   appname              varchar(300) comment '应用系统名称',
   hasorgan             numeric(2,0) comment '是否拥有独立机构',
   haspopedom           numeric(2,0) comment '是否拥有独立权限',
   hasswaraj            numeric(2,0) comment '是否独立系统',
   appurl               varchar(100) comment '系统地址',
   firsturl             varchar(100) comment '首页地址',
   flashurl             varchar(500) comment 'flash地址',
   userparaname         varchar(50) comment '用户参数名',
   passparaname         varchar(50) comment '密码参数名',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (appid)
);

alter table pc_appsystem comment '应用系统';

/*==============================================================*/
/* table: pc_menu                                               */
/*==============================================================*/
create table pc_menu
(
   menuid               varchar(64) not null comment '菜单编号',
   appid                varchar(64) comment '应用系统编号',
   resourceid           varchar(64) comment '资源编号',
   pc__menuid           varchar(64) comment '菜单编号',
   menucode             varchar(300) comment '菜单编码',
   menuname             varchar(300) comment '菜单名称',
   target               numeric(2,0) comment '打开方式',
   menuicon             varchar(300) comment '图标',
   hidden               numeric(2,0) comment '是否隐藏',
   menutype             numeric(2,0) comment '类型',
   url                  varchar(300) comment '地址',
   isexpand             numeric(2,0) comment '是否展开',
   tabsname             varchar(64) comment '页签标题',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (menuid)
);

alter table pc_menu comment '菜单';

/*==============================================================*/
/* table: pc_module                                             */
/*==============================================================*/
create table pc_module
(
   moduleid             varchar(64) not null comment '模块编号',
   appid                varchar(64) comment '应用系统编号',
   pc__moduleid         varchar(64) comment '模块编号',
   modulecode           varchar(300) comment '模块编码',
   modulename           varchar(300) comment '模块名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (moduleid)
);

alter table pc_module comment '模块';

/*==============================================================*/
/* table: pc_operation                                          */
/*==============================================================*/
create table pc_operation
(
   operationid          varchar(64) not null comment '操作编号',
   operationcode        varchar(300) comment '操作编码',
   operationname        varchar(300) comment '操作名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (operationid)
);

alter table pc_operation comment '操作';

/*==============================================================*/
/* table: pc_permission                                         */
/*==============================================================*/
create table pc_permission
(
   permissionid         varchar(64) not null comment '权限编号',
   resourceid           varchar(64) comment '资源编号',
   operationid          varchar(64) comment '操作编号',
   permissioncode       varchar(300) comment '权限编码',
   permissionname       varchar(300) comment '权限名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (permissionid)
);

alter table pc_permission comment '权限
';

/*==============================================================*/
/* table: pc_permission_constraint                              */
/*==============================================================*/
create table pc_permission_constraint
(
   pc_id                varchar(64) not null comment '权限约束编号',
   permissionid         varchar(64) comment '权限编号',
   pc__permissionid     varchar(64) comment '权限编号',
   pc_code              varchar(300) comment '权限约束编码',
   pc_name              varchar(300) comment '权限约束名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (pc_id)
);

alter table pc_permission_constraint comment '权限约束';

/*==============================================================*/
/* table: pc_resource                                           */
/*==============================================================*/
create table pc_resource
(
   resourceid           varchar(64) not null comment '资源编号',
   moduleid             varchar(64) comment '模块编号',
   resourcecode         varchar(300) comment '资源标识',
   resourcename         varchar(300) comment '资源名称',
   resourcetype         numeric(2,0) comment '资源类型',
   resourceurl          varchar(500) comment '访问地址',
   allow_distribute     numeric(2,0) comment '是否可分配',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (resourceid)
);

alter table pc_resource comment '资源';

/*==============================================================*/
/* table: pc_shortcutmenu                                       */
/*==============================================================*/
create table pc_shortcutmenu
(
   shortcutmenuid       varchar(64) not null comment '快捷菜单编号',
   menuid               varchar(64) comment '菜单编号',
   userid               varchar(64) comment '用户编号',
   shortcutmenuname     varchar(300) comment '快捷菜单名称',
   url                  varchar(300) comment '地址',
   type                 numeric(2,0) comment '类型',
   menuicon             varchar(300) comment '图标',
   target               numeric(2,0) comment '打开方式',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (shortcutmenuid)
);

alter table pc_shortcutmenu comment '快捷菜单';

/*==============================================================*/
/* table: role                                                  */
/*==============================================================*/
create table role
(
   roleid               varchar(64) not null comment '角色编号',
   roletypeid           varchar(64) comment '角色类型编号',
   rol_roleid           varchar(64) comment '角色编号',
   rolecode             varchar(300) comment '角色编码',
   rolename             varchar(300) comment '角色名称',
   createrid            varchar(64) comment '创建人id',
   creatername          varchar(300) comment '创建人名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (roleid)
);

alter table role comment '角色';

/*==============================================================*/
/* table: role_constraint                                       */
/*==============================================================*/
create table role_constraint
(
   rcid                 varchar(64) not null comment '角色约束编号',
   roleid               varchar(64) comment '角色编号',
   rol_roleid           varchar(64) comment '角色编号',
   rctype               numeric(2,0) comment '约束类型 ',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (rcid)
);

alter table role_constraint comment '角色约束';

/*==============================================================*/
/* table: role_delegate                                         */
/*==============================================================*/
create table role_delegate
(
   drid                 varchar(64) not null comment '编号',
   userid               varchar(64) comment '用户编号',
   permissionid         varchar(64) comment '权限编号',
   use_userid           varchar(64) comment '用户编号',
   starttime            varchar(20) comment '开始时间',
   endtime              varchar(20) comment '收回时间',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (drid)
);

alter table role_delegate comment '代理角色';

/*==============================================================*/
/* table: role_dept                                             */
/*==============================================================*/
create table role_dept
(
   deptid               varchar(64) comment '部门编号',
   roleid               varchar(64) comment '角色编号'
);

alter table role_dept comment '部门角色';

/*==============================================================*/
/* table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   roleid               varchar(64) comment '角色编号',
   permissionid         varchar(64) comment '权限编号'
);

alter table role_permission comment '角色权限';

/*==============================================================*/
/* table: role_quarter                                          */
/*==============================================================*/
create table role_quarter
(
   roleid               varchar(64) comment '角色编号',
   quarterid            varchar(64) comment '岗位编号'
);

alter table role_quarter comment '岗位角色';

/*==============================================================*/
/* table: role_type                                             */
/*==============================================================*/
create table role_type
(
   roletypeid           varchar(64) not null comment '角色类型编号',
   rol_roletypeid       varchar(64) comment '角色类型编号',
   roletypecode         varchar(300) comment '角色类型编码',
   roletypename         varchar(300) comment '角色类型名称',
   type                 numeric(2,0) comment '管理还是业务型',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (roletypeid)
);

alter table role_type comment '角色类型';

/*==============================================================*/
/* table: role_user                                             */
/*==============================================================*/
create table role_user
(
   roleid               varchar(64) comment '角色编号',
   userid               varchar(64) comment '用户编号'
);

alter table role_user comment '用户角色';

/*==============================================================*/
/* table: role_usergroup                                        */
/*==============================================================*/
create table role_usergroup
(
   roleid               varchar(64) comment '角色编号',
   groupid              varchar(64) comment '用户组编号'
);

alter table role_usergroup comment '用户组角色';

/*==============================================================*/
/* table: usergroup                                             */
/*==============================================================*/
create table usergroup
(
   groupid              varchar(64) not null comment '用户组编号',
   use_groupid          varchar(64) comment '用户组编号',
   groupcode            varchar(300) comment '用户组编码',
   groupname            varchar(300) comment '用户组名称',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (groupid)
);

alter table usergroup comment '用户组';

/*==============================================================*/
/* table: user_appusermapp                                      */
/*==============================================================*/
create table user_appusermapp
(
   usermapid            varchar(64) not null comment '存储编号',
   appid                varchar(64) comment '应用系统编号',
   userid               varchar(64) comment '用户编号',
   username             varchar(300) comment '用户名',
   userpass             varchar(300) comment '用户密码',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (usermapid)
);

alter table user_appusermapp comment '应用用户映射表';

/*==============================================================*/
/* table: user_info                                             */
/*==============================================================*/
create table user_info
(
   userid               varchar(64) not null comment '用户编号',
   appid                varchar(64) comment '应用系统编号',
   styleid              varchar(64) comment '样式编号',
   employeeid           varchar(64) comment '员工编号',
   layoutid             varchar(64) comment '布局编号',
   account              varchar(300) comment '用户帐号',
   password             varchar(300) comment '用户密码',
   loginmodel           numeric(8,0) comment '登录模式',
   allow_repeat_login   numeric(2,0) comment '是否可重复登录',
   enadled              numeric(2,0) comment '是否禁用',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (userid)
);

alter table user_info comment '用户';

/*==============================================================*/
/* table: user_layout                                           */
/*==============================================================*/
create table user_layout
(
   layoutid             varchar(64) not null comment '布局编号',
   layoutname           varchar(300) comment '布局名称',
   isvalidation         numeric(2,0) comment '是否有效',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (layoutid)
);

alter table user_layout comment '布局';

/*==============================================================*/
/* table: user_mapp                                             */
/*==============================================================*/
create table user_mapp
(
   usermappid           varchar(64) not null comment '用户映射编号',
   userid               varchar(64) comment '用户编号',
   appid                varchar(64) comment '应用系统编号',
   mppaccount           varchar(300) comment '用户帐号',
   mpppassword          varchar(300) comment '用户密码',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (usermappid)
);

alter table user_mapp comment '用户映射';

/*==============================================================*/
/* table: user_panel                                            */
/*==============================================================*/
create table user_panel
(
   panelid              varchar(64) not null comment '编号',
   layoutid             varchar(64) comment '布局编号',
   panelname            varchar(300) comment '名称',
   paneltitle           varchar(300) comment '标题',
   panelurl             varchar(300) comment '地址',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (panelid)
);

alter table user_panel comment '面版';

/*==============================================================*/
/* table: user_style                                            */
/*==============================================================*/
create table user_style
(
   styleid              varchar(64) not null comment '样式编号',
   stylename            varchar(300) comment '样式名称',
   folder               varchar(300) comment '目录',
   isvalidation         numeric(2,0) comment '是否有效',
   orderno              numeric(8,0) comment '排序号',
   remark               varchar(4000) comment '备注',
   primary key (styleid)
);

alter table user_style comment '样式';

/*==============================================================*/
/* table: user_usergroup                                        */
/*==============================================================*/
create table user_usergroup
(
   groupid              varchar(64) comment '用户组编号',
   userid               varchar(64) comment '用户编号'
);

