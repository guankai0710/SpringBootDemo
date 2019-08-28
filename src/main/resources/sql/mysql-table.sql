------------------------------------
---表名：DEMO_PERSON（人员信息）
------------------------------------
CREATE TABLE DEMO_PERSON
(
	ID         		VARCHAR(32) NOT NULL COMMENT '人员编号',
	NAME       		VARCHAR(10) COMMENT '姓名',
	DEPARTMENT_ID 	VARCHAR(32) COMMENT '部门编号'
	AGE        		VARCHAR(3) COMMENT '年龄',
	SEX       	 	VARCHAR(1) COMMENT '性别（0：女，1：男）',
	TEL        		VARCHAR(20) COMMENT '电话',
	ADDRESS    		VARCHAR(20) COMMENT '地址',
	PRIMARY_KEY (ID)
)COMMENT='人员信息';

------------------------------------
---表名：DEMO_DEPARTMENT（部门信息）
------------------------------------
CREATE TABLE DEMO_DEPARTMENT
(
	ID         		VARCHAR(32) NOT NULL COMMENT '部门编号',
	PARENT_ID       VARCHAR(32) NOT NULL COMMENT '父级编号',
	NAME       		VARCHAR(10) COMMENT '部门名称',
	PRIMARY_KEY (ID)
)COMMENT='部门信息';