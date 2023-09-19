#역시 함수를 나누어 짜는 게 최고라는 걸 느꼈습니다.

n=int(input())
board=[list(map(str,input())) for _ in range(n)]
answer=[0]

def swap(x,y,nx,ny):
    temp=board[x][y]
    board[x][y]=board[nx][ny]
    board[nx][ny]=temp

def check():
    for i in range(n):
        cnt=1
        for j in range(n):
            if j==n-1:
                answer[0]=max(answer[0],cnt)
                cnt=1
                break
            if board[i][j]==board[i][j+1]:
                cnt+=1
            else:
                answer[0]=max(answer[0],cnt)
                cnt=1
        for j in range(n):
            if j==n-1:
                answer[0]=max(answer[0],cnt)
                cnt=1
                break
            if board[j][i]==board[j+1][i]:
                cnt+=1
            else:
                answer[0]=max(answer[0],cnt)
                cnt=1

for i in range(n):
    for j in range(n):
        if i!=n-1:
            swap(i,j,i+1,j)
            check()
            swap(i,j,i+1,j)
        if j!=n-1:
            swap(i,j,i,j+1)
            check()
            swap(i,j,i,j+1)

print(*answer)