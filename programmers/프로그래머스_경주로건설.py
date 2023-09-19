from collections import deque


def solution(board):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    N = len(board)
    costs = []

    def bfs():
        queue = deque()
        queue.append((0, 0, -1, 0))  #x좌표, y좌표, 현방향, 현재비용을 전달
        board[0][0] = -1
        while queue:
            x, y, last_dir, c = queue.popleft()
            print(*board,sep='\n')
            for i in range(4):
                cost = c
                nx = x + dx[i]
                ny = y + dy[i]
                #범위를 벗어나거나, 이미 방문한 곳이거나, 벽이라면 더는 나아가지 않음
                if nx < 0 or ny < 0 or nx >= N or ny >= N or board[nx][ny] == 1:
                    continue
                if last_dir == -1:
                    cost += 100
                elif i == last_dir:  #같은 방향으로 나아가는 경우라면
                    cost += 100
                else:  #다른 방향으로 꺾인다면
                    cost += 600
                print('cost',c,'dir',i,'last_dir',last_dir)
                if board[nx][ny] == 0:  #이전에 방문한 적 없는 지점이라면
                    board[nx][ny] = cost
                    queue.append((nx, ny, i, cost))  #큐에 추가
                else:  #이전에 방문한 적 있는 지점이라면
                    if cost <= board[nx][ny]:  #현재의 경로가 더 최적이거나, 현재의 경로와 이전 경로의 성능이 같거나, 방향이 같은 경우
                        board[nx][ny] = cost  #최소값 갱신
                        queue.append((nx, ny, i, cost))
                if nx == ny == N - 1:  #도착지점이라면
                    costs.append(cost)
                    break

    bfs()
    answer = 0
    answer=min(costs)
    return answer