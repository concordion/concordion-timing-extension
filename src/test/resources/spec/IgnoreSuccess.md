# TimingInfoSpec

The this spec will return as a ignore if ran though a run command.

## [Success](- "")

This will succeed.

Inputing [Andrew]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").


## [Expected To Fail]( - "Expected to Failed c:status=ExpectedToFail")

This will fail and be ignored.

Inputing [Andrew2]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").