FROM --platform=linux/amd64 node:16 AS ui-build
WORKDIR /app
COPY ./package*.json ./
RUN npm install
COPY . ./
RUN npm run build

FROM nginx:stable-alpine as prod-build
COPY --from=ui-build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
