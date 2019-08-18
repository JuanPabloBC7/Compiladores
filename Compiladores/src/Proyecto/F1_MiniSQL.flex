package Proyecto;
import static Proyecto.Token.*;
%%
%class F1_MiniSQL
%type Token
%line
%column 

//------------------------------------------------------------------------------
//-----------------------------Palabras Reservadas------------------------------
//------------------------------------------------------------------------------
P_Reservadas= "ABSOLUTE" | "ACTION" | "ADA" | "ADD" | "ALL" | "ALLOCATE" | "ALTER" | "AND" | "ANY" | "ARE" | "AS" | "ASC" | "ASSERTION" | "AT" | "AUTHORIZATION" | "AVG" | "BACKUP" | "BEGIN" | "BETWEEN" | "BIT" | "BIT_LENGTH" | "BOTH" | "BREAK" | "BROWSE" | "BULK" | "BY" | "CASCADE" | "CASCADED" | "CASE" | "CAST" | "CATALOG" | "CHAR" | "CHAR_LENGTH" | "CHARACTER" | "CHARACTER_LENGTH" | "CHECK" | "CHECKPOINT" | "CLOSE" | "CLUSTERED" | "COALESCE" | "COLLATE" | "COLLATION" | "COLUMN" | "COMMIT" | "COMPUTE" | "CONNECT" | "CONNECTION" | "CONSTRAINT" | "CONSTRAINTS" | "CONTAINS" | "CONTAINSTABLE" | "CONTINUE" | "CONVERT" | "CORRESPONDING" | "COUNT" | "CREATE" | "CROSS" | "CURRENT" | "CURRENT_DATE" | "CURRENT_TIME" | "CURRENT_TIMESTAMP" | "CURRENT_USER" | "CURSOR" | "DATABASE" | "DATE" | "DAY" | "DBCC" | "DEALLOCATE" | "DEC" | "DECIMAL" | "DECLARE" | "DEFAULT" | "DEFERRABLE" | "DEFERRED" | "DELETE" | "DENY" | "DESC" | "DESCRIBE" | "DESCRIPTOR" | "DIAGNOSTICS" | "DISCONNECT" | "DISK" | "DISTINCT" | "DISTRIBUTED" | "DOMAIN" | "DOUBLE" | "DROP" | "DUMP" | "ELSE" | "END" | "END-EXEC" | "ERRLVL" | "ESCAPE" | "EXCEPT" | "EXCEPTION" | "EXEC" | "EXECUTE" | "EXISTS" | "EXIT" | "EXTERNAL" | "EXTRACT" | "FALSE" | "FETCH" | "FILE" | "FILLFACTOR" | "FIRST" | "FLOAT" | "FOR" | "FOREIGN" | "FORTRAN" | "FOUND" | "FREETEXT" | "FREETEXTTABLE" | "FROM" | "FULL" | "FUNCTION" | "GET" | "GLOBAL" | "GO" | "GOTO" | "GRANT" | "GROUP" | "HAVING" | "HOLDLOCK" | "HOUR" | "IDENTITY" | "IDENTITY_INSERT" | "IDENTITYCOL" | "IF" | "IMMEDIATE" | "IN" | "INCLUDE" | "INDEX" | "INDICATOR" | "INITIALLY" | "INNER" | "INPUT" | "INSENSITIVE" | "INSERT" | "INT" | "INTEGER" | "INTERSECT" | "INTERVAL" | "INTO" | "IS" | "ISOLATION" | "JOIN" | "KEY" | "KILL" | "LANGUAGE" | "LAST" | "LEADING" | "LEFT" | "LEVEL" | "LIKE" | "LINENO" | "LOAD" | "LOCAL" | "LOWER" | "MATCH" | "MAX" | "MERGE" | "MIN" | "MINUTE" | "MODULE" | "MONTH" | "NAMES" | "NATIONAL" | "NATURAL" | "NCHAR" | "NEXT" | "NO" | "NOCHECK" | "NONCLUSTERED" | "NONE" | "NOT" | "NULL" | "NULLIF" | "NUMERIC" | "OCTET_LENGTH" | "OF" | "OFF" | "OFFSETS" | "ON" | "ONLY" | "OPEN" | "OPENDATASOURCE" | "OPENQUERY" | "OPENROWSET" | "OPENXML" | "OPTION" | "OR" | "ORDER" | "OUTER" | "OUTPUT" | "OVER" | "OVERLAPS" | "PAD" | "PARTIAL" | "PASCAL" | "PERCENT" | "PIVOT" | "PLAN" | "POSITION" | "PRECISION" | "PREPARE" | "PRESERVE" | "PRIMARY" | "PRINT" | "PRIOR" | "PRIVILEGES" | "PROC" | "PROCEDURE" | "PUBLIC" | "RAISERROR" | "READ" | "READTEXT" | "REAL" | "RECONFIGURE" | "REFERENCES" | "RELATIVE" | "REPLICATION" | "RESTORE" | "RESTRICT" | "RETURN" | "REVERT" | "REVOKE" | "RIGHT" | "ROLLBACK" | "ROWCOUNT" | "ROWGUIDCOL" | "ROWS" | "RULE" | "SAVE" | "SCHEMA" | "SCROLL" | "SECOND" | "SECTION" | "SECURITYAUDIT" | "SELECT" | "SEMANTICKEYPHRASETABLE" | "SEMANTICSIMILARITYDETAILSTABLE" | "SEMANTICSIMILARITYTABLE" | "SESSION" | "SESSION_USER" | "SET" | "SETUSER" | "SHUTDOWN" | "SIZE" | "SMALLINT" | "SOME" | "SPACE" | "SQL" | "SQLCA" | "SQLCODE" | "SQLERROR" | "SQLSTATE" | "SQLWARNING" | "STATISTICS" | "SUBSTRING" | "SUM" | "SYSTEM_USER" | "TABLE" | "TABLESAMPLE" | "TEMPORARY" | "TEXTSIZE" | "THEN" | "TIME" | "TIMESTAMP" | "TIMEZONE_HOUR" | "TIMEZONE_MINUTE" | "TO" | "TOP" | "TRAILING" | "TRAN" | "TRANSACTION" | "TRANSLATE" | "TRANSLATION" | "TRIGGER" | "TRIM" | "TRUE" | "TRUNCATE" | "TRY_CONVERT" | "TSEQUAL" | "UNION" | "UNIQUE" | "UNKNOWN" | "UNPIVOT" | "UPDATE" | "UPDATETEXT" | "UPPER" | "USAGE" | "USE" | "USER" | "USING" | "VALUE" | "VALUES" | "VARCHAR" | "VARYING" | "VIEW" | "WAITFOR" | "WHEN" | "WHENEVER" | "WHERE" | "WHILE" | "WITH" | "WITHIN GROUP" | "WORK" | "WRITE" | "WRITETEXT" | "YEAR" | "ZONE"

