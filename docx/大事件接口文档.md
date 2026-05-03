# 大事件接口文档-V1.0

## 1. 用户管理控制器

### 1.1 用户注册

#### 1.1.1 基本信息

> 请求路径：/user/register
>
> 请求方式：POST
>
> 接口描述：该接口用于注册新用户

#### 1.1.2 请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 用户ID | number | 否       | 默认为0 |
| userName | 用户名 | string | 是       |      |
| nickName | 昵称   | string | 否       |      |
| email    | 邮箱   | string | 否       |      |
| userPic  | 用户头像 | string | 否       |      |
| createTime | 创建时间 | string | 否       | 格式：yyyy-MM-dd HH:mm:ss |
| updateTime | 更新时间 | string | 否       | 格式：yyyy-MM-dd HH:mm:ss |

请求数据样例：

```shell
userName=zhangsan&nickName=张三&email=zhangsan@example.com
```

#### 1.1.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

### 1.2 用户登录

#### 1.2.1 基本信息

> 请求路径：/user/login
>
> 请求方式：POST
>
> 接口描述：该接口用于用户登录

#### 1.2.2 请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| userName | 用户名 | string | 是       |                |
| password | 密码   | string | 是       |                |

请求数据样例：

```shell
userName=zhangsan&password=123456
```

#### 1.2.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | string | 必须     |        | 返回的数据,jwt令牌    |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### 1.2.4 备注说明

> 用户登录成功后，系统会自动下发JWT令牌，然后在后续的每次请求中，浏览器都需要在请求头header中携带到服务端，请求头的名称为 Authorization，值为 Bearer + 空格 + 登录时下发的JWT令牌。
>
> 如果检测到用户未登录，则http响应状态码为401

### 1.3 获取用户信息

#### 1.3.1 基本信息

> 请求路径：/user/:id
>
> 请求方式：GET
>
> 接口描述：该接口用于根据用户ID获取用户信息

#### 1.3.2 请求参数

请求参数格式：路径参数

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 用户ID | number | 是       |      |

请求数据样例：

```
GET /user/1
```

