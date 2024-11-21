import java.util.*;
class Solution{
    public static double calculatePower(double n,int p){
        if(p==0){
            return 1;
        }
        double temp = calculatePower(n,p/2);
        double result;
        if(p%2==0){
            result = temp*temp;
        }
        else{
            if(p>0){
                result = temp*temp*n;
            }
            else{
                result = (temp*temp)/(double)n;
            }
        }
        return result;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();
        int p = sc.nextInt();
        double answer = calculatePower(n,p);
        System.out.println(answer);
    }
}