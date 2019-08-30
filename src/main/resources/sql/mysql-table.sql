------------------------------------
---表名：DEMO_DEPARTMENT（部门信息）
------------------------------------
CREATE TABLE DEMO_DEPARTMENT
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '部门编号',
	PARENT_ID       INT NOT NULL COMMENT '父级编号',
	NAME       		  VARCHAR(10) COMMENT '部门名称',
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8 COMMENT='部门信息';


------------------------------------
---表名：DEMO_PERSON（人员信息）
------------------------------------
CREATE TABLE DEMO_PERSON
(
	ID         		  INT AUTO_INCREMENT NOT NULL COMMENT '人员编号',
	NAME       		  VARCHAR(10) COMMENT '姓名',
	ACCOUNT       	VARCHAR(10) NOT NULL COMMENT '账号',
	PASSWORD       	VARCHAR(10) NOT NULL COMMENT '密码',
	DEPARTMENT_ID 	INT COMMENT '部门编号',
	AGE        		  INT COMMENT '年龄',
	SEX       	 	  VARCHAR(1) COMMENT '性别（0：女，1：男）',
	TEL        		  VARCHAR(20) COMMENT '电话',
	EMAIL        		  VARCHAR(35) COMMENT '邮箱',
	ADDRESS    		  VARCHAR(20) COMMENT '地址',
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8 COMMENT='人员信息';

