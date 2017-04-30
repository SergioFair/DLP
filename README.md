# DLP

## To repair:
- Indexing with field access

## Ampliations:
- Increment
- Decrement
- Arithmetical assignment { +=, -=, *=, /= }
- For statement
- Do while

## To do:
1) **Allow structs and arrays to be passed as parameters**
2) **Parameter passing with ref keyword.**
3) **Switch case.**
4) **Potential operator.**
5) **Multiple comparison (<< or >>)**

1. The arrays have to share the same type and have the same size as the referenced one and the struct has to have the same fields as the referenced one.
2. I need a new class (Reference) that implements Expression and also stores an Expression which has to be lvalue (be careful with indexing). It cannot be called outside of an invocation. I have to change the parser also to allow the passed parameters to be instances of Reference class.
3. The Switch class needs an Expression that has to be lvalue, then a List of Cases (this is an approach of the Composite desing pattern), and an optional last one (default, have to think about it). Each Case needs an expression and a set of statements being the last of them "break".
4. Is an arithmetic operation, with an operator and two expressions. Have to be careful with the right expression because we need its value and if we have an invocation or something like that we will have to get the value. The idea is to use a loop to multiply the left expression by itself as many times as the value of the right expression. We have to evaluate the right expression just once and duplicate it on each iteration (because we need one as a reference and another one to check the logical operation).
5. Allows the comparison of multiple expressions without needing to use the logical operators. Whenever a comparison is false it stops checking.
