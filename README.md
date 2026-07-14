# 人事管理系统（angongye）

《Spring + MyBatis 企业应用实战》**第 14 章 实战项目：人事管理系统**课程作业。

技术栈：**Spring MVC + MyBatis + JSP + Bootstrap 5 + MySQL 8**，Maven 构建，War 包部署到 Tomcat。

## 一、作业要求与完成情况

> 要求：完成 **2 个表**的增删改查，自己设计管理页面。

本项目围绕人事管理场景，完整实现了两张业务表的增删改查（CRUD），并带有分页与管理页面：

| 模块 | 数据表 | 功能 | 页面 |
| ---- | ---- | ---- | ---- |
| **部门管理** | `t_dept` | 列表（分页）/ 新增 / 修改 / 删除 / 详情 | `webapp/dept/*.jsp` |
| **员工管理** | `t_emp` | 列表（分页）/ 新增 / 修改 / 删除 / 详情 | `webapp/emp/*.jsp` |

此外还包含登录 / 注册 / 验证码 / 加盐 MD5 加密（`t_login` 表）等基础功能。
员工与部门通过 `emp_dept_id` 关联，员工新增 / 修改页面提供**部门下拉框**，列表与详情页显示**部门名称**。

### 采用书中的 DAO 模式 + HrmService 门面（第 14.3 / 14.4 节）

按教材要求，持久层采用 **DAO 组件 + 业务门面** 结构，个人作业选取 **部门、员工两个 DAO** 实现：

- **DAO 组件**（`com.angongye.dao`）：`DeptDao`、`EmployeeDao`。使用 MyBatis **注解式**映射
  （`@Select` / `@Delete` 写静态 SQL；`@InsertProvider` / `@UpdateProvider` 配合
  `com.angongye.dao.provider` 下的 `DeptDynaSqlProvider`、`EmployeeDynaSqlProvider`，
  用 `org.apache.ibatis.jdbc.SQL` 构建器**动态拼接** SQL）。
- **公共常量类** `com.angongye.util.common.HrmConstants`：集中管理表名等常量。
- **业务门面** `com.angongye.service.HrmService`（实现类 `HrmServiceImpl`）：作为**唯一的业务逻辑组件**，
  门面式封装上述 DAO，向上提供 `findAllDept`、`findDeptById`、`addDept`、`modifyDept`、`removeDept`、
  `findAllEmployee`、`findEmployeeById`、`addEmployee`、`modifyEmployee`、`removeEmployee` 等业务方法
  （编号唯一校验、"部门下有员工不可删除"等业务规则都在此实现）。

> 调用链：`Controller → HrmService（门面） → DeptDao / EmployeeDao（DAO） → 数据库`。
> `DeptDao` 与 `EmployeeDao` 通过 `applicationContext.xml` 里 `MapperScannerConfigurer`
> 的 `basePackage="com.angongye.mapper,com.angongye.dao"` 一并被 Spring 扫描为 DAO 代理对象。

## 二、快速开始（导入 IDEA 后运行）

### 1. 准备数据库

在 MySQL 8 中执行项目自带的建库脚本 `src/main/resources/angongye.sql`：

```bash
mysql -u root -p < src/main/resources/angongye.sql
```

（也可以直接在 Navicat / IDEA 的 Database 工具中打开该文件并运行。）
脚本会自动创建数据库 `angongye`、三张表（`t_dept`、`t_emp`、`t_login`）以及演示数据。

### 2. 修改数据库连接

打开 `src/main/resources/db.properties`，把用户名 / 密码改成你本机 MySQL 的账号：

```properties
db.mysql.url=jdbc:mysql://localhost:3306/angongye?allowPublicKeyRetrieval=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true
db.mysql.username=root
db.mysql.password=你的MySQL密码
```

### 3. 运行项目

项目 `pom.xml` 已内置 `tomcat9-maven-plugin`（端口 **8088**，上下文路径 `/`）。两种运行方式任选其一：

**方式 A：命令行 / IDEA Maven 面板（推荐，最省事）**

```bash
mvn clean tomcat9:run
```

**方式 B：IDEA 配置本地 Tomcat**

`Run → Edit Configurations → + → Tomcat Server (Local)`，
在 Deployment 中添加 `angongye:war exploded`，Application context 设为 `/`，启动即可。

### 4. 访问系统

浏览器打开：<http://localhost:8088/>

- 默认账号：**zhangsan**
- 默认密码：**123456**
- 登录页需输入图片验证码（纯数字）。

登录后在左侧菜单「人事管理」下即可进入 **部门管理** 与 **员工管理**。

## 三、环境说明

- **JDK**：项目按 Java 8 编译（`maven.compiler` 设为 1.8），推荐使用 **JDK 8**（与 Spring 4.2 最匹配）；
  Lombok 已升级到 `1.18.34`，因此用 JDK 8 ~ JDK 21 均可正常编译。
- **Maven**：3.6+。
- **MySQL**：8.x（驱动 `mysql-connector-java 8.0.29`）。
- **Tomcat**：9（或使用内置的 `tomcat9-maven-plugin`）。

## 四、目录结构

```
src/main/java/com/angongye
├── controller          # DeptController / EmpController / LoginController
├── service             # 业务接口（含门面 HrmService）
│   └── impl            # 业务实现（含 HrmServiceImpl 门面实现）
├── dao                 # ★第14章 DAO 组件：DeptDao / EmployeeDao（注解式）
│   └── provider        # ★动态 SQL 提供类：DeptDynaSqlProvider / EmployeeDynaSqlProvider
├── mapper              # 早期 MyBatis Mapper 接口 + XML（Login 等仍在使用）
├── entity              # 实体类 Dept / Emp / Login
├── module              # 通用响应对象 MyResponse
├── util/common         # ★HrmConstants 公共常量类
├── filter              # 登录过滤器
└── utils               # MD5 加密、XML 读取工具
src/main/resources      # applicationContext.xml / springmvc-config.xml / mybatis-config.xml / db.properties / angongye.sql
src/main/webapp         # dept、emp 管理页面，login/main/register 页面，css/js/img
```

> 说明：`dao` 包是本次按教材新增的 DAO 门面架构（部门、员工两张表）；
> `mapper` 包是项目早期的写法，登录模块仍在使用，两者可并存。
