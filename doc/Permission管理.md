# Permission管理


## 一、数据字典

注：PRI主键约束;UNI唯一约束;MUL可以重复

| 编号 | 字段名 | 类型 | 是否允许空 | 约束 | 含义 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
 | 1 | id | int(11) | NO | PRI | ID |  |
 | 2 | name | varchar(64) | NO |  | 权限名 |  |
 | 3 | description | varchar(128) | YES |  | 描述 |  |
 | 4 | scope | varchar(255) | YES |  | 范围 |  |

## 二、接口文档

### 1 增加

`POST security/permissions/permission`

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
POST http://127.0.0.1:8080/security/permissions/permission
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
	"name" : "addUser",
	"description" : "添加用户",
	"scope" : "all"
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

`DELETE security/permissions/permission/{id}`

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
DELETE http://127.0.0.1:8080/security/permissions/permission/1
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

`PUT security/permissions/permission`

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
PUT http://127.0.0.1:8080/security/permissions/permission
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
	"name" : "string",
	"description" : "string",
	"scope" : "string"
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

`GET security/permissions/permission/{id}`

#### 4.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| id | int | 否 | path | id | 1 |  |

#### 4.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| id | int | NO | ID |  |
| name | String | NO | 权限名 |  |
| description | String | YES | 描述 |  |
| scope | String | YES | 范围 |  |

#### 4.3 请求响应示例

```
GET http://127.0.0.1:8080/security/permissions/permission/1
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
	"id" : 0,
	"name" : "string",
	"description" : "string",
	"scope" : "string"
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

`GET security/permissions`

#### 5.1 请求参数

| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |
| pageNo | int | 是 | query | 页码 | 0 | 从0开始,默认为0 |
| pageSize | int | 是 | query | 页面大小 | 10 | 默认为10 |

#### 5.2 响应参数

| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| id | int | NO | ID |  |
| name | String | NO | 权限名 |  |
| description | String | YES | 描述 |  |
| scope | String | YES | 范围（all/org/person） |  |

#### 5.3 请求响应示例

```
GET http://127.0.0.1:8080/security/permissions
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
	"total": 1,
	"data": [
		{
			"id" : 0,
			"name" : "string",
			"description" : "string",
			"scope" : "string"
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
