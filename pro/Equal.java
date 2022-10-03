package pro;
import java.util.Scanner;
public class Equal{
    private static int pow(int e,int q){
        int res=1;
        for(int i=0;i<q;i++){
            res*=e;
        }
        return res;
    }
    
    private static int equalizer(int[]ch,int[]cols){
        int[][][]tree=new int[100][][];//100 olan hisseni muveqqeti yazmisam,deepth ucun arraylist daha serfelidi
        String[][]path=new String[100][];//100 olan hisseni muveqqeti yazmisam,deepth ucun arraylist daha serfelidi
        tree[0]=new int[1][cols.length];
        path[0]=new String[1];
        path[0][0]="";
        int target=0,cntr=0,say=0;
        boolean cond=false;
        int cx=0,cy=0;
        for(int i=0;i<cols.length;i++){
            tree[0][0][i]=cols[i];
        }
        tree[1]=new int[cols.length*ch.length][cols.length];
        path[1]=new String[cols.length*ch.length];
        
        for(int i=1;i<100;i++){//100 olan hisseni muveqqeti yazmisam,deepth ucun arraylist daha serfelidi
            target=0;
            tree[i]=new int[pow(ch.length*cols.length,i)][cols.length];
            path[i]=new String[pow(ch.length*cols.length, i)];
            cx=i;
            for(int j=0;j<pow(ch.length*cols.length, i);j++){
                cy=j;
                path[i][j]=path[i-1][j/(ch.length*cols.length)]+ch[j%ch.length];
                for(int k=0;k<cols.length;k++){
                    if(k!=target){
                        tree[i][j][k]=tree[i-1][j/(ch.length*cols.length)][k]+ch[j%ch.length];
                    }
                    else{
                        tree[i][j][k]=tree[i-1][j/(ch.length*cols.length)][k];
                    }
                }
                for(int x=0;x<cols.length-1;x++){
                    System.out.print(tree[i][j][x]+",");
                    if(tree[i][j][x]==tree[i][j][x+1]){
                        cntr++;
                    }
                }
                System.out.print(tree[i][j][cols.length-1]+"\n\n");
                if(cntr==cols.length-1){
                    cond=true;
                    break;
                }
                cntr=0;
                say++;
                if(say%(cols.length*ch.length)==0){
                    target=0;
                }
                if(say%ch.length==0){
                    target++;
                }
            }
            if(cond){
                break;
            }
        }
        return path[cx][cy].length();
    }
    public static void main(String[]args){
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter number of change amounts:");
        int n=inp.nextInt();
        int[]ch=new int[n];
        for(int i=0;i<n;i++){
            System.out.println(i+" th:");
            ch[i]=inp.nextInt();
        }
        System.out.println("Enter number of collegues:");
        n=inp.nextInt();
        int[]cols=new int[n];
        System.out.println("Enter number of chocolates on each collegue:");
        for(int i=0;i<n;i++){
            System.out.println(i+" th collegue:");
            cols[i]=inp.nextInt();
        }
        inp.close();
        System.out.println("Result: "+equalizer(ch, cols));
    }
}
