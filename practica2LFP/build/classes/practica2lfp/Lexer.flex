package practica2lfp;
import static practica2lfp.Token.*;
%%
%class Lexer
%type Token
L=[a-zA-Z_]
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
WHITE=[ \t\r\n]


%{
public String lexeme;
%}
%%
({WHITE})* {/* ignore */}
"//".* {/* ignore */}


("/*")(({COM})|({PU})|({IG})|({L})|({D})|({CAA})|({CCA})|({SMA})|({SME})|({CA})|({SD})|({SP})|({PC})|({PA})|({PCE})|({LA})|({LC})|({WHITE}))*("*/") {/* ignore */}
"playNote" {lexeme=yytext(); return FUNCIONRESERVADA;}
"int"|"Tempo"|"BPM"|"Genre"|"Name"|"Metre"|"#E4"|"#F4"|"#G4"|"#A4"|"#B4"|"#C5"|"#D5"|"#F4s"|"#G4s"|"#A4s"|"#C5s"|"#D5s"|"#E5"|"#F5"|"#G5"|"#A5"|"#B5"|"#C6"|"#D6"|"#F5s"|"#G5s"|"#A5s"|"#C6s"|"#D6s"|"#E6"|"E_STRING"|"A_STRING"|"D_STRING"|"G_STRING"|"B_STRING"|"e_STRING"|"def"|"for"|"#defproperties"|"#endproperties"|"#define"|"program"|"String"|"main"|"call" {lexeme=yytext(); return PALABRARESERVADA;}
{L}({L}|{D})* {lexeme=yytext(); return ID;}
({SMA}|{SME})?{D}+ {lexeme=yytext();return INT;}
({CA})({L}|{D})*({CA}) {lexeme=yytext(); return CADENA;}
{SD} {lexeme=yytext(); return SIGNODIVISION;}
{SMA} {lexeme=yytext(); return SIGNOMAS;}
{SME} {lexeme=yytext(); return SIGNOMENOS;}
{SP} {lexeme=yytext(); return SIGNOPOR;}
{LC} {lexeme=yytext(); return LLAVECERRADO;}
{LA} {lexeme=yytext(); return LLAVEABIERTO;}
{PA} {lexeme=yytext(); return PARENTECISABIERTO;}
{PCE} {lexeme=yytext(); return PARENTECISCERRADO;}
{PC} {lexeme=yytext(); return PUNTOYCOMA;}
{SMA} {lexeme=yytext(); return SIGNOMAS;}
{SME} {lexeme=yytext(); return SIGNOMENOS;}
{CCA} {lexeme=yytext(); return CORCHETECERRADO;}
{CAA} {lexeme=yytext(); return CORCHETEABIERTO;} 
{IG} {lexeme=yytext(); return IGUAL;}
{PU}} {lexeme=yytext(); return COMA;}
{COM}} {lexeme=yytext(); return PUNTO;}
. {return ERROR;}