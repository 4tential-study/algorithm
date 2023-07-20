#2531. 회전초밥(백준, 실버 1)

#아이디어 : (완전탐색) 
#1. n개의 초밥 중 k개의 초밥을 먹을 수 있는 모든 경우를 sushi 리스트에 넣는다.
#2. 이때, sushi 리스트에 들어가는 초밥은 회전초밥임을 감안해 0~n-1의 초밥 전부 sushi 리스트의 첫 번째 요소(i)가 될 수 있도록 sushi 리스트를 n번 만든다.
#3. i부터 k개의 초밥을 먹을 것이므로 i~i+k 인덱스로 고려하되, i+k가 n보다 클 경우를 고려해 %n으로 앞쪽 초밥으로 돌아가도록 해준다. (ex. 초밥이 8개 있을 때, k=4이고 6번 초밥이 i이면 i+k초밥은 9가 되므로 범위를 벗어난다. 이때 (i+k)%n=1이 되어 앞쪽 초밥으로 이어져서 돌아간다. )

#1.
n,d,k,c=map(int,input().split())
li=[int(input()) for _ in range(n)]
cnt=0
#2.
for i in range(n):
  sushi=[]
  #3.
  for j in range(i,i+k):
    sushi.append(li[j%n])
  sushi.append(c)
  sushi=set(sushi)
  cnt=max(cnt,len(sushi))
print(cnt)