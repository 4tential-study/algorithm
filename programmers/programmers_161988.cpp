#include <string>
#include <vector>

using namespace std;
typedef long long ll;

// 누적합 배열 필요 없는 듯?
// 펄스 배열을 뒤집은 경우 -1을 곱한 것과 같기에 하나만 사용
// ll prefix[500001];

ll solution(vector<int> sequence) {
    ll answer = 0;

    // +1로 시작하는 펄스 수열의 합
    ll prefixSum1 = 0;
    
    // -1로 시작하는 펄스 수열의 합
    ll prefixSum2 = 0;
    
    for(int i = 0; i < sequence.size(); i++){
        if(i % 2 == 0){
            prefixSum1 += sequence[i];
            prefixSum2 -= sequence[i];
        }
        else{
            prefixSum1 -= sequence[i];
            prefixSum2 += sequence[i];
        }
        
        // 이 부분 생각하기가 되게 어렵네요
        if(prefixSum1 < 0) prefixSum1 = 0;
        if(prefixSum2 < 0) prefixSum2 = 0;
        
        // answer, prefixSum1, prefixSum2중의 최대값
        if(prefixSum1 > answer) answer = prefixSum1;
        if(prefixSum2 > answer) answer = prefixSum2;
    }

    return answer;
}

/*


*/