# 用户模块
用户包含学生和教师
# 题目模块
题目增删改查
# Docker镜像
docker pull jcstaffes/hellodocker:account
# 判题模块
https://github.com/jcstaffes/jmojtest
# Nginx配置
upstream judge { 

server 127.0.0.1:8080; 

server 127.0.0.1:10000; 

server 127.0.0.1:9999; 

server 127.0.0.1:9998; 

}

server { 

listen 8081; 

location / { 

proxy_pass http://judge; 

} 

}
