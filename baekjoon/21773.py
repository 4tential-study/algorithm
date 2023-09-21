import heapq
T,n=map(int,input().split())
result=[]
heap=[]
for i in range(n):
    a,b,c=map(int,input().split())
    heapq.heappush(heap,(-c,a,b))
#a: id, b: 실행시간, c: 우선순위
#우선순위가 큰 순서부터 선택되므로, 우선순위를 -로 바꾸어 넣어 최대 힙을 구현한다.
for i in range(T):
    temp=heapq.heappop(heap)
    c=temp[0]
    a=temp[1]
    b=temp[2]
    # 실행된 프로세스의 id를 결과에 넣어 출력할 수 있게 한다.
    result.append(a)
    b-=1
    # 실행된 프로세스를 제외한 나머지 프로세스들의 우선순위가 1 상승하는 것은,
    # 실행된 프로세스의 우선순위만 1 감소하는 것과 같다.
    c+=1 # 우선순위는 음수이므로 1을 더해서 감소시킨다.
    if b!=0:
        heapq.heappush(heap,(c,a,b))
print(*result,sep='\n')