#자료참고

dx=[-1,1,0,0]
dy=[0,0,-1,1]

def solution(board, aloc, bloc):
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
        if in_range(board,ny,nx) and board[ny][nx]:
            return False
    return True

def solve(board, y1,x1,y2,x2):
    if is_finished(board,y1,x1):
        return [False,0]
    #서로의 위치가 같을 때 먼저 움직이는 쪽이 이김
    if y1==y2 and x1==x2:
        return[True, 1]
    min_turn=int(1e9)
    max_turn=0
    can_win=False

    #dfs 시작
    for i in range(4):
        ny=y1+dy[i]
        nx=x1+dx[i]
        if not in_range(board,ny,nx) or not board[ny][nx]:
            continue
        board[y1][x1]=0
        result=solve(board,y2,x2,ny,nx) # 차례가 바뀌므로 위치를 바꾸어 줌
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