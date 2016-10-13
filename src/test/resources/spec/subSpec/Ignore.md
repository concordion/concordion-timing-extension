# IgnoreSpec




## [ExpectedFail]( - "Expected to Failed c:status=ExpectedToFail")

Inputing [Andrew2]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

## [Unimplemented]( - "Unimplemented c:status=Unimplemented")

Inputing [Andrew]( - "#firstName") into the name field is true.

Inputing [Andrew2]( - "#firstName") into the name field is false.


## [TableExample](- "")


|[@Example Name](- "#z=add(#x, #y)") | [Number 1](- "#x")|[Number 2](- "#y")|[Result](- "?=#z")|
| --------------------------         | ----------------: | ---------------: | ---------------: |
| Positive numbers                   |                  1|                 0|                 1|
| Negative numbers                   |                  1|                -3|                -2|
| Positive numbers2                  |                  1|                 1|                 2|
| Negative numbers2                  |                  1|                -2|                -1|

