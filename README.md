# NovelTool - 小说生成器

一个基于Spring Boot的小说管理系统，提供小说的创建、查询、更新和删除功能。

## 技术栈

- **Java**: 21
- **Spring Boot**: 3.2.0
- **Spring Data JPA**: 数据持久化
- **H2 Database**: 内存数据库（开发环境）
- **MySQL**: 关系型数据库（生产环境）
- **Lombok**: 简化Java代码
- **Maven**: 项目构建工具

## 项目结构

```
NovelTool/
├── src/
│   ├── main/
│   │   ├── java/com/noveltool/
│   │   │   ├── controller/      # 控制器层
│   │   │   ├── service/         # 服务层
│   │   │   ├── repository/      # 数据访问层
│   │   │   ├── entity/          # 实体类
│   │   │   ├── dto/             # 数据传输对象
│   │   │   ├── exception/       # 异常处理
│   │   │   └── NovelToolApplication.java  # 主启动类
│   │   └── resources/
│   │       └── application.yml  # 配置文件
│   └── test/                    # 测试代码
├── pom.xml                      # Maven配置文件
└── README.md                    # 项目说明文档
```

## 功能特性

- ✅ 小说的CRUD操作（创建、读取、更新、删除）
- ✅ 根据标题搜索小说
- ✅ 根据作者查询小说
- ✅ 根据分类查询小说
- ✅ 统一的API响应格式
- ✅ 参数验证
- ✅ 全局异常处理
- ✅ RESTful API设计

## 快速开始

### 环境要求

- JDK 21+
- Maven 3.6+

### 运行项目

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd NovelTool
   ```

2. **构建项目**
   ```bash
   mvn clean install
   ```

3. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

4. **访问应用**
   - API地址: http://localhost:8080/api
   - H2控制台: http://localhost:8080/api/h2-console
     - JDBC URL: `jdbc:h2:mem:noveldb`
     - 用户名: `sa`
     - 密码: (空)

## API文档

### 小说管理接口

#### 1. 获取所有小说
```http
GET /api/novels
```

**响应示例:**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "title": "示例小说",
      "content": "小说内容...",
      "author": "作者名",
      "category": "玄幻"
    }
  ]
}
```

#### 2. 根据ID获取小说
```http
GET /api/novels/{id}
```

#### 3. 创建小说
```http
POST /api/novels
Content-Type: application/json

{
  "title": "新小说",
  "content": "小说内容",
  "author": "作者",
  "category": "分类"
}
```

#### 4. 更新小说
```http
PUT /api/novels/{id}
Content-Type: application/json

{
  "title": "更新后的标题",
  "content": "更新后的内容",
  "author": "作者",
  "category": "分类"
}
```

#### 5. 删除小说
```http
DELETE /api/novels/{id}
```

#### 6. 根据标题搜索
```http
GET /api/novels/search?title=关键词
```

#### 7. 根据作者查询
```http
GET /api/novels/author/{author}
```

#### 8. 根据分类查询
```http
GET /api/novels/category/{category}
```

## 数据库配置

### 开发环境（H2）

项目默认使用H2内存数据库，无需额外配置。

### 生产环境（MySQL）

修改 `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/novel_db?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: your_username
    password: your_password
  
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
```

创建MySQL数据库:
```sql
CREATE DATABASE novel_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## 配置说明

主要配置项位于 `application.yml`:

- `server.port`: 服务端口（默认8080）
- `server.servlet.context-path`: API路径前缀（默认/api）
- `spring.datasource.*`: 数据库配置
- `spring.jpa.*`: JPA配置

## 开发指南

### 代码结构

- **Controller**: 处理HTTP请求，调用Service层
- **Service**: 业务逻辑处理
- **Repository**: 数据访问接口
- **Entity**: JPA实体类
- **DTO**: 数据传输对象，用于API接口

### 添加新功能

1. 在 `entity` 包中创建实体类
2. 在 `repository` 包中创建Repository接口
3. 在 `service` 包中创建Service类
4. 在 `controller` 包中创建Controller类
5. 在 `dto` 包中创建DTO类（如需要）

## 测试

运行测试:
```bash
mvn test
```

## 许可证

本项目采用 MIT 许可证。详见 [LICENSE.md](LICENSE.md)

## 贡献

欢迎提交Issue和Pull Request！

## 作者

NovelTool Team

## 更新日志

### v1.0.0
- 初始版本发布
- 实现基本的小说CRUD功能
- 支持按标题、作者、分类查询