PROGRAM posneg;

VAR
 no : INTEGER;

BEGIN
  Write('Enger a number:');
  Readln(no);

  IF (no > 0) THEN
   Writeln('You enter Positive Number')
  ELSE
    IF (no < 0) THEN
     writeln('You enter Negative number')
END