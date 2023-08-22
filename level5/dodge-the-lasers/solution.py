sqrt2 = 14142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727
sqrt2_minus1 = 4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727


def rec(n):
    if n <= 1:
        return n
    else:
        x = (sqrt2 * n) // (10 ** 100)
        y = (sqrt2_minus1 * n) // (10 ** 100)
        return x * (x + 1) // 2 - y * (y + 1) - rec(y)


def solution(s):
    return str(rec(long(s)))
