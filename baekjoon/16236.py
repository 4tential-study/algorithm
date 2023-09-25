from collections import deque
import copy

'''
문제를 잘 읽자! bfs를 제대로 이해하고 활용하자!
'''

N=int(input())
space=[]
for _ in range(N):
    line=list(map(int,input().split()))
    space.append(line)
shark_size=2

dx=[-1,1,0,0]
dy=[0,0,-1,1]

def get_distances(x,y):
    queue.append((x,y))
    x_fix=x;y_fix=y
    while queue: #아기상어가 갈 수 있는 모든 위치에 대해서 최단경로값을 매긴다.
        x,y=queue.popleft() #아기상어의 현재위치
        for i in range(4):
            nx=x+dx[i]
            ny=y+dy[i]
            if nx<0 or ny<0 or nx>N-1 or ny>N-1:
                continue
            elif map[nx][ny]==0 or map[nx][ny]==shark_size:
                map[nx][ny]=map[x][y]+1
                queue.append((nx,ny)) #모든 경로를 고려하기 위해, 한칸 이동한 위치마다 다른 경로로의 경우를 고려
    while fish_queue: #아기상어가 먹을 수 있는 사이즈의 모든 물고기에 대해서, 각 물고기를 둘러싼 상하좌우 값을 살펴 최적 물고기를 찾아냄
        a,b=fish_queue.popleft()
        li=[]
        for i in range(4):
            nx=a+dx[i]
            ny=b+dy[i]
            if nx<0 or ny<0 or nx>N-1 or ny>N-1:
                continue
            elif abs(x_fix-a)+abs(y_fix-b)==1: #만약 아기상어의 현 위치 바로 옆에 먹을 수 있는 물고기가 있다면, 이동거리는 1
                li.append(1)
            elif map[nx][ny]>=9: #이동거리를 10부터 카운트하므로.
                li.append(map[nx][ny])
        if len(li)>0: #먹을 수 있는 물고기 중 가장 작은 이동값을 갖는 물고기의 거리, 좌표를 results에 추가
            results.append((min(li),a,b))
fish_queue=deque() #목표가 될 물고기들의 좌표 담는 큐
queue=deque() #bfs 계산시 사용할 큐
whole_fish=[] #모든 물고기의 좌표를 담는 리스트
time=0 #물고기를 잡아먹을 수 있는 시간
num_fish=0 #아기상어가 해당 사이즈에서 먹은 물고기 수
for i in range(N):
    for j in range(N):
        if space[i][j]>0 and space[i][j]<shark_size: #아기상어보다 작은 모든 물고기 위치
            fish_queue.append((i,j))
        if space[i][j]==9: #아기상어의 초기 위치
            x=i;y=j
        if space[i][j]>0 and space[i][j]!=9:
            whole_fish.append((space[i][j],i,j)) #전체 물고기 수를 세어 최대 반복횟수 정하기 위함.

for _ in range(len(whole_fish)):
    map=copy.deepcopy(space) #이동거리 담을 map
    results=[] #목표가 된 물고기들의 최적 경로와 좌표 담는 리스트
    get_distances(x,y) #1회 실행
    if len(results)>0:
        results.sort()
        if results[0][0]==1: #아기상어의 현재 위치와 먹을 수 있는 가장 가까운 물고기가 붙어있는 경우
            time+=1
        elif results[0][0]!=1:
            time+=results[0][0]-8 #선택된 이동경로 거리(=시간)를 총 시간에 더함. 단, 이동거리를 아기상어 위치값인 9 기준으로 시작하여 계산하였으며(-9), 마지막 한칸 이동값은 포함되지 않았으므로(+1) 8을 빼주어야 함
        space[x][y]=0 #아기상어의 원래 위치값을 0으로 초기화
        x=results[0][1];y=results[0][2] #아기상어의 현재 위치를 이동한 좌표로 재지정
        space[x][y]=9 #아기상어의 현재 위치의 값을 9로 변경
        num_fish+=1
        '''아기상어 크기가 9를 넘어가면 이동경로를 아기상어보다 작은 물고기로 추가할 수 있으므로 반드시! 제한 걸어야 함'''
        if num_fish==shark_size and shark_size<9: #먹은 물고기 수=아기상어 사이즈 =>아기상어 사이즈업+물고기 수 초기화+사이즈는 9이상 자라지 못하게!
            shark_size+=1
            num_fish=0
    for i in range(N):
        for j in range(N):
            if space[i][j]>0 and space[i][j]<shark_size: #아기상어보다 작은 모든 물고기 위치
                fish_queue.append((i,j))
            if space[i][j]==9: #아기상어의 초기 위치
                x=i;y=j
print(time)