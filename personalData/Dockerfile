# Usar Eclipse Temurin Java 11 runtime
FROM eclipse-temurin:11-jre

LABEL maintainer="Alfredo Leal"

# Copia el JAR generado (QUE SE GENERA CON "MAVEN INSTALL"")
COPY target/personalData-0.0.1-SNAPSHOT.jar /personalData.jar 

# Expone el puerto de la app (Spring Boot)
EXPOSE 8082

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "/personalData.jar"]





#PASOS PARA CREAR UNA IMAGEN Y EJECUTAR UN CONTENEDOR
#1.Tener tu proyecto Java listo
#docker build . -t aleal/personaldata

#2.Creamos la imagen de Docker
#docker build . -t aleal/personaldata
#java -jar /personalData.jar

#3.Ejecutamos la imagen → crea el contenedor
#docker run -p 8082:8082 aleal/personaldata





#COMANDOS NECESARIOS PARA MOVERTE POR CONTENEDORES
#DOCKER PS
#Miras que puertos estan corriendo en tiempo real

#DOCKER PS -a 
#Miras los puertos que hay en total aunque no esten corriendo

#Docker start (id puerto)
#Inicializas un puerto

#Docker stop (id puerto)
#Paras el puerto


















#------------------------------------------------------------------------------------------
#ANTIGUA CONFIGURACIÓN

#Descargamos la imagen oficial de java runtime
#FROM openjdk:11-slim as build

#Información de quien mantiene esta imagen
#MAINTAINER Alfredo Leal

# Agregamos el jar al contenedor
#COPY target/personalData-0.0.1-SNAPSHOT.jar personalData-0.0.1-SNAPSHOT.jar

#ejecuta la aplicación
#ENTRYPOINT ["java","-jar","/personalData-0.0.1-SNAPSHOT.jar"]

#Comando para ejecutar el jar que generamos, mediante el mvn install (con esto se genera el JAR)
#java -jar "C:\Users\mario_buriey\Desktop\proyectosCertificacion\personalData\target\personalData-0.0.1-SNAPSHOT.jar"
