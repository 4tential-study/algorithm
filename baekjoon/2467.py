N = int(input())
li=list(map(int,input().split()))
li.sort()

result=(li[0],li[1]) #임의로 result를 초기화
mixed_liq=sum(result)
for i in range(N-1):
    start=i+1 #앞에서부터 용액 비교하므로, 현재 용액의 뒤에 위치한 용액부터 범위 적용
    end=N-1
    while start<=end:
        mid=(start+end)//2
        if li[i]+li[mid]==0: #산성도가 0이 되면 바로 값을 출력하고 종료
            print(li[i], li[mid])
            exit()
        elif abs(mixed_liq)>abs(li[i]+li[mid]) and li[i]+li[mid]<0: #산성도 합의 절댓값이 0에 더 가깝고, 산성도 합이 음수인 경우 업데이트
            start=mid+1
            result=(li[i],li[mid])
            mixed_liq=sum(result)
        elif 0<li[i]+li[mid]<abs(mixed_liq): #산성도 합의 값이 양수이고 절댓값도 0에 더 가까울 경우 업데이트
            end=mid-1
            result=(li[i],li[mid])
            mixed_liq=sum(result)
        elif li[i]+li[mid]<0: #산성도 합의 절댓값이 0에 더 가깝지는 않을 경우, 업데이트 없이 mid를 이동(산성도 합이 음수이므로 우측으로 이동)
            start=mid+1
        elif li[i]+li[mid]>0: #위와 같음, 산성도 합이 양수이므로 좌측으로 이동
            end=mid-1
print(*result)