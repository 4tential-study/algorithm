# https://donggod.tistory.com/11 자료 참조. 그림까지 보고 겨우 이해!
n,m=map(int,input().split())
male_li=list(map(int,input().split()))
female_li=list(map(int,input().split()))
male_li.sort()
female_li.sort()

dp=[[0 for _ in range(m+1)] for _ in range(n+1)]

for i in range(1,n+1):
    for j in range(1,m+1):
        dp[i][j]=dp[i-1][j-1]+abs(male_li[i-1]-female_li[j-1])
        if i>j:
            dp[i][j]=min(dp[i][j], dp[i-1][j])
        elif i<j:
            dp[i][j]=min(dp[i][j],dp[i][j-1])
print(dp[n][m])