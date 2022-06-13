PROGRAM weew;

VAR qqq : INTEGER; rr : INTEGER;


BEGIN
    qqq := 10;

    rr := 2;
    IF (rr > 2) THEN
        BEGIN
          Write ('Hello world')
        END;
    WHILE (qqq <= 100) DO
        BEGIN
            REPEAT
                rr := rr + 1
            UNTIL (rr > 0)
        END
END