def readInput() {
  def input = []

  new File('input').eachLine { line ->
    input << (line as int)
  }

  return input
}

def increaseCount() {
  int count = 0
  def items = readInput()

  for (int i = 0; i < items.size() - 3; i++) {
    if (items[i+1..i+3].sum() > items[i..i+2].sum()) {
      count++
    }
  }

  return count
}

println increaseCount()
