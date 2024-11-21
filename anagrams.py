# 9
# 14 41 12314 13124 161 611 116 7 989

# 7:1
# 14:2
# 116:3
# 989:1
# 12314:2

# 5
# 9 11 43 328 832

# 9:1
# 11:1
# 43:1
# 328:2

n = int(input())
l = input().split()

d = {}
temp = {}
for i in l:
    li = []
    for j in i:
        li+=[j]
    li.sort()
    final = "".join(li)
    if(final in d):
        d[final]+=1 
    else:
        d[final]=1 
        
for i in l:
    li = []
    for j in i:
        li+=[j]
    li.sort()
    final = "".join(li)
    temp[i] = d[final]
    
# print(temp)
keys = []
for i in temp:
    keys+=[int(i)]
keys.sort()
# print(keys)

checkList = []
for i in keys:
    li = []
    for j in str(i):
        li+=[j]
    li.sort()
    final = "".join(li)
    if(final not in checkList):
        print(str(i)+":"+str(temp[str(i)]))
    checkList+=[final]
    
    