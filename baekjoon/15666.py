#Why recursion error? Need to be fixed, and I'm on it.
import sys
sys.setrecursionlimit(10000)
n,m=map(int,input().split())
li=list(set(list(map(int,input().split()))))
li.sort()
permutation=[]
result=[]
def permutation_maker(depth):
    if depth==m:
        result.append(''.join(map(str,permutation)))
    for i in range(len(li)):
        if depth==0 or permutation[-1]<=li[i]:
            permutation.append(li[i])
            permutation_maker(depth+1)
            permutation.pop()
permutation_maker(0)
print(*result,sep='\n')