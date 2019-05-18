# User管理


## 一、数据字典

注：PRI主键约束;UNI唯一约束;MUL可以重复

| 编号 | 字段名 | 类型 | 是否允许空 | 约束 | 含义 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
 | 1 | id | int(11) | NO | PRI | ID |  |
 | 2 | username | varchar(64) | NO | UNI | 用户名 |  |
 | 3 | password | varchar(128) | NO |  | 密码 |  |

## 二、接口文档

### 1 增加

`POST security/v1/users/user`

#### 1.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| entity | json | 否 | body | 实体类 |  |  |

#### 1.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |

#### 1.3 请求响应示例

```
POST http://127.0.0.1:8080/security/v1/users/user
```

- (1) 请求头：request headers

```
sessionId: d56006cc-a698-437c-a580-6fcb8e86cc62
Content-Type: application/json;charset=utf-8
```

- (2) request payload

```json
{
	"id" : 0,
	"username" : "string",
	"password" : "string"
}
```

- (3) response

```json

```

#### 1.4 异常示例

```json
{
	"errorMessage": "没有权限",
	"errorCode": 10000,
	"instanceId": "security-xyzws"
}
```

#### 1.5 业务错误码

| 错误码 | 描述 | 解决方案 |
| ---- | ---- | ---- |
| 10000 | 没有权限 | 检查该用户的权限 |


<br/><br/>

### 2 删除

`DELETE security/v1/users/user/{id}`

#### 2.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| id | int | 否 | path | id | 1 |  |

#### 2.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |

#### 2.3 请求响应示例

```
DELETE http://127.0.0.1:8080/security/v1/users/user/1
```

- (1) 请求头：request headers

```
sessionId: d56006cc-a698-437c-a580-6fcb8e86cc62
Content-Type: application/json;charset=utf-8
```

- (2) request payload

```json

```

- (3) response

```json

```

#### 2.4 异常示例

```json
{
	"errorMessage": "没有权限",
	"errorCode": 10000,
	"instanceId": "security-xyzws"
}
```

#### 2.5 业务错误码

| 错误码 | 描述 | 解决方案 |
| ---- | ---- | ---- |
| 10000 | 没有权限 | 检查该用户的权限 |


<br/><br/>

### 3 修改

`PUT security/v1/users/user`

#### 3.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| entity | json | 否 | body | 实体类 |  |  |

#### 3.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |

#### 3.3 请求响应示例

```
PUT http://127.0.0.1:8080/security/v1/users/user
```

- (1) 请求头：request headers

```
sessionId: d56006cc-a698-437c-a580-6fcb8e86cc62
Content-Type: application/json;charset=utf-8
```

- (2) request payload

```json
{
	"id" : 0,
	"username" : "string",
	"password" : "string"
}
```

- (3) response

```json

```

#### 3.4 异常示例

```json
{
	"errorMessage": "没有权限",
	"errorCode": 10000,
	"instanceId": "security-xyzws"
}
```

#### 3.5 业务错误码

| 错误码 | 描述 | 解决方案 |
| ---- | ---- | ---- |
| 10000 | 没有权限 | 检查该用户的权限 |


<br/><br/>

### 4 通过ID查询

`GET security/v1/users/user/{id}`

#### 4.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| id | int | 否 | path | id | 1 |  |
| needRole | bool | 是 | query | 是否需要角色 | true | 默认false |
| needRoleAndPermission | bool | 是 | query | 是否需要角色和权限 | true | 默认false |

#### 4.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| id | int | NO | ID |  |
| username | String | NO | 用户名 |  |
| password | String | NO | 密码 |  |

#### 4.3 请求响应示例

```
GET http://127.0.0.1:8080/security/v1/users/user/1
```

- (1) 请求头：request headers

```
sessionId: d56006cc-a698-437c-a580-6fcb8e86cc62
Content-Type: application/json;charset=utf-8
```

- (2) request payload

```json

```

- (3) response

(3.1)当needRole为false或默认，needRoleAndPermission为false或默认时：

