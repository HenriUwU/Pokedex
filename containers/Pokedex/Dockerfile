FROM node:lts-bullseye AS build

#-- Create a workdir inside the container --#
WORKDIR	/app

#-- Copy package.json files to install dependencies --#
COPY package*.json ./

#-- Install dependencies --#
RUN	npm install

#-- Copy all files of the project into the container --#
COPY . .

#-- Expose port 4200 (default port of Angular) --#
EXPOSE	4200

#-- Default command to launch Angular in dev mode --#
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0"]