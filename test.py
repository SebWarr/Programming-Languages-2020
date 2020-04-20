class A(object):
    x:int = 1

class B(A):
    def __init__(self: "B"):
        pass

class C(B):
    z:bool = True

    def set_A(self: "C", val: int) -> object:
        self.x = val

a:A = None
b:B = None
c:C = None

a = A()
a = B()
b = a = c = C()
c = None

def set_x() -> int:
    global x
    x = 1
    return x

x:int = 0

set_x()