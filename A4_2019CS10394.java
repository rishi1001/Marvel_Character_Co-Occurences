import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;


class cc{
    ArrayList<String> com=new ArrayList<>();
    int tot;
    public cc(){
        com=new ArrayList<>();
        tot=0;
    }
}
public class A4_2019CS10394 {

    static int n,m;
    static void average(){
        float average=(float)(2*m)/n;
        String avg_string=String.format("%.2f", average);
        System.out.println(avg_string);
    }

    static void getr(ArrayList<String> tosort,int low,int high,int[] tweight){
        int var=(int)Math.round(low+(high-low)*Math.random());
        int temp=tweight[high];
        tweight[high]=tweight[var];
        tweight[var]=temp;
        String temps=tosort.get(var);
        tosort.set(var, tosort.get(high));
        tosort.set(high, temps);
    }

    static void quicksort(ArrayList<String> tosort,int low,int high,int[] tweight){
        if (low<high){
            int part=partition(tosort,low,high,tweight);
            quicksort(tosort, low, part-1,tweight);
            quicksort(tosort, part+1, high,tweight);
        }
    }

    static int partition(ArrayList<String> tosort,int low,int high,int[] tweight){
        getr(tosort, low, high, tweight);
        int pivot=tweight[high];
        String pivots=tosort.get(high);
        int i=low-1;
        for(int j=low;j<high;j++){
            if (tweight[j]>pivot){
                i++;
                int temp=tweight[i];                        
                tweight[i]=tweight[j];
                tweight[j]=temp;
                String ts=tosort.get(i);                         
                tosort.set(i, tosort.get(j));
                tosort.set(j, ts);
                
            }else if (tweight[j]==pivot){
                if (tosort.get(j).compareTo(pivots)>0){                
                    i++;
                    int temp=tweight[i];                        
                    tweight[i]=tweight[j];
                    tweight[j]=temp;
                    String ts=tosort.get(i);                          
                    tosort.set(i, tosort.get(j));
                    tosort.set(j, ts);
                }
            }
        }
        int temp=tweight[i+1];  
        tweight[i+1]=pivot;
        tweight[high]=temp;                      
        String ts=tosort.get(i+1);                          
        tosort.set(i+1, pivots);
        tosort.set(high, ts);
        return i+1;
    }

    static void getr1(ArrayList<String> tosort,int low,int high){
        int var=(int)Math.round(low+(high-low)*Math.random());
        String temps=tosort.get(var);
        tosort.set(var, tosort.get(high));
        tosort.set(high, temps);
    }


    static void sort1(ArrayList<String> tosort,int low,int high){
        if (low<high){
            int part=part1(tosort,low,high);
            sort1(tosort, low, part-1);
            sort1(tosort, part+1, high);
        }
    }

    static int part1(ArrayList<String> tosort,int low,int high){
        getr1(tosort, low, high);
        String pivot=tosort.get(high);
        int i=low-1;
        for(int j=low;j<high;j++){
            if (tosort.get(j).compareTo(pivot)>0){
                i++;
                String ts=tosort.get(i);                         
                tosort.set(i, tosort.get(j));
                tosort.set(j, ts);
            }
        }
        String ts=tosort.get(i+1);                          
        tosort.set(i+1, pivot);
        tosort.set(high, ts);
        return i+1;
    }

    static void getr2(ArrayList<cc> tosort,int low,int high){
        int var=(int)Math.round(low+(high-low)*Math.random());
        cc temp=tosort.get(var);
        tosort.set(var, tosort.get(high));
        tosort.set(high, temp);
    }

    static void sort2(ArrayList<cc> tosort,int low,int high){
        if (low<high){
            int part=part2(tosort,low,high);
            sort2(tosort, low, part-1);
            sort2(tosort, part+1, high);
        }
    }

    static int part2(ArrayList<cc> tosort,int low,int high){
        getr2(tosort, low, high);
        int pivot=tosort.get(high).tot;
        String pivots=tosort.get(high).com.get(0);
        int i=low-1;
        for(int j=low;j<high;j++){
            if (tosort.get(j).tot>pivot){
                i++;
                cc ts=tosort.get(i);                         
                tosort.set(i, tosort.get(j));
                tosort.set(j, ts);
            }else if (tosort.get(j).tot==pivot){
                if (tosort.get(j).com.get(0).compareTo(pivots)>0){
                    i++;
                    cc ts=tosort.get(i);                         
                    tosort.set(i, tosort.get(j));
                    tosort.set(j, ts);
                }
            }
        }
        cc ts=tosort.get(i+1);                         
        tosort.set(i+1,tosort.get(high));
        tosort.set(high, ts);
        return i+1;
    }



