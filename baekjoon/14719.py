h,w=map(int,input().split())
li=list(map(int,input().split()))
#와.. 여러 경우의 수를 생각해서 몇 시간동안 구현하고 고민해봤는데, 아무래도 모르겠어서 자료 검색했더니
#원리 자체는 엄청 간단하네요...충격입니다. 사고력이 중요한 문제인듯.
ans=0
for i in range(1,w-1):
    left=max(li[:i])
    right=max(li[i+1:])

    if li[i]<min(left,right):
        ans+=min(left,right)-li[i]
print(ans)