#### 1.3.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  |
| ------------- | ------ | -------- | ------ | --------------------- |
| code          | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message       | string | 非必须   |        | 提示信息              |
| data          | object | 必须     |        | 返回的数据            |
| \|-id         | number | 非必须   |        | 主键ID                |
| \|-userName   | string | 非必须   |        | 用户名                |
| \|-nickName   | string | 非必须   |        | 昵称                  |
| \|-email      | string | 非必须   |        | 邮箱                  |
| \|-userPic    | string | 非必须   |        | 头像地址              |
| \|-createTime | string | 非必须   |        | 创建时间              |
| \|-updateTime | string | 非必须   |        | 更新时间              |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "id": 1,
        "userName": "zhangsan",
        "nickName": "张三",
        "email": "zhangsan@example.com",
        "userPic": "",
        "createTime": "2023-09-02 22:21:31",
        "updateTime": "2023-09-02 22:21:31"
    }
}
```

### 1.4 更新用户信息

#### 1.4.1 基本信息

> 请求路径：/user
>
> 请求方式：PUT
>
> 接口描述：该接口用于更新用户信息

#### 1.4.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 用户ID | number | 是       |      |
| userName | 用户名 | string | 否       |      |
| nickName | 昵称   | string | 否       |      |
| email    | 邮箱   | string | 否       |      |
| userPic  | 用户头像 | string | 否       |      |

请求数据样例：

```json
{
    "id": 1,
    "userName": "zhangsan",
    "nickName": "张三",
    "email": "zhangsan@example.com",
    "userPic": ""
}
```

#### 1.4.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

### 1.5 更新用户密码

#### 1.5.1 基本信息

> 请求路径：/user/updatePwd
>
> 请求方式：PATCH
>
> 接口描述：该接口用于更新用户密码

#### 1.5.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| oldPwd   | 旧密码 | string | 是       |      |
| newPwd   | 新密码 | string | 是       |      |

请求数据样例：

```json
{
    "oldPwd": "123456",
    "newPwd": "654321"
}
```

#### 1.5.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

---

## 2. 文章管理控制器

负责文章的增删改查、状态管理、分类关联等核心功能，提供完整的文章管理API接口，支持JWT认证和权限控制。

### 2.1 添加文章

#### 2.1.1 基本信息

> 请求路径：/article
>
> 请求方式：POST
>
> 接口描述：该接口用于添加文章

#### 2.1.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称   | 说明         | 类型   | 是否必须 | 备注           |
| ---------- | ------------ | ------ | -------- | -------------- |
| id         | 文章ID       | number | 否       |                |
| title      | 文章标题     | string | 是       |                |
| content    | 文章内容     | string | 是       |                |
| coverImg   | 封面图片地址 | string | 否       |                |
| state      | 状态         | string | 否       |                |
| categoryId | 分类ID       | number | 否       |                |
| createUser | 创建用户ID   | number | 否       |                |
| updateUser | 更新用户ID   | number | 否       |                |

请求数据样例：

```json
{
    "title": "文章标题",
    "content": "文章内容",
    "coverImg": "https://example.com/image.jpg",
    "state": "published",
    "categoryId": 1
}
```

#### 2.1.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

### 2.2 获取所有文章

#### 2.2.1 基本信息

> 请求路径：/article/list
>
> 请求方式：GET
>
> 接口描述：该接口用于获取所有文章列表

#### 2.2.2 请求参数

无

#### 2.2.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  |
| ------------- | ------ | -------- | ------ | --------------------- |
| code          | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message       | string | 非必须   |        | 提示信息              |
| data          | array  | 必须     |        | 返回的数据列表        |
| \|-id         | number | 非必须   |        | 文章ID                |
| \|-title      | string | 非必须   |        | 文章标题              |
| \|-content    | string | 非必须   |        | 文章内容              |
| \|-coverImg   | string | 非必须   |        | 封面图片地址          |
| \|-state      | string | 非必须   |        | 状态                  |
| \|-categoryId | number | 非必须   |        | 分类ID                |
| \|-createTime | string | 非必须   |        | 创建时间              |
| \|-updateTime | string | 非必须   |        | 更新时间              |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": [
        {
            "id": 1,
            "title": "文章标题",
            "content": "文章内容",
            "coverImg": "https://example.com/image.jpg",
            "state": "published",
            "categoryId": 1,
            "createTime": "2023-09-02 22:21:31",
            "updateTime": "2023-09-02 22:21:31"
        }
    ]
}
```

### 2.3 获取分页文章列表

#### 2.3.1 基本信息

> 请求路径：/article
>
> 请求方式：GET
>
> 接口描述：该接口用于分页获取文章列表

#### 2.3.2 请求参数

请求参数格式：queryString

请求参数说明：

| 参数名称 | 说明     | 类型   | 是否必须 | 备注 |
| -------- | -------- | ------ | -------- | ---- |
| pageNum  | 页码     | number | 是       |      |
| pageSize | 每页数量 | number | 是       |      |

请求数据样例：

```shell
pageNum=1&pageSize=10
```

