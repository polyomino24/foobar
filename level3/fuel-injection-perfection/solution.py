def solution(n):
    n = int(n)
    ans = 0
    while n != 1:
        if n % 2 == 0:
            ans += 1
            n >>= 1
        else:
            ans += 2
            n = (n + (1 if n % 4 == 3 and n != 3 else -1)) >> 1
    return ans
