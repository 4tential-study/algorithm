#아이디어 : 
# 1. 0부터 n-1까지의 숫자를 members에 넣고 combinations를 사용해 그 중 절반을 선정하는 모든 경우의 수 구함
# 2. 모든 경우의 수를 담은 start_team 리스트의 각 함수에 대하여 combinations로 둘씩의 쌍을 구해 Sums에 저장
# 3. Sums안의 i,j 쌍에 대하여 그대로의 좌표값 / j,1에 대한 좌표값의 능력치를 s에 넣어줌
# 4. s를 모으는 sum_li에 append해줌
# 5. 이때, sum_li의 값은 순차적으로 계산된 combinations를 통해 구한 것이므로, 만약 0,1,2,3번 멤버에 대해서 0,1의 점수가 sum_li[0]에 저장되었다면 2,3의 점수가 sum_li[-1]에 저장됨.
# 6. 이 점을 이용해 점수 값 차가 가장 작은 값을 diff에 갱신해주는 방식으로 구함
from itertools import combinations

n=int(input())
li=[list(map(int,input().split())) for _ in range(n)]
#1.
members=[i for i in range(n)]
start_team=list(combinations(members,n//2))
start=0
link=0
sum_li=[]
#2.
for i in range(len(start_team)):
  Sums=list(combinations(start_team[i],2))
  s=0
  #3.
  for j in range(len(Sums)):
    s+=li[Sums[j][0]][Sums[j][1]]
    s+=li[Sums[j][1]][Sums[j][0]]
  #4.
  sum_li.append(s)
#5, 6.
diff=int(1e9)
for i in range(len(sum_li)//2):
  diff=abs(sum_li[i]-sum_li[-i-1]) if (abs(sum_li[i]-sum_li[-i-1])<diff) else diff
print(diff)