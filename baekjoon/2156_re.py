n=int(input())
li=[int(input()) for _ in range(n)]
dp=[0]*(n+1)
for i in range(1,n+1):
    if i==1:
        dp[i]=li[i-1]
    elif i==2:
        dp[i]=dp[i-1]+li[i-1]
    elif i==3:
        dp[i]=max(dp[i-2]+li[i-1],dp[i-1])
    else:
        dp[i]=max(dp[i-3]+li[i-2]+li[i-1],dp[i-2]+li[i-1],dp[i-1])
print(dp[n])