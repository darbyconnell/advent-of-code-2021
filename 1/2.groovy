def readInput() {
  def input = []

  new File('input').eachLine { line ->
    line = line as int
    input << line
  }

  return input
}

def increaseCount() {
  int count = 0
  def items = readInput()

  for (int i = 0; i < items.size() - 3; i++) {
    int currentSet = items[i] + items[i+1] + items[i+2]
    int nextSet = items[i+1] + items[i+2] + items[i+3]

    if (nextSet > currentSet) {
      count++
    }
  }

  return count
}

println increaseCount()
