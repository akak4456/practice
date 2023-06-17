#include <iostream>
using namespace std;

int N;
int M;
int B;
int land[500][500];

int solve(int targetHeight) {
    // targetHeight 보다 큰 애들의 땅을 깎는 양
    int moreBlock = 0;
    for(int i=0;i<N;i++) {
        for(int j=0;j<M;j++) {
            if(land[i][j] > targetHeight) {
                moreBlock += land[i][j] - targetHeight;
            }
        }
    }
    // targetHeight 보다 작은 애들의 땅을 추가해야 하는 양
    int lessBlock = 0;
    for(int i=0;i<N;i++) {
        for(int j=0;j<M;j++) {
            if(land[i][j] < targetHeight) {
                lessBlock += targetHeight - land[i][j];
            }
        }
    }
    if(moreBlock + B < lessBlock) {
        return -1;
    }
    return moreBlock * 2 + lessBlock;
}
int main() {
    cin >> N >> M >> B;
    for(int i=0;i<N;i++) {
        for(int j=0;j<M;j++) {
            cin >> land[i][j];
        }
    }
    int minTime = 987654321;
    int minHeight = 256;
    for(int i=256;i>=0;i--) {
        int sol = solve(i);
        if(sol != -1 && sol < minTime) {
            minTime = sol;
            minHeight = i;
        }
    }
    cout << minTime << " " << minHeight << endl;
}