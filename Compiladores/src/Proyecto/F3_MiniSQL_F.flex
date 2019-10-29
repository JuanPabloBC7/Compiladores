package Proyecto;
import java_cup.runtime.*;
import java.io.Reader;
import static Proyecto.Token.*;
import java_cup.runtime.Symbol;
%%
%class F3_MiniSQL_F
%line
%column
%cup

%{
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value){ 
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
/*-----------------------------Palabras Reservadas----------------------------*/
ABSOLUTE = ( "ABSOLUTE" )
ACTION = ( "ACTION" )
ADA = ( "ADA" )
ADD = ( "ADD" )
ALL = ( "ALL" )
ALLOCATE = ( "ALLOCATE" )
ALTER = ( "ALTER" )
AND = ( "AND" )
ANY = ( "ANY" )
ARE = ( "ARE" )
AS = ( "AS" )
ASC = ( "ASC" )
ASSERTION = ( "ASSERTION" )
AT = ( "AT" )
AUTHORIZATION = ( "AUTHORIZATION" )
AVG = ( "AVG" )
BACKUP = ( "BACKUP" )
BEGIN = ( "BEGIN" )
BETWEEN = ( "BETWEEN" )
BIT = ( "BIT" )
BIT_LENGTH = ( "BIT_LENGTH" )
BOTH = ( "BOTH" )
BREAK = ( "BREAK" )
BROWSE = ( "BROWSE" )
BULK = ( "BULK" )
BY = ( "BY" )
CASCADE = ( "CASCADE" )
CASCADED = ( "CASCADED" )
CASE = ( "CASE" )
CAST = ( "CAST" )
CATALOG = ( "CATALOG" )
CHAR = ( "CHAR" )
CHAR_LENGTH = ( "CHAR_LENGTH" )
CHARACTER = ( "CHARACTER" )
CHARACTER_LENGTH = ( "CHARACTER_LENGTH" )
CHECK = ( "CHECK" )
CHECKPOINT = ( "CHECKPOINT" )
CLOSE = ( "CLOSE" )
CLUSTERED = ( "CLUSTERED" )
COALESCE = ( "COALESCE" )
COLLATE = ( "COLLATE" )
COLLATION = ( "COLLATION" )
COLUMN = ( "COLUMN" )
COMMIT = ( "COMMIT" )
COMPUTE = ( "COMPUTE" )
CONNECT = ( "CONNECT" )
CONNECTION = ( "CONNECTION" )
CONSTRAINT = ( "CONSTRAINT" )
CONSTRAINTS = ( "CONSTRAINTS" )
CONTAINS = ( "CONTAINS" )
CONTAINSTABLE = ( "CONTAINSTABLE" )
CONTINUE = ( "CONTINUE" )
CONVERT = ( "CONVERT" )
CORRESPONDING = ( "CORRESPONDING" )
COUNT = ( "COUNT" )
CREATE = ( "CREATE" )
CROSS = ( "CROSS" )
CURRENT = ( "CURRENT" )
CURRENT_DATE = ( "CURRENT_DATE" )
CURRENT_TIME = ( "CURRENT_TIME" )
CURRENT_TIMESTAMP = ( "CURRENT_TIMESTAMP" )
CURRENT_USER = ( "CURRENT_USER" )
CURSOR = ( "CURSOR" )
DATABASE = ( "DATABASE" )
DATE = ( "DATE" )
DAY = ( "DAY" )
DBCC = ( "DBCC" )
DEALLOCATE = ( "DEALLOCATE" )
DEC = ( "DEC" )
DECIMAL = ( "DECIMAL" )
DECLARE = ( "DECLARE" )
DEFAULT = ( "DEFAULT" )
DEFERRABLE = ( "DEFERRABLE" )
DEFERRED = ( "DEFERRED" )
DELAYED_DURABILITY = ( "DELAYED_DURABILITY" )
DELETE = ( "DELETE" )
DENY = ( "DENY" )
DESC = ( "DESC" )
DESCRIBE = ( "DESCRIBE" )
DESCRIPTOR = ( "DESCRIPTOR" )
DIAGNOSTICS = ( "DIAGNOSTICS" )
DISCONNECT = ( "DISCONNECT" )
DISK = ( "DISK" )
DISTINCT = ( "DISTINCT" )
DISTRIBUTED = ( "DISTRIBUTED" )
DOMAIN = ( "DOMAIN" )
DOUBLE = ( "DOUBLE" )
DROP = ( "DROP" )
DUMP = ( "DUMP" )
DYNAMIC = ( "DYNAMIC" )
ELSE = ( "ELSE" )
END = ( "END" )
END_EXEC = ( "END_EXEC" )
ENCRYPTION = ( "ENCRYPTION" )
ERRLVL = ( "ERRLVL" )
ESCAPE = ( "ESCAPE" )
EXCEPT = ( "EXCEPT" )
EXCEPTION = ( "EXCEPTION" )
EXEC = ( "EXEC" )
EXECUTE = ( "EXECUTE" )
EXISTS = ( "EXISTS" )
EXIT = ( "EXIT" )
EXTERNAL = ( "EXTERNAL" )
EXTRACT = ( "EXTRACT" )
FALSE = ( "FALSE" )
FAST_FORWARD = ( "FAST_FORWARD" )
FETCH = ( "FETCH" )
FILE = ( "FILE" )
FILLFACTOR = ( "FILLFACTOR" )
FIRST = ( "FIRST" )
FLOAT = ( "FLOAT" )
FOR = ( "FOR" )
FOREIGN = ( "FOREIGN" )
FORTRAN = ( "FORTRAN" )
FOUND = ( "FOUND" )
FORWARD_ONLY = ( "FORWARD_ONLY" )
FREETEXT = ( "FREETEXT" )
FREETEXTTABLE = ( "FREETEXTTABLE" )
FROM = ( "FROM" )
FULL = ( "FULL" )
FUNCTION = ( "FUNCTION" )
GET = ( "GET" )
GLOBAL = ( "GLOBAL" )
GO = ( "GO" )
GOTO = ( "GOTO" )
GRANT = ( "GRANT" )
GROUP = ( "GROUP" )
HAVING = ( "HAVING" )
HOLDLOCK = ( "HOLDLOCK" )
HOUR = ( "HOUR" )
IDENTITY = ( "IDENTITY" )
IDENTITY_INSERT = ( "IDENTITY_INSERT" )
IDENTITYCOL = ( "IDENTITYCOL" )
IF = ( "IF" )
IMMEDIATE = ( "IMMEDIATE" )
IN = ( "IN" )
INCLUDE = ( "INCLUDE" )
INDEX = ( "INDEX" )
INDICATOR = ( "INDICATOR" )
INITIALLY = ( "INITIALLY" )
INNER = ( "INNER" )
INPUT = ( "INPUT" )
INSENSITIVE = ( "INSENSITIVE" )
INSERT = ( "INSERT" )
INT = ( "INT" )
INTEGER = ( "INTEGER" )
INTERSECT = ( "INTERSECT" )
INTERVAL = ( "INTERVAL" )
INTO = ( "INTO" )
IS = ( "IS" )
ISOLATION = ( "ISOLATION" )
JOIN = ( "JOIN" )
KEY = ( "KEY" )
KEYSET = ( "KEYSET" )
KILL = ( "KILL" )
LANGUAGE = ( "LANGUAGE" )
LAST = ( "LAST" )
LEADING = ( "LEADING" )
LEFT = ( "LEFT" )
LEVEL = ( "LEVEL" )
LIKE = ( "LIKE" )
LINENO = ( "LINENO" )
LOAD = ( "LOAD" )
LOCAL = ( "LOCAL" )
LOWER = ( "LOWER" )
MATCH = ( "MATCH" )
MAX = ( "MAX" )
MERGE = ( "MERGE" )
MIN = ( "MIN" )
MINUTE = ( "MINUTE" )
MODULE = ( "MODULE" )
MONTH = ( "MONTH" )
NAMES = ( "NAMES" )
NATIONAL = ( "NATIONAL" )
NATURAL = ( "NATURAL" )
NCHAR = ( "NCHAR" )
NEXT = ( "NEXT" )
NO = ( "NO" )
NOCHECK = ( "NOCHECK" )
NONCLUSTERED = ( "NONCLUSTERED" )
NOCLUSTERED = ( "NOCLUSTERED" )
NONE = ( "NONE" )
NOT = ( "NOT" )
NULL = ( "NULL" )
NULLIF = ( "NULLIF" )
NUMERIC = ( "NUMERIC" )
OCTET_LENGTH = ( "OCTET_LENGTH" )
OF = ( "OF" )
OFF = ( "OFF" )
OFFSETS = ( "OFFSETS" )
ON = ( "ON" )
ONLY = ( "ONLY" )
OPEN = ( "OPEN" )
OPENDATASOURCE = ( "OPENDATASOURCE" )
OPENQUERY = ( "OPENQUERY" )
OPENROWSET = ( "OPENROWSET" )
OPENXML = ( "OPENXML" )
OPTION = ( "OPTION" )
OPTIMISTIC = ( "OPTIMISTIC" )
OR = ( "OR" )
ORDER = ( "ORDER" )
OUT = ( "OUT" )
OUTER = ( "OUTER" )
OUTPUT = ( "OUTPUT" )
OVER = ( "OVER" )
OVERLAPS = ( "OVERLAPS" )
PAD = ( "PAD" )
PARTIAL = ( "PARTIAL" )
PASCAL = ( "PASCAL" )
PERCENT = ( "PERCENT" )
PIVOT = ( "PIVOT" )
PLAN = ( "PLAN" )
POSITION = ( "POSITION" )
PRECISION = ( "PRECISION" )
PREPARE = ( "PREPARE" )
PRESERVE = ( "PRESERVE" )
PRIMARY = ( "PRIMARY" )
PRINT = ( "PRINT" )
PRIOR = ( "PRIOR" )
PRIVILEGES = ( "PRIVILEGES" )
PROC = ( "PROC" )
PROCEDURE = ( "PROCEDURE" )
PUBLIC = ( "PUBLIC" )
RAISERROR = ( "RAISERROR" )
READ = ( "READ" )
READ_ONLY = ( "READ_ONLY" )
READTEXT = ( "READTEXT" )
REAL = ( "REAL" )
RECOMPILE = ( "RECOMPILE" )
RECONFIGURE = ( "RECONFIGURE" )
REFERENCES = ( "REFERENCES" )
RELATIVE = ( "RELATIVE" )
REPLICATION = ( "REPLICATION" )
RESTORE = ( "RESTORE" )
RESTRICT = ( "RESTRICT" )
RETURN = ( "RETURN" )
RETURNS = ( "RETURNS" )
REVERT = ( "REVERT" )
REVOKE = ( "REVOKE" )
RIGHT = ( "RIGHT" )
ROLLBACK = ( "ROLLBACK" )
ROWCOUNT = ( "ROWCOUNT" )
ROWGUIDCOL = ( "ROWGUIDCOL" )
ROWS = ( "ROWS" )
RULE = ( "RULE" )
SAVE = ( "SAVE" )
SCHEMA = ( "SCHEMA" )
SCROLL = ( "SCROLL" )
SCROLL_LOCKS = ( "SCROLL_LOCKS" )
SECOND = ( "SECOND" )
SECTION = ( "SECTION" )
SECURITYAUDIT = ( "SECURITYAUDIT" )
SELECT = ( "SELECT" )
SEMANTICKEYPHRASETABLE = ( "SEMANTICKEYPHRASETABLE" )
SEMANTICSIMILARITYDETAIL = ( "SEMANTICSIMILARITYDETAIL" )
SEMANTICSIMILARITYDETAILSTABLE = ( "SEMANTICSIMILARITYDETAILSTABLE" )
SEMANTICSIMILARITYTABLE = ( "SEMANTICSIMILARITYTABLE" )
SESSION = ( "SESSION" )
SESSION_USER = ( "SESSION_USER" )
SET = ( "SET" )
SETUSER = ( "SETUSER" )
SHUTDOWN = ( "SHUTDOWN" )
SIZE = ( "SIZE" )
SMALLINT = ( "SMALLINT" )
SOME = ( "SOME" )
SPACE = ( "SPACE" )
SQL = ( "SQL" )
SQLCA = ( "SQLCA" )
SQLCODE = ( "SQLCODE" )
SQLERROR = ( "SQLERROR" )
SQLSTATE = ( "SQLSTATE" )
SQLWARNING = ( "SQLWARNING" )
STATIC = ( "STATIC" )
STATISTICS = ( "STATISTICS" )
SUBSTRING = ( "SUBSTRING" )
SUM = ( "SUM" )
SYSTEM_USER = ( "SYSTEM_USER" )
TABLE = ( "TABLE" )
TABLESAMPLE = ( "TABLESAMPLE" )
TEMPORARY = ( "TEMPORARY" )
TEXTSIZE = ( "TEXTSIZE" )
THEN = ( "THEN" )
TIME = ( "TIME" )
TIMESTAMP = ( "TIMESTAMP" )
TIMEZONE_HOUR = ( "TIMEZONE_HOUR" )
TIMEZONE_MINUTE = ( "TIMEZONE_MINUTE" )
TO = ( "TO" )
TOP = ( "TOP" )
TRAILING = ( "TRAILING" )
TRAN = ( "TRAN" )
TRANSACTION = ( "TRANSACTION" )
TRANSLATE = ( "TRANSLATE" )
TRANSLATION = ( "TRANSLATION" )
TRIGGER = ( "TRIGGER" )
TRIM = ( "TRIM" )
TRUE = ( "TRUE" )
TRUNCATE = ( "TRUNCATE" )
TRY_CONVERT = ( "TRY_CONVERT" )
TSEQUAL = ( "TSEQUAL" )
TYPE_WARNING = ( "TYPE_WARNING" )
UNION = ( "UNION" )
UNIQUE = ( "UNIQUE" )
UNKNOWN = ( "UNKNOWN" )
UNPIVOT = ( "UNPIVOT" )
UPDATE = ( "UPDATE" )
UPDATETEXT = ( "UPDATETEXT" )
UPPER = ( "UPPER" )
USAGE = ( "USAGE" )
USE = ( "USE" )
USER = ( "USER" )
USING = ( "USING" )
VALUE = ( "VALUE" )
VALUES = ( "VALUES" )
VARCHAR = ( "VARCHAR" )
VARIYING = ( "VARIYING" )
VARYING = ( "VARYING" )
VIEW = ( "VIEW" )
WAITFOR = ( "WAITFOR" )
WHEN = ( "WHEN" )
WHENEVER = ( "WHENEVER" )
WHERE = ( "WHERE" )
WHILE = ( "WHILE" )
WITH = ( "WITH" )
WITHIN_GROUP = ( "WITHIN_GROUP" )
WORK = ( "WORK" )
WRITE = ( "WRITE" )
WRITETEXT = ( "WRITETEXT" )
YEAR = ( "YEAR" )
ZONE = ( "ZONE" )


/*-------------------------------Identificadores:-----------------------------*/
Identificadores = [A-Za-z]([_]|[A-Za-z]|[0-9]|"ñ"|"Ñ")*

/*---------------------------------Constantes---------------------------------*/
C_Booleanas = "0"|"1"|"NULL"
C_Entera    = [0-9]+
C_Decimal   = [0-9]+ "." [0-9]*
            | [0-9]+ "." [0-9]* [eE] [\+|-]? [0-9]+ "."? [0-9]*

C_String    = (\')[^\n\']*(\')

//------------------------------------------------------------------------------
//------------------------------------Otros-------------------------------------
//------------------------------------------------------------------------------
Comentario   = "/*" [^]~ "*/"
Comentario2 = "--" [^]~ "\n"
ERROR_COMENTARIO = "/*"[^\n]
//------------------------------------------------------------------------------
//---------------------------------Operadores-----------------------------------
//------------------------------------------------------------------------------
SUMA = ( "+" )
RESTA = ( "-" )
MULTIPLICACION = ( "*" )
DIVISION = ( "/" )
PORCENTAJE = ( "%" )
MENOR = ( "<" )
MENOR_IGUAL = ( "<=" )
MAYOR = ( ">" )
MAYOR_IGUAL = ( ">=" )
IGUAL = ( "=" )
IGUAL_IGUAL = ( "==" )
DIFERENCIA = ( "!=" )
DIFERENCIA2 = ( "<>" )
YY = ( "&&" )
OO = ( "||" )
ADMIRACION = ( "!" )
CORCHETE_I = ( "[" )
COMA = ( "," )
PUNTO = ( "." )
PUNTOYCOMA = ( ";" )
CORCHETE_D = ( "]" )
PARENTESIS_I = ( "(" )
PARENTESIS_D = ( ")" )
LLAVE_I = ( "{" )
LLAVE_D = ( "}" )
CORCHETES = ( "[]" )
PARENTESIS = ( "()" )
LLAVES = ( "{}" )
ARROBA = ( "@" )
NUMERAL = ( "#" )
NUMERAL_DOBLE = ( "##" )

 
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


{ERROR_COMENTARIO} {return new Symbol(sym.ERROR_COMENTARIO, yycolumn, yyline, yytext());}


//------------------------------------------------------------------------------
//-----------------------------------Retornos-----------------------------------
<YYINITIAL> {ABSOLUTE} {return new Symbol(sym.ABSOLUTE, yycolumn, yyline, yytext());}
<YYINITIAL> {ACTION} {return new Symbol(sym.ACTION, yycolumn, yyline, yytext());}
<YYINITIAL> {ADA} {return new Symbol(sym.ADA, yycolumn, yyline, yytext());}
<YYINITIAL> {ADD} {return new Symbol(sym.ADD, yycolumn, yyline, yytext());}
<YYINITIAL> {ALL} {return new Symbol(sym.ALL, yycolumn, yyline, yytext());}
<YYINITIAL> {ALLOCATE} {return new Symbol(sym.ALLOCATE, yycolumn, yyline, yytext());}
<YYINITIAL> {ALTER} {return new Symbol(sym.ALTER, yycolumn, yyline, yytext());}
<YYINITIAL> {AND} {return new Symbol(sym.AND, yycolumn, yyline, yytext());}
<YYINITIAL> {ANY} {return new Symbol(sym.ANY, yycolumn, yyline, yytext());}
<YYINITIAL> {ARE} {return new Symbol(sym.ARE, yycolumn, yyline, yytext());}
<YYINITIAL> {AS} {return new Symbol(sym.AS, yycolumn, yyline, yytext());}
<YYINITIAL> {ASC} {return new Symbol(sym.ASC, yycolumn, yyline, yytext());}
<YYINITIAL> {ASSERTION} {return new Symbol(sym.ASSERTION, yycolumn, yyline, yytext());}
<YYINITIAL> {AT} {return new Symbol(sym.AT, yycolumn, yyline, yytext());}
<YYINITIAL> {AUTHORIZATION} {return new Symbol(sym.AUTHORIZATION, yycolumn, yyline, yytext());}
<YYINITIAL> {AVG} {return new Symbol(sym.AVG, yycolumn, yyline, yytext());}
<YYINITIAL> {BACKUP} {return new Symbol(sym.BACKUP, yycolumn, yyline, yytext());}
<YYINITIAL> {BEGIN} {return new Symbol(sym.BEGIN, yycolumn, yyline, yytext());}
<YYINITIAL> {BETWEEN} {return new Symbol(sym.BETWEEN, yycolumn, yyline, yytext());}
<YYINITIAL> {BIT} {return new Symbol(sym.BIT, yycolumn, yyline, yytext());}
<YYINITIAL> {BIT_LENGTH} {return new Symbol(sym.BIT_LENGTH, yycolumn, yyline, yytext());}
<YYINITIAL> {BOTH} {return new Symbol(sym.BOTH, yycolumn, yyline, yytext());}
<YYINITIAL> {BREAK} {return new Symbol(sym.BREAK, yycolumn, yyline, yytext());}
<YYINITIAL> {BROWSE} {return new Symbol(sym.BROWSE, yycolumn, yyline, yytext());}
<YYINITIAL> {BULK} {return new Symbol(sym.BULK, yycolumn, yyline, yytext());}
<YYINITIAL> {BY} {return new Symbol(sym.BY, yycolumn, yyline, yytext());}
<YYINITIAL> {CASCADE} {return new Symbol(sym.CASCADE, yycolumn, yyline, yytext());}
<YYINITIAL> {CASCADED} {return new Symbol(sym.CASCADED, yycolumn, yyline, yytext());}
<YYINITIAL> {CASE} {return new Symbol(sym.CASE, yycolumn, yyline, yytext());}
<YYINITIAL> {CAST} {return new Symbol(sym.CAST, yycolumn, yyline, yytext());}
<YYINITIAL> {CATALOG} {return new Symbol(sym.CATALOG, yycolumn, yyline, yytext());}
<YYINITIAL> {CHAR} {return new Symbol(sym.CHAR, yycolumn, yyline, yytext());}
<YYINITIAL> {CHAR_LENGTH} {return new Symbol(sym.CHAR_LENGTH, yycolumn, yyline, yytext());}
<YYINITIAL> {CHARACTER} {return new Symbol(sym.CHARACTER, yycolumn, yyline, yytext());}
<YYINITIAL> {CHARACTER_LENGTH} {return new Symbol(sym.CHARACTER_LENGTH, yycolumn, yyline, yytext());}
<YYINITIAL> {CHECK} {return new Symbol(sym.CHECK, yycolumn, yyline, yytext());}
<YYINITIAL> {CHECKPOINT} {return new Symbol(sym.CHECKPOINT, yycolumn, yyline, yytext());}
<YYINITIAL> {CLOSE} {return new Symbol(sym.CLOSE, yycolumn, yyline, yytext());}
<YYINITIAL> {CLUSTERED} {return new Symbol(sym.CLUSTERED, yycolumn, yyline, yytext());}
<YYINITIAL> {COALESCE} {return new Symbol(sym.COALESCE, yycolumn, yyline, yytext());}
<YYINITIAL> {COLLATE} {return new Symbol(sym.COLLATE, yycolumn, yyline, yytext());}
<YYINITIAL> {COLLATION} {return new Symbol(sym.COLLATION, yycolumn, yyline, yytext());}
<YYINITIAL> {COLUMN} {return new Symbol(sym.COLUMN, yycolumn, yyline, yytext());}
<YYINITIAL> {COMMIT} {return new Symbol(sym.COMMIT, yycolumn, yyline, yytext());}
<YYINITIAL> {COMPUTE} {return new Symbol(sym.COMPUTE, yycolumn, yyline, yytext());}
<YYINITIAL> {CONNECT} {return new Symbol(sym.CONNECT, yycolumn, yyline, yytext());}
<YYINITIAL> {CONNECTION} {return new Symbol(sym.CONNECTION, yycolumn, yyline, yytext());}
<YYINITIAL> {CONSTRAINT} {return new Symbol(sym.CONSTRAINT, yycolumn, yyline, yytext());}
<YYINITIAL> {CONSTRAINTS} {return new Symbol(sym.CONSTRAINTS, yycolumn, yyline, yytext());}
<YYINITIAL> {CONTAINS} {return new Symbol(sym.CONTAINS, yycolumn, yyline, yytext());}
<YYINITIAL> {CONTAINSTABLE} {return new Symbol(sym.CONTAINSTABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {CONTINUE} {return new Symbol(sym.CONTINUE, yycolumn, yyline, yytext());}
<YYINITIAL> {CONVERT} {return new Symbol(sym.CONVERT, yycolumn, yyline, yytext());}
<YYINITIAL> {CORRESPONDING} {return new Symbol(sym.CORRESPONDING, yycolumn, yyline, yytext());}
<YYINITIAL> {COUNT} {return new Symbol(sym.COUNT, yycolumn, yyline, yytext());}
<YYINITIAL> {CREATE} {return new Symbol(sym.CREATE, yycolumn, yyline, yytext());}
<YYINITIAL> {CROSS} {return new Symbol(sym.CROSS, yycolumn, yyline, yytext());}
<YYINITIAL> {CURRENT} {return new Symbol(sym.CURRENT, yycolumn, yyline, yytext());}
<YYINITIAL> {CURRENT_DATE} {return new Symbol(sym.CURRENT_DATE, yycolumn, yyline, yytext());}
<YYINITIAL> {CURRENT_TIME} {return new Symbol(sym.CURRENT_TIME, yycolumn, yyline, yytext());}
<YYINITIAL> {CURRENT_TIMESTAMP} {return new Symbol(sym.CURRENT_TIMESTAMP, yycolumn, yyline, yytext());}
<YYINITIAL> {CURRENT_USER} {return new Symbol(sym.CURRENT_USER, yycolumn, yyline, yytext());}
<YYINITIAL> {CURSOR} {return new Symbol(sym.CURSOR, yycolumn, yyline, yytext());}
<YYINITIAL> {DATABASE} {return new Symbol(sym.DATABASE, yycolumn, yyline, yytext());}
<YYINITIAL> {DATE} {return new Symbol(sym.DATE, yycolumn, yyline, yytext());}
<YYINITIAL> {DAY} {return new Symbol(sym.DAY, yycolumn, yyline, yytext());}
<YYINITIAL> {DBCC} {return new Symbol(sym.DBCC, yycolumn, yyline, yytext());}
<YYINITIAL> {DEALLOCATE} {return new Symbol(sym.DEALLOCATE, yycolumn, yyline, yytext());}
<YYINITIAL> {DEC} {return new Symbol(sym.DEC, yycolumn, yyline, yytext());}
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yycolumn, yyline, yytext());}
<YYINITIAL> {DECLARE} {return new Symbol(sym.DECLARE, yycolumn, yyline, yytext());}
<YYINITIAL> {DEFAULT} {return new Symbol(sym.DEFAULT, yycolumn, yyline, yytext());}
<YYINITIAL> {DEFERRABLE} {return new Symbol(sym.DEFERRABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {DEFERRED} {return new Symbol(sym.DEFERRED, yycolumn, yyline, yytext());}
<YYINITIAL> {DELAYED_DURABILITY} {return new Symbol(sym.DELAYED_DURABILITY, yycolumn, yyline, yytext());}
<YYINITIAL> {DELETE} {return new Symbol(sym.DELETE, yycolumn, yyline, yytext());}
<YYINITIAL> {DENY} {return new Symbol(sym.DENY, yycolumn, yyline, yytext());}
<YYINITIAL> {DESC} {return new Symbol(sym.DESC, yycolumn, yyline, yytext());}
<YYINITIAL> {DESCRIBE} {return new Symbol(sym.DESCRIBE, yycolumn, yyline, yytext());}
<YYINITIAL> {DESCRIPTOR} {return new Symbol(sym.DESCRIPTOR, yycolumn, yyline, yytext());}
<YYINITIAL> {DIAGNOSTICS} {return new Symbol(sym.DIAGNOSTICS, yycolumn, yyline, yytext());}
<YYINITIAL> {DISCONNECT} {return new Symbol(sym.DISCONNECT, yycolumn, yyline, yytext());}
<YYINITIAL> {DISK} {return new Symbol(sym.DISK, yycolumn, yyline, yytext());}
<YYINITIAL> {DISTINCT} {return new Symbol(sym.DISTINCT, yycolumn, yyline, yytext());}
<YYINITIAL> {DISTRIBUTED} {return new Symbol(sym.DISTRIBUTED, yycolumn, yyline, yytext());}
<YYINITIAL> {DOMAIN} {return new Symbol(sym.DOMAIN, yycolumn, yyline, yytext());}
<YYINITIAL> {DOUBLE} {return new Symbol(sym.DOUBLE, yycolumn, yyline, yytext());}
<YYINITIAL> {DROP} {return new Symbol(sym.DROP, yycolumn, yyline, yytext());}
<YYINITIAL> {DUMP} {return new Symbol(sym.DUMP, yycolumn, yyline, yytext());}
<YYINITIAL> {DYNAMIC} {return new Symbol(sym.DYNAMIC, yycolumn, yyline, yytext());}
<YYINITIAL> {ELSE} {return new Symbol(sym.ELSE, yycolumn, yyline, yytext());}
<YYINITIAL> {END} {return new Symbol(sym.END, yycolumn, yyline, yytext());}
<YYINITIAL> {END_EXEC} {return new Symbol(sym.END_EXEC, yycolumn, yyline, yytext());}
<YYINITIAL> {ENCRYPTION} {return new Symbol(sym.ENCRYPTION, yycolumn, yyline, yytext());}
<YYINITIAL> {ERRLVL} {return new Symbol(sym.ERRLVL, yycolumn, yyline, yytext());}
<YYINITIAL> {ESCAPE} {return new Symbol(sym.ESCAPE, yycolumn, yyline, yytext());}
<YYINITIAL> {EXCEPT} {return new Symbol(sym.EXCEPT, yycolumn, yyline, yytext());}
<YYINITIAL> {EXCEPTION} {return new Symbol(sym.EXCEPTION, yycolumn, yyline, yytext());}
<YYINITIAL> {EXEC} {return new Symbol(sym.EXEC, yycolumn, yyline, yytext());}
<YYINITIAL> {EXECUTE} {return new Symbol(sym.EXECUTE, yycolumn, yyline, yytext());}
<YYINITIAL> {EXISTS} {return new Symbol(sym.EXISTS, yycolumn, yyline, yytext());}
<YYINITIAL> {EXIT} {return new Symbol(sym.EXIT, yycolumn, yyline, yytext());}
<YYINITIAL> {EXTERNAL} {return new Symbol(sym.EXTERNAL, yycolumn, yyline, yytext());}
<YYINITIAL> {EXTRACT} {return new Symbol(sym.EXTRACT, yycolumn, yyline, yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yycolumn, yyline, yytext());}
<YYINITIAL> {FAST_FORWARD} {return new Symbol(sym.FAST_FORWARD, yycolumn, yyline, yytext());}
<YYINITIAL> {FETCH} {return new Symbol(sym.FETCH, yycolumn, yyline, yytext());}
<YYINITIAL> {FILE} {return new Symbol(sym.FILE, yycolumn, yyline, yytext());}
<YYINITIAL> {FILLFACTOR} {return new Symbol(sym.FILLFACTOR, yycolumn, yyline, yytext());}
<YYINITIAL> {FIRST} {return new Symbol(sym.FIRST, yycolumn, yyline, yytext());}
<YYINITIAL> {FLOAT} {return new Symbol(sym.FLOAT, yycolumn, yyline, yytext());}
<YYINITIAL> {FOR} {return new Symbol(sym.FOR, yycolumn, yyline, yytext());}
<YYINITIAL> {FOREIGN} {return new Symbol(sym.FOREIGN, yycolumn, yyline, yytext());}
<YYINITIAL> {FORTRAN} {return new Symbol(sym.FORTRAN, yycolumn, yyline, yytext());}
<YYINITIAL> {FOUND} {return new Symbol(sym.FOUND, yycolumn, yyline, yytext());}
<YYINITIAL> {FORWARD_ONLY} {return new Symbol(sym.FORWARD_ONLY, yycolumn, yyline, yytext());}
<YYINITIAL> {FREETEXT} {return new Symbol(sym.FREETEXT, yycolumn, yyline, yytext());}
<YYINITIAL> {FREETEXTTABLE} {return new Symbol(sym.FREETEXTTABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {FROM} {return new Symbol(sym.FROM, yycolumn, yyline, yytext());}
<YYINITIAL> {FULL} {return new Symbol(sym.FULL, yycolumn, yyline, yytext());}
<YYINITIAL> {FUNCTION} {return new Symbol(sym.FUNCTION, yycolumn, yyline, yytext());}
<YYINITIAL> {GET} {return new Symbol(sym.GET, yycolumn, yyline, yytext());}
<YYINITIAL> {GLOBAL} {return new Symbol(sym.GLOBAL, yycolumn, yyline, yytext());}
<YYINITIAL> {GO} {return new Symbol(sym.GO, yycolumn, yyline, yytext());}
<YYINITIAL> {GOTO} {return new Symbol(sym.GOTO, yycolumn, yyline, yytext());}
<YYINITIAL> {GRANT} {return new Symbol(sym.GRANT, yycolumn, yyline, yytext());}
<YYINITIAL> {GROUP} {return new Symbol(sym.GROUP, yycolumn, yyline, yytext());}
<YYINITIAL> {HAVING} {return new Symbol(sym.HAVING, yycolumn, yyline, yytext());}
<YYINITIAL> {HOLDLOCK} {return new Symbol(sym.HOLDLOCK, yycolumn, yyline, yytext());}
<YYINITIAL> {HOUR} {return new Symbol(sym.HOUR, yycolumn, yyline, yytext());}
<YYINITIAL> {IDENTITY} {return new Symbol(sym.IDENTITY, yycolumn, yyline, yytext());}
<YYINITIAL> {IDENTITY_INSERT} {return new Symbol(sym.IDENTITY_INSERT, yycolumn, yyline, yytext());}
<YYINITIAL> {IDENTITYCOL} {return new Symbol(sym.IDENTITYCOL, yycolumn, yyline, yytext());}
<YYINITIAL> {IF} {return new Symbol(sym.IF, yycolumn, yyline, yytext());}
<YYINITIAL> {IMMEDIATE} {return new Symbol(sym.IMMEDIATE, yycolumn, yyline, yytext());}
<YYINITIAL> {IN} {return new Symbol(sym.IN, yycolumn, yyline, yytext());}
<YYINITIAL> {INCLUDE} {return new Symbol(sym.INCLUDE, yycolumn, yyline, yytext());}
<YYINITIAL> {INDEX} {return new Symbol(sym.INDEX, yycolumn, yyline, yytext());}
<YYINITIAL> {INDICATOR} {return new Symbol(sym.INDICATOR, yycolumn, yyline, yytext());}
<YYINITIAL> {INITIALLY} {return new Symbol(sym.INITIALLY, yycolumn, yyline, yytext());}
<YYINITIAL> {INNER} {return new Symbol(sym.INNER, yycolumn, yyline, yytext());}
<YYINITIAL> {INPUT} {return new Symbol(sym.INPUT, yycolumn, yyline, yytext());}
<YYINITIAL> {INSENSITIVE} {return new Symbol(sym.INSENSITIVE, yycolumn, yyline, yytext());}
<YYINITIAL> {INSERT} {return new Symbol(sym.INSERT, yycolumn, yyline, yytext());}
<YYINITIAL> {INT} {return new Symbol(sym.INT, yycolumn, yyline, yytext());}
<YYINITIAL> {INTEGER} {return new Symbol(sym.INTEGER, yycolumn, yyline, yytext());}
<YYINITIAL> {INTERSECT} {return new Symbol(sym.INTERSECT, yycolumn, yyline, yytext());}
<YYINITIAL> {INTERVAL} {return new Symbol(sym.INTERVAL, yycolumn, yyline, yytext());}
<YYINITIAL> {INTO} {return new Symbol(sym.INTO, yycolumn, yyline, yytext());}
<YYINITIAL> {IS} {return new Symbol(sym.IS, yycolumn, yyline, yytext());}
<YYINITIAL> {ISOLATION} {return new Symbol(sym.ISOLATION, yycolumn, yyline, yytext());}
<YYINITIAL> {JOIN} {return new Symbol(sym.JOIN, yycolumn, yyline, yytext());}
<YYINITIAL> {KEY} {return new Symbol(sym.KEY, yycolumn, yyline, yytext());}
<YYINITIAL> {KEYSET} {return new Symbol(sym.KEYSET, yycolumn, yyline, yytext());}
<YYINITIAL> {KILL} {return new Symbol(sym.KILL, yycolumn, yyline, yytext());}
<YYINITIAL> {LANGUAGE} {return new Symbol(sym.LANGUAGE, yycolumn, yyline, yytext());}
<YYINITIAL> {LAST} {return new Symbol(sym.LAST, yycolumn, yyline, yytext());}
<YYINITIAL> {LEADING} {return new Symbol(sym.LEADING, yycolumn, yyline, yytext());}
<YYINITIAL> {LEFT} {return new Symbol(sym.LEFT, yycolumn, yyline, yytext());}
<YYINITIAL> {LEVEL} {return new Symbol(sym.LEVEL, yycolumn, yyline, yytext());}
<YYINITIAL> {LIKE} {return new Symbol(sym.LIKE, yycolumn, yyline, yytext());}
<YYINITIAL> {LINENO} {return new Symbol(sym.LINENO, yycolumn, yyline, yytext());}
<YYINITIAL> {LOAD} {return new Symbol(sym.LOAD, yycolumn, yyline, yytext());}
<YYINITIAL> {LOCAL} {return new Symbol(sym.LOCAL, yycolumn, yyline, yytext());}
<YYINITIAL> {LOWER} {return new Symbol(sym.LOWER, yycolumn, yyline, yytext());}
<YYINITIAL> {MATCH} {return new Symbol(sym.MATCH, yycolumn, yyline, yytext());}
<YYINITIAL> {MAX} {return new Symbol(sym.MAX, yycolumn, yyline, yytext());}
<YYINITIAL> {MERGE} {return new Symbol(sym.MERGE, yycolumn, yyline, yytext());}
<YYINITIAL> {MIN} {return new Symbol(sym.MIN, yycolumn, yyline, yytext());}
<YYINITIAL> {MINUTE} {return new Symbol(sym.MINUTE, yycolumn, yyline, yytext());}
<YYINITIAL> {MODULE} {return new Symbol(sym.MODULE, yycolumn, yyline, yytext());}
<YYINITIAL> {MONTH} {return new Symbol(sym.MONTH, yycolumn, yyline, yytext());}
<YYINITIAL> {NAMES} {return new Symbol(sym.NAMES, yycolumn, yyline, yytext());}
<YYINITIAL> {NATIONAL} {return new Symbol(sym.NATIONAL, yycolumn, yyline, yytext());}
<YYINITIAL> {NATURAL} {return new Symbol(sym.NATURAL, yycolumn, yyline, yytext());}
<YYINITIAL> {NCHAR} {return new Symbol(sym.NCHAR, yycolumn, yyline, yytext());}
<YYINITIAL> {NEXT} {return new Symbol(sym.NEXT, yycolumn, yyline, yytext());}
<YYINITIAL> {NO} {return new Symbol(sym.NO, yycolumn, yyline, yytext());}
<YYINITIAL> {NOCHECK} {return new Symbol(sym.NOCHECK, yycolumn, yyline, yytext());}
<YYINITIAL> {NONCLUSTERED} {return new Symbol(sym.NONCLUSTERED, yycolumn, yyline, yytext());}
<YYINITIAL> {NOCLUSTERED} {return new Symbol(sym.NOCLUSTERED, yycolumn, yyline, yytext());}
<YYINITIAL> {NONE} {return new Symbol(sym.NONE, yycolumn, yyline, yytext());}
<YYINITIAL> {NOT} {return new Symbol(sym.NOT, yycolumn, yyline, yytext());}
<YYINITIAL> {NULL} {return new Symbol(sym.NULL, yycolumn, yyline, yytext());}
<YYINITIAL> {NULLIF} {return new Symbol(sym.NULLIF, yycolumn, yyline, yytext());}
<YYINITIAL> {NUMERIC} {return new Symbol(sym.NUMERIC, yycolumn, yyline, yytext());}
<YYINITIAL> {OCTET_LENGTH} {return new Symbol(sym.OCTET_LENGTH, yycolumn, yyline, yytext());}
<YYINITIAL> {OF} {return new Symbol(sym.OF, yycolumn, yyline, yytext());}
<YYINITIAL> {OFF} {return new Symbol(sym.OFF, yycolumn, yyline, yytext());}
<YYINITIAL> {OFFSETS} {return new Symbol(sym.OFFSETS, yycolumn, yyline, yytext());}
<YYINITIAL> {ON} {return new Symbol(sym.ON, yycolumn, yyline, yytext());}
<YYINITIAL> {ONLY} {return new Symbol(sym.ONLY, yycolumn, yyline, yytext());}
<YYINITIAL> {OPEN} {return new Symbol(sym.OPEN, yycolumn, yyline, yytext());}
<YYINITIAL> {OPENDATASOURCE} {return new Symbol(sym.OPENDATASOURCE, yycolumn, yyline, yytext());}
<YYINITIAL> {OPENQUERY} {return new Symbol(sym.OPENQUERY, yycolumn, yyline, yytext());}
<YYINITIAL> {OPENROWSET} {return new Symbol(sym.OPENROWSET, yycolumn, yyline, yytext());}
<YYINITIAL> {OPENXML} {return new Symbol(sym.OPENXML, yycolumn, yyline, yytext());}
<YYINITIAL> {OPTION} {return new Symbol(sym.OPTION, yycolumn, yyline, yytext());}
<YYINITIAL> {OPTIMISTIC} {return new Symbol(sym.OPTIMISTIC, yycolumn, yyline, yytext());}
<YYINITIAL> {OR} {return new Symbol(sym.OR, yycolumn, yyline, yytext());}
<YYINITIAL> {ORDER} {return new Symbol(sym.ORDER, yycolumn, yyline, yytext());}
<YYINITIAL> {OUT} {return new Symbol(sym.OUT, yycolumn, yyline, yytext());}
<YYINITIAL> {OUTER} {return new Symbol(sym.OUTER, yycolumn, yyline, yytext());}
<YYINITIAL> {OUTPUT} {return new Symbol(sym.OUTPUT, yycolumn, yyline, yytext());}
<YYINITIAL> {OVER} {return new Symbol(sym.OVER, yycolumn, yyline, yytext());}
<YYINITIAL> {OVERLAPS} {return new Symbol(sym.OVERLAPS, yycolumn, yyline, yytext());}
<YYINITIAL> {PAD} {return new Symbol(sym.PAD, yycolumn, yyline, yytext());}
<YYINITIAL> {PARTIAL} {return new Symbol(sym.PARTIAL, yycolumn, yyline, yytext());}
<YYINITIAL> {PASCAL} {return new Symbol(sym.PASCAL, yycolumn, yyline, yytext());}
<YYINITIAL> {PERCENT} {return new Symbol(sym.PERCENT, yycolumn, yyline, yytext());}
<YYINITIAL> {PIVOT} {return new Symbol(sym.PIVOT, yycolumn, yyline, yytext());}
<YYINITIAL> {PLAN} {return new Symbol(sym.PLAN, yycolumn, yyline, yytext());}
<YYINITIAL> {POSITION} {return new Symbol(sym.POSITION, yycolumn, yyline, yytext());}
<YYINITIAL> {PRECISION} {return new Symbol(sym.PRECISION, yycolumn, yyline, yytext());}
<YYINITIAL> {PREPARE} {return new Symbol(sym.PREPARE, yycolumn, yyline, yytext());}
<YYINITIAL> {PRESERVE} {return new Symbol(sym.PRESERVE, yycolumn, yyline, yytext());}
<YYINITIAL> {PRIMARY} {return new Symbol(sym.PRIMARY, yycolumn, yyline, yytext());}
<YYINITIAL> {PRINT} {return new Symbol(sym.PRINT, yycolumn, yyline, yytext());}
<YYINITIAL> {PRIOR} {return new Symbol(sym.PRIOR, yycolumn, yyline, yytext());}
<YYINITIAL> {PRIVILEGES} {return new Symbol(sym.PRIVILEGES, yycolumn, yyline, yytext());}
<YYINITIAL> {PROC} {return new Symbol(sym.PROC, yycolumn, yyline, yytext());}
<YYINITIAL> {PROCEDURE} {return new Symbol(sym.PROCEDURE, yycolumn, yyline, yytext());}
<YYINITIAL> {PUBLIC} {return new Symbol(sym.PUBLIC, yycolumn, yyline, yytext());}
<YYINITIAL> {RAISERROR} {return new Symbol(sym.RAISERROR, yycolumn, yyline, yytext());}
<YYINITIAL> {READ} {return new Symbol(sym.READ, yycolumn, yyline, yytext());}
<YYINITIAL> {READ_ONLY} {return new Symbol(sym.READ_ONLY, yycolumn, yyline, yytext());}
<YYINITIAL> {READTEXT} {return new Symbol(sym.READTEXT, yycolumn, yyline, yytext());}
<YYINITIAL> {REAL} {return new Symbol(sym.REAL, yycolumn, yyline, yytext());}
<YYINITIAL> {RECOMPILE} {return new Symbol(sym.RECOMPILE, yycolumn, yyline, yytext());}
<YYINITIAL> {RECONFIGURE} {return new Symbol(sym.RECONFIGURE, yycolumn, yyline, yytext());}
<YYINITIAL> {REFERENCES} {return new Symbol(sym.REFERENCES, yycolumn, yyline, yytext());}
<YYINITIAL> {RELATIVE} {return new Symbol(sym.RELATIVE, yycolumn, yyline, yytext());}
<YYINITIAL> {REPLICATION} {return new Symbol(sym.REPLICATION, yycolumn, yyline, yytext());}
<YYINITIAL> {RESTORE} {return new Symbol(sym.RESTORE, yycolumn, yyline, yytext());}
<YYINITIAL> {RESTRICT} {return new Symbol(sym.RESTRICT, yycolumn, yyline, yytext());}
<YYINITIAL> {RETURN} {return new Symbol(sym.RETURN, yycolumn, yyline, yytext());}
<YYINITIAL> {RETURNS} {return new Symbol(sym.RETURNS, yycolumn, yyline, yytext());}
<YYINITIAL> {REVERT} {return new Symbol(sym.REVERT, yycolumn, yyline, yytext());}
<YYINITIAL> {REVOKE} {return new Symbol(sym.REVOKE, yycolumn, yyline, yytext());}
<YYINITIAL> {RIGHT} {return new Symbol(sym.RIGHT, yycolumn, yyline, yytext());}
<YYINITIAL> {ROLLBACK} {return new Symbol(sym.ROLLBACK, yycolumn, yyline, yytext());}
<YYINITIAL> {ROWCOUNT} {return new Symbol(sym.ROWCOUNT, yycolumn, yyline, yytext());}
<YYINITIAL> {ROWGUIDCOL} {return new Symbol(sym.ROWGUIDCOL, yycolumn, yyline, yytext());}
<YYINITIAL> {ROWS} {return new Symbol(sym.ROWS, yycolumn, yyline, yytext());}
<YYINITIAL> {RULE} {return new Symbol(sym.RULE, yycolumn, yyline, yytext());}
<YYINITIAL> {SAVE} {return new Symbol(sym.SAVE, yycolumn, yyline, yytext());}
<YYINITIAL> {SCHEMA} {return new Symbol(sym.SCHEMA, yycolumn, yyline, yytext());}
<YYINITIAL> {SCROLL} {return new Symbol(sym.SCROLL, yycolumn, yyline, yytext());}
<YYINITIAL> {SCROLL_LOCKS} {return new Symbol(sym.SCROLL_LOCKS, yycolumn, yyline, yytext());}
<YYINITIAL> {SECOND} {return new Symbol(sym.SECOND, yycolumn, yyline, yytext());}
<YYINITIAL> {SECTION} {return new Symbol(sym.SECTION, yycolumn, yyline, yytext());}
<YYINITIAL> {SECURITYAUDIT} {return new Symbol(sym.SECURITYAUDIT, yycolumn, yyline, yytext());}
<YYINITIAL> {SELECT} {return new Symbol(sym.SELECT, yycolumn, yyline, yytext());}
<YYINITIAL> {SEMANTICKEYPHRASETABLE} {return new Symbol(sym.SEMANTICKEYPHRASETABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {SEMANTICSIMILARITYDETAIL} {return new Symbol(sym.SEMANTICSIMILARITYDETAIL, yycolumn, yyline, yytext());}
<YYINITIAL> {SEMANTICSIMILARITYDETAILSTABLE} {return new Symbol(sym.SEMANTICSIMILARITYDETAILSTABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {SEMANTICSIMILARITYTABLE} {return new Symbol(sym.SEMANTICSIMILARITYTABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {SESSION} {return new Symbol(sym.SESSION, yycolumn, yyline, yytext());}
<YYINITIAL> {SESSION_USER} {return new Symbol(sym.SESSION_USER, yycolumn, yyline, yytext());}
<YYINITIAL> {SET} {return new Symbol(sym.SET, yycolumn, yyline, yytext());}
<YYINITIAL> {SETUSER} {return new Symbol(sym.SETUSER, yycolumn, yyline, yytext());}
<YYINITIAL> {SHUTDOWN} {return new Symbol(sym.SHUTDOWN, yycolumn, yyline, yytext());}
<YYINITIAL> {SIZE} {return new Symbol(sym.SIZE, yycolumn, yyline, yytext());}
<YYINITIAL> {SMALLINT} {return new Symbol(sym.SMALLINT, yycolumn, yyline, yytext());}
<YYINITIAL> {SOME} {return new Symbol(sym.SOME, yycolumn, yyline, yytext());}
<YYINITIAL> {SPACE} {return new Symbol(sym.SPACE, yycolumn, yyline, yytext());}
<YYINITIAL> {SQL} {return new Symbol(sym.SQL, yycolumn, yyline, yytext());}
<YYINITIAL> {SQLCA} {return new Symbol(sym.SQLCA, yycolumn, yyline, yytext());}
<YYINITIAL> {SQLCODE} {return new Symbol(sym.SQLCODE, yycolumn, yyline, yytext());}
<YYINITIAL> {SQLERROR} {return new Symbol(sym.SQLERROR, yycolumn, yyline, yytext());}
<YYINITIAL> {SQLSTATE} {return new Symbol(sym.SQLSTATE, yycolumn, yyline, yytext());}
<YYINITIAL> {SQLWARNING} {return new Symbol(sym.SQLWARNING, yycolumn, yyline, yytext());}
<YYINITIAL> {STATIC} {return new Symbol(sym.STATIC, yycolumn, yyline, yytext());}
<YYINITIAL> {STATISTICS} {return new Symbol(sym.STATISTICS, yycolumn, yyline, yytext());}
<YYINITIAL> {SUBSTRING} {return new Symbol(sym.SUBSTRING, yycolumn, yyline, yytext());}
<YYINITIAL> {SUM} {return new Symbol(sym.SUM, yycolumn, yyline, yytext());}
<YYINITIAL> {SYSTEM_USER} {return new Symbol(sym.SYSTEM_USER, yycolumn, yyline, yytext());}
<YYINITIAL> {TABLE} {return new Symbol(sym.TABLE, yycolumn, yyline, yytext());}
<YYINITIAL> {TABLESAMPLE} {return new Symbol(sym.TABLESAMPLE, yycolumn, yyline, yytext());}
<YYINITIAL> {TEMPORARY} {return new Symbol(sym.TEMPORARY, yycolumn, yyline, yytext());}
<YYINITIAL> {TEXTSIZE} {return new Symbol(sym.TEXTSIZE, yycolumn, yyline, yytext());}
<YYINITIAL> {THEN} {return new Symbol(sym.THEN, yycolumn, yyline, yytext());}
<YYINITIAL> {TIME} {return new Symbol(sym.TIME, yycolumn, yyline, yytext());}
<YYINITIAL> {TIMESTAMP} {return new Symbol(sym.TIMESTAMP, yycolumn, yyline, yytext());}
<YYINITIAL> {TIMEZONE_HOUR} {return new Symbol(sym.TIMEZONE_HOUR, yycolumn, yyline, yytext());}
<YYINITIAL> {TIMEZONE_MINUTE} {return new Symbol(sym.TIMEZONE_MINUTE, yycolumn, yyline, yytext());}
<YYINITIAL> {TO} {return new Symbol(sym.TO, yycolumn, yyline, yytext());}
<YYINITIAL> {TOP} {return new Symbol(sym.TOP, yycolumn, yyline, yytext());}
<YYINITIAL> {TRAILING} {return new Symbol(sym.TRAILING, yycolumn, yyline, yytext());}
<YYINITIAL> {TRAN} {return new Symbol(sym.TRAN, yycolumn, yyline, yytext());}
<YYINITIAL> {TRANSACTION} {return new Symbol(sym.TRANSACTION, yycolumn, yyline, yytext());}
<YYINITIAL> {TRANSLATE} {return new Symbol(sym.TRANSLATE, yycolumn, yyline, yytext());}
<YYINITIAL> {TRANSLATION} {return new Symbol(sym.TRANSLATION, yycolumn, yyline, yytext());}
<YYINITIAL> {TRIGGER} {return new Symbol(sym.TRIGGER, yycolumn, yyline, yytext());}
<YYINITIAL> {TRIM} {return new Symbol(sym.TRIM, yycolumn, yyline, yytext());}
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yycolumn, yyline, yytext());}
<YYINITIAL> {TRUNCATE} {return new Symbol(sym.TRUNCATE, yycolumn, yyline, yytext());}
<YYINITIAL> {TRY_CONVERT} {return new Symbol(sym.TRY_CONVERT, yycolumn, yyline, yytext());}
<YYINITIAL> {TSEQUAL} {return new Symbol(sym.TSEQUAL, yycolumn, yyline, yytext());}
<YYINITIAL> {TYPE_WARNING} {return new Symbol(sym.TYPE_WARNING, yycolumn, yyline, yytext());}
<YYINITIAL> {UNION} {return new Symbol(sym.UNION, yycolumn, yyline, yytext());}
<YYINITIAL> {UNIQUE} {return new Symbol(sym.UNIQUE, yycolumn, yyline, yytext());}
<YYINITIAL> {UNKNOWN} {return new Symbol(sym.UNKNOWN, yycolumn, yyline, yytext());}
<YYINITIAL> {UNPIVOT} {return new Symbol(sym.UNPIVOT, yycolumn, yyline, yytext());}
<YYINITIAL> {UPDATE} {return new Symbol(sym.UPDATE, yycolumn, yyline, yytext());}
<YYINITIAL> {UPDATETEXT} {return new Symbol(sym.UPDATETEXT, yycolumn, yyline, yytext());}
<YYINITIAL> {UPPER} {return new Symbol(sym.UPPER, yycolumn, yyline, yytext());}
<YYINITIAL> {USAGE} {return new Symbol(sym.USAGE, yycolumn, yyline, yytext());}
<YYINITIAL> {USE} {return new Symbol(sym.USE, yycolumn, yyline, yytext());}
<YYINITIAL> {USER} {return new Symbol(sym.USER, yycolumn, yyline, yytext());}
<YYINITIAL> {USING} {return new Symbol(sym.USING, yycolumn, yyline, yytext());}
<YYINITIAL> {VALUE} {return new Symbol(sym.VALUE, yycolumn, yyline, yytext());}
<YYINITIAL> {VALUES} {return new Symbol(sym.VALUES, yycolumn, yyline, yytext());}
<YYINITIAL> {VARCHAR} {return new Symbol(sym.VARCHAR, yycolumn, yyline, yytext());}
<YYINITIAL> {VARIYING} {return new Symbol(sym.VARIYING, yycolumn, yyline, yytext());}
<YYINITIAL> {VARYING} {return new Symbol(sym.VARYING, yycolumn, yyline, yytext());}
<YYINITIAL> {VIEW} {return new Symbol(sym.VIEW, yycolumn, yyline, yytext());}
<YYINITIAL> {WAITFOR} {return new Symbol(sym.WAITFOR, yycolumn, yyline, yytext());}
<YYINITIAL> {WHEN} {return new Symbol(sym.WHEN, yycolumn, yyline, yytext());}
<YYINITIAL> {WHENEVER} {return new Symbol(sym.WHENEVER, yycolumn, yyline, yytext());}
<YYINITIAL> {WHERE} {return new Symbol(sym.WHERE, yycolumn, yyline, yytext());}
<YYINITIAL> {WHILE} {return new Symbol(sym.WHILE, yycolumn, yyline, yytext());}
<YYINITIAL> {WITH} {return new Symbol(sym.WITH, yycolumn, yyline, yytext());}
<YYINITIAL> {WITHIN_GROUP} {return new Symbol(sym.WITHIN_GROUP, yycolumn, yyline, yytext());}
<YYINITIAL> {WORK} {return new Symbol(sym.WORK, yycolumn, yyline, yytext());}
<YYINITIAL> {WRITE} {return new Symbol(sym.WRITE, yycolumn, yyline, yytext());}
<YYINITIAL> {WRITETEXT} {return new Symbol(sym.WRITETEXT, yycolumn, yyline, yytext());}
<YYINITIAL> {YEAR} {return new Symbol(sym.YEAR, yycolumn, yyline, yytext());}
<YYINITIAL> {ZONE} {return new Symbol(sym.ZONE, yycolumn, yyline, yytext());}






{C_Booleanas} {return new Symbol(sym.BOOLEANO, yycolumn, yyline, yytext());}
{C_Entera} {return new Symbol(sym.ENTERO, yycolumn, yyline, yytext());} 
{C_Decimal} {return new Symbol(sym.DECIMALES, yycolumn, yyline, yytext());}
{C_String} {return new Symbol(sym.CADENA, yycolumn, yyline, yytext());}




{SUMA} {return new Symbol(sym.SUMA, yycolumn, yyline, yytext());}
{RESTA} {return new Symbol(sym.RESTA, yycolumn, yyline, yytext());}
{MULTIPLICACION} {return new Symbol(sym.MULTIPLICACION, yycolumn, yyline, yytext());}
{DIVISION} {return new Symbol(sym.DIVISION, yycolumn, yyline, yytext());}
{PORCENTAJE} {return new Symbol(sym.PORCENTAJE, yycolumn, yyline, yytext());}
{MENOR} {return new Symbol(sym.MENOR, yycolumn, yyline, yytext());}
{MENOR_IGUAL} {return new Symbol(sym.MENOR_IGUAL, yycolumn, yyline, yytext());}
{MAYOR} {return new Symbol(sym.MAYOR, yycolumn, yyline, yytext());}
{MAYOR_IGUAL} {return new Symbol(sym.MAYOR_IGUAL, yycolumn, yyline, yytext());}
{IGUAL} {return new Symbol(sym.IGUAL, yycolumn, yyline, yytext());}
{IGUAL_IGUAL} {return new Symbol(sym.IGUAL_IGUAL, yycolumn, yyline, yytext());}
{DIFERENCIA} {return new Symbol(sym.DIFERENCIA, yycolumn, yyline, yytext());}
{YY} {return new Symbol(sym.YY, yycolumn, yyline, yytext());}
{OO} {return new Symbol(sym.OO, yycolumn, yyline, yytext());}
{ADMIRACION} {return new Symbol(sym.ADMIRACION, yycolumn, yyline, yytext());}
{CORCHETE_I} {return new Symbol(sym.CORCHETE_I, yycolumn, yyline, yytext());}
{COMA} {return new Symbol(sym.COMA, yycolumn, yyline, yytext());}
{PUNTO} {return new Symbol(sym.PUNTO, yycolumn, yyline, yytext());}
{PUNTOYCOMA} {return new Symbol(sym.PUNTOYCOMA, yycolumn, yyline, yytext());}
{CORCHETE_D} {return new Symbol(sym.CORCHETE_D, yycolumn, yyline, yytext());}
{PARENTESIS_I} {return new Symbol(sym.PARENTESIS_I, yycolumn, yyline, yytext());}
{PARENTESIS_D} {return new Symbol(sym.PARENTESIS_D, yycolumn, yyline, yytext());}
{LLAVE_I} {return new Symbol(sym.LLAVE_I, yycolumn, yyline, yytext());}
{LLAVE_D} {return new Symbol(sym.LLAVE_D, yycolumn, yyline, yytext());}
{CORCHETES} {return new Symbol(sym.CORCHETES, yycolumn, yyline, yytext());}
{PARENTESIS} {return new Symbol(sym.PARENTESIS, yycolumn, yyline, yytext());}
{LLAVES} {return new Symbol(sym.LLAVES, yycolumn, yyline, yytext());}
{ARROBA} {return new Symbol(sym.ARROBA, yycolumn, yyline, yytext());}
{NUMERAL} {return new Symbol(sym.NUMERAL, yycolumn, yyline, yytext());}
{NUMERAL_DOBLE} {return new Symbol(sym.NUMERAL_DOBLE, yycolumn, yyline, yytext());}
{DIFERENCIA2} {return new Symbol(sym.DIFERENCIA2, yycolumn, yyline, yytext());}




{Identificadores} {return new Symbol(sym.IDENTIFICADOR, yycolumn, yyline, yytext());}

. {return new Symbol(sym.ERROR, yycolumn, yyline, yytext());}