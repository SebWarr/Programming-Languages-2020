count: int = 0
n: int = 42
i: int = 0


def foo(s: str) -> int:
    return len(s)


# x**y
def exp(x: int, y: int) -> int:
    a: int = 0

    def f(i: int) -> int:
        nonlocal a

        def geta() -> int:
            return a

        if i <= 0:
            return geta()
        else:
            a = a * x
            return f(i - 1)

    a = 1
    return f(y)


class bar(object):
    p: bool = True

    def baz(self: "bar", xx: [int]) -> str:
        global count
        x: int = 0
        y: int = 1

        def qux(y: int) -> object:
            nonlocal x
            if x > y:
                x = -1

        for x in xx:
            self.p = x == 2

        qux(0)
        count = count + 1

        while x <= 0:
            if self.p:
                xx[0] = xx[1]
                self.p = not self.p
                x = x + 1
            elif foo("Long"[0]) == 1:
                self.p = self is None

        return "Nope"


print(bar().baz([1, 2]))

while i <= n:
    print(exp(2, i % 31))
    i = i + 1
