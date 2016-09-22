## Banking Customer Form

This specification uses validation checks for a bank customer form. We will add further specifications
as a running example for the rest of this project.

### [Validate First Name](- "example 1")

Inputing [Andrew]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

Inputing [David]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

Inputing [Steven]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

Inputing [Stephen]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

Inputing [12345]( - "#firstName") into the name field is [valid](- "c:assert-false=checkFirstName(#firstName)").

Inputing Empty String into the name field is [invalid](- "c:assert-false=checkFirstNameAsEmptyString()").

Inputing null into the name field is [invalid](- "c:assert-false=checkFirstNameAsNull()").

Inputing [Pr@sh@nt]( - "#firstName") into the name field is [invalid](- "c:assert-false=checkFirstName(#firstName)").


### [Validate email](- "example 2")

Inputing [foooooooooo]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

Inputing [fo@ooooooo@oo@]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

Inputing [joe@gmail.com]( - "#email") is [valid](- "c:assert-true=checkEmail(#email)").

Inputing [joe@]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

Inputing [@gmail]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

Inputing [joe@#gmail]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

Inputing [jAE$#AAA@#$RF]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

Inputing [joe@@mail.com]( - "#email") is [invalid](- "c:assert-false=checkEmail(#email)").

### [Validate date format](- "example 3")

Inputting [12-12-2012]( - "#date") is [valid](- "c:assert-true=checkDateFormat(#date)").

Inputting [2SW#$#RFER$ES]( - "#date") is [invalid](- "c:assert-false=checkDateFormat(#date)").

Inputting [01-01-1999]( - "#date") is [valid](- "c:assert-true=checkDateFormat(#date)").

Inputting [01-01-01-2011]( - "#date") is [invalid](- "c:assert-false=checkDateFormat(#date)").

Inputting [03-11-2011]( - "#date") is [valid](- "c:assert-true=checkDateFormat(#date)").

Inputting [2012-15]( - "#date") is [invalid](- "c:assert-false=checkDateFormat(#date)").

Inputting [2-Jan-2012]( - "#date") is [invalid](- "c:assert-false=checkDateFormat(#date)").

### [Validate postcode](- "example 4")

Inputting [6011]( - "#input") is [valid](- "c:assert-true=checkValidPostCode(#input)").

Inputting [1011]( - "#input") is [valid](- "c:assert-true=checkValidPostCode(#input)").

Inputting [9980]( - "#input") is [valid](- "c:assert-true=checkValidPostCode(#input)").

Inputting [45615611]( - "#input") is [invalid](- "c:assert-false=checkValidPostCode(#input)").

Inputting [15500]( - "#input") is [invalid](- "c:assert-false=checkValidPostCode(#input)").