/*-------------------------------Identificadores:----------------------------------------------------------------------------------------------------------------------------------------
Un identificador es una secuencia de letras, dígitos y guiones bajos, comenzando
con una letra. La longitud máxima de un identificador es de 31 caracteres. 
Mini-SQL distingue entre mayúsculas y minúsculas (case sensitive).
                     SELECT es una palabra clave pero select es un identificador
                     binky y Binky son dos identificadores distintos.*/
Identificadores = [A-Za-z]+([_]|[A-Za-z]|[0-9])*
                                                                                //ERROR_IDENTIFICADOR = ([_]|[0-9])+([_]|[0-9]|[A-Za-z])+

//------------------------------------------------------------------------------
//---------------------------------Constantes-----------------------------------
//------------------------------------------------------------------------------
C_Booleanas = "true"|"false"
C_Entera    = [0-9]+
C_Hexadecimal = 0[xX][0-9a-fA-F]+                                               //"."?[0-9a-fA-F]*
C_Double    = [0-9]+ "." [0-9]* 
            | [0-9]+ "." [0-9]* [eE] [\+|-]? [0-9]+ "."? [0-9]*
            | [0-9]+ [eE] [\+|-]? [0-9]+
                                                                                //ERROR_EXPONENTE = [0-9]+"."[eE] | "."[0-9]+[eE] | "."[eE][0-9]+ | [0-9]+[eE]"." | [eE][0-9]+"." | [eE]"."[0-9]+ | [0-9]+"."[0-9]+[eE] | "."[0-9]+[eE][0-9]+"."[0-9]+ | [0-9]+[eE]"."[0-9]+ | "."[0-9]+[eE][0-9]+ | [eE][0-9]+"."[0-9]+ | "."[0-9]+[eE][0-9]+"."[0-9]+ | "."[0-9]+[eE][0-9]+"." | "."[0-9]+[eE]"."[0-9]+
                                                                                //ERROR_DOUBLE    = "."[0-9]+ | "."0[xX][0-9a-zA-Z]+ | "."0[0-9a-zA-Z]+[xX] | "."[0-9a-zA-Z]+0[xX] | "."[0-9a-zA-Z]+[xX]0 | "."[xX][0-9a-zA-Z]+0 | "."[xX]0[0-9a-zA-Z]+ | 0[xX][0-9a-zA-Z]+"." | 0[xX]"."[0-9a-zA-Z]+ | 0"."[xX][0-9a-zA-Z]+ | 0"."[0-9a-zA-Z]+[xX] | 0[0-9a-zA-Z]+"."[xX] | 0[0-9a-zA-Z]+[xX]"." | [xX]0"."[0-9a-zA-Z]+ | [xX]0[0-9a-zA-Z]+"." | [xX][0-9a-zA-Z]+0"." | [xX][0-9a-zA-Z]+"."0 | [xX]"."[0-9a-zA-Z]+0 | [xX]"."0[0-9a-zA-Z]+ | [0-9a-zA-Z]+"."0[xX] | [0-9a-zA-Z]+"."[xX]0 | [0-9a-zA-Z]+[xX]"."0 | [0-9a-zA-Z]+[xX]0"." | [0-9a-zA-Z]+0[xX]"." | [0-9a-zA-Z]+0"."[xX] | [0-9a-zA-Z]+"."[0-9a-zA-Z]+[xX] | [0-9a-zA-Z]+"."[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+[xX]"."[0-9a-zA-Z]+ | [1-9a-zA-Z]+[xX][0-9a-zA-Z]+"." | [0-9a-zA-Z]+[xX]"." | [0-9a-zA-Z]+"."[xX] | [xX][0-9a-zA-Z]+"."[0-9a-zA-Z]+ | [xX][0-9a-zA-Z]+"."[0-9a-zA-Z]+ | [xX][0-9a-zA-Z]+"." | "."[0-9a-zA-Z]+[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+"."[xX] | "."[0-9a-zA-Z]+[xX] | [xX]"."[0-9a-zA-Z]+ | [xX][0-9a-zA-Z]+"." | [0-9a-zA-Z]+[xX][0-9a-zA-Z]+"." | "."[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+"."[0-9a-zA-Z]+[xX] | "."[0-9a-zA-Z]+[xX] | [xX][0-9a-zA-Z]+"."[0-9a-zA-Z]+ | [xX]"."[0-9a-zA-Z]+ | [0-9a-zA-Z]+[xX]"."[0-9a-zA-Z]+ | "."[xX][0-9a-zA-Z]+ | [0-9a-zA-Z]+"."[xX][0-9a-zA-Z]+ | "."[0-9a-zA-Z]+[xX][0-9a-zA-Z]+



