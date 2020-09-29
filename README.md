# sudoku-service

A service to solve sudoku puzzles. To solve a puzzle, it can be sent in the body of a POST request to the /solve endpoint.
```
$ cat puzzle.json
{
  "puzzle": "020004300900020008000609050000000001072503680600000000080205000100090003009800060"
}
$ curl -X POST -H "Content-Type: application/json" -d "@puzzle.json" localhost:8080/solve
```

Note: The `0`s represent blanks.

## Development

### Clone the repository locally
```
$ cd /workspace
$ git clone git@github.com:jahid90/sudoku-service.git
```

### To build and run the tests after any change, run
```
$ npm run build
$ npm run test
```

### To start a local server, run
```
$ npm run start
```

### To build a docker image, run
```
$ npm run docker:build
```

See `package.json` for other available commands.
