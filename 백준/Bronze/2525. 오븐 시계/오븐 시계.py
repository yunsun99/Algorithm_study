a, b = map(int, input().split())
c = int(input())

val, mod = divmod(c, 60)

if b + mod >= 60:
    if a+val+1 >= 24:
        print(a+val+1-24, b+mod-60)
    else:
        print(a+val+1, b+mod-60)
else:
    if a+val >= 24:
        print(a+val-24, b+mod)
    else:
        print(a+val, b+mod)