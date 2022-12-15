/*
prepare xyz (board coordinates) and ab (moves) for losing boards, winning boards, and AB arraylists
 */
public class Boards {
    int x;
    int y;
    int z;
    int e;
    int f;
    int mycols;
    int myrow;

    Boards(int x, int y, int z, int e, int f){
        this.x=x;
        this.y=y;
        this.z=z;
        this.e=e;
        this.f=f;

    }
    /*
    overload: so you can have a and b without always needing a and b
     */
    Boards(int x, int y, int z, int e, int f, int a, int b){
        this.x=x;
        this.y=y;
        this.z=z;
        this.e=e;
        this.f=f;
        myrow=b;
        mycols=a;
    }
    /*
   prints out all the optimal moves of all possible boards 
     */
    public void print(){
        System.out.println("MYBOARD ("+x+" , "+y+" , "+z+ " , "+e+" , "+f+") "+ "("+mycols+" , "+myrow+")");
    }






}