```json
{
  "data": {
    "id": 1,
    "username": "admin",
    "password": "Aa123456",
    "roles": null
  }
}
```
(3.2)当needRole为true，needRoleAndPermission为false或默认时：
```json
{
  "data": {
    "id": 1,
    "username": "admin",
    "password": "Aa123456",
    "roles": [
      {
        "id": 1,
        "name": "admin",
        "description": "管理员",
        "permissions": null
      }
    ]
  }
}
```
(3.3)当needRoleAndPermission为true时（不再考虑needRole的值）：
```json
{
  "data": {
    "id": 1,
    "username": "admin",
    "password": "Aa123456",
    "roles": [
      {
        "id": 1,
        "name": "admin",
        "description": "管理员",
        "permissions": [
          {
            "id": 1,
            "name": "insertPermission",
            "description": "插入权限",
            "scope": null
          },
          {
            "id": 2,
            "name": "deletePermission",
            "description": "删除权限",
            "scope": null
          },
          {
            "id": 3,
            "name": "updatePermission",
            "description": "修改权限",
            "scope": null
          }
        ]
      }
    ]
  }
}
```

#### 4.4 异常示例

```json
{
	"errorMessage": "没有权限",
	"errorCode": 10000,
	"instanceId": "security-xyzws"
}
```

#### 4.5 业务错误码

| 错误码 | 描述 | 解决方案 |
| ---- | ---- | ---- |
| 10000 | 没有权限 | 检查该用户的权限 |


<br/><br/>

### 5 查询

`GET security/v1/users`

#### 5.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| pageNo | int | 是 | query | 页码 | 0 | 从0开始,默认为0 |
| pageSize | int | 是 | query | 页面大小 | 10 | 默认为10 |
| needRole | bool | 是 | query | 是否需要角色 | true | 默认false |
| needRoleAndPermission | bool | 是 | query | 是否需要角色和权限 | true | 默认false |

#### 5.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| id | int | NO | ID |  |
| username | String | NO | 用户名 |  |
| password | String | NO | 密码 |  |

#### 5.3 请求响应示例

```
GET http://127.0.0.1:8080/security/v1/users
```

- (1) 请求头：request headers

```
sessionId: d56006cc-a698-437c-a580-6fcb8e86cc62
Content-Type: application/json;charset=utf-8
```

- (2) request payload

```json

```

- (3) response

(3.1)当needRole为false或默认，needRoleAndPermission为false或默认时：

```json
{
  "total": 2,
  "data": [
    {
      "id": 1,
      "username": "admin",
      "password": "Aa123456",
      "roles": null
    },
    {
      "id": 2,
      "username": "wangll",
      "password": "Aa123456",
      "roles": null
    }
  ]
}
```
(3.2)当needRole为true，needRoleAndPermission为false或默认时：
```json
{
  "total": 2,
  "data": [
    {
      "id": 1,
      "username": "admin",
      "password": "Aa123456",
      "roles": [
        {
          "id": 1,
          "name": "admin",
          "description": "管理员",
          "permissions": null
        }
      ]
    },
    {
      "id": 2,
      "username": "wangll",
      "password": "Aa123456",
      "roles": [
        {
          "id": 2,
          "name": "consumer",
          "description": "消费者",
          "permissions": null
        }
      ]
    }
  ]
}
```
(3.3)当needRoleAndPermission为true时（不再考虑needRole的值）：
```json
{
  "total": 2,
  "data": [
    {
      "id": 1,
      "username": "admin",
      "password": "Aa123456",
      "roles": [
        {
          "id": 1,
          "name": "admin",
          "description": "管理员",
          "permissions": [
            {
              "id": 1,
              "name": "insertPermission",
              "description": "插入权限",
              "scope": null
            },
            {
              "id": 2,
              "name": "deletePermission",
              "description": "删除权限",
              "scope": null
            },
            {
              "id": 3,
              "name": "updatePermission",
              "description": "修改权限",
              "scope": null
            }
          ]
        }
      ]
    },
    {
      "id": 2,
      "username": "wangll",
      "password": "Aa123456",
      "roles": [
        {
          "id": 2,
          "name": "consumer",
          "description": "消费者",
          "permissions": []
        }
      ]
    }
  ]
}
```

#### 5.4 异常示例

```json
{
	"errorMessage": "没有权限",
	"errorCode": 10000,
	"instanceId": "security-xyzws"
}
```

#### 5.5 业务错误码

| 错误码 | 描述 | 解决方案 |
| ---- | ---- | ---- |
| 10000 | 没有权限 | 检查该用户的权限 |


<br/><br/>


<br/><br/>
