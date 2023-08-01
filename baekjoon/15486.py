n=int(input())
days=[0]*(n+1) #해당 일에 며칠짜리 상담이 있는지 저장하는 리스트. 예를 들어, 1일에 2일이 걸리는 상담이 잡히면 days[1]=2 이다.
money=[0]*(n+1) #해당 일에 얼마짜리 상담이 있는지 저장하는 리스트. 예를 들어, 1일에 10원을 버는 상담이 있다면 money[1]=10 이다.
dp=[0]*(n+1) #날짜별로 벌 수 있는 최대금액을 갱신해줄 dp 리스트
#입력받기
for i in range(1,n+1):
    day, m = map(int,input().split())
    days[i]=day
    money[i]=m
#dp를 활용해 해당 일자의 강의를 들을 경우, 강의가 끝나는 날의 dp 인덱스에 강의 보수를 넣는다.
last_largest=0
max_val=0
for i in range(1,n+1):
    # 영대님이 갱신 아이디어를 주셨다. 감사합니다! (자다 인나서 뚝딱 푸셔서 조금 킹받..는데 감사)
    if i-2 > 0:
        dp[i-1] = max(dp[i-1], dp[i-2])
    if i+days[i]-1<=n:
        # 이때, max를 사용하여 해당 일자까지 벌 수 있는 최대 금액을 갱신하도록 한다.
        dp[i+days[i]-1]=max(dp[i+days[i]-1],dp[i-1]+money[i])
    max_val=max(max_val,dp[i])
print(max_val)