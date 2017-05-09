# DLP

## Ampliations:
- Increment
- Decrement
- Arithmetical assignment { +=, -=, *=, /= }
- For statement
- Do while
- Multiple assignment
- Ternary operator
- Switch Case
- Allow structs and arrays to be passed as parameters

## To do:
1) **Parameter passing with ref keyword.**
2) **Potential operator.**?
3) **Multiple comparison (<< or >>)**
4) **Allow variable assignment on declaration**

1. I need a new class (Reference) that implements Expression and also stores an Expression which has to be lvalue (be careful with indexing). It cannot be called outside of an invocation. I have to change the parser also to allow the passed parameters to be instances of Reference class.
2. A power has to be converted into a function (power(left,right)) in order to store a reference to both expression
3. Allows the comparison of multiple expressions without needing to use the logical operators. Whenever a comparison is false it stops checking.
