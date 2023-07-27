from math import sqrt

common_max, common_min = map(int, input().split())

divide = common_min//common_max


def gcd(a, b):
    if a % b == 0:
        return b
    return gcd(b, a % b)

for a in range(int(sqrt(divide)),0,-1):
    b = int(divide/a)

    if divide % a == 0 and gcd(a,b)==1:
        print(a * common_max, b * common_max)
        break


