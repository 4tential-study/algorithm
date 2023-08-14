import sys
from collections import deque
M,N,H=map(int,input().split())
graph=[]
queue=deque([])
for i in range(H):
    box=[]
    for j in range(N):
        box.append(list(map(int,sys.stdin.readline().split())))
        for k in range(M):
            if box[j][k]==1:
                queue.append([i,j,k])
    graph.append(box)
    
dx=[-1,1,0,0,0,0]
dy=[0,0,1,-1,0,0]
dz=[0,0,0,0,1,-1]
while(queue):
    x,y,z=queue.popleft()

    for i in range(6):
        nx=x+dx[i]
        ny=y+dy[i]
        nz=z+dz[i]
        if 0<=nx<H and 0<=ny<N and 0<=nz<M and graph[nx][ny][nz]==0:
            queue.append([nx,ny,nz])
            graph[nx][ny][nz]=graph[x][y][z]+1         
day=0
for i in graph:
    for j in i:
        for k in j:
            if k==0:
                print(-1)
                exit(0)
        day=max(day,max(j))
print(day-1)