#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <vector>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'
vector<int> arr1, arr2;//작은애, 큰애
vector<ll> sum1, sum2; //누적합 저장
int cnt1, cnt2; //처음 큐의 사이즈


int solution(vector<int> queue1, vector<int> queue2) {

    // 1. 세팅
    cnt1 = queue1.size(), cnt2 = queue2.size(); //처음 각 큐의 사이즈
    ll sumv1 = 0, sumv2 = 0; //처음 각 큐의 합
    for (int i = 0; i < queue1.size(); i++) sumv1 += queue1[i];
    for (int i = 0; i < queue2.size(); i++) sumv2 += queue2[i];

    //큰애에서 빼줘야 하는 값 need 구하기
    //그냥 sum2-평균값
    ll sum = sumv1 + sumv2;
    if (sum % 2 == 1) return -1; //합이 홀수라 처음부터 불가능한 케이스(이런테케 없었음..)
    ll half = sum /= 2;
    ll need = max(sumv1, sumv2) - half;
    // cout<<need<<endl;

    if (sumv1 == sumv2) return 0; //처음부터 정답인 케이스
    //arr1에 작은애, arr2에 큰애 들어가게
    if (sumv1 > sumv2) arr1 = queue2, arr2 = queue1;
    else arr1 = queue1, arr2 = queue2;

    //arr1은 기존arr1 + arr2 를 가질거임
    for (int i = 0; i < arr2.size(); i++) arr1.push_back(arr2[i]);

    //2. 누적합 구하기
    sum1.resize(arr1.size() + 1, 0);
    sum2.resize(arr2.size() + 1, 0);

    sum1[0] = 0;
    sum2[0] = 0;
    for (int i = 0; i < arr1.size(); i++) {
        sum1[i + 1] = sum1[i] + arr1[i];
    }
    for (int i = 0; i < arr2.size(); i++) {
        sum2[i + 1] = sum2[i] + arr2[i];
    }

    //3. 투포인터
    // sum2[start]-sum1[end]이 need보다 작으면 start++, 크면 end++해서
    // sum2[start]-sum1[end]=need읜 start, end를 구함
    int start = 0, end = 0;
    while (start < arr2.size()) {
        int cur = sum2[start] - sum1[end];
        if (end == start + cnt1 || need > cur) start++;
        else if (need == cur) break;
        else end++;
    }

    if (start >= arr2.size()) return -1;
    return start + end;
}

