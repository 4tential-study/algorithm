from itertools import combinations

N,M = map(int, input().split(' '))
graph = [list(map(int, input().split())) for _ in range(N)]
home = []
chicken= []


for i in range(N):
    for j in range(N):
        if graph[i][j] == 2:
            chicken.append((i,j))
        elif graph[i][j] == 1:
            home.append((i,j))

pick_chicken = list(combinations(chicken,M))
result = [0]*len(pick_chicken)

for i in home:
    for j in range(len(pick_chicken)):
        a = 100
        for k in pick_chicken[j]:
            temp = abs(i[0]-k[0])+abs(i[1]-k[1])
            a= min(a, temp)
        result[j] += a

print(min(result))

