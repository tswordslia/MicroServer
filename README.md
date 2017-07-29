# MicroServer

在android上实现的简单的http服务器。启动程序后直接在浏览器上访问对应端口，返回一个网页。

##测试方式

1.真机测试
真机测试只要保证开发机和手机在同一个网段内，直接访问手机ip+port 例：`192.168.1.1:8000`

2.模拟器测试
模拟器访问开发机IP:`10.0.2.2`
开发机访问模拟器端口前先将端口转发:
`telnet localhost 5554`
`redir add tcp:8000:8000`