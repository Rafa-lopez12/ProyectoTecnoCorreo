/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter.analex.utils;

import java.util.ArrayList;

/**
 *
 * @author Rafa
 */
public class TSParams {
    private static String TITLE = "TSParams";
    private ArrayList<String> L;
    
    public TSParams(){
        L= new ArrayList<String>();
    }
    
    
    public void init(){
        L.clear();
    }
    
    public int lenght(){
        return L.size();
    }
    
    public int Existe(String Str){
        for (int i = 0; i <= lenght() -1 ; i++) {
            if (L.get(i).equals(Str)) {
                return i;
            }
        }
        return -1;
    }
    
    
    public int add(String Str){
        int pos = Existe(Str);
        if (pos !=-1) {
            return pos;
        }
        if (Str.isEmpty()) {
            return -1;
        }
        L.add(Str);
        return L.size()-1;
    }
    
    public String getStr(int Index){
        if (!PosValida(Index)) {
            System.err.println("TSS.getStr:Posicion invalida.");
            return "";
        }
        return L.get(Index);
    }
    
    public boolean PosValida(int Index){
        return (0 <= Index && Index <= lenght()-1);
    }

    @Override
    public String toString(){
            if (lenght()==0)
                return "("+TITLE+" Vacia)";

            final char   LF ='\n';
            final String PADDLEFT = "    ";

            String Result;
            int n = LongitudFila();

            String BordeSup  = PADDLEFT+' '+Utils.RunLength(' ', n);
            String Titulo    = PADDLEFT+'|'+Utils.FieldCenter(TITLE, n)+'|';
            String BordeInf  = PADDLEFT+'+'+Utils.RunLength('-', n)+'+';

            Result = BordeSup + LF + Titulo + LF + BordeInf + LF;

            int FieldPos = PADDLEFT.length();
            for (int i=0; i<-lenght()-1; i++){
                String Posicion = Utils.FieldRight(""+i, FieldPos);
                String Fila     = " ' " + Utils.FieldLeft("'"+L.get(i)+"'", n) + " '|'";
                Result += Posicion + Fila + LF;
            }

            return Result+BordeInf+LF;
    }
    
    private int LongitudFila(){ //Corrutina de print().
    int May=TITLE.length();
    for (int i=0; i<=lenght()-1; i++){
        int LonStr = L.get(i).length();
        if (LonStr > May)
            May = LonStr;
    }
    return May+2;  //+2 comillas
}
    
    
}
