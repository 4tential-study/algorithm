def solution():
    input_line = list(map(int,input().split()))
    N : int = int(input_line[0])
    d : int = int(input_line[1]) # 초밥의 총 가짓수
    k : int = int(input_line[2]) # 연속해서 먹을 접시 수
    c : int = int(input_line[3]) # 쿠폰의 초밥번호
    # 회전초밥을 넣을 리스트 생성
    plates = []
    # 넣기
    for i in range(N):
        n = int(input())
        plates.append(n)

    # 초밥종류만큼의 리스트 생성
    kind_of_plate = [0] * (d+1)
    # 쿠폰번호에 적힌 초밥번호 + 1


    #초밥 종류 개수
    count: int = 0



    print(":", count)

    for idx in range(0, N):
        # [배열이 초과하는 경우]
        end = idx + k
        if end >= N:
            end = end % N

        # 처음
        if idx == 0:
            for i in range(idx, end):
                if kind_of_plate[plates[i]] == 0:
                    kind_of_plate[plates[i]] += 1
                    count += 1
        else: # 이후
            for i in range(idx, end):
                kind_of_plate[idx] -= 1
                if kind_of_plate[idx] == 0:
                    count -= 1
                if kind_of_plate[end] == 0:
                    kind_of_plate[end] += 1
                    count += 1
    # 쿠폰 초밥 추가
    if kind_of_plate[c] == 0:
        count += 1
    print(count)


solution()