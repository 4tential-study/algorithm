#모르겠어서 구글링함... 다시 풀어봐야 할 문제(1)

def draw_stars(n):
    #n=1일 떄 별을 한개 리턴해준다.
    if n==1:
        return ['*']
    #n!=1일 때 draw_stars(n//3)을 Stars에 넣어준다.
    Stars=draw_stars(n//3)
    L=[]
    #아래 과정을 통해 Stars에는 n=3일떄의 모형, n=9일떄의 모형 등이 순차적으로 들어가게 되며, n=3일때의 형태를 쌓아서 n=9를 만드는 식으로 나아간다.
    for star in Stars:
        L.append(star*3)
    for star in Stars:
        L.append(star+' '*(n//3)+star)
    for star in Stars:
        L.append(star*3)

    return L

N=int(input())
print('\n'.join(draw_stars(N)))