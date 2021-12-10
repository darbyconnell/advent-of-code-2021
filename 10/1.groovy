def loadInput() {
  def output = []
  new File('input').eachLine {
    output << it.trim()
  }
  return output
}

def getPoints(character) {
  switch (character) {
    case ')': return 3
    case ']': return 57
    case '}': return 1197
    case '>': return 25137
  }

  return 0
}

def corrupted() {
  def data = loadInput()
  def closing = [')', ']', '}', '>']
  def matching = [
    ')': '(',
    ']': '[',
    '}': '{',
    '>': '<',
  ]
  int points = 0
  data.each { line ->
    def part = []
    for (int i = 0; i < line.size(); i++) {
      if (closing.contains(line[i])) {
        // Corrupt
        if (part.last() != matching[line[i]]) {
          points += getPoints(line[i])
          break
        }
        else {
          // Remove the opening tag because we don't care about it any more as it has been closed
          part.removeLast()
        }
      }
      else {
        part << line[i]
      }
    }
  }

  return points
}

println corrupted()
