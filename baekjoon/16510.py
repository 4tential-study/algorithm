#Predictable Queue
import sys
input = sys.stdin.readline

N,M=map(int,input().split())
li=list(map(int,input().split()))
time_li=[int(input()) for _ in range(M)]
for i in range(N-1):
    li[i+1]+=li[i]
for i in range(M):
    num=time_li[i]
    result=0
    for j in range(N):
        if j==N-1 and num>=li[j]:
            print(N)
        elif j==N-1 and num<li[j]:
            print(N-1)
        elif num>li[j]:
            continue
        elif num<li[j]:
            print(j)
            break
        else:
            print(j+1)
            break