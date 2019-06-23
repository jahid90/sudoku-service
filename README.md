# sudoku-service
A service to solve sudoku puzzle

## Setup

Clone the package and open in IntelliJ.

Running the `main` method in SudokuServiceApplication class starts a local server.

To deploy in a docker container

```bash
$ ./bin/build-base-docker-image.sh
$ ./bin/build-app-jar.sh
$ sudo docker-compose up -d
```

To stop the container
```bash
sudo docker-compose down
```
