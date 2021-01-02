
#Solves the problem by putting blocks of size (1,1,1) wherever needed. 
#Should give the worst possible solution
def solve(tc):
    #3D map of 0/1 if we have to fill or not
    map = [[[0 for _ in range(tc.dimz)] for _ in range(tc.dimy)] for _ in range(tc.dimx)]
    output = []
    for px in range(tc.dimx):
        for py in range(tc.dimy):
            for pz in range(tc.dimz):
                if tc.hmap[py][px]>pz:
                    map[px][py][pz]=1
    
    # print_map(map)
    for px in range(tc.dimx):
        for py in range(tc.dimy):
            for pz in range(tc.dimz):
                if map[px][py][pz] == 1:
                    output.append(((px,py,pz), (1,1,1)))
    
    return output

def print_map(map):
    h = len(map[0][0])
    
    for layer in range(h):
        for y in range(len(map[0])):
            for x in range(len(map)):
                print("#" if map[x][y][layer]==1 else ".", end="")
            print()
        print()
    print()