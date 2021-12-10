def loadInput() {
  def output = []
  new File('input').eachLine {
    output << it.trim()
  }
  return output
}

def getPoints(character) {
  switch (character) {
    case ')': return 1
    case ']': return 2
    case '}': return 3
    case '>': return 4
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
    '(': ')',
    '{': '}',
    '[': ']',
    '<': '>',
  ]
  def linePoints = []
  data.each { line ->
    long points = 0
    def part = []
    boolean isCorrupt = false
    for (int i = 0; i < line.size(); i++) {
      if (closing.contains(line[i])) {
        // Corrupt
        if (part.last() != matching[line[i]]) {
          points += getPoints(line[i])
          isCorrupt = true
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

    if (part.size() && !isCorrupt) {
      part = part.reverse()

      for (int i = 0; i < part.size(); i++) {
        points = points * 5 + getPoints(matching[part[i]])
      }

      linePoints << points
    }
  }

  linePoints.sort()
  int middle = Math.floor(((linePoints.size() - 1) / 2)).round()
  return linePoints[middle]
}

println corrupted()
