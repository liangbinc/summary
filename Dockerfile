FROM mamohr/centos-java

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone \



ENV LANG=en_US.utf8
WORKDIR /home/chang
EXPOSE 10060:10061
VOLUME /home/changlb /home/changlb