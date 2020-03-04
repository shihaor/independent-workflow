# Independent workflow
本开源项目项目基于activiti做进一步的二次开发，旨在开发一个集绘制流程，引擎，表单于一体的综合项目。因为工作流引擎本身
表单部分较为薄弱，不适用于大部分场景，所以应嵌合一个外部的表单替代。
  --- 
## 在线绘制bpmn图的组件（Activiti-modeler）
1、localhost:xxxx/index  首页
2、在首页可以对模型进行增删改查
3、可以绘制模型
## 工作流引擎(Activiti)
1、localhost:xxxx/workflow/findAllDeployments 展示部署资源的页面，可以对已部署的资源进行一系列操作
2、人员这个版块的数据因为一般是由主项目灌输，所以这个项目不做人员，只是单纯的模拟人员，人员数据的导入只提供接口；人员只
提供新增与更新，不支持修改，因为要保证与主项目的人员是并集。
## 动态表单（前端项目，最好是一个拖拽式的表单生成工具）
## swagger 便于开发
swagger访问地址 127.0.0.1:8080/swagger-ui.html

