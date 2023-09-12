#자료 참고 ㅠㅠ

def solution(info, edges):
    visited = [0] * len(info)
    visited[0]=1
    answer=[]
    
    #함수 안에 함수 선언해서 재귀시키는 건 처음 봅니다..진짜 대박
    def dfs(sheep, wolf):
        if sheep > wolf:
            answer.append(sheep)
        else:
            return 
        
        for p, c in edges:
            if visited[p] and not visited[c]:
                visited[c] = 1
                if info[c] == 0:
                    dfs(sheep+1, wolf)
                else:
                    dfs(sheep, wolf+1)
                visited[c] = 0
    dfs(1, 0)

    return max(answer)