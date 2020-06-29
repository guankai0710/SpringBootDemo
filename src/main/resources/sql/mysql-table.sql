------------------------------------
---表名：DEMO_USER（用户信息）
------------------------------------
CREATE TABLE DEMO_USER
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '用户编号',
	USER_NAME       VARCHAR(64) NOT NULL COMMENT '用户名称',
	ACCOUNT       	VARCHAR(64) NOT NULL COMMENT '账号',
	PASSWORD       	VARCHAR(64) NOT NULL COMMENT '密码',
	DELETED         INT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	CREATE_TIME     TIMESTAMP COMMENT '创建时间',
	UPDATE_TIME     TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';


------------------------------------
---表名：DEMO_USER（用户-角色中间表）
------------------------------------
CREATE TABLE DEMO_USER_ROLE
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '主键',
	USER_ID         INT NOT NULL COMMENT '用户编号',
	ROLE_ID         INT NOT NULL COMMENT '角色编号',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色中间表';


------------------------------------
---表名：DEMO_ROLE（角色信息）
------------------------------------
CREATE TABLE DEMO_ROLE
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '角色编号',
	ROLE_NAME       VARCHAR(64) NOT NULL COMMENT '角色名称',
	ROLE_NAME_ZH    VARCHAR(64) NOT NULL COMMENT '角色中文名称',
	ROLE_DESC       VARCHAR(1024) COMMENT '角色描述',
	DELETED         INT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	CREATE_TIME     TIMESTAMP COMMENT '创建时间',
	UPDATE_TIME     TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';


------------------------------------
---表名：DEMO_ROLE_RESOURCE（角色-权限中间表）
------------------------------------
CREATE TABLE DEMO_ROLE_RESOURCE
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '主键',
	ROLE_ID         INT NOT NULL COMMENT '角色编号',
	RESOURCE_ID     INT NOT NULL COMMENT '权限编号',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限中间表';


------------------------------------
---表名：DEMO_RESOURCE（权限信息）
------------------------------------
CREATE TABLE DEMO_RESOURCE
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '权限编号',
	URL             VARCHAR(64) NOT NULL COMMENT '地址',
	RES_NAME        VARCHAR(64) NOT NULL COMMENT '权限名称',
	RES_DESC        VARCHAR(1024) COMMENT '权限描述',
	DELETED         INT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	CREATE_TIME     TIMESTAMP COMMENT '创建时间',
	UPDATE_TIME     TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息';


------------------------------------
---表名：DEMO_PERSON（人员信息）
------------------------------------
CREATE TABLE DEMO_PERSON
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '人员编号',
	NAME       		  VARCHAR(32) COMMENT '姓名',
	ACCOUNT       	VARCHAR(64) NOT NULL COMMENT '账号',
	PASSWORD       	VARCHAR(64) NOT NULL COMMENT '密码',
	AGE        		  INT COMMENT '年龄',
	SEX       	 	  VARCHAR(1) COMMENT '性别（0：女，1：男）',
	TEL        		  VARCHAR(20) COMMENT '电话',
	EMAIL        		VARCHAR(35) COMMENT '邮箱',
	ADDRESS    		  VARCHAR(20) COMMENT '地址',
	DELETED         INT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	CREATE_TIME     TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息';


------------------------------------
---表名：DEMO_DEPARTMENT（部门信息）
------------------------------------
CREATE TABLE DEMO_DEPARTMENT
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '部门编号',
	PARENT_ID       INT NOT NULL COMMENT '父级编号',
	NAME       		  VARCHAR(10) COMMENT '部门名称',
	DELETED         INT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	CREATE_TIME     TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息';


------------------------------------
---表名：DEMO_CATEGORY（类别信息）
------------------------------------
CREATE TABLE DEMO_CATEGORY
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '类别编号',
	PARENT_ID       INT NOT NULL COMMENT '父级编号',
	NAME       		  VARCHAR(10) COMMENT '类别名称',
	DELETED         INT NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除；1：已删除）',
	CREATE_TIME     TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类别信息';


