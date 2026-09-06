FROM node:20-alpine

WORKDIR /frontend

COPY package.json ./
COPY package-lock.json ./

RUN npm install

CMD ["npm", "run", "dev"]