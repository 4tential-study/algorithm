import sys

def solution():
    n = int(input())
    T = []
    P = []
    answer = [0 for _ in range(n+1)]
    for _ in range(n):
        t, p = map(int, input().split())
        T.append(t)
        P.append(p)


    for i in range(n-1, -1, -1):
        #print(i)
        if i + T[i] > n:
            answer[i] = answer[i+1]

            continue
        else:
            if T[i] == 1:

                answer[i] = P[i] + answer[i+1]


                continue

            elif answer[i+1] < answer[i+T[i]] + P[i]:

                answer[i] = P[i] + answer[i+T[i]]

            else:
                answer[i] = answer[i] + answer[i+1]


    print(answer[0])

solution()