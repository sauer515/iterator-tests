### Iterator: Input parameter and characteristics

| Method name | Parameters     | Return Types | Possible Values | Exceptions                       | Characteristic ID | Characteristics                             | Covered by |
|-------------|----------------|--------------|-----------------|----------------------------------|-------------------|---------------------------------------------|------------|
| hasNext()   | Iterator state | boolean      | true, false     |                                  | C1                | Iterator has next element                   |            |
|             |                |              |                 | ConcurrentModificationException  |                   |                                             | C5         |
| next()      | Iterator state | Object       | Object, null    |                                  | C2                | Iterator returns a not-null next element    |            |
|             |                |              |                 | NoSuchElementException           |                   |                                             | C1         |
|             |                |              |                 | ConcurrentModificationException  |                   |                                             | C5         |
| remove()    | Iterator state | void         |                 | IllegalStateException            | C3                | remove() constraint is satisfied            |            |
|             |                |              |                 | UnsupportedOperationException    | C4                | remove() is supported                       |            |
|             |                |              |                 | ConcurrentModificationException  | C5                | Collection unmodified while iterator in use |            |


### Partitions and base cases
| Characteristic ID | hasNext() | next() | remove() | Partition      | Base Case |
|-------------------|-----------|--------|----------|----------------|-----------|
| C1                | X         | X      | X        | {true, false}  | true      |
| C2                |           | X      | X        | {true, false}  | true      |
| C3                |           |        | X        | {true, false}  | true      |
| C4                |           |        | X        | {true, false}  | true      |
| C5                | X         | X      | X        | {true, false}  | true      |

### Test requirements
| Method    | Characteristics    | Test Requirements                              | Infeasible Test Requirements | Revised Test Requirements | # of Test Requirements |
|-----------|--------------------|------------------------------------------------|------------------------------|---------------------------|------------------------|
| hasNext() | C1, C5             | {**TT**, FT, TF}                               | 0                            |                           | 3                      |
| next()    | C2, C3, C5         | {**TTT**, FTT, TFT, TTF}                       | FTT                          | FTT -> FFT                | 4                      |
| remove()  | C1, C2, C3, C4, C5 | {**TTTTT**, FTTTT, TFTTT, TTFTT, TTTFT, TTTTF} | FTTTT                        | FTTTT -> FFTTT            | 6                      |
