#include <iostream>
using namespace std;

int main()
{
    int testCase;
    cin >> testCase;
    for (int i = 0; i < testCase; i++) {
        string input;
        cin >> input;
        string output;
        output += input.at(0);
        output += input.at(input.size() - 1);
        cout << output << endl;
    }
}