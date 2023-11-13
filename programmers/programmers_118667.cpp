#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    int answer = 0;
    
    long long sum1 = 0, sum2 = 0;
    
    for(int v : queue1) sum1 += v;
    for(int v : queue2) sum2 += v;
    
    int s1 = queue1.size(), s2 = queue2.size();

    int idx1 = 0, idx2 = 0;
    while(sum1 != sum2){
        if(idx1 >= s1 && idx2 >= s2){
            answer = -1;
            break;
        }
        
        if(sum1 > sum2){
            sum1 -= queue1[idx1];
            sum2 += queue1[idx1];
            
            // cout << "1 -> 2 : "<< queue1[idx1] << '\n';
                
            queue2.push_back(queue1[idx1++]);
        }else if(sum1 < sum2){
            sum1 += queue2[idx2];
            sum2 -= queue2[idx2];
            
            // cout << "2 -> 1 : "<< queue2[idx2] << '\n';
            
            queue1.push_back(queue2[idx2++]);
        }
        
        // cout << "sum1 : " << sum1 << '\n';
        // cout << "sum2 : " << sum2 << '\n';
        
        ++answer;
    }
    
    return answer;
}