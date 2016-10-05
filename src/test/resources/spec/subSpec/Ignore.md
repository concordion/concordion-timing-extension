# IgnoreSpec




## [ExpectedFail]( - "Expected to Failed c:status=ExpectedToFail")

Inputing [Andrew2]( - "#firstName") into the name field is [valid](- "c:assert-true=checkFirstName(#firstName)").

## [Unimplemented]( - "Unimplemented c:status=Unimplemented")

Inputing [Andrew]( - "#firstName") into the name field is true.

Inputing [Andrew2]( - "#firstName") into the name field is false.


## [TableExample](- "")


|[_add_](- "#z=add(#x, #y)") [-](- "c:example=") | [Number 1](- "#x")|[Number 2](- "#y")|[Result](- "?=#z")|
| --------------------------                           | ----------------: | ---------------: | ---------------: |
| Positive numbers                                     |                  1|                 0|                 1|
| Negative numbers                                     |                  1|                -3|                -2|
| Positive numbers2                                     |                  2|                 0|                 2|
| Negative numbers2                                     |                  1|                -2|                -1|


|[_add2_](- "#z2=add(#x2, #y2)") [-2](- "c:example=poi") | [Number 12](- "#x2")|[Number 22](- "#y2")|[Result2](- "?=#z2")|
| --------------------------                           | ----------------: | ---------------: | ---------------: |
| Positive numbers23                                     |                  1|                 0|                 1|
| Negative numbers23                                     |                  1|                -3|                -2|
| Positive numbers22                                     |                  2|                 0|                 2|
| Negative numbers22                                     |                  1|                -2|                -1|

