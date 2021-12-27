FROM ubuntu:latest
RUN apt update
RUN DEBIAN_FRONTEND="noninteractive" TZ="Europe/Zurich" apt install nano wget maven git screen openssh-server openjdk-16-jdk curl -y
RUN echo 'root:Inside71' | chpasswd
RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
EXPOSE 22
ENV JAVA_HOME /usr/lib/jvm/java-16-openjdk-amd64
CMD ["/usr/sbin/sshd", "-D"]
RUN mkdir /javautils
COPY . /javautils
WORKDIR /javautils
# RUN mvn clean package

# CMD "java" "-jar" "/javautils/target/JavaUtils-1.8.2-RELEASE.jar"

RUN curl -z BuildTools.jar -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
RUN java -jar BuildTools.jar --rev 1.17.1
RUN wget https://framedev.ch/downloads/EssentialsMini-Latest.jar

RUN mkdir plugins
RUN mv EssentialsMini-Latest.jar plugins/EssentialsMini.jar

CMD "touch" "eula.txt"
RUN echo eula=true > eula.txt
CMD "echo ${ls}"
CMD "chmod" "a+x" "spigot-1.17.1.jar"
CMD ["java", "-jar", "spigot-1.17.1.jar"]
EXPOSE 8080