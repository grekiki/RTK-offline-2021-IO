import input
import output
import solver_basic

if __name__ == "__main__":
    case = 1
    tc = input.parse_case(case)
    solution = solver_basic.solve(tc)
    output.print_output(tc, solution)

