#자료참고
n, c = map(int, input().split())

array = []
for i in range(n):
    array.append(int(input()))

array.sort()


def binary_search(array, start, end):
    while start <= end:
        mid = (start + end) // 2
        current = array[0]
        count = 1

        for i in range(1, len(array)):
            if array[i] >= current + mid: #앞 공유기의 위치+일정거리(mid) 한 것보다 다음 집이 멀리 있으면 그곳에 설치
                count += 1 #설치한 공유기의 개수가 하나 늘어났다!
                current = array[i] #지금 집에 공유기 설치하였으므로 앞 공유기의 위치 업데이트

        if count >= c: #공유기가 정해진 것보다 많이 설치된 경우(즉, 거리가 더 넓어져야 함)
            global answer
            start = mid + 1 #공유기를 더 멀리 설치해야 하므로, mid를 늘려주기 위해 start=mid+1 해준다. 즉, 이전에 mid=50이었다면, 이제는 mid=75가 될 것
            answer = mid #현재의 일정 거리값(mid)을 answer에 저장. 주어진 조건보다 많이 설치될 수 있다는 뜻이므로.
        else:
            end = mid - 1 #공유기가 정해진 것보다 적게 설치된 경우, answer를 업데이트할 필요는 없다. 무조건 더 줄어야 하기 때문에. 대신 mid를 줄여주기 위해 end를 옮긴다.


start = 1 #인덱스나 로케이션이 아니라, '일정거리(mid)'를 계산하기 위한 것이므로 1부터 시작
end = array[-1] - array[0] #마찬가지로, '일정거리(mid)'를 계산하기 위한 것이므로 첫 집과 마지막 집의 거리(최장거리)로 시작
answer = 0

binary_search(array, start, end)
print(answer)