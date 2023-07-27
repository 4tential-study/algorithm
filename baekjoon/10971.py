#이게..맞나? 이게 백트래킹?
n=int(input())
li=[list(map(int,input().split())) for _ in range(n)]
graph=[[] for _ in range(n)]
for i in range(n):
    for j in range(n):
        if j==i:
            continue
        elif li[i][j]==0:
            continue
        else:
            graph[i].append((li[i][j],j))
    graph[i].sort()
ans=[int(1e9)]
visited=[False]*(n+1)
visited[0]=True
def circuit(start,now,n,depth,ans,cost):
    if depth == (n-1):
        if li[now][start]!=0:
            cost+=li[now][start]
            ans[0]=min(ans[0],cost)
        return
    if cost>=ans[0]:
        return
    for pair in graph[now]:
        plus_cost=pair[0]
        city=pair[1]
        if not visited[city] and li[now][city]!=0:
            visited[city]=True
            circuit(start,city,n,depth+1,ans,cost+plus_cost)
            visited[city]=False
    return #이게 맞나?...
circuit(0,0,n,0,ans,0)
print(*ans)