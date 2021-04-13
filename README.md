# 多端签字版食用说明书

## 项目架构以及项目的编译和打包说明
软件采用springboot架构，jdk版本最好使用1.8，下载项目源代码后执行相关的maven命令package即可。

当然 这里也有编译好的可供直接运行的jar包，可以直接下载使用
<br/>下载地址：
https://github.com/haust-lyb/signature/files/6301170/signature-0.0.1-SNAPSHOT.jar.zip/
<br/>
或者https://github.com/haust-lyb/signature/issues/1

## 手机端签字版效果图（手机端默认横屏操作）：
![image](https://user-images.githubusercontent.com/23397828/114495785-49d65780-9c51-11eb-9d0c-d756a3cd65cf.png)

## 签字后如何获取签字图片？
首先，签字版页面需要附带一个唯一的UUID（随机字符串）
例如：http://[yourhost]/signature/index?uuid=test1234
从该地址进行签名并提交后，您的签字内容即和uuid=1234进行了一对一的绑定关系，之后，便可以通过绑定关系获取签字的base64内容
例如：获取签字内容地址：http://[yourhost]/signature/getData?uuid=test1234

![image](https://user-images.githubusercontent.com/23397828/114496554-c6b60100-9c52-11eb-9553-b8deca0a5d8b.png)
