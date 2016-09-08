# TimingInfoSpec

The timing info spec will save timing info to file and dyanamically load it to the specification.

## [Example](- "")

Given we have 2 examples. example1 takes 100ms and example2 takes 2s.

Inputing [Andrew]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

The output will annotate each example, so that example1 displays 100ms and example2 displays 2s.