#### 2.3.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  |
| ------------- | ------ | -------- | ------ | --------------------- |
| code          | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message       | string | 非必须   |        | 提示信息              |
| data          | object | 必须     |        | 返回的数据            |
| \|-total      | number | 必须     |        | 总记录数              |
| \|-items      | array  | 必须     |        | 当前页数据列表        |
| \|-id         | number | 非必须   |        | 文章ID                |
| \|-title      | string | 非必须   |        | 文章标题              |
| \|-content    | string | 非必须   |        | 文章内容              |
| \|-coverImg   | string | 非必须   |        | 封面图片地址          |
| \|-state      | string | 非必须   |        | 状态                  |
| \|-categoryId | number | 非必须   |        | 分类ID                |
| \|-createTime | string | 非必须   |        | 创建时间              |
| \|-updateTime | string | 非必须   |        | 更新时间              |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "total": 100,
        "items": [
            {
                "id": 1,
                "title": "文章标题",
                "content": "文章内容",
                "coverImg": "https://example.com/image.jpg",
                "state": "published",
                "categoryId": 1,
                "createTime": "2023-09-02 22:21:31",
                "updateTime": "2023-09-02 22:21:31"
            }
        ]
    }
}
```

### 2.4 获取单个文章

#### 2.4.1 基本信息

> 请求路径：/article/:id
>
> 请求方式：GET
>
> 接口描述：该接口用于根据文章ID获取文章详情

#### 2.4.2 请求参数

请求参数格式：路径参数

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 文章ID | number | 是       |      |

请求数据样例：

```
GET /article/1
```

#### 2.4.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  |
| ------------- | ------ | -------- | ------ | --------------------- |
| code          | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message       | string | 非必须   |        | 提示信息              |
| data          | object | 必须     |        | 返回的数据            |
| \|-id         | number | 非必须   |        | 文章ID                |
| \|-title      | string | 非必须   |        | 文章标题              |
| \|-content    | string | 非必须   |        | 文章内容              |
| \|-coverImg   | string | 非必须   |        | 封面图片地址          |
| \|-state      | string | 非必须   |        | 状态                  |
| \|-categoryId | number | 非必须   |        | 分类ID                |
| \|-createTime | string | 非必须   |        | 创建时间              |
| \|-updateTime | string | 非必须   |        | 更新时间              |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "id": 1,
        "title": "文章标题",
        "content": "文章内容",
        "coverImg": "https://example.com/image.jpg",
        "state": "published",
        "categoryId": 1,
        "createTime": "2023-09-02 22:21:31",
        "updateTime": "2023-09-02 22:21:31"
    }
}
```

### 2.5 更新文章

#### 2.5.1 基本信息

> 请求路径：/article
>
> 请求方式：PUT
>
> 接口描述：该接口用于更新文章信息

#### 2.5.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称   | 说明         | 类型   | 是否必须 | 备注 |
| ---------- | ------------ | ------ | -------- | ---- |
| id         | 文章ID       | number | 是       |      |
| title      | 文章标题     | string | 否       |      |
| content    | 文章内容     | string | 否       |      |
| coverImg   | 封面图片地址 | string | 否       |      |
| state      | 状态         | string | 否       |      |
| categoryId | 分类ID       | number | 否       |      |
| updateUser | 更新用户ID   | number | 否       |      |

请求数据样例：

```json
{
    "id": 1,
    "title": "更新后的标题",
    "content": "更新后的内容",
    "coverImg": "https://example.com/new-image.jpg",
    "state": "published",
    "categoryId": 2
}
```

#### 2.5.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

### 2.6 删除文章

#### 2.6.1 基本信息

> 请求路径：/article/:id
>
> 请求方式：DELETE
>
> 接口描述：该接口用于根据文章ID删除文章

#### 2.6.2 请求参数

请求参数格式：路径参数

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 文章ID | number | 是       |      |

请求数据样例：

```
DELETE /article/1
```

#### 2.6.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

---

## 3. 分类管理控制器

### 3.1 添加分类

#### 3.1.1 基本信息

> 请求路径：/category
>
> 请求方式：POST
>
> 接口描述：该接口用于添加文章分类

#### 3.1.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称      | 说明     | 类型   | 是否必须 | 备注 |
| ------------- | -------- | ------ | -------- | ---- |
| id            | 分类ID   | number | 否       |      |
| categoryName  | 分类名称 | string | 是       |      |
| categoryAlias | 分类别名 | string | 是       |      |
| createUser    | 创建用户ID | number | 否       |      |
| updateUser    | 更新用户ID | number | 否       |      |

请求数据样例：

```json
{
    "categoryName": "技术",
    "categoryAlias": "tech"
}
```

#### 3.1.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

### 3.2 获取所有分类

#### 3.2.1 基本信息

> 请求路径：/category
>
> 请求方式：GET
>
> 接口描述：该接口用于获取所有文章分类

#### 3.2.2 请求参数

无

