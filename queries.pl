
/* Get all variable types and their declaration lines, and return the count */
?- findall(Type, variableCreated(Line, Type, Name), VariableTypes),
	findall(Line, variableCreated(Line, Type, Name), VariableLines),
	length(VariableLines, VariableCount).
/* Then do the same for just ints */
?- findall(Line, variableCreated(Line, int, Name), IntLines),
	length(IntLines, IntCount).
/* ...and Strings */
?- findall(Line, variableCreated(Line, string, Name), StringLines),
	length(StringLines, StringCount).
/* Then, can compare the number of each variable type */


/* Get all function types and their starting lines, and return the count */
?- findall(Type, functionHeader(Line, Security, Type, Name), FunctionTypes),
	findall(Line, functionHeader(Line, Security, Type, Name), FunctionLines),
	length(FunctionLines, FunctionCount).
/* This has potential too... maybe compare return types to variable declarations? */
/* NOTE: Main shows a return type of static rather than void thanks to my logic */


/* Get all of the return statements and compare to function counts */
?- findall(Line, controlStatement(Line, return), ReturnLines),
	length(ReturnLines, ReturnCount).
/* Can compare to the function count for verification */
/* NOTE: Number is off because Boolean functions have multiple return statements */


/* How many print statements are there? */
?- findall(Line, controlStatement(Line, print), PrintLines),
	length(PrintLines, PrintCount).
/* Maybe expand this to count per function? */


/* How many for loops are there? */
?- findall(Line, loopStart(Line, for), ForLines),
	length(ForLines, ForCount).
/* How many while loops are there? */
?- findall(Line, loopStart(Line, while), WhileLines),
	length(WhileLines, WhileCount).
/* Can compare for loop use vs while loop use */


/* How many if statements are there? */
?- findall(Line, conditionalCheck(Line, if), IfLines),
	length(IfLines, IfCount).
/* How many else statements are there? */
?- findall(Line, conditionalCheck(Line, else), ElseLines),
	length(ElseLines, ElseCount).
/* Can see how many ifs are standalone vs with an else/else if condition */

