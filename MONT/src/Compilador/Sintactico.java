/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Compilador;

import javax.swing.JOptionPane;

/**
*
* @author fernando
*/
public class Sintactico 
{
  String[][] tablaTokens;
  int indiceGeneral=0;
  String resultadoGeneral="";
  public String procesarTokens(String tokens)
  {
    String[] arregloTokens=tokens.split("\n");
    
    tablaTokens=new String[arregloTokens.length-1][3];

    for(int i=0;i<arregloTokens.length-1;i++)
    {
      String[] tokenIndividual=arregloTokens[i].split(" ");

      tablaTokens[i][0]=tokenIndividual[0];
      tablaTokens[i][1]=tokenIndividual[1];
      tablaTokens[i][2]=tokenIndividual[2];
    }

    comprobarProgramaGeneral();
    return resultadoGeneral;
  }

  public void comprobarProgramaGeneral()
  {
    boolean analisisCorrecto=true;
    String lineaError="";
    indiceGeneral=0;

    if(comprobarNombrePrograma())
    {
      //JOptionPane.showMessageDialog(null,"El nombre del programa se declaro correctamente");
      resultadoGeneral+="El nombre del programa se declaro correctamente\n";
    }

    else
    {
      //JOptionPane.showMessageDialog(null,"El nombre del programa se declaro incorrectamente");
      resultadoGeneral+="El nombre del programa se declaro incorrectamente\n";
      return;
    }

    //ES NECESARIO QUE AL MENOS UNA DE LAS 3 DECLARACIONES SEA ESCRITA  (CONFIGURACION,VARIABLE,MANIOBRA.INICIAR)
    boolean intentoConfiguracion=detectoIntentoConfiguracion();


    // LA CONFIGURACION PUEDE O NO PUEDE ESTAR ASI QUE SOLO SI DETECTA QUE SE ESCRIBIO LA PALABRA RESERVADA "configuracion"
    //SI SE ESCRIBIO COMPRUEBA QUE LA SINTAXIS ESTE CORRECTA 
   
   //INTENTO CONFIGURACION REVISA EL INDICE 3 
    //SI HUBO INTENTO DE CONFIGURACION ENTRA EN BUSCA DE LA SINTAXIS AVANZANDO EN 
    //INDICE HASTA 8
    if(intentoConfiguracion)
    {
      boolean configuracionCorrecta=comprobarConfiguracion();

      if(configuracionCorrecta)
      {
       //JOptionPane.showMessageDialog(null,"La configuracion fue declarada correctamente");  
       resultadoGeneral+="La configuracion fue declarada correctamente\n";
      }

      else
      {
      //JOptionPane.showMessageDialog(null,"La configuracion fue declarada incorrectamente"); 
      resultadoGeneral+="La configuracion fue declarada incorrectamente\n";
      return;
      }  
    }

    //INTENTO VARIABLE REVISA EL INDICE 9 SI EXISTIO UNA CONFIGURACION PUES ES LO ESPERADO 
    //SI NO HUBO UNA CONFIGURACION REVISARA EN EL INDICE 3
    //SI HUBO INTENTO DE VARIABLE ENTRA EN BUSCA DE LA SINTAXIS AVANZANDO EN 
    //INDICE HASTA 11
    boolean intentoVariable=false;
    boolean intentoDefinir;
    boolean declaraVariableOAsignaValor=true;
    while(declaraVariableOAsignaValor==true)
    {
      if(intentoVariable=detectoIntentoVariable())
      {
        boolean declaracionVariableCorrecta=comprobarDefineVariable();
        if(declaracionVariableCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Una variable fue declarada correctamente");
           resultadoGeneral+="Una variable fue declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La variable fue declarada incorrectamente");  
          resultadoGeneral+="La variable fue declarada incorrectamente\n";
        return;
        }
        declaraVariableOAsignaValor=true;
      }
      
      else
      {
        declaraVariableOAsignaValor=false;
      }

      if(intentoDefinir=detectoIntentoDefinir())
      {
        boolean declaracionDefinicionCorrecta=comprobarAsignacion();
        if(declaracionDefinicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se asigno un valor a una variable");
          resultadoGeneral+="Se asigno un valor a una variable\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La asignacion de valor a una variable fue escrita incorrectamente");  
          resultadoGeneral+="La asignacion de valor a una variable fue escrita incorrectamente\n";
          return;
        }
         declaraVariableOAsignaValor=true;
      }
      
