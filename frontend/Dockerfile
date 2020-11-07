# pull official base image
FROM node:13.12.0-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH
ENV CHOKIDAR_USEPOLLING true

# install app dependencies
RUN npm install
RUN npm install react-scripts@3.4.1 -g

# start app
CMD ["npm", "start"]
EXPOSE 3000
