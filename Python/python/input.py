from typing import List, Tuple, NamedTuple

TestCase = NamedTuple("TestCase", 
[('id', int), ('dimx', int), ('dimy', int), ('dimz', int), ('hmap', List[List[int]]), ('num_blocks', int), ('blocks', List[Tuple[int, int, int]])])

def parse_case(num: int) -> (int, int, int, List[List[int]], int, List[Tuple[int, int, int]]):
    with open("Python\\input.in") as f:
        if f.readline().strip() != "Pokrajina":
            print("Input file is not correct. The first line should be Pokrajina")
        cases = int(f.readline().strip())
        if num > cases or num < 0:
            print("No testcase with id ",num)
            return None
        f.readline()
        
        # We'll assume the file is correct
        for _ in range(num-1):
            parse_single_case(f)
        return parse_single_case(f)
    print("Issue opening file")
    return None

def parse_single_case(f):
    test_num = int(f.readline().strip())
    w, h = map(int, f.readline().strip().split())
    hmap = []
    M = 0
    for _ in range(h):
        hmap.append(list(map(int, f.readline().strip().split())))
        if len(hmap[-1]) != w:
            print("Length of heightmap line is", len(hmap[-1]), "but should be", w)
        M = max(M, max(hmap[-1]))

    num_rects = int(f.readline().strip())
    rects = []
    for _ in range(num_rects):
        rects.append(tuple(map(int, f.readline().strip().split())))
        if len(rects[-1]) != 3:
            print("Length of rects line is", len(rects[-1]), "but should be", 3)
    f.readline()
    return TestCase(test_num, w, h, M, hmap, num_rects, rects)