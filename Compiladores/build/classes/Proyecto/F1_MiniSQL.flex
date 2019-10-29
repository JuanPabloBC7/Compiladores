package Proyecto;
import static Proyecto.Token.*;
%%
%class F1_MiniSQL
%type Token
%line
%column 
/*JPBC*/
//-----------------------------Palabras Reservadas---------------------------------------------------------------------------------------------------------------------------------------
P_Reservadas= "ABSOLUTE" | "ACTION" | "ADA" | "ADD" | "ALL" | "ALLOCATE" | "ALTER" | "AND" | "ANY" | "ARE" | "AS" | "ASC" | "ASSERTION" | "AT" | "AUTHORIZATION" | "AVG" | "BACKUP" | "BEGIN" | "BETWEEN" | "BIT" | "BIT_LENGTH" | "BOTH" | "BREAK" | "BROWSE" | "BULK" | "BY" | "CASCADE" | "CASCADED" | "CASE" | "CAST" | "CATALOG" | "CHAR" | "CHAR_LENGTH" | "CHARACTER" | "CHARACTER_LENGTH" | "CHECK" | "CHECKPOINT" | "CLOSE" | "CLUSTERED" | "COALESCE" | "COLLATE" | "COLLATION" | "COLUMN" | "COMMIT" | "COMPUTE" | "CONNECT" | "CONNECTION" | "CONSTRAINT" | "CONSTRAINTS" | "CONTAINS" | "CONTAINSTABLE" | "CONTINUE" | "CONVERT" | "CORRESPONDING" | "COUNT" | "CREATE" | "CROSS" | "CURRENT" | "CURRENT_DATE" | "CURRENT_TIME" | "CURRENT_TIMESTAMP" | "CURRENT_USER" | "CURSOR" | "DATABASE" | "DATE" | "DAY" | "DBCC" | "DEALLOCATE" | "DEC" | "DECIMAL" | "DECLARE" | "DEFAULT" | "DEFERRABLE" | "DEFERRED" | "DELETE" | "DENY" | "DESC" | "DESCRIBE" | "DESCRIPTOR" | "DIAGNOSTICS" | "DISCONNECT" | "DISK" | "DISTINCT" | "DISTRIBUTED" | "DOMAIN" | "DOUBLE" | "DROP" | "DUMP" | "ELSE" | "END" | "END-EXEC" | "ERRLVL" | "ESCAPE" | "EXCEPT" | "EXCEPTION" | "EXEC" | "EXECUTE" | "EXISTS" | "EXIT" | "EXTERNAL" | "EXTRACT" | "FALSE" | "FETCH" | "FILE" | "FILLFACTOR" | "FIRST" | "FLOAT" | "FOR" | "FOREIGN" | "FORTRAN" | "FOUND" | "FREETEXT" | "FREETEXTTABLE" | "FROM" | "FULL" | "FUNCTION" | "GET" | "GLOBAL" | "GO" | "GOTO" | "GRANT" | "GROUP" | "HAVING" | "HOLDLOCK" | "HOUR" | "IDENTITY" | "IDENTITY_INSERT" | "IDENTITYCOL" | "IF" | "IMMEDIATE" | "IN" | "INCLUDE" | "INDEX" | "INDICATOR" | "INITIALLY" | "INNER" | "INPUT" | "INSENSITIVE" | "INSERT" | "INT" | "INTEGER" | "INTERSECT" | "INTERVAL" | "INTO" | "IS" | "ISOLATION" | "JOIN" | "KEY" | "KILL" | "LANGUAGE" | "LAST" | "LEADING" | "LEFT" | "LEVEL" | "LIKE" | "LINENO" | "LOAD" | "LOCAL" | "LOWER" | "MATCH" | "MAX" | "MERGE" | "MIN" | "MINUTE" | "MODULE" | "MONTH" | "NAMES" | "NATIONAL" | "NATURAL" | "NCHAR" | "NEXT" | "NO" | "NOCHECK" | "NONCLUSTERED" | "NONE" | "NOT" | "NULL" | "NULLIF" | "NUMERIC" | "OCTET_LENGTH" | "OF" | "OFF" | "OFFSETS" | "ON" | "ONLY" | "OPEN" | "OPENDATASOURCE" | "OPENQUERY" | "OPENROWSET" | "OPENXML" | "OPTION" | "OR" | "ORDER" | "OUTER" | "OUTPUT" | "OVER" | "OVERLAPS" | "PAD" | "PARTIAL" | "PASCAL" | "PERCENT" | "PIVOT" | "PLAN" | "POSITION" | "PRECISION" | "PREPARE" | "PRESERVE" | "PRIMARY" | "PRINT" | "PRIOR" | "PRIVILEGES" | "PROC" | "PROCEDURE" | "PUBLIC" | "RAISERROR" | "READ" | "READTEXT" | "REAL" | "RECONFIGURE" | "REFERENCES" | "RELATIVE" | "REPLICATION" | "RESTORE" | "RESTRICT" | "RETURN" | "REVERT" | "REVOKE" | "RIGHT" | "ROLLBACK" | "ROWCOUNT" | "ROWGUIDCOL" | "ROWS" | "RULE" | "SAVE" | "SCHEMA" | "SCROLL" | "SECOND" | "SECTION" | "SECURITYAUDIT" | "SELECT" | "SEMANTICKEYPHRASETABLE" | "SEMANTICSIMILARITYDETAILSTABLE" | "SEMANTICSIMILARITYTABLE" | "SESSION" | "SESSION_USER" | "SET" | "SETUSER" | "SHUTDOWN" | "SIZE" | "SMALLINT" | "SOME" | "SPACE" | "SQL" | "SQLCA" | "SQLCODE" | "SQLERROR" | "SQLSTATE" | "SQLWARNING" | "STATISTICS" | "SUBSTRING" | "SUM" | "SYSTEM_USER" | "TABLE" | "TABLESAMPLE" | "TEMPORARY" | "TEXTSIZE" | "THEN" | "TIME" | "TIMESTAMP" | "TIMEZONE_HOUR" | "TIMEZONE_MINUTE" | "TO" | "TOP" | "TRAILING" | "TRAN" | "TRANSACTION" | "TRANSLATE" | "TRANSLATION" | "TRIGGER" | "TRIM" | "TRUE" | "TRUNCATE" | "TRY_CONVERT" | "TSEQUAL" | "UNION" | "UNIQUE" | "UNKNOWN" | "UNPIVOT" | "UPDATE" | "UPDATETEXT" | "UPPER" | "USAGE" | "USE" | "USER" | "USING" | "VALUE" | "VALUES" | "VARCHAR" | "VARYING" | "VIEW" | "WAITFOR" | "WHEN" | "WHENEVER" | "WHERE" | "WHILE" | "WITH" | "WITHIN GROUP" | "WORK" | "WRITE" | "WRITETEXT" | "YEAR" | "ZONE"

