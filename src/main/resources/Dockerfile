FROM java:8u40

MAINTAINER nicholas.mcdowall@gmail.com

COPY ${project.build.finalName}.jar /app/${project.build.finalName}.jar

ENTRYPOINT ["java", "-jar", "/app/${project.build.finalName}.jar"]