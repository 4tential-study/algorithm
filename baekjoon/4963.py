from collections import deque
import sys
sys.setrecursionlimit(10**6)
input=sys.stdin.readline

def bfs(x,y):
    queue=deque()
    queue.append((x,y))
    while queue:
        x,y=queue.popleft()
        dx=[-1,-1,-1,0,0,1,1,1]
        dy=[-1,0,1,-1,1,-1,0,1]
        for i in range(8):
            nx=x+dx[i]
            ny=y+dy[i]
            if nx<0 or ny<0 or nx>=h or ny>=w:
                continue
            if graph[nx][ny]==0:
                continue
            if graph[nx][ny]==1:
                graph[nx][ny]=0
                queue.append((nx,ny))
w=1;h=1
num=[]
while w!=0 and h!=0:
    w,h=map(int,input().split())
    graph=[]
    for i in range(h):
        graph.append(list(map(int,input().split())))
    n=0
    for i in range(h):
        for j in range(w):
            if graph[i][j]==1:
                bfs(i,j)
                n+=1
    if w!=0 and h!=0:
        num.append(n)
print(*num,sep='\n')