      else
      {
        declaraVariableOAsignaValor=false;
      }
    }
    
    //INTENTO MANIOBRA REVISA EL INDICE 12 SI EXISTIO UNA CONFIGURACION Y VARIABLE HASTA EL 18
    //SI SOLO HUBO CONFIGURACION Y NO VARIABLE REVISA EN EL INDICE 9   HASTA 15
    //SI SOLO HUBO VARIABLE Y NO CONFIGURACION REVISA EN EL INDICE 6 HASTA 12
    //SI NO HUBO UNA CONFIGURACION NI VARIABLE REVISARA EN EL INDICE 3 HASTA 9
    boolean intentoManiobra=detectoIntentoManiobra();
    if(intentoManiobra)
    {
      boolean declaracionManiobraCorrecta=comprobarDefineManiobra();
      if(declaracionManiobraCorrecta)
      {
        //JOptionPane.showMessageDialog(null,"La maniobra fue declarada correctamente");
        resultadoGeneral+="La maniobra fue declarada correctamente\n";
      }

      else
      {
        //JOptionPane.showMessageDialog(null,"La maniobra fue declarada incorrectamente");  
        resultadoGeneral+="La maniobra fue declarada incorrectamente\n";
        return;
      }
    }
    
    boolean declarandoFunciones=true;
    boolean intentoDeclaraFuncVacia=false;
    
    while(declarandoFunciones==true)
    {
       intentoDeclaraFuncVacia=detectoDeclaracionFuncionVacia();

      if(intentoDeclaraFuncVacia)
      {
        boolean declaracionFuncVaciaCorrecta=comprobarDeclaraFuncionVacia();
        if(declaracionFuncVaciaCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"La funcion vacia fue declarada correctamente");
          resultadoGeneral+="La funcion vacia fue declarada correctamente\n";
        }

        else
        {
          resultadoGeneral+="La funcion vacia fue declarada incorrectamente\n";
          //JOptionPane.showMessageDialog(null,"La funcion vacia fue declarada incorrectamente");  
        return;
        }
        declarandoFunciones=true;
      }

      else
      {
         declarandoFunciones=false;
      }
    }
   

    if(!intentoVariable&&!intentoConfiguracion&&!intentoManiobra)
    {
      resultadoGeneral+="Se esperaba la declaracion de la configuracion,de una variable o el inicio de la maniobra para poder continuar\n";
      //JOptionPane.showMessageDialog(null,"Se esperaba la declaracion de la configuracion,de una variable o el inicio de la maniobra para poder continuar");
      return;
    }
  }

  public boolean comprobarNombrePrograma()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    //tabla tokens debe tener al menos 3 espacios para trabajar esto para evitar errores de pila
    if(tablaTokens.length>=3)
    {
      //MONT
      if(!tablaTokens[indiceGeneral][1].equals("Mont"))
      {
        analisisCorrecto=false;
        lineaError=tablaTokens[indiceGeneral][2];
      }

      indiceGeneral++;

      //IDENTIFICADOR
      if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
      {
        analisisCorrecto=false;
        lineaError=tablaTokens[indiceGeneral][2];
      }
      indiceGeneral++;

      //PUNTO Y COMA
      if(!tablaTokens[indiceGeneral][0].equals("PUNTO_Y_COMA"))
      {
        analisisCorrecto=false;
        lineaError=tablaTokens[indiceGeneral][2];
      }
      indiceGeneral++;
    }

    else
    {
      return false;   
    }

    if(analisisCorrecto==true)
    {
      return true;
    }

    else
    {
      return false;
    }
  }

  public boolean detectoIntentoConfiguracion()
  {
    boolean analisisCorrecto=true;
    if(tablaTokens.length>=4)
    {
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("configuracion"))
        {
          analisisCorrecto=false;
        }
      }

      catch(ArrayIndexOutOfBoundsException ex)
      {
        return false;  
      }
    }

    else
    {
      return false;   
    }
    return analisisCorrecto;
  }

  public boolean comprobarConfiguracion()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    {
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("configuracion"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++;

        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++;

        if(!tablaTokens[indiceGeneral][1].equals("{"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++;

        //AQUI SE MANDARA A LLAMAR A OTRA FUNCION QUE COMPROBARA LA EXISTENCIA DE BLOQUES DE INSTRUCCIONES
        //LOS BLOQUES DE INSTRUCCIONES  ESTAN ALMACENADOS DENTRO DE CORCHETEES
        //CADA BLOQUE DE INSTRUCCION ESTA DELIMITADO POR, EXCEPTO EL ULTIMO BLOQUE DE INSTRUCCIONES
        /*
        INCORRECTA
        PRIMERO COMPROBAR QUE HAYA INTENTO DECLARACION DE BLOQUE '['
        EN DADO CASO COMPROBAR QUE HAYA EXPRESIONES CORRECTAS ESO ES OTRA FUNCION
        DESPUES DEBE COMPROBAR QUE EXISTA ']'
        DESPUES QUE EXISTA ',' 
        SI YA ENCONTRO ',' AHORA DEBE COMPROBAR QUE EN VERDAD SEA NECESARIA PARA ESO SIEMPRE TRATARA DE ENCONTRAR '});' EN SECUENCIA
        SI NO EXISTE '});' QUIERE DECIR QUE HAY OTRO BLOQUE DE CODIGO ASI QUE BUSCARA '['

        SI SI EXISTE LA DECLARACION DE LA CONFIGURACION ESTARA INCORRECTA
        */
         
        /*
        CORRECTA
        PRIMERO COMPROBAR QUE HAYA INTENTO DECLARACION DE BLOQUE '['
        EN DADO CASO COMPROBAR QUE HAYA EXPRESIONES CORRECTAS ESO ES OTRA FUNCION
        DESPUES DEBE COMPROBAR QUE EXISTA ']'
        DESPUES QUE EXISTA ',' 
        SI YA ENCONTRO ',' AHORA DEBE COMPROBAR QUE EN VERDAD SEA NECESARIA PARA ESO SIEMPRE TRATARA DE ENCONTRAR '['
        SI NO EXISTE '[' QUIERE DECIR LA CONFIGURACION ES INCORRECTA

        SI NO ENCONTRO ',' AHORA DEBE COMPROBAR QUE EN VERDAD SEA QUE YA NO HAY BLOQUES POR LO TANTO BUSCARA QUE EXISTA LA SECUENCIA '});'

        SI SI SE ENCUENTRA '});' LA DECLARACION DE BLOQUES ESTARA CORRECTA RETORNARA TRUE

        SI NO SE ENCUENTRA LA DECLARACION DE BLOQUES ESTARA INCORRECTA RETORNARA FALSE
        */
        boolean intentoBloque;
        boolean bloqueEncontrado=true;
        boolean declaraBloqueCorrecto=comprobarBloque();
        while(intentoBloque=detectoIntentoBloque())
        {
           if(declaraBloqueCorrecto=comprobarBloque())
            {
              //JOptionPane.showMessageDialog(null,"Un bloque de codigo fue declarado correctamente");
              resultadoGeneral+="Un bloque de codigo dentro de la configuracion fue declarado correctamente\n";
            }

            else
            {
              resultadoGeneral+="Un bloque de codigo dentro de la configuracion fue declarado incorrectamente\n";
              //JOptionPane.showMessageDialog(null,"Un bloque de codigo fue declarado incorrectamente"); 
              return false;
            }
         }
        
        //JOptionPane.showMessageDialog(null,"Esto es? } "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("}"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++;
       
        //JOptionPane.showMessageDialog(null,"Esto es? ) "+tablaTokens[indiceGeneral][1]);
       if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++;
        
        //JOptionPane.showMessageDialog(null,"Esto es? ; "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++;
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        indiceGeneral=3;
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
  }

  public boolean detectoIntentoVariable()
  {
    //JOptionPane.showMessageDialog(null,"INDICE "+indiceGeneral);
    boolean analisisCorrecto=true;
    if(tablaTokens.length>=4)
    {
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("entero")&&!tablaTokens[indiceGeneral][1].equals("flotante")&&!tablaTokens[indiceGeneral][1].equals("cadena")&&!tablaTokens[indiceGeneral][1].equals("booleano"))
        {
          analisisCorrecto=false;
        }
      }

      catch(ArrayIndexOutOfBoundsException ex)
      {
         return false;
      }
    }

    else
    {
      return false;   
    }
    return analisisCorrecto;
  }

  public boolean comprobarDefineVariable()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        //JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("entero")&&!tablaTokens[indiceGeneral][1].equals("flotante")&&!tablaTokens[indiceGeneral][1].equals("cadena")&&!tablaTokens[indiceGeneral][1].equals("booleano"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        //JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][0]);
        }
        indiceGeneral++; 

        //JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false;
      }
    }

    else
    {
      return false;
    }
    //JOptionPane.showMessageDialog(null,analisisCorrecto);
    return analisisCorrecto;
  }

   public boolean detectoIntentoDefinir()
  {
    //JOptionPane.showMessageDialog(null,"INDICE "+indiceGeneral);
    boolean analisisCorrecto=true;
    if(tablaTokens.length>=4)
    {
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("define"))
        {
          analisisCorrecto=false;
        }
      }

      catch(ArrayIndexOutOfBoundsException ex)
      {
         return false;
      }
    }

    else
    {
      return false;   
    }
    return analisisCorrecto;
  }

    public boolean comprobarAsignacion()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        //JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("define"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        //JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][0]);
        }
        indiceGeneral++; 

         if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][0]);
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals(","))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][0]);
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][0].equals("ENTERO")&&!tablaTokens[indiceGeneral][0].equals("CADENA")&&!tablaTokens[indiceGeneral][1].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][0]);
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][0]);
        }
        indiceGeneral++; 

        //JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"ERROR EN: "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false;
      }
    }

    else
    {
      return false;
    }
    //JOptionPane.showMessageDialog(null,analisisCorrecto);
    return analisisCorrecto;
  }

  public boolean detectoIntentoManiobra()
  {
    boolean analisisCorrecto=true;
    if(tablaTokens.length>=4)
    {
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("Maniobra"))
        {
          analisisCorrecto=false;
        }
      }

      catch(ArrayIndexOutOfBoundsException ex)
      {
        return false;  
      }
    }

    else
    {
      return false;   
    }
    return analisisCorrecto;
  }

  public boolean comprobarDefineManiobra()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("Maniobra"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("."))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("iniciar"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("{"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

boolean intentoCiclo=false;
boolean intentoCondicion=false;
boolean intentoContrario=false;
boolean intentoVariable=false;
boolean intentoDefinir=false;
boolean intentoFuncionPropia=false;
boolean intentoFuncionNativa=false;
boolean estructurasEnBloque=true;

while(estructurasEnBloque==true)
{
  if(intentoCondicion=detectoIntentoSi())
      {
        boolean condicionCorrecta=comprobarDefineSi();
        if(condicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada correctamente");
          resultadoGeneral+="una sentencia si ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada incorrectamente");  
          resultadoGeneral+="una sentencia si ha sido declarada incorrectamente\n";
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoContrario=detectoIntentoContrario())
      {
        boolean contrarioCorrecta=comprobarDefineContrario();
        if(contrarioCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada correctamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada correctamente\n";
        }

        else
        {
        //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada incorrectamente"); 
        resultadoGeneral+="una sentencia contrario ha sido declarada incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

       if(intentoCiclo=detectoIntentoCiclo())
      {
        boolean cicloCorrecto=comprobarDefineCiclo();
        if(cicloCorrecto)
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado correctamente");
          resultadoGeneral+="un ciclo ha sido declarado correctamente\n"; 
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado incorrectamente"); 
          resultadoGeneral+="un ciclo ha sido declarado incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoVariable=detectoIntentoVariable())
      {
        boolean declaracionVariableCorrecta=comprobarDefineVariable();
        if(declaracionVariableCorrecta)
        {
        //JOptionPane.showMessageDialog(null,"Una variable fue declarada correctamente");
         resultadoGeneral+="Una variable fue declarada correctamente\n";  
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La variable fue declarada incorrectamente"); 
          resultadoGeneral+="La variable fue declarada incorrectamente\n";
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoDefinir=detectoIntentoDefinir())
      {
        boolean declaracionDefinicionCorrecta=comprobarAsignacion();
        if(declaracionDefinicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se asigno un valor a una variable");
          resultadoGeneral+="Se asigno un valor a una variable\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La asignacion de valor a una variable fue escrita incorrectamente");  
          resultadoGeneral+="La asignacion de valor a una variable fue escrita incorrectamente\n";
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionPropia=detectoIntentoLlamadaFuncionPropia())
      {
        boolean llamadaFuncPropCorrecta=comprobarLlamadaFuncionPropia();
        if(llamadaFuncPropCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion definida por el usuario");
          resultadoGeneral+="Se llamo a una funcion definida por el usuario\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion definida por el usuario de manera incorrecta");  
          resultadoGeneral+="llamada a funcion definida por el usuario de manera incorrecta\n";
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionNativa=detectoIntentoLlamadaFuncionNativa())
      {
        boolean llamadaFuncNatCorrecta=comprobarLlamadaFuncionNativa();
        if(llamadaFuncNatCorrecta)
        {
          resultadoGeneral+="Se llamo a una funcion nativa\n";
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion nativa");
        }

        else
        {
          resultadoGeneral+="llamada a funcion nativa de manera incorrecta\n";
          //JOptionPane.showMessageDialog(null,"llamada a funcion nativa de manera incorrecta");  
          return false;
        }
         estructurasEnBloque=true;
      }
      
      if(!intentoCiclo&&!intentoCondicion&&!intentoCondicion&&!intentoVariable&&!intentoDefinir&&!intentoFuncionPropia&&!intentoFuncionNativa)
      {
      estructurasEnBloque=false;
      }
}

        if(!tablaTokens[indiceGeneral][1].equals("}"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }

    public boolean detectoIntentoBloque()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
          if(!tablaTokens[indiceGeneral][1].equals("["))
          {
            analisisCorrecto=false;
          }
          else
          {
            //JOptionPane.showMessageDialog(null,"Detecto intento de bloque");
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }

    public boolean comprobarBloque()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        //JOptionPane.showMessageDialog(null,"comienzo de comrobacion");
        //JOptionPane.showMessageDialog(null,"esto es [ ?"+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("["))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
boolean intentoCiclo=false;
boolean intentoCondicion=false;
boolean intentoContrario=false;
boolean estructurasEnBloque=true;

while(estructurasEnBloque==true)
{
  if(intentoCondicion=detectoIntentoSi())
      {
        boolean condicionCorrecta=comprobarDefineSi();
        if(condicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada correctamente");
          resultadoGeneral+="una sentencia si ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada incorrectamente"); 
          resultadoGeneral+="una sentencia si ha sido declarada incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoContrario=detectoIntentoContrario())
      {
        boolean contrarioCorrecta=comprobarDefineContrario();
        if(contrarioCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada correctamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada incorrectamente"); 
          resultadoGeneral+="una sentencia contrario ha sido declarada incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

       if(intentoCiclo=detectoIntentoCiclo())
      {
        boolean cicloCorrecto=comprobarDefineCiclo();
        if(cicloCorrecto)
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado correctamente");
          resultadoGeneral+="un ciclo ha sido declarado correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado incorrectamente"); 
          resultadoGeneral+="un ciclo ha sido declarado incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }
      
      if(!intentoCiclo&&!intentoCondicion&&!intentoCondicion)
      {
      estructurasEnBloque=false;
      }
}

         //JOptionPane.showMessageDialog(null,"esto es ] ?"+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("]"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        ////JOptionPane.showMessageDialog(null,"esto es , ?"+tablaTokens[indiceGeneral][1]);
        if(tablaTokens[indiceGeneral][1].equals(","))
        {
           ////JOptionPane.showMessageDialog(null,"se encontro una coma comprobando que haya otro bloque");
           ////JOptionPane.showMessageDialog(null,"esto es [ ?"+tablaTokens[indiceGeneral+1][1]);
           if(tablaTokens[indiceGeneral+1][1].equals("["))
           {
            ////JOptionPane.showMessageDialog(null,"se encontro otro bloque se vuelven a buscar bloques");
            indiceGeneral++;
            return true;
           }

           else
           {
            return false;
           }
        }

        else
        {
            //JOptionPane.showMessageDialog(null,"no hubo coma comprobando que haya fin de configuracion");
            //JOptionPane.showMessageDialog(null,"esto es } ?"+tablaTokens[indiceGeneral][1]);
            //JOptionPane.showMessageDialog(null,"esto es ) ?"+tablaTokens[indiceGeneral+1][1]);
            //JOptionPane.showMessageDialog(null,"esto es ; ?"+tablaTokens[indiceGeneral+2][1]);
           if(tablaTokens[indiceGeneral][1].equals("}")&&tablaTokens[indiceGeneral+1][1].equals(")")&&tablaTokens[indiceGeneral+2][1].equals(";"))
            {
              return true;
            }

            else
            {
              return false;
            }
        }
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }
  }

    public boolean detectoIntentoSi()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
          if(!tablaTokens[indiceGeneral][1].equals("si"))
          {
            analisisCorrecto=false;
          }
          else
          {
          //JOptionPane.showMessageDialog(null,"Detecto intento de condicion");
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }

    public boolean comprobarDefineSi()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("si"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR")||tablaTokens[indiceGeneral][0].equals("CADENA")||tablaTokens[indiceGeneral][0].equals("ENTERO")||tablaTokens[indiceGeneral][1].equals("verdadero")||tablaTokens[indiceGeneral][1].equals("falso"))
        {
         
        }

        else
        {
           analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(tablaTokens[indiceGeneral][1].equals("<")||tablaTokens[indiceGeneral][1].equals(">")||(tablaTokens[indiceGeneral][1].equals("=") && tablaTokens[indiceGeneral+1][1].equals("="))||(tablaTokens[indiceGeneral][1].equals(">") && tablaTokens[indiceGeneral+1][1].equals("="))||(tablaTokens[indiceGeneral][1].equals("<") && tablaTokens[indiceGeneral+1][1].equals("=")))
        {
         
        }

        else
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR")||tablaTokens[indiceGeneral][0].equals("CADENA")||tablaTokens[indiceGeneral][0].equals("ENTERO")||tablaTokens[indiceGeneral][1].equals("verdadero")||tablaTokens[indiceGeneral][1].equals("falso"))
        {
         
        }

        else
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
       

        if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("{"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        boolean intentoCiclo=false;
boolean intentoCondicion=false;
boolean intentoContrario=false;
boolean intentoVariable=false;
boolean intentoDefinir=false;
boolean intentoFuncionPropia=false;
boolean intentoFuncionNativa=false;
boolean estructurasEnBloque=true;

while(estructurasEnBloque==true)
{
  if(intentoCondicion=detectoIntentoSi())
      {
        boolean condicionCorrecta=comprobarDefineSi();
        if(condicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada correctamente");
          resultadoGeneral+="una sentencia si ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada incorrectamente");  
          resultadoGeneral+="una sentencia si ha sido declarada incorrectamente\n";
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoContrario=detectoIntentoContrario())
      {
        boolean contrarioCorrecta=comprobarDefineContrario();
        if(contrarioCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada correctamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada incorrectamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

       if(intentoCiclo=detectoIntentoCiclo())
      {
        boolean cicloCorrecto=comprobarDefineCiclo();
        if(cicloCorrecto)
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado correctamente");
          resultadoGeneral+="un ciclo ha sido declarado correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado incorrectamente"); 
          resultadoGeneral+="un ciclo ha sido declarado incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoVariable=detectoIntentoVariable())
      {
        boolean declaracionVariableCorrecta=comprobarDefineVariable();
        if(declaracionVariableCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Una variable fue declarada correctamente");
          resultadoGeneral+="Una variable fue declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La variable fue declarada incorrectamente");
          resultadoGeneral+="La variable fue declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoDefinir=detectoIntentoDefinir())
      {
        boolean declaracionDefinicionCorrecta=comprobarAsignacion();
        if(declaracionDefinicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se asigno un valor a una variable");
          resultadoGeneral+="Se asigno un valor a una variable\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La asignacion de valor a una variable fue escrita incorrectamente"); 
          resultadoGeneral+="La asignacion de valor a una variable fue escrita incorrectamente\n";
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionPropia=detectoIntentoLlamadaFuncionPropia())
      {
        boolean llamadaFuncPropCorrecta=comprobarLlamadaFuncionPropia();
        if(llamadaFuncPropCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion definida por el usuario");
          resultadoGeneral+="Se llamo a una funcion definida por el usuario\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion definida por el usuario de manera incorrecta");
          resultadoGeneral+="llamada a funcion definida por el usuario de manera incorrecta\n";  
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionNativa=detectoIntentoLlamadaFuncionNativa())
      {
        boolean llamadaFuncNatCorrecta=comprobarLlamadaFuncionNativa();
        if(llamadaFuncNatCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion nativa");
          resultadoGeneral+="Se llamo a una funcion nativa\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion nativa de manera incorrecta"); 
          resultadoGeneral+="llamada a funcion nativa de manera incorrecta\n"; 
          return false;
        }
         estructurasEnBloque=true;
      }
      
      if(!intentoCiclo&&!intentoCondicion&&!intentoCondicion&&!intentoVariable&&!intentoDefinir&&!intentoFuncionPropia&&!intentoFuncionNativa)
      {
      estructurasEnBloque=false;
      }
}


        if(!tablaTokens[indiceGeneral][1].equals("}"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }

    public boolean detectoIntentoContrario()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
          if(!tablaTokens[indiceGeneral][1].equals("contrario"))
          {
            analisisCorrecto=false;
          }
          else
          {
          //JOptionPane.showMessageDialog(null,"Detecto de sentencia contrario");
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }
    
public boolean comprobarDefineContrario()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        if(!tablaTokens[indiceGeneral][1].equals("contrario"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals("{"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        boolean intentoCiclo=false;
boolean intentoCondicion=false;
boolean intentoContrario=false;
boolean intentoVariable=false;
boolean intentoDefinir=false;
boolean intentoFuncionPropia=false;
boolean intentoFuncionNativa=false;
boolean estructurasEnBloque=true;

while(estructurasEnBloque==true)
{
  if(intentoCondicion=detectoIntentoSi())
      {
        boolean condicionCorrecta=comprobarDefineSi();
        if(condicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada correctamente");
          resultadoGeneral+="una sentencia si ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada incorrectamente");
          resultadoGeneral+="una sentencia si ha sido declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoContrario=detectoIntentoContrario())
      {
        boolean contrarioCorrecta=comprobarDefineContrario();
        if(contrarioCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada correctamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada incorrectamente"); 
          resultadoGeneral+="una sentencia contrario ha sido declarada incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

       if(intentoCiclo=detectoIntentoCiclo())
      {
        boolean cicloCorrecto=comprobarDefineCiclo();
        if(cicloCorrecto)
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado correctamente");
          resultadoGeneral+="un ciclo ha sido declarado correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado incorrectamente");
          resultadoGeneral+="un ciclo ha sido declarado incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoVariable=detectoIntentoVariable())
      {
        boolean declaracionVariableCorrecta=comprobarDefineVariable();
        if(declaracionVariableCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Una variable fue declarada correctamente");
          resultadoGeneral+="Una variable fue declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La variable fue declarada incorrectamente");
          resultadoGeneral+="La variable fue declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoDefinir=detectoIntentoDefinir())
      {
        boolean declaracionDefinicionCorrecta=comprobarAsignacion();
        if(declaracionDefinicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se asigno un valor a una variable");
          resultadoGeneral+="Se asigno un valor a una variable\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La asignacion de valor a una variable fue escrita incorrectamente");
          resultadoGeneral+="La asignacion de valor a una variable fue escrita incorrectamente\n";  
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionPropia=detectoIntentoLlamadaFuncionPropia())
      {
        boolean llamadaFuncPropCorrecta=comprobarLlamadaFuncionPropia();
        if(llamadaFuncPropCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion definida por el usuario");
          resultadoGeneral+="Se llamo a una funcion definida por el usuario\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion definida por el usuario de manera incorrecta");
          resultadoGeneral+="llamada a funcion definida por el usuario de manera incorrecta\n";  
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionNativa=detectoIntentoLlamadaFuncionNativa())
      {
        boolean llamadaFuncNatCorrecta=comprobarLlamadaFuncionNativa();
        if(llamadaFuncNatCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion nativa");
          resultadoGeneral+="Se llamo a una funcion nativa\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion nativa de manera incorrecta");
          resultadoGeneral+="llamada a funcion nativa de manera incorrecta\n";  
          return false;
        }
         estructurasEnBloque=true;
      }
      
      if(!intentoCiclo&&!intentoCondicion&&!intentoCondicion&&!intentoVariable&&!intentoDefinir&&!intentoFuncionPropia&&!intentoFuncionNativa)
      {
      estructurasEnBloque=false;
      }
}


        if(!tablaTokens[indiceGeneral][1].equals("}"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }
       
    public boolean detectoIntentoCiclo()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
          if(!tablaTokens[indiceGeneral][1].equals("ciclo"))
          {
            analisisCorrecto=false;
          }
          else
          {
          //JOptionPane.showMessageDialog(null,"Detecto de estructura ciclo");
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }
    
    public boolean comprobarDefineCiclo()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        // JOptionPane.showMessageDialog(null,"esta la palabra ciclo ? "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("ciclo"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
        
        // JOptionPane.showMessageDialog(null,"esta la palabra ( ? "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 
        
        // JOptionPane.showMessageDialog(null,"esta entero ? "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("entero"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        // JOptionPane.showMessageDialog(null,"esta IDENTIFICADOR ? "+tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,"esta = ? "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("="))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,"esta IDENTIFICADOR o ENTERO ? "+tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR")&&!tablaTokens[indiceGeneral][0].equals("ENTERO"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,"esta ; ? "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,"esta IDENTIFICADOR ? "+tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        // JOptionPane.showMessageDialog(null,"esta < o > ? "+tablaTokens[indiceGeneral][1]);
        if(tablaTokens[indiceGeneral][1].equals("<")||tablaTokens[indiceGeneral][1].equals(">"))
        {
        }

        else
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        //JOptionPane.showMessageDialog(null,"esta IDENTIFICADOR o ENTERO ? "+tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR")&&!tablaTokens[indiceGeneral][0].equals("ENTERO"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        //JOptionPane.showMessageDialog(null,"esta ; ? "+tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

         //JOptionPane.showMessageDialog(null,"esta IDENTIFICADOR ? "+tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          //JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 
        
        //JOptionPane.showMessageDialog(null,"esta ++ o -- ? "+tablaTokens[indiceGeneral][1]+" "+tablaTokens[indiceGeneral+1][1]);
        if( (tablaTokens[indiceGeneral][1].equals("+")&&tablaTokens[indiceGeneral+1][1].equals("+")) || (tablaTokens[indiceGeneral][1].equals("-")&&tablaTokens[indiceGeneral+1][1].equals("-")) )
        {
          
        }

        else
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 
        indiceGeneral++; 

       // JOptionPane.showMessageDialog(null,"esta )"+tablaTokens[indiceGeneral][1]); 
       if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,"esta {"+tablaTokens[indiceGeneral][1]); 
        if(!tablaTokens[indiceGeneral][1].equals("{"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

        boolean intentoCiclo=false;
boolean intentoCondicion=false;
boolean intentoContrario=false;
boolean intentoVariable=false;
boolean intentoDefinir=false;
boolean intentoFuncionPropia=false;
boolean intentoFuncionNativa=false;
boolean estructurasEnBloque=true;

while(estructurasEnBloque==true)
{
  if(intentoCondicion=detectoIntentoSi())
      {
        boolean condicionCorrecta=comprobarDefineSi();
        if(condicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada correctamente");
          resultadoGeneral+="una sentencia si ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada incorrectamente");
          resultadoGeneral+="una sentencia si ha sido declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoContrario=detectoIntentoContrario())
      {
        boolean contrarioCorrecta=comprobarDefineContrario();
        if(contrarioCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada correctamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada incorrectamente"); 
          resultadoGeneral+="una sentencia contrario ha sido declarada incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

       if(intentoCiclo=detectoIntentoCiclo())
      {
        boolean cicloCorrecto=comprobarDefineCiclo();
        if(cicloCorrecto)
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado correctamente");
          resultadoGeneral+="un ciclo ha sido declarado correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado incorrectamente"); 
          resultadoGeneral+="un ciclo ha sido declarado incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoVariable=detectoIntentoVariable())
      {
        boolean declaracionVariableCorrecta=comprobarDefineVariable();
        if(declaracionVariableCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Una variable fue declarada correctamente");
          resultadoGeneral+="Una variable fue declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La variable fue declarada incorrectamente");
          resultadoGeneral+="La variable fue declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoDefinir=detectoIntentoDefinir())
      {
        boolean declaracionDefinicionCorrecta=comprobarAsignacion();
        if(declaracionDefinicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se asigno un valor a una variable");
          resultadoGeneral+="Se asigno un valor a una variable\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La asignacion de valor a una variable fue escrita incorrectamente");
          resultadoGeneral+="La asignacion de valor a una variable fue escrita incorrectamente\n";  
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionPropia=detectoIntentoLlamadaFuncionPropia())
      {
        boolean llamadaFuncPropCorrecta=comprobarLlamadaFuncionPropia();
        if(llamadaFuncPropCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion definida por el usuario");
          resultadoGeneral+="Se llamo a una funcion definida por el usuario\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion definida por el usuario de manera incorrecta"); 
          resultadoGeneral+="llamada a funcion definida por el usuario de manera incorrecta\n"; 
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionNativa=detectoIntentoLlamadaFuncionNativa())
      {
        boolean llamadaFuncNatCorrecta=comprobarLlamadaFuncionNativa();
        if(llamadaFuncNatCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion nativa");
          resultadoGeneral+="Se llamo a una funcion nativa\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion nativa de manera incorrecta"); 
          resultadoGeneral+="llamada a funcion nativa de manera incorrecta\n"; 
          return false;
        }
         estructurasEnBloque=true;
      }
      
      if(!intentoCiclo&&!intentoCondicion&&!intentoCondicion&&!intentoVariable&&!intentoDefinir&&!intentoFuncionPropia&&!intentoFuncionNativa)
      {
      estructurasEnBloque=false;
      }
}

        
         // JOptionPane.showMessageDialog(null,"esta }"+tablaTokens[indiceGeneral][1]); 
        if(!tablaTokens[indiceGeneral][1].equals("}"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
          // JOptionPane.showMessageDialog(null,"Error en "+tablaTokens[indiceGeneral][1]);
        }
        indiceGeneral++; 

      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }

       public boolean detectoIntentoLlamadaFuncionPropia()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
            // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
          if(tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR")&&tablaTokens[indiceGeneral+1][1].equals("("))
          {
             //JOptionPane.showMessageDialog(null,"Detecto de llamda a funcion propia");
          }
          else
          {
          analisisCorrecto=false;
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }

public boolean comprobarLlamadaFuncionPropia()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {
        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }

    public boolean detectoIntentoLlamadaFuncionNativa()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
            // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
          if(tablaTokens[indiceGeneral][0].equals("PALABRA_RESERVADA")&&tablaTokens[indiceGeneral+1][1].equals(".")&&tablaTokens[indiceGeneral+2][0].equals("FUNCION_RESERVADA"))
          {
             //JOptionPane.showMessageDialog(null,"Detecto de llamAda a funcion nativa");
          }
          else
          {
          analisisCorrecto=false;
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }

  public boolean comprobarLlamadaFuncionNativa()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("PALABRA_RESERVADA"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 


        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][1].equals("."))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][0].equals("FUNCION_RESERVADA"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        if(!tablaTokens[indiceGeneral][1].equals(";"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }

  public boolean detectoDeclaracionFuncionVacia()
    {
      boolean analisisCorrecto=true;
      if(tablaTokens.length>=4)
      {
        try
        {
            // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
          if(tablaTokens[indiceGeneral][1].equals("funcion")&&tablaTokens[indiceGeneral+1][1].equals("noRetorna"))
          {
             //JOptionPane.showMessageDialog(null,"Detecto de declaracion de funcion vacia");
          }
          else
          {
          analisisCorrecto=false;
          }
        }

        catch(ArrayIndexOutOfBoundsException ex)
        {
          return false;  
        }
      }

      else
      {
        return false;   
      }
      return analisisCorrecto;
    }

  public boolean comprobarDeclaraFuncionVacia()
  {
    boolean analisisCorrecto=true;
    String lineaError="";

    if(tablaTokens.length>=4)
    { 
      try
      {

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][1].equals("funcion"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 


        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][1].equals("noRetorna"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][0]);
        if(!tablaTokens[indiceGeneral][0].equals("IDENTIFICADOR"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("("))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals(")"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

         // JOptionPane.showMessageDialog(null,tablaTokens[indiceGeneral][1]);
        if(!tablaTokens[indiceGeneral][1].equals("{"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 

        boolean intentoCiclo=false;
boolean intentoCondicion=false;
boolean intentoContrario=false;
boolean intentoVariable=false;
boolean intentoDefinir=false;
boolean intentoFuncionPropia=false;
boolean intentoFuncionNativa=false;
boolean estructurasEnBloque=true;

while(estructurasEnBloque==true)
{
  if(intentoCondicion=detectoIntentoSi())
      {
        boolean condicionCorrecta=comprobarDefineSi();
        if(condicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada correctamente");
          resultadoGeneral+="una sentencia si ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia si ha sido declarada incorrectamente");
          resultadoGeneral+="una sentencia si ha sido declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoContrario=detectoIntentoContrario())
      {
        boolean contrarioCorrecta=comprobarDefineContrario();
        if(contrarioCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada correctamente");
          resultadoGeneral+="una sentencia contrario ha sido declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"una sentencia contrario ha sido declarada incorrectamente"); 
          resultadoGeneral+="una sentencia contrario ha sido declarada incorrectamente\n"; 
        return false;
        }
        estructurasEnBloque=true;
      }

       if(intentoCiclo=detectoIntentoCiclo())
      {
        boolean cicloCorrecto=comprobarDefineCiclo();
        if(cicloCorrecto)
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado correctamente");
          resultadoGeneral+="un ciclo ha sido declarado correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"un ciclo ha sido declarado incorrectamente");
          resultadoGeneral+="un ciclo ha sido declarado incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoVariable=detectoIntentoVariable())
      {
        boolean declaracionVariableCorrecta=comprobarDefineVariable();
        if(declaracionVariableCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Una variable fue declarada correctamente");
          resultadoGeneral+="Una variable fue declarada correctamente\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La variable fue declarada incorrectamente");
          resultadoGeneral+="La variable fue declarada incorrectamente\n";  
        return false;
        }
        estructurasEnBloque=true;
      }

      if(intentoDefinir=detectoIntentoDefinir())
      {
        boolean declaracionDefinicionCorrecta=comprobarAsignacion();
        if(declaracionDefinicionCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se asigno un valor a una variable");
          resultadoGeneral+="Se asigno un valor a una variable\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"La asignacion de valor a una variable fue escrita incorrectamente");
          resultadoGeneral+="La asignacion de valor a una variable fue escrita incorrectamente\n";  
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionPropia=detectoIntentoLlamadaFuncionPropia())
      {
        boolean llamadaFuncPropCorrecta=comprobarLlamadaFuncionPropia();
        if(llamadaFuncPropCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion definida por el usuario");
          resultadoGeneral+="Se llamo a una funcion definida por el usuario\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion definida por el usuario de manera incorrecta"); 
          resultadoGeneral+="llamada a funcion definida por el usuario de manera incorrecta\n"; 
          return false;
        }
         estructurasEnBloque=true;
      }

      if(intentoFuncionNativa=detectoIntentoLlamadaFuncionNativa())
      {
        boolean llamadaFuncNatCorrecta=comprobarLlamadaFuncionNativa();
        if(llamadaFuncNatCorrecta)
        {
          //JOptionPane.showMessageDialog(null,"Se llamo a una funcion nativa");
          resultadoGeneral+="Se llamo a una funcion nativa\n";
        }

        else
        {
          //JOptionPane.showMessageDialog(null,"llamada a funcion nativa de manera incorrecta");
          resultadoGeneral+="llamada a funcion nativa de manera incorrecta\n";  
          return false;
        }
         estructurasEnBloque=true;
      }
      
      if(!intentoCiclo&&!intentoCondicion&&!intentoCondicion&&!intentoVariable&&!intentoDefinir&&!intentoFuncionPropia&&!intentoFuncionNativa)
      {
      estructurasEnBloque=false;
      }
}


        if(!tablaTokens[indiceGeneral][1].equals("}"))
        {
          analisisCorrecto=false;
          lineaError=tablaTokens[indiceGeneral][2];
        }
        indiceGeneral++; 
      }

      catch(ArrayIndexOutOfBoundsException exception)
      {
        return false; 
      }
    }

    else
    {
      return false;
    }

    return analisisCorrecto;
    }
  }