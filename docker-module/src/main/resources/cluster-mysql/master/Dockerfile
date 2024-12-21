# test
FROM registry.cn-beijing.aliyuncs.com/yuncenliu/mysql:5.7.44
MAINTAINER cluster-master-mysql from data UTC by Asia/Shanghai "yuncenLiu@163.com"
ENV TZ Asia/Shanghai
COPY my.cnf /etc/mysql

# docker build --rm -t registry.cn-beijing.aliyuncs.com/yuncenliu/mysql:cluster-master-mysql-5.7.44 .

# docker run -itd --name cluster-master-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 \
# registry.cn-beijing.aliyuncs.com/yuncenliu/mysql:cluster-master-mysql-5.7.44 \
# --character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci
