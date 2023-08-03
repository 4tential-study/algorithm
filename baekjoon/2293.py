#갓-다민님의 알고리즘 풀이! 없었으면 오늘도 하나 날렸을뻔...^^ dp 어려워용
n,k=map(int,input().split())
coins=[int(input()) for _ in range(n)]
dp=[0]*(k+1)
for i in range(k+1):
    if i%coins[0]==0:
        dp[i]=1
for i in range(n):
    if i==0:
        continue
    for j in range(coins[i],k+1):
        dp[j]+=dp[j-coins[i]]
print(dp[-1])