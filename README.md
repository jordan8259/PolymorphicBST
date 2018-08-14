# PolymorphicBST

## Objective
The objective of this project was to implement a polymorphic binary search tree. This project was designed to help develop my skills at recursion, polymorphism, and testing with JUnit.

## Design notes
The insert and delete methods on `Tree` objects return references to `Tree` objects. In many cases, these functions may return a reference to the **`this`** object. However, in some cases they can't. For example, `EmptyTree.getInstance.insert("a", "1")` has to return an instance of an `NonEmptyTree` object.
