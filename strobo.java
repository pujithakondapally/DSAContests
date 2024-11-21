import java.util.*;
class Solution{
    static String numList[][] = {{"1","1"},{"6","9"},{"8","8"},{"9","6"}};
    public static List<String> stroboNumber(int n,int m){
        if(n==0){
            return new ArrayList<String>(Arrays.asList(""));
        }
        if(n==1){
            return new ArrayList<String>(Arrays.asList("0","1","8"));
        }
        List<String> stroboList = stroboNumber(n-2,m);
        List<String> result = new ArrayList<>();
        for(int i=0;i<stroboList.size();i++){
            String str = stroboList.get(i);
            if(n!=m){
                result.add("0" + str + "0");
            }
            for(int j=0;j<4;j++){
                result.add(numList[j][0] + str + numList[j][1]);
            }
        }
        return result;
    }
    public static void main(String[] args){
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        List<String> result = stroboNumber(n,n);
        Collections.sort(result);
        System.out.println(result);
    }
}