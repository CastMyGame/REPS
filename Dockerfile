# syntax=docker/dockerfile:1

FROM node:18-alpine
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/main/java/com/dms/hims/HimsApplication.java"]
EXPOSE 8080
