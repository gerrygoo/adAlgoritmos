package tree23; 

import java.util.Scanner;
        // dict.put(1, 1);
        // System.out.println(dict);
        // dict.put(2, 2);
        // System.out.println(dict);
        // dict.put(3, 3);
        // System.out.println(dict);
        // dict.put(4, 4);
        // System.out.println(dict);
        // dict.put(0, 0);
        // System.out.println(dict);
        // dict.put(5, 5);
        // System.out.println(dict);
        // dict.put(-1, -1);
        // System.out.println(dict);
        // dict.put(6, 6);
        // System.out.println(dict);
        // dict.put(9, 9);
        // System.out.println(dict);
        // dict.put(7, 7);
        // System.out.println(dict);
        // dict.put(1, 10);
        // System.out.println(dict);
        // dict.remove(1);
        // System.out.println(dict);
        // dict.remove(7);
        // System.out.println(dict);
        // dict.remove(4);
        // System.out.println(dict);
        // dict.remove(3);
        // System.out.println(dict);    
        
public class Main {
    private static Dictionary23 dict;
    private static Scanner s = new Scanner(System.in);
    private static String in;
    private static String k, v;
    private static boolean keyDouble, valueDouble;

    private static void init(){
        while(true){
            System.out.println("Tipo de llave: s <string> / n <numero>");
            System.out.print(">");
            k = s.nextLine();
            if(k.compareTo("s") == 0 || k.compareTo("n") == 0){
                break;
            }else{
                System.out.println("\nEntrada invalida\n");
            }
        }
        while(true){
            System.out.println("Tipo de valor: s <string> / n <numero>");
            System.out.print(">");
            v = s.nextLine();
            if(k.compareTo("s") == 0 || k.compareTo("n") == 0){
                break;
            }else{
                System.out.println("\nEntrada invalida\n");
            }
        }

        if(k.compareTo("s") == 0){
            if(v.compareTo("n") == 0){
                dict = new Dictionary23<String, Double>();
                keyDouble = false; valueDouble = true;
            }else{
                dict = new Dictionary23<String, String>();
                keyDouble = false; valueDouble = false;
            }
        }else{
            if(v.compareTo("n") == 0){
                dict = new Dictionary23<Double, Double>();
                keyDouble = true; valueDouble = true;
            }else{
                dict = new Dictionary23<Double, String>();
                keyDouble = true; valueDouble = false;
            }
        }
    }

    private static void process(String inn){
        if(inn.compareTo("get") == 0){
            System.out.print("key>");
            if(keyDouble){
                System.out.println("Value : " + dict.get(s.nextDouble()));
            }else{
                 System.out.println("Value : " + dict.get(s.nextLine()));
            }
        }else if(inn.compareTo("put") == 0){
            Comparable key, value;
            System.out.print("key>");
            if(keyDouble){
                key = s.nextDouble();
            }else{
                key = s.nextLine();
            }

            System.out.print("value>");
            if(valueDouble){
                value = s.nextDouble();
            }else{
                value = s.nextLine();
            }

            dict.put(key, value);
        }else if(inn.compareTo("remove") == 0){
            System.out.print("key>");
            if(keyDouble){
                dict.remove(s.nextDouble());
            }else{
                dict.remove(s.nextLine());
            }
        }else if(inn.compareTo("print") == 0){
            System.out.println(dict);
        }
    }

    public static void main(String[] args) {
        System.out.println("Proyecto de analisis y diseño de algoritmos: Diccionario con arbol 2-3 \n ");
        init();

        System.out.println("put / get / remove / print / -1 (para terminar)");
        
        while(true){
            System.out.print(">");
            in = s.nextLine();
            if (in.compareTo("-1") == 0) break;
            process(in);
        }
    }
    
    
}
