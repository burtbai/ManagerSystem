# Manager System

### BUILD

```shell
./gradlew clean build
```

### RUN

```shell
java -jar build/libs/ManagerSystem-1.0-SNAPSHOT.jar
```

### REQUEST

```http
### {"userId":123456,"accountName":"admin1","role":"admin"}

POST http://localhost:8080/admin/addUser
Content-Type: application/json
user-info: eyJ1c2VySWQiOjEyMzQ1NiwiYWNjb3VudE5hbWUiOiJhZG1pbjEiLCJyb2xlIjoiYWRtaW4ifQo=

{
  "userId": 123456,
  "endpoint": ["aaa", "bbb"]
}
```

```http
GET http://localhost:8080/user/aaa
user-info: eyJ1c2VySWQiOjEyMzQ1NiwiYWNjb3VudE5hbWUiOiJhZG1pbjEiLCJyb2xlIjoiYWRtaW4ifQo=
```

### TEST

```shell
./gradlew clean test --info
```

### TODO
- [ ] 更多测试用例, Jacoco/sonarqube
- [ ] API/文档
- [ ] 日志
- [ ] 操作文件并发问题
- [ ] 埋点
- [ ] 运行环境/配置区分,生产 预发 测试
- [ ] 监控