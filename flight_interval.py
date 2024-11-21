# given an array of heights print the maximum interval of heights where they are strictly increasing and then decreasing 

# input: 2 1 4 7 3 2 5 1
# output: 1 4 7 3 2 

# input: 10 3 5 7 2 9 11 6
# output: 3 5 7 2 

# ex 1 4 5 2 2 3 2 1
# output : 1 4 5 2
# if two max intervals exist return the first interval 

l = list(map(int,input().split()))
m = []
i=0
m=0
while i<len(l)-1:
    # start = i 
    while (i<len(l)-1) and l[i]>=l[i+1]:
        i+=1 
    start = i 
    while i<len(l)-1 and l[i]<l[i+1]:
        i+=1  
    peak = i 
    while i<len(l)-1 and l[i]>l[i+1]:
        i+=1  
    end = i 
    if end-start > m:
        m = end-start 
        # peaks = [start,end] 
        peak_start = start
# print(*(l[peaks[0]:peaks[1]+1]))
print(*(l[peak_start:peak_start+m+1]))