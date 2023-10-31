#20440 - 니가 싫어 싫어 너무 싫어 오지 마 내게 찝쩍대지마.. 제목부터 신박하다.

#일반적인 방법으로 푸는 게 아니라 dict를 사용해서 풀 수 있다.
#물론 자료 참고했습니다.

import sys
input=sys.stdin.readline

n=int(input())
#누적합 위한 딕셔너리 생성
dict={}

for i in range(n):
    TE,TX=map(int,input().split())

    #dictionary에서 get 함수를 통해 key에 해당하는 value에 접근
    #단, 해당하는 키가 없어도 오류 대신 none을 반환해준다.
    #값 두 개를 넣는 경우, 예를 들어 dict.get(1,0)을 넣는 경우 1이라는 키가 없으면 해당 키를 생성하고 0을 넣어준다.
    #즉, 아래 식 대로라면 TE가 없는 경우 0을 value로 갖는 TE 키를 생성하고 1을 더해 1이라는 값을 갖게 해준다. TX의 경우 -1.
    dict[TE]=dict.get(TE,0)+1
    dict[TX]=dict.get(TX,0)-1

#결과를 담을 변수
mosquitoes=-1
start=0
end=0

#모기가 가장 많은 시간 중 가장 앞 타임을 판별하기 위해서
check=0

#시간 순서대로 탐색하기 위해 키를 기준으로 정렬
new_order=sorted(dict.keys())
cnt=0

for i in new_order:
    cnt+=dict[i]
    if cnt>mosquitoes:
        mosquitoes=cnt
        start=i
        check=1
    elif cnt<mosquitoes and check==1:
        end=i
        check=0
print(mosquitoes)
print(start,end)