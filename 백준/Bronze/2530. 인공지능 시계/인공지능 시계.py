a, b, c = map(int, input().split())
d = int(input())

original = a*60*60 + b*60 + c
added = original + d

hour = added // 3600
min = added % 3600 // 60
sec = added % 3600 % 60

hour %= 24

print(hour, min, sec)