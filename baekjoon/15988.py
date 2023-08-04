T=int(input())
result=[]
for i in range(T):
    n=int(input())
    if (n<3): 
        dp=[0]*4
    else: 
        dp=[0]*(n+1)
    dp[1]=1; dp[2]=2; dp[3]=4
    for j in range(4,n+1):
        dp[j]=(dp[j-1]+dp[j-2]+dp[j-3])%1000000009
    result.append(dp[n])
print(*result,sep='\n')