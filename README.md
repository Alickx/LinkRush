## link-rush 短链系统

### 1. 项目结构:

| 项目                | 说明                                    |
|-------------------|---------------------------------------|
| link-rush-api     | 面向外部，提供短链生成，短链查询等功能                   |
| link-rush-common  | 项目公用模块，例如异常，返回类等                      |
| link-rush-handler | 项目执行模块，通过消息队列接收来自api模块的消息，并执行相关逻辑     |
| link-rush-web     | 项目管理端，通过可视化后台对部分配置进行管理，并实现数据查询和实时图表分析 |

### 2. 项目简介
通过配置域名，实现短链生成。项目还未完善，以下是可以进行完善的地方：
- [ ] 短链随机码生成算法优化
- [ ] 域名配置
- [ ] 实时数据分析
- [ ] 目标链接短链数量限制
...