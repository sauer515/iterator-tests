# Iterator Tests Project

This project contains JUnit tests for various behaviors of Java `Iterator` implementations. The tests cover different scenarios and edge cases to ensure the correct functionality of the `Iterator` methods: `hasNext()`, `next()`, and `remove()`.

## Project Structure

- `src/IteratorTests.java`: Contains the JUnit test cases for the `Iterator` methods.
- `inputDomainModel.md`: Provides the input parameter characteristics, partitions, base cases, and test requirements for the `Iterator` methods.

## Test Cases

### `hasNext()`

- **Base Case**: Verifies that `hasNext()` returns `true` when the iterator has more elements.
- **Empty List**: Verifies that `hasNext()` returns `false` when the iterator has no more elements.
- **Concurrent Modification**: Verifies that `hasNext()` throws `ConcurrentModificationException` when the underlying collection is modified.

### `next()`

- **Base Case**: Verifies that `next()` returns the next element in the iteration.
- **No Such Element**: Verifies that `next()` throws `NoSuchElementException` when there are no more elements.
- **Concurrent Modification**: Verifies that `next()` throws `ConcurrentModificationException` when the underlying collection is modified.

### `remove()`

- **Base Case**: Verifies that `remove()` removes the last element returned by the iterator.
- **Illegal State**: Verifies that `remove()` throws `IllegalStateException` if `next()` has not been called before `remove()`.
- **Unsupported Operation**: Verifies that `remove()` throws `UnsupportedOperationException` if the `remove` operation is not supported by the iterator.
- **Concurrent Modification**: Verifies that `remove()` throws `ConcurrentModificationException` when the underlying collection is modified.

## Running the Tests

To run the tests, use the following command:

```sh
mvn test
```

This will execute all the JUnit test cases in the project.

## Requirements

- Java 8 or higher
- Maven 3.6 or higher

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
