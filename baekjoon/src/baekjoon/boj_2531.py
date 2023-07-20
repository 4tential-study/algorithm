

def solution():
    input_line = list(map(int,input().split()))
    N : int = int(input_line[0])
    d : int = int(input_line[1])
    k : int = int(input_line[2])
    c : int = int(input_line[3])

    flates = []

    max = 0
    for i in range(N):
        n = int(input())
        flates.append(n)
        if max < n:
            max = n

    kind_of_flate2 = [0] * (max + 1)
    kind_of_flate2[c] += 1

    #
    count: int = 0

    for i in range(0, k):
        if kind_of_flate2[flates[i]] == 0:
            kind_of_flate2[flates[i]] += 1
            count += 1

    print(":", count)

    for idx in range(0, N):
        end = idx + k
        if end >= N:
            end = end % N

        if kind_of_flate2[idx] > 0:
            kind_of_flate2[idx] -= 1

        if kind_of_flate2[idx] == 0:
            count -= 1

        if kind_of_flate2[end] == 0 :
            kind_of_flate2[end] += 1
            count += 1
        print(": ", count)

    for i in kind_of_flate2:
        print(i)

    print(count)

solution()

