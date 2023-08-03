# N=int(input())
# dp=[[] for _ in range(N)]
# dp[0].append(9)
# for i in range(N-1):
#     for j in dp[i]:
#         if j==1:
#             dp[i+1].append(j)
#         else:
#             dp[i+1].append(j-1)
#             dp[i+1].append(j)
# print(dp[N-1])
# print(sum(dp[N-1])%1000000000)

# N=int(input())
# dp=[[] for _ in range(N)]
# dp=[0]*9
# dp[8]=1
# for i in range(N-1):
#     for j in range(8):
#         if j==0:
#             dp[j]+=dp[j]
#         else:
#             dp[j]+=dp[j+1]
# ans=0
# for i in range(9):
#     ans+=dp[i]*(i+1)
# print(ans%1000000000)

#자료참고
n = int(input())
dp = [[0 for i in range(10)] for j in range(101)]
for i in range(1, 10):
    dp[1][i] = 1
for i in range(2, n + 1):
    for j in range(10):
        if j == 0:
            dp[i][j] = dp[i - 1][1]
        elif j == 9:
            dp[i][j] = dp[i - 1][8]
        else:
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1]
print(sum(dp[n]) % 1000000000)