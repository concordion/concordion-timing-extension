# TimingInfoSpec

The timing info spec will save timing info to file and dyanamically load it to the specification.

## [Example](- "")

Given we have 2 examples. example1 takes 100ms and example2 takes 2s.

Inputing [Andrew2]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

Throwing Error Timer Test: [ ]( - "throwRuntimeException()")

## [Example 2]( - "Expected to Failed c:status=ExpectedToFail")

Inputing [Andrew2]( - "#firstName") into the name field is [valid](- "c:assert-false=checkFirstName(#firstName)").

## [Example 3]( - "Unimplemented c:status=Unimplemented")

Inputing [Andrew]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstNameUnimpl(#firstName)").

Inputing [Andrew2]( - "#firstName") into the name field is [valid](- "c:assert-false=checkFirstNameUnimpl(#firstName)").