#include <iostream>
#include <vector>

using namespace std;

class FenwickTree {
public:
    FenwickTree(int n) {
        tree.resize(n + 1);
    }

    void update(long long int i, long long int delta) {
        while (i < tree.size()) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    long long int query(long long int i) {
        long long int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= i & -i;
        }
        return res;
    }

    long long int query_range(long long int i, long long int j) {
        return query(j) - query(i - 1);
    }

private:
    vector<long long int> tree;
};
vector<long long int> arr;
vector<pair<long long int, pair<long long int, long long int>>> problem;
int main() {
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int N, M, K;
    cin >> N >> M >> K;

    for (int i = 0; i < N; i++) {
        long long int t;
        cin >> t;
        arr.push_back(t);
    }

    int T = M + K;
    while (T--) {
        long long int a, b, c;
        cin >> a >> b >> c;
        problem.push_back(make_pair(a, make_pair(b, c)));
    }
    FenwickTree ft(N);

    // √ ±‚»≠
    for (int i = 1; i <= N; i++) {
        ft.update(i, arr[i - 1]);
    }
    for (int i = 0; i < problem.size(); i++) {
        long long int a = problem[i].first;
        if (a == 1) {
            long long int b = problem[i].second.first;
            long long int c = problem[i].second.second;
            ft.update(b, c - arr[b - 1]);
            arr[b - 1] = c;
        }
        else {
            long long int b = problem[i].second.first;
            long long int c = problem[i].second.second;
            cout << ft.query_range(b, c) << endl;
        }
    }
    return 0;
}