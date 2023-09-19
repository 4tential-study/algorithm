# 컨베이어 벨트 위의 로봇 코드
# 로직부터 잘못되었나..

n,k=map(int,input().split())
li=list(map(int,input().split()))
robots=[0]*2*n
up=0;down=n-1
robots[up]=1 # 0번 칸에 로봇이 올라감
li[0]-=1 # 0번 칸 내구도 감소
turn=2 # 몇 단계를 돌렸는지 표시
zero_cnt=-1
while (zero_cnt<k):
    zero_cnt=0 #내구성이 0인 칸의 개수 
    # 컨베이어를 돌리는 대신, 로봇을 올리고 내리는 지점을 하나씩 더해줌. 단, 범위를 벗어나는 걸 대비해서 나머지로 표현
    up=(up-1)%(2*n)
    down=(down-1)%(2*n)
    print('로봇', robots)
    print('내구성', li)
    print('이번 턴에서 올라가는 칸',up,'내려가는 칸',down)
    # 한 바퀴 돌린다고 가정했을 때의 각 칸을 계산
    for i in range(2*n):
        # 올리는 칸 내구성이 0이 아니고 로봇이 없으면 로봇을 올림
        if i==up and li[i]!=0 and robots[i]==0:
            robots[i]=turn
            li[i]-=1 # 로봇을 올렸으므로 내구도 감소
        elif i==up and li[i]==0 and robots[i]!=0:
            robots[i]=turn
        # 내리는 칸에 로봇이 있으면 내림
        elif i==down and robots[i]!=0:
            robots[i]=0
        # 그 외 모든 칸에 대하여 1. 해당 칸에 로봇이 있고, 2. 해당 칸의 다음 칸에 로봇이 없고, 3. 다음 칸의 내구성이 0이 아니라면
        elif robots[i]==turn-1 and robots[(i+1)%(2*n)]==0 and li[(i+1)%(2*n)]>0:
            # 로봇을 다음 칸으로 옮긴다.
            robots[i]=0
            robots[(i+1)%(2*n)]=turn
            # 다음 칸의 내구도는 1 감소
            li[(i+1)%(2*n)]-=1
        # 앞으로 이동하지 못하는 칸의 로봇에 대하여, 값을 갱신(값은 로봇이 한 턴에 여러 칸 이동하는 것을 막기 위해 설정되어 있음)
        elif robots[i]==turn-1:
            robots[i]=turn
        # 만약 위의 모든 과정을 거친 후 해당 칸의 내구도가 0이라면 zero_cnt+=1
        if li[i]==0:
            zero_cnt+=1
    # print('turn', turn)
    turn+=1
print('로봇', robots)
print('내구성', li)
print(turn-2)