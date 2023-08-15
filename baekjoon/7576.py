from collections import deque

M,N=map(int,input().split())
graph=[]
for _ in range(N):
    graph.append(list(map(int,input().split())))
li=[]
for i in range(N):
    for j in range(M):
        if graph[i][j]==1:
            li.append((i,j))
def bfs(li):
    queue=deque()
    for i in range(len(li)):
        queue.append(li[i])
    dx=[-1,1,0,0]
    dy=[0,0,-1,1]
    while queue:
        x,y=queue.popleft()
        for i in range(4):
            nx=x+dx[i]
            ny=y+dy[i]
            if nx<0 or ny<0 or nx>=N or ny>=M:
                continue
            if graph[nx][ny]==0:
                graph[nx][ny]=graph[x][y]+1
                queue.append((nx,ny))
bfs(li)
result=[]
for i in range(N):
    for j in range(M):
        if graph[i][j]==0:
            print(-1)
            exit()
    ans=max(graph[i])
    result.append(ans)
print(max(result)-1)