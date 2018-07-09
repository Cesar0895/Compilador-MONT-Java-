using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using System.Text.RegularExpressions;
using Xamarin.Forms;

namespace Mont
{
	public partial class MainPage : ContentPage
	{
        String cadenaTexto = "";
        String[,] arreglolineasTexto;
        String[,] arregloPalabrasReservadas = new String[,] {
            { "Mont", "Palabra reservada", "Palabra que indica el inicio de una programa",@"Mont(\s+)","0"},
           
            { "noRetorna", "Palabra reservada", "Palabra que indica que una función no retorna ningún valor",@"noRetorna(\s+)","0"},
           
            { "funcion", "Palabra reservada", "Palabra que indica la declaración de una función",@"funcion(\s+)", "0" },
           
            {"configuracion","Palabra reservada","Palabra que indica la declaración de la configuración del montacargas",@"configuracion","1"},

            {"iniciar","Funcion nativa","Función de la clase Maniobra encargada de iniciar las maniobras",@"iniciar","2"},

            {"encender","Funcion nativa","Función de la clase Motor la cual se encarga de encender el motor",@"encender","2"},

            {"apagar","Palabra reservada","Función de la clase Motor la cual se encarga de apagar el motor",@"apagar","2"},

            {"avanzar","Funcion nativa","Función de la clase Motor la cual se encarga de accionar el motor, hasta que el montacargas recorra cierta distancia en metros. toma un parámetro del tipo flotante de 0 a n ",@"avanzar","2"},

            {"reversa","Funcion nativa","Función de la clase Motor la cual se encarga de accionar el motor, hasta que el montacargas recorra cierta distancia hacia atrás en metros. toma un parámetro del tipo flotante de 0 a n",@"reversa","2"},

            {"frenar","Funcion nativa","Función de la clase Motor la cual se encarga de detener el motor. Haciendo que el montacargas también se detenga",@"frenar","2"},

            {"mover","Funcion nativa","Función de la clase Cuchilla la cual mueve las cuchillas en vertical porcentualmente.",@"mover","2"},

            {"abrir","Funcion nativa","Función de la clase Cuchilla la cual controla la apertura de las cuchillas porcentualmente. Toma un parámetro flotante de 0 a 100",@"abrir","2"},

            {"inclinar","Funcion nativa","Función de la clase Cuchilla la cual controla la ",@"inclinar","2"},

            {"Maniobra","Clase","Clase para administrar la ejecución de las maniobras del montacargas",@"Maniobra","3"},

            {"Cuchilla","Clase","Clase que contiene funciones y propiedades de la cuchilla",@"Cuchilla","3"},

            {"Motor","Clase","Clase que contiene funciones y propiedades del motor",@"Motor","3"},

            {"entero","Clase","Clase del tipo de datos entero, contiene todas sus propiedades y funciones",@"entero","3"},

            {"flotante","Clase","Clase del tipo de datos flotante, contiene todas sus propiedades y funciones",@"flotante","3"},

            {"cadena","Clase","Clase del tipo de datos cadena, contiene todas sus propiedades y funciones ",@"cadena","3"},

            {"booleano","Clase","Clase del tipo de datos booleano, contiene todas sus propiedades y funciones",@"booleano","3"},

            {"ESTADO_MOTOR","Propiedad nativa","Propiedad cadena de la clase Motor, que contiene el estado actual del motor",@"ESTADO_MOTOR","4"},

            {"DISTANCIA_ESTABLECIDA","Propiedad nativa","Propiedad flotante de la clase Motor, que contiene la distancia que el desarrollador le establece al motor para recorrer",@"DISTANCIA_ESTABLECIDA","4"},

            {"DISTANCIA_ESTABLECIDA_REVERSA","Propiedad nativa","Propiedad flotante de la clase Motor, que contiene la distancia en reversa que el desarrollador le establece al motor para recorrer",@"DISTANCIA_ESTABLECIDA_REVERSA","4"},

            {"DISTANCIA_RECORRIDA","Propiedad nativa","Propiedad flotante de la clase Motor, que contiene la distancia recorrida desde el inicio de la instrucción",@"DISTANCIA_RECORRIDA","4"},

            {"ESTADO_INCLINACION","Propiedad nativa","Propiedad booleana de la clase Cuchilla la cual contiene el estado de inclinación de las cuchillas",@"ESTADO_INCLINACION","4"},

            {"APERTURA_ESTABLECIDA","Propiedad nativa","Propiedad flotante de la clase Cuchilla la cual contiene la apertura establecida de las cuchillas",@"APERTURA_ESTABLECIDA","4"},

            {"APERTURA_AVANCE","Propiedad nativa","Propiedad flotante de la clase Cuchilla la cual contiene la apertura actual de las cuchillas desde que se inició la instrucción",@"APERTURA_AVANCE","4"},

            {"MOVIMIENTO_ESTABLECIDO","Propiedad nativa","Propiedad flotante de la clase Cuchilla la cual contiene el movimiento establecido en vertical de las cuchillas",@"MOVIMIENTO_ESTABLECIDO","4"},

            {"MOVIMIENTO_AVANCE","Propiedad nativa","Propiedad flotante de la clase Cuchilla la cual contiene el movimiento actual en vertical de las cuchillas desde que se inició la instrucción",@"MOVIMIENTO_AVANCE","4"},
            
            {"(","simbolo","",@"\(","5"},
            
            {")","simbolo","",@"\)","5"},
            
            {"-","simbolo","",@"\-","5"},
            
            {"+","simbolo","",@"\+","5"},
            
            {"*","simbolo","",@"\*","5"},
            
            {"/","simbolo","",@"\/","5"},
            
            {"&","simbolo","",@"\&","5"},
            
            {"=","simbolo","",@"\=","5"},
            
            {".","simbolo","",@"\.","5"},
            
            {"{","simbolo","",@"\{","5"},
            
            {"}","simbolo","",@"\}","5"},
            
            {"[","simbolo","",@"\[","5"},
            
            {"]","simbolo","",@"\]","5"}
        };
        List<PalabraEncontrada> listaPalabrasEncontradas = new List<PalabraEncontrada>();
        List<PalabraError> listaErroresEncontrados = new List<PalabraError>();
        public MainPage()
		{
			InitializeComponent();
            btnCompilar.Clicked += btnCompilar_Clicked;
        }

