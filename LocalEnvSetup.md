# OnCampus Local Environment Setup
There are a few steps involving Docker, Port Forwarding, and other modifications you have to make to your code.
## Docker
[First download and install Docker.](https://docs.docker.com/docker-for-windows/install/) (This will make your computer restart)
In oncampus-backend code, comment/delete all of this of code in docker-compose.yml: 
```
caddy:
image: abiosoft/caddy:no-stats
restart: on-failure
ports:
- "80:80"
- "443:443"
environment:
- ACME_AGREE=true
#- GODADDY_API_KEY=e4MxkPffEhsM_38Zzy9uLA26SDYnMU3nvsj
#- GODADDY_API_SECRET=EsoU7j8xxhFuRHu2KGj52T
volumes:
- ./.caddy:/root/.caddy
- ./Caddyfile:/etc/Caddyfile
- ./oncampus-frontend/build:/root/build
- ./src/static:/root/build/django
container_name: caddy
```
Now switch to <span>initialize.sh<span>. Assuming you are using VS Code, you should have option to change 'CRLF' on the bottom right to 'LF'. This changes some file setting. I have no idea what it means but it won't work if you don't do it.

![Make sure it says LF and not CRLF](https://cdn.discordapp.com/attachments/789028045658652682/809300221929979954/unknown.png)

Next we have to change the <span>settings.p<span>y file in src/oncampus-backend. Under Databases and default, change 'HOST' to 'host.docker.internal'. This makes it so the container will look on localhost for the database. 
```
'HOST': 'host.docker.internal',
```
Make sure to save all the files after you have made these changes!
## Port Forwarding
Make sure you have your private key in .pem format. In case you don't [follow this](https://aws.amazon.com/premiumsupport/knowledge-center/convert-pem-file-into-ppk/) guide to convert a .ppk private key to .pem. 

Now our backend django container will be looking for the database on localhost port 5432. Since there is no database there, we can use port forwarding to 'forward' any of the requests to the localhost:5432 to the port where our database is on the AWS EC2 server.
Here is this command for the port forward:

    ssh -i [privatekeyname] -L 5432:oncampus-test.cp0ukcriuakg.us-west-1.rds.amazonaws.com:5432 ubuntu@test.oncampus.us

(If you aren't in the same directory as where your private key is, you will have to include the file path to it.)

## Frontend Changes
In the frontend code, go to the networking.js file. In it you just have to change the base from test.oncampus to localhost:8000 where docker is running the backend.

    export  const  base = "http://localhost:8000/api/v1/backend";

   ## Spin Everything Up
   Start the docker containers with:

    docker-compose up --build
Start the frontend with:

    npm start
After a few minutes you should see that the containers are up and if you go to localhost:3000 it should work. 
