#답지 봤어요. 만감이 교차하네요. dp는 아무쪼록 많이 해서 형식을 외워야겠다 싶습니다!

import sys
input= sys.stdin.readline

T=int(input())
K=int(input())
coins=[list(map(int,input().split())) for _ in range(K)]
dp=[0]*(T+1)
dp[0]=1
for coin,cnt in coins:
    for money in range(T,0,-1):
        for i in range(1,cnt+1):
            if money-coin*i>=0:
                dp[money]+=dp[money-coin*i]
print(dp[-1])