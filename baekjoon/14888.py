from itertools import permutations
#주석 파업
#자바에서 combination이냐 permutation 구현하는 법 추천받습니다..

N=int(input())
numbers=list(map(int,input().split()))
operators=[]
plus,minus,product,divide=map(int,input().split())
li=[]
result=[]
for i in range(plus):
    operators.append('+')
for i in range(minus):
    operators.append('-')
for i in range(product):
    operators.append('*')
for i in range(divide):
    operators.append('/')
operators=list(permutations(operators,N-1))
for i in operators:
    li.append(''.join(i))
li=list(set(li))
for i in range(len(li)):
    num=numbers[0]
    for j in range(N-1):
        if li[i][j]=='+':
            num+=numbers[j+1]
        elif li[i][j]=='-':
            num-=numbers[j+1]
        elif li[i][j]=='*':
            num*=numbers[j+1]
        elif li[i][j]=='/':
            if num<0:
                num=-((-num)//numbers[j+1])
            else:
                num//=numbers[j+1]
    result.append(num)
print(max(result))
print(min(result))