/*-------------------------------Identificadores:----------------------------------------------------------------------------------------------------------------------------------------
                Un identificador es una secuencia de letras, dígitos y guiones bajos, comenzando con una letra. La longitud máxima de un identificador es de 31 caracteres. 
                Mini-SQL distingue entre mayúsculas y minúsculas (case sensitive).
                        SELECT es una palabra clave pero select es un identificador
                        binky y Binky son dos identificadores distintos.*/
Identificadores = [A-Za-z]([_]|[A-Za-z]|[0-9]|"ñ"|"Ñ")*                         //ERROR_IDENTIFICADOR = (_)([_]|[0-9]|[A-Za-z])+

/*---------------------------------Constantes---------------------------------------------------------------------------------------------------------------------------------------------
                Las constantes booleanas son de tipo bit y pueden guardar valores: 0, 1 o NULL.
                Una constante entera es de tipo int y puede ser expresada en decimal como una secuencia de dígitos decimales (0-9).
                Una constante decimal será de tipo float; siendo una secuencia de dígitos,un punto, seguido de una secuencia de dígitos, o nada. Así, .12 no es una constante tipo double, 
                    pero 12. y 0.12 lo son. Una constante doble puede tener parte exponencial, por ejemplo, 12.2E+2. Para la constante doble en la notación científica el punto decimal es 
                    requerido, el signo del exponente es opcional (si no está especificado, + es asumido), y el E puede ser en mayúscula y minúscula. Entonces, .12E+2 es inválido, 
                    pero 12.E+2 es válido. Ceros al inicio de la mantisa y el exponente son permitidos.
                Una constante string o cadena de caracteres es una secuencia de caracteres encerrada por apóstrofos ‘’. Los strings pueden contener cualquier carácter excepto una línea 
                    nueva o apóstrofo. Una constante string debe comenzar y finalizar en una misma línea, y no puede partirse en líneas múltiples. Ejemplos:
                            ‘Está es una cadena de caracteres que no tiene su apóstrofo
                            Esta no es parte de la cadena de arriba*/
C_Booleanas = "0"|"1"|"NULL"
C_Entera    = [0-9]+                                                            //C_Hexadecimal = 0[xX][0-9a-fA-F]+                                               //"."?[0-9a-fA-F]*
C_Decimal   = [0-9]+ "." [0-9]*                                                 //| "." [0-9]+
            | [0-9]+ "." [0-9]* [eE] [\+|-]? [0-9]+ "."? [0-9]*                 //| "." [0-9]+ [eE] [\+|-]? [0-9]+                                    
