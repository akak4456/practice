#include <iostream>
using namespace std;

int N;
int T[15 + 1];
int P[15 + 1];

int solve(int startDay) {
    if(startDay > N) return 0;
    int ret = solve(startDay + 1); // startDay 에 상담을 잡지 않는 경우
    int ret2 = 0; // startDay 에 상담을 잡는 경우
    if(startDay + T[startDay] - 1 <= N) {
        ret2 = solve(startDay+T[startDay]) + P[startDay];
    }
    if(ret > ret2) return ret;
    else return ret2;
}

int main()
{
    cin >> N;
    for(int i=1;i<=N;i++) {
        cin >> T[i] >> P[i];
    }
    /*
    경우의 수가 2^15 이므로 사실 DP 등 최적화를 하지 않아도 충분히 시간 내에 풀 수 있다.
    만약에 N이 30보다 같거나 크다면 DP 를 고려해야 할 수도 있다.
    */
    cout << solve(1) << endl;

    return 0;
}
