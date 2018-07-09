package Compilador;
import static Compilador.Token.*;
%%
%class Lexer
%line   
%type Token

%{   
public String lexeme;
public int linea;
%}
L=[a-zA-Z]
D=[0-9]
CAA=["["]
CCA=["]"]
SMA=[+]
SME=[-]
CA=[\"]
SD=[/]
SP=[*]
PC=[;]
PA=[(]
PCE=[)]
LA=[{]
LC=[}]
IG=[=]
MY=[>]
MN=[<]
COM=[,]
PU=[.]
SMI=["$""&""?""¿""!""¿""%"]
WHITE=[ \t\r\n]



%{

%}
%%

({WHITE})* {/* ignore */}
"//".* {/* ignore */}
("/*")(({SMI})|({COM})|({PU})|({IG})|({L})|({D})|({CAA})|({CCA})|({SMA})|({SME})|({CA})|({SD})|({SP})|({PC})|({PA})|({PCE})|({LA})|({LC})|({WHITE}))*("*/") {/* ignore */}
"configuracion"|"obtenBateria"|"mover"|"apagar"|"obtenAceleracion"|"obtenY"|"obtenX"|"iniciar"|"avanzar"|"reversa"|"giro"|"obtenI"|"obtenA"|"obtenCarga" {lexeme=yytext(); linea=yyline; return FUNCION_RESERVADA;}
"Mont"|"si"|"contrario"|"Cuchilla"|"Motor"|"Montacarga"|"Maniobra"|"funcion"|"frenoMano"|"verdadero"|"falso"|"define"|"flotante"|"Carga"|"ciclo"|"cargaMaxima"|"noRetorna"|"retornar"|"Direccion"|"entero"|"cadena"|"booleano"|"verdadero"|"falso" {lexeme=yytext(); linea=yyline; return PALABRA_RESERVADA;}
({CA})({L}|{D})*({CA}) {lexeme=yytext(); linea=yyline; return CADENA;}
{SD} {lexeme=yytext(); linea=yyline; return SIGNO_DIVISION;}
{SMA} {lexeme=yytext(); linea=yyline; return SIGNO_MAS;}
{SME} {lexeme=yytext(); linea=yyline; return SIGNO_MENOS;}
{SP} {lexeme=yytext(); linea=yyline; return SIGNO_MULTIPLICACION;}
{LC} {lexeme=yytext(); linea=yyline; return LLAVE_CERRADA;}
{LA} {lexeme=yytext(); linea=yyline; return LLAVE_ABIERTA;}
{PA} {lexeme=yytext(); linea=yyline; return PARENTESIS_ABIERTO;}
{PCE} {lexeme=yytext(); linea=yyline; return PARENTESIS_CERRADO;}
{PC} {lexeme=yytext(); linea=yyline; return PUNTO_Y_COMA;}
{SMA} {lexeme=yytext(); linea=yyline; return SIGNO_MAS;}
{SME} {lexeme=yytext(); linea=yyline; return SIGNO_MENOS;}
{CCA} {lexeme=yytext(); linea=yyline; return CORCHETE_CERRADO;}
{CAA} {lexeme=yytext(); linea=yyline; return CORCHETE_ABIERTO;} 
{IG} {lexeme=yytext(); linea=yyline; return IGUAL;}
{MY}|{MN}|{IG}{IG}|{MY}{IG}|{MN}{IG} {lexeme=yytext(); linea=yyline; return COMPARACION;}
{PU} {lexeme=yytext(); linea=yyline; return PUNTO;}
{COM} {lexeme=yytext(); linea=yyline; return COMA;}
({L}|{D}|{SMI})+ {lexeme=yytext(); linea=yyline; return CADERROR;}

. {return ERROR;}