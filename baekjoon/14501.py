import sys
input=sys.stdin.readline
N=int(input())
dp=[[0] for _ in range(N+1)]
T_li=[0]*(N+1)
P_li=[0]*(N+1)
ans=0
for i in range(N):
    T_li[i],P_li[i]=map(int,input().split())
for i in range(N):
    if i>0:
        dp[i].append(max(dp[i-1]))
    if i+T_li[i]<N+1:
        num=max(dp[i])+P_li[i]
        if num>ans:
            ans=num
        dp[i+T_li[i]].append(num)
print(ans)