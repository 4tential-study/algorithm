#자료참고

dx=[-1,1,0,0]
dy=[0,0,-1,1]

def solution(board, aloc, bloc):
    #그냥 바로 solve 함수 호출해서 결과로 return되는 list의 두 번째 값을 반환함!
    return solve(board, aloc[0],aloc[1],bloc[0],bloc[1])[1]

def in_range(board,y,x):
    #범위를 벗어날 경우 False 리턴 (아니면 True)
    if y<0 or x<0 or y>=len(board) or x>=len(board[0]):
        return False
    return True

def is_finished(board,y,x):
    for i in range(4):
        nx=x+dx[i]
        ny=y+dy[i]
        #상,하,좌,우 중 단 한 칸이라도 범위를 벗어나지 않았고, board[ny][nx]==True이면 아직 안끝남!
        if in_range(board,ny,nx) and board[ny][nx]:
            return False
    #모든 칸이 갈 수 없으면 끝남?=True가 된다~
    return True

def solve(board, y1,x1,y2,x2):
    if is_finished(board,y1,x1):
        return [False,0]
    #게임 시작 시점에서 서로의 위치가 같을 때 먼저 움직이는 쪽이 이김\
    #누가 아기든 간에 1번만에 끝나지롱
    if y1==y2 and x1==x2:
        return[True, 1]
    min_turn=int(1e9)
    max_turn=0
    can_win=False

    #dfs 시작
    for i in range(4):
        ny=y1+dy[i]
        nx=x1+dx[i]
        #ny,nx가 범위 벗어나거나 이미 점령된 칸이라면? continue
        if not in_range(board,ny,nx) or not board[ny][nx]:
            continue
        #움직였으니 칸은 비게 된다.
        board[y1][x1]=0
        result=solve(board,y2,x2,ny,nx) # 차례가 바뀌므로 위치 바꾸어 줌
        board[y2][x2]=1

        #이 시점에서는 result[0]이 False일 때만 이김
        if not result[0]:
            can_win=True
            min_turn=min(min_turn, result[1])
        elif not can_win:
            max_turn = max(max_turn, result[1])
    if can_win==True:
        turn=min_turn
    else:
        turn=max_turn
    return [can_win, turn+1]