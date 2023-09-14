#dp였다고..? 자료 참고 ㅠㅠ
li=list(map(int,input())) #하나의 단어처럼 주어진 정수의 경우 split() 없이 map을 써서 int로 input을 list에 분할해 넣어주면 된다.
n=len(li)
dp = [0]*(n+1)
dp[0]=1
dp[1]=1
if (li[0]==0):
    print("0")
else:
    li=[0]+li
    n+=1
    for i in range(2,n):
        if li[i]>0:
            dp[i]+=dp[i-1]
        temp=li[i-1]*10+li[i]
        if temp>=10 and temp<=26:
            dp[i]+=dp[i-2]
        dp[i]=dp[i]%1000000
    print(dp[n-1])