        private async void BtnPalabras_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Tabla());
        }

        private void btnCompilar_Clicked(object sender, EventArgs e)
        {
            analizarLexico();
        }

        public void analizarLexico()
        {   //Lista temporal donde se guardaran los resultados por linea
            List<PalabraEncontrada> listaPalabrasEncontradaTemporal = new List<PalabraEncontrada>();
            //Lista temporal donde se guardaran los resultados por linea ya ordenadas
            List<PalabraEncontrada> listaPalabrasEncontradaPorLineaOrdenada;
            //Lista temporal donde se guardaran los resultados por linea ya ordenadas
            List<PalabraError> listaErroresTemporal;

            //Tomar cadena de texto
            cadenaTexto = txtEditor.Text;
            //Descomponer cadena en saltos de linea 
            String[] arregloLineas = cadenaTexto.Split('\n');
            //Inicializar el arreglo de lineas de texto
            arreglolineasTexto = new String[arregloLineas.Length, 2];
            //Agregar cada linea a un arreglo multidimensional
            for (int i = 0; i < arregloLineas.Length; i++)
            {
                string lineaActual = arregloLineas[i];
                //DisplayAlert("Aviso",lineaActual, "Si", "No");
                int numeroLinea = i + 1;
                arreglolineasTexto[i, 0] = numeroLinea.ToString();
                arreglolineasTexto[i, 1] = " "+lineaActual+" ";
            }
            //Por cada linea someterla a todas las palabras reservadas
            for (int i = 0; i < arreglolineasTexto.GetLength(0); i++)
            {
                String cadenaTexto = arreglolineasTexto[i, 1];
                //DisplayAlert("Aviso", cadenaTexto,"si","no");
                for (int j = 0; j < arregloPalabrasReservadas.GetLength(0); j++)
                {
                    //tomo su expresion regular
                    Regex rx = new Regex(arregloPalabrasReservadas[j, 3]);

                    //ciclo foreach para recorrer la cadena en busca de 1 o mas coincidencias

                    foreach (Match validacionregex in rx.Matches(cadenaTexto))
                    {
                        //DisplayAlert("Aviso", validacionregex.Value+" INDICE:"+validacionregex.Index, "si", "no");
                        //Cada que encuentre una pasarla a comprobar
                        if (comprobarToken(j, validacionregex, i))
                        {
                            //Por cada palabra encontrada crear un objeto temporal
                            PalabraEncontrada palabraReservada = new PalabraEncontrada();
                            palabraReservada.palabra = arregloPalabrasReservadas[j, 0];
                            palabraReservada.tipo = arregloPalabrasReservadas[j, 2];
                            palabraReservada.descripcion = arregloPalabrasReservadas[j, 2];
                            palabraReservada.indice = validacionregex.Index;
                            palabraReservada.nLinea = int.Parse(arreglolineasTexto[i, 0]);

                            //Y agregarlo a listaPalabrasEncontradaTemporal
                            listaPalabrasEncontradaTemporal.Add(palabraReservada);
                        }

                    }
                }
                //Cada que termina de someter una linea de texto a las palabras reservadas
                //Si encontro algunas hay que ordenarlas
                listaPalabrasEncontradaPorLineaOrdenada = ordenarPalabrasEncontradasPorLinea(listaPalabrasEncontradaTemporal);
                //Una vez ordenada la lista encontrar las palabras que no correspondieron a ninguna palabra reservada
                listaErroresTemporal = encontrarErroresPorLinea(listaPalabrasEncontradaPorLineaOrdenada, arreglolineasTexto[i, 1], i+1);

                //Agregar palabras encontradas y errores a sus respectivas listas 
                listaPalabrasEncontradas.AddRange(listaPalabrasEncontradaPorLineaOrdenada);
                listaErroresEncontrados.AddRange(listaErroresTemporal);
            }

            //Por ultimo mostrar los resultados
            //mostrarPalabrasEncontradasEnTabla();
            mostrarPalabrasEncontradas(listaPalabrasEncontradas);
            mostrarErroresEncontrados(listaErroresEncontrados);
            //mostrarErroresEcontradosEnLinea();
        }

        public bool comprobarToken(int indicePalabraArreglo, Match validacionRegex, int indiceLineaArreglo)
        {
            //Tomar el indice de la coincidencia encontrada
            var indicePalabraReservada = validacionRegex.Index;
            //obtener el tipo de palabra reservada (aislada,parametro)
            var tipoPalabraReservada = arregloPalabrasReservadas[indicePalabraArreglo, 4];

            switch (tipoPalabraReservada)
            {
                //palabra aislada
                case "0":
                    //DisplayAlert("Aviso","Se trata de una palabra aislada","si","no");
                    if (comprobarPalabraAislada(validacionRegex, indicePalabraReservada, indiceLineaArreglo))
                    {
                        return true;
                    }
                    break;
                //palabra parametro
                case "1":
                    if (comprobarPalabraParametro(validacionRegex, indicePalabraReservada, indicePalabraArreglo, indiceLineaArreglo))
                    {
                        return true;
                    }
                    break;

                //palabra funcion nativa con punto antes opcional y parentesis despues opcional
                case "2":
                    if (comprobarPalabraFuncionNativa(validacionRegex, indicePalabraReservada, indicePalabraArreglo, indiceLineaArreglo))
                    {
                        return true;
                    }
                    break;

                //palabra clase con espacio en blanco antes opcional y con punto final opcional
                case "3":
                    if (comprobarPalabraClase(validacionRegex, indicePalabraReservada, indicePalabraArreglo, indiceLineaArreglo))
                    {
                        return true;
                    }
                    break;

                //palabra propiedad con punto detras opcional o espacio en blanco 
                //y operadores logicos y de asignacion o espacio en blanco final opcional
                case "4":
                    if (comprobarPalabraPropiedad(validacionRegex, indicePalabraReservada, indicePalabraArreglo, indiceLineaArreglo))
                    {
                        return true;
                    }
                    break;

                case "5":
                    if (comprobarPalabraSimbolo(validacionRegex, indicePalabraReservada, indicePalabraArreglo, indiceLineaArreglo))
                    {
                        return true;
                    }
                    break;

                default:
                   
                    break;
            }
            return false;
        }

        public bool comprobarPalabraAislada(Match validacionRegex, int indicePalabraReservada, int indiceLineaArreglo)
        {
            string cadenaTexto = arreglolineasTexto[indiceLineaArreglo, 1];
            //DisplayAlert("Aviso",cadenaTexto,"si","no");
            //validar que la palabra este aislada  por la parte de atras 
            if ((cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(" "))
            {
                //DisplayAlert("Aviso", "la palabra "+validacionRegex.Value+" se encuentra aislada y lista para agregarse a la tabla", "si","no");
                return true;
            }
            return false;
        }

        public bool  comprobarPalabraParametro(Match validacionRegex, int indicePalabraReservada,int indicePalabraArreglo, int indiceLineaArreglo)
        {
            int longitudPalabraArreglo = arregloPalabrasReservadas[indicePalabraArreglo, 0].Length;
            string cadenaTexto = arreglolineasTexto[indiceLineaArreglo, 1];
            //Validar que detras de la palabra haya un espacio en blanco
            if ((cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(" "))
            {
                //Despues validar que despues de la palabra haya un espacio en blanco o un parentesis (
                if ((cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals(" ") || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("("))
                {
                    //DisplayAlert("Aviso", "la palabra " + validacionRegex.Value + " se encuentra aislada y lista para agregarse a la tabla", "si", "no");
                    return true;
                }
            }
            return false;
        }

        public bool  comprobarPalabraFuncionNativa(Match validacionRegex, int indicePalabraReservada, int indicePalabraArreglo, int indiceLineaArreglo)
        {
            int longitudPalabraArreglo = arregloPalabrasReservadas[indicePalabraArreglo, 0].Length;
            string cadenaTexto = arreglolineasTexto[indiceLineaArreglo, 1];

            //Validar que antes de la palabra haya un espacio en blanco o un punto
            if ((cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(" ") || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("."))
            {
                //Validar que despues de la palabra haya un espacio en blanco o un parentesis
                if ((cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals(" ") || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("("))
                {
                   // DisplayAlert("Aviso", "la palabra " + validacionRegex.Value + " se encuentra aislada y lista para agregarse a la tabla", "si", "no");
                    return true;
                }
            }
            return false;
        }

        public bool comprobarPalabraClase(Match validacionRegex, int indicePalabraReservada, int indicePalabraArreglo, int indiceLineaArreglo)
        {
            int longitudPalabraArreglo = arregloPalabrasReservadas[indicePalabraArreglo, 0].Length;
            string cadenaTexto = arreglolineasTexto[indiceLineaArreglo, 1];
   
            //validar que antes de la palabra haya espacios en blanco o simbolos
            if (
                (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(" ")
                ||(cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("=")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("<")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(">")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("-")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("+")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("/")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("*")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("&")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("|")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("(")
                || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(";")
                )
            {
                //Validar que despues de la palabra haya un espacio en blanco o un punto
                if ((cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals(" ") || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("."))
                {
                   // DisplayAlert("Aviso", "la palabra " + validacionRegex.Value + " se encuentra aislada y lista para agregarse a la tabla", "si", "no");
                    return true;
                }
            }
            return false;
        }

        public bool comprobarPalabraPropiedad(Match validacionRegex, int indicePalabraReservada, int indicePalabraArreglo, int indiceLineaArreglo)
        {
            int longitudPalabraArreglo = arregloPalabrasReservadas[indicePalabraArreglo, 0].Length;
            string cadenaTexto = arreglolineasTexto[indiceLineaArreglo, 1];

            //validar que la palabra este aislada con espacio en blanco o un punto .
            if ((cadenaTexto[indicePalabraReservada - 1].ToString()).Equals(" ") || (cadenaTexto[indicePalabraReservada - 1].ToString()).Equals("."))
            {
                //Validar que despues de la palabra haya espacio en blanco o simbolos
                if ((cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals(" ")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("=")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("<")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals(">")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("-")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("+")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("/")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("*")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("&")
                || (cadenaTexto[indicePalabraReservada + longitudPalabraArreglo].ToString()).Equals("|"))
                {
                    //DisplayAlert("Aviso", "la palabra " + validacionRegex.Value + " se encuentra aislada y lista para agregarse a la tabla", "si", "no");
                    return true;
                }
            }
            return false;
        }

        public bool  comprobarPalabraSimbolo(Match validacionRegex, int indicePalabraReservada, int indicePalabraArreglo, int indiceLineaArreglo)
        {
            //DisplayAlert("Aviso", "simbolo " + validacionRegex.Value,"si","no");
            return true;
        }

        public List<PalabraEncontrada> ordenarPalabrasEncontradasPorLinea(List<PalabraEncontrada> listaPalabrasEncontradaTemporal)
        {
            List<PalabraEncontrada> ListaOrdenada = listaPalabrasEncontradaTemporal.OrderBy(o => o.indice).ToList();
            string palabrasEncontradas = "";
            for (int i=0;i< ListaOrdenada.Count; i++)
            {
                palabrasEncontradas += ListaOrdenada[i].palabra + "\n";
            }
            //DisplayAlert("Aviso", palabrasEncontradas, "si", "no");

            return ListaOrdenada;
        }

        public List<PalabraError> encontrarErroresPorLinea(List<PalabraEncontrada> listaPalabrasEncontradaPorLinea,string lineaTexto,int nLinea)
        {
            string palabraError = "";
            List<PalabraError> listaErrores = new List<PalabraError>();
            for (int i=0; i<listaPalabrasEncontradaPorLinea.Count; i++)
            {
                if ((i+1) < listaPalabrasEncontradaPorLinea.Count)
                {
                    int tamanoPalabra = (listaPalabrasEncontradaPorLinea[i].palabra.Length);
                    int indiceError = (listaPalabrasEncontradaPorLinea[i].indice)+tamanoPalabra;
                    int finError = listaPalabrasEncontradaPorLinea[i+1].indice;
                    int longitudPalabraError = finError - indiceError;
                    string error = lineaTexto.Substring(indiceError, longitudPalabraError);
                    palabraError += error + "\n";

                    PalabraError palabraE = new PalabraError();
                   
                    palabraE.nLinea = nLinea;
                    palabraE.palabra = error;
                   
                    listaErrores.Add(palabraE);
                }
            }

            //DisplayAlert("Aviso",palabraError,"si","no");

            return listaErrores;
        }

        public void mostrarPalabrasEncontradas(List<PalabraEncontrada> listaPalabras)
        {
            string palabras = "";
            for (int i = 0; i < listaPalabras.Count; i++)
            {
                palabras += "Palabra: " + listaPalabras[i].palabra + "\n" + "N. Linea: " + listaPalabras[i].nLinea + "\n\n";
            }

            DisplayAlert("Lista de palabras encontradas",palabras,"Acaptar","Cerrar");
        }

        public void mostrarErroresEncontrados(List<PalabraError> listaPalabras)
        {
            string palabras = "";
            for (int i = 0; i < listaPalabras.Count; i++)
            {
                palabras += "Palabra: " + listaPalabras[i].palabra + "\n" + "N. Linea: " + listaPalabras[i].nLinea + "\n\n";
            }

            DisplayAlert("Lista de errores encontradas", palabras, "Acaptar", "Cerrar");
        }
    }
}
