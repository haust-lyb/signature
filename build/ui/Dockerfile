FROM nginx:stable-alpine
COPY ./seasoning/dist /usr/share/nginx/html/signature-ui
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

