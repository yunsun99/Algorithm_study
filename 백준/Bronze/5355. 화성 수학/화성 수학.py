T = int(input())

for i in range(T):
    toks = input().split()
    ans = float(toks[0])
    
    for tok in toks[1:]:
        if tok == "@":
            ans *= 3
        if tok == "%":
            ans += 5
        if tok == "#":
            ans -= 7

    print(f"{ans:.2f}")