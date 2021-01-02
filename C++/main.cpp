#include <bits/stdc++.h>

using namespace std;

vector<vector<vector<int>>> grid;
vector<array<int, 3>> blocks;

int h, w, d;
//Fill the grid with rectangles with IDs from 1 onwards. 
void solve()
{
    int currSquare = 1;
    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < w; j++)
        {
            for (int k = 0; k < d; k++)
            {
                if (grid[i][j][k] == 1)
                {
                    grid[i][j][k] = currSquare;
                    currSquare++;
                }
            }
        }
    }

}


int main()
{
    ios::sync_with_stdio(0);
    ifstream fin;
    fin.open("pokrajina.in");
    fin.tie(0);

    ofstream fout;
    fout.open("pokrajina.out");
    fout << "THIS SHOULD THE CODE YOU GOT AT REGISTRATION" << "\n";
    fout << "Pokrajina" << "\n";

    string name;
    getline(fin, name);

    int testCases;
    fin >> testCases;

    for (int testCase = 1; testCase <= testCases; testCase++)
    {
        int tc;//Unused, but need to dump a line somewhere
        fin>>tc;

        cout << testCase << endl;
        fin >> w >> h;
        d = 0;
        vector<vector<int>> hmap(h, vector<int>(w));
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                fin >> hmap[i][j];
                d = max(d, hmap[i][j]);
            }
        }
        
        blocks.clear();
        grid.clear();
        grid.resize(h, vector<vector<int>>(w, vector<int>(d)));
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                for (int k = 0; k < hmap[i][j]; k++) grid[i][j][k] = 1;
            }
        }

        int numBLocks;
        fin >> numBLocks;
        for (int i = 0; i < numBLocks; i++)
        {
            int x, y, z;
            fin >> x >> y >> z;
            array<int, 3> ar1 = {x, y, z};
            array<int, 3> ar2 = {y, x, z};
            blocks.push_back(ar1);
            if (x != y) blocks.push_back(ar2);
        }

        solve();

        fout << "\n";
        fout << testCase << "\n";
        for (int k = 0; k < d; k++)
        {
            for (int i = 0; i < h; i++)
            {
                for (int j = 0; j < w; j++)
                {
                    fout << grid[i][j][k] << " ";
                }
                fout << "\n";
            }
        }       
    }

    fin.close();

    fout.close();
}