//ERROR_DOUBLE= "."[0-9]+ | "."[0-9]+[eE] | "."[0-9]+[eE][0-9]+        //ERROR_DOUBLE    = "."0[xX][0-9a-zA-Z]+ | "."0[0-9a-zA-Z]+[xX] | "."[0-9a-zA-Z]+0[xX] | "."[0-9a-zA-Z]+[xX]0 | "."[xX][0-9a-zA-Z]+0 | "."[xX]0[0-9a-zA-Z]+ | 0[xX][0-9a-zA-Z]+"." | 0[xX]"."[0-9a-zA-Z]+ | 0"."[xX][0-9a-zA-Z]+ | 0"."[0-9a-zA-Z]+[xX] | 0[0-9a-zA-Z]+"."[xX] | 0[0-9a-zA-Z]+[xX]"." | [xX]0"."[0-9a-zA-Z]+ | [xX]0[0-9a-zA-Z]+"." | [xX][0-9a-zA-Z]+0"." | [xX][0-9a-zA-Z]+"."0 | [xX]"."[0-9a-zA-Z]+0 | [xX]"."0[0-9a-zA-Z]+ | [0-9a-zA-Z]+"."0[xX] | [0-9a-zA-Z]+"."[xX]0 | [0-9a-zA-Z]+[xX]"."0 | [0-9a-zA-Z]+[xX]0"." | [0-9a-zA-Z]+0[xX]"." | [0-9a-zA-Z]+0"."[xX] | [0-9a-zA-Z]+"."[0-9a-zA-Z]+[xX] | [0-9a-zA-Z]+"."[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+[xX]"."[0-9a-zA-Z]+ | [1-9a-zA-Z]+[xX][0-9a-zA-Z]+"." | [0-9a-zA-Z]+[xX]"." | [0-9a-zA-Z]+"."[xX] | [xX][0-9a-zA-Z]+"."[0-9a-zA-Z]+ | [xX][0-9a-zA-Z]+"."[0-9a-zA-Z]+ | [xX][0-9a-zA-Z]+"." | "."[0-9a-zA-Z]+[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+"."[xX] | "."[0-9a-zA-Z]+[xX] | [xX]"."[0-9a-zA-Z]+ | [xX][0-9a-zA-Z]+"." | [0-9a-zA-Z]+[xX][0-9a-zA-Z]+"." | "."[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+"."[0-9a-zA-Z]+[xX] | "."[0-9a-zA-Z]+[xX] | [xX][0-9a-zA-Z]+"."[0-9a-zA-Z]+ | [xX]"."[0-9a-zA-Z]+ | [0-9a-zA-Z]+[xX]"."[0-9a-zA-Z]+ | "."[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+"."[xX][0-9a-zA-Z]+ | "."[0-9a-zA-Z]+[xX][0-9a-zA-Z]+



C_String    = (\')[^\n\']*(\')                                                  //ERROR_STRING    = \" (" "|\r|\t|{Identificadores}|{Abcedario}|{Digitos}|[A-Za-z]|{ASCII}|{Operadores}|\')* (\n) 
//------------------------------------------------------------------------------
//------------------------------------Otros-------------------------------------
//------------------------------------------------------------------------------
Abcedario   = ([A-Za-z])*
Digitos     = ([0-9])*
Espacio     = [" "|\n|\r|\t]
ASCII = [A-Za-z _ . ! # $ % & = \? ¡ ¿ @ ´ ¨ \+ < > , - : ; \\ \* \/]

Comentario   = "/*" [^]~ "*/"                                                   //Comentario   = "/*" ({Espacio} | [^.])* "*/"
Comentario2 = "--" [^]~ "\n"                                                    //Comentario2 = "//" ([" "|\r|\t]* | [^.])* "\n"
ERROR_COMENTARIO = "/*"[^\n] 
//------------------------------------------------------------------------------
//---------------------------------Operadores-----------------------------------
//------------------------------------------------------------------------------
Operadores = \+ | - | \* | \/ | % | < | "<=" | > | ">=" | = | "==" | "!=" | "&&" | "||" | "!" | "[" | , | "." | ; | "]" | \( | \) | \{ | \} | "[]" | "()" | "{}" | "@" | "#" | "##"
 
%{
    public int Lineas(java.io.Reader reader) 
    {
        return yyline;
    }
    public int Columnas(java.io.Reader reader) 
    {
        return yycolumn;
    }
%}

%{
    public String Texto;
%}
%%


//------------------------------------------------------------------------------
//------------------------------------Ignorar-----------------------------------
" " {/*Ignore*/}
"\t" {/*Ignore*/}
"\n" {/*Ignore*/}
"\r" {/*Ignore*/}
//"//".* {/*Ignore*/}
{Comentario} {/*Ignore*/}
{Comentario2} {/*Ignore*/}


{ERROR_COMENTARIO} {Texto = yytext(); return ERROR_COMENTARIO;}


//------------------------------------------------------------------------------
//-----------------------------------Retornos-----------------------------------
{P_Reservadas} {Texto = yytext(); return PALABRA_RESERVADA;}

{C_Booleanas} {Texto = yytext(); return BOOLEANO;}
{C_Entera} {Texto = yytext(); return ENTERO;} 
{C_Decimal} {Texto = yytext(); return DECIMAL;}
{C_String} {Texto = yytext(); return CADENA;}

{Operadores} {Texto = yytext(); return OPERADOR;}
{Identificadores} {Texto = yytext(); return IDENTIFICADOR;}

                                                                                /*{ERROR_IDENTIFICADOR}   {Texto = yytext(); return ERROR;}
                                                                                {ERROR_EXPONENTE}       {Texto = yytext(); return ERROR;}
                                                                                {ERROR_DOUBLE}          {Texto = yytext(); return ERROR;}
                                                                                {ERROR_STRING}          {Texto = yytext(); return ERROR;}*/
. {Texto = yytext(); return ERROR;}