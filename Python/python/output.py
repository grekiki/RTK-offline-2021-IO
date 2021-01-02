import input
code = "THIS SHOULD BE THE CODE YOU GOT AT REGISTRATION"

# blocks is a list of tuples of position and size
def print_output(tc, blocks) -> None:
    #Build a grid
    grid = build(tc.dimx, tc.dimy, tc.dimz, blocks)
    if not check(grid, tc, blocks):
        print("Solution is not correct")
        return None
    if not is_better(blocks, tc):
        return None
    with open("outputs\\output"+str(tc.id)+".txt", "w") as f:
        f.write(str(code))
        f.write("\n")
        f.write("Pokrajina")
        f.write("\n")
        f.write("\n")
        f.write(str(tc.id)+"\n")
        #Print a grid
        for h in range(tc.dimz):
            for py in range(tc.dimy):
                s=""
                for px in range(tc.dimx):
                    s+=str(grid[px][py][h])+" "
                f.write(s+"\n")

#Builds a 3d array that contains a block index in a positions the block is in, and 0 elsewhere
def build(dx, dy, dz, blocks):
    grid=[[[0 for _ in range(dz)] for _ in range(dy)] for _ in range(dx)]
    p=1
    for block in blocks:
        position = block[0]
        size = block[1]
        for dx in range(size[0]):
            for dy in range(size[1]):
                for dz in range(size[2]):
                    x = position[0]+dx
                    y = position[1]+dy
                    z = position[2]+dz
                    grid[x][y][z] = p
        p+=1

    return grid

#Check if the solution is better than the one we already have
def is_better(blocks, tc):
    id = tc.id
    length = len(blocks)
    p = 1
    s = ""
    changed = False
    for l in open("scores.txt"):
        if p==id:
            if length < int(l.strip()):
                s += str(length)+"\n"
                changed = True
            else:
                print("For case", tc.id, "we have solution of length", l.strip(), "which is not worse than", length)
                return False
        if changed:
            changed=False
        else:
            s+=l
        p+=1
    with open("scores.txt", "w") as f:
        f.write(s)
    return True

def check(grid, tc, blocks):
    #3D map of 0/1 if we have to fill or not
    map = [[[0 for _ in range(tc.dimz)] for _ in range(tc.dimy)] for _ in range(tc.dimx)]
    for px in range(tc.dimx):
        for py in range(tc.dimy):
            for pz in range(tc.dimz):
                if tc.hmap[py][px]>pz:
                    map[px][py][pz]=1

    #First ensure we fill exactly the heightmap
    for px in range(tc.dimx):
        for py in range(tc.dimy):
            for pz in range(tc.dimz):
                if map[px][py][pz]==0 and grid[px][py][pz]!=0:
                    print("There are blocks at position", px, py, pz)
                    return False
                elif map[px][py][pz]!=0 and grid[px][py][pz]==0:
                    print("There are missing blocks at position", px, py, pz)
                    return False
    #Check for block dimensions
    for block_pos in blocks:
        ok = False
        block = block_pos[1]
        for valid_block in tc.blocks:
            if block==valid_block or block == (valid_block[1], valid_block[0], valid_block[2]):
                ok=True
                break
        if not ok:
            print("Used invalid block size")
            return False
    return True
                