    static void rank(int[] tweight,ArrayList<String> tosort){           // which is faster array or array list
        quicksort(tosort, 0, n-1, tweight);
        for(int i=0;i<n-1;i++){
            System.out.print(tosort.get(i)+",");
        }
        System.out.println(tosort.get(n-1));
    }

    static void dfs(int s,boolean vis[],int x,ArrayList<cc> a,LinkedList<Integer> adj[],ArrayList<String> vertices){
        a.get(x).com.add(vertices.get(s));
        a.get(x).tot++;
        vis[s]=true;
        Iterator<Integer> j = adj[s].listIterator();
        while (j.hasNext()){
            int v = j.next();
            if (!vis[v]){
                dfs(v,vis,x,a,adj,vertices);
            }    
        }

    }


    static void independent_dfs(LinkedList<Integer> adj[],ArrayList<String> vertices){
        boolean vis[]=new boolean[n];
        for(int i=0;i<n;i++){
            vis[i]=false;
        }
        ArrayList<cc> a=new ArrayList<>();
        int x=0;
        for(int i=0;i<n;i++){
            if (!vis[i]){
                a.add(new cc());
                dfs(i, vis, x, a, adj, vertices);
                x++;
            }
        }
        for(int i=0;i<x;i++){
            sort1(a.get(i).com, 0, a.get(i).com.size()-1);
        }
        sort2(a, 0, x-1);
        for(int i=0;i<x;i++){
            int s=a.get(i).com.size();
            for(int j=0;j<s-1;j++){
                System.out.print(a.get(i).com.get(j)+",");
            }
            System.out.println(a.get(i).com.get(s-1));
        }

    }



    @SuppressWarnings("unchecked") public static void main(String[] args) throws FileNotFoundException,IOException {
        // here take input from csv file
        String line="";
        if (args[2].equals("average")){
            BufferedReader br = new BufferedReader(new FileReader(args[0])); 
            int a=0,b=0;
            line=br.readLine();
            while ((line = br.readLine()) != null){
                a++;                
            }
            br = new BufferedReader(new FileReader(args[1])); 
            line=br.readLine();
            while ((line = br.readLine()) != null){
                b++;                
            }
            n=a;
            m=b;
            average();

        }else if (args[2].equals("rank")){
            HashMap<String, Integer> map = new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(args[0])); 
            line=br.readLine();
            int a=0,b=0;
            ArrayList<String> tosort=new ArrayList<>();
            while ((line = br.readLine()) != null){
                String[] in1=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (in1[1].charAt(0)=='"') in1[1]=in1[1].substring(1,in1[1].length()-1); 
                map.put(in1[1], a);
                tosort.add(in1[1]);
                a++;                
            }
            int[] tweight=new int[a]; 
            br = new BufferedReader(new FileReader(args[1])); 
            line=br.readLine();
            while ((line = br.readLine()) != null){
                String[] in2=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (in2[0].charAt(0)=='"') in2[0]=in2[0].substring(1,in2[0].length()-1);
                if (in2[1].charAt(0)=='"') in2[1]=in2[1].substring(1,in2[1].length()-1);
                int x=map.get(in2[0]);
                tweight[x]+=Integer.parseInt(in2[2]); 
                x=map.get(in2[1]);
                tweight[x]+=Integer.parseInt(in2[2]);               
                b++;                
            }
            n=a;
            m=b;
            rank(tweight, tosort);
        }else if (args[2].equals("independent_storylines_dfs")){
            HashMap<String, Integer> map = new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(args[0])); 
            line=br.readLine();
            int a=0,b=0;
            ArrayList<String> vertices=new ArrayList<>();
            while ((line = br.readLine()) != null){
                String[] in1=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (in1[1].charAt(0)=='"') in1[1]=in1[1].substring(1,in1[1].length()-1);
                map.put(in1[1], a);
                vertices.add(in1[1]);
                a++;                
            }
            LinkedList<Integer> adj[]=new LinkedList[a];
            for(int i=0;i<a;i++) adj[i]=new LinkedList<>();
            br = new BufferedReader(new FileReader(args[1])); 
            line=br.readLine();
            while ((line = br.readLine()) != null){
                String[] in2=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (in2[0].charAt(0)=='"') in2[0]=in2[0].substring(1,in2[0].length()-1);
                if (in2[1].charAt(0)=='"') in2[1]=in2[1].substring(1,in2[1].length()-1);
                int x=map.get(in2[0]);
                int y=map.get(in2[1]);
                adj[x].add(y);
                adj[y].add(x);
                b++;                
            }
            n=a;
            m=b;
            independent_dfs(adj, vertices);
        }
    }
    
}
