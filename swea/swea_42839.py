#주석? 그런 거 없다. 제가 삭막한 사람인 게 아닙니다. 이 생활이 나를 이렇게 만든 겁니다.

from itertools import permutations

def solution(numbers):
    answer=0
    numbers=list(map(int,str(numbers)))
    madeup_numbers=[]
    for i in range(1,len(numbers)+1):
        li=list(permutations(numbers,i))
        for j in li:
            madeup_numbers.append(int(''.join(map(str,j))))
    madeup_numbers=list(set(madeup_numbers))
    madeup_numbers.sort()
    number=madeup_numbers[-1]
    decimal_li=[0]*(number+1)
    for i in range(2,number+1):
        decimal=1
        for j in range(2,int(i**(1/2))+1):
            if decimal==0:
                break
            if i%j==0:
                decimal=0
        if decimal==1:
            decimal_li[i]=1
    for i in madeup_numbers:
        if decimal_li[i]==1:
            answer+=1  
    return answer