# @simdronedrone 참고
n,d,k,c=map(int,input().split())
list=[int(input()) for _ in range(n)]
cnt = 0

for i in range(n):
    eating_plates = []
    for j in range(i,i+k):
        eating_plates.append(list[j%n])
    eating_plates.append(c)
    eating_plates = set(eating_plates)
    cnt = max(cnt, len(eating_plates))

print(cnt)

