import java.io.*;
import java.util.*;
import java.awt.*;

import javax.swing.JFrame;
public class Prueba {
    public static void main(String[] args) {
    	lista estatico = new lista();
        Expresiones listexp = new Expresiones();
        LeerSML(estatico,listexp);
    	validacion a = new validacion();
    	//System.out.println(a.tipolista("val x = [[[[4,2],[5,6]],[[4,2],[5,6]]],[[[4,2],[5,6]],[[4,2],[5,6]]]]"));
    	//a.definirtipo("val x = [[[[true,false],[5,6]],[[4,2],[5,6]]],[[[4,2],[5,6]],[[4,2],[5,6]]]]");
    	//estatico = a.definirtipo("val x = (1,2,())");
    	//estatico.Imprimir();
    	//System.out.println(a.tipotupla("val x = (1,2,(1))"));
    }
     
    public static int estaEnString(String a, String b){
        int respuesta;
        int indice;
        respuesta = a.indexOf(b);
        if(respuesta==-1)
            return respuesta;
        else{
            indice = respuesta;
            return indice+2;
        }
    }
     
    public static void LeerSML(lista estatico, Expresiones xps){
        try{
        	//lista estatico = new lista();
        	validacion a = new validacion();
            int indice;
            int linea = 0;
            String temp = "";
            String codigo = "";
            filechooser nueva = new filechooser();
            FileReader fr = new FileReader(nueva.nombreArchivo);
            BufferedReader buffer = new BufferedReader(fr);
            String strlinea = buffer.readLine();
            StringTokenizer first = new StringTokenizer(strlinea," ;");
            while(first.hasMoreTokens()){
            	temp+=first.nextToken()+" ";
            }
            codigo += strlinea+"\n";
        	linea++;
        	if((indice=estaEnString(strlinea,"val"))!=-1){
        		//estatico 
        		//a.definirtipo(strlinea);
        		//System.out.println("si esta la palabra val en la linea: "+linea);
        		
        	}
        	else
        	    {
        		
        	    }
        	
            strlinea = buffer.readLine();
            while(strlinea != null){
            	if(strlinea.equals("")==false){
            		StringTokenizer second = new StringTokenizer (strlinea," ;");
            		while(second.hasMoreTokens()){
            			temp+=second.nextToken()+" ";
            		}
            		codigo += strlinea+"\n";
            		linea++;
            		if((indice=estaEnString(strlinea,"val"))!=-1){
            			//estatico = a.definirtipo(strlinea);
            			//System.out.print("La expresión"+strlinea+"es de tipo: ");
            			//a.definirtipo(strlinea);
            			//System.out.println("si esta la palabra val en la linea: "+linea);
            		}
            		strlinea = buffer.readLine();
            	}
            	else{
            		xps.InsertaFinal(temp);
            		temp="";
            		strlinea = buffer.readLine();
            	}
            if((strlinea == null)&(temp.equals("")==false))
               {xps.InsertaFinal(temp);
               }
            }
            //xps.InsertaFinal(temp);
            nodo aux = xps.PrimerNodo;
            while(aux!=null){
            	String exp = aux.exp;
            	String cadena = a.definirtipo(exp);
        		StringTokenizer tok = new StringTokenizer(cadena,"!");
        		String variable = tok.nextToken();
        		String tipo = tok.nextToken();
        		estatico.InsertaFinal(variable, tipo);
        		aux = aux.siguiente;
            	
            }
            Interfaz nueva1 = new Interfaz(codigo);
        	nueva1.setVisible(true);
        	nueva1.setLocationRelativeTo(null);
        	nueva1.setResizable(false);
        	nueva1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	nueva1.modificarestatico(estatico.obtenerdatos());
        	buffer.close();
        	estatico.Imprimir();
        	//System.out.println(estatico.PrimerNodo.nombre);
        	
        	System.out.println();
        }
         
        catch (Exception e){
            System.err.println();
            //System.err.println("Error: " + e.getMessage());
        }
    }
    
    public static boolean terminaexpresion(String str){
    	if(str.equals("val"))
    		return true;
    	else if (str.equals("fun"))
    		return true;
    	else if (str.equals("int"))
    		return true;
    	else if (str.equals("boolean"))
    		return true;
    	else if (str.equals("list"))
    		return true;
    	else
    		return false;
    	
    }
}