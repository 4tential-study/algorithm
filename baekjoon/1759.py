#1759. 암호 만들기 (백준, 골드 5) - 재귀(순열)

#어찌저찌 해냄! itertools 라이브러리에서 졸업하는 중.
#아이디어 : 오늘 배운 그대로, permutation을 재귀 형식으로 구현.
#단, 알파벳으로 넣는 경우 대소비교를 통한 사전순 생성이 어렵다는 점을 고려하여 각 알파벳의 인덱스로 순열을 만들고,
#마찬가지로 자음 / 모음 인덱스와의 비교로 알파벳의 자음 모음 개수를 카운트해,
#순열이 완성되면 해당 인덱스에 해당하는 알파벳을 조합해 조건을 검사하고 결과물을 반환하였음.
L,C=map(int,input().split())
characters=list(map(str,input().split()))
characters.sort()
visited=[False]*C
pwd=[0]*L
result=[]
vowel=[]
consonant=[]

#모음자음의 인덱스를 체크!
for i in range(C):
    if characters[i] in ['a','e','i','o','u']:
        vowel.append(i)
    else:
        consonant.append(i)

def dfs(depth):
    if depth==L:
        word=''
        v_cnt=0
        c_cnt=0
        for i in range(L):
            #pwd에 넣어뒀던 인덱스를 characters 리스트에 넣어 해당하는 알파벳을 word에 넣어줌
            word+=characters[pwd[i]]
            #pwd의 모든 인덱스에 대하여, 자음의 인덱스인지 / 모음의 인덱스인지 확인하여 개수 카운트
            if pwd[i] in vowel:
                v_cnt+=1
            else:
                c_cnt+=1
        #모음 1개 이상, 자음 2개 이상 조건 충족하면 해당 단어를 result 리스트에 넣어줌.
        if v_cnt>=1 and c_cnt>=2:
            result.append(word)
        return
    for i in range(C):
        if not visited[i]:
            visited[i]=True
            if depth==0:
                pwd[0]=i
                dfs(depth+1)
            elif depth>0:
                if pwd[depth-1]<i:
                    pwd[depth]=i
                    dfs(depth+1)
            visited[i]=False
dfs(0)
print(*result,sep='\n')