FROM registry.cn-beijing.aliyuncs.com/yuncenliu/centos:7.9.2009
MAINTAINER centos add aliyunYumSource "yuncenLiu@163.com"
ENV TZ Asia/Shanghai

RUN rm -rf /etc/yum.repos.d/*

COPY Centos7-aliyun.repo /etc/yum.repos.d/
COPY Centos7-aliyun-x86_64.repo /etc/yum.repos.d/

RUN yum clean all
RUN yum makecache
