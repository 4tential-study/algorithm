import math

global operators
max_result = -1000000001
min_result = 1000000001
n = int(input())
nums = list(map(int,input().split()))
operators = list(map(int,input().split()))

def bf( nums : [], index: int, temp: int): # index = nums의 순서
    if index == n:
        global max_result
        global min_result
        max_result = max(temp, max_result)
        min_result = min(temp, min_result)
        return

    for i in range(4): # operator순회
        global operators
        if operators[i] > 0:
            operators[i] -= 1
            if i == 0:
                bf(nums, index+1, temp+nums[index])
            elif i==1:
                bf(nums, index+1, temp-nums[index])
            elif i==2:
                bf(nums, index+1, temp*nums[index])
            else:
                bf(nums, index+1, int(temp/nums[index]))
            operators[i] += 1
    return

bf(nums, 1,nums[0])
print(max_result)
print(min_result)

# - "//"
# 연산자의 경우, 몫보다 작거나 같은 정수를 선택하고, / 연산자는 소수 부분을 버린다.
# - -1 / 3 의 경우, 0이 아니라, -1이 나온다.
# - 즉, int()와 / 를 사용해야한다.