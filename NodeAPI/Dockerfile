FROM node:14.16-alpine AS builder

WORKDIR /opt/web

COPY package.json ./

RUN npm install

COPY . ./

EXPOSE 5000

CMD ["npm" , "start"]