'''In the heart of a mystical jungle, an expedition of archaeologists has discovered an ancient temple that holds relics of untold power and mystery. These relics, sacred to the people who built the temple, are carefully stored in massive stone towers, with each tower holding stacks of artifacts. However, the most important relics, essential to the expedition's research, are buried under layers of other less significant objects. The temple's guardians, who left behind the instructions on how to safely retrieve the relics, forbid reckless handling of the treasures, meaning they can only be moved one at a time to avoid damage.

At the center of the temple is a giant, levitating crystal—an artifact that the team can use to move the relics between towers. The crystal, however, requires careful management, as it can only lift the top relic from a tower and place it on another.

The team is eager to start their work but realizes they don’t know which relics will end up on top of each tower after the complex rearrangement process. They’ve uncovered an ancient scroll that details the exact steps needed to rearrange the towers, but they cannot afford to interrupt the levitation crystal’s movements once the process begins. The scroll outlines the movements using a series of arrows, with number of dashes indicating the number of relics to be moved in each step. The team must carefully follow these steps to ensure that the desired relics are uncovered and ready to be retrieved.

Note:
- Empty towers are represented as containing '-' relic.
- You can have maximum of 10 towers

Input Format

First line: No. of towers N Next N lines: List of artifacts bottom to up for each tower
N+2 line: No. of instructions S Next S lines: List of instructions in the exact order

Constraints

1 <= towers <= 10
0 <= relics per tower <= 10
1 <= instructions <= 10

Output Format

A list of N artifacts on top of each tower.

Sample Input 0

3
Z N
M C D
P
4
2 -> 1
1 ---> 3
2 --> 1
1 -> 2
Sample Output 0

C M Z
Explanation 0

In this example, there are three towers of relics. Tower 1 contains two relics: relics Z is on the bottom, and relics N is on top. Tower 2 contains three relics; from bottom to top, they are crates M, C, and D. Finally, Tower 3 contains a single relic, P.

1 [Z][N]
2 [M][C][D]
3 [P]

In each instruction of the scroll, a quantity of relics to be moved from one tower to a different one is determined by the number of dashes in the instruction. In the first step of the above rearrangement procedure 2 -> 1, one relics is moved from tower 2 to tower 1, resulting in this configuration:

1 [Z][N][D]
2 [M][C]
3 [P]

In the second step 1 ---> 3, three relics are moved from tower 1 to tower 3. Relics are moved one at a time, so the first relic to be moved (D) ends up below the second and third relics:

1
2 [M][C]
3 [P][D][N][Z]

Then, both relics are moved from tower 2 to tower 1 (2 --> 1). Again, because relics are moved one at a time, relic C ends up below crate M:

1 [C][M]
2
3 [P][D][N][Z]

Finally, one relics is moved from tower 1 to tower 2 (1 -> 2):

1 [C]
2 [M]
3 [P][D][N][Z]

Thus at the end of the instruction scroll the top of the towers will have their top relics as

1 [C]
2 [M]
3 [Z]'''























n = int(input())

towers = []
for i in range (n):
    relics = input().split()
    towers.append(relics) 

n = int(input())

for i in range (n):
    l = input().strip()
    s = l.split(" ")
    u = int(s[0])-1
    v = int(s[2])-1
    
    
#     u=0
#     v=0
#     flag=0
#     for j in range (len(s)):
#         if(s[i].isdigit() and flag==0):
#             u = u*10 + int(s[i])
#         elif(s[i].isdigit() and flag==1):
#             v = v*10 + int(s[i])
#         elif(s[i]=='-'):
#             flag=1
        
    # u-=1
    # v-=1
    
#     if(u<0 or u>=n or v<0 or v>=n):
#         continue
    
    m = s[1].count("-")
    
    for j in range (m):
        if(towers[u]):
            r = towers[u].pop() 
            towers[v].append(r) 
            
for tower in towers:
    if (tower):
        # print(tower,end="\n")
        print(tower[-1],end=" ")
    else:
        print("-" ,end=" ")
        
    