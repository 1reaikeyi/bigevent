# BigEvent 大事件管理系统

一个基于 Spring Boot + Vue 3 的现代化文章管理系统，提供用户认证、文章管理、分类管理等核心功能。

## 技术栈

### 后端技术
| 技术 | 版本 | 说明 |
| :--- | :--- | :--- |
| Spring Boot | 3.3.8 | 后端框架 |
| Java | 17 | 编程语言 |
| MyBatis Plus | 3.5.9 | ORM框架 |
| MySQL | - | 关系型数据库 |
| Redis | - | 缓存数据库 |
| JJWT | 0.12.6 | JWT认证 |
| Lombok | - | 代码简化 |

### 前端技术
| 技术 | 版本 | 说明 |
| :--- | :--- | :--- |
| Vue | 3.5.32 | 前端框架 |
| Element Plus | 2.13.7 | UI组件库 |
| Pinia | 3.0.4 | 状态管理 |
| Vue Router | 4.6.4 | 路由管理 |
| Vite | 8.0.8 | 构建工具 |
| Axios | 1.15.1 | HTTP客户端 |

## 功能特性

- **用户管理**：用户注册、登录、个人信息修改、头像上传、密码修改
- **文章管理**：文章列表展示、发布文章、编辑文章、删除文章
- **分类管理**：分类创建、编辑、删除、列表展示
- **JWT认证**：基于Token的无状态认证机制
- **图片上传**：支持文章封面和用户头像上传

## 项目结构

```
big_event/
├── 后端/                              # 后端代码
│   └── title/
│       ├── src/main/java/
│       │   ├── common/                # 常量配置
│       │   ├── pojo/                  # 实体类
│       │   ├── repository/            # 数据访问层
│       │   ├── service/               # 业务逻辑层
│       │   └── web/                   # 控制层及配置
│       └── src/main/resources/        # 配置文件
├── 前端/                              # 前端代码
│   └── title/
│       ├── src/
│       │   ├── api/                   # API接口
│       │   ├── router/                # 路由配置
│       │   ├── stores/                # 状态管理
│       │   ├── utils/                 # 工具函数
│       │   └── views/                 # 页面组件
│       └── package.json
├── database/                          # 数据库脚本
│   └── sql.txt                        # 创建表语句
└── docx/                              # 文档
    ├── 大事件接口文档.md               # API接口文档
    └── 测试postman.txt               # Postman测试用例
```

## 快速开始

### 环境要求

- JDK 17+
- Node.js 20.19.0+ 或 22.12.0+
- MySQL 8.0+
- Redis 7.0+

### 后端启动

1. **创建数据库**

```sql
CREATE DATABASE IF NOT EXISTS use_big_event;
```

2. **执行数据库脚本**

```bash
mysql -u root -p use_big_event < database/sql.txt
```

3. **配置数据库连接**

修改 `后端/title/src/main/resources/application-dev.yml` 文件，配置数据库和Redis连接信息。

4. **启动后端服务**

```bash
cd 后端/title
mvn spring-boot:run
```

服务启动后访问：http://localhost:8080

### 前端启动

1. **安装依赖**

```bash
cd 前端/title
npm install
```

2. **启动开发服务器**

```bash
npm run dev
```

访问地址：http://localhost:5173

## API接口

### 用户接口

| 方法 | 路径 | 说明 |
| :--- | :--- | :--- |
| POST | `/api/user/register` | 用户注册 |
| POST | `/api/user/login` | 用户登录 |
| GET | `/api/user/info` | 获取用户信息 |
| PUT | `/api/user/update` | 更新用户信息 |
| PUT | `/api/user/updateAvatar` | 更新头像 |
| PUT | `/api/user/updatePwd` | 修改密码 |

### 分类接口

| 方法 | 路径 | 说明 |
| :--- | :--- | :--- |
| POST | `/api/category` | 创建分类 |
| GET | `/api/category` | 获取分类列表 |
| GET | `/api/category/{id}` | 获取分类详情 |
| PUT | `/api/category/{id}` | 更新分类 |
| DELETE | `/api/category/{id}` | 删除分类 |

### 文章接口

| 方法 | 路径 | 说明 |
| :--- | :--- | :--- |
| POST | `/api/article` | 发布文章 |
| GET | `/api/article` | 获取文章列表 |
| GET | `/api/article/{id}` | 获取文章详情 |
| PUT | `/api/article/{id}` | 更新文章 |
| DELETE | `/api/article/{id}` | 删除文章 |

## 数据库表结构

### user（用户表）
| 字段 | 类型 | 说明 |
| :--- | :--- | :--- |
| id | bigint | 主键ID |
| username | varchar(20) | 用户名 |
| password | varchar(32) | 密码 |
| nickname | varchar(10) | 昵称 |
| email | varchar(128) | 邮箱 |
| user_pic | varchar(128) | 头像 |
| create_time | datetime | 创建时间 |
| update_time | datetime | 修改时间 |

### category（分类表）
| 字段 | 类型 | 说明 |
| :--- | :--- | :--- |
| id | bigint | 主键ID |
| category_name | varchar(32) | 分类名称 |
| category_alias | varchar(32) | 分类别名 |
| create_user | bigint | 创建人ID |
| create_time | datetime | 创建时间 |
| update_time | datetime | 修改时间 |

### article（文章表）
| 字段 | 类型 | 说明 |
| :--- | :--- | :--- |
| id | bigint | 主键ID |
| title | varchar(30) | 文章标题 |
| content | varchar(10000) | 文章内容 |
| cover_img | varchar(128) | 封面图片 |
| state | varchar(3) | 状态（已发布/草稿） |
| category_id | bigint | 分类ID |
| create_user | bigint | 创建人ID |
| create_time | datetime | 创建时间 |
| update_time | datetime | 修改时间 |

## 开发说明

### 后端开发

```bash
# 运行测试
cd 后端/title
mvn test

# 打包构建
mvn clean package
```

### 前端开发

```bash
# 开发模式
npm run dev

# 生产构建
npm run build

# 预览构建结果
npm run preview
```

## 许可证

MIT License
