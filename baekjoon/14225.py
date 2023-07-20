# 14225. 부분수열의 합(백준, 실버 1)

# 아이디어 : 1. 모든 부분집합의 합을 계산하여 result 리스트에 넣고,
#2. 비교를 위해 result를 list->set->list 하여 중복값 없애준 뒤 정렬
#3. 1부터 result의 최댓값까지를 앞에서부터 비교해 존재하지 않는 가장 작은 수를 찾기 위해 비교 리스트 comp 생성
#4. for문을 통해 앞에서부터 비교, 가장 작은 값을 구하면 ans에 넣고 break.
#5. 두 리스트가 완벽하게 일치하는 경우를 대비해 0으로 초기화된 ans를 통해 결과값 출력

#1.
from itertools import combinations
n=int(input())
result=[]
li=list(map(int,input().split()))
for i in range(1,n+1):
    every_set = list(combinations(li,i))
    for j in range(len(every_set)):
        result.append(sum(every_set[j]))
#2.
result=list(set(result))
result.sort()
#3.
ans=0
comp=[i for i in range(1,result[-1]+1)]
#4.
for i in range(len(result)):
    if result[i]!=comp[i]:
        ans=comp[i]
        break
#5.
if ans==0:
    print(result[-1]+1)
else:
    print(ans)