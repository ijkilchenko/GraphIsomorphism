package com.gi.base;
/**
 * User: ijk
 * Date: 1/6/14
 */

//An object which holds only necessary information about a spanning tree.

public class AbstractTree {
    int n;
    public int height;
    public int[] width; //Number of nodes in the spanning tree at a level.
    int[] level; //Map the index of a node to its level in the spanning tree.

    public AbstractTree(){

    }

    public AbstractTree(int n){
        this.n= n;
        level= new int[n];
    }

    public void setLevel(int node, int l){
        level[node]= l;
    }

    public int getLevel(int i){
        return level[i];
    }

    public void setHeight(){
        int largest= 0;
        for (int i= 0; i < n; i++){
            if (level[i] > largest){
                largest= level[i];
            }
        }
        height= largest;
    }

    public void setWidths(){
        width= new int[height+1];
        width[0]= 1;
        for (int i= 0; i < n; i++){
            if (level[i] != 0){
                width[level[i]]++;
            }
        }
    }

}
