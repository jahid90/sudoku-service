# sudoku-service
A service to solve sudoku puzzles

## Setup

Clone the package and open in IntelliJ.

Running the `main` method in SudokuServiceApplication class starts a local server.

To deploy in a docker container

```bash
$ ./scripts/build-base-docker-image.sh
$ ./scripts/build-app-jar.sh
$ ./scripts/build.sh
$ docker-compose up -d
```

To stop the container
```bash
$ docker-compose down
```
