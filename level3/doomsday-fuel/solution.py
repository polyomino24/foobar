from fractions import Fraction
from fractions import gcd

# A, B: same size matrix, return A - B
def sub(A, B):
    return [[A[i][j] - B[i][j] for j in range(len(A[0]))] for i in range(len(A))]


# A, B: matrix where (row size of A) == (column size of B), return A * B
def mul(A, B):
    return [[sum(i*j for i,j in zip(a_row,b_col)) for b_col in zip(*B)] for a_row in A]

# n: integer, return identity matrix of size n
def id(n):
    return [[1 if i == j else 0 for j in range(n)] for i in range(n)]

# A: regular matrix, return A^{-1}
def inv(A):
    n = len(A)
    B = id(n)
    for d in range(n):
        x = Fraction(1, A[d][d])
        for j in range(n):
            A[d][j] *= x
            B[d][j] *= x
        for i in range(n)[0:d] + range(n)[d + 1 :]:
            y = A[i][d]
            for j in range(n):
                A[i][j] = A[i][j] - y * A[d][j]
                B[i][j] = B[i][j] - y * B[d][j]
    return B

def solution(M):

    n = len(M)

    terminal = [True] * n

    for i in range(n):
        for x in M[i]:
            if x:
                terminal[i] = False

    if terminal.count(True) == 1:
        return [1, 1]
    

    # convert M to probability matrix
    for i in range(n):
        if terminal[i]:
            M[i][i] = 1
        s = sum(M[i])
        for j in range(n):
             M[i][j] = Fraction(M[i][j], s)

    A = [
        [M[i][j] for j in range(n) if not terminal[j]] for i in range(n) if not terminal[i]
    ]

    B = [
        [M[i][j] for j in range(n) if terminal[j]] for i in range(n) if not terminal[i]
    ]

    x = mul(inv(sub(id(len(A)), A)), B)[0]

    lcm = x[0].denominator

    for y in x:
        lcm = lcm / gcd(lcm, y.denominator) * y.denominator
    
    ans = [y.numerator * lcm / y.denominator for y in x]
    ans += [lcm]
    return ans

# print solution([[0, 1, 0, 0, 0, 1], [4, 0, 0, 3, 2, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]])