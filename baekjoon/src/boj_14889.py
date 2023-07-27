import itertools

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
combination = list(itertools.combinations(list(range(n)),n//2))
answer = 99999

for combs in combination:
    visited = [0] * n
    s_ele = []
    for each in combs:
        visited[each] = True
        s_ele.append(each)
    s_sum = 0
    comb = list(itertools.combinations(s_ele,2))
    for each in comb:
        s_sum += board[each[0]][each[1]]+board[each[1]][each[0]]

    l_ele = []
    for i in range(n):
        if not visited[i]:
            l_ele.append(i)
    l_sum=0
    comb_l = list(itertools.combinations(l_ele,2))
    for each in comb_l:
        l_sum += board[each[0]][each[1]]+board[each[1]][each[0]]

    answer = min(answer, abs(s_sum-l_sum))

print(answer)