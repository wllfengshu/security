# 安全控制
- 登陆、登出、心跳、查询当前登陆的用户信息

### 1 登陆

`POST security/login`

#### 1.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| username | string | 否 | body | 用户名 | admin | |
| password | string | 否 | body | 密码 | Aa123456 |  |

#### 1.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |

#### 1.3 请求响应示例

```
POST http://127.0.0.1:8080/login
```

- (1) 请求头：request headers

```
Content-Type: application/json;charset=utf-8
```

- (2) request payload

```json
{
  "password": "Aa123456",
  "username": "admin"
}
```

- (3) response

```json
{
  "sessionId": "2B695D34B2F55DF058BC0D6122B0E77E"
}
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
| 10001 | 用户名不能为空 |  |
| 10002 | 密码不能为空 |  |
| 10003 | 用户名不存在 |  |
| 10004 | 用户名或密码错误 |  |
| 10005 | 账户被锁定 |  |
| 10006 | 过度尝试异常 |  |
| 10007 | 身份验证异常 |  |

<br/><br/>


### 2 登出

`GET security/logout`

#### 2.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |

#### 2.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| logout | string | NO | 登出状态 |  |

#### 2.3 请求响应示例

```
GET http://127.0.0.1:8080/security/logout
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
{
  "logout": "success"
}
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
| 10008 | 未登陆，无法操作 |  |
| 10009 | 已登陆，但sessionId不匹配 |  |

<br/><br/>


### 3 心跳

`GET security/touch`

#### 3.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |

#### 3.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| touch | bool | NO | 心跳状态 |  |

#### 3.3 请求响应示例

```
GET http://127.0.0.1:8080/security/touch
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
{
  "touch": true
}
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
| 10008 | 未登陆，无法操作 |  |
| 10009 | 已登陆，但sessionId不匹配 |  |

<br/><br/>


### 4 获取当前登陆的用户信息

`GET security/session`

#### 4.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |

#### 4.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| id | int | NO | ID |  |
| username | String | NO | 用户名 |  |
| password | String | NO | 密码 |  |

#### 4.3 请求响应示例

```
GET http://127.0.0.1:8080/security/session
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
| 10008 | 未登陆，无法操作 |  |
| 10009 | 已登陆，但sessionId不匹配 |  |