#### 3.2.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称             | 类型   | 是否必须 | 默认值 | 备注                  |
| ---------------- | ------ | -------- | ------ | --------------------- |
| code             | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message          | string | 非必须   |        | 提示信息              |
| data             | array  | 必须     |        | 返回的数据列表        |
| \|-id            | number | 非必须   |        | 分类ID                |
| \|-categoryName  | string | 非必须   |        | 分类名称              |
| \|-categoryAlias | string | 非必须   |        | 分类别名              |
| \|-createTime    | string | 非必须   |        | 创建时间              |
| \|-updateTime    | string | 非必须   |        | 更新时间              |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": [
        {
            "id": 1,
            "categoryName": "技术",
            "categoryAlias": "tech",
            "createTime": "2023-09-02 22:21:31",
            "updateTime": "2023-09-02 22:21:31"
        }
    ]
}
```

### 3.3 获取分类详情

#### 3.3.1 基本信息

> 请求路径：/category/:id
>
> 请求方式：GET
>
> 接口描述：该接口用于根据分类ID获取分类详情

#### 3.3.2 请求参数

请求参数格式：路径参数

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 分类ID | number | 是       |      |

请求数据样例：

```
GET /category/1
```

#### 3.3.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称             | 类型   | 是否必须 | 默认值 | 备注                  |
| ---------------- | ------ | -------- | ------ | --------------------- |
| code             | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message          | string | 非必须   |        | 提示信息              |
| data             | object | 必须     |        | 返回的数据            |
| \|-id            | number | 非必须   |        | 分类ID                |
| \|-categoryName  | string | 非必须   |        | 分类名称              |
| \|-categoryAlias | string | 非必须   |        | 分类别名              |
| \|-createTime    | string | 非必须   |        | 创建时间              |
| \|-updateTime    | string | 非必须   |        | 更新时间              |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "id": 1,
        "categoryName": "技术",
        "categoryAlias": "tech",
        "createTime": "2023-09-02 22:21:31",
        "updateTime": "2023-09-02 22:21:31"
    }
}
```

### 3.4 更新分类

#### 3.4.1 基本信息

> 请求路径：/category
>
> 请求方式：PUT
>
> 接口描述：该接口用于更新文章分类

#### 3.4.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称      | 说明     | 类型   | 是否必须 | 备注 |
| ------------- | -------- | ------ | -------- | ---- |
| id            | 分类ID   | number | 是       |      |
| categoryName  | 分类名称 | string | 否       |      |
| categoryAlias | 分类别名 | string | 否       |      |
| updateUser    | 更新用户ID | number | 否       |      |

请求数据样例：

```json
{
    "id": 1,
    "categoryName": "技术开发",
    "categoryAlias": "tech-dev"
}
```

#### 3.4.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

### 3.5 删除分类

#### 3.5.1 基本信息

> 请求路径：/category/:id
>
> 请求方式：DELETE
>
> 接口描述：该接口用于根据分类ID删除分类

#### 3.5.2 请求参数

请求参数格式：路径参数

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| id       | 分类ID | number | 是       |      |

请求数据样例：

```
DELETE /category/1
```

#### 3.5.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | object | 非必须   |        | 返回的数据            |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": null
}
```

---

## 4. 文件管理控制器

### 4.1 上传文件

#### 4.1.1 基本信息

> 请求路径：/common/upload
>
> 请求方式：POST
>
> 接口描述：该接口用于上传文件

#### 4.1.2 请求参数

请求参数格式：multipart/form-data

请求参数说明：

| 参数名称 | 说明     | 类型 | 是否必须 | 备注 |
| -------- | -------- | ---- | -------- | ---- |
| file     | 上传文件 | file | 是       |      |

请求数据样例：

表单提交，包含file字段

#### 4.1.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 200-成功,0-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | string | 必须     |        | 文件访问URL           |

响应数据样例：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": "https://example.com/uploads/file.jpg"
}
```

### 4.2 下载文件

#### 4.2.1 基本信息

> 请求路径：/common/download
>
> 请求方式：GET
>
> 接口描述：该接口用于下载文件

#### 4.2.2 请求参数

请求参数格式：queryString

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注 |
| -------- | ------ | ------ | -------- | ---- |
| fileName | 文件名 | string | 是       |      |

请求数据样例：

```shell
fileName=file.jpg
```

#### 4.2.3 响应数据

响应数据类型：文件流

响应说明：直接返回文件内容，Content-Type根据文件类型自动设置
