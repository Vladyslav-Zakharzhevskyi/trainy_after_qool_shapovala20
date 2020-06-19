#stop running containers
docker stop $(docker ps -a -q)
#remove stopped containers
docker rm $(docker ps -a -q)
#remove images
docker rmi -f $(docker images -a -q)

docker images
