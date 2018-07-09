package Compilador;
import java_cup.runtime.*;

%%


%public
%class Scanner
%implements sym

%unicode

%line
%column

%cup
%cupdebug


%{

StringBuilder string = new StringBuilder();
  
  private Symbol  symbol(sym.int type) {
    return new Java symbol(sym.type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(sym.int type, Object value) {
    return new Java symbol(sym.type, yyline+1, yycolumn+1, value);
  }

%}
<YYINITIAL>{
({WHITE})* {/* ignore */}
"//".* {/* ignore */}
("/*")(({SMI})|({COM})|({PU})|({IG})|({L})|({D})|({CAA})|({CCA})|({SMA})|({SME})|({CA})|({SD})|({SP})|({PC})|({PA})|({PCE})|({LA})|({LC})|({WHITE}))*("*/") {/* ignore */}
L=[a-zA-Z_ñ]
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
COM=[,]
PU=[.]
AA=[&]
SMI=["$" "?" "¿" "!" "¿"]
WHITE=[ \t\r\n]


	/*reserved words */
	"playNote"|"main"|"call"                                {return  symbol(sym.R_FUNCTION);}
	"def" 							{return  symbol(sym.DEF);}
	"for" 							{return  symbol(sym.FOR);}
	"#define" 						{return  symbol(sym.DEFINE);}
	"program" 						{return  symbol(sym.PROGRAM);}
	"#defproperties"                                        {return  symbol(sym.DEF_PROPERTIES);}
	"#endproperties"                                        {return  symbol(sym.END_PROPERTIES);}
	"int" 							{return  symbol(sym.DATA_TYPE);}
        "String"                                                {return  symbol(sym.DATA_TYPE);}
	
        /*reserved words of the same type*/
	"Tempo"|"BPM"|"Genre"|"Name"|"Metre" {  return  symbol(sym.W_PROPERTIES);}
	"#E4"|"#F4"|"#G4"|"#A4"|"#B4"|"#C5"|"#D5"|"#F4s"|"#G4s"|"#A4s"|"#C5s"|"#D5s"|"#E5"|"#F5"|"#G5"|"#A5"|"#B5"|"#C6"|"#D6"|"#F5s"|"#G5s"|"#A5s"|"#C6s"|"#D6s"|"#E6" {  return  symbol(sym.PITCH_NOTATION);}
	"E_STRING"|"A_STRING"|"D_STRING"|"G_STRING"|"B_STRING"|"e_STRING" {  return  symbol(sym.NOTE_STRING);}
	"QUADRUPLE_WHOLE_NOTE" | "DOUBLE_WHOLE_NOTE" | "WHOLE_NOTE" | "HALF_NOTE" | "QUARTER_NOTE" | "EIGHTH_NOTE" | "SIXTEENTH_NOTE" {  return  symbol(sym.NOTE_VALUE);}
        ({CA})({L}|{D})*({CA}) {return symbol(sys.CADENA);}
	
/*	identifiers */
	{L}({L}|{D}|"_")* 				{  return symbol(sym.ID);}
	({SMA}|{SME})?{D}+ 				{  return symbol(sym.INT);}
	({L}|{D}|{SMI})+ 				{  return symbol(sym.STR_ERROR);}

	/* separators */
	"("                            {   return  symbol(sym.LPAREN); }
	")"                            {   return  symbol(sym.RPAREN); }
	"{"                            {   return  symbol(sym.LBRACE); }
	"}"                            {   return  symbol(sym.RBRACE); }
	"["                            {   return  symbol(sym.LBRACK); }
	"]"                            {   return  symbol(sym.RBRACK); }
	";"                            {   return  symbol(sym.SEMICOLON); }
	","                            {   return  symbol(sym.COMMA); }
	"."                            {   return  symbol(sym.DOT); }
	  
	/* operators */
	"="                            {   return  symbol(sym.EQ); }
	">"                            {   return  symbol(sym.GT); }
	"<"                            {   return  symbol(sym.LT); }
	"=="                           {   return  symbol(sym.EQEQ); }
	"<="                           {   return  symbol(sym.LTEQ); }
	">="                           {   return  symbol(sym.GTEQ); }
	"++"                           {   return  symbol(sym.PLUSPLUS); }
	"--"                           {   return  symbol(sym.MINUSMINUS); }
	"+"                            {   return  symbol(sym.PLUS); }
	"-"                            {   return  symbol(sym.MINUS); }
        "&"                            {   return  symbol(sym.EQ); }
}



. {return ERROR;}