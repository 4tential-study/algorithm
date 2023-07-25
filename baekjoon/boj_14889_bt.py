n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
# combination = list(itertools.combinations(list(range(n)),n//2))
answer = 99999




used = set()
listlist = []

def combination(order, list :[]):
    if n == order:
        listlist.append(list)
        return
    for i in range(1,n):
        if i in used:
            continue
        used.add(i)
        list.add(i)
        combination(i+1, list)
        used.remove(i)
        list.remove(i)

list= []
combination(0, list)
# listlist

print(listlist)
# for comb in listlist:

