#2225. 합분해 (백준, 골드 5)
#꿈도 희망도 없었는데, 플래선생님의 알고리즘 강의 덕분에 문제가 쉬워졌어요! 다른 수강생분들께도 추천합니다bb

n, k = map(int, input().split())
arr = [[0] * (k+1) for _ in range(n+1)]
for i in range(n+1):
  for j in range(1,k+1):
    if i==0 and j==0:
      continue
    if j==0:
      continue
    if i==0:
      arr[i][j]=1
    else:
      arr[i][j]=(arr[i-1][j]+arr[i][j-1])%1000000000
print(arr[n][k])