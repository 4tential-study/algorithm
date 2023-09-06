def time_checker(music):
    start=music[0].split(':')
    end=music[1].split(':')
    time=int(end[0])*60-int(start[0])*60+int(end[1])-int(start[1])
    return time


def solution(m, musicinfos):
    answer = ''
    names=[]
    results=[]
    times=[]
    original_melody=[]
    
    for i in range(len(m)): #오리지널 멜로디의 음을 잘라서 original_melody 리스트에 넣기
        if m[i]=='#':
            del original_melody[-1]
            original_melody.append(m[i-1:i+1])
        else:
            original_melody.append(m[i])
        # if m[i+1]!='#':
        #     original_melody.append(m[i])
        # elif m[i]=='#':
        #     original_melody[-1]=m[i-1:i+1]
    original_length=len(original_melody) # original_length에 해당 리스트의 크기 저장

    for i in range(len(musicinfos)): # 각 곡에 대하여
        entire=[] # 전체 곡의 음을 담을 entire 리스트
        result=0 # 가장 긴 겹치는 길이를 갱신할 result
        music=musicinfos[i].split(',') # 각 곡에 대해서 0번은 시작시간, 1번은 끝나는 시간, 2번은 곡의 제목, 3번은 반복된 멜로디로 저장
        time=time_checker(music) # 음악이 재생된 총 길이를 구하는 time_checker 함수 사용하여 time에 저장
        times.append(time) # 해당 곡에 대하여 재생된 총 길이를 times 리스트에 저장
        names.append(music[2]) # 해당 곡의 제목을 names 리스트에 저장
        melody=[]
        mels=music[3] # mels에 해당 멜로디 담아주기
        for j in range(len(mels)): # 해당 곡의 멜로디의 음을 잘라서 melody 리스트에 넣기
            if mels[j]=='#':
                del melody[-1]
                melody.append(mels[j-1:j+1])
            else:
                melody.append(mels[j])

            # if mels[j]!='#':
            #     melody.append(mels[j])
            # elif m[i]=='#':
            #     melody[-1]=m[i-1:i+1]
        # print(melody)
        div=time//len(melody) # div는 전체 시간을 멜로디의 크기로 나눈 몫
        rest=time%len(melody) # rest는 전체 시간을 멜로디의 크기로 나눈 나머지
        for j in range(div): # 몫의 횟수만큼 entire에 멜로디를 넣어줌
            for k in range(len(melody)):
                entire.append(melody[k])
        # print(melody)
        # print(rest)
        for j in range(rest): # 나머지만큼 멜로디를 잘라서 entire에 넣어줌
            # print(rest,"j",j)
            entire.append(melody[j])
        for j in range(time): # 전체 시간에 대하여
            cnt=0 # 얼마나 겹치는지 측정하는 cnt 초기화
            if original_melody[0]==entire[j]:
                for k in range(original_length):
                    if j+k>=len(entire)-1:
                        break
                    if original_melody[k]==entire[j+k]:
                        cnt+=1
                    else:
                        break
            # print(cnt)
            result=max(cnt,result)
        results.append(result)
        # print(entire)
    max_val=max(results)
    # print(max_val)
    if max_val<original_length:
        answer='none'
    elif results.count(max_val)==1:
        answer=names[results.index(max_val)]
    else:
        ind=results.index(max_val)
        for i in range(len(results)):
            if results[i]==max_val:
                if times[i]>times[ind]:
                    ind=i
        answer=names[ind]
    return answer


# m="ABCDEFG"
# musicinfos=["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"]
# m="CC#BCC#BCC#BCC#B"
# musicinfos=["03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"]

# m="ABC" 
# musicinfos=["12:00,12:06,HELLO,ABC#ABC#ABC"] 
# "(None)"
# m="ABC" 
# musicinfos=["12:00,12:10,HELLO,ABC#ABC#ABC"] 
# "HELLO"
# m="ABC" 
# musicinfos=["12:04,13:00,HELLO,ABC#ABC#ABC"]
# "HELLO"
# m="C#C" 
# musicinfos=["12:00,12:06,HELLO,C#C#CC#"]
#  "HELLO"
m = "ABC"
musicinfos=["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"]

print(solution(m,musicinfos))