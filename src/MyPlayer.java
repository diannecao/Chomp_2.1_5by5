import com.sun.jdi.connect.spi.TransportService;

import java.awt.*;
import java.util.ArrayList;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    ArrayList<Boards> losingBoard = new ArrayList<Boards>();

    ArrayList<Boards> winningBoard = new ArrayList<Boards>();
    ArrayList<Boards> AB = new ArrayList<Boards>();
    public int currentcols=0;
    public int currentrow=0;

    public MyPlayer() {
        columns = new int[5];
        MakeChomp();
    }
    public void MakeChomp() {
        boolean gameOver = false;
        losingBoard.add(new Boards(1,0,0,0,0));

        int cnt = 0;
        for(int i=0; i<=5; i++){
            for(int j=0; j<=i; j++){
                for (int k = 0; k<=j; k++) {
                    for(int l=0; l<=k; l++) {
                        for (int m = 0; m <= l; m++) {
                            if (i == 0 && j == 0 && k == 0 && l == 0) {
                                continue;
                            }
                            cnt++;
                            //System.out.println("Possible board Number " + cnt);
                            System.out.println(i + " " + j + " " + k+" "+l+" "+m);
                            Organizer(i, j, k, l, m);
                            AB.add(new Boards(i, j, k, l, m, currentcols, currentrow));

                        }
                    }
                }
            }
        }
        // System.out.println("allboards");
        for(int x=0;x<AB.size();x++){
            AB.get(x).print();
        }
    }
    /*
    reducer/organizer method does actions to the possible boards
    1) it organizes the boards into possibilities of the next step
    2) it finds the moves that you have to take to get to a next step board
    3) it runs through all the next step boards and assign their possible board as a losing/winning board
    4) after it finds a losing board it prints out the optimal move
     */
    public void Organizer(int I, int J, int K, int L, int M) {
        boolean hasLosingBoard=false;
        int i = I;
        int j = J;
        int k = K;
        int l = L;
        int m = M;

        for(int a=m; a>=0; a--){ //5th column m
            System.out.println("PB5:"+i+" "+j+" "+k+" "+l+" "+a);

            for(int p=0; p< losingBoard.size(); p++) {

                if (i == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && a == losingBoard.get(p).f) {
                    currentcols=4;
                    currentrow=a;
                    hasLosingBoard = true;
                    // System.out.println("5 is making the stupid mistake: "+currentcols + " " + currentrow);
                }
            }

        }
        m=M;
        for(int a=l; a>=0; a--){ //4th column l
            if(a<m){
                m = a;
                System.out.println("PB4:"+i+" "+j+" "+k+" "+a+" "+m);

                for(int p=0; p< losingBoard.size(); p++) {

                    if (i == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && a == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=3;
                        currentrow=a;
                        hasLosingBoard = true;
                        // System.out.println("4.1 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else {
            System.out.println("PB4:"+i+" "+j+" "+k+" "+a+" "+m);
            for (int p = 0; p < losingBoard.size(); p++) {
                if (i == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && a == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                    currentcols = 3;
                    currentrow = a;
                    hasLosingBoard = true;
                    //System.out.println("4.2 is making the stupid mistake: " + currentcols + " " + currentrow);

                }

                //}
                // System.out.println("PB2:"+i+" "+j+" "+k+" "+a+" "+m);
            }

        }
        m = M;
        l = L;

        for(int c=k; c>=0; c--){ // 3rd column k
            if(c<m) {
                m = c;
                System.out.println("PB3:"+i+" "+j+" "+c+" "+l+" "+m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (i == losingBoard.get(p).x && j == losingBoard.get(p).y && c == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols = 2;
                        currentrow = c;
                        hasLosingBoard = true;
                        //System.out.println("3.1 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else
            if(c<l){
                l = c;
                System.out.println("PB3:"+i+" "+j+" "+c+" "+l+" "+m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (i == losingBoard.get(p).x && j == losingBoard.get(p).y && c == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols = 2;
                        currentrow = c;
                        hasLosingBoard = true;
                        // System.out.println("3.2 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else {
            System.out.println("PB3:"+i+" "+j+" "+c+" "+l+" "+m);
            for (int p = 0; p < losingBoard.size(); p++) {
                if (i == losingBoard.get(p).x && j == losingBoard.get(p).y && c == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                    currentcols = 2;
                    currentrow = c;
                    hasLosingBoard = true;
                    // System.out.println("3.3 is making the stupid mistake: " + currentcols + " " + currentrow);
                }
                //}
            }
            l = L;
            m = M;
            k = K;
            // System.out.println("PB2:"+i+" "+j+" "+c+" "+l+" "+m);


        } // end of third column reduced boards


        for(int b=j; b>=0; b--){ // second column j
            if (b<m) {
                m = b;
                System.out.println("PB2a:"+i+" "+b+" "+k+" "+l+" "+ m);
                for(int p=0; p< losingBoard.size(); p++) {

                    if (i == losingBoard.get(p).x && b == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=1;
                        currentrow=b;
                        hasLosingBoard = true;
                        //System.out.println("2.1 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } if(b<l){
                l = b;
                System.out.println("PB2b:"+i+" "+b+" "+k+" "+l+" "+m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (i == losingBoard.get(p).x && b == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=1;
                        currentrow=b;
                        hasLosingBoard = true;
                        //System.out.println("2.2 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else
            if(b<k) {
                k=b;
                System.out.println("PB2c:"+i+" "+b+" "+k+" "+l+" "+m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (i == losingBoard.get(p).x && b == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=1;
                        currentrow=b;
                        hasLosingBoard = true;
                        // System.out.println("2.3 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            }//else {
            System.out.println("PB2d:" + i + " " + b + " " + k + " " + l + " " + m);
            for (int p = 0; p < losingBoard.size(); p++) {
                if (i == losingBoard.get(p).x && b == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                    currentcols = 1;
                    currentrow = b;
                    hasLosingBoard = true;
                    //System.out.println("2.4 is making the stupid mistake: " + currentcols + " " + currentrow);
                }
                //}
            }
        } // end of second column reduced boards
        l = L;
        m = M;
        k = K;
        j = J;

        for(int a=i; a>=1; a--){// first column i
            if(a<m){
                m = a;
                System.out.println("PBi1:" + a + " " + j + " " + k + " " + l + " " + m);
                for(int p=0; p< losingBoard.size(); p++) {

                    if (a == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=0;
                        currentrow=a;
                        hasLosingBoard = true;
                        //System.out.println("1.1 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }

            } //else
            if(a<l){
                l = a;
                System.out.println("PBai2:" + a + " " + j + " " + k + " " + a + " " + m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (a == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=0;
                        currentrow=a;
                        hasLosingBoard = true;
                        //System.out.println("1.2 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else
            if(a<k){
                k = a;
                System.out.println("PBi3:" + a + " " + j + " " + k + " " + l + " " + m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (a == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=0;
                        currentrow=a;
                        hasLosingBoard = true;
                        // System.out.println("1.3 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else
            if(a<j){
                j = a;
                System.out.println("PBi4:" + a + " " + j + " " + k + " " + l + " " + m);
                for(int p=0; p< losingBoard.size(); p++) {
                    if (a == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                        currentcols=0;
                        currentrow=a;
                        hasLosingBoard = true;
                        //System.out.println("1.4 is making the stupid mistake: "+currentcols + " " + currentrow);
                    }
                }
            } //else{
            System.out.println("PB2d:" + a + " " + j + " " + k + " " + l + " " + m);
            for(int p=0; p< losingBoard.size(); p++) {
                if (a == losingBoard.get(p).x && j == losingBoard.get(p).y && k == losingBoard.get(p).z && l == losingBoard.get(p).e && m == losingBoard.get(p).f) {
                    currentcols=0;
                    currentrow=a;
                    hasLosingBoard = true;
                    //System.out.println("1.5 is making the stupid mistake: "+currentcols + " " + currentrow);
                }
                //}
            }

        } // end of first column reduced boards
        l = L;
        m = M;
        k = K;
        j = J;
        i = I;

        if(hasLosingBoard==false){
//            for (Boards board : losingBoard) {
//                if(i== board.x && j== board.y && k== board.z){
//                    break;
//                }
//            }
            losingBoard.add(new Boards(i, j, k, l, m));
            System.out.println(i +", "+j+", "+k+", "+l +", "+m);
            //  System.out.println("This is a losing board!");
        } else {
            winningBoard.add(new Boards(i, j, k, l, m));
            // System.out.println("This is a winning board!");
        }
//        for (int a=0; a <= i; a++) {

//            for (int b =0; b <= j; b++) {
//                    for (int c=0; c<=k; c++) {
//                        if(i==1 && j==0 && k==0){
//                         //   System.out.println("losing board");
//                        }
//
//                     //   System.out.println(i + " " + j + " " + k);
//                }

        //}
//        for(int p=0; p<losingBoard.size();p++ ) {
//            System.out.println("Losing "+ losingBoard.get(p).x+ " "+losingBoard.get(p).y+" "+losingBoard.get(p).z);
//        }

//        for (Boards board : losingBoard) {
//            System.out.println("Losing " + board.x + " " + board.y + " " + board.z);
//        }

    }

    //add your code to return the row and the column of the chip you want to take.
    //you'll be returning a data type called Point which consists of two integers.
    public Point move(Chip[][] pBoard) {

        int count=0;

        columns = new int[5];

        for(int x=0; x<6; x++){
            for(int y=0; y<6;y++){
                if(pBoard[x][y].isAlive == true) {
                    columns[y]++;
                }
            }
        }
        int column = 0;
        int row = 0;
        row = 1;
        column = 1;
        for(int x=0;x<AB.size();x++) {
            //System.out.println("I am working");
//            for(int c=0; c<10;c++) {
//                System.out.print(columns[c] + ",");
//            }
            if (AB.get(x).x == columns[0] && AB.get(x).y == columns[1] && AB.get(x).z == columns[2] && AB.get(x).e == columns[3] && AB.get(x).f == columns[4]) {
                System.out.println("Optimal move: "+AB.get(x).mycols + " , " + AB.get(x).myrow);
                row = AB.get(x).myrow;
                column = AB.get(x).mycols;
            }

        }
        for(int c=0; c<3;c++){
            System.out.print(columns[c]+",");
            columns[c]=0;
        }


        gameBoard = pBoard;




        Point myMove = new Point(row, column);
        return myMove;
    }

}