C_String    = (\")[^\n]*(\") 
//ERROR_STRING    = \" (" "|\r|\t|{Identificadores}|{Abcedario}|{Digitos}|[A-Za-z]|{ASCII}|{Operadores}|\')* (\n) 

//------------------------------------------------------------------------------
//------------------------------------Otros-------------------------------------
//------------------------------------------------------------------------------
Abcedario   = ([A-Za-z])*
Digitos     = ([0-9])*
Espacio     = [" "|\n|\r|\t]
ASCII = [A-Za-z _ . ! # $ % & = \? ¡ ¿ @ ´ ¨ \+ < > , - : ; \\ \* \/]

//Comentario   = "/*" ({Espacio} | [^.])* "*/"
Comentario   = "/*" [^]~ "*/"
//Comentario2 = "//" ([" "|\r|\t]* | [^.])* "\n"
Comentario2 = "//" [^]~ "\n"
ERROR_COMENTARIO = "/*" [^*]+ 
//------------------------------------------------------------------------------
//---------------------------------Operadores-----------------------------------
//------------------------------------------------------------------------------
Operadores = \+ | - | \* | \/ | % | < | "<=" | > | ">=" | = | "==" | "!=" | "&&" | "||" | "!" | "[" | , | "." | ; | "]" | \( | \) | \{ | \} | "[]" | "()" | "{}"

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
{ERROR_COMENTARIO}      {Texto = yytext(); return ERROR;}

{Comentario} {/*Ignore*/}
{Comentario2} {/*Ignore*/}
//------------------------------------------------------------------------------
//-----------------------------------Retornos-----------------------------------
{P_Reservadas}    {Texto = yytext(); return PALABRA_RESERVADA;}


{C_Booleanas}   {Texto = yytext(); return BOOLEANO;}
{C_Entera}      {Texto = yytext(); return ENTERO;} 
{C_Hexadecimal} {Texto = yytext(); return HEXADECIMAL;} 
{C_Double}      {Texto = yytext(); return DECIMAL;}
{C_String}      {Texto = yytext(); return CADENA;}

{Operadores} {Texto = yytext(); return OPERADOR;}
{Identificadores} {Texto = yytext(); return IDENTIFICADOR;}

/*{ERROR_IDENTIFICADOR}   {Texto = yytext(); return ERROR;}
{ERROR_EXPONENTE}       {Texto = yytext(); return ERROR;}
{ERROR_DOUBLE}          {Texto = yytext(); return ERROR;}
{ERROR_STRING}          {Texto = yytext(); return ERROR;}*/
. {Texto = yytext(); return ERROR;}