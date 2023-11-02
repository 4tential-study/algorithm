from collections import deque

n=int(input())
for i in range(n):
    inp=input()
    q=deque()
    temp=deque()
    for j in range(len(inp)):
        if inp[j]=='-':
            if len(q)!=0:
                q.pop()
        elif inp[j]=='<':
            if len(q)!=0:
                temp.append(q.pop())
        elif inp[j]=='>':
            if len(temp)!=0:
                q.append(temp.pop())
        else:
            q.append(inp[j])
    temp.reverse()
    result=''.join(q)+''.join(temp)
    print(result)