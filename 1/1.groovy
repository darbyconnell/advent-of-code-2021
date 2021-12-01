def readInput() {
  def input = []

  new File('input').eachLine { line ->
    input << line
  }

  return input
}

def increaseCount() {
  def count = 0
  def stack = readInput().reverse() as Stack

  def current = stack.pop() as int
  def nextItem

  while (!stack.isEmpty()) {
    nextItem = stack.pop() as int
    if (nextItem > current) {
      count++
    }
    current = nextItem
  }

  return count
}

println increaseCount()
