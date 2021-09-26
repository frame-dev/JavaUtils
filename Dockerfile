FROM ubuntu:latest
RUN apt update
RUN DEBIAN_FRONTEND="noninteractive" TZ="Europe/Zurich" apt install nano wget maven git screen openssh-server -y
RUN echo 'root:Inside71' | chpasswd
RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]
RUN mkdir /javautils
COPY . /javautils
WORKDIR /javautils
RUN mvn clean package

CMD "java" "-jar" "/javautils/target/JavaUtils-1.6.5.8.1-RELEASE.jar"

RUN mkdir Developer
RUN mkdir minecraft
WORKDIR /Developer
RUN wget https://framedev.ch/downloads/bash_debian.sh
RUN ls
WORKDIR minecraft
RUN mkdir Server
WORKDIR /minecraft/Server
CMD "touch" "eula.txt"
RUN wget --content-disposition https://papermc.io/api/v1/paper/1.16.1/latest/download
RUN echo eula=true > eula.txt
CMD "chmod" "a+x" "paper-1.16.1-138.jar"
CMD "screen" "-S" "-d" "Server" "java" "-jar" "paper-1.16.1-138.jar" "nogui" "pause"