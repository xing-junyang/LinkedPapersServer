# Linked Papers 后端接口设计文档

## 错误码说明
- 0: 成功
- -1: 失败

## 1. 用户认证模块

### 1.1 用户注册
- **接口**: POST /api/auth/register
- **描述**: 用户注册，需要加密存储email和password
- **请求体**:
```json
{
  "username": "string",
  "email": "string",
  "password": "string"
}
```
- **响应**:
```json
{
  "code": 0,
  "message": "注册成功",
  "data": {
    "userId": "string",
    "username": "string"
  }
}
```

### 1.2 用户登录
- **接口**: POST /api/auth/login
- **描述**: 用户登录，返回JWT token
- **请求体**:
```json
{
  "email": "string",
  "password": "string"
}
```
- **响应**:
```json
{
  "code": 0,
  "message": "登录成功",
  "data": {
    "token": "string",
    "userInfo": {
      "userId": "string",
      "username": "string",
      "role": "NORMAL/VIP"
    }
  }
}
```

### 1.3 获取用户信息
- **接口**: GET /api/auth/userinfo
- **描述**: 获取当前登录用户信息
- **权限**: 需要登录
- **请求头**:
```
Authorization: Bearer {token}
```
- **响应**:
```json
{
    "code": 0,
    "data": {
        "userId": "string",
        "username": "string",
        "email": "string",
        "role": "NORMAL/VIP",
        "registerTime": "datetime",
        "lastLoginTime": "datetime"
    }
}
```

## 2. 论文检索模块

### 2.1 关键词搜索
- **接口**: GET /api/papers/search
- **描述**: 基于关键词搜索论文
- **参数**:
  - keywords: string (查询关键词)
  - page: int (页码)
  - size: int (每页数量)
  - sortBy: string (排序方式: relevance/year)
  - category: string (可选，按类别筛选)
  - yearStart: number (可选，起始年份)
  - yearEnd: number (可选，结束年份)
- **响应**:
```json
{
    "code": 0,
    "data": {
        "total": 100,
        "papers": [{
            "paperId": "string",
            "title": "string",
            "abstract": "string",
            "year": "number",
            "category": "string",
            "citationCount": "number"
        }]
    }
}
```

### 2.2 向量相似度搜索
- **接口**: POST /api/papers/vector-search
- **描述**: 基于论文向量进行相似度搜索
- **请求体**:
```json
{
    "vector": [0.1, 0.2, ...], // 128维向量
    "limit": 10
}
```
- **响应**:
```json
{
    "code": 0,
    "data": {
        "papers": [{
            "paperId": "string",
            "title": "string",
            "similarity": "number"
        }]
    }
}
```

### 2.3 获取论文详情
- **接口**: GET /api/papers/{paperId}
- **描述**: 获取论文详细信息，根据用户角色返回不同内容
- **权限**: 需要登录
- **请求头**:
```
Authorization: Bearer {token}
```
- **响应**:
```json
{
    "code": 0,
    "data": {
        "paperId": "string",
        "title": "string",
        "abstract": "string",
        "year": "number",
        "category": "string",
        "vector": [0.1, 0.2, ...], // 128维向量
        "citations": [{
            "paperId": "string",
            "title": "string",
            "year": "number"
        }],
        "similarPapers": [{ // 仅VIP可见
            "paperId": "string",
            "title": "string",
            "similarity": "number"
        }],
        "sameCategoryPapers": [{ // 仅VIP可见
            "paperId": "string",
            "title": "string"
        }]
    }
}
```

## 3. 论文分类模块

### 3.1 批量预测论文分类
- **接口**: POST /api/papers/predict-categories
- **描述**: 对测试集论文进行分类预测
- **请求体**:
```json
{
    "paperIds": ["string"]
}
```
- **响应**:
```json
{
    "code": 0,
    "data": {
        "predictions": [{
            "paperId": "string",
            "category": "string",
            "confidence": "number"
        }]
    }
}
```

## 4. 用户中心模块

### 4.1 获取浏览历史
- **接口**: GET /api/user/history
- **描述**: 获取用户的论文浏览历史
- **权限**: 需要登录
- **请求头**:
```
Authorization: Bearer {token}
```
- **参数**:
  - page: int
  - size: int
- **响应**:
```json
{
    "code": 0,
    "data": {
        "total": 100,
        "history": [{
            "paperId": "string",
            "title": "string",
            "viewTime": "datetime"
        }]
    }
}
```

### 4.2 记录浏览历史
- **接口**: POST /api/user/history
- **描述**: 记录用户浏览论文的历史
- **权限**: 需要登录
- **请求头**:
```
Authorization: Bearer {token}
```
- **请求体**:
```json
{
    "paperId": "string"
}
```
- **响应**:
```json
{
    "code": 0,
    "message": "记录成功"
}
```

### 4.3 获取个性化推荐
- **接口**: GET /api/user/recommendations
- **描述**: 基于用户浏览历史推荐论文
- **权限**: 需要登录
- **请求头**:
```
Authorization: Bearer {token}
```
- **响应**:
```json
{
    "code": 0,
    "data": {
        "recommendations": [{
            "paperId": "string",
            "title": "string",
            "abstract": "string",
            "reason": "string",
            "similarity": "number"
        }]
    }
}
```

## 5. 系统管理模块

### 5.1 更新用户角色
- **接口**: PUT /api/admin/users/{userId}/role
- **描述**: 更新用户角色（普通用户/VIP）
- **权限**: 需要管理员权限
- **请求头**:
```
Authorization: Bearer {token}
```
- **请求体**:
```json
{
    "role": "NORMAL/VIP"
}
```
- **响应**:
```json
{
    "code": 0,
    "message": "更新成功"
}
```

### 5.2 系统性能统计
- **接口**: GET /api/admin/statistics
- **描述**: 获取系统性能统计数据
- **响应**:
```json
{
    "code": 0,
    "data": {
        "searchLatency": "number", // 平均搜索延迟
        "categoryAccuracy": "number", // 分类准确率
        "dailyActiveUsers": "number", // 日活跃用户
        "totalQueries": "number" // 总查询次数
    }
}
```