# RedLive
[apk](https://github.com/tecmry/RedLive/raw/master/file/app-debug.apk)
![](https://github.com/tecmry/RedLive/blob/master/file/web_hi_res_512.png)
 <br>这是一个类似知乎Live的App，还是有遗憾开始想实现的一些功能还是没能在指定时间实现后续会尽快加上。在这个过程中也学习到了不少新东西。下面开始上图了。
 #### 想法来由
 <br>其实这个的来由是从知乎Live开始的也参与过几场Live觉得这种知识分享模式确实挺不错的，所以这个App就围绕着知识分享Live来实现的。但是评论的功能暂时未能实现,添加了每日热词搜索的功能你可以查找词条在各个搜索引擎上的搜索指数，也有每日一句的功能是用了金山词霸的接口，点击能跳转至下载英文版的链接，显示的是每日一句的中午文版翻译，今后不断的修改希望能把这个做成小而美的App.
 #### 每日一图
 <br>用的是巨硬的接口，每天有七张图片，每次打开从这七张图片里（伪）随机选一张
 ![每日一图](https://github.com/tecmry/RedLive/blob/master/file/main.png)
 #### 展示Live
 <br>Live的头像会是该Live的创建者的头像
![](https://github.com/tecmry/RedLive/blob/master/file/group.png)
![](https://github.com/tecmry/RedLive/blob/master/file/addLive.png)
 ####  进入Live界面
![](https://github.com/tecmry/RedLive/blob/master/file/talk.png)
 ####  我的
 <br>右上角的搜索按钮可以帮助你找到朋(ji)友(lao) (逃 
 <br>只有找到此人CardView才会出现
 <br>未登录用户点击头像能进入登入界面 登录用户点击进入用户信息修改界面点击上方图像处可进行修改
 <br>每日一句点击既跳转至下载英文版界面 以后改进成在应用内播放
 <br>今日推荐(好吧其实没啥推荐的你要自己找推荐)  提供搜索热词功能查看实时搜索热度
 <br>意见反馈 （从下弹出popupwindow）
 ![](https://github.com/tecmry/RedLive/blob/master/file/mine.png)
 ![](https://github.com/tecmry/RedLive/blob/master/file/back.png)
 ![](https://github.com/tecmry/RedLive/blob/master/file/mineeditor.png)
 ![](https://github.com/tecmry/RedLive/blob/master/file/count.png)
 ![](https://github.com/tecmry/RedLive/blob/master/file/userimage.png)
 
 #### 总结
 这个版本还比较简陋比如在Live'过程的语音播放没有实现进度条一旦听漏了要重头开始听，以后会进行修改优化界面特别是Live的创建和用户信息的修改。代码还是写的有点乱没有使用MVP/MVC，在导Leancloud的包的过程中也遇到了一些坑上午能正常运行的下午会出现依赖冲突，所以把后面依赖的包直接下下来一个一个的看发现是两个包使用的okhttp版本不同产生了冲突做降级处理过了几天在看官方github发现上面更新了然而官方文档指向的还是旧版本。所以遇到问题要冷静下来，心不能乱会弄得只是平添烦恼还弄得不能成事儿，平静下来滤清思路才是关键而不是拿着错误码一头扎到搜索引擎里了。很感谢你耐心的看完了这个ReadMe并浏览使用项目如果在浏览过程中遇到了什么问题欢迎提交issue,这一年在红岩学到了很多认识了很多朋(da)友(lao)，接下来继续努力！！
##### 测试账号
admin 123456789
<br> admin1 123456789
