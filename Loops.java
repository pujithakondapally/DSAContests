import java.util.*;

class Loops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<String> li = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            li.add(sc.nextLine().trim());
        }
        helper(li);
    }

    private static void helper(List<String> li) {
        Stack<Integer> loops = new Stack<>();
        Stack<Integer> curr = new Stack<>();
        StringBuilder output = new StringBuilder();
        int i = 0;

        while (i < li.size()) {
            String c = li.get(i);
            if (c.startsWith("for")) {
                int times = Integer.parseInt(c.split(" ")[1]);
                loops.push(times);
                curr.push(0);
            } 
          	else if (c.equals("do")) {
            } 
          	else if (c.equals("done")) {
                int current = curr.pop() + 1;
                int max = loops.pop();
                if (current < max) {
                    loops.push(max);
                    curr.push(current);
                    i = loopStart(li, i);
                    continue;
                }
            } 
          	else if (c.startsWith("break")) {
                int breakAt = Integer.parseInt(c.split(" ")[1]);
                if (curr.peek() + 1 == breakAt) {
                    loops.pop();
                    curr.pop();
                    i = loopEnd(li, i);
                }
            } 
          	else if (c.startsWith("continue")) {
                int continueAt = Integer.parseInt(c.split(" ")[1]);
                if (curr.peek() + 1 == continueAt) {
                    int max = loops.peek();
                    int current = curr.pop() + 1;
                    if (current < max) {
                        curr.push(current);
                        i = loopStart(li, i);
                    }
                    continue;
                }
            } 
          	else if (c.startsWith("print")) {
                String message = c.substring(c.indexOf("\"") + 1, c.lastIndexOf("\""));
                output.append(message).append("\n");
            }
            i++;
        }
        System.out.print(output.toString());
    }

    private static int loopStart(List<String> li, int ci) {
        int nested = 0;
        for (int i = ci - 1; i >= 0; i--) {
            if (li.get(i).equals("done")) {
                nested++;
            } else if (li.get(i).equals("do")) {
                if (nested == 0) {
                    return i;
                }
                nested--;
            }
        }
        return 0;
    }

    private static int loopEnd(List<String> li, int ci) {
        int nested = 0;
        for (int i = ci + 1; i < li.size(); i++) {
            if (li.get(i).equals("do")) {
                nested++;
            } else if (li.get(i).equals("done")) {
                if (nested == 0) {
                    return i;
                }
                nested--;
            }
        }
        return li.size();
    }
}
