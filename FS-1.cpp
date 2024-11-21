// Imagine you're a lead developer working on a highly sensitive code analysis tool
// for a large software development company. One of the core responsibilities of 
// this tool is to ensure that code written by the developers is syntactically 
// correct before it gets deployed.

// One of the most common errors you need to catch involves mismatched or 
// incorrectly nested comments in the code, where developers use /* */, //, or 
// block comments using {}. These comments must always be properly closed, and 
// block comments cannot overlap in an incorrect order.

// Your task is to write a validation function that checks whether the comments 
// in the code are properly opened and closed.

// The rules for valid comments are:
//  - A comment started by /* must be closed with */.
//  - A block comment opened by { must be closed with }.
//  - If a comment or block is opened, it must be properly closed before another 
//    of the same type is opened (no overlapping).

// Given a string s that represents a piece of code containing only comment 
// delimiters /* */, //, and { }, determine if the string has valid comments.
// Print true if all the comments and block statements in the code are valid, 
// and false otherwise.

// Input Format:
// -------------
// A string S, containing only the characters representing comments and blocks: 
//             /*, */, {, }, and any additional surrounding code.

// Output Format:
// --------------
// Boolean value.


// Sample Input-1:
// ---------------
// { /* comment */ // Single line comment }

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// { /* Start of comment block } */

// Sample Output-2:
// ----------------
// false

#include <bits/stdc++.h>
using namespace std;

int main(){
    string s;
    getline(cin,s);
    int n = s.length();
    stack<string> st;
    int i=0;
    while(i<n){
        if(i<n-1 && s[i]=='/' && s[i+1]=='/'){
            i+=2;
            while(i<n-1 && s[i]!='\n'){
                i++;
            }
        }
        else if(i<n-1 && s[i]=='/' && s[i+1]=='*'){
            st.push("/*");
            i+=2;
        }
        else if(i<n-1 && s[i]=='*' && s[i+1]=='/'){
            if(!st.empty() && st.top()=="/*"){
                st.pop();
            }
            else{
                cout<<"false";
                return 0;
            }
            i+=2;
        }
        else if(s[i]=='/'){
            cout<<"false";
            return 0;
        }
        else if(s[i]=='{'){
            st.push("{");
            i+=1;
        }
        else if(s[i]=='}'){
            if(!st.empty() && st.top()=="{"){
                st.pop();
            }
            else{
                cout<<"false";
                return 0;
            }
            i+=1;
        }
        else{
            i+=1;
        }
    }
    if(st.empty()){
        cout<<"true";
    }
    else{
        cout<<"false";
    }
}




// import java.util.*;
// public class test{
//     public static boolean isValid(String s){
//         Stack<Character> st = new Stack<>();
//         for(int i=0;i<s.length();i++){
//             char ch = s.charAt(i);
//             if(ch=='{'){
//                 st.push(ch);
//             }else if(ch=='/' && i<s.length()-1 && s.charAt(i+1)=='*'){
//                 st.push('@');
//                 i++;
//             }else if(ch=='}'){
//                 if(st.peek()!='{'){
//                     return false;
//                 }
//                 st.pop();
//             }else if(ch=='*' && i<s.length()-1 && s.charAt(i+1)=='/'){
//                 if(st.peek()!='@'){
//                     return false;
//                 }
//                 st.pop();
//                 i++;
//             }
//             }
//             return st.empty();
//     }
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         String s = sc.nextLine();
//         System.out.println(isValid(s));

//     }
// }
