# 侧边栏

## 获取侧边栏数据

```
/getMenuList
```

```javascript
{
    "data": [
        {
            "id": 1,
            "menu": "首页",
            "pid": 0,
            "path": "/dashboard",
            "children": []
        },
        {
            "id": 2,
            "menu": "用户管理",
            "pid": 0,
            "path": "/user",
            "children": [
                {
                    "id": 3,
                    "menu": "会员管理",
                    "pid": 2,
                    "path": "/user/user",
                    "children": null
                },
                {
                    "id": 4,
                    "menu": "收货地址",
                    "pid": 2,
                    "path": "/user/address",
                    "children": null
                },
                {
                    "id": 7,
                    "menu": "搜索历史",
                    "pid": 2,
                    "path": "/user/history",
                    "children": null
                },
                {
                    "id": 8,
                    "menu": "意见反馈",
                    "pid": 2,
                    "path": "/user/feedback",
                    "children": null
                }
            ]
        }
    ],
    "meta": {
        "msg": "获取成功",
        "status": 200
    }
}
```

# 用户管理

## 角色登录

```
/login
```

| 参数名称 | 必选 | 类型   | 说明 |
| -------- | ---- | ------ | ---- |
| username | 是   | String | 账号 |
| password | 是   | String | 密码 |

```javascript
{
    "data": {
        "userId": 1,
        "userName": "admin",
        "userPassword": "admin123",
        "userPhone": 1594586999,
        "userSex": "男",
        "userGrade": 0,
        "userState": 1
    },
    "meta": {
        "msg": "登录成功",
        "status": 200
    }
}
```





























